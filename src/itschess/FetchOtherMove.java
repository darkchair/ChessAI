
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
    
    public void run(){
        AlphaBetaSearch.board.colorWhite = false;
        String gameID = "85";
        String head = "http://www.bencarle.com/chess/poll/120/2/1a77594c/";
        String head2 = "http://www.bencarle.com/chess/poll/154/2/1a77594c/";
        String test;
        boolean moveRead = false;
        String otherMove = "";

        String serverText = "";
        int moveIndex;
        try {
            serverText = getServerText(head2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        moveIndex = serverText.indexOf(":") + 1;
        String testfas = serverText.substring(moveIndex,serverText.indexOf(",")).trim();
        if(testfas.equals("true"))
        {
                moveRead = true;
                String move;
                otherMove = serverText.substring(serverText.lastIndexOf(":")+3, serverText.lastIndexOf("}")-1);
                if(!otherMove.equals(""))
                    AlphaBetaSearch.board.makeOtherPlayerMove(otherMove);

                move = AlphaBetaSearch.alphaBetaSearch();
                System.out.println(move);
                //	if(Chess.turns == 4)
                //		move = AlphaBetaSearch.board.castle();
            // else
            //  {
                // move = AlphaBetaSearch.board.fetchMove();

                    //AlphaBetaSearch.board.makeOurMove(move);
        //    }
                try {
                    sendMove(move);
                    AlphaBetaSearch.board.makeOurMove(move);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        else
        {
            System.out.println("waiting");
        }
            //ItsChess.timer.schedule(new FetchOtherMove(), 5 * 1000);
        ItsChess.turns++;
    }

    public void sendMove(String move) throws IOException {
        URL url = new URL("http://www.bencarle.com/chess/move/154/2/1a77594c/" + move + "/");
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuffer sBuffer = new StringBuffer();
        String inputLine;
        while ((inputLine = res.readLine()) != null)
                sBuffer.append(inputLine);
        res.close();
        }

        public String getServerText(String head) throws IOException{
        URL url = new URL(head);
        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuffer sBuffer = new StringBuffer();
        String inputLine;
        while ((inputLine = res.readLine()) != null)
                sBuffer.append(inputLine);
        res.close();
        String serverText1 = sBuffer.toString();
        return serverText1;
    }
    
}
