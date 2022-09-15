import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EcoCar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EcoCar extends Vehicle
{
    private int offset;
    private Person p;
    private Turtle t;
    
    private LaneSpawnCheck lanecheck;
    private LaneSpawnCheck lanecheck2;
    private GreenfootSound beepbeep;
   

    public EcoCar ()
    {
        maxSpeed = 6;
        speed = maxSpeed;
        beepbeep = new GreenfootSound("beepbeep.wav");
        
    }

    public void act()
    {
        //If there is a protest, the vehicle should stop whatever it is doing
        countProtest--;
        if(countProtest <= 0){
            checkHitPedestrian (); 
            drive();
            checkEdges();
        }
    }

    // Add a drive() method which changes lanes.
    public void drive () {
        // If there is a wave, vehicles no longer drive forward, but move in the same direction of wave.
        Wave w = (Wave) getOneIntersectingObject(Wave.class);
        if(w != null){
            setLocation(getX(), getY() + 2);
        }
        else{
            Vehicle ahead = (Vehicle) getOneObjectAtOffset (speed + getImage().getWidth()/2 + 2, 0, Vehicle.class);
            t = (Turtle)getOneObjectAtOffset((this.getImage().getWidth() /2) + speed + 2, 0, Turtle.class);
            //The eco car repects turtles and stops for them no matter super or not
            if (t != null && t.standingUp())
            {
                speed = 0;
    
            } else if (ahead != null) {
                if(ahead.getRotation() == 180){
                    //If a car is driving towards you because of a super turtle, turn aswell to keep traffic flowing.
                    setRotation(180);
                    speed = maxSpeed;
                    
                }
                else{
                    //Try to change lanes, if not follow the vehicle in front's speed.
                    speed = ahead.getSpeed();
                    
                    lanecheck = new LaneSpawnCheck(); 
             
                    getWorld().addObject (lanecheck, getX(), getY() - 48);
                
                    lanecheck2 = new LaneSpawnCheck();
                    getWorld().addObject (lanecheck2, getX(), getY() + 48);
                    if(lanecheck.safeToChangeLanes() == true && getY() != 134){
                        setLocation(getX(), getY() - 48);
                        beepbeep.play();
                    }   
                    else if(lanecheck2.safeToChangeLanes() == true && getY() != 374){
                        setLocation(getX(), getY() + 48);
                        beepbeep.play();
                    }
                    getWorld().removeObject(lanecheck);
                    getWorld().removeObject(lanecheck2);
                }
            }
            else {
                //If nothing in front then drive at max speed
                speed = maxSpeed;
            }
            move (speed);
        }
    }

    /**
     * check if I hit a Pedestrian, and if so, act
     * accordingly
     */
    public void checkHitPedestrian ()
    {
        // Check collision for a pedestrian one pixel ahead
        // of the Vehicle
        offset = (this.getImage().getWidth() / 2) + 1;
        
        
        //Kill people
        p = (Person)getOneObjectAtOffset(offset, 0, Person.class);
        if (p != null)
        {
            //System.out.println("got here");
            p.knockMeOver();
        }
        
    }
    
}
