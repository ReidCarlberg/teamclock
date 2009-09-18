/*
 * Created on Sep 15, 2004 by Reid
 */
package com.fivesticks.time.systemowner;

import com.fivesticks.time.common.IdReadable;

/**
 * @author Reid
 */
public interface SystemOwnerKeyAware extends IdReadable {

    public void setOwnerKey(String ownerKey);

    public String getOwnerKey();

}