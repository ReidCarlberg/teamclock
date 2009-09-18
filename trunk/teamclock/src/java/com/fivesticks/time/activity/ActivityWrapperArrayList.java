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
 * Created on Sep 10, 2003
 *
 */
package com.fivesticks.time.activity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Reid
 *  
 */
public class ActivityWrapperArrayList extends ArrayList {

    private double totalElapsed;
    
    private double totalChargeableElapsed;
    
    private static DecimalFormat df = new DecimalFormat("#,##0.000;(#)");
    
    public void calculateTotals() {
        

        Iterator i = this.iterator();

        while (i.hasNext()) {
            ActivityWrapper c = (ActivityWrapper) i.next();
            if (c.getActivity().getElapsed() != null)
                totalElapsed += c.getActivity().getElapsed().doubleValue();
            
            if (c.getActivity().getChargeableElapsed() != null)
                totalChargeableElapsed += c.getActivity().getChargeableElapsed().doubleValue();
        }

        
        
    }

    public String getFormattedTotal() {
        
        return this.getFormattedTotalElapsed();
    }
    
    public String getFormattedTotalElapsed() {
        
        return df.format(this.getTotalElapsed());
    }
    
    public String getFormattedTotalChargeableElapsed() {
        
        return df.format(this.getTotalChargeableElapsed());
    }

    /**
     * @return Returns the totalChargeableElapsed.
     */
    public double getTotalChargeableElapsed() {
        return totalChargeableElapsed;
    }

    /**
     * @param totalChargeableElapsed The totalChargeableElapsed to set.
     */
    public void setTotalChargeableElapsed(double totalChargeableElapsed) {
        this.totalChargeableElapsed = totalChargeableElapsed;
    }

    /**
     * @return Returns the totalElapsed.
     */
    
    public double getTotal() {
//        if (this.getTotalElapsed() == 0.0 && this.getTotalChargeableElapsed() == 0.0) {
//            this.calculateTotals();
//        }
        return this.getTotalElapsed();
    }
    
    public double getTotalElapsed() {
//        if (this.getTotalElapsed() == 0.0 && this.getTotalChargeableElapsed() == 0.0) {
//            this.calculateTotals();
//        }
        return totalElapsed;
    }

    /**
     * @param totalElapsed The totalElapsed to set.
     */
    public void setTotalElapsed(double totalElapsed) {
        if (this.getTotalElapsed() == 0.0 && this.getTotalChargeableElapsed() == 0.0) {
            this.calculateTotals();
        }
        this.totalElapsed = totalElapsed;
    }

}