import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * This is a resource turned into an assignment. First of all, thanks to the original 
 * author: Michael Berry, 2009
 * 
 * 
 * @author Jordan Cohen (modified)
 * @version 2019
 */
public class Table extends World
{

    private Deck deck;

    /**
     * Create a new table
     */
    public Table()
    {    
        super(600, 400, 1);
        // This currently uses an ArrayListDeck - change the type to 
        // test your QueueDeck and StackDeck. Parameters should still
        // be the same, so just change the type
        deck = new QueueDeck(Card.Colour.BLUE, true, 1, 1, 1, 1);
        addObject(deck, 40, 50);
        reset();
    }

    /**
     * If the deck is clicked on, then draw a card from it (unless it's empty.)
     */
    public void act()
    {
        if(Greenfoot.mouseClicked(deck) && deck.getSize()>0) {
            Card c = deck.drawCard();
            if (c.getWorld() == null){
                addObject(c, Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
            } else {
                c.setLocation(Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(getHeight()));
            }
        }
        if(Greenfoot.isKeyDown("space")) {
            reset();
        }
    }

    /**
     * Fill the deck and get rid of cards on the table.
     */
    private void reset()
    {
        for(Actor a : getObjects(Actor.class)) {
            if(! (a instanceof Deck)) {
                removeObject(a);
            }
        }
        deck.fill();
        deck.shuffle();
        //Greenfoot.start();
    }
}
