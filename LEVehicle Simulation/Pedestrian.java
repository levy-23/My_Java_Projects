 import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pedestrian Class
 * 
 * Moves mindlessly up the road, reacting to cars
 * that hit it, ambulances that heal it, and potentially
 * buses that pick it up.
 * 
 * @author Jordan Cohen
 * 
 */

public abstract class Pedestrian extends Actor
{
    // Instance variables
    protected int myWidth;
    protected boolean healthy;
    protected int startSpeed = 1;
    protected int speed;
    protected int upright;
    
    // Constructor
    

    // act() method - called by Greenfoot at every
    // "act" or step of execution
    public void act() 
    {
        // move upwards
        
        setLocation (getX(), getY() - speed);
        
        
        // check if I'm at the edge of the world,
        // and if so, remove myself
        if (atWorldEdge())
        {
            getWorld().removeObject(this);
        }
    }    

    /**
     * Method causes this Pedestrian to stop moving
     * and appear to fall over
     */
    public void knockMeOver ()
    {
        speed = 0;
        setRotation (upright + 90);
        getWorld().setPaintOrder(Vehicle.class, SuperAura.class, Pedestrian.class, ScoreBar.class, Wave.class);
       
    }
    /**
     * Method causes this pedestrian to "heal" - regain
     * upright position and start moving again
     */
    public void healMe ()
    {
        speed = startSpeed;
        setRotation (upright);
    }
    
    /**
     * Method causes the pedestrian to get picked up
     * by the bus - not yet implemented
     */
    public void pickMeUp()
    {
        if(getRotation() == 0){
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Handy method that checks if this object is at the edge
     * of the World
     * 
     * @return boolean  true if at or past the edge of the World, otherwise false
     */
    public boolean atWorldEdge()
    {
        if (getX() < -(myWidth / 2) || getX() > getWorld().getWidth() + (myWidth / 2))
            return true;
        else if (getY() < -(myWidth / 2) || getY () > getWorld().getHeight() + (myWidth / 2))
            return true;
        else
            return false;
    }
    
    public boolean standingUp ()
    {
        return getRotation() == upright;
    }
}
