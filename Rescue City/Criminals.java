import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Criminals here.
 * 
 * @author (Fatima Yahya, Vivian Luo) 
 * @version (a version number or a date)
 */
public abstract class Criminals extends People
{
    // Instance Variables
    protected int houseNumberY;
    protected int houseNumberX;
    protected int randomNumber;
    protected GameWorld gameWorld;
    
    private int rotation;
    private int countDown;
 
    private CriminalDepot home;
    private Jail jail;
    private boolean criminalCaught;
    private boolean escape;
    /**
     * Constructor for Criminals - calls the People constructor and sets initial values
     * @param maxSpeed The maximum speed at which the object should travel at.
     */
    public Criminals(int maxSpeed)
    {
        super(maxSpeed);
        rotation = 0;
        countDown = 15 * 60;
        home = new CriminalDepot();
        criminalCaught = false;
        escape = false;
    }
    
    /**
     * Act - do whatever the Criminals wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    /**
     * When added to world, calls the People addedToWorld, 
     * and sets where the criminal depot and the jail is
     * 
     */
    public void addedToWorld(World w)
    {        
        gameWorld = (GameWorld) w;
        super.addedToWorld(w);
        // sets the home's location as there is only one in the world
        home = w.getObjects(CriminalDepot.class).get(0);
        // sets the jails's location as there is only one in the world
        jail = w.getObjects(Jail.class).get(0);
    }
    
    /**
     * if the world is not null, then gets the X coordinate of a random house
     * @return The X coordinate of a random house
     */
    public int getXRandomHouse()
    {
        if (gameWorld != null)
        {
            return gameWorld.returnCoordinatesX(randomNumber);
        }
        else
        { 
            return 1;
        }
    }
    
    /**
     * if the world is not null, then gets the Y coordinate of a random house
     * @return The Y coordinate of a random house
     */
    public int getYRandomHouse()
    {
        if (gameWorld != null)
        {
            return gameWorld.returnCoordinatesY(randomNumber);
        }
        
        else
        { 
            return 1;
        }
    }
    
    /**
     * If the criminal is arrested, he/she is removed from the world
     */
    public void arrest()
    {
        getWorld().removeObject(this);
    }
    
    /**
     * Gets the x coordinate of random house at which the criminal is at/going to.
     * @return The target X coordinate of the criminal
     */
    public int getTargetHouseX()
    {
        return houseNumberX;
    }
    
    /**
     * Gets the y coordinate of random house at which the criminal is at/going to.
     * @return The target Y coordinate of the criminal
     */
    public int getTargetHouseY()
    {
        return houseNumberY;
    }  
    
    /**
     * Returns to the criminal depot and removes the object from the world after a 15 second intervaol of waiting.
     */
    public void walkHome(Criminals c)
    {
            // if the 15 seconds are over
            if (countDown == 0)
            {
                // then the criminal has not arrived at destination
                ifArrived = false;
                // the spawn checks are remanaged and set to theire correct respective locations
                manageSpawnChecks();
                // the criminal walks back to the "home" or "criminal depot"
                walkTo(home.getX(), home.getY());
                // if the criminal is walking towards the house, that means the police officer has not caught the criminal and therefore it has "escaped"
                escape = true;
                // if the object is close en
                if(GameWorld.distanceBetween(this, home) <= 120)
                {
                    ifArrived = true;
                    getWorld().removeObject(checkUp);
                    getWorld().removeObject(checkDown);
                    getWorld().removeObject(checkLeft);
                    getWorld().removeObject(checkRight);
                    getWorld().removeObject(this);
                }
            }
            else
            {
                countDown--;
            }
        
    }
    
    // added this just in case (can later delete if we don't need) 
    public void knockOver () {
        speed = 0;
        setRotation (90);
        rotation = 90;
    }
    
    /**
     * If the policeman shoot the criminal
     */
    public void ifShot () {
        if (speed > 0) {
            speed--; 
        }
        else if (speed == 0)
        {
            setRotation(90);
            // dead = true or smth
        }
    }
    
    /**
     * If the criminal is arrested, then they are sent to jail.
     */
    public void sendToJail()
    {
        ((GameWorld)getWorld()).numArrests += 1;
        jail.increaseNumInJail();
        //setCriminalCaught(true);
        //getWorld().removeObject(this);
    }
    
    public boolean getCriminalCaught()
    {
        return criminalCaught;
    }
    
    public void setCriminalCaught(boolean c)
    {
         criminalCaught = c;
    }
    
    public boolean getEscape()
    {
        return escape;
    }
}
