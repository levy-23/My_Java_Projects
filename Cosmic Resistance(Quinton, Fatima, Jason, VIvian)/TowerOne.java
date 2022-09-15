import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tower one is the simple turret. It shoots consistent low damage shots and is cheap.
 * 
 * 
 * @author (Quinton Mak) 
 * @version 17 June 2021
 */
public class TowerOne extends Tower
{
    private GreenfootSound s; 
    /**
     * Constructor for the primary turret. The special skill when maxed out is
     * rapid fire
     * 
     */
    public TowerOne(){
        super(12, 60, "turret.png", 50, 30);
        maxSkill = "Upgrade - Rapid Fire: ";
        //setImage("turret.png");
        
        s = new GreenfootSound ("Shooting2.wav"); 
    }
  
    public void act() 
    {
        super.act();
    }    
    
    /**
     * Upgrades tower.
     * Update's the tower's damage increase, since each tower has a unique damage increase.
     * Upgrades to tower's unique max level ability
     */
    public void upgrade(){
        
        // increase damage 
        damage += 3;
        super.upgrade();
        if(level == 4){
            shootSpeed = 20;
        }
        
        
    }
}
