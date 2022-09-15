import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Collections;
/**
 * Write a description of class ArrayListDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ArrayListDeck extends Deck
{

    private ArrayList<Card> cards;
    public ArrayListDeck (Card.Colour colour, boolean jokers, int spades, int clubs, int hearts, int diamonds){
        super (colour, jokers, spades, clubs, hearts, diamonds);
    }

    public ArrayListDeck (Card.Colour colour, boolean jokers){
        super (colour, jokers);
    }

    /**
     * Fill the deck with a complete set of cards. Get rid of any cards
     * still in the deck.
     */
    public void fill()
    {
        cards = new ArrayList<Card>();
        for(Card.Suit suit : Card.Suit.values()) {
            for(int i=0 ; i<num(suit) ; i++) {
                for(Card.Value value : Card.Value.values()) {
                    cards.add(new Card(colour, value, suit, false));
                }
            }
        }
        if(jokers) {
            cards.add(new Joker(colour, false));
            cards.add(new Joker(colour, false));
        }
        setColour();
    }

    /**
     * Draw a card from the deck.
     * @return the card that's been drawn, or null if no cards are left.
     */
    public Card drawCard()
    {
        if(getSize()==0) return null;
        Card card = cards.get(0);
        cards.remove(card);
        if(getSize()==0) {
            setImage(new GreenfootImage(Card.CARD_IMAGE_LOCATION+EMPTY_DECK));
        }
        return card;
    }

    /**
     * Shows a card from the deck. Same as drawCard, but doesn't remove it.
     * @return the card that's been shown, or null if no cards are left.
     */
    public Card showCard()
    {
        if(getSize()==0) return null;
        showNum = showNum%getSize();
        Card card = cards.get(showNum);
        showNum = (showNum+1)%getSize();
        return card;
    }

    /**
     * Puts a card into the deck.
     * @param card the card to put in the deck.
     */
    public void addCard(Card card)
    {
        cards.add(card);
    }

    /**
     * Shuffle the deck
     */
    public void shuffle()
    {
        Collections.shuffle(cards);
    }
    
        /**
     * Get the size of the deck
     * @return the number of cards left in the deck
     */
    public int getSize()
    {
        return cards.size();
    }

   

}
