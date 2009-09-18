/*
 * Created on Mar 30, 2005 by Reid
 */
package com.fivesticks.util.images.legacy;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Reid
 */
public class ImageScalerJpegAwtImpl implements ImageScaler {

    Log log = LogFactory.getLog(ImageScalerJpegAwtImpl.class);

    private static String format = "jpg";
    
    public String scale(String originalFile, int newMax, String newFileName) throws ScalePictureException {

        BufferedImage original = getBufferedImage(originalFile);

        float scale = getScale(original, newMax);

        File res = null;
        try {
            res = writeScale(original, newFileName, scale, format);
        } catch (RuntimeException e) {
            throw new ScalePictureException(e);
        }

        original.flush();

        return res.getName();
    }

    /**
     * @param original
     * @param thumbMax
     * @return
     */
    private float getScale(BufferedImage original, int max) {

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

    private File writeScale(BufferedImage original, String outputFilename,
            float scale, String format) {

        BufferedImage bimage2 = new BufferedImage(
                (int) (original.getWidth() * scale), (int) (original
                        .getHeight() * scale), BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = (Graphics2D) bimage2.getGraphics();

        g2d.scale(scale, scale);

        g2d.drawImage(original, 0, 0, new JFrame());

        File out = new File(outputFilename);

        if (out.exists())
            out.delete();

        try {
            ImageIO.write(bimage2, format, new File(outputFilename));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return out;
    }

    private BufferedImage getBufferedImage(String filename) {
        if (filename == null || filename.length() == 0) {
            throw new RuntimeException("No filename!");
        }
        File src = new File(filename);
        if (!src.exists()) {
            throw new RuntimeException("File don't exist!");
        }

        BufferedImage ret = null;
        try {
            ret = ImageIO.read(src);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return ret;
    }

}