import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Person here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Person extends Pedestrian
{
    
    
    
    public Person(){
        GreenfootImage g = this.getImage();
        myWidth = g.getWidth();
        
        startSpeed = 2;
        
        // Set current healthy status to true (alive and moving)
        healthy = true;
        // Set initial speed
        speed = startSpeed;
        
        upright = 0;
        
        
    }
    
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        Wave w = (Wave) getOneIntersectingObject(Wave.class);
        if(w != null){
            setLocation(getX(), getY() + 2);
        }
        else{
            
            if( getY() == 76){
                setLocation(getX() - speed, getY());
            }
            else {
                setLocation (getX(), getY() - speed);
            }
            
            // check if I'm at the edge of the world,
            // and if so, remove myself
        }
        if ( getX() == 34)
        {
            getWorld().removeObject(this);
            
        }
        else if(isAtEdge()){
            getWorld().removeObject(this);
           
        }
    }    
}
