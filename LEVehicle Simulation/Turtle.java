import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Turtle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Turtle extends Pedestrian
{
    private int startSpeed = 1;
    private SuperAura aura;
    private boolean isSuperTurtle;
    private int counterSuper;
    
    
    public Turtle(){
        GreenfootImage g = this.getImage();
        myWidth = g.getWidth();
        
        // Set current healthy status to true (alive and moving)
        healthy = true;
        // Set initial speed
        speed = startSpeed;
        
        upright = 270;
        
        setRotation(270);
        
        aura = new SuperAura();
        
        isSuperTurtle = false;
        
        counterSuper = 0;
        
    }
    
    /**
     * Act - do whatever the Turtle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(isSuperTurtle == true){
            
            if(counterSuper > 0){
                aura.setLocation(getX(), getY());
            }
            else{
                isSuperTurtle = false;
                ArrayList<SuperAura> auras = (ArrayList<SuperAura>)getWorld().getObjects(SuperAura.class);
                for(SuperAura i : auras){
                    getWorld().removeObject(i);
                }
            }
            counterSuper--;
        }
        
        if(getY() == 75 || getY() == 76){
            setRotation(0);
            setLocation(getX() + speed, getY());
        }
        else{
            setLocation(getX(), getY() - speed);
        }
        
        // move upwards
        
        //setLocation (getX(), getY() - speed);
        Wave w = (Wave) getOneIntersectingObject(Wave.class);
        if(w != null && speed == 0){
            setLocation(getX(), getY() + 2);
        }
        
        // check if I'm at the edge of the world,
        // and if so, remove myself
        if (getX() == 560)
        {
            getWorld().removeObject(this);
        }
        else if(isAtEdge()){
            getWorld().removeObject(this);
        }
        
        
    }
    
    public int valueX()
    {
        return getX();
    }
    public int valueY()
    {
        return getY();
    }
    public void superTurtle(){
        isSuperTurtle = true; 
        getWorld().addObject(aura, getX(), getY());
        counterSuper = 60* 3;
    }
    public void knockMeOver ()
    {
         if( isSuperTurtle == false){
             speed = 0;
             setRotation (upright + 90);
             getWorld().setPaintOrder(Vehicle.class, SuperAura.class, Pedestrian.class, ScoreBar.class, Wave.class);
       
         }
         
         
    }
}
