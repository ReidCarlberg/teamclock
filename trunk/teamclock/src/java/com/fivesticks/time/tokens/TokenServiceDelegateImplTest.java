/*
 * Created on Oct 18, 2005
 *
 */
package com.fivesticks.time.tokens;

import java.util.Collection;

import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.customer.Project;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.AbstractTimeTestCase;
import com.fstx.stdlib.authen.users.User;

public class TokenServiceDelegateImplTest extends AbstractTimeTestCase {

    TokenServiceDelegate sd = null;

    protected void setUp() throws Exception {
        super.setUp();
        sd = TokenServiceDelegateFactory.factory.build();
    }

    public void testBasic() throws Exception {

        Token t1 = TokenTestFactory.getPersisted(this.systemOwner);

        assertNotNull(t1.getId());

    }

    public void testIssueToSystemOwner() throws Exception {

        Collection cb4 = sd.getAllActiveTokens();

        assertEquals(0, cb4.size());

        String token = sd.generateSystemToken(this.systemOwner);

        assertNotNull(token);

        assertTrue(token.length() > 49);

        Collection c = sd.getAllActiveTokens();

        assertEquals(1, c.size());

        Token t = (Token) c.toArray()[0];

        assertEquals(t.getOwnerKey(), this.systemOwner.getKey());

        assertEquals(t.getType(), TokenServiceDelegateImpl.TYPE_SYSTEM);

        assertNotNull(t.getToken());

        assertTrue(t.getToken().length() > 50);

        assertTrue(t.isActive());

        assertNull(t.getUsername());

    }

    public void testGetSystemOwnerByToken() throws Exception {

        String token = sd.generateSystemToken(this.systemOwner);

        SystemOwner owner = sd.getSystemOwnerFromToken(token);

        assertNotNull(owner);

        assertEquals(owner.getKey(), this.systemOwner.getKey());
    }

    public void testGetSystemOwnerByTokenAndRevoke() throws Exception {

        String token = sd.generateSystemToken(this.systemOwner);

        SystemOwner owner = sd.getSystemOwnerFromToken(token);

        assertNotNull(owner);

        assertEquals(owner.getKey(), this.systemOwner.getKey());

        Collection cb4 = sd.getAllActiveTokens();
        assertEquals(1, cb4.size());

        sd.revokeToken(token);

        Collection cafter = sd.getAllActiveTokens();
        assertEquals(0, cafter.size());

        try {
            SystemOwner o2 = sd.getSystemOwnerFromToken(token);
            assertTrue(false);
        } catch (TokenServiceDelegateException e) {

        }

    }

    public void testIssueToUser() throws Exception {

        Collection cb4 = sd.getAllActiveTokens();

        assertEquals(0, cb4.size());

        String token = sd.generateUserToken(this.user);

        assertNotNull(token);

        assertTrue(token.length() > 49);

        Collection c = sd.getAllActiveTokens();

        assertEquals(1, c.size());

        Token t = (Token) c.toArray()[0];

        assertNull(t.getOwnerKey());

        assertEquals(t.getType(), TokenServiceDelegateImpl.TYPE_USER);

        assertNotNull(t.getToken());

        assertTrue(t.getToken().length() > 50);

        assertTrue(t.isActive());

        assertEquals(this.user.getUsername(), t.getUsername());

    }

    public void testGetUserByToken() throws Exception {

        String token1 = sd.generateUserToken(this.user);

        User u = sd.getUserFromToken(token1);

        assertNotNull(u);

        assertEquals(u.getUsername(), this.user.getUsername());

    }

    public void testGetUserByTokenWithRevoke() throws Exception {

        String token1 = sd.generateUserToken(this.user);

        User u = sd.getUserFromToken(token1);

        assertNotNull(u);

        assertEquals(u.getUsername(), this.user.getUsername());

        sd.revokeToken(token1);

        Collection c = sd.getAllActiveTokens();

        assertEquals(0, c.size());

        try {
            User u2 = sd.getUserFromToken(token1);
            assertTrue(false);
        } catch (TokenServiceDelegateException e) {

        }

    }
    
    public void testGetTokenByUser() throws Exception {
        
        String t = sd.generateUserToken(this.user);
        
        User u2 = sd.getUserFromToken(t);
        
        assertEquals(u2.getUsername(),this.user.getUsername());
        
        String t2 = sd.findToken(this.user);
        
        assertEquals(t,t2);
    }
    
    public void testCustomerToken() throws Exception {
        
        String tSys = sd.generateSystemToken(this.systemOwner);
        
        String tProj = sd.generateProjectToken(this.project);
        
        String t = sd.generateCustomerToken(this.customer);
        
        
        assertNotNull(t);
        
        Customer c2 = sd.getCustomerFromToken(t);
        
        assertNotNull(c2);
        assertEquals(c2.getId(),this.customer.getId());
        
        sd.revokeToken(t);
        
        try {
            Customer c3 = sd.getCustomerFromToken(t);
            assertTrue(false);
            
        } catch (TokenServiceDelegateException e) {
            
        }
        
        
    }
    
    public void testProjectToken() throws Exception {
        
        String tSys = sd.generateSystemToken(this.systemOwner);
        
        String tProj = sd.generateProjectToken(this.project);
        
        String t = sd.generateCustomerToken(this.customer);
        
        
        assertNotNull(t);
        
        Project c2 = sd.getProjectFromToken(tProj);
        
        assertNotNull(c2);
        assertEquals(c2.getId(),this.project.getId());
        
        sd.revokeToken(tProj);
        
        try {
            Project c3 = sd.getProjectFromToken(tProj);
            assertTrue(false);
            
        } catch (TokenServiceDelegateException e) {
            
        }
        
        
    }
    
    public void testWithABunchOfSystems() throws Exception {
        
        for (int i = 0; i < 25; i++) {
            SystemOwner so = SystemOwnerTestFactory.getOwner();
            String t = sd.generateSystemToken(so);
        }
        
        String tReal = sd.generateSystemToken(this.systemOwner);
        
        for (int i = 0; i < 25; i++) {
            SystemOwner so = SystemOwnerTestFactory.getOwner();
            String t = sd.generateSystemToken(so);
        }        
        
        SystemOwner soTest = sd.getSystemOwnerFromToken(tReal);
        
        assertEquals(soTest.getId(),this.systemOwner.getId());
    }
    

}
