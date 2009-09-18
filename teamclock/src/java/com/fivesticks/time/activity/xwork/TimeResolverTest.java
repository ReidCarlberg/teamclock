/*

 Five Sticks
 6957 W. North Ave., #202
 Chicago, IL 60302
 USA
 http://www.fivesticks.com
 mailto:info@fivesticks.com

 Copyright (c) 2003-2004, Five Sticks Publications, Inc.
 All rights reserved.

 Redistribution and use in source and binary forms, 
 with or without modification, are permitted provided
 that the following conditions are met:

 * Redistributions of source code must retain 
 the above copyright notice, this list of conditions 
 and the following disclaimer.
 * Redistributions in binary form must reproduce 
 the above copyright notice, this list of conditions 
 and the following disclaimer in the documentation 
 and/or other materials provided with the distribution.
 * Neither the name of the Five Sticks Publications, Inc.,
 nor the names of its contributors may be used to 
 endorse or promote products derived from this software 
 without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND 
 CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, 
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR 
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, 
 BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR 
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE 
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF 
 SUCH DAMAGE.

 license: http://www.opensource.org/licenses/bsd-license.php

 This software uses a variety of Open Source software as
 a foundation.  See the file 

 [your install]/WEB-INF/component-acknowledgement.txt
 
 For more information.
 */
/*
 * Created on Sep 9, 2003
 *
 */
package com.fivesticks.time.activity.xwork;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 *  
 */
public class TimeResolverTest extends TestCase {

    /**
     *  
     */
    public TimeResolverTest() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public TimeResolverTest(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    public void testBasicA() throws Exception {
        Date today = SimpleDate.factory.build("2002-10-12").getDate();
        String timestring = "10:30 a";

        Date resolved = new TimeResolver(today, timestring).resolve();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm a");

        String s = sdf.format(resolved);

        log.info("formatted: " + s);
        assertTrue(s.equals("2002-10-12 10:30 AM"));

        log.info(resolved);

    }

    public void testBasicB() throws Exception {
        Date today = SimpleDate.factory.build("2002-10-12").getDate();
        String timestring = "10:30 p";

        Date resolved = new TimeResolver(today, timestring).resolve();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm a");

        String s = sdf.format(resolved);

        log.info("formatted: " + s);
        assertTrue(s.equals("2002-10-12 10:30 PM"));

        log.info(resolved);

    }

    public void testBasicC() throws Exception {
        Date today = SimpleDate.factory.build("2002-10-12").getDate();
        String timestring = "10:30p";

        Date resolved = new TimeResolver(today, timestring).resolve();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm a");

        String s = sdf.format(resolved);

        log.info("formatted: " + s);
        assertTrue(s.equals("2002-10-12 10:30 PM"));

        log.info(resolved);

    }

    public void testBasicD() throws Exception {
        Date today = SimpleDate.factory.build("2002-10-12").getDate();
        String timestring = "10.40p";

        Date resolved = new TimeResolver(today, timestring).resolve();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm a");

        String s = sdf.format(resolved);

        log.info("formatted: " + s);
        assertTrue(s.equals("2002-10-12 10:40 PM"));

        log.info(resolved);

    }

    protected static Log log = LogFactory.getLog(TimeResolverTest.class
            .getName());
}