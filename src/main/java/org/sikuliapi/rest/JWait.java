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
public class JWait {
    
    public static final String field_jPattern = "jPattern";
    public static final String field_timeout = "timeout";
    
    public JPattern jPattern;
    public double timeout;
    
    public JWait(){
        
    }
    /*
        Parse the specified JSON string into a JWait object
    */
    public static JWait getJWait(String json){
        JSONObject jO = new JSONObject(json);
        JWait jWait = new JWait();
        jWait.jPattern = JPattern.getJPattern(jO.getJSONObject(field_jPattern).toString());
        jWait.timeout = jO.getInt(field_timeout);
        return jWait;
    }
}
