/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerodds;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anthonyvalenti
 */
public class Test {

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

        System.out.println("FTR: " + ftr);
        System.out.println("Your Cards" + yourCards);
        System.out.println("Oponent " + oponent);
        System.out.println(compare(yourCards, oponent, ftr));
        System.out.println("Your Score: " + yourCards.getScore());
        System.out.println("Oponent Score: " + oponent.getScore());

    }

    public static int checkStraight(CardPile c, CardPile ftr) {
        int[] pVal = new int[2];
        int[] ftrVal = new int[5];
        int[] comVal = new int[7];
        int count = 1;
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
            c.setScore(count);
        }
        return count;
    }

    public static int checkFlush(CardPile c, CardPile ftr) {
        int[] pSuit = new int[2];
        int[] ftrSuit = new int[5];
        int[] comSuit = new int[7];
        int count = 1;
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
            c.setScore(count);
        }
        return count;

    }

    public static int checkPairs(CardPile c, CardPile ftr) {
        int count = 1;
        for (int i = 0; i < ftr.getCards().size(); i++) {
            for (int j = 0; j < c.getCards().size(); j++) {
                if (ftr.getCards().get(i).getRank() == c.getCards().get(j).getRank()) {
                    count++;
                }
            }
        }
        c.setScore(count);
        return count;
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
        c.setScore(comVal[6]);
        return (comVal[6]);
    }

    public static String compare(CardPile yourCards, CardPile oponents, CardPile ftr) {
        checkStraight(yourCards, ftr);
        checkStraight(oponents, ftr);
        checkFlush(yourCards, ftr);
        checkFlush(oponents, ftr);
        checkPairs(yourCards, ftr);
        checkPairs(oponents, ftr);
        if (yourCards.getScore() > oponents.getScore()) {
            return ("You win");
        } else if (yourCards.getScore() == oponents.getScore()) {
            return ("Push");
        } else {
            return ("You lose");
        }
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
