import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Alarm here.
 * 
 * @author (Fatima Yahya) 
 * @version (April 2021)
 */
public class Alarm extends Actor
{
    // Instance Variables
    public GreenfootImage image;
    private int countDown;
    private GreenfootSound alarmSound; 
    /**
     * Constructor for Alarm - sets sound and initial values
     */
    public Alarm()
    {
        // number of acts to last for
        countDown = 15 * 60;
        alarmSound = new GreenfootSound ("SoundAlarm5.wav"); 
    }
    /**
     * Act - do whatever the Fire wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        // if the number of acts to last for is 0, then remove
        if (countDown == 0)
        {
            getWorld().removeObject(this);
        }
        // otherwise reduce the number of acts to remain in this world for and play an alarm sound.
        else
        {
            countDown--;
            alarmSound.play(); 
        }
    }    
    
    
}
