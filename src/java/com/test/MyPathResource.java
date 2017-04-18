/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


/**
 * REST Web Service
 *
 * @author yiful
 */

@Path("/MyPath")
public class MyPathResource {
    private String httpUrl = "https://itunes.apple.com/search?term=";
    
    @GET
    @Produces("text/plain")
    public String getTextHello() {
        return "User name:"; 
   }
    
    @Path("/{username}")
    @GET
    @Produces("text/plain")
    public String getText(@PathParam("username") String username) {
        return "User name: "+username;
    }
    
    @Path("MyMethod")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String Query(@QueryParam("name")String name) throws IOException
    {
        String name2=name.replaceAll(" ","+");
        URL myUrl = null;
        try {
            myUrl = new URL("https://itunes.apple.com/search?term="+name2+"&limit=5");
        } catch (MalformedURLException ex) {
            Logger.getLogger(MyPathResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        URLConnection myUrlConnection = null;
        try {
            myUrlConnection = myUrl.openConnection();
        } catch (IOException ex) {
            Logger.getLogger(MyPathResource.class.getName()).log(Level.SEVERE, null, ex);
        }
			    
			    java.io.InputStream myInputStream = myUrlConnection.getInputStream();
			    
			    InputStreamReader myInputStreamReader = new InputStreamReader(myInputStream);
			    
			    BufferedReader in = new BufferedReader(myInputStreamReader);
			    
			    String line, str;
		        
		        str = "";
		        while ((line = in.readLine()) != null) 
		        {
		        	 str += line;
		        }    	
        return str; 
    }
}

