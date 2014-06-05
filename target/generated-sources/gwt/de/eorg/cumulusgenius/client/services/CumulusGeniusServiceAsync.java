package de.eorg.cumulusgenius.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface CumulusGeniusServiceAsync
{

    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static CumulusGeniusServiceAsync instance;

        public static final CumulusGeniusServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (CumulusGeniusServiceAsync) GWT.create( CumulusGeniusService.class );
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint( GWT.getModuleBaseURL() + "cumulusGenius" );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
