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
        Timer timer = new Timer();
        timer.schedule(new FetchOtherMove(), new Date(), 6000*5);
       // AlphaBetaSearch.board.colorWhite = true;
        
        while(!AlphaBetaSearch.board.gameOver)
        {
            if(otherMove.compareTo("") != 0)//If they moved
            {
                //Move their piece
                AlphaBetaSearch.board.makeOtherPlayerMove(otherMove);
                
                //Search for our move
                if(turns > 2)
                    move = AlphaBetaSearch.alphaBetaSearch();
                else
                    AlphaBetaSearch.board.fetchMove();
                
                //Send our move
                URL url = new URL("http://www.bencarle.com/chess/move/85/2/1a77594c/Pe7e5/");
                URLConnection connection1 = url.openConnection();
                connection1.setDoOutput(true);
                OutputStream tempOut = connection1.getOutputStream();
                OutputStreamWriter out1 = new OutputStreamWriter(tempOut, "UTF-8");

                out1.close();
                tempOut.close();
                otherMove = "";
            }
            
//            if(AlphaBetaSearch.board.colorWhite == true)
//            {
//                if(turns > 2)
//                    move = AlphaBetaSearch.alphaBetaSearch();
//
//                else
//                {
//                    AlphaBetaSearch.board.fetchMove();
//                }
//                String str = "q";
//                try {
//                    // Read a whole line a time. Check the string for
//                    // the "quit" input to jump from the loop.
//
//                    str = buf_in.readLine ();
//
//                }
//                catch  (IOException e) {
//                    System.out.println ("IO exception = " + e );
//                }
//
//                AlphaBetaSearch.board.makeOtherPlayerMove(str);
//                System.out.println(AlphaBetaSearch.board.toString());
//            }
//            else
//            {
//            	otherMove = fetchOtherMove();
//            }
//            turns ++;
        }
    }
}
