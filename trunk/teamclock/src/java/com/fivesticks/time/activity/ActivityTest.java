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
 * Created on Nov 1, 2003
 *
 */
package com.fivesticks.time.activity;

import java.util.Date;

import junit.framework.TestCase;

import com.fivesticks.time.systemowner.SystemOwner;
import com.fivesticks.time.systemowner.SystemOwnerTestFactory;
import com.fivesticks.time.testutil.JunitSettings;

/**
 * @author Reid
 *  
 */
public class ActivityTest extends TestCase {

    SystemOwner systemOwner;

    /*
     * (non-Javadoc)
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        JunitSettings.initializeTestSystem();
        systemOwner = new SystemOwnerTestFactory().buildWithDefaultSettings();
    }

    public void testBasic_A() throws Exception {
        Activity t = new Activity();
        t.setComments("comments");
        t.setDate(new Date());
        t.setElapsed(new Double(1.0));
        t.setProject("proj");
        t.setStart(new Date());
        t.setStop(new Date());
        t.setTask("task");
        t.setUsername("user");
        t.setProjectId(new Long(1));
        t.setOwnerKey(systemOwner.getKey());
        t = ActivityDAOFactory.factory.build().save(t);

        for (int i = 0; i < 100; i++) {
            t.setComments("comments " + i);
            t = ActivityDAOFactory.factory.build().save(t);
        }
    }

    public void testBasic_B() throws Exception {
        Activity t = new Activity();
        t.setComments("comments");
        t.setDate(new Date());
        t.setElapsed(new Double(1.0));
        t.setProject("proj");
        t.setStart(new Date());
        t.setStop(new Date());
        t.setTask("task");
        t.setUsername("user");
        t.setProjectId(new Long(1));
        t.setOwnerKey(systemOwner.getKey());
        t = ActivityDAOFactory.factory.build().save(t);

        for (int i = 0; i < 100; i++) {
            Activity t2 = ActivityDAOFactory.factory.build().get(t.getId());
            t2.setComments("comments " + i);
            t2 = ActivityDAOFactory.factory.build().save(t2);
        }
    }

}