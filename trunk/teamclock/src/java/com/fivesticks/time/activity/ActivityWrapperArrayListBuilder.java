/*
 * Created on Jun 17, 2004
 *
 */
package com.fivesticks.time.activity;

import java.util.Collection;
import java.util.Iterator;

import com.fivesticks.time.common.SessionContext;

/**
 * @author Reid
 *  
 */
public class ActivityWrapperArrayListBuilder {

    public ActivityWrapperArrayList buildFxtxTimeWrapperArrayList(
            Collection raw, SessionContext sessionContext) {

        ActivityWrapperArrayList fancy = new ActivityWrapperArrayList();

        Iterator orig = raw.iterator();

        while (orig.hasNext()) {
            ActivityWrapper wrap = new ActivityWrapper(
                    (Activity) orig.next());
            wrap.setSessionContext(sessionContext);
            fancy.add(wrap);
        }
        
        fancy.calculateTotals();

        //	request.setAttribute("list", fancy);

        return fancy;
    }

}