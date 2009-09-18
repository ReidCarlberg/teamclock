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
package com.fstx.stdlib.common.messages;

/**
 * @author Reid S. Carlberg
 * @version 1.0.0
 *  
 */
class MailHostImpl implements MailHost {

    private final String address;

    private final String username;

    private final String password;
    
    private final String port;

    protected MailHostImpl(final String address, final String username,
            final String password, final String port) {
        this.address = address;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    /**
     * @see com.ess.messages.MailHost#getAddress()
     */
    public String getAddress() {
        return address;
    }

    /**
     * @see com.ess.messages.MailHost#getImgtarget()
     */
    public String getUsername() {
        return username;
    }

    /**
     * @see com.ess.messages.MailHost#getPassword()
     */
    public String getPassword() {
        return password;
    }

    public String toString() {
        return getAddress() + ", " + getUsername() + ", " + getPassword();
    }

    public boolean equals(Object o) {
        boolean retValue = false;
        if (o instanceof MailHostImpl) {
            MailHostImpl test = (MailHostImpl) o;
            while (true) {
                if (!(test.getAddress() == null && getAddress() == null)
                        && !(test.getAddress() != null && test.getAddress()
                                .equals(getAddress()))) {
                    break;
                }
                if (!(test.getUsername() == null && getUsername() == null)
                        && !(test.getUsername() != null && test.getUsername()
                                .equals(getUsername()))) {
                    break;
                }
                if (!(test.getPassword() == null && getPassword() == null)
                        && !(test.getPassword() != null && test.getPassword()
                                .equals(getPassword()))) {
                    break;
                }

                retValue = true;
                break;
            }
        }
        return retValue;
    }

    /*
     * If a user and password are given we want to authenticate, if not we can't
     * authenticate so we can assume authentication is not needed.
     * 
     * (non-Javadoc)
     * 
     * @see com.fstx.stdlib.common.messages.MailHost#useAuthentication()
     */

    public boolean useAuthentication() {
        if (this.getUsername() != null && this.getPassword() != null) {
            return true;
        }

        return false;
    }

    /**
     * @return Returns the port.
     */
    public String getPort() {
        return port;
    }
}