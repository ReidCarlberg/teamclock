/*
 * Created on Aug 30, 2006
 *
 */
package com.fivesticks.time.contactv2;

import java.util.Date;

import com.fivesticks.time.common.dao.CriteriaParameters;

public class ContactV2CriteriaParameters extends ContactV2 implements
        CriteriaParameters {

    private Integer maxResults;
    private String nameFirstLike;
    private String nameLastLike;
    private String nameFormattedLike;
    private String organizationNameLike;
    private String organizationCityLike;
    
    private Date createDateRangeStart;
    private Date createDateRangeStop;

    private Date modifyDateRangeStart;
    private Date modifyDateRangeStop;

    private Long contactClassLuId;
    private Long contactInterestLuId;
    private Long contactSourceLuId;
    
    /**
     * @return Returns the contactInterestLuId.
     */
    public Long getContactInterestLuId() {
        return contactInterestLuId;
    }
    /**
     * @param contactInterestLuId The contactInterestLuId to set.
     */
    public void setContactInterestLuId(Long contactInterestLuId) {
        this.contactInterestLuId = contactInterestLuId;
    }
    /**
     * @return Returns the contactSourceLuId.
     */
    public Long getContactSourceLuId() {
        return contactSourceLuId;
    }
    /**
     * @param contactSourceLuId The contactSourceLuId to set.
     */
    public void setContactSourceLuId(Long contactSourceLuId) {
        this.contactSourceLuId = contactSourceLuId;
    }
    /**
     * @return Returns the nameFirstLike.
     */
    public String getNameFirstLike() {
        return nameFirstLike;
    }
    /**
     * @param nameFirstLike The nameFirstLike to set.
     */
    public void setNameFirstLike(String nameFirstLike) {
        this.nameFirstLike = nameFirstLike;
    }
    /**
     * @return Returns the nameLastLike.
     */
    public String getNameLastLike() {
        return nameLastLike;
    }
    /**
     * @param nameLastLike The nameLastLike to set.
     */
    public void setNameLastLike(String nameLastLike) {
        this.nameLastLike = nameLastLike;
    }
    public Integer getMaxResults() {
        return maxResults;
    }
    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }
    /**
     * @return Returns the organizationNameLike.
     */
    public String getOrganizationNameLike() {
        return organizationNameLike;
    }
    /**
     * @param organizationNameLike The organizationNameLike to set.
     */
    public void setOrganizationNameLike(String organizationNameLike) {
        this.organizationNameLike = organizationNameLike;
    }
    /**
     * @return Returns the nameFormattedLike.
     */
    public String getNameFormattedLike() {
        return nameFormattedLike;
    }
    /**
     * @param nameFormattedLike The nameFormattedLike to set.
     */
    public void setNameFormattedLike(String nameFormattedLike) {
        this.nameFormattedLike = nameFormattedLike;
    }
    /**
     * @return Returns the organizationCityLike.
     */
    public String getOrganizationCityLike() {
        return organizationCityLike;
    }
    /**
     * @param organizationCityLike The organizationCityLike to set.
     */
    public void setOrganizationCityLike(String organizationCityLike) {
        this.organizationCityLike = organizationCityLike;
    }
    /**
     * @return Returns the createDateEnd.
     */
    public Date getCreateDateRangeStop() {
        return createDateRangeStop;
    }
    /**
     * @param createDateEnd The createDateEnd to set.
     */
    public void setCreateDateRangeStop(Date createDateEnd) {
        this.createDateRangeStop = createDateEnd;
    }
    /**
     * @return Returns the createDateStart.
     */
    public Date getCreateDateRangeStart() {
        return createDateRangeStart;
    }
    /**
     * @param createDateStart The createDateStart to set.
     */
    public void setCreateDateRangeStart(Date createDateStart) {
        this.createDateRangeStart = createDateStart;
    }
    /**
     * @return Returns the modifyDateRangeEnd.
     */
    public Date getModifyDateRangeStop() {
        return modifyDateRangeStop;
    }
    /**
     * @param modifyDateRangeEnd The modifyDateRangeEnd to set.
     */
    public void setModifyDateRangeStop(Date modifyDateRangeEnd) {
        this.modifyDateRangeStop = modifyDateRangeEnd;
    }
    /**
     * @return Returns the modifyDateRangeStart.
     */
    public Date getModifyDateRangeStart() {
        return modifyDateRangeStart;
    }
    /**
     * @param modifyDateRangeStart The modifyDateRangeStart to set.
     */
    public void setModifyDateRangeStart(Date modifyDateRangeStart) {
        this.modifyDateRangeStart = modifyDateRangeStart;
    }
    /**
     * @return Returns the contactClassLuId.
     */
    public Long getContactClassLuId() {
        return contactClassLuId;
    }
    /**
     * @param contactClassLuId The contactClassLuId to set.
     */
    public void setContactClassLuId(Long contactClassLuId) {
        this.contactClassLuId = contactClassLuId;
    }
    
}
