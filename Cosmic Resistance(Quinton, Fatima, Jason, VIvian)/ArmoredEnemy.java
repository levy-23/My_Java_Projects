import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Armored enemy is a strong, beefy enemy. Do not underestimate this type while
 * defending. 
 * 
 * @author (Quinton Mak) 
 * @version 17 June 2021
 */
public class ArmoredEnemy extends Enemy
{
    
    public void act() 
    {
        super.act();
    }    
    /**
     * Constructor for Armored enermy
     */
    public ArmoredEnemy(int multiplier){
        super(1, multiplier*400);
        setImage("enemy armor.png");
        points = 20;
        armored = true;
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
