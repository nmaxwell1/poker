/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.com.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import poker.com.example.domain.*;
import static poker.com.example.domain.Suit.*;

/**
 *
 * @author madi
 */
public class PokerTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int count = 0;
        boolean found = false;
        while (found == false) {
            found = a();
            count++;
        }
        System.out.println("Count is " + count);

    }

    static boolean a() {
        Deck d = new Deck();
        d.getFlop();
        d.getTurn();
        d.getRiver();

        HoleCardsComparator c = new HoleCardsComparator();
        HoleCards h1 = d.getHoleCards();
        List<Card> boardList = d.getBoardList();
        List boardNHoleCards = c.mergeHoleCardsNBoard(h1, boardList);

        Map rankCountMap = c.rankCountMap(boardNHoleCards);
        System.out.println("rank map : " + rankCountMap);

        if (c.cardFound(14, SPADE, boardList)) {
            System.out.println("Found Ace of Spade");
        }

        boolean tmp = false;
        int straightFlushHighCard = c.checkStraightFlush(boardNHoleCards);
        if (straightFlushHighCard > 0) {
            System.out.println("==========================================");

            tmp = true;
            printList("Board List: ", boardList);
            System.out.println(h1);

            Map suitCountMap = c.suitCountMap(boardNHoleCards);
            System.out.println("suit map : " + suitCountMap);
//            Map rankCountMap = c.rankCountMap(boardNHoleCards);
//            System.out.println("rank map : " + rankCountMap);
//            c.getFlushSuit(boardNHoleCards);
//            Card flushCard = c.GetFlushCardInPlay(h1, boardList);
            System.out.println("Player has " + straightFlushHighCard + " high straigh flush.");

        }

        return tmp;

    }

    static void printList(String s, Collection col) {   // with toString implementation of elements
        System.out.print(s + "[");
        for (Object item : col) {
            System.out.print(item + " ");
        }
        System.out.println("]");
    }

    static Set<Comparable> getUniqueList(Collection<Comparable> list) {
        Set<Comparable> uniqueCardList = new TreeSet<>();

        for (Comparable c : list) {
            uniqueCardList.add(c);
        }
        return uniqueCardList;
    }

}
