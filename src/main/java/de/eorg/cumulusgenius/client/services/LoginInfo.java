package de.eorg.cumulusgenius.client.services;

import java.io.Serializable;

public class LoginInfo implements Serializable {

  /**
 * @uml.property  name="loggedIn"
 */
private boolean loggedIn = false;
  /**
 * @uml.property  name="loginUrl"
 */
private String loginUrl;
  /**
 * @uml.property  name="logoutUrl"
 */
private String logoutUrl;
  /**
 * @uml.property  name="emailAddress"
 */
private String emailAddress;
  /**
 * @uml.property  name="nickname"
 */
private String nickname;

  /**
 * @return
 * @uml.property  name="loggedIn"
 */
public boolean isLoggedIn() {
    return loggedIn;
  }

  /**
 * @param loggedIn
 * @uml.property  name="loggedIn"
 */
public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }

  /**
 * @return
 * @uml.property  name="loginUrl"
 */
public String getLoginUrl() {
    return loginUrl;
  }

  /**
 * @param loginUrl
 * @uml.property  name="loginUrl"
 */
public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }

  /**
 * @return
 * @uml.property  name="logoutUrl"
 */
public String getLogoutUrl() {
    return logoutUrl;
  }

  /**
 * @param logoutUrl
 * @uml.property  name="logoutUrl"
 */
public void setLogoutUrl(String logoutUrl) {
    this.logoutUrl = logoutUrl;
  }

  /**
 * @return
 * @uml.property  name="emailAddress"
 */
public String getEmailAddress() {
    return emailAddress;
  }

  /**
 * @param emailAddress
 * @uml.property  name="emailAddress"
 */
public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  /**
 * @return
 * @uml.property  name="nickname"
 */
public String getNickname() {
    return nickname;
  }

  /**
 * @param nickname
 * @uml.property  name="nickname"
 */
public void setNickname(String nickname) {
    this.nickname = nickname;
  }
}
