/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sikuliapi.rest;

import org.json.JSONObject;
import static org.sikuliapi.rest.JClick.field_jKeyModifier;
import static org.sikuliapi.rest.JClick.field_jPattern;

/**
 *
 * @author Alan
 */
public class JType {
    
    public static final String field_jPattern = "jPattern";
    public static final String field_jKeyModifier = "jKeyModifier";
    public static final String field_text = "text";
    
    public JPattern jPattern;
    public JKeyModifier jKeyModifier;
    public String text;
    
    public JType(){
        
    }
    
    public static JType getJType(String json){
        JSONObject jO = new JSONObject(json);
        JType jType = new JType();
        jType.jPattern = JPattern.getJPattern(jO.getJSONObject(field_jPattern).toString());
        jType.jKeyModifier = JKeyModifier.valueOf(jO.getString(field_jKeyModifier));
        jType.text = jO.getString(field_text);
        return jType;
    }
}
