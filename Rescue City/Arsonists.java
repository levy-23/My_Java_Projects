import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Arsonists here.
 * Arsonists walk to a random house on the map and set it on fire. 
 * They admire their work for a few seconds and then head back home. (Criminal Depot)
 * 
 * @author (Vivian Luo,Fatima Yahya, Quinton Mak) 
 * @version (a version number or a date)
 */
public class Arsonists extends Criminals
{
    // Instance Variables
    private GreenfootImage arsonist; 
    private ArrayList<House> nearHouse = new ArrayList<House>();
    private House houseNearby;
    private House nearestHouse;
    private double distanceAway;
    private double closestDistance;
    
    private boolean houseFound;
    private int currX;
    private int currY;
    
    // Delcare an array to store sound.
    private GreenfootSound[] fireSounds; 
    // Declare a variable to keep count of the sound array index. 
    private int fireSoundsIndex; 
    /**
     * Basic Constructor for Arsonists, sets initial values 
     * Also calls the constructor for Criminals
     */
    public Arsonists()
    {
        super(2);
        GreenfootImage arsonist = new GreenfootImage("arsonist.png");
        randomNumber = Greenfoot.getRandomNumber(30); 
        
        houseFound = false;
        
        fireSoundsIndex = 0; 
        // Create 20 copies of the sound file; 
        fireSounds = new GreenfootSound[20]; 
        // Assign each array index the fire sound file.
        for (int i = 0; i < fireSounds.length; i++) {
            fireSounds[i] = new GreenfootSound ("SoundFireWind2.wav"); 
        }
    }
    
    /**
     * Act - do whatever the Arsonists wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(!houseFound) walkTo(houseNumberX, houseNumberY);
        burnHouses();
    }    
    
    /**
     * When added to world, calls the Criminals added to World, 
     * and picks a random house to go to.
     * Also sets the value for the nearest house.
     */
    public void addedToWorld(World w)
    {
        super.addedToWorld(w);
        houseNumberY = getYRandomHouse();
        houseNumberX = getXRandomHouse();
        
        nearHouse = (ArrayList) getWorld().getObjects(House.class);
        nearestHouse = nearHouse.get(randomNumber);
    }
    
    /**
     * Find the nearest house in range and sets it on fire
     */
    public void burnHouses()
    {
        // if the arsonist has arrived at the target location and the world is not null
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
                closestDistance = 100;
                // finds the distance away from officer using pythag (Math.hypot)
                distanceAway = Math.hypot(houseNearby.getX() - getX(), houseNearby.getY() - getY());
                if (distanceAway < closestDistance) 
                {
                    nearestHouse = nearHouse.get(i);
                    closestDistance = distanceAway;
                }
            }
            // if the closest house is not null (ie no houses in a 100 cell radius)
            if (nearestHouse != null)
            {
                // and if not under construction
                if (nearestHouse.getIfUnderConstruction() == false)
                {
                    // the house is set on fire
                    currX = getX();
                    currY = getY();
                    nearestHouse.setOnFire(true);
                    //getWorld().setPaintOrder(Arsonists.class, Fire.class, House.class);
                    // Plays the fire sound. 
                    fireSounds[fireSoundsIndex].play(); 
                    fireSoundsIndex++; 
                    if (fireSoundsIndex >= fireSounds.length) {
                        fireSoundsIndex = 0; 
                    }
                    //setLocation(nearestHouse.getX() + 12, nearestHouse.getY());
                    
                }
                else
                {
                    walkHome(this);
                }
                
            }
            houseFound = true;
            
            //getWorld().removeObject(this);
        }  
        
        if(houseFound) walkHome(this);
    }
    
    
}
