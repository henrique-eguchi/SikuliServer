/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sikuliapi.rest;

import org.json.JSONObject;

/**
 *
 * @author Alan
 */
public class JFind {
    
    public static final String field_jPattern = "jPattern";
    public static final String field_highlight = "highlight";
    
    public JPattern jPattern;
    public boolean highlight;
    
    public JFind(){
        
    }
    
    public static JFind getJFind(String json){
        JSONObject jO = new JSONObject(json);
        JFind jFind = new JFind();
        jFind.jPattern = JPattern.getJPattern(jO.getJSONObject(field_jPattern).toString());
        jFind.highlight = jO.getBoolean(field_highlight);
        return jFind;
    }
}
