import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * EnergyBall 
 * 
 * @author (Levent Eren) 
 * @version 17 June 2021
 */
public class EnergyBall extends Rocket
{
    private int slowDuration;
    /**
     * Energy ball constructor
     */
    public EnergyBall (Enemy e, Tower t, int damage, int explosionradius, int slowDuration){
        super(e, t, damage, explosionradius);
        image = new GreenfootImage("energy ball.png");
        image.scale(30, 30);
        setImage(image);
        speed = 2;
        this.slowDuration = slowDuration;
    }
    
    public void act() 
    {
        super.act();
    } 
    /**
     * Moves towards enemy and checks if hit enemy. If so it explodes and deals 
     * damage and slows enemies in explosion radius, and then removes energy ball and explosion.
     */
    public void moveTowards(){
        if(!exploded){
            
            
            move(speed);
            
            if(isTouching(Enemy.class)){
                move(explosionRadius);    
                
                inRange = (ArrayList) getObjectsInRange(explosionRadius +  25, Enemy.class);
                for(Enemy e : inRange){
                        e.hit(damage);
                        e.slow(slowDuration);
                        
                    }
                                          
                        //explode
                exploded = true;
                    
                image = new GreenfootImage("50x50 aoe blue.png");
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
