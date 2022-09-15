import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Enemies try to reach the cosmic secrets. They have to follow the path to the 
 * base at the end with the secrets. They spawn if varius different health, speed and path.
 * 
 * @author (Quinton Mak) 
 * @version 17 June 2021
 */
public class Enemy extends SmoothMover
{
    protected StatBar healthBar;
    
    private PathPiece current, next;
    private int[] coords;
    
    protected double speed;
    protected int maxHP, currentHP;
    protected int baseDamage;
    protected int points;
    protected boolean alive;
    protected boolean armored;
    protected int money;
    protected boolean slowed;
    protected int slowedCounter;
    protected int slowedRate;
    protected GreenfootImage image;
    protected MyWorld world; 
    /**
     * Main Enemy constructor
     */
    public Enemy(double speed, int hp)
    {
        this.speed = speed;
        maxHP = hp;
        currentHP = maxHP;
        baseDamage = 1;
        image = getImage();
        
        healthBar = new StatBar(maxHP, this);
        alive = true;
        slowed = false;
        slowedRate = 4*60;
        slowedCounter = slowedRate;
    }
    /**
     * Constructor for heavier enemies
     */
    public Enemy(double speed, int hp, int baseDamage)
    {
        this.speed = speed;
        maxHP = hp;
        currentHP = maxHP;
        this.baseDamage = baseDamage;
        image = getImage();
        
        healthBar = new StatBar(maxHP, this);
        alive = true;
        
        armored = false;
        slowed = false;
    }
    
    public void addedToWorld(World w){
        int[] coords = GridConverter.worldToGrid(getX(), getY()); //get location in the grid
        current = (PathPiece) ((MyWorld) getWorld()).getGrid()[coords[0]][coords[1]]; //get the square in said location 
        next = current.getNextPath(); //get the next square
        getWorld().addObject(healthBar, getX(), getY());
        healthBar.update(currentHP);
        world = (MyWorld)w;
    }
    /**
     * Finds path, updates health regularily. If at base, deal damage and remove itself.
     */
    public void act() 
    {
        pathFinding();
        
        healthBar.update(currentHP);
        
        if(isAtEdge() || currentHP <= 0){
            //damage base 
            if(isAtEdge()) world.decreaseLives(-baseDamage);
            Base.change(); 
            getWorld().removeObject(this);
        }
    }
    /**
     * Method for when enemy gets hit by ammunition. Decreases its current hp by
     * the damage taken. If dead then add money, change score and remove object.
     * 
     * @param damage. The damage taken from hit.
     */
    public void hit(int damage){
        
        currentHP -= damage;
        if(currentHP <= 0){
            
            alive = false;
            money = world.getMoney();
            world.updateMoney(money+10);
            MyWorld.changeScore(points);
            getWorld().removeObject(this);
        }
    }
    /**
     * Method for when enemy is slowed by electric ball. Updates enemy status to slowed, 
     * and starts counter for the durration of the energy ball's effect.
     * 
     * @param slowDuration. Time the enemy is slowed for.
     */
    public void slow(int slowDuration){
        slowed = true;
        slowedCounter = slowDuration;
        
    }
    /**
     * Get method for if enemy is alive
     * 
     * @return alive. If enemy is alive or not.
     */
    public boolean isAlive(){
        return alive;
    }
    /**
     * Finds path for enemies.
     * (work as long as the speed is below 10)
     */
    public void pathFinding(){
        coords = GridConverter.worldToGrid(getX(), getY());
        
        if(next != null){
            
            double theta = Math.toRadians(getRotation() - 180);
            int x = getX() + (int) (25*Math.cos(theta));
            int y = getY() + (int) (25*Math.sin(theta));
            //If 25 pixels behind the enemy is inside the next square, enemy is enough into the next square to turn
            if(GridConverter.worldToSquareWorld(x, y)[0] == next.getX() && GridConverter.worldToSquareWorld(x, y)[1] == next.getY()){
                current = (PathPiece) ((MyWorld) getWorld()).getGrid()[coords[0]][coords[1]];
                next = current.getNextPath();
                if(next != null){
                    turnTowards(next.getX(), next.getY());
                    setRotation((int)( Math.round(((double) (getRotation()))/90.0) * 90));
                }
            }
        }
        
        
        if(slowed){
            move(speed/2);
            slowedCounter--;
            if(slowedCounter <= 0){
                slowed = false;
                slowedCounter = slowedRate;
            }
        }
        else{
            move(speed);
        }
        
    }
    /**
     * Changes enemy's hp
     * 
     * @param change. The amount the hp needs to be changed by
     */
    public void changeHp(int change){
        currentHP += change;
        if(currentHP > maxHP) currentHP = maxHP;
        
    }
    
    /**
     * Get enemy's hp
     * 
     * @return currentHP. Enemy's health points.
     */
    public int getHP()
    {
        return currentHP;
    }
    /**
     * Get if enemy is armored
     * 
     * @return armored. If Enemy is armored.
     */
    public boolean isArmored(){
        return armored;
    }
}
