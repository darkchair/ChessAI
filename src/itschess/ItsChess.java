/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itschess;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Timer;

/**
 *
 * @author Ian
 */
public class ItsChess 
{
    static InputStreamReader reader = new InputStreamReader (System.in);
    // Wrap the reader with a buffered reader.
    static BufferedReader buf_in = new BufferedReader (reader);
    
    static String otherMove = "";//where the timer puts its results
	/**
	 * @param args
	 */
    public static int turns = 0;
    public static Timer timer = new Timer();
        
    public static void main(String[] args) {
        
//        AlphaBetaSearch.alphaBetaSearch();
//        for(int i = 0; i < 4; i ++)
//            System.out.println(AlphaBetaSearch.bestMoves[i]);
        try {
            gameLoop();
        }
        catch (IOException e) {
            System.out.println();
        }
        
    }
    
    public static void gameLoop() throws MalformedURLException, IOException 
    {
        String move = "";
        
        timer.scheduleAtFixedRate(new FetchOtherMove(), new Date(), 5000);
       // AlphaBetaSearch.board.colorWhite = true;
        
        while(!AlphaBetaSearch.board.gameOver)
        {
            if(otherMove.compareTo("") != 0)//If they moved
            {
                //Move their piece
                AlphaBetaSearch.board.makeOtherPlayerMove(otherMove);
                
                //Search for our move
                move = AlphaBetaSearch.alphaBetaSearch();
                
                //Send our move
                URL url = new URL("http://www.bencarle.com/chess/move/101/2/1a77594c/" + move + "/");
                URLConnection connection = url.openConnection();
                InputStream in = connection.getInputStream();
                BufferedReader res = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                StringBuffer sBuffer = new StringBuffer();
                String inputLine;
                while ((inputLine = res.readLine()) != null)
                        sBuffer.append(inputLine);
                res.close();
                
                //Make our move
                AlphaBetaSearch.board.makeOurMove(move);

                otherMove = "";
                //ItsChess.timer.scheduleAtFixedRate(new FetchOtherMove(), 5000, 5000);
            }
        }
    }
}
