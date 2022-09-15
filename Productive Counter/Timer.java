
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Timer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Timer extends Actor
{
    private int numTimeMin;
    private int numTimeSec;
    private int counter;
    private String textNumTime;
    private Color bck;
    private Color txt;
    private boolean done;
    private boolean started;
    public Timer(int min)
    {
        numTimeMin = min;
        numTimeSec = 0;
        counter = 1;
        done = false;
        started = false;
        String textNumTime = "0" + numTimeMin + ":0" + numTimeSec ;
        txt = new Color(0,0,0);
        bck = new Color(255, 255, 255);
        setImage(new GreenfootImage(textNumTime, 30, bck,txt));
    }
    /**
     * Act - do whatever the Timer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(started){
            counter--;
            if(counter == 0)
            {
                if(numTimeSec == 0 && numTimeMin ==0){
                    done = true;
                    started = false;
                }else{
                    if(numTimeSec == 0){
                        numTimeMin--;
                        numTimeSec = 59;
                    }else{
                        numTimeSec--;
                    }
                    if(numTimeSec < 10){
                        textNumTime = "0" + numTimeMin + ":0" + numTimeSec;
                    }else{
                        textNumTime = "0" + numTimeMin + ":" + numTimeSec;
                    }
                }
                setImage(new GreenfootImage(textNumTime, 30, bck,txt));
                counter = 60;   
            }
        }
        
    }   
    public boolean isDone(){
        return done;
    }
    public void setDone(boolean d){
        done = d;
    }
    public void start(){
        started = true;
    }
    public void setMin(int min){
        numTimeMin = min;
        textNumTime = "0" + numTimeMin + ":0" + numTimeSec;
        setImage(new GreenfootImage(textNumTime, 30, bck,txt));
    }
}
