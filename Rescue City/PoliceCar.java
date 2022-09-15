import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Police Cars rush to robbed houses to catch robbers in the act! If they are fast
 * enough, they can ambush the robber at the house. Two police men step out of the 
 * car and rush the robber. Lives can be lost, but in the end its all in the name
 * of duty. 
 * 
 * 
 * @author (Levent Eren, Fatima Yahya, Quinton Mak)
 * @version V.7.1
 */
public class PoliceCar extends Vehicles
{
    // Instance Variables
    
    private Robbers targetRobber;
    private PoliceStation policeStation;
    private PoliceMan policeman;
    private boolean returnToStation;
    
    private House targetRobbery;
    private ArrayList<House> housesInWorld = new ArrayList<House>();
    private double initialDistanceHouse;
    
    private ArrayList<Criminals> criminalsInRange = new ArrayList<Criminals>();
    
    private Criminals c;
    
    private int arrestCounter;    
    // Delcare an array to store sound.
    private GreenfootSound[] sirenSounds; 
    // Declare a variable to keep count of the sound array index. 
    private int sirenSoundsIndex;
    
    private int arrestAfterCount;
    
    private boolean robberIsAtHouse;
    private boolean copsAreBack;
    private boolean standOffOnce;
    private int stopCounter;
    private int returnCounter;
    public PoliceCar()
    {
        super(2);
        getImage().scale(60, 20);
        policeman = new PoliceMan(this);
        returnToStation = false;
        arrestAfterCount = 75;
        arrestCounter = 0;
        returnCounter = 30;
        robberIsAtHouse = false;
        copsAreBack = false;
        standOffOnce = true;
        stopCounter =  2 * 60;
        sirenSoundsIndex = 0; 
        // Create 20 copies of the sound file; 
        sirenSounds = new GreenfootSound[20]; 
        // Assign each array index the siren sound file.
        for (int i = 0; i < sirenSounds.length; i++) {
            sirenSounds[i] = new GreenfootSound ("Siren2.wav");  
        }
        
    }
    
    /**
     * When added to world, calls the Vehicles added to World.
     * Also assigns the police station.
     */
    public void addedToWorld(World w){
        super.addedToWorld(w);
        policeStation = w.getObjects(PoliceStation.class).get(0);
    }
    
    /**
     * Act - do whatever the PoliceCar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        //If police is arresting a criminal, they cannot do anything else
        arrestCounter--;
        arrestAfterCount--;
        
        if(arrestCounter <= 0){
            //Find closest robbery and drives to it.
            
            findClosestRobbery();
        
            //Drive to robbery
            if(targetRobbery != null){
                //Drive to the house of the robbery
                driveTo(targetRobbery.getX(), targetRobbery.getY());
                
                // Plays the siren sound. 
                sirenSounds[sirenSoundsIndex].play(); 
                sirenSoundsIndex++; 
                if (sirenSoundsIndex >= sirenSounds.length) {
                    sirenSoundsIndex = 0; 
                }
                if (arrestAfterCount == 0)
                {
                    //arrest criminals on the way, stop car to arrest them.
                    arrestOnTheWay();
                    arrestAfterCount = 3 * 60;
                }
                
                
                //If robber is still at house, deploy policemen
                if(getIfArrived())
                { //Check if robber is still at house
                    if(checkRobberIsAtHouse())
                    {
                        //If robber is still at house, policemen are deployed
                        standOff();
                    }
                    else
                    {
                        //If robber is not at house, chase the closest robber in the area the cop can see/detect.
                        findClosestRobberAndDrive();
                        //Arrest on the way
                        arrestOnTheWay();
                    }
                }
            }
            //If no robbery to drive to, return to police station
            else
            {
                returnToStation = true;
                returnCounter = 0;
            }
            
            //if cops return to police car after arresting robber, the car can return to station
            if(copsAreBack)
            {
                returnToStation = true;
            }

            // if the police man is dead, return to station
            if (policeman.getIfDead())
            {
                returnToStation = true;
            }
            
            // if return to station is true, then go to the police station
            if (returnToStation)
            {
                if (returnCounter == 0)
                {
                    returnToStation();
                }
                else
                {
                    returnCounter--;
                }
            }
        }
    }    
    
    
    /**
     * Gets boolean copsAreBack, to check if cops are back to the station.
     * 
     * @return boolean True if the cops are back to the car, returns false if they are not
     */
    public boolean getCopsAreBack()
    {
        return copsAreBack;
    }
    
    /**
     * Sets boolean copsAreBack, to update whether cops are back to the car or not
     * 
     * @param boolean whether the cops are back, or not
     */
    public void setCopsAreBack(boolean copsAreBack)
    {
        this.copsAreBack = copsAreBack;
    }
    
    /**
     * Spawns two police men to deal with the robber in the house
     */    
    public void standOff()
    {
        if (stopCounter == 0)
        {
            //Cops exit car from the doors from either side
            if(getRotation() == 0 && standOffOnce)
             {
                 getWorld().addObject(new PoliceMan(this), getX() - 10, getY() + 20) ;
                 getWorld().addObject(new PoliceMan(this), getX() +10 , getY() + 20) ;
                 standOffOnce = false;
             }
             else if(getRotation() == 90 && standOffOnce)
             {
                getWorld().addObject(new PoliceMan(this), getX() - 20, getY() - 10) ;
                getWorld().addObject(new PoliceMan(this), getX() - 20 , getY() + 10) ;
                standOffOnce = false;
               }
               else if(getRotation() == 180 && standOffOnce)
               {
                   getWorld().addObject(new PoliceMan(this), getX() + 10, getY() - 20) ;
                   getWorld().addObject(new PoliceMan(this), getX() - 10 , getY() - 20) ;
                   standOffOnce = false;
               }
               else if(getRotation() == 270 && standOffOnce)
               {
                  getWorld().addObject(new PoliceMan(this), getX() + 20, getY() - 10) ;
                  getWorld().addObject(new PoliceMan(this), getX() + 20 , getY() + 10) ;
                  standOffOnce = false;
               }
        }
        else
        {
            stopCounter--;
        }
    }
    
    /**
     * Checks to see if robber is still in the house
     * 
     * @return boolean True if robber is still in the house, false if robber left already.
     */
    public boolean checkRobberIsAtHouse()
    {
        ArrayList<Robbers> robbersInHouse = (ArrayList<Robbers>) getObjectsInRange(100, Robbers.class);         
        if(robbersInHouse.isEmpty())
        {
            return false;
        }
        else
        {
            Robbers scene = robbersInHouse.get(0);
            if (scene.getIfArrived())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }
    
    /**
     * Sends any criminal it comes across to jail
     */
    public void arrestOnTheWay()
    {
        criminalsInRange = (ArrayList<Criminals>) getObjectsInRange(50, Criminals.class);
        if(criminalsInRange != null){ // if there are criminals close enough, arrest them
            for(Criminals c : criminalsInRange){
                c.sendToJail();
                //Set counter because police are now occupied
                arrestCounter = 3*60;
            }
        }
    }
    
    /**
     * Finds the closest house that is being robbed
     */
    public void findClosestRobbery()
    {
        if(targetRobbery != null) targetRobbery.setPoliceCarComing(false);
        
        housesInWorld = (ArrayList)((GameWorld)getWorld()).getObjects(House.class);
        double initialDistanceHouse = 1132; // ~sqrt(2) * 800 --> the max possible distance between 2 objects in the world
        
        boolean hasRobbed = false;
        
        for(House h : housesInWorld)
        {
            if(!h.getPoliceCarComing()){
                 if(GameWorld.distanceBetween(this, h) < initialDistanceHouse && h.isRobbed())
                 {
                     targetRobbery = h;
                     initialDistanceHouse = GameWorld.distanceBetween(this, h);
                 }
                 
                 hasRobbed = true;
                 
             }
            
        }
        
        if(!hasRobbed) targetRobbery = null;
         
        if(targetRobbery != null) targetRobbery.setPoliceCarComing(true);
    }
    
    /**
     * Finds the closest robber in the world.
     */
    public void findClosestRobberAndDrive()
    {
         if(targetRobber != null)
         {
             targetRobber.setPoliceCarComing(false); 
         }
        
         ArrayList<Robbers> robbers = new ArrayList<Robbers>();
         robbers = (ArrayList) getWorld().getObjects(Robbers.class);
         double initialDistance = 1132; // sqrt(2) * 800 --> the max possible distance between 2 objects in the world
         boolean hasRobber = false;
         // Pick the closest robber
         for(Robbers robber : robbers)
         {
             if(robber.getIfArrived() && !robber.getPoliceCarComing())
             {
                 if(GameWorld.distanceBetween(this, robber) < initialDistance)
                 {
                     targetRobber = robber;
                     initialDistance = GameWorld.distanceBetween(this, robber);
                 }
                 hasRobber = true;
             }
         }
         if(!hasRobber)
         {
             targetRobber = null;
         }    
         
         if(targetRobber != null) 
         {
             targetRobber.setPoliceCarComing(true); 
         }
         
         if(targetRobber != null)
         {
            // Drive to the target robber
            driveTo(targetRobber.getX(), targetRobber.getY());
            // If close enough, add a policeman
            if(GameWorld.distanceBetween(this, targetRobber) <= 45 + myWidth/2 && targetRobber.getIfArrived() && policeman.getHealth() > 0){
                //getWorld().addObject(policeman, getX(), getY());
                //policeman.followRobber(targetRobber);
            }
         }
         else 
         {
            returnToStation = true;
         }
    }
    
    /**
     * Drives back to station, and arrests any criminals along the way.
     */
    public void returnToStation(){
        arrestOnTheWay();
        driveTo(policeStation.getX(), policeStation.getY());
        if(GameWorld.distanceBetween(this, policeStation) <= 90){
            policeStation.increaseNumPolicecars();
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Sets arrest counter
     * 
     * @param int counter length
     */
    public void setArrestCounter(int counter){
        arrestCounter = counter;
    }
    
    /**
     * Gets if it is returning to the station or not
     * 
     * @return true if returning to the station
     */
    public boolean getIfReturningToStation()
    {
        return returnToStation;
    }
    
}
