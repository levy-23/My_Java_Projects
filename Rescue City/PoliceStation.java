import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * The police station holds a set number of police cars, each parked over their assigned 
 * spots. They leave their spots when there is a new robbery and the front of their 
 * station is free. When cop cars return back to the station, they re-occupy their 
 * parking spots.
 * 
 * @author (Quinton Mak, Fatima Yahya, Levent Eren) 
 * @version V2
 */
public class PoliceStation extends Buildings
{
    private int counter;
    private int carSpawnTime;
    private int numPoliceCars, maxNumPoliceCars;
    private BuildingSpawnCheck carChecker;
    
    private GreenfootImage policeCarFull = new GreenfootImage("policeCarFull.png");
    private GreenfootImage policeCarOne = new GreenfootImage("policeCarOne.png");
    private GreenfootImage policeCarTwo = new GreenfootImage("policeCarTwo.png");
    private GreenfootImage policeCarThree = new GreenfootImage("policeCarThree.png");
    private GreenfootImage policeCarFour = new GreenfootImage("policeCarFour.png");
    private GreenfootImage policeCarFive = new GreenfootImage("policeCarFive.png");
    private GreenfootImage policeCarSix = new GreenfootImage("policeCarSix.png");
    private GreenfootImage policeCarSeven = new GreenfootImage("policeCarSeven.png");
    private GreenfootImage policeCarEight = new GreenfootImage("policeCarEight.png");
    private GreenfootImage policeCarNine = new GreenfootImage("policeCarNine.png");
    private GreenfootImage policeCarEmpty = new GreenfootImage("policeCarEmpty.png");
    /**
     * Act - do whatever the PoliceStation wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // If spawn time and no car infront of station then possibly add a police car
        counter++;
        if(counter % carSpawnTime == 0 && !carChecker.vehiclePresent()){
            ArrayList<Robbers> robbers = new ArrayList<Robbers>();
            robbers = (ArrayList) getWorld().getObjects(Robbers.class);
            
            boolean hasRobber = false;
            
            for(Robbers r : robbers){
                if(r.getIfArrived() && !r.getPoliceCarComing()){
                    
                 
                    hasRobber = true;
                }
            
             }
            //If robber is at house and no police car is already going to it, then spawn a police car to get to the robbery
            if(hasRobber && numPoliceCars > 0)
            {
                spawnPoliceCar();
            }
        }
        changeImage();
    }    
    
    public PoliceStation(int maxNumPoliceCars){
        getImage().scale(190, 65);
        counter = 0;
        carSpawnTime = 200;
        this.maxNumPoliceCars = maxNumPoliceCars;
        numPoliceCars = maxNumPoliceCars;
        
        carChecker = new BuildingSpawnCheck(190, 40);
        
    }
    
    /**
     * Add car checker
     */
    public void addedToWorld(World w){
        getWorld().addObject(carChecker, getX(), getY() - 50);
    }
    
    /**
     * Add police car
     */
    public void spawnPoliceCar(){
        getWorld().addObject(new PoliceCar(), getX(), getY() - 55);
        numPoliceCars = numPoliceCars - 1;
    }
    
    /**
     * Changes the image depending on the number of criminals in jail at the time
     */
    public void changeImage()
    {
        if (numPoliceCars == 10)
        {
            this.setImage(policeCarFull);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 9)
        {
            this.setImage(policeCarOne);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 8)
        {
            this.setImage(policeCarTwo);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 7)
        {
            this.setImage(policeCarThree);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 6)
        {
            this.setImage(policeCarFour);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 5)
        {
            this.setImage(policeCarFive);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 4)
        {
            this.setImage(policeCarSix);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 3)
        {
            this.setImage(policeCarSeven);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 2)
        {
            this.setImage(policeCarEight);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 1)
        {
            this.setImage(policeCarNine);
            getImage().scale(190, 65);
        }
        else if (numPoliceCars == 0)
        {
            this.setImage(policeCarEmpty);
            getImage().scale(190, 65);
        }

    }
    
    /**
     * Increase the number of police cars, to make sure balance is kept
     */
    public void increaseNumPolicecars()
    {
        numPoliceCars++;
    }
}
