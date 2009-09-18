/*
 * Created on Mar 6, 2005 by REID
 */
package com.fivesticks.time.systemowner;

/**
 * @author REID
 */
public class MockSystemOwner extends SystemOwner {

    	public MockSystemOwner() {
    	    this.setId(new Long(1));
    	    this.setActivated(true);
    	    this.setAddress1("6957 Test Ave");
    	    this.setAddress2("Ste. 202");
    	    this.setCity("Oak Park");
    	    this.setState("state");
    	    this.setCountry("US");
    	    this.setPostalCode("post");
    	    this.setContactEmail("rsc1@fivesticks.com");
    	    this.setContactName("contact name");
    	    this.setContactPhone("708-990-2233");
    	}
}
