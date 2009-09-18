/*
 * Created on Dec 3, 2005
 *
 */
package com.fivesticks.time.ws.xwork;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.webwork.WebWorkStatics;
import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

public abstract class AbstractAuthenticationBasedServiceSupportAware extends ActionSupport implements
        AuthenticationBasedServiceSupportAware {

    private AuthenticationBasedServiceSupport authenticationBasedServiceSupport;

    private String token;

    private String username;

    private String password;

    private String action;

    private String result;

    private String verboseResult;
    
    public String getSessionRemoteAddress() {

        String ret = "Remote address not available";

        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext().get(WebWorkStatics.HTTP_REQUEST);
        
        if (request != null)
            ret = request.getRemoteAddr();

        return ret;
    }
    
    /**
     * @return Returns the action.
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            The action to set.
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return Returns the authenticationBasedServiceSupport.
     */
    public AuthenticationBasedServiceSupport getAuthenticationBasedServiceSupport() {
        return authenticationBasedServiceSupport;
    }

    /**
     * @param authenticationBasedServiceSupport
     *            The authenticationBasedServiceSupport to set.
     */
    public void setAuthenticationBasedServiceSupport(
            AuthenticationBasedServiceSupport authenticationBasedServiceSupport) {
        this.authenticationBasedServiceSupport = authenticationBasedServiceSupport;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return Returns the token.
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            The token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return Returns the result.
     */
    public String getResult() {
        return result;
    }

    /**
     * @param result
     *            The result to set.
     */
    public void setResult(String result) {
        this.result = result;
    }
    
    /**
     * @return Returns the verboseResult.
     */
    public String getVerboseResult() {
        return verboseResult;
    }

    /**
     * @param verboseResult
     *            The verboseResult to set.
     */
    public void setVerboseResult(String verboseResult) {
        this.verboseResult = verboseResult;
    }    
}
