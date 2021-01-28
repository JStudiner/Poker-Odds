/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerodds;

import java.util.ArrayList;

/**
 *
 * @author anthonyvalenti
 */
public class PokerHands {

    public static final int NOTHING = 0;
    public static final int PAIR = 1;
    public static final int TWO_PAIR = 2;
    public static final int TRIPS = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int QUADS = 7;
    public static final int STRAIGHT_FLUSH = 8;

    public static CardPile evaluate(CardPile yourCards, ArrayList<CardPile> oponents, CardPile ftr) {
        ArrayList<CardPile> winner = new ArrayList<>();
        findHands(yourCards, ftr);
        winner.add(yourCards);
        oponents.forEach((oponent) -> {
            findHands(oponent, ftr);
            winner.add(oponent);
        });
        for (int i = 1; i < winner.size(); i++) {
            if (winner.get(i).getHand() > winner.get(i - 1).getHand()) {
                winner.remove(i - 1);
            } else {
                winner.remove(i);
            }
        }
        System.out.println("Winning hand: "+winner.get(0));
        System.out.println(winner.get(0).getHand());
        System.out.println(showWinner(winner.get(0)));
        
        return winner.get(0);
    }

    public static void findHands(CardPile c, CardPile ftr) {
        checkHigh(c, ftr);
        checkPair(c, ftr);
        checkTwoPair(c, ftr);
        checkTrips(c, ftr);
        checkStraight(c, ftr);
        checkFlush(c, ftr);
        checkFullHouse(c, ftr);
        checkStraightFlush(c, ftr);
    }

    public static boolean checkStraightFlush(CardPile c, CardPile ftr) {
        return checkStraight(c, ftr) && checkFlush(c, ftr);
    }

    public static boolean checkStraight(CardPile c, CardPile ftr) {
        int[] pVal = new int[2];
        int[] ftrVal = new int[5];
        int[] comVal = new int[7];
        int count = 1;
        boolean straight = false;
        for (int i = 0; i < pVal.length; i++) {
            pVal[i] = c.getCards().get(i).getRank();
            comVal[i] = pVal[i];
        }
        for (int i = 0; i < ftrVal.length; i++) {
            ftrVal[i] = ftr.getCards().get(i).getRank();
            comVal[i + 2] = ftrVal[i];
        }
        sortArr(comVal);
        for (int i = 1; i < comVal.length; i++) {
            if (comVal[i] == comVal[i - 1] + 1) {
                count++;
            } else {
                count = 1;
            }
        }
        if (count >= 5) {
            straight = true;
            if (c.getHand() < STRAIGHT) {
                c.setHand(STRAIGHT);
            }
        }

        return straight;
    }

    public static boolean checkFlush(CardPile c, CardPile ftr) {
        int[] pSuit = new int[2];
        int[] ftrSuit = new int[5];
        int[] comSuit = new int[7];
        int count = 1;
        boolean flush = false;
        for (int i = 0; i < pSuit.length; i++) {
            pSuit[i] = c.getCards().get(i).getSuit();
            comSuit[i] = pSuit[i];
        }
        for (int i = 0; i < ftrSuit.length; i++) {
            ftrSuit[i] = ftr.getCards().get(i).getSuit();
            comSuit[i + 2] = ftrSuit[i];
        }
        sortArr(comSuit);
        for (int i = 1; i < comSuit.length; i++) {
            if (comSuit[i] == comSuit[i - 1]) {
                count++;
            } else {
                count = 1;
            }
        }
        if (count >= 5) {
            if (c.getHand() < FLUSH) {
                c.setHand(FLUSH);
            }
            flush = true;
        }
        return flush;

    }

    public static int checkPair(CardPile c, CardPile ftr) {
        int[] pVal = new int[2];
        int[] ftrVal = new int[5];
        int[] comVal = new int[7];
        for (int i = 0; i < pVal.length; i++) {
            pVal[i] = c.getCards().get(i).getRank();
            comVal[i] = pVal[i];

        }
        for (int i = 0; i < ftrVal.length; i++) {
            ftrVal[i] = ftr.getCards().get(i).getRank();
            comVal[i + 2] = ftrVal[i];
        }
        sortArr(comVal);
        for (int i = 1; i < comVal.length; i++) {
            if (comVal[i] == comVal[i - 1] && comVal[i] != checkTrips(c, ftr)) {
                if (c.getHand() < PAIR) {
                    c.setHand(PAIR);
                }
                return comVal[i];
            }
        }

        return 0;
    }

    public static boolean checkTwoPair(CardPile c, CardPile ftr) {
        int[] pVal = new int[2];
        int[] ftrVal = new int[5];
        int[] comVal = new int[7];
        for (int i = 0; i < pVal.length; i++) {
            pVal[i] = c.getCards().get(i).getRank();
            comVal[i] = pVal[i];

        }
        for (int i = 0; i < ftrVal.length; i++) {
            ftrVal[i] = ftr.getCards().get(i).getRank();
            comVal[i + 2] = ftrVal[i];
        }
        sortArr(comVal);
        for (int i = 1; i < comVal.length; i++) {
            if (comVal[i] == comVal[i - 1] && checkTrips(c, ftr) == 0) {
                for (int j = i + 1; j < comVal.length; j++) {
                    if (comVal[j] == comVal[j - 1]) {
                        if (c.getHand() < TWO_PAIR) {
                            c.setHand(TWO_PAIR);
                        }
                        return true;
                    }

                }
            }

        }

        return false;
    }

    public static int checkTrips(CardPile c, CardPile ftr) {
        int[] pVal = new int[2];
        int[] ftrVal = new int[5];
        int[] comVal = new int[7];
        for (int i = 0; i < pVal.length; i++) {
            pVal[i] = c.getCards().get(i).getRank();
            comVal[i] = pVal[i];

        }
        for (int i = 0; i < ftrVal.length; i++) {
            ftrVal[i] = ftr.getCards().get(i).getRank();
            comVal[i + 2] = ftrVal[i];
        }
        sortArr(comVal);
        for (int i = 2; i < comVal.length; i++) {
            if (comVal[i] == comVal[i - 1] && comVal[i - 1] == comVal[i - 2]) {
                if (c.getHand() < TRIPS) {
                    c.setHand(TRIPS);
                }
                return comVal[i];
            }
        }

        return 0;
    }

    public static boolean checkFullHouse(CardPile c, CardPile ftr) {
        if (checkPair(c, ftr) != 0 && checkTrips(c, ftr) != 0 && checkPair(c, ftr) != checkTrips(c, ftr)) {
            if (c.getHand() < FULL_HOUSE) {
                c.setHand(FULL_HOUSE);
            }
            return true;
        }
        return false;
    }

    public static int checkHigh(CardPile c, CardPile ftr) {
        int[] pVal = new int[2];
        int[] ftrVal = new int[5];
        int[] comVal = new int[7];
        for (int i = 0; i < pVal.length; i++) {
            pVal[i] = c.getCards().get(i).getRank();
            comVal[i] = pVal[i];
        }
        for (int i = 0; i < ftrVal.length; i++) {
            ftrVal[i] = ftr.getCards().get(i).getRank();
            comVal[i + 2] = ftrVal[i];
        }
        sortArr(comVal);
        c.setHand(NOTHING);
        return (comVal[6]);
    }

    public static int[] sortArr(int[] comVal) {
        for (int top = 1; top < comVal.length; top++) {
            int item = comVal[top];
            int i = top;
            while (i > 0 && item < comVal[i - 1]) {
                comVal[i] = comVal[i - 1];
                i--;
            }
            comVal[i] = item;
        }
        return (comVal);
    }

    public static String showWinner(CardPile c) {
        String temp = "";
        if (c.getHand() == NOTHING) {
            return ("High card win");
        }
        if (c.getHand() == PAIR) {
            return ("Pair win");
        }
        if (c.getHand() == TWO_PAIR) {
            return ("Two pair win");
        }
        if (c.getHand() == TRIPS) {
            return ("Trips win");
        }
        if (c.getHand() == STRAIGHT) {
            return ("Straight win");
        }
        if (c.getHand() == FLUSH) {
            return ("Flush win");
        }
        if (c.getHand() == FULL_HOUSE) {
            return ("Full house win");
        }
        if (c.getHand() == QUADS) {
            return ("Quads win");
        }
        if (c.getHand() == STRAIGHT_FLUSH) {
            return ("Straight flush win");
        }
        return ("");

    }

}
