import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.Collections;
import java.util.LinkedList;
/**
 * Write a description of class QueueDeck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QueueDeck extends Deck
{

    private Queue<Card> cards;
    public QueueDeck (Card.Colour colour, boolean jokers, int spades, int clubs, int hearts, int diamonds){
        super (colour, jokers, spades, clubs, hearts, diamonds);
    }

    public void act() 
    {
        super.act();
    }    

    /**
     * Shuffle the deck
     */
    public void shuffle()
    {
        // Must treat as a LinkedList in order to shuffle ...
        // Queues don't support shuffling
        Collections.shuffle((LinkedList<Card>)cards);
    }

    /**
     * Get the size of the deck
     * @return the number of cards left in the deck
     */
    public int getSize()
    {
        return cards.size();
    }  

    public void fill (){
       cards = new LinkedList<Card>();
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

    public Card drawCard ()
    {
        if(getSize()==0) return null;
        if(getSize()==0) {
            setImage(new GreenfootImage(Card.CARD_IMAGE_LOCATION+EMPTY_DECK));
        }
        return cards.remove();  
        //return null;
    }

    public Card showCard (){
        if(getSize()==0) return null;
        return cards.peek(); 
    }

    public void addCard (Card card){
        
    }
}
