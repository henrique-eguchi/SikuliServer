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
import org.sikuliapi.rest.JClick;
import org.sikuliapi.rest.JPattern;
import org.sikuliapi.rest.JResult;

/**
 *
 * @author Alan
 */
public class doubleclick extends baseservlet{
    
    public static final String ServletPath = "/doubleclick";
    
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        JResult jResult = new JResult();
        log.WriteLine("Request Recieved!");
        try{
            JClick jClick = JClick.getJClick(getJsonString(request));
            log.WriteLine("DoubleClicking Pattern...");
            if(getKeyModifier(jClick.jKeyModifier) != 0){
                log.WriteLine("Using KeyModifier: "+jClick.jKeyModifier.toString());
                getScreen().doubleClick(jClick.jPattern.toPattern(), getKeyModifier(jClick.jKeyModifier));
            }else{
                getScreen().doubleClick(jClick.jPattern.toPattern());
            }
            log.WriteLine("Pattern DoubleClicked!");
            jResult.message = "Pattern DoubleClicked";
            jResult.setResult(ActionResult.PASS);
        }catch(Exception e){
            e.printStackTrace();
            log.WriteLine("DoubleClicked Failed!");
            jResult.throwableInfo(e);
        }catch(LinkageError e){
            e.printStackTrace();
            log.WriteLine("DoubleClicked Failed!");
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
