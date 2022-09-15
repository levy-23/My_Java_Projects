import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Animator allows an actor in the world to be animated through alternating between multiple images (sprites). 
 * The animations use acts and occurs at a user specified speed, and the user can specify the images used. 
 * 
 * @author Quinton Mak, Fatima Yahya
 * @version 4/9/2021
 */
public class Animator extends Actor
{
    // Instance Variables
    private String[] images;//Array of images for animation
    private int animateActs, actsPassed;//number of acts between each image
    private Actor owner;
    
    
    /**
     * Act - do whatever the Animator wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    
    /**
     * Allows an actor to be animated. The instance of Animator should be contructed in the owner's constructor.
     * 
     * @param owner The object to be animated
     */
    public Animator(Actor owner){
        this.owner = owner;
        actsPassed = 0;
    }
    
    /**
     * Allows an actor to be animated. The instance of Animator should be contructed in the owner's constructor.
     * 
     * @param owner The object to be animated
     * @param startingImage The image that the actor starts as in the world
     */
    public Animator(Actor owner, String startingImage){
        this.owner = owner;
        actsPassed = 0;
        //Set starting image
        owner.setImage(startingImage);
    }
    
    /**
     * Animates the actor by cycling through an array of images (sprites)
     * 
     * @param images Array of image file names that is cycled through in order to animate the actor. The needed array should be in the owner object. 
     * These image files can be obtained through extracting from a spritesheet using Mr. Cohen's Spritefoot program. Make sure to include file 
     * extensions (.png, .jpeg, etc) in the file names. 
     * @param animateActs The number of acts that will pass before switching to the next image (must be at least 1)
     */
    public void animate(String[] images, int animateActs){
        //If it is a different animation cycle, reset the act counter
        if(this.images != null){    
            if(!this.images.equals(images)){
                actsPassed = 0;
            }
        }
        //Set parameters to the instance variables
        this.images = images;
        this.animateActs = animateActs;
        //If actsPassed/animateActs equals the length of the array, the index is too high and actsPassed must be set back to 0
        if(actsPassed/animateActs == images.length ){
            actsPassed = 0;
        }
        //Every animateActs acts, switch to the next sprite in the array
        if(actsPassed % animateActs == 0){
            owner.setImage(images[actsPassed/animateActs]);
        }
        //Increment the act counter   
        actsPassed ++;
    }
    
    /**
     * Sets the index of images at which the animator is currently at
     * 
     * @return integer of the index of images in the array
     */
    public int getIndexImages()
    {
        return (actsPassed / animateActs);
    }
    
    /**
     * Sets the number of acts passed to the parameter give, essentially resets the animator timer.
     * 
     * @param actsPass  The number of acts that have passed since the new animation cycle, 0 to reset.
     */
    public void setIndexImages(int actsPass)
    {
        actsPassed = actsPass;
    }
    
}
