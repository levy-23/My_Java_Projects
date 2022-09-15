import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Turret three is a piercing turret dealing more damage and piercing enemies.
 * 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TowerThree extends Tower
{
    private int chance, random;
    private GreenfootSound s; 
    /**
     * Constructor for the piercing turret. The special skill when maxed is a chance to
     * deal double damage, however decreasing shotspeed.
     */
    public TowerThree()
    {
        super(60, 90, "turretRocketMini.png", 200, 100);
        radius = 150;
        chance = 0;
        maxSkill = "Upgrade - Lucky Strike: ";
        s = new GreenfootSound ("Shooting2.wav"); 
    }
  
    public void act() 
    {
        super.act();
    }    
    /**
     * Unique shoot method. Shoots piercing ammunition instead  of regular ammunition. 
     * Plus includes chance if tower is maxed out.
     */
    public void shoot()
    {
        
        if(targetE != null){
            counter++;
            
            if(counter > shootSpeed){
                random = Greenfoot.getRandomNumber(10);
                if(random < chance) getWorld().addObject(new Ammunition(targetE, this, 2*damage, true), getX(), getY());
                else getWorld().addObject(new Ammunition(targetE, this, damage, true), getX(), getY());
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
        damage += 10;
        super.upgrade();
        if(level == 4){
            shootSpeed -= 30;
            chance = 2;
            //max level skill
        }
    }
}
