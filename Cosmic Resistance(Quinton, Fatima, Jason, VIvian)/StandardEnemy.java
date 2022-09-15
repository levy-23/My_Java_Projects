import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Standard enemy is the easiest, simpliest enemy. A simple grunt. 
 * 
 * @author (Quinton Mak) 
 * @version 17 June 2021
 */
public class StandardEnemy extends Enemy
{
    
    public void act() 
    {
        // Add your action code here.
        super.act();
    }    
    /**
     * Constructor for standard enemy
     */
    public StandardEnemy(int multiplier){
        super(1, multiplier*100);
        setImage("enemy normal.png");
        getImage().scale(40, 40);
        points = 10;
    }
}
