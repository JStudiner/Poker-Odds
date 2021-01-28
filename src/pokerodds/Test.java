/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerodds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author anthonyvalenti
 */
public class Test {

    public static final int NOTHING = 0;
    public static final int PAIR = 1;
    public static final int TWO_PAIR = 2;
    public static final int TRIPS = 3;
    public static final int STRAIGHT = 4;
    public static final int FLUSH = 5;
    public static final int FULL_HOUSE = 6;
    public static final int QUADS = 7;
    public static final int STRAIGHT_FLUSH = 8;

    public static void main(String[] args) {
        CardPile deck = new CardPile();
        CardPile burns = new CardPile();
        CardPile ftr = new CardPile();
        CardPile yourCards = new CardPile();
        CardPile oponent = new CardPile();

        /*
        ArrayList<CardPile> players = new ArrayList();
        Scanner c = new Scanner(System.in);
        System.out.println("How many players not including yourself?");
        int numplayers = c.nextInt();
        for (int i = 0; i < numplayers; i++) {
            CardPile p = new CardPile();
            p.add(deck.removeRandom());
            p.add(deck.removeRandom());
            players.add(p);
        }
         */
        for (int i = 2; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(i, j));
            }
        }
        for (int i = 0; i < 3; i++) {
            burns.add(deck.removeRandom());
        }
        for (int i = 0; i < 5; i++) {
            ftr.add(deck.removeRandom());
        }
        for (int i = 0; i < 2; i++) {
            yourCards.add(deck.removeRandom());
            oponent.add(deck.removeRandom());
        }
        System.out.println(checkFullHouse(yourCards, ftr));
        System.out.println("FTR: " + ftr);
        System.out.println("Your Cards" + yourCards);
        System.out.println("Oponent " + oponent);

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
            c.setHand(STRAIGHT);
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
            c.setHand(FLUSH);
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
                c.setHand(PAIR);
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
                        c.setHand(TWO_PAIR);
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
                c.setHand(TRIPS);
                return comVal[i];
            }
        }

        return 0;
    }

    public static boolean checkFullHouse(CardPile c, CardPile ftr) {
        if (checkPair(c, ftr) != 0 && checkTrips(c, ftr) != 0 && checkPair(c, ftr) != checkTrips(c, ftr)) {
            c.setHand(FULL_HOUSE);
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
        c.setHand(comVal[6]);
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

}
