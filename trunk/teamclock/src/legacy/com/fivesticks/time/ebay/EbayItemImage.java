/*
 * Created on Mar 16, 2005 by REID
 */
package com.fivesticks.time.ebay;

import java.io.Serializable;

import com.fivesticks.time.common.AbstractTimeObject;
import com.fivesticks.util.SequenceAware;

/**
 * @author REID
 */
public class EbayItemImage  extends AbstractTimeObject implements  Serializable, SequenceAware {


    private Long ebayId;
    private Integer sequence;
    private String imageSmall;
    private String imageFull;
    private String caption;
    
    public Long getEbayId() {
        return ebayId;
    }
    public void setEbayId(Long ebayId) {
        this.ebayId = ebayId;
    }
//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
    public String getImageFull() {
        return imageFull;
    }
    public void setImageFull(String imageFull) {
        this.imageFull = imageFull;
    }
    public String getImageSmall() {
        return imageSmall;
    }
    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }
//    public String getOwnerKey() {
//        return ownerKey;
//    }
//    public void setOwnerKey(String ownerKey) {
//        this.ownerKey = ownerKey;
//    }
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    /**
     * @return Returns the caption.
     */
    public String getCaption() {
        return caption;
    }
    /**
     * @param caption The caption to set.
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }
}
