import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class FireStation here.
 * 
 * @author Quinton Mak 
 * @version 4/32/2021
 */
public class FireStation extends Buildings
{
    private int counter;
    private int truckSpawnTime;
    private int numFireTrucks, maxNumFireTrucks;
    private BuildingSpawnCheck carChecker;
    
    private GreenfootImage fireStationFull = new GreenfootImage("FireStationFull.png");
    private GreenfootImage fireStationOne = new GreenfootImage("FireStationOne.png");
    private GreenfootImage fireStationTwo = new GreenfootImage("FireStationTwo.png");
    private GreenfootImage fireStationThree = new GreenfootImage("FireStationThree.png");
    private GreenfootImage fireStationFour = new GreenfootImage("FireStationFour.png");
    private GreenfootImage fireStationFive = new GreenfootImage("FireStationFive.png");
    private GreenfootImage fireStationSix = new GreenfootImage("FireStationSix.png");
    private GreenfootImage fireStationSeven = new GreenfootImage("FireStationSeven.png");
    private GreenfootImage fireStationEight = new GreenfootImage("FireStationEight.png");
    private GreenfootImage fireStationNine = new GreenfootImage("FireStationNine.png");
    private GreenfootImage fireStationEmpty = new GreenfootImage("FireStationEmpty.png");
    /**
     * Act - do whatever the FireStation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        counter++;
        if(counter % truckSpawnTime == 0 && !carChecker.vehiclePresent()){
            ArrayList<Fire> fires = new ArrayList<Fire>();
            fires = (ArrayList) getWorld().getObjects(Fire.class);
            
            boolean hasBurning = false;
            
            for(Fire f : fires){
                if(!f.getFireTruckComing()){
                    hasBurning = true;
                }
            
            }
            
            
            if(hasBurning && numFireTrucks > 0) 
            {
                spawnFiretruck();
            }
        }
        changeImage();
    }    
    
    public FireStation(int maxNumFireTrucks){
        counter = 0;
        truckSpawnTime = 200;
        getImage().scale(190, 65);
        this.maxNumFireTrucks = maxNumFireTrucks;
        numFireTrucks = maxNumFireTrucks;
        
        carChecker = new BuildingSpawnCheck(190, 40);
    }
    
    public void addedToWorld(World w){
        getWorld().addObject(carChecker, getX(), getY() - 50);
    }
    
    public void spawnFiretruck(){
        getWorld().addObject(new FireTruck(), getX(), getY() - 55);
        numFireTrucks = numFireTrucks - 1;
    }
    
    /**
     * Changes the image depending on the number of fire trucks in the world
     */
    public void changeImage()
    {
        if (numFireTrucks == 10)
        {
            this.setImage(fireStationFull);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 9)
        {
            this.setImage(fireStationOne);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 8)
        {
            this.setImage(fireStationTwo);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 7)
        {
            this.setImage(fireStationThree);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 6)
        {
            this.setImage(fireStationFour);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 5)
        {
            this.setImage(fireStationFive);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 4)
        {
            this.setImage(fireStationSix);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 3)
        {
            this.setImage(fireStationSeven);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 2)
        {
            this.setImage(fireStationEight);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 1)
        {
            this.setImage(fireStationNine);
            getImage().scale(190, 65);
        }
        else if (numFireTrucks == 0)
        {
            this.setImage(fireStationEmpty);
            getImage().scale(190, 65);
        }

   }
   
   public void increaseNumFireTrucks()
    {
        numFireTrucks++;
    }
}
