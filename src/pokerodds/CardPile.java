package pokerodds;

/**
 *
 * @author
 */
import java.util.ArrayList;
import java.util.Random;

/**
 * A pile of cards.
 *
 */
public class CardPile {

    private ArrayList<Card> cards;
    private Random rand;
    private int score;
    private boolean winner;

    public CardPile() {
        this.rand = new Random();
        this.cards = new ArrayList();

    }

    /**
     * Add a card to the pile.
     *
     * @param card
     */
    public void add(Card card) {
        cards.add(card);
    }

    /**
     * Remove a card chosen at random from the pile.
     *
     * @return
     */
    public Card removeRandom() {
        return cards.remove(rand.nextInt(cards.size())); //removes card in random order from index between 0 and 51(size of cards array)
    }

    public Card removeSpecific(Card c) {
        return cards.remove(cards.indexOf(c));
    }

    public int getHand() {
        return score;
    }

    public void setHand(int score) {
        this.score += score;
    }

    public int getHandStrength() {
        return score;
    }

    public void setHandStrength(int score) {
        this.score += score;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean getWinner() {
        return (winner);
    }

    /**
     * The string representation is a space separated list of each card.
     *
     * @return
     */
    @Override
    public String toString() {
        return cards + " ";
    }

    /**
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

}
