/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.com.example.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author madi
 */
public class Flop {
    protected List<Card> flop = new ArrayList(3);

    public Flop() {
    }
    public Flop(Card c1, Card c2, Card c3) {
        flop.add(c1);
        flop.add(c2);
        flop.add(c3);
    }

    
    public void addCard(Card card){
        if (flop.size() == 3)
            System.err.print("Already three cards for flop.");
        else
            flop.add(card);
    }
    
    public List<Card> getFlop() {
        return flop;
    }
    
    public String toString(){
        return "Flop: " + flop.get(0)+ " " + flop.get(1) + " " + flop.get(2);
    }
}
