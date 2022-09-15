import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Robbers here.
 * 
 * Robbers walk to random houses and wait for the police to show up. 
 * They shoot the police with bullets. They have a maximum of 5 bullets per 
 * robber. Three bullets kill a policeman.
 * 
 * @author (Fatima Yahya) 
 * @version (April 2021)
 */
public class Robbers extends Criminals
{   
    // Instance variables
    private GreenfootImage robber; 
    private int actCounter;
    private int bulletsRemaining;
    
    private ArrayList<Officers> nearOfficers = new ArrayList<Officers>();
    private Officers officerNearby;
    private Officers nearestOfficer;
    private double distanceAway;
    private double closestDistance;
    private int initialRotation;
    private boolean policeCarComing;
    
    private boolean officerFound;
    
    private ArrayList<House> nearHouse = new ArrayList<House>();
    private House houseNearby;
    private House nearestHouse;
    private double distanceAwayHouse;
    private double closestDistanceHouse;
    
    private boolean houseFound;
    
    // Delcare an array to store sound.
    private GreenfootSound[] shootingSounds; 
    // Declare a variable to keep count of the sound array index. 
    private int shootingSoundsIndex; 
    
    private boolean isShooting;
    /**
     * Constructor for Robbers, sets initial values
     * Also calls the constructor for Criminals
     */
    public Robbers()
    {
        super(2);
        GreenfootImage robber = new GreenfootImage("robber.png");
        getImage().scale(28, 36); 
        randomNumber = Greenfoot.getRandomNumber(30); 
        actCounter = 0;
        bulletsRemaining = 5; 
        initialRotation = getRotation();
        policeCarComing = false;
        officerFound = false;
        isShooting = false;
        shootingSoundsIndex = 0; 
        // Create 20 copies of the sound file; 
        shootingSounds = new GreenfootSound[20]; 
        // Assign each array index the shooting sound file.
        for (int i = 0; i < shootingSounds.length; i++) {
            shootingSounds[i] = new GreenfootSound ("Shooting2.wav");  
        }
    }
    
    /**
     * When added to world, calls the Criminals added to World, 
     * and picks a random house to go to.
     */
    public void addedToWorld(World w)
    {
        super.addedToWorld(w);
        houseNumberY = getYRandomHouse();
        houseNumberX = getXRandomHouse();
    }
    
    /**
     * Act - do whatever the Robbers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        // Walks to a random house
        if(!officerFound) 
        {
            walkTo(houseNumberX, houseNumberY);
            robHouses();
        }
        // AIf it has arrived, it waits 80 acts and if there are bullets remaining, the robber shoots
        if (ifArrived && (actCounter % 80 == 0) && bulletsRemaining != 0 )
        {
            shoot();
        }
        else if(ifArrived && (actCounter % 80 == 0) && bulletsRemaining == 0 )
        {
            isShooting = false;
        }
        if(officerFound) 
        {
            walkHome(this);
        }
        // After killing/attempting to kill the police, it returns home
        //walkHome();
        actCounter++;
    }   
    
    public boolean getIsShooting()
    {
        return isShooting;
    }
    
    /**
     * Shoots the closest officer within a range of 100 cells
     */
    public void shoot()
    {
        // if Robber is alive,
        if (this.getRotation() == 0)
        {
            // gets a list of officers in a 81 cell radius
            isShooting = true;
            nearOfficers = (ArrayList<Officers>) getObjectsInRange(81, Officers.class);
            nearestOfficer =  null;
            // using  a for loop, iterates through to find the officer that is the closest.
            for (int i = 0; i < nearOfficers.size(); i++) 
            {
                officerNearby = nearOfficers.get(i);
                // the first officer will always be within 81 because that is the condition for the array list so therefore the closest distance can never be larger than 81
                closestDistance = 81;
                // finds the distance away from officer using pythag (Math.hypot)
                distanceAway = Math.hypot(officerNearby.getX() - getX(), officerNearby.getY() - getY());
                if (distanceAway < closestDistance) 
                {
                    nearestOfficer = nearOfficers.get(i);
                    closestDistance = distanceAway;
                }
            }
            // if there is a officer nearby
            if (nearestOfficer != null)
            {   
                // turn towards the officer, so that the bullet touches the officer
                turnTowards(nearestOfficer.getX(), nearestOfficer.getY());
                // if the officer is not dead,
                if (nearestOfficer.getIfDead() ==  false)
                {
                    // then shoot him with a bullet
                    getWorld().addObject(new Bullet(this.getRotation()), getX(), getY());
                    ((GameWorld)getWorld()).numRobberies += 1;
                    // Plays the shooting sound. 
                    shootingSounds[shootingSoundsIndex].play(); 
                    shootingSoundsIndex++; 
                    if (shootingSoundsIndex >= shootingSounds.length) {
                        shootingSoundsIndex = 0; 
                    }
                    // there is one less bullet remaining now.
                    bulletsRemaining--;
                    // the robbers rotation is back to the normal position
                    setRotation(initialRotation);
                }  
                else
                {
                    walkHome(this);
                }
            }
            officerFound = true;
        }
        setRotation(initialRotation);
        
    }
    
    /**
     * Set whether a police car is coming
     * @param policeCarComing whether a police car is coming towards the robber or not
     */
    public void setPoliceCarComing(boolean policeCarComing)
    {
        this.policeCarComing = policeCarComing;
    }
    
    /**
     * Returns whether a police car is approaching the robber
     * @return true if a police car is coming, false if not
     */
    public boolean getPoliceCarComing()
    {
        return policeCarComing;
    }
    
    /**
     * Find the nearest house in range and robs it
     */
    public void robHouses()
    {
        // if the robber has arrived at the target location and the world is not null
        if (ifArrived && getWorld() != null)
        {    
            // an array list with the houses in the range of 100 cells is initialised
            nearHouse = (ArrayList<House>) getObjectsInRange(100, House.class);
            nearestHouse =  null;
            // using  a for loop, iterates through to find the house that is the closest.
            for (int i = 0; i < nearHouse.size(); i++) 
            {
                houseNearby = nearHouse.get(i);
                // the first house will always be within 100 because that is the condition for the array list so therefore the closest distance can never be larger than 100
                closestDistanceHouse = 100;
                // finds the distance away from officer using pythag (Math.hypot)
                distanceAwayHouse = Math.hypot(houseNearby.getX() - getX(), houseNearby.getY() - getY());
                if (distanceAwayHouse < closestDistanceHouse) 
                {
                    nearestHouse = nearHouse.get(i);
                    closestDistanceHouse = distanceAwayHouse;
                }
            }
            // if the closest house is not null (ie no houses in a 100 cell radius)
            if (nearestHouse != null)
            {
                // and if not under construction
                if (nearestHouse.getIfUnderConstruction() == false)
                {
                    // house is robbed, set house robbed
                    nearestHouse.setRobbed(true);
                    
                }
                else
                {
                    walkHome(this);
                }
                
            }
            houseFound = true;
            //getWorld().removeObject(this);
        }  
        
        if(houseFound)
        {
            walkHome(this);
            //escape = true;
        }
    }

   
}
