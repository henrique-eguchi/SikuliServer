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
public class JClick {
    
    public static final String field_jPattern = "jPattern";
    public static final String field_jKeyModifier = "jKeyModifier";
    
    public JPattern jPattern;
    public JKeyModifier jKeyModifier;
    
    public JClick(){
        
    }
    
    public static JClick getJClick(String json){
        JSONObject jO = new JSONObject(json);
        JClick jClick = new JClick();
        jClick.jPattern = JPattern.getJPattern(jO.getJSONObject(field_jPattern).toString());
        jClick.jKeyModifier = JKeyModifier.valueOf(jO.getString(field_jKeyModifier));
        return jClick;
    }
}
