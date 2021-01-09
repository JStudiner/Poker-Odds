package pokerodds;

public class Card{
    //Symbolic constants
    public static final int CLUB = 0;
    public static final int DIAMOND = 1;
    public static final int HEART = 2;
    public static final int SPADE = 3;
    private int rank;
    private int suit;

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
    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;

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

   
     /*
     * @return the String representation
     */
    @Override
    public String toString() {
        return (getRankString() + " of " + getSuitString() + "s"); // String representation of entire card, Rank and Suit

    }

}
