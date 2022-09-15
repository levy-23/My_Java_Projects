import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Instructions of the game, this is essentially an image
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Instructions extends World
{

    /**
     * Constructor for objects of class Instructions.
     * 
     */
    public Instructions()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        setBackground("Instructions.png");
    }
    
    public void act()
    {
        // startVideoMusic(); // starts the music
        // if enter is clicked, the simulation is started.
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld (new MyWorld());
            // animationBgm.pause(); // if world is changed music is paused
            
            // WelcomeWorld.startStartingBgm(); // and new music is started
        }
        
    }
    
}
