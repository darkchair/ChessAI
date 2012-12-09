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
    public static String[] bestMoves = new String[5];
     
    public static String alphaBetaSearch()
    {
        Byte a = -100; Byte b = 100;//should these be ints or Integers?
        board = new Board();
        int v = maxValue(a, b);
        //v shouldnt be 100
        System.out.println(board);
        return AlphaBetaSearch.bestMoves[0];
    }
    
    public static byte maxValue(Byte a, Byte b)
    {//a = alpha, b = beta
        byte tempA = -100;//holds max value
        byte hold = -99;//holds the current max
        while(!board.done)
        {
            if(depth != 1)//Begining by testing only to depth 4
            {
                board.move(depth);
 //               System.out.println(board);
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
                depth--;
                return tempA;
            }
            if(tempA > a)
                a = tempA;
            
            board.undo(depth);
//            System.out.println(board);
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
            if(depth != 1)//Begining by testing only to depth 4
            {
                board.move(depth);
  //              System.out.println(board);
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
            {//If this branch is bad skip it
                //board.undo(depth); //dont know if this should be removed
                depth--;
                return tempB;
            }
            if(tempB < b)
                b = tempB;
            
            
            board.undo(depth);
//            System.out.println(board);
            if(board.done)
                System.out.println();//Never gets hit dont know why
        }
        
        depth--;
        //board.undo(depth);? no?
        board.done = false;
        return tempB;
    }
    
    

}
