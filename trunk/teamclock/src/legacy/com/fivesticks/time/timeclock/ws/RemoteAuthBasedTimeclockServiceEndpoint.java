/*
 * Created on Oct 18, 2005
 *
 */
package com.fivesticks.time.timeclock.ws;

import com.fivesticks.time.timeclock.FstxTimeclockBD;
import com.fivesticks.time.timeclock.FstxTimeclockBDException;
import com.fivesticks.time.timeclock.FstxTimeclockBDFactory;
import com.fivesticks.time.timeclock.FstxTimeclockEventEnum;
import com.fivesticks.time.timeclock.UserShiftRecord;
import com.fivesticks.time.timeclock.UserShiftRecordBuilder;
import com.fivesticks.time.timeclock.UserShiftRecordBuilderException;
import com.fivesticks.time.ws.AbstractAuthBasedAuthenticationService;

public class RemoteAuthBasedTimeclockServiceEndpoint extends
        AbstractAuthBasedAuthenticationService implements
        RemoteAuthTimeclockService {

    // private static Log log =
    // LogFactory.getLog(RemoteTimeclockServiceEndpoint.class);

    public String punchIn(String token, String username, String password) {

        return handleEvent(FstxTimeclockEventEnum.CLOCK_IN, token, username,
                password);
    }

    public String punchOut(String token, String username, String password) {

        return handleEvent(FstxTimeclockEventEnum.CLOCK_OUT, token, username,
                password);
    }

    public String breakStart(String token, String username, String password) {

        return handleEvent(FstxTimeclockEventEnum.BREAK_START, token, username,
                password);
    }

    public String breakStop(String token, String username, String password) {

        return handleEvent(FstxTimeclockEventEnum.BREAK_STOP, token, username,
                password);
    }

    public String getStatus(String token, String username, String password) {

        String ret = null;

        ret = this.handleCommonPrep(token, username, password);

        if (ret != null) {
            return ret;
        }
        UserShiftRecord shift = null;

        /*
         * RSC 2005-11-09 requires a resolved date.
         */
        // Date targetDate = this.getResolver().resolve(new Date());
        try {
            shift = new UserShiftRecordBuilder(this.systemOwner)
                    .buildByResolvedStartDate(this.user, this.sessionContext.getResolvedNow());
        } catch (UserShiftRecordBuilderException e) {
            return FAILED_USER_SHIFT_BUIDLER;
        }
        return shift.getStatus().getFriendlyName();
    }

    private String handleEvent(FstxTimeclockEventEnum event, String token,
            String username, String password) {

        String ret = null;

        ret = this.handleCommonPrep(token, username, password);

        if (ret != null) {
            return ret;
        }

        // SessionContext sessionContext =
        // SessionContext.factory.build(this.user, this.systemOwner);

        FstxTimeclockBD timeclock = FstxTimeclockBDFactory.factory
                .build(sessionContext);

        try {
            timeclock.handleEvent(this.user, event);
        } catch (FstxTimeclockBDException e) {
            ret = FAILED_EVENT + e.getMessage();
            return ret;
        }

        return SUCCESS;

    }

}
