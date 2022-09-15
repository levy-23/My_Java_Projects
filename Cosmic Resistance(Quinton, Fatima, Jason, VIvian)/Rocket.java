import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * AOE projectile - explodes upon impact dealing damage to surounding enemies.
 * 
 * @author (Quinton Mak, Fatima Yahya) 
 * @version 17 June 2021
 */
public class Rocket extends Ammunition
{
    protected ArrayList<Enemy> inRange;
    protected int explosionRadius;
    protected boolean exploded;
    public void act() 
    {
        super.act();
    }    
    /**
     * Rocket constructor
     */
    public Rocket(Enemy e, Tower t, int damage, int explodeRadius){
        super(e, t, damage);
        explosionRadius = explodeRadius;
        image = new GreenfootImage("ammo rocket large.png");
        image.scale(40, 10);
        setImage(image);
        
        exploded = false;
    }
    /**
     * Moves towards enemy and checks if hit enemy. If so it explodes and deals 
     * damage to enemies in explosion radius, and then removes rocket and explosion.
     */
    public void moveTowards(){
        
        
        
        if(!exploded){
            
            
            move(speed);
            
            if(isTouching(Enemy.class)){
                move(explosionRadius);    
                
                inRange = (ArrayList) getObjectsInRange(explosionRadius +  25, Enemy.class);
                for(Enemy e : inRange){
                        e.hit(damage);
                    }
                                          
                        //explode
                exploded = true;
                    
                image = new GreenfootImage("50x50 aoe.png");
                image.scale(explosionRadius*2, explosionRadius*2);
                setImage(image);
                        //getWorld().removeObject(this);
                }
            else if (isAtEdge())
                {
                    getWorld().removeObject(this);
                }
            else if ( radius < distance) {
                getWorld().removeObject(this);    
            }
        }
        else{
            setImage(image);
            image.setTransparency(image.getTransparency() - 5);
            if(image.getTransparency() <= 0) getWorld().removeObject(this);
        }
    }
    
}
