/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itschess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Ian
 */
public class ItsChess 
{
    static InputStreamReader reader = new InputStreamReader (System.in);
    // Wrap the reader with a buffered reader.
    static BufferedReader buf_in = new BufferedReader (reader);
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
        String otherMove = "";
        AlphaBetaSearch.board.colorWhite = true;
        String gameID = "84";
        String head = "http://bencarle.com/chess/poll/" + gameID + "/1/32c68cae";
        String head2 = "http://bencarle.com/chess/poll/" + gameID + "/2/1a77594c";
        String test;
        
        URL url = new URL(head);
        URLConnection connection = url.openConnection();
        connection.setDoInput(true); connection.setDoOutput(true);
        
        url = new URL(head);
        URLConnection connection2 = url.openConnection();
        connection2.setDoInput(true); connection2.setDoOutput(true);
        
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        buf_in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        OutputStreamWriter out2 = new OutputStreamWriter(connection.getOutputStream());
        BufferedReader buf_in2 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        while((test = buf_in.readLine()) != null)
        {
            System.out.println(test);
        }
        
//        while(!AlphaBetaSearch.board.gameOver)
//        {
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
//
//            }
//            turns ++;
//        }
    }
}
