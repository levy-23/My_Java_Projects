import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Boss enemy is the strongest and beefiest enemy. It arrived every once in a while.
 * However, when it does, legend says it could take on 50 turrets. 
 * 
 * @author (Quinton Mak) 
 * @version 17 June 2021
 */
public class BossEnemy extends Enemy
{
    private int counter;
    private boolean healing;
    /**
     * This enemy acts like a noraml enemy, however it also heals itself.
     */
    public void act() 
    {
        super.act();
        counter++;
        
        if(counter >= 300){
            counter = 0;
            speed = 0.5;
            healing = false;
        }
        else if(counter >= 250 && currentHP < maxHP){
            speed = 0;
            changeHp(10);
            healing = true;
        }
    } 
    /**
     * Constructor for boss enemy
     */
    public BossEnemy(){
        super(0.5, 8000, 5);
        armored = true;
        points = 500;
        healing = false;
        healthBar = new StatBar(maxHP, currentHP, this, 80, 4, 40);
        setImage("enemy armor boss.png");
    }
    /**
     * Constructor for boss enemy
     * 
     * @param multiplier the amount which the basee hp is multiplied by
     */
    public BossEnemy(int multiplier){
        super(0.5, 8000*multiplier, 5);
        armored = true;
        points = 500;
        healing = false;
        healthBar = new StatBar(maxHP, currentHP, this, 80, 4, 40);
        setImage("enemy armor boss.png");
    }
    /**
     * This enemy can only get hit when not healing
     * If dead, add a lot more money than the default enemy.
     */
    public void hit(int damage){
        
        if(!healing) currentHP -= damage;
        if(currentHP <= 0){
            
            alive = false;
            money = world.getMoney();
            world.updateMoney(money+200);
            MyWorld.changeScore(points);
            getWorld().removeObject(this);
        }
    }
}
