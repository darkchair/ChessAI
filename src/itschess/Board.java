/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itschess;

/**
 *
 * @author Ian
 */
public class Board {

    public byte board[][] = new byte [8][8];
    public byte attackboard[][] = new byte[8][8];

    public boolean whiteMove = false;
    public boolean colorWhite = true;
    public boolean ourMove = false;
    
    public boolean ourCheckMate = false;
    public boolean otherCheckMate = false;
    public boolean gameOver = false;

    byte firstX = 0;
    byte firstY = 0;
    byte secondX = 0;
    byte secondY = 0;
    byte thirdX = 0;
    byte thirdY = 0;
    byte fourthX = 0;
    byte fourthY = 0;
    

    byte firstMove = 0;
    byte secondMove = 0;
    byte thirdMove = 0;
    byte fourthMove = 0;
    
    byte possibleLength = 0;

    public boolean done = false;

    public String[] chessMoves = new String[5];

    int depth = 0;

    public byte[][] possibleMoves;
    
    public double boardScore = 0;

    public Board ()
    {
//        byte[] holder = {-3,-5,-4,-2,-1,-4,-5,-3};
//        board[0] = holder; 
//        for(int i = 0; i < 8; i ++)
//        {
//                board[1][i] = -6;
//
//        }
//        for(int i = 2; i < 6; i ++)
//        {
//                for(int j = 0; j < 8; j ++)
//                {
//                        board[i][j] = 0;
//                }
//        }
//        for(int i = 0; i < 8; i ++)
//        {
//                board[6][i] = 6;
//
//        }
//        board[7][0] = 3;
//        board[7][1] = 5;
//        board[7][2] = 4;
//        board[7][3] = 2;
//        board[7][4] = 1;
//        board[7][5] = 4;
//        board[7][6] = 5;
//        board[7][7] = 3;
//
//        for(byte i = 0; i < 8; i ++)
//        {
//                for(byte j = 0; j <8; j++)
//                {
//                        attackboard[i][j] = 0;
//                }
//        }
        
        //Doesn't work in this test case, checkmate is N 31 to 12
        //Knight moves in opposite direction
        byte[] hold1 = {-3,0,-4,-5,-1,-4,0,-3};
        System.arraycopy(hold1, 0, board[0], 0, 8);
        byte[] hold2 = {-6,-6,0,0,-6,-6,-6,-6};
        System.arraycopy(hold2, 0, board[1], 0, 8);
        byte[] hold3 = {0,0,0,0,0,-5,0,0};
        System.arraycopy(hold3, 0, board[2], 0, 8);
        byte[] hold4 = {0,5,0,0,5,0,0,0};
        System.arraycopy(hold4, 0, board[3], 0, 8);
        byte[] hold5 = {0,0,4,0,0,0,0,0};
        System.arraycopy(hold5, 0, board[4], 0, 8);
        byte[] hold6 = {0,0,0,0,0,0,0,0};
        System.arraycopy(hold6, 0, board[5], 0, 8);
        byte[] hold7 = {6,6,0,0,0,6,6,6};
        System.arraycopy(hold7, 0, board[6], 0, 8);
        byte[] hold8 = {3,0,4,0,1,0,0,3};
        System.arraycopy(hold8, 0, board[7], 0, 8);
        
    }
    
    
    public void move(int d)
    {
        depth = d;
        byte y = 0;
        byte x = 0;
        byte move = 0;
        //Initialize
        if(d == 0)
        {
            y = (byte) (firstY);
            x = (byte) (firstX);
            move = firstMove;
        }
        else if(d == 1)
        {
            y = (byte)(secondY);
            x = (byte)(secondX);
            move = secondMove;
        }
        else if(d == 2)
        {
            y = (byte)(thirdY);
            x = (byte)(thirdX);
            move = thirdMove;
        }
        else if(d == 3)
        {
            y = (byte)(fourthY);
            x = (byte)(fourthX);
            move = fourthMove;
        }
        //gatherMoves(y, x);
        //if(possibleMoves == null || move >= possibleMoves.length)
        //{
        //    if(possibleMoves != null )
        //    {
        //            y++;
        //            x++;
        //    }
        //move = 0;
        //Iterate through the board
        for(; y < 8; y++)
        {
            for(; x < 8; x++)
            {
                if(depth%2 == 1)
                {
                    if(board[y][x] < 0)
                    {//if there is a piece to move (black turn)

                        gatherMoves(y, x);

                        for(; move<possibleMoves.length; move++)
                        {
                            if(possibleMoves[move][0] != 100)
                            {//If we find a move return it
                                movePiece(y,x,possibleMoves[move][0],possibleMoves[move][1]);
                                move++;
                                if(depth == 1){
                                    secondMove = move; secondX = x; secondY = y;
                                }
                                if(depth == 3){
                                    fourthMove = move; fourthX = x; fourthY = y;
                                }
                                return;
                            }
                        }
                        move = 0;
                        if(depth == 1)
                            secondMove = 0;
                        if(depth == 3)
                            fourthMove = 0;
                    }
                }
                else
                {
                    if(board[y][x] > 0)
                    {//if there is a piece to move (white turn)
                        
                        gatherMoves(y, x);

                        for(; move<possibleMoves.length; move++)
                        {
                            if(possibleMoves[move][0] != 100)
                            {//If we find a move return it
                                movePiece(y,x,possibleMoves[move][0],possibleMoves[move][1]);
                                move++;
                                if(depth == 0){
                                    firstMove = move; firstX = x; firstY = y;
                                }
                                if(depth == 2)
                                {
                                    thirdMove = move; thirdX = x; thirdY = y;
                                }
                                return;
                            }
                        }
                        move = 0;
                        if(depth == 0)
                            firstMove = move;
                        if(depth == 2)
                            thirdMove = move;
                    }
                }
            }
            x = 0;
        }
        
        
        
        if(depth == 0)
                done = true;
        else if(depth == 3)
        {
                fourthX = 0;
                fourthY = 0;
                fourthMove = 0;
                done = true;
        }
        else if(depth == 2)
        {
                thirdX = 0;
                thirdY = 0;
                thirdMove = 0;
                done = true;
        }
        else if(depth == 1)
        {
                secondX = 0;
                secondY = 0;
                secondMove =0;
                done = true;
        }
    }
    
    public void undo(int d)
    {
        depth = d;
        if(chessMoves[depth] != null)
        {
            String oldX = String.valueOf(chessMoves[depth].charAt(2));
            String oldY = String.valueOf(chessMoves[depth].charAt(1));
            String currX = String.valueOf(chessMoves[depth].charAt(4));
            String currY = String.valueOf(chessMoves[depth].charAt(3));
            String piece = String.valueOf(chessMoves[depth].charAt(0));
            char pc = chessMoves[depth].charAt(5);
            byte pieceCaptured;
            if(pc == '-')
                pieceCaptured = Byte.parseByte(Character.toString(chessMoves[depth].charAt(5)) + chessMoves[depth].charAt(6));
            else
                pieceCaptured = Byte.parseByte(Character.toString(chessMoves[depth].charAt(5)));

            movePiece((byte)Integer.parseInt(currY),(byte)Integer.parseInt(currX),
                    (byte)Integer.parseInt(oldY),(byte)Integer.parseInt(oldX));//not supposed to use movePiece()?
            board[Integer.parseInt(currY)][Integer.parseInt(currX)] = pieceCaptured;

            String tempStr = "";
            tempStr += piece;
            tempStr += oldY; tempStr += oldX; tempStr += currY; tempStr += currX;
            if(tempStr.compareTo("60000") > 0)
            {
                //System.out.println();
            }
            //chessMoves[depth] = tempStr;
        }

        if(depth == 2)
        {
            fourthX = 0;
            fourthY = 0;
        }
        else if(depth == 1)
        {
            thirdX = 0;
            thirdY = 0;
        }
        else if(depth == 0)
        {
            secondX = 0;
            secondY = 0;
        }
    }

    public void gatherMoves(byte y, byte x)
    {
            if(board[y][x] == -6)
            {
                    possibleMoves = possibleMovesPB(y,x);
            }
            else if(board[y][x] == 6)
            {
                    possibleMoves = possibleMovesP(y,x);
            }
            else if (Math.abs(board[y][x]) == (byte) 5)
            {
                    possibleMoves  = possibleMovesN(y,x);
            }
            else if (Math.abs(board[y][x]) == (byte) 4)
            {
                    possibleMoves  = possibleMovesBishop(y,x);
            }
            else if (Math.abs(board[y][x]) == (byte) 3)
            {
                    possibleMoves  = possibleMovesRook(y,x);
            }
            else if (Math.abs(board[y][x]) == (byte) 2)
            {
                    possibleMoves  = possibleMovesQ(y,x);
            }
            else if (Math.abs(board[y][x]) == (byte) 1)
            {
                    possibleMoves  = possibleMovesBishop(y,x);
            }
            else 
                    possibleMoves = null;

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
                                        //attackboard[moves[k][0]][moves[k][1]] = (byte) (attackboard[moves[k][0]][moves[k][1]] + 1);
                                }
                        }
                }
            }
        }
    }

    public double eval()
    {
        int k = 0;
        int kb = 0;
        int q =0;
        int qb = 0;
        int r =0;
        int rb = 0;
        int b =0;
        int bb = 0;
        int n =0;
        int nb = 0;
        int p =0;
        int pb = 0;
        int d = 0;
        int db = 0;

        for(int i = 0; i < 8; i ++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(board[i][j] == 1)
                    k ++;
                else if(board[i][j] == -1)
                    kb ++;
                else if(board[i][j] == 2)
                    q ++;
                else if(board[i][j] == -2)
                    qb ++;
                else if(board[i][j] == 3)
                    r ++;
                else if(board[i][j] == -3)
                    rb ++;
                else if(board[i][j] == 4)
                    b ++;
                else if(board[i][j] == -4)
                    bb ++;
                else if(board[i][j] == 5)
                    n ++;
                else if(board[i][j] == -5)
                    nb ++;
                else if(board[i][j] == 6)
                {
                    p ++;
                    if (i > 0 && board[i-1][j] == 6)
                            d ++;
                }
                else if(board[i][j] == -6)
                {
                    pb ++;
                    if (i < 6 && board[i+1][j] == -6)
                            db ++;
                }
            }	
        }
        double evalNum = 200*(k - kb) + 9*(q-qb) + 5*(r-rb) + 3*((b-bb) + (n-nb)) + (p-pb) - .5*(d-db);

        return evalNum;
    }

    public void movePiece(byte y, byte x, byte y1, byte x1)
    {
        String retVal = "";
        retVal += Math.abs(board[y][x]);
        String pieceCaptured = Byte.toString(board[y1][x1]);
        board[y1][x1] = board[y][x];
        retVal += "" + y + "" + x + "" + y1 + "" + x1 + "" + pieceCaptured;
        if(retVal.compareTo("60000") > 0)
        {
            //System.out.println();
        }
        chessMoves[depth] =  retVal;
        board[y][x] = 0;
    }

    public byte[][] possibleMovesPB(byte y, byte x)
    {
        byte[][] retVal = new byte[4][2];
        if(y<7 && board[y+1][(byte)(x)] == 0)
        {
            retVal[0][0] = (byte) (y+1);
            retVal[0][1] = x;
        }
        else
        {
            retVal[0][0] = (byte) 100;
            retVal[0][1] = (byte) 100;
        }
        if(board[(byte)(y+2)][x] == 0 && board[y+1][(byte) (x)] == 0 && y == 1)
        {
            retVal[1][0] = (byte) (y+2);
            retVal[1][1] = x;
        }
        else
        {
            retVal[0][0] = (byte) 100;
            retVal[0][1] = (byte) 100;
        }
        if(x<7 && y<7 && board[(byte)(y+1)][(byte)(x+1)] > 0)
        {

            retVal[2][0] = (byte) (y+1);
            retVal[2][1] = (byte) (x+1);
        }
        else
        {
            retVal[2][0] = (byte) 100;
            retVal[2][1] = (byte) 100;
        }
        if(x>0 && y<7 && board[(byte)(y+1)][(byte)(x-1)] > 0 )
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

    public byte[][] possibleMovesP(byte y, byte x)
    {
        byte[][] retVal = new byte[4][2];
        if(y>0 && board[y-1][(byte) (x)] == 0)
        {
                retVal[0][0] = (byte) (y-1);
                retVal[0][1] = x;
        }
        else
        {
                retVal[0][0] = (byte) 100;
                retVal[0][1] = (byte) 100;
        }
        if(board[y-2][(byte) (x)] == 0 && board[y-1][(byte) (x)] == 0 && y == 6)
        {
                retVal[1][0] = (byte)(y-2);
                retVal[1][1] = x;
        }
        else
        {
                retVal[1][0] = (byte) 100;
                retVal[1][1] = (byte) 100;
        }
        if(y>0 && x<7 && board[(byte) (y-1)][(byte) (x+1)] < 0)
        {
                retVal[2][0] = (byte) (y-1);
                retVal[2][1] = (byte) (x+1);
        }
        else
        {
                retVal[2][0] = (byte) 100;
                retVal[2][1] = (byte) 100;
        }
        if(y>0 && x >0 && board[(byte) (y-1)][(byte) (x-1)] < 0)
        {
                retVal[3][0] = (byte) (y-1);
                retVal[3][1] = (byte) (x-1);
        }
        else
        {
                retVal[3][0] = (byte)100;
                retVal[3][1] = (byte)100;
        }
        return retVal;
    }

    public byte[][] possibleMovesN(byte y, byte x)
    {
        byte[][] retVal = new byte[8][2];
            
        boolean white;
        if( board[(byte) y][(byte) x] > 0 )
        {
            white = true;
        }
        else
            white = false;

        if(x>0 && y<6  &&  ((board[(byte) y+2][(byte) x-1] == 0)
                            || (white && board[(byte) y+2][(byte) x-1] < 0)
                            || (!white && board[(byte) y+2][(byte) x-1] > 0)) )
        {
            retVal[0][0] = (byte) (y+2);
            retVal[0][1] = (byte) (x-1);
        }
        else
        {
                retVal[0][0] = (byte) 100;
                retVal[0][1] = (byte) 100;
        }

        if(x<7 && y<6  &&  ((board[ (byte) y+2][(byte) x+1] == 0)
                            || (white && board[(byte) y+2][(byte) x+1] < 0)
                            || (!white && board[(byte) y+2][(byte) x+1] > 0)) )
        {
            retVal[1][0] = (byte) (y+2);
            retVal[1][1] = (byte) (x+1);
        }
        else
        {
            retVal[1][0] = (byte) 100;
            retVal[1][1] = (byte) 100;
        }

        if(x<6 && y<7  &&  ((board[(byte) y+1][(byte) x+2] == 0)
                            || (white && board[(byte) y+1][(byte) x+2] < 0)
                            || (!white && board[(byte) y+1][(byte) x+2] > 0)) )
        {
            retVal[2][0] = (byte) (y+1);
            retVal[2][1] = (byte) (x+2);
        }
        else
        {
            retVal[2][0] = (byte) 100;
            retVal[2][1] = (byte) 100;
        }
        if(x>1 && y<7  &&  ((board[(byte) y+1][(byte) x-2] == 0)
                            || (white && board[(byte) y+1][(byte) x-2] < 0)
                            || (!white && board[(byte) y+1][(byte) x-2] > 0)) )
        {
            retVal[3][0] = (byte) (y+1);
            retVal[3][1] = (byte) (x-2);
        }
        else
        {
            retVal[3][0] = (byte)100;
            retVal[3][1] = (byte)100;
        }

        if(x<6 && y>0  &&  ((board[(byte) y-1][(byte) x+2] == 0)
                            || (white && board[(byte) y-1][(byte) x+2] < 0)
                            || (!white && board[(byte) y-1][(byte) x+2] > 0)) )
        {
            retVal[4][0] = (byte) (y-1);
            retVal[4][1] = (byte) (x+2);
        }
        else
        {
            retVal[4][0] = (byte) 100;
            retVal[4][1] = (byte) 100;
        }

        if(x>1 && y>0  &&  ((board[(byte) y-1][(byte) x-2] == 0)
                            || (white && board[(byte) y-1][(byte) x-2] < 0)
                            || (!white && board[(byte) y-1][(byte) x-2] > 0)) )
        {
            retVal[5][0] = (byte) (y-1);
            retVal[5][1] = (byte) (x-2);
        }
        else
        {
            retVal[5][0] = (byte)100;
            retVal[5][1] = (byte)100;
        }

        if(x<7 && y>1  &&  ((board[(byte) y-2][(byte) x+1] == 0)
                            || (white && board[(byte) y-2][(byte) x+1] < 0)
                            || (!white && board[(byte) y-2][(byte) x+1] > 0)) )
        {
            retVal[6][0] = (byte) (y-2);
            retVal[6][1] = (byte) (x+1);
        }
        else
        {
            retVal[6][0] = (byte) 100;
            retVal[6][1] = (byte) 100;
        }

        if(x>0 && y>1  &&  ((board[(byte) y-2][(byte) x-1] == 0)
                            || (white && board[(byte) y-2][(byte) x-1] < 0)
                            || (!white && board[(byte) y-2][(byte) x-1] > 0)) )
        {
            retVal[7][0] = (byte) (y-2);
            retVal[7][1] = (byte) (x-1);
        }
        else
        {
            retVal[7][0] = (byte)100;
            retVal[7][1] = (byte)100;
        }
        return retVal;
    }

    public byte[][] possibleMovesRook(byte y, byte x)
    {//returns a byte array of the possible moves a rook can make
        byte[][] retval = new byte[14][2];
        byte sizecount = 0;

        for(byte m=(byte)(x+1), i=y; m<8; m++)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i+1);
                        retval[sizecount][1] = (byte)(m);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=(byte)(x-1), i=y; m>0; m--)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i-1);
                        retval[sizecount][1] = (byte)(m);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=x, i=(byte)(y+1); i<8; i++)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i);
                        retval[sizecount][1] = (byte)(m+1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=x, i=(byte)(y-1); i>0; i--)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i);
                        retval[sizecount][1] = (byte)(m-1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        
        for(int i=sizecount; i<14; i++)
        {
            retval[i][0] = (byte)(100);
            retval[i][1] = (byte)(100);
        }

        return retval;
    }

    public byte[][] possibleMovesBishop(byte y, byte x)
    {//returns a byte array of the possible moves a bishop can make
        byte[][] retval = new byte[13][2];
        //System.out.println("X: " + x + "Y: " + y);
        byte sizecount = 0;

        for(byte m=(byte)(x+1), i=(byte)(y+1); m<8 && i<8; m++,i++)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i+1);
                        retval[sizecount][1] = (byte)(m+1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=(byte)(x-1), i=(byte)(y+1); m>0 && i<8; m--, i++)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i-1);
                        retval[sizecount][1] = (byte)(m+1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=(byte)(x+1), i=(byte)(y-1); m<8 && i>0; m++, i--)
        {
            if(board[(i)][(m)] == 0 && board[i][m] < 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 7 && m != 1)
                {
                   if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i+1);
                        retval[sizecount][1] = (byte)(m-1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=(byte)(x-1), i=(byte)(y-1); m>0 && i>0; m--, i--)
        {
            if(board[(i)][(m)] == 0 && board[i][m] < 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 1 && m != 1)
                {
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i-1);
                        retval[sizecount][1] = (byte)(m-1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        
        for(int i=sizecount; i<13; i++)
        {
            retval[i][0] = (byte)(100);
            retval[i][1] = (byte)(100);
        }

        return retval;
    }

    public byte[][] possibleMovesQ(byte y, byte x)
    {
        byte[][] retval = new byte[27][2];
        byte sizecount = 0;

        for(byte m=(byte)(x+1), i=y; m<8; m++)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i+1);
                        retval[sizecount][1] = (byte)(m);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=(byte)(x-1), i=y; m>0; m--)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i-1);
                        retval[sizecount][1] = (byte)(m);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=x, i=(byte)(y+1); i<8; i++)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i);
                        retval[sizecount][1] = (byte)(m+1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=x, i=(byte)(y-1); i>0; i--)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i);
                        retval[sizecount][1] = (byte)(m-1);
                        sizecount++;
                    }
                }
                break;
            }
        }


        for(byte m=(byte)(x+1), i=(byte)(y+1); m<8 && i<8; m++,i++)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i+1);
                        retval[sizecount][1] = (byte)(m+1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=(byte)(x-1), i=(byte)(y+1); m>0 && i<8; m--, i++)
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
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i-1);
                        retval[sizecount][1] = (byte)(m+1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=(byte)(x+1), i=(byte)(y-1); m<8 && i>0; m++, i--)
        {
            if(board[(i)][(m)] == 0 && board[i][m] < 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 7 && m != 1)
                {
                   if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i+1);
                        retval[sizecount][1] = (byte)(m-1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        for(byte m=(byte)(x-1), i=(byte)(y-1); m>0 && i>0; m--, i--)
        {
            if(board[(i)][(m)] == 0 && board[i][m] < 0)
            {
                retval[sizecount][0] = (byte)(i);
                retval[sizecount][1] = (byte)(m);
                sizecount++;
            }
            else
            {
                if(i != 1 && m != 1)
                {
                    if((board[y][x] > 0 && board[i][m] < 0)
                    || (board[y][x] < 0 && board[i][m] > 0))
                    {
                        retval[sizecount][0] = (byte)(i-1);
                        retval[sizecount][1] = (byte)(m-1);
                        sizecount++;
                    }
                }
                break;
            }
        }
        
        
        for(int i=sizecount; i<27; i++)
        {
            retval[i][0] = (byte)(100);
            retval[i][1] = (byte)(100);
        }

        return retval;

    }

    @Override
    public String toString()
    {
            String retVal = "";
            for(int i =0; i < 8; i ++)
            {
                    for(int j = 0; j < 8; j++)
                    {
                            retVal += board[i][j] + "\t";
                    }
                    retVal += "\n";
            }
            retVal += "\n";
//            for(int i =0; i < 8; i ++)
//            {
//                    for(int j = 0; j < 8; j++)
//                    {
//                            retVal += attackboard[i][j] + "\t";
//                    }
//                    retVal += "\n";
//            }
            return retVal;
    }
}