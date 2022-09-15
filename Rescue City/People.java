import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class People here.
 * Abstract class that holds shared methods between firemen, policemen, robbers and arsonists.
 * 
 * @author (Fatima Yahya, Quinton Mak) 
 * @version (April 2021)
 */
public abstract class People extends SmoothMover
{
    // Instance Variables
    protected double speed;
    protected double maxSpeed;
    protected int myWidth, myHeight;
    
    protected boolean canMoveUp;
    protected boolean canMoveLeft;
    protected boolean canMoveRight;
    protected boolean canMoveDown;
    protected boolean ifArrived;
    protected BuildingSpawnCheck checkUp, checkRight, checkLeft, checkDown;
    protected String direction;
    protected boolean smartDrive;
    /**
     * Basic constructor for People
     * @param maxSpeed The maximum speed of the object.
     */
    public People(double maxSpeed)
    {
        this.maxSpeed = maxSpeed;
        speed = maxSpeed;
        myWidth = this.getImage().getWidth();
        myHeight = this.getImage().getHeight();
        
        //checkRight = new BuildingSpawnCheck(40, 20);
        //checkDown = new BuildingSpawnCheck(20, 40);
        //checkUp = new BuildingSpawnCheck(20, 40);
        //checkLeft = new BuildingSpawnCheck(40, 20);
        
       
        checkRight = new BuildingSpawnCheck(myWidth/2, 4*myHeight/5);
        checkLeft = new BuildingSpawnCheck(myWidth/2, 4*myHeight/5);
        checkDown = new BuildingSpawnCheck(4*myWidth/5, myHeight/2);
        checkUp = new BuildingSpawnCheck(4*myWidth/5, myHeight/2);
        smartDrive = false;
        ifArrived = false;
        
        direction = "up";
    }
    /**
     * Act - do whatever the People wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
    /**
     * Adds building spawn checks when added to the world
     */
    public void addedToWorld(World w)
    {        
        getWorld().addObject(checkDown, getX(), getY() + 30);
        getWorld().addObject(checkUp, getX(), getY() - 30);
        getWorld().addObject(checkRight, getX() + 30, getY());
        getWorld().addObject(checkLeft, getX() - 30, getY());
        
    }
    
    /**
     * Determine the rectangular coordinates of where to place a building check object based on the direction relative to the criminals
     * 
     */
    protected void manageSpawnChecks(){
        //Set locations based on the direction the criminal is moving
        if(direction.equals("up")){
            checkDown.setLocation(getX(), getY() + myHeight);
            checkUp.setLocation(getX(), getY() - myHeight/4);
            checkRight.setLocation(getX() + myWidth, getY());
            checkLeft.setLocation(getX() - myWidth, getY());

        }
        if(direction.equals("down")){
            checkDown.setLocation(getX(), getY() + myHeight/4);
            checkUp.setLocation(getX(), getY() - myHeight);
            checkRight.setLocation(getX() + myWidth, getY());
            checkLeft.setLocation(getX() - myWidth, getY());

        }
        if(direction.equals("right")){
            checkDown.setLocation(getX(), getY() + myHeight);
            checkUp.setLocation(getX(), getY() - myHeight);
            checkRight.setLocation(getX() + myWidth/4, getY());
            checkLeft.setLocation(getX() - myWidth, getY());

        }
        if(direction.equals("left")){
            checkDown.setLocation(getX(), getY() + myHeight);
            checkUp.setLocation(getX(), getY() - myHeight);
            checkRight.setLocation(getX() + myWidth, getY());
            checkLeft.setLocation(getX() - myWidth/4, getY());

        }
        // Set booleans based on the presence of obstacles in the vicinity
        canMoveUp = !checkUp.curbPresent();
        canMoveDown = !checkDown.curbPresent();
        canMoveRight = !checkRight.curbPresent();
        canMoveLeft = !checkLeft.curbPresent();
    }
    
    
    
    /**
     * Makes the person walk towards a certain point. It stops when near that point
     * 
     * @param x The x-coordinate of the point to walk to (between 0 and world's width)
     * @param y The y-coordinate of the point to walk to (between 0 and world's height)
     */
    public void walkTo(int x, int y){
        
        if(smartDrive = false){
            smartDrive = true;
            if(canMoveUp || canMoveDown){
                if(getY() > y) direction = "up";
                else direction = "down";
            }
            else if(canMoveLeft || canMoveRight){
                if(getX() < x) direction = "right";
                else direction = "left";
            }
        }
        
        
        manageSpawnChecks();
        
        int[] startCoordinates = {getX(), getY()};
        String startDirection = direction;
        
        
        if(canMoveUp || canMoveLeft || canMoveRight || canMoveDown)
        {
            speed = maxSpeed;
            
            //If close enough to the point, don't move anymore
            if(GameWorld.distanceBetween(getX(), getY(), x, y) <= 60 + myHeight/2){
                speed = 0;
                ifArrived = true;
            }
            else ifArrived = false;
            
            //Determine direction of movement, prioritizing in the order: Direction towards the point, straight, turning away from the point
            
            if(direction.equals("right")){
                if(getY() - 80 > y){
                    //Ex. If the point is far above the criminal's location, and the criminal is facing to the right
                    if(canMoveUp) setLocation(getX(), getY() - speed);//First, try to face upward toward the point
                    else if(canMoveRight) setLocation(getX() +  speed, getY());//Then try to go straight
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);//If above 2 are not possible, try to go down
                }
                else if(getY() + 80 < y) {
                    //If the point is far below, and the criminal is facing right
                    if(canMoveDown) setLocation(getX(), getY() +  speed);//Try to go down
                    else if(canMoveRight)  setLocation(getX() +  speed, getY());//Try to go straight
                    else if(canMoveUp) setLocation(getX(), getY() - speed);//Try to go up
                }
                else{//If the point is only slightly above or below, try to go straight first
                    
                    if(canMoveRight) setLocation(getX() +  speed, getY());
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);
                    else if(canMoveUp) setLocation(getX(), getY() - speed);
                }
            }
            else if(direction.equals("up")){
                if(getX() + 80 < x){
                    if(canMoveRight)  setLocation(getX() +  speed, getY());
                    else if(canMoveUp) setLocation(getX(), getY() - speed);
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                }
                else if(getX() - 80 > x) {
                    if(canMoveLeft) setLocation(getX() - speed, getY());
                    else if(canMoveUp) setLocation(getX(), getY() - speed);
                    else if(canMoveRight)  setLocation(getX() +  speed, getY());
                }
                else{
                    
                    if(canMoveUp) setLocation(getX(), getY() - speed);
                    else if(canMoveRight)  setLocation(getX() +  speed, getY());
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                }
            }
            else if(direction.equals("left")){
                if(getY() - 80 > y){
                    
                    if(canMoveUp)setLocation(getX(), getY() - speed);
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);
                }
                else if(getY() + 80 < y) {
                    
                    if(canMoveDown) setLocation(getX(), getY() +  speed);
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                    else if(canMoveUp)setLocation(getX(), getY() - speed);
                }
                else{
                    
                    if(canMoveLeft) setLocation(getX() - speed, getY());
                    else if(canMoveUp)setLocation(getX(), getY() - speed);
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);
                    
                }
            }
            else if(direction.equals("down")){
                if(getX() + 80 < x){
                    if(canMoveRight)  setLocation(getX() +  speed, getY());
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                }
                else if(getX() - 80 > x) {
                    if(canMoveLeft) setLocation(getX() - speed, getY());
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);
                    else if(canMoveRight)  setLocation(getX() +  speed, getY());
                }
                else{
                    
                    if(canMoveDown) setLocation(getX(), getY() +  speed);
                    else if(canMoveRight)  setLocation(getX() +  speed, getY());
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                }
            }
        }
        else speed = 0;//If there are no possible directions, stop
        
        
        
        //Determine the direction the criminal is travelling
        if(getX() > startCoordinates[0]){
            direction = "right";
        }
        if(getX() < startCoordinates[0]) direction = "left";
        if(getY() > startCoordinates[1]){
            direction = "down";
        }
        if(getY() < startCoordinates[1]) direction = "up";
        
        //U-turn at edge
        if(isAtEdge()){
            if(direction.equals("up")) direction = "down";
            else if(direction.equals("down")) direction = "up";
            else if(direction.equals("right")) direction = "left";
            else if(direction.equals("left")) direction = "right";
            
        }
        
        //Direction change control to prevent random U turns
        if(!startDirection.equals(direction)){
            if(direction.equals("up")) setLocation(getX(), getY() -  myHeight/2);
            if(direction.equals("down")) setLocation(getX(), getY() +  myHeight/2);
            if(direction.equals("right")) setLocation(getX() +  myWidth, getY());
            if(direction.equals("left")) setLocation(getX() -  myWidth, getY());
        }
    }
    
    /**
     * @return If the person has arrived at the target location or not.
     */
    public boolean getIfArrived()
    {
        return ifArrived;
    }
    
    /**
     * Chnages the maximum speed
     * @param s The speed value to change to.
     */
    public void setSpeed(int s)
    {
        maxSpeed = s;        
    }
    
    /**
     * Gets the speed of the object
     * @return The value of speed at which the object is travelling at.
     */
    public double getSpeed()
    {
        return speed;
    } 
}
