import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SuperAura here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SuperAura extends Actor
{
    private GreenfootImage imageSuperTurtle;
    public SuperAura(){
        //Set new image
        imageSuperTurtle = new GreenfootImage("superAura.png");
        setImage(imageSuperTurtle);
        
    }
    /**
     * Act - do whatever the SuperAura wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        //Sometimes aura stays after turtle leaves. Since aura shhould only exist with turtle, remove if not witha turtle.
        if((Turtle)getOneIntersectingObject(Turtle.class) == null){
            getWorld().removeObject(this);
        }
    }    
}
