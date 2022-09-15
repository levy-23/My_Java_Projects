import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CriminalDepot here.
 * Home to all the criminals (Robbers and Arsonists)
 * 
 * @author (Fatima Yahya) 
 * @version (April 2021)
 */
public class CriminalDepot extends Buildings
{
    // Instance Variables
    private GreenfootImage image;
    /**
     * Basic Constructor for CriminalDepot, sets image and scales it.
     */
    public CriminalDepot()
    {
        GreenfootImage image = new GreenfootImage("DepotCriminal.png");
        getImage().scale(150, 130);
    }
    
    /**
     * Act - do whatever the CriminalDepot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
