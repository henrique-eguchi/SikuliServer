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
import org.sikuliapi.rest.JFind;
import org.sikuliapi.rest.JResult;

import org.sikuli.script.*;

/**
 *
 * @author Alan
 */
public class find extends baseservlet{
    
    public static final String ServletPath = "/find";
    
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        JResult jResult = new JResult();
        log.WriteLine("Request Recieved!");
        try{
            JFind jFind = JFind.getJFind(getJsonString(request));
            log.WriteLine("Finding Pattern...");
            Match findMatch = getScreen().find(jFind.jPattern.toPattern());
            log.WriteLine("Pattern found!");
            if(jFind.highlight){
                log.WriteLine("Highlighting Pattern...");
                findMatch.highlight();
                log.WriteLine("Pattern highlighted!");
                Thread.sleep(1000);
                findMatch.highlight();
                log.WriteLine("Pattern highlight removed.");
            }
            jResult.message = "Pattern Found!";
            jResult.setResult(ActionResult.PASS);
        }catch(Exception e){
            e.printStackTrace();
            log.WriteLine("Find Failed!");
            jResult.throwableInfo(e);
        }catch(LinkageError e){
            e.printStackTrace();
            log.WriteLine("Find Failed!");
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
