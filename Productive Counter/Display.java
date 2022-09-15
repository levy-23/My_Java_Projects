import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NumDisplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Display extends Actor
{
    protected Color background;
    protected Color textColor;
    protected int num;
    protected String textNum;
    protected String textInstruc;
    protected int fontSize;
    protected boolean textDis;
    
    public Display(String text, int fontSize, Color textColor, Color background){
        this.textColor = textColor;
        this.background = background;
        this.fontSize = fontSize;
        textDis = false;
        setImage(new GreenfootImage(text, fontSize, textColor, background));
    }
    
    /**
     * Act - do whatever the NumDisplay wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        if(!textDis){
            setImage(new GreenfootImage(textNum, fontSize, textColor, background));

        }
    }  
    
    public void update(){
        num++;
        textNum = num + "";
    }
    
}
