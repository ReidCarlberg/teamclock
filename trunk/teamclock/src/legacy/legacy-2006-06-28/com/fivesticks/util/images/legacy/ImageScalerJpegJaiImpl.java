/*
 * Created on Apr 1, 2005 by REID
 */
package com.fivesticks.util.images.legacy;

import java.io.File;

import javax.media.jai.Interpolation;
import javax.media.jai.JAI;
import javax.media.jai.ParameterBlockJAI;
import javax.media.jai.PlanarImage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author REID
 */
public class ImageScalerJpegJaiImpl implements ImageScaler {

    Log log = LogFactory.getLog(ImageScalerJpegJaiImpl.class);

    public String scale(String originalFile, int newMax, String newFileName) throws ScalePictureException {

        File file = new File(originalFile);
        
        PlanarImage original = loadImage(originalFile);

        float scale = getScale(original, newMax);

        PlanarImage scaledImage = scale(original, scale);

        String ret = newFileName;
        
        saveImage(scaledImage, ret);
        
        return ret;
    }

    /**
     * @param original
     * @param thumbMax
     * @return
     */
    private float getScale(PlanarImage original, int max) {

        float ret = 1.0f;

        int x = original.getWidth();
        int y = original.getHeight();

        if (x > y ) {
            ret = (float) max / (float) x;
        } else  {
            ret = (float) max / (float) y;
        }

        if (ret > 1.0f) {
            ret = 1.0f;
        }
        
        return ret;
    }

    private PlanarImage scale(PlanarImage target, float amount) {
        ParameterBlockJAI pb = new ParameterBlockJAI("scale");
        pb.addSource(target);
        pb.setParameter("xScale", amount); //x Scale Factor
        pb.setParameter("yScale", amount); //y Scale Factor
        pb.setParameter("xTrans", 0.0F);      //x Translate amount
        pb.setParameter("yTrans", 0.0F);      //y Translate amount
        pb.setParameter("interpolation", Interpolation.getInstance(Interpolation.INTERP_BILINEAR));
        PlanarImage ret = JAI.create("scale", pb, null);
        return ret;
    }
    
    private PlanarImage loadImage(String filepath) {
        
        PlanarImage ret = (PlanarImage)JAI.create("fileload", filepath);
        
        return ret;
    }
    
    

    /**
     *Saves the image to the given path and filename using the given codec
     *
     *@param filepath the path and filename to save the image to.
     *@param type The JAI-defined codec type to save as.
     */
    public void saveImage(PlanarImage target, String filepath) {
        //Saves the image to the given path and filename
        JAI.create("filestore", target, filepath, "JPEG", null);
    }
    

    
}
