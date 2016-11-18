/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sikuliapi.rest.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;
import org.sikuliapi.rest.JKeyModifier;
import org.sikuliapi.rest.Log;

/**
 *
 * @author Alan
 */
public class baseservlet extends HttpServlet{
    
    public Log log;
    public static Screen scrn;
    
    public baseservlet(){
        log = new Log();
    }
    
    public Screen getScreen(){
        if(scrn == null){
            scrn = new Screen();
            log.WriteLine("Instantiated new Screen object...");
        }
        return scrn;
    }
    
    /*
        Parse the POST of the HttpServletRequest and return the Json object as a string.
    */
    public String getJsonString(HttpServletRequest request)throws IOException{
        String jsonObject = null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        try{
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null)
                jb.append(line);
        }catch(Exception e){
            e.printStackTrace();
        }
        jsonObject = jb.toString();
        log.WriteLine("Parsed request POST: "+ jsonObject);
        return jsonObject;
    }
    
    /*
        Set the POST of the HttpServletResponse to a json object -- usually a JResult
    */
    public HttpServletResponse setResponsePOST(HttpServletResponse response, String jsonObject){
        try{
            log.WriteLine("Setting response POST...");
            log.WriteLine("POST Content: "+jsonObject);
            response.setContentType("application/json");
            PrintWriter respPOST = response.getWriter();
            respPOST.print(jsonObject);
            log.WriteLine("Setting response POST was successful!");
        }catch(Exception e){
            e.printStackTrace();
            log.WriteLine("Setting response POST failed.");
        }
        return response;
    }
    
    public int getKeyModifier(JKeyModifier jKeyModifier){
        if(jKeyModifier.equals(JKeyModifier.ALT)){
            return KeyModifier.ALT;
        }else if(jKeyModifier.equals(JKeyModifier.ALTGR)){
            return KeyModifier.ALTGR;
        }else if(jKeyModifier.equals(JKeyModifier.CMD)){
            return KeyModifier.CMD;
        }else if(jKeyModifier.equals(JKeyModifier.CTRL)){
            return KeyModifier.CTRL;
        }else if(jKeyModifier.equals(JKeyModifier.META)){
            return KeyModifier.META;
        }else if(jKeyModifier.equals(JKeyModifier.SHIFT)){
            return KeyModifier.SHIFT;
        }else if(jKeyModifier.equals(JKeyModifier.WIN)){
            return KeyModifier.WIN;
        }else{
            return 0;
        }
    }
}
