/*
 * Created on Apr 1, 2005 by REID
 */
package com.fivesticks.util.images.legacy;

/**
 * @author REID
 */
public interface ImageScaler {
    
    public abstract String scale(String originalFile, int newMax,
            String newFileName) throws ScalePictureException;
}