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
public enum Suit {
    HEART('h'),
    CLUB('c'),
    SPADE('s'),
    DIAMOND('d');
    
    private char suitChar;
    
    
    Suit(char c){
        suitChar = c;
    }

    public char getSuitChar() {
        return suitChar;
    }
    
    
}
