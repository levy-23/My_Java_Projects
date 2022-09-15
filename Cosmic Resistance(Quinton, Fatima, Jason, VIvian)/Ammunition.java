import greenfoot.*;
import java.util.ArrayList;
/**
 * Ammunition is shot from towers to kill enemies. Ammunition deals damage to enemies,
 * and does an addition effect on impact for certain types. 
 * 
 * @author (Quinton Mak, Levent Eren, Fatima Yahya) 
 * @ 17 June 2021
 */
public class Ammunition extends Actor
{
    //protected int bulletDMG;
    protected int speed;
    protected Enemy myEnemy;
    protected Tower myTower;
    protected int damage;
    protected int targetX;
    protected int targetY;
    protected int towerX, towerY;
    protected int distance;
    protected int radius;
    protected GreenfootImage image;
    private boolean armorPierce;
    
    public Ammunition(Enemy e, Tower t)
    {
        this(e, t, 10);
    }
    
    public Ammunition(Enemy e, Tower t, int damage)
    {
        this(e, t, damage, false);
    }
    /**
     * Main tower consructor for ammunition
     */
    public Ammunition(Enemy e, Tower t, int damage, boolean armorPierce)
    {
        myEnemy = e;
        myTower = t;
        this.damage = damage;
        speed = 10;
        targetX = myEnemy.getX();
        targetY = myEnemy.getY();
        towerX = myTower.getX();
        towerY = myTower.getY();
        this.armorPierce = armorPierce;
        radius = myTower.getRadius();
        if(!armorPierce){
            setImage("normalProjectile.png");
            getImage().scale(15, 7);
        }
        else{
            setImage("ammo rocket small.png");
            getImage().scale(25, 9);
            
        }
        
    }

    public void addedToWorld(World w){
        turnTowards(targetX, targetY);
        distance = distanceBetween(getX(), getY(), towerX, towerY);
    }
    /**
     * Every act, ammunition checks if it should remove it self, or else it moves
     * towards the enemy. If outside radius, or outside the world, it should remove it self.
     */
    public void act() 
    {
        
       
        if (isAtEdge() == true)
        {getWorld().removeObject(this);}
        else if ( radius < distance) {
            getWorld().removeObject(this);    
        }
        else
        {
            distance = distanceBetween(getX(), getY(), towerX, towerY);
            moveTowards();
            
        }

    }
    /**
     * Ammunition moves towards enemy and detects hit along the way
     */
    protected void moveTowards()
    {
        if(myEnemy != null){  
            
            move(speed);
            
            detectHit();
        }
        else{
            getWorld().removeObject(this);
        }
    }
    
    /**
     * If ammunition hits an enemy, it will deal damage (double if piercing) and 
     * remove itself.
     */
    protected void detectHit()
    {
        
        if(isTouching(Enemy.class)){
                ArrayList<Enemy> attackers = (ArrayList) getIntersectingObjects(Enemy.class);
                Enemy attacker = attackers.get(0);
                if(armorPierce && attacker.isArmored()){
                    attacker.hit(damage*2);
                }
                else attacker.hit(damage);
                
                if(!attacker.isAlive()){
                    myEnemy = null;
                    
                }
                getWorld().removeObject(this);
            }
        else if (isAtEdge())
            {getWorld().removeObject(this);}
        else if ( radius < distance) {
            getWorld().removeObject(this);    
        }
    }
    /**
     * Remove if at edge
     */
    protected void detectEdge()
    {
        if (isAtEdge() == true)
        {getWorld().removeObject(this);}
    }

    /**
     * Calculates distance between two points
     * 
     * @returns the distance
     */
    public static int distanceBetween(int x1, int y1, int x2, int y2)
    {
        return (int) Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    }
}