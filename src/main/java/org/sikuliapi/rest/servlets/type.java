/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sikuliapi.rest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sikuli.script.*;
import org.sikuliapi.rest.ActionResult;
import org.sikuliapi.rest.JPattern;
import org.sikuliapi.rest.JResult;
import org.sikuliapi.rest.JType;

/**
 *
 * @author Alan
 */
public class type extends baseservlet{
    public static final String ServletPath = "/type";
    
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        JResult jResult = new JResult();
        log.WriteLine("Request Recieved!");
        try{
            JType jType = JType.getJType(getJsonString(request));
            log.WriteLine("Typing in Pattern...");
            if(getKeyModifier(jType.jKeyModifier) != 0){
                log.WriteLine("Using KeyModifier: "+jType.jKeyModifier.toString());
                getScreen().type(jType.jPattern.toPattern(), jType.text, getKeyModifier(jType.jKeyModifier));
            }else{
                getScreen().type(jType.jPattern.toPattern(), jType.text);
            }
            log.WriteLine("Pattern Typed in!");
            jResult.message = "Pattern Typed in";
            jResult.setResult(ActionResult.PASS);
        }catch(Exception e){
            e.printStackTrace();
            log.WriteLine("Type Failed!");
            jResult.throwableInfo(e);
        }catch(LinkageError e){
            e.printStackTrace();
            log.WriteLine("Type Failed!");
            jResult.throwableInfo(e);
        }finally{
            setResponsePOST(response, jResult.toJson());
        }
    }
    
    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        response.getWriter().append(String.format("Welcome to the Sikuli API!"));
    }
}
