import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (Fatima Yahya) 
 * @version (April 2021)
 */
public class Instructions extends World
{
    private GreenfootImage image;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Instructions()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        image = new GreenfootImage("Instructions.png");
        image.scale(1000, 600);
        setBackground (new GreenfootImage(image));  
    }
    
    public void act()
    {
        // if enter is clicked, the simulation is started.
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld (new MenuWorld());
        }
    }
}
