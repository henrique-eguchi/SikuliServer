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

import org.sikuliapi.rest.ActionResult;
import org.sikuliapi.rest.JPattern;
import org.sikuliapi.rest.JWaitVanish;
import org.sikuliapi.rest.JResult;

import org.sikuli.script.*;

/**
 *
 * @author Alan
 */
public class waitvanish extends baseservlet{
    
    public static final String ServletPath = "/waitvanish";
    
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        JResult jResult = new JResult();
        JWaitVanish jWaitVanish = new JWaitVanish();
        log.WriteLine("Request Recieved!");
        try{
            jWaitVanish = JWaitVanish.getJWaitVanish(getJsonString(request));
            log.WriteLine("Waiting for Pattern to disappear...");
            jWaitVanish.patternDisappeared = getScreen().waitVanish(jWaitVanish.jPattern.toPattern(), jWaitVanish.timeout);
            if(jWaitVanish.patternDisappeared == true){
                log.WriteLine("Pattern Disappeared!");
                jResult.message = "Pattern Disappeared";
            }else{
                log.WriteLine("Pattern Not Disappeared!");
                jResult.message = "Pattern Not Disappeared";
            }
            jResult.setResult(ActionResult.PASS);
            jWaitVanish.jResult = jResult;
        }catch(Exception e){
            e.printStackTrace();
            log.WriteLine("WaitVanish Failed!");
            jResult.throwableInfo(e);
            jWaitVanish.jResult = jResult;
        }catch(LinkageError e){
            e.printStackTrace();
            log.WriteLine("WaitVanish Failed!");
            jResult.throwableInfo(e);
            jWaitVanish.jResult = jResult;
        }finally{
            setResponsePOST(response, jWaitVanish.toJson());
        }
    }
    
    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        response.getWriter().append(String.format("Welcome to the Sikuli API!"));
    }
}
