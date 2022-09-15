import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Officers here.
 * 
 * @author (Fatima Yahya) 
 * @version (a version number or a date)
 */
public abstract class Officers extends People
{
    // Instance Variables
    private int health;
    protected boolean ifDead;
    protected StatBar healthBar;
    /**
     * Constructor for Officers - calls the People constructor and sets initial values
     * @param maxSpeed The maximum speed at which the object should travel at.
     */
    public Officers(double maxSpeed)
    {
        super(maxSpeed);
        health = 3;
        healthBar = new StatBar(health, this);
    }
    
    public void addedToWorld(World w)
    {
        super.addedToWorld(w);
        w.addObject (healthBar, getX(), getY());
        healthBar.update(health);
    }
    
    /**
     * Act - do whatever the Officers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if (health == 0)
        {
            getWorld().removeObject(healthBar);
        }
    }    
    
    /**
     * Knocks over the Officer - sets rotation to 90, to mimic a dead officer.
     */
    public void knockMeOver()
    {
        setRotation (90);
    }
    
    /**
     * Decreases the health of the officer by one - maximum health is 3
     */
    public void decreaseHealth()
    {
        health--;
        healthBar.update(health);
    }
    
    /**
     * Gets the current health of the officer - maximum is 3
     * @return  The health of the officer
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Gets if the officer is dead or not depending on the rotation of the officer
     * @return true if officer is dead
     */
    public boolean getIfDead()
    {
        if (getRotation() == 90)
        {
            ifDead = true;
        }
        else
        {
            ifDead = false;
        }
        return ifDead;
    }
    
    /**
     * Makes the person walk towards a certain point. It stops when near that point
     * 
     * @param x The x-coordinate of the point to walk to (between 0 and world's width)
     * @param y The y-coordinate of the point to walk to (between 0 and world's height)
     */
    public void walkTo(int x, int y){
        
        if(smartDrive = false){//Assuming starts off vertical
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
            
            // If close enough to the point, don't move anymore
            if(GameWorld.distanceBetween(getX(), getY(), x, y) < 0.5){
                speed = 0;
                ifArrived = true;
            }
            else ifArrived = false;
            
            //Determine direction of movement, prioritizing in the order: Direction towards the point, straight, turning away from the point
            
            if(direction.equals("right")){
                if(getY() > y){
                    //Ex. If the point is far above the criminal's location, and the criminal is facing to the right
                    if(canMoveUp) setLocation(getX(), getY() - speed);//First, try to face upward toward the point
                    else if(canMoveRight) setLocation(getX() +  speed, getY());//Then try to go straight
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);//If above 2 are not possible, try to go down
                }
                else if(getY() < y) {
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
                if(getX() < x){
                    if(canMoveRight)  setLocation(getX() +  speed, getY());
                    else if(canMoveUp) setLocation(getX(), getY() - speed);
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                }
                else if(getX() > x) {
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
                if(getY() > y){
                    
                    if(canMoveUp)setLocation(getX(), getY() - speed);
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);
                }
                else if(getY() < y) {
                    
                    if(canMoveDown) setLocation(getX(), getY() +  speed);
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                    else if(canMoveUp)setLocation(getX(), getY() - speed);
                }
                else{
                    
                    if(canMoveLeft) setLocation(getX() - speed, getY());
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);
                    else if(canMoveUp)setLocation(getX(), getY() - speed);
                }
            }
            else if(direction.equals("down")){
                if(getX() < x){
                    if(canMoveRight)  setLocation(getX() +  speed, getY());
                    else if(canMoveDown) setLocation(getX(), getY() +  speed);
                    else if(canMoveLeft) setLocation(getX() - speed, getY());
                }
                else if(getX() > x) {
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
    
}
