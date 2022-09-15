import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Creates a vehicle actor that moves in the world. It is able to turn when there are obstacles in its path.
 * 
 * @author Fatima Yahya
 * @author Quinton Mak
 * @version 4/19/2021
 */
public abstract class Vehicles extends Actor
{
    protected int speed;
    protected int maxSpeed;
    protected int myWidth, myHeight;
    private boolean canMoveStraight;
    private boolean canMoveLeft;
    private boolean canMoveRight;
    private boolean smartDrive;
    
    protected BuildingSpawnCheck checkStraight, checkRight, checkLeft; //Checks in front, right and left of the vehicle, from the vehicle's perspective
    
    private boolean ifArrived;
    
    public Vehicles(int maxSpeed){
        this.maxSpeed = maxSpeed;
        speed = maxSpeed;
         
        ifArrived = false;
        smartDrive = false;
    }
    
    public void addedToWorld(World w){
        myWidth = this.getImage().getWidth();
        myHeight = this.getImage().getHeight();
        
        //Create lane checking objects
        checkStraight = new BuildingSpawnCheck(myWidth/2, 4 * myHeight / 5);
        checkRight = new BuildingSpawnCheck(4*myWidth/5, myHeight);
        checkLeft = new BuildingSpawnCheck(4*myWidth/5, myHeight);
        
        getWorld().addObject(checkLeft, getX(), getY());
        getWorld().addObject(checkStraight, getX(), getY());
        getWorld().addObject(checkRight, getX(), getY());
    }
    
    /**
     * Act - do whatever the Vehicles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }    
  
    // drive ahead, slow down if another vehicle ahead
    public void drive()
    {
        
        
        // checks if house is present in the place where building spawn check spawned
        canMoveStraight = !checkStraight.curbPresent();
        canMoveLeft = !checkLeft.curbPresent();
        canMoveRight = !checkRight.curbPresent();
        
        speed = maxSpeed;
        if (canMoveStraight)
        {
            speed = maxSpeed;
        }
        else if(canMoveRight){
            turn(90);
        }
        else if(canMoveLeft){
            turn(-90);
        }
        else{
            speed = 0;
        }
        
        // moves at speed "speed"
        move (speed);
        
        setSpawnCheckLocation(1, checkRight);
        setSpawnCheckLocation(0, checkStraight);
        setSpawnCheckLocation(-1, checkLeft);
        
        if(isAtEdge()) getWorld().removeObject(this);
    }
    /**
     * The vehicle moves along the roads to a certain point in the world. It stops when near the point (usually the coordinates of a building)
     * 
     * @param x The x coordinate where the vehicle moves to
     * @param y The y coordintate where the vehicle moves to
     * 
     */
    public void driveTo(int x, int y){
        
        int startRotation = getRotation();
        //assuming that the vehicle starts off horizontal
        if(!smartDrive){
            smartDrive = true;
            if(getX() < x){
                setRotation(0);
            }
            else{
                setRotation(180);
            }
        }
        //Set spawn check objects
        manageSpawnChecks();
        
        headOnCollision();
        
        if (canMoveStraight){
            speed = maxSpeed;
        }
        //Determine direction of movement, prioritizing in the order: Direction towards the point, straight, turning away from the point
        if(canMoveStraight || canMoveLeft || canMoveRight){
            if(getRotation() == 0){
                if(getY() - 80 > y){
                    //Ex. If the point is far above the vehicle's location, and the vehicle is facing to the right
                    if(canMoveLeft) turn(-90);//First, try to turn left (face upward toward the point) 
                    else if(canMoveStraight);//Then try to go straight
                    else if(canMoveRight) turn(90);//If above 2 are not possible, try to turn right
                }
                else if(getY() + 80 < y) {
                    //If the point is far below, and the vehicle is facing right
                    if(canMoveRight) turn(90);//Try to turn right
                    else if(canMoveStraight);//Try to go straight
                    else if(canMoveLeft) turn(-90);//Try to turn left
                }
                else{//If the point is only slightly above or below, try to go straight first
                    
                    if(canMoveStraight);
                    else if(canMoveRight) turn(90);
                    else if(canMoveLeft) turn(-90);
                }
            }
            else if(getRotation() == 90){
                if(getX() + 80< x){
                    if(canMoveLeft) turn(-90);
                    else if(canMoveStraight);
                    else if(canMoveRight) turn(90);
                }
                else if(getX() - 80 > x) {
                    if(canMoveRight) turn(90);
                    else if(canMoveStraight);
                    else if(canMoveLeft) turn(-90);
                }
                else{
                    
                    if(canMoveStraight);
                    else if(canMoveRight) turn(90);
                    else if(canMoveLeft) turn(-90);
                }
            }
            else if(getRotation() == 180){
                if(getY() - 80 > y){
                    if(canMoveRight) turn(90);
                    else if(canMoveStraight);
                    else if(canMoveLeft) turn(-90);
                }
                else if(getY() + 80 < y){
                    if(canMoveLeft) turn(-90);
                    else if(canMoveStraight);
                    else if(canMoveRight) turn(90);
                }
                else{
                    
                    if(canMoveStraight);
                    else if(canMoveRight) turn(90);
                    else if(canMoveLeft) turn(-90);
                }
            }
            else if(getRotation() == 270){
                if(getX() - 80 < x){
                    if(canMoveRight) turn(90);
                    else if(canMoveStraight);
                    else if(canMoveLeft) turn(-90);
                }
                else if(getX() + 80 > x){
                    if(canMoveLeft) turn(-90);
                    else if(canMoveStraight);
                    else if(canMoveRight) turn(90);
                }
                else{
                    
                    if(canMoveStraight);
                    else if(canMoveRight) turn(90);
                    else if(canMoveLeft) turn(-90);
                }
            }
        }
        else speed = 0;//If there are no possible directions, stop
        
        //U-turn at edge
        if(isAtEdge()){
            turn(180);
        }
        
        //If close enough to the destination, stop and don't turn
        if(GameWorld.distanceBetween(getX(), getY(), x, y) <= 60 + myWidth/2){
            setRotation(startRotation);
            speed = 0;
            ifArrived = true;
        }
        else{
            ifArrived = false;
        }
        
        move(speed);
        
        if(startRotation != getRotation()){
            move(myWidth/3); //Move forward while turning so the back of the vehicle doesn't "fishtail", and to avoid faulty turn detections
            adjust();
        }
        
    }
    
    /**
     * Gets the speed of the object
     * @return The value of speed at which the object is travelling at.
     */
    public int getSpeed()
    {
        return speed;
    }
    
    /**
     * Determine the rectangular coordinates of where to place a building check object based on the position relative to the vehicle
     * 
     * @param direction The direction on which side of the vehicle to put the BuildingSpawnCheck: -1, 0, 1, correspond to left, front, right, respectively
     * @param check The BuildingSpawnCheck object to be positioned in the world
     */
    public void setSpawnCheckLocation(int direction, BuildingSpawnCheck check){
        //Initialize polar coordinates (r, theta)
        double r;
        int theta; 
 
        if(direction == 0) r = 0.25*myWidth + 5;//Position the front collision checker
        else r = 0.4*myWidth + 0.5* myHeight; //If to the side (left/right) position farther away
        
        theta = 90 * direction + getRotation();//Angle from the +x axis of the spawn check's position
        
        double radTheta = Math.toRadians(theta);//Convert to radians
        
        check.setRotation(getRotation());
        check.setLocation((int)(r* Math.cos(radTheta)) + this.getX(), getY() + (int)(r * Math.sin(radTheta)));
        //Set to rectangular coordinates
        
        if(direction != 0) check.move(myWidth/4 - 8);//Move left and right checks forward a bit
        
        check.setRotation(theta);
       
    }
    
    private void manageSpawnChecks(){
        //Set location of spawn check objects
        setSpawnCheckLocation(1, checkRight);
        setSpawnCheckLocation(0, checkStraight);
        setSpawnCheckLocation(-1, checkLeft);
        //Set booleans based on the presence of obstacles in the vicinity
        canMoveStraight = !checkStraight.curbPresent() && !checkStraight.vehiclePresent(this);
        canMoveLeft = !checkLeft.curbPresent() && !checkLeft.vehiclePresent(this);
        canMoveRight = !checkRight.curbPresent() && !checkRight.vehiclePresent(this);
    }
    
    public boolean canMoveStraight(){
        return canMoveStraight;
    }
    
    public void headOnCollision(){
        if(!canMoveStraight){
            double radRot = Math.toRadians(getRotation());
            Vehicles v = checkStraight.getVehicle(this);
            if(v != null){
                boolean dictateTurn = getX() > v.getX();
                if(!v.canMoveStraight && dictateTurn){
                    if(getRotation() == 0 || getRotation() == 180){
                        if(Math.abs(getX() - 400) < Math.abs(v.getX() - 400)){
                            turn(180);
                        }
                        else{
                            v.turn(180);
                        }
                    }
                    else if(getRotation() == 90 || getRotation() == 270){
                        if(Math.abs(getY() - 400) < Math.abs(v.getY() - 400)){
                            turn(180);
                        }
                        else{
                            v.turn(180);
                        }
                    }
                    
                    
                }
            }
        }
    }
    
    /**
     * Makes sure the vehicle is fully in the road to avoid clipping with houses(curb) 
     */
    public void adjust(){
        int counter = 0;
        manageSpawnChecks();//Set spawn checkers
        
        while(!canMoveStraight){//Move left and right (from vehicle's perspective) increasing the movement amount by 1 each time. In effect, checks pixels beside the vehicles
            //until a location can be found where the vehicle is not clipping the curb, and is fully on the road (This avoids repeated turns)
            
            if(getRotation() == 90 || getRotation() == 270){//Vertical
                if(counter % 2 == 0) setLocation(getX() + (1 + counter), getY());
                else setLocation(getX() - (1 + counter), getY());
            }
            else if(getRotation() == 0 || getRotation() == 180){//Horizontal
                if(counter % 2 == 0) setLocation(getX(), getY() + (1 + counter));
                else setLocation(getX(), getY() - (1 + counter));
            }
            
            manageSpawnChecks();//Set spawn checkers
            counter ++;
        }
    }
    
     /**
     * Drives back to the Station (Police Station / Fire Station).
     * If close enough to the station, then it is removed from the world along with all it's building spawn checks.
     */
    public void returnToStation(FireStation s){
        driveTo(s.getX(), s.getY());
        if(GameWorld.distanceBetween(this, s) <= 90){
            s.increaseNumFireTrucks();
            getWorld().removeObject(checkStraight);
            getWorld().removeObject(checkLeft);
            getWorld().removeObject(checkRight);
            getWorld().removeObject(this);
        }
    }
    
   
    
    public boolean getIfArrived()
    {
        return ifArrived;
    }
}
