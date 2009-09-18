/*
 * Created on Apr 1, 2005 by REID
 */
package com.fivesticks.util.images.legacy;

/**
 * @author REID
 */
public class ImageScalerFactory {

    public static final ImageScalerFactory factory = new ImageScalerFactory();

    public ImageScaler getJpegJai() {
        return new ImageScalerJpegJaiImpl();
    }
}
