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
    static byte[][] theBoard;
    static byte[][] possibleMoves;
    
    public class Board
    {
        public void move()
        {
            
        }
    }
    
    public void alphaBetaSearch(ItsChess.Board board)
    {
        Integer a = new Integer(-50000); Integer b = new Integer(50000);
        int v = maxValue(board, a, b);
        
    }
    
    public int maxValue(ItsChess.Board board, Integer a, Integer b)
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
                a = v;
        }
        return v;
    }
    
    public int minValue(ItsChess.Board board, Integer a, Integer b)
    {
        byte v = 100;//initial min value
        int mv = 0;//holds the current min
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
                b = v;
        }
        return v;
    }
    
    public byte[][] possibleMovesKnight(byte x, byte y)
    {//returns a byte array of the possible moves a knight can make
        byte[][] retval = new byte[8][2];
        
        if(theBoard[(x-1)][(y+2)] == 0)
        {
            retval[0][0] = (byte)(x-1);
            retval[0][1] = (byte)(y+2);
        }
        if(theBoard[(x+1)][(y+2)] == 0)
        {
            retval[1][0] = (byte)(x+1);
            retval[1][1] = (byte)(y+2);
        }
        if(theBoard[(x+2)][(y+1)] == 0)
        {
            retval[2][0] = (byte)(x+2);
            retval[2][1] = (byte)(y+1);
        }
        if(theBoard[(x+2)][(y-1)] == 0)
        {
            retval[3][0] = (byte)(x+2);
            retval[3][1] = (byte)(y-1);
        }
        if(theBoard[(x+1)][(y-2)] == 0)
        {
            retval[4][0] = (byte)(x+1);
            retval[4][1] = (byte)(y-2);
        }
        if(theBoard[(x-1)][(y-2)] == 0)
        {
            retval[5][0] = (byte)(x-1);
            retval[5][1] = (byte)(y-2);
        }
        if(theBoard[(x-2)][(y-1)] == 0)
        {
            retval[6][0] = (byte)(x-2);
            retval[6][1] = (byte)(y-1);
        }
        if(theBoard[(x-2)][(y+1)] == 0)
        {
            retval[7][0] = (byte)(x-2);
            retval[7][1] = (byte)(y+1);
        }
        
        return retval;
    }
        
    public byte[][] possibleMovesRook(byte x, byte y)
    {//returns a byte array of the possible moves a rook can make
        byte[][] retval = new byte[14][2];
        
        for(int i=0, m=y; i<7; i++, m++)
        {
            if(m==7)
                m = 0;
            if(theBoard[(x)][(m)] == 0)
            {
                retval[i][0] = (byte)(x);
                retval[i][1] = (byte)(m);
            }
        }
        for(int i=0, m=x; i<7; i++, m++)
        {
            if(m==7)
                m = 0;
            if(theBoard[(m)][(y)] == 0)
            {
                retval[i][0] = (byte)(m);
                retval[i][1] = (byte)(y);
            }
        }
        
        return retval;
    }
    
    public byte[][] possibleMovesBishop(byte x, byte y)
    {//returns a byte array of the possible moves a bishop can make
        byte tx = x; byte ty = y;
        
        int i=0;
        for(; i<7; i++)
        {
            tx--; ty--;
            if(tx < 1 || ty < 1)
                break;
        }
        
        tx = x; ty = y;
        
        int j=0;
        for(; j<7; j++)
        {
            tx++; ty++;
            if(tx > 8 || ty > 8)
                break;
        }
        
        tx = x; ty = y;
        
        int o=0;
        for(; o<7; o++)
        {
            tx--; ty++;
            if(tx < 1 || ty > 8)
                break;
        }
        
        tx = x; ty = y;
        
        int p=0;
        for(; p<7; p++)
        {
            tx++; ty--;
            if(tx > 8 || ty < 1)
                break;
        }
        
        //------------------------------
        
        byte[][] retval = new byte[i+j+o+p][2];
        
        tx = x; ty = y;
        for(int k=0; k<i; k++)
        {
            tx--; ty--;
            if(theBoard[(tx)][(ty)] == 0)
            {
                retval[k][0] = (byte)(tx);
                retval[k][1] = (byte)(ty);
            }
        }
        
        tx = x; ty = y;
        for(int k=0; k<j; k++)
        {
            tx++; ty++;
            if(theBoard[(tx)][(ty)] == 0)
            {
                retval[k+i][0] = (byte)(tx);
                retval[k+i][1] = (byte)(ty);
            }
        }
        
        tx = x; ty = y;
        for(int k=0; k<o; k++)
        {
            tx--; ty++;
            if(theBoard[(tx)][(ty)] == 0)
            {
                retval[k+i+j][0] = (byte)(tx);
                retval[k+i+j][1] = (byte)(ty);
            }
        }
        
        tx = x; ty = y;
        for(int k=0; k<p; k++)
        {
            tx++; ty--;
            if(theBoard[(tx)][(ty)] == 0)
            {
                retval[k+i+j+o][0] = (byte)(tx);
                retval[k+i+j+o][1] = (byte)(ty);
            }
        }
        
        return retval;
    }
    
    
    //eval function chess //google search
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
