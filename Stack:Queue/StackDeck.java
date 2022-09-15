import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Stack;
import java.util.Collections;

/**
 * Your task - Complete this Class, based on the instructions. Be sure
 * to test the result.
 * 
 * @author Jordan Cohen
 * @version December 2019
 */
public class StackDeck extends Deck
{
    private Stack<Card> cards;
    public StackDeck (Card.Colour colour, boolean jokers, int spades, int clubs, int hearts, int diamonds){
        super (colour, jokers, spades, clubs, hearts, diamonds);
    }

    /**
     * Act - do whatever the StackDeck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
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

    
    // STUDENTS WILL COMPLETE THE FOLLOWING METHODS:
    
    public void fill (){
        cards = new Stack<Card>();
        for(Card.Suit suit : Card.Suit.values()) {
            for(int i=0 ; i<num(suit) ; i++) {
                for(Card.Value value : Card.Value.values()) {
                    cards.push(new Card(colour, value, suit, false));
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
        return cards.pop();   
    }

    public Card showCard (){
        if(getSize()==0) return null;
        return cards.peek(); 
    }

    public void addCard (Card card){
        cards.push(card);
    }
}

