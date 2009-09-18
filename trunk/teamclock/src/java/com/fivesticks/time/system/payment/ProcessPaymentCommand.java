/*
 * Created on Aug 25, 2006 by Reid
 */
package com.fivesticks.time.system.payment;

import java.util.Enumeration;
import java.util.Hashtable;

import com.fivesticks.common.util.TimerUtil;
import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.payments.PaymentMethod;

public class ProcessPaymentCommand {

    private static final String UPGI_USERNAME = "FIVESTICKS15";
    private static final String UPGI_PASSWORD = "iaUb111515V";
    
    private PaymentMethod paymentMethod;
    
    private SystemOwner systemOwner;
    
    private String amount;
    
    
    public ProcessPaymentCommand(SystemOwner systemOwner, PaymentMethod paymentMethod, String amount) {
        this.systemOwner = systemOwner;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        
    }
    
    public void execute() throws Exception {
        
        if (this.getPaymentMethod() == null || this.getSystemOwner() == null || this.getAmount() == null) {
            throw new RuntimeException("parameters not set.");
        }
        
        Hashtable request = buildRequestMap();
        
        this.processRequest(request);
    }

    public Hashtable buildRequestMap() {
        
        TimerUtil timerUtil = TimerUtil.getInstanceAndStart("UPGI");
        
        // initialize a hash table with UPGI parameters
        Hashtable htReq = new Hashtable();
        htReq.put("target_app", "WebCharge_v5.06");
        htReq.put("response_mode", "simple");
        htReq.put("response_fmt", "delimited");
        htReq.put("upg_auth", "zxcvlkjh");
        htReq.put("card_type", "visa");
        htReq.put("delimited_fmt_field_delimiter", "=");
        htReq.put("delimited_fmt_include_fields", "true");
        htReq.put("delimited_fmt_value_delimiter", "|");
        
        htReq.put("pw", UPGI_PASSWORD);
        htReq.put("username", UPGI_USERNAME);
        
        htReq.put("ccnumber", this.getPaymentMethod().getNumber());
        htReq.put("month", this.getPaymentMethod().getExpiresMonth());
        htReq.put("year", this.getPaymentMethod().getExpiresYear());
        htReq.put("fulltotal", this.getAmount());
        htReq.put("ccname", this.getPaymentMethod().getNameOnCard());
        htReq.put("baddress", this.getPaymentMethod().getStreet());
        htReq.put("bcity", this.getPaymentMethod().getCity());
        htReq.put("bstate", this.getPaymentMethod().getState());
        htReq.put("bzip", this.getPaymentMethod().getZip());
        htReq.put("bcountry", this.getPaymentMethod().getCountry());
        
        htReq.put("email","gatewaytest@gatewaytest.com");
        htReq.put("bphone", this.getPaymentMethod().getPhone());
        htReq.put("test_override_errors", "testing");
       
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public SystemOwner getSystemOwner() {
        return systemOwner;
    }

    public void setSystemOwner(SystemOwner systemOwner) {
        this.systemOwner = systemOwner;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
