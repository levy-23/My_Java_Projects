import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Towers are placed to defend against enemies. They shoot ammunition that damage the enemies. 
 * 
 * @author (Quinton Mak, Levent Eren, Fatima Yahya) 
 * @ 17 June 2021
 */
public class Tower extends Actor  
{
    // instance variables
    protected int counter;
    //tower stats
    protected int shootSpeed;
    protected int damage;
    protected int cost;
    protected int upgradeCost;
    private int sellAmount;
    protected int radius;
    protected int level;
    protected String maxSkill;
    
    private GreenfootImage towerImage;
    private Circle radiusCircle;
    
    protected Enemy targetE;
    protected ArrayList<Enemy> enemies;
    
    private boolean selected;
    
    private ValueBar towerInfoOne;
    private ValueBar towerInfoTwo;
    private ValueBar towerInfoThree;
    //private ValueBar levelInfo;
    
    private GreenfootSound bgm; 
    
    private GreenfootSound s; 
    
    
    public Tower(int damage, int cost)
    {
        this(damage, 60, "turret.png", cost, 25);
        bgm = new GreenfootSound ("HitImpact.wav"); 
        
        s = new GreenfootSound ("Shooting2.wav"); 
    }
    
    public Tower(int damage, String imageName, int cost, int upgradeCost)
    {
        this(damage, 60, imageName, cost, upgradeCost);
        bgm = new GreenfootSound ("HitImpact.wav"); 
        
        s = new GreenfootSound ("Shooting2.wav"); 
    }
    /**
     * Main tower consructor for towers 
     */
    public Tower(int damage, int shootSpeed, String imageName, int cost, int upgradeCost)
    {
        this.cost = cost;
        sellAmount = (int) (cost*0.8);
        this.upgradeCost = upgradeCost;
        this.damage = damage;
        this.shootSpeed = shootSpeed;
        counter = shootSpeed;
        radius = 120;
        
        level = 1;
        
        targetE = null;
        selected = true;
        
        towerImage = new GreenfootImage(imageName);
        setImage(towerImage);
        
        towerInfoOne = new ValueBar("Damage: ", damage);
        towerInfoTwo = new ValueBar("Sell For: ", sellAmount);
        towerInfoThree = new ValueBar("Upgrade Cost: ", upgradeCost);
        //levelInfo = new ValueBar("Level ", level);
        radiusCircle = new Circle(radius);
        
        bgm = new GreenfootSound ("HitImpact.wav"); 
        
        s = new GreenfootSound ("Shooting2.wav"); 
    }
    
    public void addedToWorld(World w){
        getWorld().addObject(radiusCircle, getX(), getY());
        bgm.setVolume(50); 
        bgm.play(); 
    }
    /**
     * Every act Towers do 3 things. They detect if they are selected, in order to display their info.
     * They locate an enemy and shoot at it.
     * 
     */
    public void act()
    {
        detectSelect();
        findE();
        shoot();
    }
    /**
     * Check if this tower is selected, and if so, display the towers info. 
     * They display damage, price to sell, and upgrade cost.
     */
    public void detectSelect(){
        
        
        if(selected){
            //display tower info
            getWorld().addObject(towerInfoOne, 800, 515);
            getWorld().addObject(towerInfoTwo, 800, 550);
            getWorld().addObject(towerInfoThree, 800, 585);
            
            //Display tower range
            radiusCircle.updateRadius(radius);
            getWorld().addObject(radiusCircle, getX(), getY());
            
        }
        
        
        
        if(Greenfoot.mousePressed(this)){
            if(selected){
                selected = false;
            }
            else{
                ((MyWorld) getWorld()).deselectTowers();
                ((MyWorld) getWorld()).deselectButtons();
                selected = true;
            }
            
        }
        
        if (!selected)
        {
            getWorld().removeObject(towerInfoOne);
            getWorld().removeObject(towerInfoTwo);
            getWorld().removeObject(towerInfoThree);
            //getWorld().removeObject(levelInfo);
            getWorld().removeObject(radiusCircle);
        }
        
    }
    /**
     * Get method for if tower is selected
     * 
     * @return selected. True if this tower is clicked on/selected and false if not.
     */
    public boolean isSelected(){
        return selected;
    }
    /**
     * Mutator method for if tower is selected
     * 
     * @param select, whether the tower is selected or not.
     */
    public void setSelect(boolean select){
        selected = select;
    }
    /**
     * Shoot the enemy by adding ammunition to the world.
     */
    public void shoot()
    {
        
        if(targetE != null){
            counter++;
            
            
            if(counter > shootSpeed){
                getWorld().addObject(new Ammunition(targetE, this, damage), getX(), getY());
                counter=0;
                s.play(); 
            }
        }
        
    }
    /**
     * Method for locating the target enemy
     */
    protected void findE()
    {
        enemies = (ArrayList)getObjectsInRange(radius, Enemy.class);
        
        
        
        if(enemies.size() > 0){
            if(targetE != null){
                if(!targetE.isAlive() || !enemies.contains(targetE)){
                    targetE = enemies.get(0);//If the current target is dead, or if the current target is not in range anymore, switch targets
                }
                else{
                    
                }
            }
            else targetE = enemies.get(0);//if target is null, selsect target
    
        }
        else{
            targetE= null;
            counter = shootSpeed;
        }
        if(targetE != null){
            if(targetE.isAlive()){
                turnTowards(targetE.getX(), targetE.getY());//turn towards the current target
            }
        }
    }
    /**
     * Get method for radius
     */
    public int getRadius(){
        return radius;
    }
    /**
     * Upgrade method for towers. Increases stats.
     * Increases radius, sell amount, and upgrade cost. Updates damage as well.
     * Displays max level if tower cannot be upgraded further.
     */
    public void upgrade()
    {
        
        radius = radius + 15;
        radiusCircle.updateRadius(radius);
        //update damage
        towerInfoOne.update(damage);
        //increase sell amount
        sellAmount += upgradeCost/2;
        towerInfoTwo.update(sellAmount);
        //increase next upgrade cost;
        upgradeCost += 20;
        towerInfoThree.update(upgradeCost);
        //Level up
        level++;
        if(level == 3) {
            
            towerInfoThree.update(maxSkill);
        }
        else if(level == 4) {
            towerInfoThree.update(0);
            towerInfoThree.update("MAX LEVEL");
        }
        
    }
    /**
     * Get method for the tower's cost of upgrade
     * 
     * @return upgradeCost, the cost to upgrade the tower's level.
     */
    public int getUpgradeCost(){
        return upgradeCost;
    }
    /**
     * Get method for tower's level
     * 
     * @return level, the level the tower is at.
     */
    public int getLevel(){
        return level;
    }
    /**
     * Sell method for towers. Removes Tower and info and increases money.
     */
    public void sell(){
        ((MyWorld) getWorld()).changeMoney(sellAmount);
        getWorld().removeObject(towerInfoOne);
        getWorld().removeObject(towerInfoTwo);
        getWorld().removeObject(towerInfoThree);
        getWorld().removeObject(radiusCircle);
        
        getWorld().removeObject(this);
    }
}
