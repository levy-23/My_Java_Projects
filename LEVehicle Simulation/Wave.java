import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Wave here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wave extends Actor
{
    private GreenfootImage image;
    
    public Wave(){
        //Set new image
        image = new GreenfootImage("wave.png");
        setImage(image);
    }
    /**
     * Act - do whatever the Wave wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Move down the screen
        setLocation(getX(), getY() + 2);
    }    
}
