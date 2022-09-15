import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (Fatima Yahya) 
 * @version (April 2021)
 */
public class WelcomeWorld extends World
{
    private GreenfootImage image;
    private static GreenfootSound startingBgm; 
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public WelcomeWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        image = new GreenfootImage("backgroundOne.png");
        image.scale(1000, 600);
        setBackground (new GreenfootImage(image));  
        startingBgm = new GreenfootSound ("Start1.mp3");
    }
    
    public void act()
    {
        startingBgm.play(); 
        
        // if enter is clicked, the simulation is started.
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld (new WelcomeVideo());
            stopStartingBgm();
        }
    }
    
    /**
     * Stops the starting background music. 
     */
    public static void stopStartingBgm () {
        startingBgm.stop(); 
    }
    
    /**
     * Starts the starting background music. 
     */
    public static void startStartingBgm () {
        startingBgm.playLoop(); 
    }
}


