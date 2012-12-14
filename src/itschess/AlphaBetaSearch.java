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
        double a = -10000000; double b = 10000000;
        
        double v = maxValue(a, b);
        System.out.println(AlphaBetaSearch.bestMoves[0]);
        depth = 0;
        return AlphaBetaSearch.bestMoves[0];
    }
    
    public static double maxValue(double a, double b)
    {//a = alpha, b = beta
        double tempA = -10000000;//holds max value
        double hold = -9999999;//holds the current max
        while(!board.done)
        {
            if(depth != 3)//Begining by testing only to depth 3
            {
                board.move(depth);
 //               System.out.println(board);
                depth++;
                hold = minValue(a, b);
            }
            else
            {
                hold = Evaluation.eval(board);
                depth--;
                //board.undo(depth);
                return hold;
            }
            
            //System.out.println(board.chessMoves[depth]);
            //System.out.println(board);
//            if(hold == 10000000) //branch is done
//            {
//                if(depth == 0)
//                    System.out.println();
//                depth--;
//                return tempA;
//            }
            if(/*hold != 10000000 && */hold > tempA)
            {//If this move is the best found so far
                if(depth == 0)
                    ;//System.out.println();
                if(hold != 10000000)
                {
                    bestMoves[depth] = board.chessMoves[depth];
                    tempA = hold;
                }
            }
            if(tempA >= b)      
            {//If this branch is bad skip it
                if(/*a != -10000000 && */depth != 0)
                {
                    board.undo(depth);
                    depth--;
                    return tempA;
                }
            }
            if(tempA > a)
                a = tempA;
            
            //if(depth != 2)
                board.undo(depth);
            //System.out.println(board);
            
            if(board.done)
                ;//System.out.println();//Never gets hit dont know why
        }
        //board.chessMoves[depth] = null;
        depth--;
        if(board.depth != 2)
            board.done = false;
        return tempA;
    }
    
    public static double minValue(double a, double b)
    {//a = alpha, b = beta
        double tempB = 10000000;//initial min value
        double hold = 9999999;//holds the current min
        while(!board.done)
        {
            if(depth != 3)//Begining by testing only to depth 3
            {
                board.move(depth);
  //              System.out.println(board);
                depth++;
                hold = maxValue(a, b);
            }
            else
            {	
                hold = Evaluation.eval(board);
                depth--;
                //board.undo(depth);
                return hold;
            }
            
//            System.out.println(board.chessMoves[depth]);
//            System.out.println(board);
//            if(hold == -10000000) //branch is done
//            {
//                depth--;
//                return tempB;
//            }
            
            if(hold < tempB)
            {//If this move is the best found so far
                bestMoves[depth] = board.chessMoves[depth];
                tempB = hold;
            }
            if(tempB <= a)      
            {//If this branch is bad skip it
                board.undo(depth);
                depth--;
                if(hold == -10000000)
                    System.out.println();
                return tempB;
            }
            if(tempB < b)
                b = tempB;
            
            //if(depth != 2)
                board.undo(depth);
//            System.out.println(board);
     //       if(board.done)
            //    System.out.println();//Never gets hit dont know why
        }
        
        depth--;
        //board.undo(depth);? no?
        if(hold == 10000000)
            System.out.println();
        if(board.depth != 2)
        {
            board.done = false;
        }
        //else
          //  board.undo(depth);
        return tempB;
    }
    
    

}
