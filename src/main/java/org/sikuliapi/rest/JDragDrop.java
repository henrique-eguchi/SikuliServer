/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sikuliapi.rest;

import org.json.JSONObject;
import org.sikuliapi.rest.JPattern;

/**
 *
 * @author Alan
 */
public class JDragDrop {
    
    public static final String field_jClickPattern = "jClickPattern";
    public static final String field_jKeyModifier = "jKeyModifier";
    public static final String field_jDropPattern = "jDropPattern";
    
    public JPattern jClickPattern;
    public JPattern jDropPattern;
    public JKeyModifier jKeyModifier;
    
    public JDragDrop(){
        
    }
    
    public static JDragDrop getJDragDrop(String json){
        JSONObject jO = new JSONObject(json);
        JDragDrop jDragDrop = new JDragDrop();
        jDragDrop.jClickPattern = JPattern.getJPattern(jO.getJSONObject(field_jClickPattern).toString());
        jDragDrop.jDropPattern = JPattern.getJPattern(jO.getJSONObject(field_jDropPattern).toString());
        jDragDrop.jKeyModifier = JKeyModifier.valueOf(jO.getString(field_jKeyModifier));
        return jDragDrop;
    }
}
