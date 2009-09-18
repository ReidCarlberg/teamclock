/*
 * Created on Aug 25, 2006
 *
 */
package com.fivesticks.payments;


import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.*;

import com.fivesticks.common.util.TimerUtil;


class UpgiExample {
    
    public static void main(String[] arg) {
        
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
//        htReq.put("pw", "GateTest2002");
//        htReq.put("username", "GatewayTest");
        
        htReq.put("pw", "iaUb111515V");
        htReq.put("username", "FIVESTICKS15");
        
//        htReq.put("ccnumber", "5424000000000015");
        htReq.put("ccnumber", "4111111111111111");
        htReq.put("month", "12");
        htReq.put("year", "2005");
        htReq.put("fulltotal", "2.00");
        htReq.put("ccname", "Gateway Test");
        htReq.put("baddress", "4134");
        htReq.put("bcity", "Dallas");
        htReq.put("bstate", "TX");
        htReq.put("bzip", "75240");
        htReq.put("bcountry", "US");
        htReq.put("email","gatewaytest@gatewaytest.com");
        htReq.put("bphone", "972-999-9999");
        htReq.put("test_override_errors", "testing");
       
        Hashtable htResp = UpgiClient.executeRequest(htReq);
        
        
        for( Enumeration e = htResp.keys(); e.hasMoreElements(); ) {
            String key = (String)e.nextElement();
            String value = (String)htResp.get(key);
            System.out.println(key+"="+value);
        }
        
        timerUtil.stop();
      

    }
}