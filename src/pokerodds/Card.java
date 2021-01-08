package pokerodds;

public class Card implements Comparable {
    //Symbolic constants
    public static final int CLUB = 0;
    public static final int DIAMOND = 1;
    public static final int HEART = 2;
    public static final int SPADE = 3;
    private int rank;
    private int suit;
    private boolean faceUp;

    /**
     * Construct a card of the given rank, suit and whether it is faceup or
     * facedown. The rank is an integer from 2 to 14. Numbered cards (2 to 10)
     * have a rank equal to their number. Jack, Queen, King and Ace have the
     * ranks 11, 12, 13, and 14 respectively. The suit is an integer from 0 to 3
     * for Clubs, Diamonds, Hearts and Spades respectively.
     *
     * @param rank
     * @param suit
     * @param faceUp
     */
    public Card(int rank, int suit, boolean faceUp) {
        this.rank = rank;
        this.suit = suit;
        this.faceUp = faceUp;

    }

    /**
     * @return the faceUp
     */
    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * @param faceUp the faceUp to set
     */
    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    /**
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * @return the suit
     */
    public int getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object ob) {
        if (!(ob instanceof Card)) {
            return false;
        }
        Card c = (Card) ob;
        return this.compareTo(ob) == 0;
    }

    @Override
    public int hashCode() {//DO NOT MODIFY
        int hash = 7;
        hash = 31 * hash + this.getRank();
        hash = 31 * hash + this.getSuit();
        return hash;
    }

    @Override
    public int compareTo(Object obj) {//DO NOT MODIFY
        return compareTo((Card) obj);
    }

    public int compareTo(Card c) {
        if (rank < c.rank) {
            return -1;
        }
        if (rank > c.rank) {
            return 1;
        }

        if (suit < c.suit) {
            return -1;
        }
        if (suit > c.suit) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Return the rank as a String. For example, the 3 of Hearts produces the
     * String "3". The King of Diamonds produces the String "King".
     *
     * @return the rank String
     */
    public String getRankString() {
        String[] val = {"Jack", "Queen", "King", "Ace"}; //array of face names corresponding to rank
        if (rank >= 11) {
            return val[rank - 11]; //this gets the string equivalent of the rank, subtracting 11 allows me to index my array of names, EX Jack Rank=11, subtract 11 returns index of 0 in val[]
        } else {
            return ("" + rank); //If its not a face card, simply returns the rank as a string
        }
    }

    /**
     * Return the suit as a String: "Clubs", "Diamonds", "Hearts" or "Spades".
     *
     * @return the suit String
     */
    public String getSuitString() {
        String[] name = {"Club", "Diamond", "Heart", "Spade"}; //array of strings for suit names
        return (name[suit]); //suit as an integer acts as an index for my array of suit strings, EX if suit=0, returns name[0] which is a club
    }

    /**
     * Return "?" if the card is facedown; otherwise, the rank and suit of the
     * card.
     *
     * @return the String representation
     */
    @Override
    public String toString() {
        if (!faceUp) {
            return ("?");
        } else {
            return (getRankString() + " of " + getSuitString() + "s"); // String representation of entire card, Rank and Suit
        }
    }

    public static void main(String[] args) {
        //Create 5 of clubs
        Card club5 = new Card(5, 0, true);
        System.out.println("club5: " + club5);
        Card spadeAce = new Card(14, SPADE, true);
        System.out.println("spadeAce: " + spadeAce);
        System.out.println("club5 compareTo spadeAce: "
                + club5.compareTo(spadeAce));
        System.out.println("club5 compareTo club5: "
                + club5.compareTo(club5));
        System.out.println("club5 equals spadeAce: "
                + club5.equals(spadeAce));
        System.out.println("club5 equals club5: "
                + club5.equals(club5));
    }
}
