import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the Base class that creates the home base of the game. The
 * base will get damaged and its animation will change every time 
 * an enemy enters. There is a Counter class that corresponds with 
 * the Base class to keep count of the number of lives the base has. 
 * 
 * Credit for images: Daniel Stephens (http://www.unknown-horizons.org/)
 * 
 * @author Vivian Luo
 * @version June 14, 2021 
 */
public class Base extends Actor
{
    // Declare class variables 
    private int count; 
    // An String array for the images 
    private String[] images; 
    // Static boolean for other class to access 
    private static boolean hasChange; 
    
    // Declare a sounds variable 
    private GreenfootSound s; 
    
    /**
     * Creates a Base object. 
     */
    public Base ()
    {
       count = 4; 
       images = new String [5]; 
       images[count] = ("Base5.png"); 
       this.setImage(images[count]); 
       count = 5; 
       
       // Initialize sound 
       s = new GreenfootSound ("Stomp.wav"); 
    }
    
    /**
     * Changes the base image. 
     */
    public void changeImage()
    {
        if (count == 5)
        {
            count = 4; 
        }
        else if (count == 4)
        {
            count = 3; 
        }
        else if (count == 3)
        {
            count = 2; 
        }
        else if (count == 2)
        {
            count = 1; 
        }

        images[count] = ("Base" + (count) + ".png"); 
        this.setImage(images[count]); 
        hasChange = false; 
    }
    
    /**
     * Checks for animation change every act. 
     */
    public void act() 
    {
        // If image needs to changed -- enemy has entered 
        if (hasChange == true)
        {
            // play sound 
            s.play(); 
            // change the image 
            changeImage(); 
        }
    }    
    
    /**
     * Sets the hasChange boolean to true.  
     */
    public static void change ()
    {
        hasChange = true; 
    }
    
    /**
     * Returns the value of baseCount variable. 
     */
    public int getBaseCount ()
    {
        return count; 
    }
}

