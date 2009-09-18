/*
 * Created on Oct 17, 2005
 *
 */
package com.fivesticks.time.tokens;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

import com.fivesticks.time.common.AbstractServiceDelegate;
import com.fivesticks.time.common.util.RandomStringBuilder;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.CustomerServiceDelegateException;
import com.fivesticks.time.customer.CustomerServiceDelegateFactory;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.customer.ProjectBDException;
import com.fivesticks.time.customer.ProjectServiceDelegateFactory;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateException;
import com.fivesticks.time.systemowner.SystemOwnerServiceDelegateFactory;
import com.fstx.stdlib.authen.users.User;
import com.fstx.stdlib.authen.users.UserBDException;
import com.fstx.stdlib.authen.users.UserBDFactory;

public class TokenServiceDelegateImpl extends AbstractServiceDelegate implements
        TokenServiceDelegate {

    private Random random = new Random(new Date().getTime());

    private static int MINIMUM_LENGTH = 50;

    private static int MAXIMUM_LENGTH = 100;

    static String TYPE_SYSTEM = "system";

    static String TYPE_USER = "user";

    static String TYPE_CUSTOMER = "customer";

    static String TYPE_PROJECT = "project";

    public String findToken(SystemOwner systemOwner)
            throws TokenServiceDelegateException {

       return this.findToken(TYPE_SYSTEM,systemOwner.getKey(),null,null,null);
       
    }

    public String findToken(User user)
            throws TokenServiceDelegateException {

        return this.findToken(TYPE_USER,null,user.getUsername(),null,null);
        
    }

    public String findToken(String type, String ownerKey, String username,
            Long custId, Long projId) throws TokenServiceDelegateException {
        TokenCriteriaParameters p = new TokenCriteriaParameters();
        p.setType(type);
        p.setActiveSetting(new Boolean(true));
        p.setOwnerKey(ownerKey);
        p.setUsername(username);

        return this.findSingle(p).getToken();
    }

    public SystemOwner getSystemOwnerFromToken(String token)
            throws TokenServiceDelegateException {

        TokenCriteriaParameters p = new TokenCriteriaParameters();
        p.setType(TYPE_SYSTEM);
        p.setActiveSetting(new Boolean(true));
        p.setToken(token);

        Token r = this.findSingle(p);

        SystemOwner ret;
        try {
            ret = SystemOwnerServiceDelegateFactory.factory.build().get(
                    r.getOwnerKey());
        } catch (SystemOwnerServiceDelegateException e) {
            throw new TokenServiceDelegateException(e);
        }

        return ret;
    }

    public User getUserFromToken(String token)
            throws TokenServiceDelegateException {

        TokenCriteriaParameters p = new TokenCriteriaParameters();
        p.setType(TYPE_USER);
        p.setActiveSetting(new Boolean(true));
        p.setToken(token);

        Token r = this.findSingle(p);

        User ret = null;

        try {
            ret = UserBDFactory.factory.build().getByUsername(r.getUsername());
        } catch (UserBDException e) {
            throw new TokenServiceDelegateException(e);
        }

        return ret;
    }
    
    public Customer getCustomerFromToken(String token) throws TokenServiceDelegateException {
        
        TokenCriteriaParameters p = new TokenCriteriaParameters();
        p.setType(TYPE_CUSTOMER);
        p.setActiveSetting(new Boolean(true));
        p.setToken(token);

        Token r = this.findSingle(p);

        Customer ret = null;

        try {
            ret = CustomerServiceDelegateFactory.factory.build(this.getSystemOwner(r.getOwnerKey())).getFstxCustomer(r.getCustId());
        } catch (CustomerServiceDelegateException e) {
            throw new TokenServiceDelegateException(e);
        }

        return ret;
    }

    public Project getProjectFromToken(String token) throws TokenServiceDelegateException {
        
        TokenCriteriaParameters p = new TokenCriteriaParameters();
        p.setType(TYPE_PROJECT);
        p.setActiveSetting(new Boolean(true));
        p.setToken(token);

        Token r = this.findSingle(p);

        Project ret = null;

        try {
            ret = ProjectServiceDelegateFactory.factory.build(this.getSystemOwner(r.getOwnerKey())).getFstxProject(r.getProjId());
        } catch (ProjectBDException e) {
            throw new TokenServiceDelegateException(e);
        }

        return ret;
    }    
    
    private SystemOwner getSystemOwner(String ownerKey) throws TokenServiceDelegateException {
        try {
            return SystemOwnerServiceDelegateFactory.factory.build().get(ownerKey);
        } catch (SystemOwnerServiceDelegateException e) {
            throw new TokenServiceDelegateException(e);
            
        }
    }

    public void revokeToken(String token) throws TokenServiceDelegateException {
        TokenCriteriaParameters p = new TokenCriteriaParameters();
        p.setActiveSetting(new Boolean(true));
        p.setToken(token);

        Token r = this.findSingle(p);
        r.setActive(false);
        r.setRevoked(new Date());

        this.getDao().save(r);
    }

    public String generateSystemToken(SystemOwner systemOwner)
            throws TokenServiceDelegateException {

        return generateToken(TYPE_SYSTEM,systemOwner.getKey(),null,null,null);
    }

    public String generateUserToken(User user)
            throws TokenServiceDelegateException {

        return generateToken(TYPE_USER,null,user.getUsername(),null,null);
    }
    
    public String generateCustomerToken(Customer customer) throws TokenServiceDelegateException {
        
        return generateToken(TYPE_CUSTOMER, customer.getOwnerKey(),null,customer.getId(),null);
    }

    public String generateProjectToken(Project project) throws TokenServiceDelegateException {
        
        return generateToken(TYPE_PROJECT,project.getOwnerKey(),null,null,project.getId());
    }    
    
    public String generateToken(String type, String ownerKey, String username, Long custId, Long projId) throws TokenServiceDelegateException {
        Token t = new Token();
        t.setType(type);
        t.setActive(true);
        t.setOwnerKey(ownerKey);
        t.setUsername(username);
        t.setCustId(custId);
        t.setProjId(projId);
        t.setIssued(new Date());
        t.setToken(generateUniqueToken());

        try {
            this.getDao().save(t);
        } catch (RuntimeException e) {
            throw new TokenServiceDelegateException(
                    "failed to create a " + type + " token");
        }

        return t.getToken();        
    }

    public Collection getAllActiveTokens() throws TokenServiceDelegateException {

        TokenCriteriaParameters p = new TokenCriteriaParameters();
        p.setActiveSetting(new Boolean(true));

        return this.find(p);
    }

    private String generateUniqueToken() throws TokenServiceDelegateException {
        String ret = null;

        // try 15 times.
        for (int i = 0; i < 15; i++) {
            String temp = this.generateKey();
            if (!isUsed(temp)) {
                ret = temp;
                break;
            }
        }

        if (ret == null)
            throw new TokenServiceDelegateException("couldn't find unique key.");

        return ret;
    }

    private boolean isUsed(String token)  {
        TokenCriteriaParameters p = new TokenCriteriaParameters();
        p.setToken(token);

        Collection c = this.find(p);

        if (c.size() == 0)
            return false;

        return true;
    }

    private Collection find(TokenCriteriaParameters params)
             {
        Collection c = this.getDao().find(params);
        return c;
    }

    private Token findSingle(TokenCriteriaParameters params)
            throws TokenServiceDelegateException {

        Token ret = null;

        Collection c = this.find(params);

        if (c.size() == 1) {
            ret = (Token) c.toArray()[0];
        } else {

            throw new TokenServiceDelegateException("not found");
        }

        return ret;
    }

    private String generateKey() {

        int keyLength = MINIMUM_LENGTH
                + Math
                        .abs(random.nextInt()
                                % (MAXIMUM_LENGTH - MINIMUM_LENGTH));

        return new RandomStringBuilder().buildRandomString(keyLength);

    }




}
