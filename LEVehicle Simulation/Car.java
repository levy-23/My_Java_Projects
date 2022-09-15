import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Car extends Vehicle
{
    private int offset;
    private Pedestrian p;
    
    private LaneSpawnCheck lanecheck;
    private LaneSpawnCheck lanecheck2;
    private GreenfootSound beepbeep;
   

    public Car ()
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

    
    public void drive () {
        // If there is a wave, vehicles no longer drive forward, but move in the same direction of wave.
        Wave w = (Wave) getOneIntersectingObject(Wave.class);
        if(w != null){
            setLocation(getX(), getY() + 2);
        }
        else{
            Vehicle ahead = (Vehicle) getOneObjectAtOffset (speed + getImage().getWidth()/2 + 2, 0, Vehicle.class);
            if (ahead == null)
            {
                //If nothing is in front, great, go max speed
                speed = maxSpeed;
            } else {
                if(ahead.getRotation() == 180){
                    // If a vehicle is going the wrong was it is because therer is a super turtle ahead and you need to turn as well.
                    setRotation(180);
                    speed = maxSpeed;
                }
                else{
                    //Try to change lanes, if not drive the speed of the vehicle infront
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
            move (speed);
        }
    }

    /**
     * check if I hit a Pedestrian, and if so, act
     * accordingly
     */
    public void checkHitPedestrian ()
    {
        
        offset = (this.getImage().getWidth() / 2) + 1;
        //If there is an aura then there is a super turtle and vehicles turn around in fear.
        SuperAura s = (SuperAura)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0 , SuperAura.class);
        if(s != null)
        {
            setRotation(180);
        }

        //Kill any pedestrian
        p = (Pedestrian)getOneObjectAtOffset(offset, 0, Pedestrian.class);
        if (p != null)
        {
            //System.out.println("got here");
            p.knockMeOver();
        }
    }
}
