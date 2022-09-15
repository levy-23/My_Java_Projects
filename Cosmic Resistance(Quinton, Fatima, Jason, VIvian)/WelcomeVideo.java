import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Video that plays before the game starts
 * 
 * @author (Fatima Yahya, Vivian Luo) 
 * @version June 2021
 */
public class WelcomeVideo extends World
{
    private Video video;
    // private GreenfootSound animationBgm; 
    /**
     * Constructor for objects of class myWorld.
     * 
     */
    public WelcomeVideo()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        video = new Video(); // initialises video animation
        addObject(video, 500, 300); // add animation video to the world
        //Greenfoot.setSpeed(40);
        // animationBgm = new GreenfootSound ("EpicBgm2.mp3"); // adds background music
    }
    
    public void act()
    {
        // startVideoMusic(); // starts the music
        // if enter is clicked, the game is started.
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld (new Instructions());
            // animationBgm.pause(); // if world is changed music is paused
            
            // WelcomeWorld.startStartingBgm(); // and new music is started
        }
        
    }
    
    /**
     *  called automatically by Greenfoot. 
     */
    public void startVideoMusic () {
        // Start playing background music 
        // animationBgm.playLoop(); 
    }
    
}
