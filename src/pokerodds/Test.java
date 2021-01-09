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
        ArrayList<CardPile> players = new ArrayList();
        CardPile yourCards = new CardPile();
        Scanner c = new Scanner(System.in);
        System.out.println("How many players not including yourself?");
        int numplayers = c.nextInt();
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
        }
        for (int i = 0; i < numplayers; i++) {
            CardPile p = new CardPile();
            p.add(deck.removeRandom());
            p.add(deck.removeRandom());
            players.add(p);
        }
        System.out.println("FTR: " + ftr);
        System.out.println("Your Cards" + yourCards);
        System.out.println("PLayers " + players);
        if (checkStraight(yourCards, ftr)>=5) {
            System.out.println("You have a straight");
        } else {
            System.out.println("No Straight");
        }
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
        for (int i = 0; i < comVal.length; i++) {
            System.out.println(comVal[i]);
        }
        for (int i = 1; i < comVal.length; i++) {
            if (comVal[i] == comVal[i - 1] + 1) {
                count++;
                System.out.println("Count: " + count);
            } 
        }
        return count;
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
