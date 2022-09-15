import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RoundsDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoundsDisplay extends Display
{
    public RoundsDisplay()
    {
        super("Rounds: 0", 22, new Color(255,0,255), new Color(0,0,0));
        num = 0;
        textNum = "Rounds: " + num;
    }
    /**
     * Act - do whatever the RoundsDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        super.act();
    } 
    public void update(){
        num++;
        textNum = "Rounds: " + num;
    }
    
}
