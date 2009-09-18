/*
 * Created on Sep 27, 2004 by Reid
 */
package com.fivesticks.time.timeclock.util;

import junit.framework.TestCase;

import com.fivesticks.time.timeclock.PayPeriodTypeEnum;
import com.fstx.stdlib.common.simpledate.DatePair;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class PayPeriodDeterminatorTest extends TestCase {

    /*
     * @see TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
    }

    /*
     * tests saturday through sunday
     */
    public void testWeeklySimple() throws Exception {
        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("09/19/2004");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("09/19/2004");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_WEEKLY, testRefDate, testTargetDate
                        .getDate());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("9/19/2004"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "9/25/2004"));
    }

    /*
     * tests wednesday through tuesday
     */
    public void testWeeklySlightlyComplex() throws Exception {

        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("09/1/2004");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("09/19/2004");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_WEEKLY, testRefDate, testTargetDate
                        .getDate());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("9/15/2004"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "9/21/2004"));

    }

    public void testBiweeklySimple() throws Exception {
        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("09/19/2004");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("09/19/2004");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_BIWEEKLY, testRefDate,
                testTargetDate.getDate());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("9/19/2004"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "10/2/2004"));

    }

    public void testBiweeklySlightlyComplex() throws Exception {
        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("2/18/2004");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("04/12/2004");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_BIWEEKLY, testRefDate,
                testTargetDate.getDate());
        System.out.println("Start: " + res.getSimpleStart().getMmddyyyy());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("3/31/2004"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "4/13/2004"));

    }

    /*
     * Really only handles 1-15, 16 to EOM
     */
    public void testSemiMonthlySimple1() throws Exception {
        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("09/1/2004");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("09/19/2004");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_SEMIMONTHLY, testRefDate,
                testTargetDate.getDate());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("9/16/2004"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "9/30/2004"));
    }

    public void testSemiMonthlySimple2() throws Exception {
        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("09/1/2004");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("09/1/2004");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_SEMIMONTHLY, testRefDate,
                testTargetDate.getDate());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("9/1/2004"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "9/15/2004"));
    }

    public void testSemiMonthlyFebruaryLeapYear() throws Exception {
        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("02/1/2004");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("02/17/2004");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_SEMIMONTHLY, testRefDate,
                testTargetDate.getDate());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("2/16/2004"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "2/29/2004"));
    }

    public void testSemiMonthlyFebruaryStandardYear() throws Exception {
        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("02/1/2003");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("02/17/2003");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_SEMIMONTHLY, testRefDate,
                testTargetDate.getDate());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("2/16/2003"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "2/28/2003"));
    }

    public void testMonthlySimple1() throws Exception {
        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("09/1/2004");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("09/1/2004");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_MONTHLY, testRefDate,
                testTargetDate.getDate());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("9/1/2004"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "9/30/2004"));
    }

    public void testMonthlySimple2() throws Exception {
        SimpleDate testRefDate = SimpleDate.factory.buildMidnight("09/1/2004");
        SimpleDate testTargetDate = SimpleDate.factory
                .buildMidnight("10/1/2004");
        DatePair res = new PayPeriodDeterminator().getPayPeriodByDate(
                PayPeriodTypeEnum.PAYPERIOD_MONTHLY, testRefDate,
                testTargetDate.getDate());
        assertTrue(SimpleDate.factory.build(res.getStart()).getMmddyyyy()
                .equals("10/1/2004"));
        assertTrue(SimpleDate.factory.build(res.getEnd()).getMmddyyyy().equals(
                "10/31/2004"));
    }

}