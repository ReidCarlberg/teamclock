/*
 * Created on Aug 11, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

/**
 * @author Reid
 */
public class SystemOwnerCriteriaParameters extends SystemOwner {

    private Integer maxResults;
    
    private boolean sortedByName;

    private boolean sortedByKey;
    
    private String keyLike;

    public SystemOwnerCriteriaParameters() {
        super();
        this.setName(null);
        this.setAddress1(null);
        this.setAddress2(null);
        this.setCity(null);
        this.setState(null);
        this.setPostalCode(null);
        this.setContactEmail(null);
        this.setContactName(null);
    }
    /**
     * @return Returns the sortedByKey.
     */
    public boolean isSortedByKey() {
        return sortedByKey;
    }

    /**
     * @param sortedByKey
     *            The sortedByKey to set.
     */
    public void setSortedByKey(boolean sortedByKey) {
        this.sortedByKey = sortedByKey;
    }

    /**
     * @return Returns the sortedByName.
     */
    public boolean isSortedByContactName() {
        return sortedByName;
    }

    /**
     * @param sortedByName
     *            The sortedByName to set.
     */
    public void setSortedByName(boolean sortedByName) {
        this.sortedByName = sortedByName;
    }

    /**
     * @return Returns the maxResults.
     */
    public Integer getMaxResults() {
        return maxResults;
    }

    /**
     * @param maxResults The maxResults to set.
     */
    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }
    /**
     * @return Returns the keyLike.
     */
    public String getKeyLike() {
        return keyLike;
    }
    /**
     * @param keyLike The keyLike to set.
     */
    public void setKeyLike(String keyLike) {
        this.keyLike = keyLike;
    }
    /**
     * @return Returns the sortedByName.
     */
    public boolean isSortedByName() {
        return sortedByName;
    }
}