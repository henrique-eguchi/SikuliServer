/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sikuliapi.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.json.JSONObject;
import org.sikuli.script.Pattern;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Alan
 */
public class JPattern {
    public static final String field_imageIn64 = "imageIn64";
    public static final String field_offset_x = "offset_x";
    public static final String field_offset_y = "offset_y";
    public static final String field_similar = "similar";
    
    public String imageIn64;
    public int offset_x;
    public int offset_y;
    public float similar;
    
    public JPattern(){
        
    }
    /*
        Parse the passed string into a JPattern object
    */
    public static JPattern getJPattern(String json){
        JSONObject jO = new JSONObject(json);
        JPattern jP = new JPattern();
        jP.imageIn64 = jO.getString(field_imageIn64);
        jP.offset_x = jO.getInt(field_offset_x);
        jP.offset_y = jO.getInt(field_offset_y);
        jP.similar = (float) jO.getDouble(field_similar);
        return jP;
    }
    /*
        Parse the JPattern object into a JSON string
    */
    public String toJson(){
        JSONObject jO = new JSONObject();
        jO.put(field_imageIn64, imageIn64);
        jO.put(field_offset_x, offset_x);
        jO.put(field_offset_y, offset_y);
        jO.put(field_similar, similar);
        return jO.toString();
    }
    /*
        Create a Sikuli Pattern object from this JPattern
    */
    public Pattern toPattern() throws IOException{
        
        
        // create a buffered image
        BufferedImage image = null;
        byte[] imageByte;

        BASE64Decoder decoder = new BASE64Decoder();
        imageByte = decoder.decodeBuffer(imageIn64);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
        image = ImageIO.read(bis);
        
        bis.close();

        // write the image to a file
//        File outputfile = new File("image.png");
//        ImageIO.write(image, "png", outputfile);
        
        
        
        //Create a Pattern from a buffered image
        Pattern pattern = new Pattern(image);
        pattern.targetOffset(offset_x, offset_y);
        pattern.similar(similar);
        return pattern;
    }
}
