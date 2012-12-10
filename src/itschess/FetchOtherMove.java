
package itschess;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
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
        String head = "http://www.bencarle.com/chess/poll/101/2/1a77594c/";
        String head2 = "http://www.bencarle.com/chess/poll/101/1/32c68cae/";
    	
        String serverText = "";
        int moveIndex;
        
        BufferedReader res = null;
        //System.out.println();
        try{
            URL url = new URL(head);
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            res = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        }
        catch (IOException e){
            System.out.println();
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
            //moveRead = true;
            ItsChess.otherMove = serverText.substring(serverText.lastIndexOf(":")+3, serverText.lastIndexOf("}")-1);
            
        }
        
    }
    
}
