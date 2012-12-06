/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itschess;

/**
 *
 * @author Ian
 */
public class AlphaBetaSearch {

    public static byte depth = 0;
    static byte[][] possibleMoves;
    static Board board;
    public static String[] bestMoves = new String[4];
     
    public static void alphaBetaSearch()
    {
        Byte a = -100; Byte b = 100;//should these be ints or Integers?
        board = new Board();
        int v = maxValue(a, b);
        
    }
    
    public static byte maxValue(Byte a, Byte b)
    {//a = alpha, b = beta
        byte v = -100;//initial max value
        byte mv = -99;//holds the current max
        while(!board.done)
        {
            if(depth != 3)//Begining by testing only to depth 2
            {
                board.move(depth);
                depth++;
                mv = minValue(a, b);
            }
            else
            {
            	depth--;
                mv = (byte) board.eval();
                return mv;
            }
            
            if(mv > v)
            {//If this move is the best found so far
                bestMoves[depth] = board.chessMoves[depth];
                v = mv;
            }
            if(v >= b)      
            {//If this branch is bad skip it
                //board.undo(depth);
                return v;
            }
            if(v > a)//not sure
                a = v;
            
            board.undo(depth);
            //depth--;
        }
        //board.chessMoves[depth] = null;
        depth--;
        board.done = false;
        return v;
    }
    
    public static byte minValue(Byte a, Byte b)
    {//a = alpha, b = beta
        byte v = 100;//initial min value
        byte mv = 99;//holds the current min
        while(!board.done)
        {
            if(depth != 3)//Begining by testing only to depth 2
            {
                board.move(depth);
                depth++;
                mv = maxValue(a, b);
            }
            else
            {	
                depth--;
                mv = (byte)board.eval();
                return mv;
            }
            
            if(mv < v)
            {//If this move is the best found so far
                bestMoves[depth] = board.chessMoves[depth];
                v = mv;
            }
            if(v <= a)      
            {//If this brach is bad skip it
                //board.undo(depth);
                depth--;
                return v;
            }
            if(v < b)//not sure
                b = v;
            
            
            board.undo(depth);
            //depth--;
        }
        
        depth--;
        //board.undo(depth);? no?
        board.done = false;
        return v;
    }
    
    

}
