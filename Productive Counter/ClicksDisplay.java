
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ClicksDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClicksDisplay extends Display
{
    private int stage;
    public ClicksDisplay(){
        super("Clicks: 1", 17, new Color(255,0,255), new Color(0,0,0));
        num = 1;
        stage = 1;
        textNum = "Clicks: " + num;
    }
    /**
     * Act - do whatever the ClicksDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        super.act();
    }  
    public boolean clicksLeft(){
        if(num == 0){
            return false;
        }else{
            return true;
        }
    }
    public void update(){
        num--;
        textNum = "Clicks: " + num;
    }
    public void increase(){
        stage++;
        if(stage > 10){
            stage = 1;
            ((MyWorld)getWorld()).getRounds().update();
        }
        num = stage;
        textNum = "Clicks: " + num;
        ((MyWorld)getWorld()).isIncreased();
    }
}
