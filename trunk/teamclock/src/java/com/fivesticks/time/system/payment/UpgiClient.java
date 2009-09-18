/*
 * Created on Aug 25, 2006
 *
 */
package com.fivesticks.time.system.payment;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

//
// UpgiClient 
// 
// Demonstrates how to connect to the IGS server via SSL in order to
// process a credit card transaction.
//
/**
 * 2006-08-25 using exact from sample code.
 */
class UpgiClient {
    
    // executeRequest()
    //
    // Gets a SSL connection with the server, formats and sends a HTTP request,
    // receives the server response, and parses the response into a Hashtable
    //
    public static Hashtable executeRequest(Hashtable htReq) {
        
        try {
            // get a SSL connection to the server
            SSLSocketFactory sf = (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket s = (SSLSocket)sf.createSocket("transactions.innovativegateway.com", 443);
            
            // create a PrintWriter for sending request to the server
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                    new OutputStreamWriter(
                        s.getOutputStream())));
            
            // send the HTTP header name/value pairs
            out.println("POST /servlet/com.gateway.aai.Aai HTTP/1.1");
            out.println("Content-Type: application/x-www-form-urlencoded");
            out.println("User-Agent: My.TeamClock.com");
            out.println("Host: transactions.innovativegateway.com");
            out.println("Connection: close");
            
            // build the body by enumerating through the specifed Hashtable
            String body = new String();
            for( Enumeration e = htReq.keys(); e.hasMoreElements(); ) {
                String key = (String)e.nextElement();
                String value = (String)htReq.get(key);
                // format according to IGS specifications.
                body += URLEncoder.encode(key) + "=" + URLEncoder.encode(value) + "&";
            }
            
            // specify the content length and the blank line following the HTTP heades
            out.println("Content-Length: "+body.length());
            out.println();
            
            // send the body and flush the output
            out.print(body);
            out.flush();
            
            // create a buffered reader for reading the server's response
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            // get the last line of the response
            // NOTE: this example is not validating the HTTP response codes.
            String line = null;
            String lastLine = null;
            while( (line = in.readLine()) != null ) {
                lastLine = line;
            }
            
            // decode the response fields into a Hashtable
            Hashtable resp = urlDecodeFields(lastLine, true);
           
            // close the socket
            s.close();
            
            // return the response.
            return resp;
            
        }
        catch(Exception e) {
            return null;
        }
    }
    
    // urlDecodeFields()
    //
    // Takes a string which is url encoded and delimited with the '=' and '|' characters
    // and inserts the key/value pairs into the returned Hashtable.
    //
    private static Hashtable urlDecodeFields( String encoded, boolean lowercase ) {

        Hashtable   nameValue = new Hashtable();
        String      name;
        String      value;

        // Parse & decode the fields
        try {
            int strLoc = 0;

            // Skip leading |
            if ( encoded.indexOf( "|" ) == 0 )
                strLoc = 1;

            int ampLoc = encoded.indexOf( "|", strLoc );
            if ( ampLoc == -1 ) ampLoc = encoded.length();

            while( strLoc < encoded.length() ) {

                // Find index to "=" char
                int equalsLoc = encoded.indexOf( "=", strLoc );

                // Get field name
                name = encoded.substring( strLoc, equalsLoc );
                name = URLDecoder.decode( name );
                if ( lowercase ) name = name.toLowerCase();

                // Get field value
                if ( equalsLoc+1 > encoded.length() )
                    value = "";
                else {
                    value = encoded.substring( equalsLoc+1, ampLoc );
                    value = URLDecoder.decode( value );
                }

                nameValue.put( name, value );

                // Adjust string index
                strLoc = ampLoc + 1;

                // Find index to the next "|" char
                ampLoc = encoded.indexOf( "|", strLoc );
                if ( ampLoc == -1 ) ampLoc = encoded.length();
            }
        }
        catch( Exception ex ) {};

        return nameValue;
    }

}

