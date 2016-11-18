/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sikuliapi.rest;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.json.JSONObject;

/**
 *
 * @author Alan
 */
public class JResult {
    public static final String field_message = "message";
    public static final String field_stacktrace = "stacktrace";
    public static final String field_result = "result";
    
    public String message;
    public String stacktrace;
    public String result;
    
    public JResult() {
        
    }
    /*
    Convert the result string above into an ActionResult enum object for easy conparisons
    */
    public ActionResult toActionResult(){
        if(result.equals(ActionResult.FAIL.toString())){
            return ActionResult.FAIL;
        }else if(result.equals(ActionResult.PASS.toString())){
            return ActionResult.PASS;
        }else{
            return ActionResult.UNKNOWN;
        }
    }
    
    public void setResult(ActionResult rslt){
        result = rslt.toString();
    }
    
    public void setStackTrace(Throwable thrownError){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        thrownError.printStackTrace(pw);
        stacktrace = sw.toString();
    }
    
    public String toJson(){
        JSONObject jO = new JSONObject();
        jO.put(field_message, message);
        jO.put(field_stacktrace, stacktrace);
        jO.put(field_result, result);
        return jO.toString();
    }
    
    public void throwableInfo(Throwable thrownError){
        message = thrownError.getMessage();
        setResult(ActionResult.FAIL);
        setStackTrace(thrownError);
    }
}
