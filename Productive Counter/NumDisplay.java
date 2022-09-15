import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NumDIsplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NumDisplay extends Display
{
    public NumDisplay(){
        super("0", 50, new Color(255,0,255), new Color(0,0,0));
        num = 0;
        textNum = num + "";
        
    }
    /**
     * Act - do whatever the NumDIsplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
    }    
    public int getScore(){
        return num;
    }
    public void setScore(int s){
        num = s;
    }
}
