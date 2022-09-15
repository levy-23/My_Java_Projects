import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Curb here.
 * 
 * @author Levent Eren 
 * @version (a version number or a date)
 */
public class Curb extends Actor
{
    // Instance Variables
    private GreenfootImage empty;
    private Color tester;
    
    /**
     * Creates a empty/colourless rectangle of a given width and height
     * @param x width of given rectangle
     * @param y height of given rectangle
     */
    public Curb(int x, int y)
    {
        empty = new GreenfootImage(x, y);
        //tester = new Color (255,0,0, 90);
        //empty.setColor(tester);
        //empty.fill();
        setImage(empty);
    }
    
    
    /**
     * Act - do whatever the Curb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
