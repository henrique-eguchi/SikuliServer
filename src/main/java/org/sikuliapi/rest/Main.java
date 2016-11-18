/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.sikuliapi.rest;

import java.util.Scanner;
import org.apache.catalina.LifecycleException;

import org.sikuliapi.rest.jetty.JettyEmbeddedRunner;

/**
 * @author Henrique Akiyoshi Eguchi
 */
public class Main {
    public static void main(String[] args) throws LifecycleException{
        System.out.println("### STARTING SIKULI EMBEDDED WEB CONTAINER ###");
        System.out.println("### Starting Jetty... ###");

        switch (args.length)
        {
            case 0:
                new JettyEmbeddedRunner().startServer();
                break;
            case 1:
                new JettyEmbeddedRunner(Integer.parseInt(args[0])).startServer();
                break;
            case 2:
                new JettyEmbeddedRunner(args[0], Integer.parseInt(args[1])).startServer();
                break;
            default:
                new JettyEmbeddedRunner().startServer();
                break;
        }
        
        
    }
}
