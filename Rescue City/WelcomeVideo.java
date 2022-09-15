import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class myWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WelcomeVideo extends World
{
    private Video video;
    private GreenfootSound animationBgm; 
    /**
     * Constructor for objects of class myWorld.
     * 
     */
    public WelcomeVideo()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        video = new Video();
        addObject(video, 500, 300);
        Greenfoot.setSpeed(40);
        animationBgm = new GreenfootSound ("EpicBgm2.mp3"); 
    }
    
    public void act()
    {
        startVideoMusic();
        // if enter is clicked, the simulation is started.
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld (new Instructions());
            animationBgm.pause(); 
            
            WelcomeWorld.startStartingBgm();
        }
        
    }
    
    /**
     *  called automatically by Greenfoot. 
     */
    public void startVideoMusic () {
        // Start playing background music 
        animationBgm.playLoop(); 
    }
    
}
