/*
 * Created on Jun 24, 2005
 *
 */
package com.fivesticks.time.employee;

import java.io.Serializable;

import com.fivesticks.time.common.AbstractTimeObject;

/**
 * @author Reid
 *
 */
public class Employee extends AbstractTimeObject implements Serializable {

//    private Long id;
//    private String ownerKey;
    private String username;
    private String code;
    private String nameFirst;
    private String nameLast;
    private Double hourlyRate;
    private Long status;

    /**
     * @return Returns the code.
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code The code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return Returns the nameFirst.
     */
    public String getNameFirst() {
        return nameFirst;
    }
    /**
     * @param nameFirst The nameFirst to set.
     */
    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }
    /**
     * @return Returns the nameLast.
     */
    public String getNameLast() {
        return nameLast;
    }
    /**
     * @param nameLast The nameLast to set.
     */
    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    /**
     * @return Returns the status.
     */
    public Long getStatus() { 
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(Long status) {
        this.status = status;
    }
    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * @return Returns the hourlyRate.
     */
    public Double getHourlyRate() {
        return hourlyRate;
    }
    /**
     * @param hourlyRate The hourlyRate to set.
     */
    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
