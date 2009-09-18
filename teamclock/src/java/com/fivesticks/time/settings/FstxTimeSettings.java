/*
 * Created on Jun 12, 2004
 *
 */
package com.fivesticks.time.settings;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fivesticks.time.useradmin.UserPasswordComplexityEnum;
import com.fstx.stdlib.common.simpledate.SimpleDate;

/**
 * <p>
 * Basically just delivers settings from a Spring bean.
 * 
 * <p>
 * 8/31/2004--still need to figure out how to handle smtp settings, since those
 * are system wide and NOT system owner specific.
 * 
 * <p>
 * 11/8/2005--some user settings will override system settings.  Time zone is an example.
 * 
 * @author REID
 *  
 */
public class FstxTimeSettings implements Serializable {
    //shuji modifying to static Sep. 1 2004
    //rsc unmodified 10/1/2004

    private SettingFeatureSetTypeEnum featureSet;

    private int maxActiveUsers;

    private String systemName;

    private boolean systemTokenAllowed;
    
    
    private String logoURL;

    private String systemTimeZone;

    private boolean okToRememberPassword;

    private boolean okToLoginFromTimeclock;
    
    private String activityRounderType;

    private long activityDefaultProject;

    private long activityDefaultTask;

    private long todoDefaultProject;

    private String todoDefaultEnteredBy;

    private String todoDefaultAssignedTo;
    
    private boolean queueImportFromEmail;
    
    private String queueSmtpServer;
    
    private String queueSmtpServerFolder;
    
    private String queueSmtpServerUsername;
    
    private String queueSmtpServerPassword;

    private String timeClockRounderType;

    private String timeClockUserShiftType;

    private String timeClockPayPeriodType;

    private SimpleDate timeClockPayPeriodRefDate;
    
    

    private long calendarNormalDayStart;

    private long calendarNormalDayEnds;

    private long userPasswordLife;
    
    private UserPasswordComplexityEnum userPasswordComplexity; 
    
    private String smtpHost;
    
    private String smtpPort;

    private String smtpUsername;

    private String smtpPassword;

    private String mailFromName;
    
    private String mailFromAddress;

    private SimpleDate configDatabaseVersion;

    private SimpleDate activityPostedThrough;
    
//    private String ebayImagesFtpServer;
//    
//    private String ebayImagesFtpUsername;
//    
//    private String ebayImagesFtpPassword;
//    
//    private String ebayImagesFtpPath;
//    
//    private int ebayImagesWidthFull;
//    
//    private int ebayImagesWidthThumb;
//    
//    private String ebayImagesSrc;
//    
//    private long ebayDefaultCommission;
    
   // private String systemCounty;

    private Log log = LogFactory.getLog(FstxTimeSettings.class);

    /**
     * @return
     */
    public String getLogoURL() {
        return logoURL;
    }

    /**
     * @param string
     */
    public void setLogoURL(String string) {
        logoURL = string;
    }

    /**
     * @return
     */
    public boolean isOkToLoginFromTimeclock() {
        return okToLoginFromTimeclock;
    }

    /**
     * @return
     */
    public boolean isOkToRememberPassword() {
        return okToRememberPassword;
    }

    /**
     * @param b
     */
    public void setOkToLoginFromTimeclock(boolean b) {
        okToLoginFromTimeclock = b;
    }

    /**
     * @param b
     */
    public void setOkToRememberPassword(boolean b) {
        okToRememberPassword = b;
    }

    /**
     * @return
     */
    public String getTimeClockRounderType() {
        return timeClockRounderType;
    }

    /**
     * @param string
     */
    public void setTimeClockRounderType(String string) {
        timeClockRounderType = string;
    }

    /**
     * @return
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * @param string
     */
    public void setSystemName(String string) {
        systemName = string;
    }

    /**
     * @return
     */
    public SimpleDate getConfigDatabaseVersion() {
        return configDatabaseVersion;
    }

    /**
     * @return
     */
    public Log getLog() {
        return log;
    }

    /**
     * @return
     */
    public String getSmtpHost() {
        return smtpHost;
    }

    /**
     * @return
     */
    public String getSmtpPassword() {
        return smtpPassword;
    }

    /**
     * @return
     */
    public String getSmtpUsername() {
        return smtpUsername;
    }

    /**
     * @param date
     */
    public void setConfigDatabaseVersion(SimpleDate date) {
        configDatabaseVersion = date;
    }

    /**
     * @param log
     */
    public void setLog(Log log) {
        this.log = log;
    }

    /**
     * @param string
     */
    public void setSmtpHost(String string) {
        smtpHost = string;
    }

    /**
     * @param string
     */
    public void setSmtpPassword(String string) {
        smtpPassword = string;
    }

    /**
     * @param string
     */
    public void setSmtpUsername(String string) {
        smtpUsername = string;
    }

    /**
     * @return
     */
    public String getTimeClockUserShiftType() {
        return timeClockUserShiftType;
    }

    /**
     * @param string
     */
    public void setTimeClockUserShiftType(String string) {
        timeClockUserShiftType = string;
    }

    /**
     * @return
     */
    public long getCalendarNormalDayEnds() {
        return calendarNormalDayEnds;
    }

    /**
     * @return
     */
    public long getCalendarNormalDayStart() {
        return calendarNormalDayStart;
    }

    /**
     * @param l
     */
    public void setCalendarNormalDayEnds(long l) {
        calendarNormalDayEnds = l;
    }

    /**
     * @param l
     */
    public void setCalendarNormalDayStart(long l) {
        calendarNormalDayStart = l;
    }

    /**
     * @return
     */
    public String getActivityRounderType() {
        return activityRounderType;
    }

    /**
     * @param string
     */
    public void setActivityRounderType(String string) {
        activityRounderType = string;
    }

    /**
     * @return
     */
    public long getActivityDefaultProject() {
        return activityDefaultProject;
    }

    /**
     * @return
     */
    public long getActivityDefaultTask() {
        return activityDefaultTask;
    }

    /**
     * @param long1
     */
    public void setActivityDefaultProject(long long1) {
        activityDefaultProject = long1;
    }

    /**
     * @param string
     */
    public void setActivityDefaultTask(long longValue) {
        activityDefaultTask = longValue;
    }

    /**
     * @return Returns the timeClockPayPeriodRefDate.
     */
    public SimpleDate getTimeClockPayPeriodRefDate() {
        return timeClockPayPeriodRefDate;
    }

    /**
     * @param timeClockPayPeriodRefDate
     *            The timeClockPayPeriodRefDate to set.
     */
    public void setTimeClockPayPeriodRefDate(
            SimpleDate timeClockPayPeriodRefDate) {
        this.timeClockPayPeriodRefDate = timeClockPayPeriodRefDate;
    }

    /**
     * @return Returns the timeClockPayPeriodType.
     */
    public String getTimeClockPayPeriodType() {
        return timeClockPayPeriodType;
    }

    /**
     * @param timeClockPayPeriodType
     *            The timeClockPayPeriodType to set.
     */
    public void setTimeClockPayPeriodType(String timeClockPayPeriodType) {
        this.timeClockPayPeriodType = timeClockPayPeriodType;
    }

    /**
     * @return Returns the systemTimeZone.
     */
    public String getSystemTimeZone() {
        return systemTimeZone;
    }

    /**
     * @param systemTimeZone
     *            The systemTimeZone to set.
     */
    public void setSystemTimeZone(String systemTimeZone) {
        this.systemTimeZone = systemTimeZone;
    }

    /**
     * @return Returns the featureSet.
     */
    public SettingFeatureSetTypeEnum getFeatureSet() {
        return featureSet;
    }

    /**
     * @param featureSet
     *            The featureSet to set.
     */
    public void setFeatureSet(SettingFeatureSetTypeEnum featureSet) {
        this.featureSet = featureSet;
    }

    /**
     * @return Returns the activityPostedThrough.
     */
    public SimpleDate getActivityPostedThrough() {
        return activityPostedThrough;
    }

    /**
     * @param activityPostedThrough
     *            The activityPostedThrough to set.
     */
    public void setActivityPostedThrough(SimpleDate activityPostedThrough) {
        this.activityPostedThrough = activityPostedThrough;
    }

    /**
     * @return Returns the maxActiveUsers.
     */
    public int getMaxActiveUsers() {
        return maxActiveUsers;
    }

    /**
     * @param maxActiveUsers
     *            The maxActiveUsers to set.
     */
    public void setMaxActiveUsers(int maxActiveUsers) {
        this.maxActiveUsers = maxActiveUsers;
    }

    /**
     * @return Returns the todoDefaultAssignedTo.
     */
    public String getTodoDefaultAssignedTo() {
        return todoDefaultAssignedTo;
    }

    /**
     * @param todoDefaultAssignedTo
     *            The todoDefaultAssignedTo to set.
     */
    public void setTodoDefaultAssignedTo(String todoDefaultAssignedTo) {
        this.todoDefaultAssignedTo = todoDefaultAssignedTo;
    }

    /**
     * @return Returns the todoDefaultEnteredBy.
     */
    public String getTodoDefaultEnteredBy() {
        return todoDefaultEnteredBy;
    }

    /**
     * @param todoDefaultEnteredBy
     *            The todoDefaultEnteredBy to set.
     */
    public void setTodoDefaultEnteredBy(String todoDefaultEnteredBy) {
        this.todoDefaultEnteredBy = todoDefaultEnteredBy;
    }

    /**
     * @return Returns the todoDefaultProject.
     */
    public long getTodoDefaultProject() {
        return todoDefaultProject;
    }

    /**
     * @param todoDefaultProject
     *            The todoDefaultProject to set.
     */
    public void setTodoDefaultProject(long todoDefaultProject) {
        this.todoDefaultProject = todoDefaultProject;
    }
    /**
     * @return Returns the userPasswordComplexity.
     */
    public UserPasswordComplexityEnum getUserPasswordComplexity() {
        return userPasswordComplexity;
    }
    /**
     * @param userPasswordComplexity The userPasswordComplexity to set.
     */
    public void setUserPasswordComplexity(
            UserPasswordComplexityEnum userPasswordComplexity) {
        this.userPasswordComplexity = userPasswordComplexity;
    }
    /**
     * @return Returns the userPasswordLife.
     */
    public long getUserPasswordLife() {
        return userPasswordLife;
    }
    /**
     * @param userPasswordLife The userPasswordLife to set.
     */
    public void setUserPasswordLife(long userPasswordLife) {
        this.userPasswordLife = userPasswordLife;
    }
    public boolean isQueueImportFromEmail() {
        return queueImportFromEmail;
    }
    public void setQueueImportFromEmail(boolean importFromEmailToQueue) {
        this.queueImportFromEmail = importFromEmailToQueue;
    }
    public String getQueueSmtpServer() {
        return queueSmtpServer;
    }
    public void setQueueSmtpServer(String smtpServer) {
        this.queueSmtpServer = smtpServer;
    }
    public String getQueueSmtpServerFolder() {
        return queueSmtpServerFolder;
    }
    public void setQueueSmtpServerFolder(String smtpServerFolder) {
        this.queueSmtpServerFolder = smtpServerFolder;
    }
    public String getQueueSmtpServerPassword() {
        return queueSmtpServerPassword;
    }
    public void setQueueSmtpServerPassword(String smtpServerPassword) {
        this.queueSmtpServerPassword = smtpServerPassword;
    }
    public String getQueueSmtpServerUsername() {
        return queueSmtpServerUsername;
    }
    public void setQueueSmtpServerUsername(String smtpServerUsername) {
        this.queueSmtpServerUsername = smtpServerUsername;
    }
//    /**
//     * @return Returns the ebayImagesFtpPassword.
//     */
//    public String getEbayImagesFtpPassword() {
//        return ebayImagesFtpPassword;
//    }
//    /**
//     * @param ebayImagesFtpPassword The ebayImagesFtpPassword to set.
//     */
//    public void setEbayImagesFtpPassword(String ebayImagesFtpPassword) {
//        this.ebayImagesFtpPassword = ebayImagesFtpPassword;
//    }
//    /**
//     * @return Returns the ebayImagesFtpPath.
//     */
//    public String getEbayImagesFtpPath() {
//        return ebayImagesFtpPath;
//    }
//    /**
//     * @param ebayImagesFtpPath The ebayImagesFtpPath to set.
//     */
//    public void setEbayImagesFtpPath(String ebayImagesFtpPath) {
//        this.ebayImagesFtpPath = ebayImagesFtpPath;
//    }
//    /**
//     * @return Returns the ebayImagesFtpServer.
//     */
//    public String getEbayImagesFtpServer() {
//        return ebayImagesFtpServer;
//    }
//    /**
//     * @param ebayImagesFtpServer The ebayImagesFtpServer to set.
//     */
//    public void setEbayImagesFtpServer(String ebayImagesFtpServer) {
//        this.ebayImagesFtpServer = ebayImagesFtpServer;
//    }
//    /**
//     * @return Returns the ebayImagesFtpUsername.
//     */
//    public String getEbayImagesFtpUsername() {
//        return ebayImagesFtpUsername;
//    }
//    /**
//     * @param ebayImagesFtpUsername The ebayImagesFtpUsername to set.
//     */
//    public void setEbayImagesFtpUsername(String ebayImagesFtpUsername) {
//        this.ebayImagesFtpUsername = ebayImagesFtpUsername;
//    }
//    /**
//     * @return Returns the ebayImagesSrc.
//     */
//    public String getEbayImagesSrc() {
//        return ebayImagesSrc;
//    }
//    /**
//     * @param ebayImagesSrc The ebayImagesSrc to set.
//     */
//    public void setEbayImagesSrc(String ebayImagesSrc) {
//        this.ebayImagesSrc = ebayImagesSrc;
//    }
//    /**
//     * @return Returns the ebayImagesWidthFull.
//     */
//    public int getEbayImagesWidthFull() {
//        return ebayImagesWidthFull;
//    }
//    /**
//     * @param ebayImagesWidthFull The ebayImagesWidthFull to set.
//     */
//    public void setEbayImagesWidthFull(int ebayImagesWidthFull) {
//        this.ebayImagesWidthFull = ebayImagesWidthFull;
//    }
//    /**
//     * @return Returns the ebayImagesWidthThumb.
//     */
//    public int getEbayImagesWidthThumb() {
//        return ebayImagesWidthThumb;
//    }
//    /**
//     * @param ebayImagesWidthThumb The ebayImagesWidthThumb to set.
//     */
//    public void setEbayImagesWidthThumb(int ebayImagesWidthThumb) {
//        this.ebayImagesWidthThumb = ebayImagesWidthThumb;
//    }
//    /**
//     * @return Returns the ebayDefaultCommission.
//     */
//    public long getEbayDefaultCommission() {
//        return ebayDefaultCommission;
//    }
//    /**
//     * @param ebayDefaultCommission The ebayDefaultCommission to set.
//     */
//    public void setEbayDefaultCommission(long ebayDefaultCommission) {
//        this.ebayDefaultCommission = ebayDefaultCommission;
//    }



    /**
     * @return Returns the systemTokenAllowed.
     */
    public boolean isSystemTokenAllowed() {
        return systemTokenAllowed;
    }

    /**
     * @param systemTokenAllowed The systemTokenAllowed to set.
     */
    public void setSystemTokenAllowed(boolean systemTokenAllowed) {
        this.systemTokenAllowed = systemTokenAllowed;
    }

    /**
     * @return Returns the smtpPort.
     */
    public String getSmtpPort() {
        return smtpPort;
    }

    /**
     * @param smtpPort The smtpPort to set.
     */
    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    /**
     * @return Returns the mailFromAddress.
     */
    public String getMailFromAddress() {
        return mailFromAddress;
    }

    /**
     * @param mailFromAddress The mailFromAddress to set.
     */
    public void setMailFromAddress(String mailFromAddress) {
        this.mailFromAddress = mailFromAddress;
    }

    /**
     * @return Returns the mailFromName.
     */
    public String getMailFromName() {
        return mailFromName;
    }

    /**
     * @param mailFromName The mailFromName to set.
     */
    public void setMailFromName(String mailFromName) {
        this.mailFromName = mailFromName;
    }

//    public String getSystemCounty() {
//        return systemCounty;
//    }
//
//    public void setSystemCounty(String systemCounty) {
//        this.systemCounty = systemCounty;
//    }
}