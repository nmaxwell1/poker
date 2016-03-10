/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.com.example.domain;

/**
 *
 * @author madi
 */

public enum Rank {
    A(14), K(13), Q(12), J(11);

    private int rank;
    
    Rank(int n){
        rank = n;
    }

    public int getRank() {
        return rank;
    }
    
        
}