import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Turret four is an electric turret. This turret shoots energy balls which slow 
 * down enemies.
 * 
 * @author (Levent Eren) 
 * @version 17 June 2021
 */
public class TowerFour extends Tower
{
    private int explodeRadius;
    private int slowDuration;
    
    private GreenfootSound s; 
    /**
     * Constructor for the area damage turret. The special skill when maxed is an increased
     * slow duration for enemies when hit.
     */
    public TowerFour()
    {
        super(15, 90, "turret electric.png", 200, 100);
        explodeRadius = 40;
        
        radius = 100;
        slowDuration = 3*60;
        maxSkill = "Upgrade - Systems Down: ";
        
        s = new GreenfootSound ("Shooting2.wav"); 
    }
  
    public void act() 
    {
        super.act();
    }     
    /**
     * Unique shoot method. Shoots energy balls instead  of regular ammunition
     */
    public void shoot()
    {
        
        if(targetE != null){
            counter++;
            

            if(counter > shootSpeed){
                getWorld().addObject(new EnergyBall(targetE, this, damage, explodeRadius, slowDuration), getX(), getY());
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
            slowDuration += 60;
        }
    }
}
