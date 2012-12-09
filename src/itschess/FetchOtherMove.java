
package itschess;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ian
 */
public class FetchOtherMove extends TimerTask{
    
    public void run()
    {
        String gameID = "85";
        String head = "http://www.bencarle.com/chess/poll/89/2/1a77594c/";
        String head2 = "http://www.bencarle.com/chess/poll/89/1/32c68cae/";
        String test;
        boolean moveRead = false;
        String otherMove = "";
    	
        String serverText = "";
        int moveIndex;
        
        BufferedReader res = null;
        try{
            URL url = new URL(head2);
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            res = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        }
        catch (IOException e){
            
        }
        StringBuffer sBuffer = new StringBuffer();
        String inputLine;
        try {
            while ((inputLine = res.readLine()) != null)
                sBuffer.append(inputLine);
        } catch (IOException ex) {
            Logger.getLogger(FetchOtherMove.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            res.close();
        } catch (IOException ex) {
            Logger.getLogger(FetchOtherMove.class.getName()).log(Level.SEVERE, null, ex);
        }
        serverText = sBuffer.toString();
        moveIndex = serverText.indexOf(":") + 1;
        String testfas = serverText.substring(moveIndex,sBuffer.indexOf(",")).trim();
        if(testfas.equals("true"))
        {
            moveRead = true;
            otherMove = serverText.substring(serverText.lastIndexOf(":")+3, serverText.lastIndexOf("}")-1);
            
//            
//            
//            URL url = null;
//            try {
//                url = new URL("http://www.bencarle.com/chess/move/85/2/1a77594c/" + otherMove + "/");
//            } catch (MalformedURLException ex) {
//                Logger.getLogger(FetchOtherMove.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            URLConnection connection1 = null;
//            try {
//                connection1 = url.openConnection();
//            } catch (IOException ex) {
//                Logger.getLogger(FetchOtherMove.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            connection1.setDoOutput(true);
//            OutputStream tempOut = null;
//            try {
//                tempOut = connection1.getOutputStream();
//            } catch (IOException ex) {
//                Logger.getLogger(FetchOtherMove.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            OutputStreamWriter out1 = null;
//            try {
//                out1 = new OutputStreamWriter(tempOut, "UTF-8");
//            } catch (UnsupportedEncodingException ex) {
//                Logger.getLogger(FetchOtherMove.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                out1.close();
//            } catch (IOException ex) {
//                Logger.getLogger(FetchOtherMove.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            try {
//                tempOut.close();
//            } catch (IOException ex) {
//                Logger.getLogger(FetchOtherMove.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
    }
    
}
