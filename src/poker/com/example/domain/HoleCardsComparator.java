/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poker.com.example.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author madi
 */
public class HoleCardsComparator implements Comparator<HoleCards> {

    public HoleCardsComparator() {
    }

    @Override
    public int compare(HoleCards player1, HoleCards player2) {
        return 0;

    }

    // merges HoleCards and board into one list of 7 Cards
    public ArrayList<Card> mergeHoleCardsNBoard(HoleCards p, List<Card> list) {
        ArrayList<Card> boardNHoleCards = new ArrayList<>(7);
        // add board cards
        for (Card c : list) {
            boardNHoleCards.add(c);
        }
        //Add hole cards
        boardNHoleCards.add(p.getCard1());
        boardNHoleCards.add(p.getCard2());
        return boardNHoleCards;
    }

    //Given board and hole cards, checks for a possible straight
    //returns 0 if no straight or high card number for straight
    public int hasStraight(HoleCards p, List boardList) {
        ArrayList<Card> boardNHoleCards = mergeHoleCardsNBoard(p, boardList);
        return checkStraightInList(boardNHoleCards);
    }

    // returns 0 if no straight or else return high card for straight
    public int checkStraightInList(List<Card> list) {
        Map<Integer, Integer> map = rankCountMap(list);

        int counter = 1;
        int howHigh = 0;
        int straightLength = 0;

        Set<Integer> mapKeys = map.keySet();        // gets all the keys of map (unique since it's Set)
        Object[] arrayKeys = mapKeys.toArray();     // gets all the keys into an arrey
        int prev = (int) arrayKeys[0];

        System.out.println("Set map.keySet() is : " + mapKeys.toString());

        // check if there is 5 consecutive increasing keys in array    
        for (int i = 1; i < arrayKeys.length; i++) {
            int cur = (int) arrayKeys[i];
            if (prev + 1 == cur) {
                prev = cur;
                counter++;

                if (counter >= 5) {   // straight found
                    howHigh = cur;
                    straightLength = counter;
                }

            } else {                // current key not connected to prev key, reset counter
                counter = 1;
            }
        }
        System.out.println("Player has a " + straightLength + " card straight, " + howHigh + " high.");
        return howHigh;
    }

    // returns 0 if no straight flush else returns how hight straight flush is
    public int checkStraightFlush(List<Card> boardNHoleCards) {
        Suit flushSuit = getFlushSuit(boardNHoleCards);
        // run low overhead procedures first
        if (flushSuit == null || checkStraightInList(boardNHoleCards) == 0) {
            return 0;
        }

        return checkStraightFlushInList(boardNHoleCards, flushSuit);
    }

    // returns 0 if no straight or else return high card for straight
    public int checkStraightFlushInList(List<Card> list, Suit flushSuit) {
        Map<Integer, Integer> map = rankCountMap(list);

        int counter = 1;
        int howHigh = 0;
        int straightLength = 0;

        Set<Integer> mapKeys = map.keySet();        // gets all the keys of map (unique since it's Set)
        Object[] arrayKeys = mapKeys.toArray();     // gets all the keys into an arrey
        int prev = (int) arrayKeys[0];

        System.out.println("Set map.keySet() is : " + mapKeys.toString());

        // check if there is 5 consecutive increasing keys in array    
        for (int i = 1; i < arrayKeys.length; i++) {
            int cur = (int) arrayKeys[i];
            //check if prev n cur are consecutive and flush suit
            if (prev + 1 == cur && cardFound(prev, flushSuit, list) && cardFound(cur, flushSuit, list)) {
                prev = cur;
                counter++;

                if (counter >= 5) {   // straight found
                    howHigh = cur;
                    straightLength = counter;
                }

            } else {                // current key not connected to prev key, reset counter
                counter = 1;
            }
        }
        System.out.println("Player has a " + straightLength + " card straight flush, " + howHigh + " high.");
        return howHigh;
    }

    // gives the highest rank for trips in list
    // i.e if list contains same rank three 
    public int checkHighestTripsInList(List<Card> list) {
        Map<Integer, Integer> map = rankCountMap(list);
        int tripRank = 0;

        Set<Integer> mapKeys = map.keySet();        // gets all the keys of map (unique since it's Set)
        Object[] arrayKeys = mapKeys.toArray();     // gets all the keys into an arrey
        int prev = (int) arrayKeys[0];

        for (int i = 0; i < arrayKeys.length; i++) {
            int key = (int) arrayKeys[i];
            if(map.get(key) == 3)
                return key;
        }    
        System.out.println("Set map.keySet() is : " + mapKeys.toString());
        return tripRank;
    }
// added a new line
    
    public boolean cardFound(int rank, Suit suit, List<Card> boardNHoleCards) {
        for (Card c : boardNHoleCards) {
            if (c.getRank() == rank && c.getSuit() == suit) {
                return true;
            }
        }
        return false;
    }

    // Return highest hole card that comes into play for flush.
    // returns null if no hole card comes into play (None of the same suit or lower than 5 card board flush)
    public Card GetFlushCardInPlay(HoleCards h, List<Card> board) {
        ArrayList<Card> boardNHoleCards = mergeHoleCardsNBoard(h, board);

        Suit flushSuit = getFlushSuit(boardNHoleCards);
        Card card1 = h.getCard1();
        Card card2 = h.getCard2();

        if (flushSuit == null) // if no flush at all
        {
            return null;
        }

        //if 5 card flush on board and hole flush cards lower than board or non flush holdings
        Map<Suit, Integer> map = suitCountMap(board);
        if (map.get(flushSuit) == 5) {
            Collections.sort(board);
            Card lowestOnBoard = board.get(0);
            System.out.println("lowes on board is " + lowestOnBoard);
            //compare hole cards to lowest on board
            if (card1.compareTo(lowestOnBoard) < 1 || card1.getSuit() != flushSuit) {
                if (card2.compareTo(lowestOnBoard) < 1 || card2.getSuit() != flushSuit) {

                    return null;

                }
            }
        }

        Card flushCard = null;

        if (card1.getSuit() == flushSuit) {
            flushCard = card1;
        }

        if (card2.getSuit() == flushSuit) {
            if (flushCard == null || card1.compareTo(card2) < 0) {
                flushCard = card2;
            }
        }
        return flushCard;
    }

    // returns null if no flush or returns the flush suit 
    public Suit getFlushSuit(List<Card> boardNHoleCards) {
        Map<Suit, Integer> map = suitCountMap(boardNHoleCards);

        Set<Suit> mapKeys = map.keySet();

        System.out.println("SuitMap is : " + map);

        for (Suit s : mapKeys) {
            if (map.get(s) >= 5) {
                return s;
            }
        }
        return null;
    }

    //Maps number of times a suit is found in list of cards to that suit.
    public Map suitCountMap(List<Card> list) {
        Map<Suit, Integer> suitCountMap = new HashMap<>();
        for (Card c : list) {
            if (suitCountMap.containsKey(c.getSuit())) {
                int val = suitCountMap.get(c.getSuit());
                val++;
                suitCountMap.put(c.getSuit(), val);
            } else {
                suitCountMap.put(c.getSuit(), 1);
            }

        }

        return suitCountMap;
    }

    //Maps number of times a rank is found in list of cards to that rank.    
    public Map rankCountMap(List<Card> list) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (Card c : list) {
            int rank = c.getRank();
            if (map.containsKey(rank)) {
                int val = map.get(rank);
                val++;
                map.put(rank, new Integer(val));
            } else {
                map.put(rank, new Integer(1));
            }

        }

        return map;
    }

    static void printList(Collection col) {   // with toString implementation of elements

        for (Object item : col) {
            System.out.println("jjj : " + item);
        }
    }

}
