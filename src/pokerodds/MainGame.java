/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokerodds;

/**
 *
 * @author anthonyvalenti
 */
import java.util.ArrayList;
import java.util.Scanner;

public class MainGame {
    //ur code is ass
    private static CardPile deck;
    private static CardPile burns;
    private static CardPile ftr;
    private static CardPile yourCards;
    private static ArrayList<CardPile> opponent = new ArrayList<CardPile>();
    private static Scanner c = new Scanner(System.in);

    public static void main(String [] args) {
        deal();
        showCards();
        System.out.println("---------------------");
        PokerHands.evaluate(yourCards, opponent, ftr);

    }

    public static void deal() {
        deck = new CardPile();
        burns = new CardPile();
        ftr = new CardPile();
        yourCards = new CardPile();
        System.out.print("Enter number of oponents: ");
        int numOps = c.nextInt();
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
        for (int i = 0; i < numOps; i++) {
            CardPile c = new CardPile();
            c.add(deck.removeRandom());
            c.add(deck.removeRandom());
            opponent.add(c);
        }
    }

    public static void showCards() {
        System.out.println("Communal cards: " + ftr);
        System.out.println("Your cards: " + yourCards);
        System.out.println("Opponents cards: " + opponent);
    }

}
