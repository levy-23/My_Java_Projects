import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class FireTruck here.
 * 
 * @author Quinton Mak
 * @version 4/23/2021
 */
public class FireTruck extends Vehicles
{
    private Fire target;
    private FireStation fireStation;
    private int fireCounter;
    private boolean arrivedAtFire;
    //private FireMan fireman;
    
    // Delcare an array to store sound.
    private GreenfootSound[] waterSounds; 
    // Declare a variable to keep count of the sound array index. 
    private int waterSoundsIndex; 
    
    // Delcare an array to store sound.
    private GreenfootSound[] sirenSounds; 
    // Declare a variable to keep count of the sound array index. 
    private int sirenSoundsIndex; 
    public FireTruck()
    {
        super(2);
        fireCounter = 0;
        getImage().scale(60, 25);
        arrivedAtFire = false;
        //fireman = new FireMan();
        
        waterSoundsIndex = 0; 
        // Create 20 copies of the sound file; 
        waterSounds = new GreenfootSound[20]; 
        // Assign each array index the water sound file.
        for (int i = 0; i < waterSounds.length; i++) {
            waterSounds[i] = new GreenfootSound ("SoundWater3.wav"); 
        }
        
        sirenSoundsIndex = 0; 
        // Create 20 copies of the sound file; 
        sirenSounds = new GreenfootSound[20]; 
        // Assign each array index the siren sound file.
        for (int i = 0; i < sirenSounds.length; i++) {
            sirenSounds[i] = new GreenfootSound ("Siren2.wav");  
        }
    }
    
    public void addedToWorld(World w){
        super.addedToWorld(w);
        fireStation = w.getObjects(FireStation.class).get(0);
    }
    
    /**
     * Act - do whatever the FireTruck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //Find a house to go to
        findClosestBurningHouse();
        
        if(target != null){
            //Drive to the house
            driveTo(target.getX(), target.getY());
            
            // Plays the siren sound. 
            sirenSounds[sirenSoundsIndex].play(); 
            sirenSoundsIndex++; 
            if (sirenSoundsIndex >= sirenSounds.length) {
                sirenSoundsIndex = 0; 
            }
            
            //If close enough, put out the fire
            if(GameWorld.distanceBetween(this, target) <= 60 + myWidth/2){
                putOutFire();
                //getWorld().addObject(fireman, getX(), getY());
                // Plays the fire sound. 
                waterSounds[waterSoundsIndex].play(); 
                waterSoundsIndex++; 
                if (waterSoundsIndex >= waterSounds.length) {
                     waterSoundsIndex = 0; 
                }
                
                arrivedAtFire = true;
            }
        }
        else {
            returnToStation(fireStation);
        }
        //headOnCollision();
    }    
    
    /**
     * Finds the closest burning house in the world, and sets it to the fire truck's target
     */
    public void findClosestBurningHouse(){
         if(target != null) target.setFireTruckComing(false); 
        
         ArrayList<Fire> fires = new ArrayList<Fire>();
         fires = (ArrayList) getWorld().getObjects(Fire.class);
         double initialDistance = 1132; // ~sqrt(2) * 800 --> the max possible distance between 2 objects in the world
         //if(target != null) if(target.isOnFire()) initialDistance = GameWorld.distanceBetween(this, target); //If the current target is on fire, look for houses closer than the current target
         boolean hasBurning = false;
         //Pick the closest burning house
         for(Fire f : fires){
             if(!f.getFireTruckComing()){
                 if(GameWorld.distanceBetween(this, f) < initialDistance){
                     target = f;
                     initialDistance = GameWorld.distanceBetween(this, f);
                    }
                 
                 hasBurning = true;
             }
            
         }
         if(!hasBurning) target = null;
         
         if(target != null) target.setFireTruckComing(true); 
    }
    
    /**
     * Puts out the fire at a house
     */
    public void putOutFire(){
        target.putOutFire();
    }
    
}
