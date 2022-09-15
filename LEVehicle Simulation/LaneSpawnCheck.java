import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LaneSpawnCheck extends Actor
{
    private GreenfootImage blank;
    private Color highlight;
    
    public LaneSpawnCheck (){
        blank = new GreenfootImage (80, 50);
        
        
        setImage(blank);
    }
    
    public boolean vehiclePresent () {
        return isTouching (Vehicle.class);
    }
    
    public boolean safeToChangeLanes() {
        if(getOneIntersectingObject(Vehicle.class) != null){
            return false;
        }
        else{
            return true;
        }
    }
   
}
