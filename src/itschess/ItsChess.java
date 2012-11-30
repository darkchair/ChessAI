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
    
    public class Board
    {
        public byte[][] theBoard;
        
        public void move()
        {
            
        }
    }
    
    public void alphaBetaSearch(ItsChess.Board board)
    {
        Integer a = new Integer(-50000); Integer b = new Integer(50000);
        int v = maxValue(board, a, b);
        
    }
    
    public byte maxValue(ItsChess.Board board, Integer a, Integer b)
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
    
    public byte minValue(ItsChess.Board board, Integer a, Integer b)
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
    
    public byte utility(ItsChess.Board board)
    {
        //return 200(k) + 9(q) + 5(r) + 3(b+n) + (p) - 0.5(d+s+i) + 0.1(m);
        return 0;
    }
    
    public byte[][] possibleMovesKnight(ItsChess.Board board, byte x, byte y)
    {//returns a byte array of the possible moves a knight can make
        byte[][] retval = new byte[8][2];
        
        if(board.theBoard[(x-1)][(y+2)] == 0)
        {
            retval[0][0] = (byte)(x-1);
            retval[0][1] = (byte)(y+2);
        }
        if(board.theBoard[(x+1)][(y+2)] == 0)
        {
            retval[1][0] = (byte)(x+1);
            retval[1][1] = (byte)(y+2);
        }
        if(board.theBoard[(x+2)][(y+1)] == 0)
        {
            retval[2][0] = (byte)(x+2);
            retval[2][1] = (byte)(y+1);
        }
        if(board.theBoard[(x+2)][(y-1)] == 0)
        {
            retval[3][0] = (byte)(x+2);
            retval[3][1] = (byte)(y-1);
        }
        if(board.theBoard[(x+1)][(y-2)] == 0)
        {
            retval[4][0] = (byte)(x+1);
            retval[4][1] = (byte)(y-2);
        }
        if(board.theBoard[(x-1)][(y-2)] == 0)
        {
            retval[5][0] = (byte)(x-1);
            retval[5][1] = (byte)(y-2);
        }
        if(board.theBoard[(x-2)][(y-1)] == 0)
        {
            retval[6][0] = (byte)(x-2);
            retval[6][1] = (byte)(y-1);
        }
        if(board.theBoard[(x-2)][(y+1)] == 0)
        {
            retval[7][0] = (byte)(x-2);
            retval[7][1] = (byte)(y+1);
        }
        
        return retval;
    }
        
    public byte[][] possibleMovesRook(ItsChess.Board board, byte x, byte y)
    {//returns a byte array of the possible moves a rook can make
        byte[][] retval = new byte[14][2];
        byte sizecount = 0;
        
        for(byte i=x, m=y; i<8; i++)
        {
            if(board.theBoard[(i)][(m)] == 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 7)
                {
                    retval[sizecount][0] = (byte)(i+1);
                    retval[sizecount][1] = (byte)(m);
                    sizecount++;
                }
                break;
            }
        }
        for(byte i=x, m=y; i>0; i--)
        {
            if(board.theBoard[(i)][(m)] == 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 1)
                {
                    retval[sizecount][0] = (byte)(i-1);
                    retval[sizecount][1] = (byte)(m);
                    sizecount++;
                }
                break;
            }
        }
        for(byte i=x, m=y; m<8; m++)
        {
            if(board.theBoard[(i)][(m)] == 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(m != 7)
                {
                    retval[sizecount][0] = (byte)(i);
                    retval[sizecount][1] = (byte)(m+1);
                    sizecount++;
                }
                break;
            }
        }
        for(byte i=x, m=y; m>0; m--)
        {
            if(board.theBoard[(i)][(m)] == 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(m != 1)
                {
                    retval[sizecount][0] = (byte)(i);
                    retval[sizecount][1] = (byte)(m-1);
                    sizecount++;
                }
                break;
            }
        }
        
        return retval;
    }
    
    public byte[][] possibleMovesBishop(ItsChess.Board board, byte x, byte y)
    {//returns a byte array of the possible moves a bishop can make
        byte[][] retval = new byte[13][2];
        byte sizecount = 0;
        
        for(byte i=x, m=y; i<8 && m<8; i++,m++)
        {
            if(board.theBoard[(i)][(m)] == 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 7 && m != 7)
                {
                    retval[sizecount][0] = (byte)(i+1);
                    retval[sizecount][1] = (byte)(m+1);
                    sizecount++;
                }
                break;
            }
        }
        for(byte i=x, m=y; i>0 && m<8; i--, m++)
        {
            if(board.theBoard[(i)][(m)] == 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 1 && m != 7)
                {
                    retval[sizecount][0] = (byte)(i-1);
                    retval[sizecount][1] = (byte)(m+1);
                    sizecount++;
                }
                break;
            }
        }
        for(byte i=x, m=y; i<8 && m>0; i++, m--)
        {
            if(board.theBoard[(i)][(m)] == 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 7 && m != 1)
                {
                    retval[sizecount][0] = (byte)(i+1);
                    retval[sizecount][1] = (byte)(m-1);
                    sizecount++;
                }
                break;
            }
        }
        for(byte i=x, m=y; i>0 && m>0; i--, m--)
        {
            if(board.theBoard[(i)][(m)] == 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 1 && m != 1)
                {
                    retval[sizecount][0] = (byte)(i-1);
                    retval[sizecount][1] = (byte)(m-1);
                    sizecount++;
                }
                break;
            }
        }
        
        return retval;
        
        /*
        
        byte tx = x; byte ty = y;
        
        byte i=0;
        for(; i<7; i++)
        {
            tx--; ty--;
            if(tx < 1 || ty < 1)
                break;
        }
        
        tx = x; ty = y;
        
        byte j=0;
        for(; j<7; j++)
        {
            tx++; ty++;
            if(tx > 8 || ty > 8)
                break;
        }
        
        tx = x; ty = y;
        
        byte o=0;
        for(; o<7; o++)
        {
            tx--; ty++;
            if(tx < 1 || ty > 8)
                break;
        }
        
        tx = x; ty = y;
        
        byte p=0;
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
            if(board.theBoard[(tx)][(ty)] == 0)
            {
                retval[k][0] = (byte)(tx);
                retval[k][1] = (byte)(ty);
            }
        }
        
        tx = x; ty = y;
        for(int k=0; k<j; k++)
        {
            tx++; ty++;
            if(board.theBoard[(tx)][(ty)] == 0)
            {
                retval[k+i][0] = (byte)(tx);
                retval[k+i][1] = (byte)(ty);
            }
        }
        
        tx = x; ty = y;
        for(int k=0; k<o; k++)
        {
            tx--; ty++;
            if(board.theBoard[(tx)][(ty)] == 0)
            {
                retval[k+i+j][0] = (byte)(tx);
                retval[k+i+j][1] = (byte)(ty);
            }
        }
        
        tx = x; ty = y;
        for(int k=0; k<p; k++)
        {
            tx++; ty--;
            if(board.theBoard[(tx)][(ty)] == 0)
            {
                retval[k+i+j+o][0] = (byte)(tx);
                retval[k+i+j+o][1] = (byte)(ty);
            }
        }
        
        return retval; */
    }
    
    
    //eval function chess //google search
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
