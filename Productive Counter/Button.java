import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    private GreenfootImage img;
    private Color available;
    private Color unavailable;
    private boolean btnAvailable;
    public Button(){
        img = new GreenfootImage(70,40);
        setImage(img);
        available = new Color(0,0,255);
        unavailable = new Color(125,125,125);
        img.setColor(available);
        img.fill();
        btnAvailable = true;
        
        
    }
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }
    public void unavailable(){
        img.setColor(unavailable);
        img.fill();
        btnAvailable = false;
    }
    public void available(){
        img.setColor(available);
        img.fill();
        btnAvailable = true;
    }
    public boolean getAvailable(){
        return btnAvailable;
    }
}
