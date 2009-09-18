/*
 * Created on Nov 8, 2005
 *
 */
package com.fivesticks.time.common.util;

import java.util.Date;

import com.fstx.stdlib.common.simpledate.SimpleDate;

public abstract class AbstractRounder implements Rounder {

    public Date round(Date toRound) {

         return new GenericRounder().round(toRound, this.getRoundBy());
    }

    public SimpleDate round(SimpleDate toRound) {

         return new GenericRounder().round(toRound, this.getRoundBy());
    }

    public abstract int getRoundBy();
}
