/*
 * Created on May 6, 2003
 *
 */
package com.fivesticks.time.common.util;

import java.io.Serializable;

/**
 * @author Reid S Carlberg
 * @version 1.0.0
 */
public class GeographicVO implements Serializable {
	private final String shortName;
	private final String longName;
	
	public GeographicVO(final String shortName, final String longName) {
		this.shortName = shortName;
		this.longName = longName; 
	}
	
	
	/**
	 * @return
	 */
	public String getLongName() {
		return longName;
	}

	/**
	 * @return
	 */
	public String getShortName() {
		return shortName;
	}

}
