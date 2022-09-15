import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Actor
{
    private int s = 59;
    private int m = 2;
    private int c = 30;
    private String t;
    public Counter (){
        setImage(new GreenfootImage("2:59", 30, new Color(0,0,0), new Color(255,255,255)));
        
    }
    /**
     * Act - do whatever the counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        seconds();
    }  
    public void seconds(){
        c--;
        if(c==0){
            s--;
            if(s==0){
                m--;
                s=59;
            }
            if(s<10){
                t = m + ":0" + s;
            }else{
                t = m + ":" + s;
            }
            setImage(new GreenfootImage(t, 30, new Color(0,0,0), new Color(255,255,255)));
            c=30;
            
        }
    }
}
