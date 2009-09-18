/*
 * Created on Aug 25, 2006 by Reid
 */
package com.fivesticks.time.customer.payments;

import java.util.Enumeration;
import java.util.Hashtable;

import com.fivesticks.common.util.TimerUtil;
import com.fivesticks.time.customer.Customer;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.payments.PaymentMethod;

public class ProcessPaymentCommand {

    private static final String UPGI_USERNAME = "FIVESTICKS15";
    private static final String UPGI_PASSWORD = "iaUb111515V";
    
//    private static final String UPGI_USERNAME = "gatewaytest";
//    private static final String UPGI_PASSWORD = "GateTest2002";
    
    private Customer customer;
    
    private String amount;
    
    
    public ProcessPaymentCommand(Customer customer, String amount) {

    	this.customer = customer;
    	this.amount = amount;
        
    }
    
    public void execute() throws Exception {
        
        if (this.getAmount() == null) {
            throw new RuntimeException("parameters not set.");
        }
        
        Hashtable request = buildRequestMap();
        
        this.processRequest(request);
    }

    public Hashtable buildRequestMap() {
        
        TimerUtil timerUtil = TimerUtil.getInstanceAndStart("UPGI");
        
        Hashtable<String,String> htReq = new Hashtable<String,String>();
        htReq.put("target_app", "WebCharge_v5.06");
        htReq.put("response_mode", "simple");
        htReq.put("response_fmt", "delimited");
        htReq.put("upg_auth", "zxcvlkjh");
        htReq.put("card_type", this.customer.getPayMethod());
        htReq.put("delimited_fmt_field_delimiter", "=");
        htReq.put("delimited_fmt_include_fields", "true");
        htReq.put("delimited_fmt_value_delimiter", "|");
        htReq.put("trantype", "sale");
        
        htReq.put("pw", UPGI_PASSWORD);
        htReq.put("username", UPGI_USERNAME);
        
        htReq.put("ccnumber", this.customer.getPayNumber());
        htReq.put("month", this.customer.getPayMonth());
        htReq.put("year", this.customer.getPayYear());
        htReq.put("fulltotal", this.getAmount());
        htReq.put("ccname", this.customer.getPayName());
        htReq.put("baddress", this.customer.getPayStreet());
        htReq.put("bzip", this.customer.getPayZip());
//        htReq.put("test_override_errors", "testing");
       
        return htReq;
        
    }
    
    public void processRequest(Hashtable htReq) throws Exception {
        
        Hashtable htResp = UpgiClient.executeRequest(htReq);
        
        
        for( Enumeration e = htResp.keys(); e.hasMoreElements(); ) {
            String key = (String)e.nextElement();
            String value = (String)htResp.get(key);
            System.out.println(key+"="+value);
        }
        


        
    }



    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
