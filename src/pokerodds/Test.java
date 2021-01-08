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
        CardPile yourCards=new CardPile();
        Scanner c = new Scanner(System.in);
        System.out.println("How many players not including yourself?");
        int numplayers = c.nextInt();
        for (int i = 2; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(i, j, true));
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

    }

}
