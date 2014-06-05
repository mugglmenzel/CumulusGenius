package de.eorg.cumulusgenius.shared.cloudmapping.model;

import static org.jclouds.ec2.options.RunInstancesOptions.Builder.asType;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.jclouds.compute.ComputeServiceContextFactory;
import org.jclouds.ec2.EC2AsyncClient;
import org.jclouds.ec2.EC2Client;
import org.jclouds.ec2.domain.InstanceType;
import org.jclouds.ec2.domain.IpProtocol;
import org.jclouds.ec2.domain.KeyPair;
import org.jclouds.ec2.domain.Reservation;
import org.jclouds.ec2.domain.RunningInstance;
import org.jclouds.ec2.predicates.InstanceStateRunning;
import org.jclouds.net.IPSocket;
import org.jclouds.predicates.InetSocketAddressConnect;
import org.jclouds.predicates.RetryablePredicate;
import org.jclouds.rest.RestContext;

import com.google.common.collect.Iterables;

import de.eorg.cumulusgenius.shared.cloudmapping.model.mapping.Instance;

public class DeploymentManager {

	/**
	 * @uml.property  name="instance"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private final Instance instance;
	/**
	 * @uml.property  name="publicKey"
	 */
	private final String publicKey;
	/**
	 * @uml.property  name="secretKey"
	 */
	private final String secretKey;

	public DeploymentManager(Instance instance, String publicKey,
			String secretKey) {
		super();
		this.instance = instance;
		this.publicKey = publicKey;
		this.secretKey = secretKey;

	}

	/**
	 * @return
	 * @uml.property  name="instance"
	 */
	public Instance getInstance() {
		return instance;
	}

	public void deployInstance(String name) {
		String ami = this.instance.getAppliance().getName();
		String size = this.instance.getComputeService().getName();
		

		RestContext<EC2Client, EC2AsyncClient> context = new ComputeServiceContextFactory()
				.createContext("aws-ec2", publicKey, secretKey)
				.getProviderSpecificContext();

		// Get a synchronous client
		EC2Client client = context.getApi();

		try {
			KeyPair pair = createKeyPair(client, name);

			RunningInstance instance = createSecurityGroupKeyPairAndInstance(
					client, name, ami, size);

			System.out.printf("instance %s ready%n", instance.getId());
			System.out.printf("ip address: %s%n", instance.getIpAddress());
			System.out.printf("dns name: %s%n", instance.getDnsName());
			System.out.printf("login identity:%n%s%n", pair.getKeyMaterial());
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static RunningInstance createSecurityGroupKeyPairAndInstance(
			EC2Client client, String name, String ami, String size) throws TimeoutException {
		// create a new security group
		createSecurityGroupAndAuthorizePorts(client, name);

		// create a new instance
		RunningInstance instance = runInstance(client, name, name, ami, size);

		// await for the instance to start
		return blockUntilInstanceRunning(client, instance);
	}

	private static void createSecurityGroupAndAuthorizePorts(EC2Client client,
			String name) {
		System.out.printf("%d: creating security group: %s%n",
				System.currentTimeMillis(), name);
		client.getSecurityGroupServices().createSecurityGroupInRegion(null,
				name, name);
		for (int port : new int[] { 80, 8080, 443, 22 }) {
			client.getSecurityGroupServices()
					.authorizeSecurityGroupIngressInRegion(null, name,
							IpProtocol.TCP, port, port, "0.0.0.0/0");
		}
	}

	private static KeyPair createKeyPair(EC2Client client, String name) {
		System.out.printf("%d: creating keypair: %s%n",
				System.currentTimeMillis(), name);
		return client.getKeyPairServices().createKeyPairInRegion(null, name);
	}

	private static RunningInstance runInstance(EC2Client client,
			String securityGroupName, String keyPairName, String ami, String size) {
		String script = ""; /*
							 * new ScriptBuilder() // lamp install script
							 * .addStatement
							 * (exec("runurl run.alestic.com/apt/upgrade"))//
							 * .addStatement
							 * (exec("runurl run.alestic.com/install/lamp"))//
							 * .addStatement
							 * (exec("apt-get -y install openjdk-6-jdk"))// no
							 * license agreement! .render(OsFamily.UNIX);
							 */

		System.out.printf("%d: running instance%n", System.currentTimeMillis());

		Reservation<? extends RunningInstance> reservation = client
				.getInstanceServices().runInstancesInRegion(null, null, // allow
						// ec2
						// to
						// chose
						// an
						// availability
						// zone
						ami, // ami we want to run
						// user data scripts
						1, // minimum instances
						1, // maximum instances
						asType(InstanceType.M1_SMALL) // smallest instance size
								.withKeyName(keyPairName) // key I created above
								.withSecurityGroup(securityGroupName)); // group
																		// I
		// created
		// above
		// .withUserData(script.getBytes())); // script to
		// run as
		// root

		return Iterables.getOnlyElement(reservation);

	}

	private static RunningInstance blockUntilInstanceRunning(EC2Client client,
			RunningInstance instance) throws TimeoutException {
		// create utilities that wait for the instance to finish
		RetryablePredicate<RunningInstance> runningTester = new RetryablePredicate<RunningInstance>(
				new InstanceStateRunning(client), 180, 5, TimeUnit.SECONDS);

		System.out.printf("%d: %s awaiting instance to run %n",
				System.currentTimeMillis(), instance.getId());
		if (!runningTester.apply(instance))
			throw new TimeoutException("timeout waiting for instance to run: "
					+ instance.getId());

		instance = findInstanceById(client, instance.getId());

		RetryablePredicate<IPSocket> socketTester = new RetryablePredicate<IPSocket>(
				new InetSocketAddressConnect(), 300, 1, TimeUnit.SECONDS);
		System.out.printf("%d: %s awaiting ssh service to start%n",
				System.currentTimeMillis(), instance.getIpAddress());
		if (!socketTester.apply(new IPSocket(instance.getIpAddress(), 22)))
			throw new TimeoutException("timeout waiting for ssh to start: "
					+ instance.getIpAddress());

		System.out.printf("%d: %s ssh service started%n",
				System.currentTimeMillis(), instance.getIpAddress());

		System.out.printf("%d: %s awaiting http service to start%n",
				System.currentTimeMillis(), instance.getIpAddress());
		if (!socketTester.apply(new IPSocket(instance.getIpAddress(), 80)))
			throw new TimeoutException("timeout waiting for http to start: "
					+ instance.getIpAddress());

		System.out.printf("%d: %s http service started%n",
				System.currentTimeMillis(), instance.getIpAddress());
		return instance;
	}

	private static RunningInstance findInstanceById(EC2Client client,
			String instanceId) {
		// search my account for the instance I just created
		Set<? extends Reservation<? extends RunningInstance>> reservations = client
				.getInstanceServices().describeInstancesInRegion(null,
						instanceId); // last parameter (ids) narrows the
		// search

		// since we refined by instanceId there should only be one instance
		return Iterables.getOnlyElement(Iterables.getOnlyElement(reservations));
	}
}
