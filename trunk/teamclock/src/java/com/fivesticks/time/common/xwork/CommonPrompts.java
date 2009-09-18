/*
 * Created on Jun 16, 2004
 *
 */
package com.fivesticks.time.common.xwork;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Reid
 *  
 */
public class CommonPrompts {

    public static final CommonPrompts singleton = new CommonPrompts();

    private Collection booleanSurrogates;

    public Collection getBooleanSurrogates() {
        if (booleanSurrogates == null)
            buildBooleanSurrogates();

        return booleanSurrogates;
    }

    /**
     *  
     */
    private void buildBooleanSurrogates() {
        booleanSurrogates = new ArrayList();

        booleanSurrogates.add(new KeyValueVO("true", "Yes"));
        booleanSurrogates.add(new KeyValueVO("false", "No"));
    }

}