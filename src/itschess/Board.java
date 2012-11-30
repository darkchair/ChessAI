/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itschess;

/**
 *
 * @author Ian
 */
public class Board
{
    public byte board[][] = new byte [8][8];
    public byte attackboard[][] = new byte[8][8];

    public Board (){
        byte[] holder = {-3,-5,-4,-2,-1,-4,-5,-3};
        board[0] = holder; 
        for(int i = 0; i < 8; i ++)
        {
                board[1][i] = -6;

        }
        for(int i = 2; i < 6; i ++)
        {
                for(int j = 0; j < 8; j ++)
                {
                        board[i][j] = 0;
                }
        }
        for(int i = 0; i < 8; i ++)
        {
                board[6][i] = 6;

        }
        board[7][0] = 3;
        board[7][1] = 5;
        board[7][2] = 4;
        board[7][3] = 2;
        board[7][4] = 1;
        board[7][5] = 4;
        board[7][6] = 5;
        board[7][7] = 3;

        for(byte i = 0; i < 8; i ++)
        {
                for(byte j = 0; j <8; j++)
                {
                        attackboard[i][j] = 0;
                }
        }
    }

    public void analysis()
    {
        byte[][] moves;
        for(byte i = 0; i < 8; i ++)
        {
            for(byte j = 0; j <8; j++)
            {
                if(board[i][j] == (byte) -6)
                {
                    moves = possibleMovesPB(j,i);
                    for(byte k = 0; k < moves.length; k ++)
                    {
                        if(moves[k][0] != (byte)100)
                        {
                            //movePiece(i,j,moves[k][0], moves[k][1]);
                            attackboard[moves[k][0]][moves[k][1]] = (byte) (attackboard[moves[k][0]][moves[k][1]] + 1);
                        }
                    }
                }
                else if (board[i][j] == (byte) -5)
                {
                    moves = possibleMovesN(j,i);
                    for(byte k = 0; k < moves.length; k ++)
                    {
                        if(moves[k][0] != (byte)100)
                        {
                            //movePiece(i,j,moves[k][0], moves[k][1]);
                            attackboard[moves[k][0]][moves[k][1]] = (byte) (attackboard[moves[k][0]][moves[k][1]] + 1);
                        }
                    }
                }
                else if (board[i][j] == (byte) -4)
                {
                    moves = possibleMovesBishop(j,i);
                    for(byte k = 0; k < moves.length; k ++)
                    {
                        if(moves[k][0] != (byte)100)
                        {
                            //movePiece(i,j,moves[k][0], moves[k][1]);
                            attackboard[moves[k][0]][moves[k][1]] = (byte) (attackboard[moves[k][0]][moves[k][1]] + 1);
                        }
                    }
                }
            }
        }
    }

    public void movePiece(byte x, byte y, byte x1, byte y1)
    {
            board[x1][y1] = board[x][y];
    }

    public void move()
    {

    }

    public byte[][] possibleMovesPB(byte x, byte y)
    {
        byte[][] retVal = new byte[4][2];
        if(board[y+1][(byte) (x)] == 0)
        {
            retVal[0][0] = (byte) (y+1);
            retVal[0][1] = x;
        }
        else
        {
            retVal[0][0] = (byte) 100;
            retVal[0][1] = (byte) 100;
        }
        if(board[(byte) (y+2)][x] == 0)
        {
            retVal[1][0] = (byte) (y+2);
            retVal[1][1] = x;
        }
        else
        {
            retVal[0][0] = (byte) 100;
            retVal[0][1] = (byte) 100;
        }
        if(x+1 < 8 && board[(byte) (y+1)][(byte) (x+1)] == 0 )
        {
            retVal[2][0] = (byte) (y+1);
            retVal[2][1] = (byte) (x+1);
        }
        else
        {
            retVal[2][0] = (byte) 100;
            retVal[2][1] = (byte) 100;
        }
        if(x > 0 && board[(byte) (y+1)][(byte) (x-1)] == 0 )
        {
            retVal[3][0] = (byte) (y+1);
            retVal[3][1] = (byte) (x-1);
        }
        else
        {
            retVal[3][0] = (byte)100;
            retVal[3][1] = (byte)100;
        }
        return retVal;
    }

    public byte[][] possibleMovesP(byte x, byte y)
    {
        byte[][] retVal = new byte[4][2];
        if(board[x][(byte) (y+1)] == 0  && y<8)
        {
            retVal[0][0] = x;
            retVal[0][1] = (byte) (y+1);
        }
        else
        {
            retVal[0][0] = (byte) 100;
            retVal[0][1] = (byte) 100;
        }
        if(board[x][(byte) (y+2)] == 0)
        {
            retVal[1][0] = x;
            retVal[1][1] = (byte) (y+2);
        }
        else
        {
            retVal[0][0] = (byte) 100;
            retVal[0][1] = (byte) 100;
        }
        if(board[(byte) (x+1)][(byte) (y+1)] == 0)
        {
            retVal[2][0] = (byte) (x+1);
            retVal[2][1] = (byte) (y+1);
        }
        else
        {
            retVal[2][0] = (byte) 100;
            retVal[2][1] = (byte) 100;
        }
        if(board[(byte) (x-1)][(byte) (y+1)] == 0)
        {
            retVal[3][0] = (byte) (x-1);
            retVal[3][1] = (byte) (y+1);
        }
        else
        {
            retVal[3][0] = (byte)100;
            retVal[3][1] = (byte)100;
        }
        return retVal;
    }

    public byte[][] possibleMovesN(byte x, byte y){
        byte[][] retVal = new byte[8][2];
        if(x>0 && y<6 && board[(byte) x-1][(byte) y+2] == 0)
        {
            retVal[0][0] = (byte) (x-1);
            retVal[0][1] = (byte) (y+2);
        }
        else
        {
            retVal[0][0] = (byte) 100;
            retVal[0][1] = (byte) 100;
        }
        if(x<7 && y<6 && board[ (byte) x+1][(byte) y+2] == 0)
        {
            retVal[1][0] = (byte) (x+1);
            retVal[1][1] = (byte) (y+2);
        }
        else
        {
            retVal[1][0] = (byte) 100;
            retVal[1][1] = (byte) 100;
        }
        if(x<6 && y<7 &&board[(byte) (x+(byte)2)][(byte) (y+(byte)1)] == 0)
        {
            retVal[2][0] = (byte) (x+2);
            retVal[2][1] = (byte) (y+1);
        }
        else
        {
            retVal[2][0] = (byte) 100;
            retVal[2][1] = (byte) 100;
        }
        if(x>1 && y<7 && board[(byte) (x-2)][(byte) (y+1)] == 0)
        {
            retVal[3][0] = (byte) (x-(byte)2);
            retVal[3][1] = (byte) (y+(byte)1);
        }
        else
        {
            retVal[3][0] = (byte)100;
            retVal[3][1] = (byte)100;
        }
        if(x<6 && y>0 && board[(byte) (x+2)][(byte) (y-1)] == 0)
        {
            retVal[2][0] = (byte) (x+(byte)2);
            retVal[2][1] = (byte) (y-(byte)1);
        }
        else
        {
            retVal[2][0] = (byte) 100;
            retVal[2][1] = (byte) 100;
        }
        if(x>1 && y>0 && board[(byte) (x-2)][(byte) (y-1)] == 0)
        {
            retVal[3][0] = (byte) (x-(byte)2);
            retVal[3][1] = (byte) (y-(byte)1);
        }
        else
        {
            retVal[3][0] = (byte)100;
            retVal[3][1] = (byte)100;
        }
        if(x<7 && y>1 && board[(byte) (x+(byte)1)][(byte) (y-(byte)2)] == 0)
        {
            retVal[2][0] = (byte) (x+(byte)1);
            retVal[2][1] = (byte) (y-(byte)2);
        }
        else
        {
            retVal[2][0] = (byte) 100;
            retVal[2][1] = (byte) 100;
        }
        if(x>0 && y>1 && board[(byte) (x-(byte)1)][(byte) (y-(byte)2)] == 0)
        {
            retVal[3][0] = (byte) (x-(byte)1);
            retVal[3][1] = (byte) (y-(byte)2);
        }
        else
        {
            retVal[3][0] = (byte)100;
            retVal[3][1] = (byte)100;
        }
        return retVal;
    }

    public byte[][] possibleMovesBishop(byte x, byte y)
    {//returns a byte array of the possible moves a bishop can make
        byte[][] retval = new byte[13][2];
        byte sizecount = 0;

        for(byte i=x, m=y; i<8 && m<8; i++,m++)
        {
            if(board[(i)][(m)] == 0)
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
            if(board[(i)][(m)] == 0)
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
            if(board[(i)][(m)] == 0)
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
            if(board[(i)][(m)] == 0)
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
    }

    public byte[][] possibleMovesRook(byte x, byte y)
    {//returns a byte array of the possible moves a rook can make
        byte[][] retval = new byte[14][2];
        byte sizecount = 0;

        for(byte i=x, m=y; i<8; i++)
        {
            if(board[(i)][(m)] == 0)
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
            if(board[(i)][(m)] == 0)
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
            if(board[(i)][(m)] == 0)
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
            if(board[(i)][(m)] == 0)
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
}
