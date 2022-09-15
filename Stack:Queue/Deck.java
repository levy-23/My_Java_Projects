import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;


/**
 * A deck that deals cards.
 * 
 * @author Michael Berry
 * @version 23/04/09
 */
public abstract class Deck extends Actor
{


    protected boolean jokers;
    /** The number of sets of each of the suits. */
    protected int spades, clubs, hearts, diamonds;
    protected Card.Colour colour;
    /** The location of the picture of an empty deck (outline of a deck.) */
    protected static final String EMPTY_DECK = "empty.png";
    protected int showNum;

    
    /**
     * Create a new deck of a certain colour
     * @param colour the colour of the deck
     * @param jokers true if jokers should be included, false otherwise
     */
    public Deck(Card.Colour colour, boolean jokers)
    {
        this.colour = colour;
        this.jokers = jokers;
        spades = 1;
        hearts = 1;
        clubs = 1;
        diamonds = 1;
        setColour();
        fill();
        shuffle();
    }
    
    /**
     * Create a customised deck of a certain colour
     * @param colour the colour of the deck
     * @param jokers true if jokers should be included, false otherwise
     */
    public Deck(Card.Colour colour, boolean jokers, int spades, int clubs, int hearts, int diamonds)
    {
        this.colour = colour;
        this.jokers = jokers;
        this.diamonds = diamonds;
        this.clubs = clubs;
        this.spades = spades;
        this.hearts = hearts;
        setColour();
        fill();
        shuffle();
    }
    
    public abstract void fill();    
    public abstract Card drawCard();   
    public abstract Card showCard();    
    public abstract void addCard (Card card);    
    public abstract void shuffle();    
    public abstract int getSize();
    
    
    
    /**
     * Get the number of sets needed of a particular suit.
     */
    protected int num(Card.Suit suit) {
        if(suit==Card.Suit.CLUBS) {
            return clubs;
        }
        if(suit==Card.Suit.SPADES) {
            return spades;
        }
        if(suit==Card.Suit.DIAMONDS) {
            return diamonds;
        }
        if(suit==Card.Suit.HEARTS) {
            return hearts;
        }
        throw new RuntimeException("Invalid suit...");
    }
       
    /**
     * Determine whether the deck contains jokers.
     * @return true if the deck contains jokers, false otherwise.
     */
    public boolean hasJokers()
    {
        return jokers;
    }
    
    /**
     * Set the deck to a certin colour
     */
    protected void setColour()
    {
        if(colour==Card.Colour.BLUE) {
            setImage(new GreenfootImage("images/cards/blueflip.png"));
        }
        else {
            setImage(new GreenfootImage("images/cards/redflip.png"));
        }
    }  
}
