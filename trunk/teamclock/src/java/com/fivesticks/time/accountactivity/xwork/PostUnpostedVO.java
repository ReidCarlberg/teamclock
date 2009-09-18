/*
 * Created on May 17, 2006
 *
 */
package com.fivesticks.time.accountactivity.xwork;

public class PostUnpostedVO {

    private Long id;
    private Double chargeableElapsed;
    private boolean skip;
    /**
     * @return Returns the elapsedChargeable.
     */
    public Double getChargeableElapsed() {
        return chargeableElapsed;
    }
    /**
     * @param elapsedChargeable The elapsedChargeable to set.
     */
    public void setChargeableElapsed(Double elapsedChargeable) {
        this.chargeableElapsed = elapsedChargeable;
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
     * @return Returns the skip.
     */
    public boolean isSkip() {
        return skip;
    }
    /**
     * @param skip The skip to set.
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    
}
