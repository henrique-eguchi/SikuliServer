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
import org.sikuliapi.rest.JExists;
import org.sikuliapi.rest.JResult;

import org.sikuli.script.*;

/**
 *
 * @author Alan
 */
public class exists extends baseservlet{
    
    public static final String ServletPath = "/exists";
    
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        JResult jResult = new JResult();
        JExists jExists = new JExists();
        log.WriteLine("Request Recieved!");
        try{
            jExists = JExists.getJExists(getJsonString(request));
            log.WriteLine("Waiting for Pattern to disappear...");
            jExists.patternExists = getScreen().exists(jExists.jPattern.toPattern(), jExists.timeout) != null;
            if(jExists.patternExists == true){
                log.WriteLine("Pattern Exists!");
                jResult.message = "Pattern Exists";
            }else{
                log.WriteLine("Pattern does not Exist!");
                jResult.message = "Pattern does not Exist";
            }
            jResult.setResult(ActionResult.PASS);
            jExists.jResult = jResult;
        }catch(Exception e){
            e.printStackTrace();
            log.WriteLine("Exists Failed!");
            jResult.throwableInfo(e);
            jExists.jResult = jResult;
        }catch(LinkageError e){
            e.printStackTrace();
            log.WriteLine("Exists Failed!");
            jResult.throwableInfo(e);
            jExists.jResult = jResult;
        }finally{
            setResponsePOST(response, jExists.toJson());
        }
    }
    
    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        response.getWriter().append(String.format("Welcome to the Sikuli API!"));
    }
}
