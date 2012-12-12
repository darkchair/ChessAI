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
    static Board board = new Board();
    public static String[] bestMoves = new String[5];
     
    public static String alphaBetaSearch()
    {
        int[] a = new int[1]; a[0] = -10000000; int[] b = new int[1]; b[0] = 10000000;
        
        int v = maxValue(a, b);
        System.out.println(AlphaBetaSearch.bestMoves[0]);
        depth = 0;
        return AlphaBetaSearch.bestMoves[0];
    }
    
    public static int maxValue(int[] a, int[] b)
    {//a = alpha, b = beta
        int tempA = -10000000;//holds max value
        int hold = -9999999;//holds the current max
        while(!board.done)
        {
            if(depth != 2)//Begining by testing only to depth 4
            {
                board.move(depth);
 //               System.out.println(board);
                depth++;
                hold = minValue(a, b);
            }
            else
            {
                hold = (int) Evaluation.eval(board);
                depth--;
                //board.undo(depth);
                return hold;
            }
            if(hold == 10000000) //branch is done
            {
                depth--;
                return tempA;
            }
            
            if(/*hold != 10000000 && */hold > tempA)
            {//If this move is the best found so far
                bestMoves[depth] = board.chessMoves[depth];
                tempA = hold;
            }
            if(tempA > a[0])
                a[0]= tempA;
            if(/*hold != 10000000 && */tempA >= b[0])      
            {//If this branch is bad skip it
                //board.undo(depth); //dont know if this should be removed
                //depth--;
                //return tempA;
                
                continue;
            }
            
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
    
    public static int minValue(int[] a, int[] b)
    {//a = alpha, b = beta
        int tempB = 10000000;//initial min value
        int hold = 9999999;//holds the current min
        while(!board.done)
        {
            if(depth != 2)//Begining by testing only to depth 4
            {
                board.move(depth);
  //              System.out.println(board);
                depth++;
                hold = maxValue(a, b);
            }
            else
            {	
                hold = (int) Evaluation.eval(board);
                depth--;
                //board.undo(depth);
                return hold;
            }
            
            if(hold == -10000000) //branch is done
            {
                depth--;
                return tempB;
            }
            
            if(/*hold != -10000000 && */hold < tempB)
            {//If this move is the best found so far
                bestMoves[depth] = board.chessMoves[depth];
                tempB = hold;
            }
            if(tempB < b[0])
                b[0] = tempB;
            if(/*hold != -10000000 && */tempB <= a[0])      
            {//If this branch is bad skip it
                //board.undo(depth); //dont know if this should be removed
                depth--;
                return tempB;
            }
            
            
            board.undo(depth);
//            System.out.println(board);
     //       if(board.done)
            //    System.out.println();//Never gets hit dont know why
        }
        
        depth--;
        //board.undo(depth);? no?
        board.done = false;
        return tempB;
    }
    
    

}
