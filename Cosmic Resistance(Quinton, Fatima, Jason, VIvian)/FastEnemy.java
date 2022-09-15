import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fast enemy is the weakest but fastest enemy. Easy to rush through the lines so
 * be careful defending.
 * 
 * @author (Quinton Mak) 
 * @version 17 June 2021
 */
public class FastEnemy extends Enemy
{
    
    public void act() 
    {
        // Add your action code here.
        super.act();
    }    
    /**
     * Constructor for fast enemy.
     */
    public FastEnemy(int multiplier){
        super(3, multiplier*65);
        setImage("enemy small.png");
        points = 15;
    }
    
    
}
