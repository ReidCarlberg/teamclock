/*
 * Created on Sep 27, 2004 by Reid
 */
package com.fivesticks.time.timeclock.util;

import java.util.Date;

import com.fivesticks.time.common.SessionContext;
import com.fivesticks.time.timeclock.PayPeriodTypeEnum;
import com.fstx.stdlib.common.simpledate.DatePair;
import com.fstx.stdlib.common.simpledate.DatePairBuilder;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * @author Reid
 */
public class PayPeriodDeterminator {

    public DatePair getBySessionTypeAndDate(SessionContext sessionContext,
            String periodType, Date date) throws PayPeriodDeterminatorException {
        PayPeriodTypeEnum payPeriodType = PayPeriodTypeEnum
                .getByName(sessionContext.getSettings()
                        .getTimeClockPayPeriodType());

        SimpleDate refDate = sessionContext.getSettings()
                .getTimeClockPayPeriodRefDate();

        DatePair targetPeriod = null;

        if (periodType.equalsIgnoreCase("current")) {
            targetPeriod = getCurrentPayPeriod(payPeriodType, refDate);
        } else if (periodType.equalsIgnoreCase("previous")) {
            targetPeriod = getPriorPayPeriod(payPeriodType, refDate);
        } else if (date == null) {
            throw new PayPeriodDeterminatorException(
                    "not enough information to determine pay period");
        } else {
            targetPeriod = getPayPeriodByDate(payPeriodType, refDate, date);
        }

        return targetPeriod;
    }

    public DatePair getCurrentPayPeriod(PayPeriodTypeEnum payPeriodTypeEnum,
            SimpleDate payPeriodRefDate) throws PayPeriodDeterminatorException {
        DatePair ret = null;

        ret = getPayPeriodByDate(payPeriodTypeEnum, payPeriodRefDate,
                new Date());

        return ret;
    }

    public DatePair getPriorPayPeriod(PayPeriodTypeEnum payPeriodTypeEnum,
            SimpleDate payPeriodRefDate) throws PayPeriodDeterminatorException {

        SimpleDate targetDate = SimpleDate.factory.buildMidnight();

        handleTargetDateAdjustment(payPeriodTypeEnum, targetDate);

        DatePair ret = null;

        ret = getPayPeriodByDate(payPeriodTypeEnum, payPeriodRefDate,
                targetDate.getDate());

        return ret;

    }

    /**
     * @param payPeriodTypeEnum
     * @param targetDate
     * @throws PayPeriodDeterminatorException
     */
    private void handleTargetDateAdjustment(
            PayPeriodTypeEnum payPeriodTypeEnum, SimpleDate targetDate)
            throws PayPeriodDeterminatorException {

        if (payPeriodTypeEnum == PayPeriodTypeEnum.PAYPERIOD_WEEKLY) {
            targetDate.advanceDay(-7);
        } else if (payPeriodTypeEnum == PayPeriodTypeEnum.PAYPERIOD_BIWEEKLY) {
            targetDate.advanceDay(-14);
        } else if (payPeriodTypeEnum == PayPeriodTypeEnum.PAYPERIOD_SEMIMONTHLY) {
            if (targetDate.getDayOfMonth() > 15) {
                targetDate.setDay(15);
            } else {
                targetDate.advanceMonth(-1);
                targetDate.setDay(16);
            }
        } else if (payPeriodTypeEnum == PayPeriodTypeEnum.PAYPERIOD_MONTHLY) {
            if (targetDate.getDayOfMonth() > 28) {
                targetDate.advanceDay(-3);
            }
            targetDate.advanceMonth(-1);
        } else {
            throw new PayPeriodDeterminatorException("invalid type");
        }
    }

    /**
     * @param payPeriodTypeEnum
     * @param payPeriodStart
     * @param payPeriodRefDate
     * @param date
     * @return @throws
     *         PayPeriodDeterminatorException
     */
    public DatePair getPayPeriodByDate(PayPeriodTypeEnum payPeriodTypeEnum,
            SimpleDate payPeriodRefDate, Date date)
            throws PayPeriodDeterminatorException {

        if (payPeriodTypeEnum == null || payPeriodRefDate == null
                || date == null) {
            throw new PayPeriodDeterminatorException(
                    "A key parameter is null.  Cannot determine pay period date range.");
        }

        DatePair ret = null;

        ret = handleGetPayPeriod(payPeriodTypeEnum, payPeriodRefDate, date);

        return ret;
    }

    /**
     * @param payPeriodTypeEnum
     * @param payPeriodStart
     * @param payPeriodRefDate
     * @param date
     * @return @throws
     *         PayPeriodDeterminatorException
     */
    private DatePair handleGetPayPeriod(PayPeriodTypeEnum payPeriodTypeEnum,
            SimpleDate payPeriodRefDate, Date date)
            throws PayPeriodDeterminatorException {

        DatePair ret = null;

        if (payPeriodTypeEnum == PayPeriodTypeEnum.PAYPERIOD_WEEKLY) {
            ret = handleWeekly(payPeriodRefDate, date);
        } else if (payPeriodTypeEnum == PayPeriodTypeEnum.PAYPERIOD_BIWEEKLY) {
            ret = handleBiweekly(payPeriodRefDate, date);
        } else if (payPeriodTypeEnum == PayPeriodTypeEnum.PAYPERIOD_SEMIMONTHLY) {
            ret = handleSemiMonthly(payPeriodRefDate, date);
        } else if (payPeriodTypeEnum == PayPeriodTypeEnum.PAYPERIOD_MONTHLY) {
            ret = handleMonthly(payPeriodRefDate, date);
        } else {
            throw new PayPeriodDeterminatorException("Invalid pay period type.");
        }

        return ret;
    }

    /**
     * @param payPeriodStart
     * @param payPeriodRefDate
     * @param date
     * @return
     */
    private DatePair handleWeekly(SimpleDate payPeriodRefDate, Date date) {

        //start date is the first date prior or equal to date matching the day
        // of week
        //of the payPeriodRefDate
        SimpleDate start = SimpleDate.factory.buildMidnight(date);
        SimpleDate stop = SimpleDate.factory.buildMidnight(date);

        SimpleDate working = SimpleDate.factory.buildMidnight(payPeriodRefDate
                .getDate());

        while (working.getDayOfWeek() != start.getDayOfWeek()) {
            start.advanceDay(-1);
        }

        working.advanceDay(-1);

        while (working.getDayOfWeek() != stop.getDayOfWeek()) {
            stop.advanceDay(1);
        }

        handleStopModify(stop);

        DatePair ret = DatePairBuilder.singleton.buildByRange(start.getDate(),
                stop.getDate());

        /*
         * stop date is the last date before that day occurs again.
         */

        return ret;
    }

    /**
     * @param stop
     */
    private void handleStopModify(SimpleDate stop) {
        stop.advanceDay(1);
        stop.advanceMilliseconds(-1);
    }

    /**
     * @param payPeriodStart
     * @param payPeriodRefDate
     * @param date
     * @return @throws
     *         PayPeriodDeterminatorException
     */
    private DatePair handleBiweekly(SimpleDate payPeriodRefDate, Date date)
            throws PayPeriodDeterminatorException {

        /*
         * payPeriodRefDate is a period start date.
         * 
         */
        /*
         * the only way to really get this is to start at the reference date,
         * and build forward until we have a date range that includes the target
         * date.
         */
        DatePair ret = null;

        SimpleDate working = SimpleDate.factory.buildMidnight(payPeriodRefDate
                .getDate());

        int i = 0;

        /*
         * We may need to search backwards, if we have a date before the
         * Refdate.
         */
        if (working.getDate().compareTo(date) > 0) {

            while (i < 200) {
                i++;

                DatePair current = getBiweeklyForDate(working);

                if (current.getStart().compareTo(date) <= 0) {
                    ret = current;
                    break;
                }

                working = current.getSimpleStart();
                working.advanceDay(-14);
            }

        } else {
            while (i < 200) {
                i++;

                DatePair current = getBiweeklyForDate(working);

                if (current.getEnd().compareTo(date) >= 0) {
                    ret = current;
                    break;
                }

                working = current.getSimpleEnd();
                working.advanceDay();
            }
        }
        if (ret == null) {
            throw new PayPeriodDeterminatorException(
                    "couldn't determine biweekly payperiod.");
        }

        return ret;
    }

    private DatePair getBiweeklyForDate(SimpleDate targetDate) {
        
        /*
         * so if the target date is 9/1/05, stop is 9/14/05.
         * 
         */
        SimpleDate working = SimpleDate.factory.buildMidnight(targetDate
                .getDate());

        SimpleDate stop = SimpleDate.factory
                .buildMidnight(targetDate.getDate());

        stop.advanceDay(13); //13 since the target date already includes 1 of
        // the days.

        handleStopModify(stop);

        DatePair ret = DatePairBuilder.singleton.buildByRange(targetDate
                .getDate(), stop.getDate());

        return ret;
    }

    /**
     * @param payPeriodStart
     * @param payPeriodRefDate
     * @param date
     * @return
     */
    private DatePair handleSemiMonthly(SimpleDate payPeriodRefDate, Date date) {

        SimpleDate working = SimpleDate.factory.buildMidnight(date);

        SimpleDate start = SimpleDate.factory.buildMidnight(date);
        SimpleDate stop = SimpleDate.factory.buildMidnight(date);

        if (working.getDayOfMonth() <= 15) {
            start.setDay(1);
            stop.setDay(15);
        } else {
            start.setDay(16);
            stop.setDay(getlastDayInMonth(stop).getDayOfMonth());
        }

        handleStopModify(stop);

        DatePair ret = DatePairBuilder.singleton.buildByRange(start.getDate(),
                stop.getDate());
        return ret;
    }

    private SimpleDate getlastDayInMonth(SimpleDate target) {
        SimpleDate ret = SimpleDate.factory.buildMidnight(target.getDate());
        SimpleDate working = SimpleDate.factory.buildMidnight(target.getDate());

        while (true) {

            working.advanceDay();

            if (working.getDayOfMonth() == 1) {
                break;
            }

            ret.advanceDay();

        }

        return ret;
    }

    /**
     * @param payPeriodStart
     * @param payPeriodRefDate
     * @param date
     * @return
     */
    private DatePair handleMonthly(SimpleDate payPeriodRefDate, Date date) {

        SimpleDate start = SimpleDate.factory.buildMidnight(date);

        SimpleDate stop = SimpleDate.factory.buildMidnight(date);

        start.setDay(1);

        stop.setDay(getlastDayInMonth(stop).getDayOfMonth());

        handleStopModify(stop);

        DatePair ret = new DatePair(start, stop);

        return ret;
    }

}