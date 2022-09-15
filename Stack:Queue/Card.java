import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A playing card.
 * 
 * @author Michael Berry
 * @version 23/04/09
 */
public class Card  extends Actor
{

    /** The suits a card can belong to */
    public enum Suit {CLUBS, HEARTS, SPADES, DIAMONDS};
    /** The numbers a card can take */
    public enum Value {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};
    /** The colours a card can be */
    public enum Colour {RED, BLUE};

    protected static final String CARD_IMAGE_LOCATION = "images/cards/";
    private int xOffset;
    private int yOffset;
    private Colour colour;
    private Suit suit;
    private Value value;
    private boolean flipped;
    private boolean canDrag;
    
    /**
     * Whether the playing card can be dragged by default or not.
     */
    {
        canDrag = true;
    }
    
    /**
     * Generate a card with random properties (but not a joker)
     */
    public Card() {
        int rand = Greenfoot.getRandomNumber(Suit.values().length);
        suit = Suit.values()[rand];
        rand = Greenfoot.getRandomNumber(Value.values().length);
        value = Value.values()[rand];
        rand = Greenfoot.getRandomNumber(Colour.values().length);
        colour = Colour.values()[rand];
        draw();
    }
    
    /**
     * Generate a card with a colour, suit and value
     * @param colour the colour of the card
     * @param value the value of the card
     * @param suit the suit of the card
     * @param flipped true if the card is face down, false otherwise
     */
    public Card(Colour colour, Value value, Suit suit, boolean flipped) {
        this.colour = colour;
        this.value = value;
        this.suit = suit;
        this.flipped = flipped;
        draw();
    }
    
    /**
     * Special constructor called by the joker
     */
    protected Card(Colour colour, boolean flipped) {
        this.colour = colour;
        this.flipped = flipped;
        value = null;
        suit = null;
        draw();
    }
    
    /**
     * Select the image of the card based on its suit, value and colour
     * and draw it.
     */
    protected void draw() {
        String fileName = CARD_IMAGE_LOCATION;
        if(flipped) {
            fileName += colour;
            fileName += "flip";
        }
        else {
            fileName += value;
            fileName += suit;
        }
        fileName += ".png";
        fileName = fileName.toLowerCase();
        setImage(new GreenfootImage(fileName));
    }
    
    /**
     * Set whether the card is flipped over or not
     * @param flipped true if the card is face down, false otherwise
     */
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
        draw();
    }
    
    /**
     * Set whether we can drag the card around or not
     * @param drag true if we can drag it around, false otherwise
     */
    public void setDraggable(boolean drag) {
        this.canDrag = drag;
    }
    
    /**
     * Determine whether we can drag the card around or not
     * @return true if we can drag it around, false otherwise
     */
    public boolean isDraggable() {
        return canDrag;
    }
    
    /**
     * Determine whether the card is flipped or not
     * @return true if the card is face down, false otherwise
     */
    public boolean isFlipped() {
        return flipped;
    }
    
    /**
     * Get the colour of the card
     * @return the colour of the card
     */
    public Colour getColour() {
        return colour;
    }
    
    /**
     * Get the value of the card
     * @return the value of the card
     */
    public Value getValue() {
        return value;
    }
    
    /**
     * Get the suit of the card
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }
    
    /**
     * If we're allowed to drag the card around, manage the dragging.
     */
    public void act() {
        if(canDrag) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if(mouse!=null && mouse.getActor()==this) {
                if(Greenfoot.mousePressed(this)) {
                    xOffset = getX()-mouse.getX();
                    yOffset = getY()-mouse.getY();
                }
                if(Greenfoot.mouseDragged(this)) {
                    setLocation(mouse.getX()+xOffset, mouse.getY()+yOffset);
                    //Ensure the card being dragged is always on top
                    if(getOneIntersectingObject(Card.class)!=null) {
                        reAdd();
                    }
                }
            }
        }
    }
    
    /**
     * Remove and add the object to the world
     */
    private void reAdd() {
        int x = getX();
        int y = getY();
        int rotation = getRotation();
        World world = getWorld();
        world.removeObject(this);
        world.addObject(this, x, y);
    }
}
