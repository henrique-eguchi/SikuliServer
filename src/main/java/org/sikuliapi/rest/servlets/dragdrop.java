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
import org.sikuliapi.rest.JDragDrop;
import org.sikuliapi.rest.JPattern;
import org.sikuliapi.rest.JResult;

/**
 *
 * @author Alan
 */
public class dragdrop extends baseservlet{
    
    public static final String ServletPath = "/dragdrop";
    
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException{
        JResult jResult = new JResult();
        log.WriteLine("Request Recieved!");
        try{
            JDragDrop jDragDrop = JDragDrop.getJDragDrop(getJsonString(request));
            log.WriteLine("Drag-Dropping Patterns...");
            if(getKeyModifier(jDragDrop.jKeyModifier) != 0){
                log.WriteLine("Not Using KeyModifier: "+jDragDrop.jKeyModifier.toString() + "... Java API of Sikuli does not support a KeyModifier for DragDrop");
                getScreen().dragDrop(jDragDrop.jClickPattern.toPattern(), jDragDrop.jDropPattern.toPattern());
            }else{
                getScreen().dragDrop(jDragDrop.jClickPattern.toPattern(), jDragDrop.jDropPattern.toPattern());
            }
            log.WriteLine("Patterns Drag-Dropped!");
            jResult.message = "Patterns Drag-Dropped";
            jResult.setResult(ActionResult.PASS);
        }catch(Exception e){
            e.printStackTrace();
            log.WriteLine("Drag-Dropping Failed!");
            jResult.throwableInfo(e);
        }catch(LinkageError e){
            e.printStackTrace();
            log.WriteLine("Drag-Dropping Failed!");
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
