import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class Vehicle extends Actor
{   
    // Variables in abstract classes should be
    // implemented 
    protected int speed;
    protected int maxSpeed;
    //Affect drive methods incase there is a protest
    protected int countProtest; 
    /**
     * Abstract method declarations: 
     * This means that all Vehicles must have a
     * move() and checkHitPedestrian() method with the same signature:
     * E.g: public void move ();
     * 
     * HINT:
     * However, in this current implementation, the drive()
     * method for all three subclasses is the same. Perhaps
     * one of these methods will change if you implement something
     * interesting that requires different drive methods. If not,
     * it would be more efficient to actually write the method
     * here, making it inherited by not abstract.
     */

    // This is a promise - all Vehicles MUST contain this method:
    public abstract void checkHitPedestrian ();

    // These two methods are inherited by any children who do not overwrite them:
    // Remove me if I've gone fully off the edge
    protected void checkEdges(){
        if (getX() < -getImage().getWidth() || getX() > getWorld().getBackground().getWidth() + getImage().getWidth()){
            getWorld().removeObject(this);
        }
    }

    /**
     * Method that deals with movement. Speed can be set by individual subclasses in their constructors
     */
    public void drive() 
    {
        // If there is a wave, vehicles no longer drive forward, but move in the same direction of wave.
        Wave w = (Wave) getOneIntersectingObject(Wave.class);
        if(w != null){
            setLocation(getX(), getY() + 2);
        }
        else{
            //If no vehicle or wave, then drive at the vehicles possible max speed.
            Vehicle ahead = (Vehicle) getOneObjectAtOffset (speed + getImage().getWidth()/2 + 2, 0, Vehicle.class);
            if (ahead == null)
            {
                speed = maxSpeed;
    
            } else {
                //If a vehicle is driving the other way, join them. Vehicles are scared of super turtles and need to drive to where they came from.
                if(ahead.getRotation() == 180){
                    setRotation(180);
                    speed = maxSpeed;
                }
                else{

                    //If no wave check if another vehicle is infront of you, if so match their speed.
                    speed = ahead.getSpeed();
                }
    
            }
            move (speed);
        }
    }   

    /** This is a public method. It will be inherited
     *  by all subclasses of Vehicle and will allow other
     *  Vehicles to attain this Vehicle's speed.
     *  
     **/
    public int getSpeed(){
        return speed;
    }
    
    public void stop(){
        countProtest = 60*4;
    }
}

