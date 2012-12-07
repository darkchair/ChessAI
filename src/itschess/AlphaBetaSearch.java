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
        System.out.println();
    }
    
    public static byte maxValue(Byte a, Byte b)
    {//a = alpha, b = beta
        byte tempA = -100;//holds max value
        byte hold = -99;//holds the current max
        while(!board.done)
        {
            if(depth != 3)//Begining by testing only to depth 3
            {
                board.move(depth);
                depth++;
                hold = minValue(a, b);
            }
            else
            {
                hold = (byte) Evaluation.eval(board);
                depth--;
                //board.undo(depth);
                return hold;
            }
            
            if(hold > tempA)
            {//If this move is the best found so far
                bestMoves[depth] = board.chessMoves[depth];
                tempA = hold;
            }
            if(tempA >= b)      
            {//If this branch is bad skip it
                //board.undo(depth); //dont know if this should be removed
                if(depth == 0)
                    System.out.println();
                depth--;
                return tempA;
            }
            if(tempA > a)
                a = tempA;
            
            board.undo(depth);
            if(board.done)
                System.out.println();//Never gets hit dont know why
        }
        //board.chessMoves[depth] = null;
        depth--;
        board.done = false;
        if(depth == 0)
            System.out.println();
        return tempA;
    }
    
    public static byte minValue(Byte a, Byte b)
    {//a = alpha, b = beta
        byte tempB = 100;//initial min value
        byte hold = 99;//holds the current min
        while(!board.done)
        {
            if(depth != 3)//Begining by testing only to depth 3
            {
                board.move(depth);
                depth++;
                hold = maxValue(a, b);
            }
            else
            {	
                hold = (byte) Evaluation.eval(board);
                depth--;
                //board.undo(depth);
                return hold;
            }
            
            if(hold < tempB)
            {//If this move is the best found so far
                bestMoves[depth] = board.chessMoves[depth];
                tempB = hold;
            }
            if(tempB <= a)      
            {//If this brach is bad skip it
                //board.undo(depth); //dont know if this should be removed
                depth--;
                return tempB;
            }
            if(tempB < b)
                b = tempB;
            
            
            board.undo(depth);
            if(board.done)
                System.out.println();//Never gets hit dont know why
        }
        
        depth--;
        //board.undo(depth);? no?
        board.done = false;
        return tempB;
    }
    
    

}
