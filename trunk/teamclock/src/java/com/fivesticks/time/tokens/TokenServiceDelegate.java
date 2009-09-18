/*
 * Created on Sep 17, 2005
 *
 */
package com.fivesticks.time.tokens;

import java.util.Collection;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fstx.stdlib.authen.users.User;

public interface TokenServiceDelegate {

    public String findToken(SystemOwner systemOwner) throws TokenServiceDelegateException;
    
    public String findToken(User user) throws TokenServiceDelegateException;
    
    public SystemOwner getSystemOwnerFromToken(String token) throws TokenServiceDelegateException;
    
    public User getUserFromToken(String token) throws TokenServiceDelegateException;

    public Customer getCustomerFromToken(String token) throws TokenServiceDelegateException;

    public Project getProjectFromToken(String token) throws TokenServiceDelegateException;

    public void revokeToken(String token) throws TokenServiceDelegateException;
    
    public String generateSystemToken(SystemOwner systemOwner) throws TokenServiceDelegateException;
    
    public String generateUserToken(User user) throws TokenServiceDelegateException;

    public String generateCustomerToken(Customer customer) throws TokenServiceDelegateException;

    public String generateProjectToken(Project project) throws TokenServiceDelegateException;

    public Collection getAllActiveTokens() throws TokenServiceDelegateException;
}
