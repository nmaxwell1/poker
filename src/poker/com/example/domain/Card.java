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
public class Card implements Comparable <Card> {
    private final int rank;
    private final Suit suit;

    public Card(Rank r, Suit suit) {
        this.rank = r.getRank();
        this.suit = suit;
    }
    public Card(int r, Suit suit) {
        this.rank = r;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }
    
    

    
    public String toString(){
        String st = "";
        st = st + rank;
        char c = suit.getSuitChar();
        st = st + " " + suit; //Character.toString(c);
        return st;
    }

    @Override
    public int compareTo(Card  c) {
        if (rank > c.rank)
            return 1;
        else if(rank < c.rank)
            return -1;
        else
            return 0;
    }
    
    
    
}
