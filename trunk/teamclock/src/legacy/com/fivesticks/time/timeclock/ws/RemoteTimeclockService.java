/*
 * Created on Oct 18, 2005
 *
 */
package com.fivesticks.time.timeclock.ws;

import com.fivesticks.time.ws.GeneralTimeService;

public interface RemoteTimeclockService extends GeneralTimeService {


    
    public String punchIn(String token, String username);
    
    public String punchOut(String token, String username);
    
    public String breakStart(String token, String username);
    
    public String breakStop(String token, String username);
    
    public String getStatus(String token, String username);
}
