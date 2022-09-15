import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen of the game
 * 
 * @author Fatima Yahya
 * @version June 2021
 */
public class WelcomeWorld extends World
{
    private UserInfo user;
    private String name;
    private QMTextbox welcomeText;
    private GreenfootImage image;
    /**
     * Constructor for objects of class WelcomeWorld.
     * 
     */
    public WelcomeWorld()
    {    
        
        super(1000, 600, 1); 
        image = new GreenfootImage("welcomeScreen.png");
        image.scale(1000, 600);
        setBackground(image);
        
        
    }
    
    public void act()
    {
        // startVideoMusic(); // starts the music
        // if enter is clicked, the simulation is started.
        if (Greenfoot.isKeyDown("enter"))
        {
            Greenfoot.setWorld (new WelcomeVideo());
            // animationBgm.pause(); // if world is changed music is paused
            
            // WelcomeWorld.startStartingBgm(); // and new music is started
        }
        
    }
}
