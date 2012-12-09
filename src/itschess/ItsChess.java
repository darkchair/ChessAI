/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itschess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        
        AlphaBetaSearch.alphaBetaSearch();
        for(int i = 0; i < 4; i ++)
            System.out.println(AlphaBetaSearch.bestMoves[i]);
        
    }
    
    public static void gameLoop()
    {
        String move = "";
        String otherMove = "";
        AlphaBetaSearch.board.colorWhite = true;
        while(!AlphaBetaSearch.board.gameOver)
        {
            if(AlphaBetaSearch.board.colorWhite == true)
            {
                if(turns > 5)
                    move = AlphaBetaSearch.alphaBetaSearch();

                else
                {
                    AlphaBetaSearch.board.fetchMove();
                }
                String str = "q";
                try {
                    // Read a whole line a time. Check the string for
                    // the "quit" input to jump from the loop.

                    str = buf_in.readLine ();

                }
                catch  (IOException e) {
                    System.out.println ("IO exception = " + e );
                }

                AlphaBetaSearch.board.makeOtherPlayerMove(str);
                System.out.println(AlphaBetaSearch.board.toString());
            }
            else
            {

            }
            turns ++;
        }
    }
}
