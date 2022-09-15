import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WaterSpray here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WaterSpray extends Actor
{
    // Instance Variables
    private Animator a;
    private String[] images;
    private int countDown;
    
    /**
     * Constructor for WaterSpray - sets images and initial values
     */
    public WaterSpray()
    {
        images = new String[4];
        for(int i = 0; i < 4; i++){
            images[i] = "Water" + i + ".png";
            //getImage().scale(100, 100);
            //getImage().setTransparency(255);
        }
        a = new Animator(this, images[0]);
        countDown = 255;
        //getWorld().setPaintOrder(WaterSpray.class, Fire.class);
    }
    
    /**
     * Act - do whatever the WaterSpray wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        // rotates through an array of images to mimic the effect of an animation
        a.animate(images, 10);
        getImage().setTransparency(255);
        getImage().scale(150, 150);
        // decreases the number of act that it is in the world for
        countDown--;
        // if the number of acts that it is in the world for is 0
        if (countDown == 0)
        {
            // then the object is removed from the world
            getWorld().removeObject(this);
        }
    }    
    
    
}
