/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anritsu.bugzillaXmlRpc.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 *
 * @author RO100051
 */
public class BugzillaXmlRpcClient {
    public final String BUGZILLA_XMLRPC = "http://172.28.35.31/bugzilla/xmlrpc.cgi";
    public static void main(String [] args){
        System.out.println(new BugzillaXmlRpcClient().login("ro100051","asdfgh.,123"));
    }
    
    public XmlRpcClient getXmlRpcClient(String url){
        XmlRpcClient xmlRpcClient = new XmlRpcClient();
        try {       
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(url));
            xmlRpcClient.setConfig(config);
        } catch (MalformedURLException ex) {
            Logger.getLogger(BugzillaXmlRpcClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xmlRpcClient;
    }
    
    public int login(String user, String pass){
        HashMap result = new HashMap();
        try {
            XmlRpcClient xmlRpcClient = getXmlRpcClient(BUGZILLA_XMLRPC);
            ArrayList<Object> params = new ArrayList<>();
            HashMap<String,Object> executionData = new HashMap<String,Object>();
            executionData.put("login", user);
            executionData.put("password", pass);
            executionData.put("remember",true);
            params.add(executionData);
            result = (HashMap) xmlRpcClient.execute("User.login", params);
        } catch (XmlRpcException ex) {
            Logger.getLogger(BugzillaXmlRpcClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.parseInt(result.get("id").toString());
    }
    
}
