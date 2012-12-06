/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itschess;

/**
 *
 * @author Ian
 */
public class ItsChess 
{
    public static void main(String[] args) {
        
        AlphaBetaSearch.alphaBetaSearch();
        for(int i = 0; i <3 ; i ++)
            System.out.println(AlphaBetaSearch.bestMoves[i]);
        
    }
}
