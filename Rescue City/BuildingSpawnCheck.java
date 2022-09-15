import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class BuildingSpawnCheck here.
 * 
 * @author (Jordan Cohen, Fatima Yahya, Quinton Mak)
 * @version (April 2021)
 */
public class BuildingSpawnCheck extends Actor
{
    // Instance Variables
    private GreenfootImage blank;
    private Color highlight;
    
    /**
     * Constructor - Creates a blank rectangle of width 77 and height 36 
     */
    public BuildingSpawnCheck ()
    {
        blank = new GreenfootImage (77, 36);

        // Uncomment the following to see your hidden spawn blocker thingies
        //highlight = new Color (255,0,0, 90);
        //blank.setColor(highlight);
        //blank.fill();
        setImage(blank);
    }
    
    /**
     * Constructor - Creates a blank rectangle of a given width and height
     * @param width Width of the rectangle
     * @param height    Height of the rectangle
     */
    public BuildingSpawnCheck (int width, int height)
    {
        blank = new GreenfootImage (width, height);

        // Uncomment the following to see your hidden spawn blocker thingies
        //highlight = new Color (255,0,0, 90);
        //blank.setColor(highlight);
        //blank.fill();
        setImage(blank);
    }
    
    /**
     * Gets if touching a vehicle
     * @return true if touching a vehicle
     */
    public boolean vehiclePresent () {
        return isTouching (Vehicles.class);
    } 
    
    /**
     * Gets how many vehicles are present
     * @param v How many other than the given vehicle are present
     * @return number of vehicles that are present
     */
    public boolean vehiclePresent(Vehicles v){
        ArrayList<Vehicles> vehicles = (ArrayList) getIntersectingObjects(Vehicles.class);
        vehicles.remove(v);
        return vehicles.size() != 0;
    }
    /**
     * Returns a vehicle in the spawn check, excluding one
     * 
     * @param v The vehicle that is NOT to be counted
     * @return A vehicle in the spawn check
     */
    public Vehicles getVehicle(Vehicles v){
        ArrayList<Vehicles> vehicles = (ArrayList) getIntersectingObjects(Vehicles.class);
        vehicles.remove(v);
        if(vehicles.size() == 0) return null;
        return vehicles.get(0);
    }
    
    /**
     * Gets if touching a house
     * @return true if touching a house
     */
    public boolean housePresent () 
    {
        return isTouching (House.class);
    }
    
    /**
     * Gets if touching a curb
     * @return true if touching a curb
     */
    public boolean curbPresent () 
    {
        return isTouching (Curb.class);
    }
    
    /**
     * Gets if touching the People.class
     * @return true if touching a person
     */
    public boolean peoplePresent () 
    {
        return isTouching (People.class);
    }
    
}
