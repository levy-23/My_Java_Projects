import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Flying enemy is an enemy that flies over the path. This enemy will catch you by surprise.
 * It does not follow the original path so change your towers accordingly.
 * Additionally, do not underestimate its health either, it is a worthy opponant.
 * 
 * @author (Quinton Mak) 
 * @version 17 June 2021
 */
public class FlyingEnemy extends Enemy
{
    
    public void act() 
    {
        super.act();
    }    
    /**
     * Constructor for flying enemy
     */
    public FlyingEnemy(int multiplier){
        super(1, multiplier*500, 2);
        setImage("enemy flying.png");
        getImage().scale(70, 70);
        points = 30;
    }
    
    public void addedToWorld(World w){
        //override the one in the superclass
        getWorld().addObject(healthBar, getX(), getY());
        healthBar.update(currentHP);
        world = (MyWorld)w;
    }
    /**
     * Unique pathfinding. This enemy flies over the path in a straight line to the base.
     */
    public void pathFinding(){
        if(slowed){
            move(speed/2);
            slowedCounter--;
            if(slowedCounter <= 0){
                slowed = false;
                //slowedCounter = slowedRate;
            }
        }
        else{
            move(speed);
        }
    }
    /**
     * If dead, add more money than the default enemy.
     */
    public void hit(int damage){
        
        currentHP -= damage;
        if(currentHP <= 0){
            
            alive = false;
            money = world.getMoney();
            world.updateMoney(money+20);
            MyWorld.changeScore(points);
            getWorld().removeObject(this);
        }
    }
}
