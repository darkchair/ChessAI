/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itschess;

/**
 *
 * @author Ian
 */
public class ItsChess {

    /**
     * @param args the command line arguments
     */
    static byte depth = 1;
    static byte[][] possibleMoves;
    
    public void alphaBetaSearch(Board board)
    {
        Integer a = new Integer(-50000); Integer b = new Integer(50000);
        int v = maxValue(board, a, b);
        
    }
    
    public byte maxValue(Board board, Integer a, Integer b)
    {
        byte v = -100;//initial max value
        byte mv = -99;//holds the current max
        for(int i=0; i<possibleMoves.length; i++)
        {
            if(depth != 3)
            {//Begining by testing only to depth 3
                board.move();
                mv = minValue(board, a, b);
            }
            else
                mv = utility(board);
            if(mv > v)
                v = mv;
            if(v > b)
                return v;
            if(v > a)
                a = new Integer(v);
        }
        return v;
    }
    
    public byte minValue(Board board, Integer a, Integer b)
    {
        byte v = 100;//initial min value
        byte mv = 99;//holds the current min
        for(int i=0; i<possibleMoves.length; i++)
        {
            if(depth != 3)
            {//Begining by testing only to depth 3
                board.move();
                mv = maxValue(board, a, b);
            }
            else
                mv = utility(board);
            if(mv < v)
                v = mv;
            if(v < a)
                return v;
            if(v < b)
                b = new Integer(v);
        }
        return v;
    }
    
    public byte utility(Board board)
    {
        //return 200(k) + 9(q) + 5(r) + 3(b+n) + (p) - 0.5(d+s+i) + 0.1(m);
        return 0;
    }
    
    //eval function chess //google search
    
    
    public static void main(String[] args) {
        
        Board chessboard = new Board();
        chessboard.analysis();
        System.out.println(chessboard.toString());
        
    }
}
