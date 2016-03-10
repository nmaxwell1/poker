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
public class HoleCards {
    private Card card1;
    private Card card2;

    public Card getCard1() {
        return card1;
    }

    public Card getCard2() {
        return card2;
    }

    public HoleCards(Card card1, Card card2) {
        this.card1 = card1;
        this.card2 = card2;
    }

    @Override
    public String toString() {
        return "Hole Cards: " +  card1.toString() + " " + card2.toString();
    }
    
    
}
