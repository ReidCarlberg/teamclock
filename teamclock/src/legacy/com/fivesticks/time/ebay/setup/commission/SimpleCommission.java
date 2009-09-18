/*
 * Created on Jun 14, 2005
 *
 */
package com.fivesticks.time.ebay.setup.commission;

import java.io.Serializable;

import com.fivesticks.time.systemowner.SystemOwnerKeyAware;

/**
 * @author Reid
 *
 */
public class SimpleCommission implements SystemOwnerKeyAware, Serializable {

    private Long id;
    private String ownerKey;
    private String name;
    
    private double minimum;    
    private double oneMin;
    private double oneMax;
    private double oneRate;
    private double twoMin;
    private double twoMax;
    private double twoRate;
    private double threeMin;
    private double threeMax;
    private double threeRate;
    private double fourMin;
    private double fourMax;
    private double fourRate;
    private double fiveMin;
    private double fiveMax;
    private double fiveRate;
    private double sixMin;
    private double sixMax;
    private double sixRate;
    
    /**
     * @return Returns the fiveMax.
     */
    public double getFiveMax() {
        return fiveMax;
    }
    /**
     * @param fiveMax The fiveMax to set.
     */
    public void setFiveMax(double fiveMax) {
        this.fiveMax = fiveMax;
    }
    /**
     * @return Returns the fiveMin.
     */
    public double getFiveMin() {
        return fiveMin;
    }
    /**
     * @param fiveMin The fiveMin to set.
     */
    public void setFiveMin(double fiveMin) {
        this.fiveMin = fiveMin;
    }
    /**
     * @return Returns the fiveRate.
     */
    public double getFiveRate() {
        return fiveRate;
    }
    /**
     * @param fiveRate The fiveRate to set.
     */
    public void setFiveRate(double fiveRate) {
        this.fiveRate = fiveRate;
    }
    /**
     * @return Returns the fourMax.
     */
    public double getFourMax() {
        return fourMax;
    }
    /**
     * @param fourMax The fourMax to set.
     */
    public void setFourMax(double fourMax) {
        this.fourMax = fourMax;
    }
    /**
     * @return Returns the fourMin.
     */
    public double getFourMin() {
        return fourMin;
    }
    /**
     * @param fourMin The fourMin to set.
     */
    public void setFourMin(double fourMin) {
        this.fourMin = fourMin;
    }
    /**
     * @return Returns the fourRate.
     */
    public double getFourRate() {
        return fourRate;
    }
    /**
     * @param fourRate The fourRate to set.
     */
    public void setFourRate(double fourRate) {
        this.fourRate = fourRate;
    }
    /**
     * @return Returns the minimum.
     */
    public double getMinimum() {
        return minimum;
    }
    /**
     * @param minimum The minimum to set.
     */
    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }
    /**
     * @return Returns the oneMax.
     */
    public double getOneMax() {
        return oneMax;
    }
    /**
     * @param oneMax The oneMax to set.
     */
    public void setOneMax(double oneMax) {
        this.oneMax = oneMax;
    }
    /**
     * @return Returns the oneMin.
     */
    public double getOneMin() {
        return oneMin;
    }
    /**
     * @param oneMin The oneMin to set.
     */
    public void setOneMin(double oneMin) {
        this.oneMin = oneMin;
    }
    /**
     * @return Returns the oneRate.
     */
    public double getOneRate() {
        return oneRate;
    }
    /**
     * @param oneRate The oneRate to set.
     */
    public void setOneRate(double oneRate) {
        this.oneRate = oneRate;
    }
    /**
     * @return Returns the sixMax.
     */
    public double getSixMax() {
        return sixMax;
    }
    /**
     * @param sixMax The sixMax to set.
     */
    public void setSixMax(double sixMax) {
        this.sixMax = sixMax;
    }
    /**
     * @return Returns the sixMin.
     */
    public double getSixMin() {
        return sixMin;
    }
    /**
     * @param sixMin The sixMin to set.
     */
    public void setSixMin(double sixMin) {
        this.sixMin = sixMin;
    }
    /**
     * @return Returns the sixRate.
     */
    public double getSixRate() {
        return sixRate;
    }
    /**
     * @param sixRate The sixRate to set.
     */
    public void setSixRate(double sixRate) {
        this.sixRate = sixRate;
    }
    /**
     * @return Returns the threeMax.
     */
    public double getThreeMax() {
        return threeMax;
    }
    /**
     * @param threeMax The threeMax to set.
     */
    public void setThreeMax(double threeMax) {
        this.threeMax = threeMax;
    }
    /**
     * @return Returns the threeMin.
     */
    public double getThreeMin() {
        return threeMin;
    }
    /**
     * @param threeMin The threeMin to set.
     */
    public void setThreeMin(double threeMin) {
        this.threeMin = threeMin;
    }
    /**
     * @return Returns the threeRate.
     */
    public double getThreeRate() {
        return threeRate;
    }
    /**
     * @param threeRate The threeRate to set.
     */
    public void setThreeRate(double threeRate) {
        this.threeRate = threeRate;
    }
    /**
     * @return Returns the twoMax.
     */
    public double getTwoMax() {
        return twoMax;
    }
    /**
     * @param twoMax The twoMax to set.
     */
    public void setTwoMax(double twoMax) {
        this.twoMax = twoMax;
    }
    /**
     * @return Returns the twoMin.
     */
    public double getTwoMin() {
        return twoMin;
    }
    /**
     * @param twoMin The twoMin to set.
     */
    public void setTwoMin(double twoMin) {
        this.twoMin = twoMin;
    }
    /**
     * @return Returns the twoRate.
     */
    public double getTwoRate() {
        return twoRate;
    }
    /**
     * @param twoRate The twoRate to set.
     */
    public void setTwoRate(double twoRate) {
        this.twoRate = twoRate;
    }


    
    /**
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @return Returns the ownerKey.
     */
    public String getOwnerKey() {
        return ownerKey;
    }
    /**
     * @param ownerKey The ownerKey to set.
     */
    public void setOwnerKey(String ownerKey) {
        this.ownerKey = ownerKey;
    }
    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
