import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Turret two is an area damage turret. This turret shoots a rocket which explodes 
 * on impact dealing damage to enemies in explotion radius.
 * 
 * @author (Quinton) 
 * @version 17 June 2021
 */
public class TowerTwo extends Tower
{
    private int explodeRadius;
    private GreenfootSound s; 
    /**
     * Constructor for the area damage turret. The special skill when maxed is an increased
     * area for area damage.
     */
    public TowerTwo()
    {
        super(30, "turretRocketPod.png", 100, 50);
        explodeRadius = 25;
        maxSkill = "Upgrade - Enhanced Blast: ";
        s = new GreenfootSound ("Shooting2.wav"); 
    }
  
    public void act() 
    {
        super.act();
    }     
    /**
     * Unique shoot method. Shoots rockets instead  of regular ammunition
     */
    public void shoot()
    {
        
        if(targetE != null){
            counter++;
            
            if(counter > shootSpeed){
                getWorld().addObject(new Rocket(targetE, this, damage, explodeRadius), getX(), getY());
                counter=0;
                s.play(); 
            }
        }
        
    }
    /**
     * Upgrades tower.
     * Update's the tower's damage increase, since each tower has a unique damage increase.
     * Upgrades to tower's unique max level ability
     */
    public void upgrade(){
        damage += 6;
        super.upgrade();
        if(level == 4){
            explodeRadius += 15;
        }
    }
}
