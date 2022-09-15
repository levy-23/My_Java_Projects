import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A bullet can only be fired by a robber and kills Officers if within one pixel.
 * 
 * @author (Fatima Yahya) 
 * @version (April 2021)
 */
public class Bullet extends Actor
{
    // Instance Variables
    private int myWidth;
    private int offset;
    private int speed;
    private Officers o;
    private boolean removeFromWorld;
    
    /**
     * Basic Constructor to initialise variables when bullet is created.
     * @param rotation  Thw direction in which the bullet should travel in
     */
    public Bullet (int rotation)
    {
        // Random rotation direction is selected. 
        setRotation(rotation);
        // Width of image is found - for atEdge purposes.
        GreenfootImage bullet = new GreenfootImage("bullet.png");
        setImage(bullet);
        getImage().scale(15, 10);
        myWidth = bullet.getWidth();
        // sets initial value for speed
        speed = 3;
        removeFromWorld = false;
    }
    
    /**
     * Basic Constructor to initialise variables when bullet is created.
     * The bullet travels in the direction it is facing
     * 
     */
    public Bullet ()
    {
        // Width of image is found - for atEdge purposes.
        GreenfootImage bullet = this.getImage();
        getImage().scale(15, 10);
        // Width of image is found - for atEdge purposes.
        myWidth = bullet.getWidth();
        // sets initial value for speed
        speed = 3;
    }
    
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        // The bullet moves and checks if it is touching an officer
        move(speed);
        checkIfOfficerThenKill();
        // if the bullet reaches the edge of the world, then it is removed.
        if (atWorldEdge())
        {
            removeFromWorld = true;
        }
        if (removeFromWorld)
        {
            getWorld().removeObject(this);
        }
    }    
    
    /**
     * Handy method that checks if this object is at the edge
     * of the World
     * @ Author (Jordan Cohen)
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
    
    
    /**
     * Checks to see if bullet is near an officer and if so shoots and eventually kills according to the health of the officer
     */
    public void checkIfOfficerThenKill()
    {
        // Gets how much infront of the bullet to check
        offset = (this.getImage().getWidth() / 2) + 1;
        // gets if there is an officer present at the predefined offset
        o = (Officers)getOneObjectAtOffset(offset, 0, Officers.class);
        // if there is an officer present,
        if (o != null)
        {
            // and if the bullet is touching the officer
            if (isTouching(Officers.class))
            {
                // then the officers health decreases
                o.decreaseHealth();
                // if the officers health is 0
                if (o.getHealth() == 0)
                {
                    // then the officer is killed - rotation is set to 90, ie knocked over
                    o.knockMeOver();
                    // and the officer is removed from the world
                    getWorld().removeObject(o);
                }
                // the bullet is also removed from the world.
                removeFromWorld = true;
            }
        }
    }
}
