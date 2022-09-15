import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Box here.
 * 
 * @author Quinton Mak
 * @version Jan 2020
 */
public class Box extends Actor
{
    protected GreenfootImage image;
    private boolean dissapearing;
    private int transparency;
    private int speed;
    private int room;
    public void act() 
    {
        setImage(image);
        if(dissapearing) dissapear();
        if(image.getTransparency() < 130) getWorld().removeObject(this); //Removes the box after transparency reachs a certain minimum value
        
    } 
    
    public Box(int width, int height, Color color)//This box is a rectangular object that stays on the screen
    {
        dissapearing = false;
        image = new GreenfootImage(width, height);
        image.setColor(color);
        image.fill();
        this.setImage(image);
    }
    /*
    public Box(int width, int height, Color color, int room)
    {
        dissapearing = false;
        image = new GreenfootImage(width, height);
        image.setColor(color);
        image.fill();
        this.setImage(image);
        this.room = room;
        boundToRoom = true;
    }
    **/
    public Box(int width, int height, Color color, int speed, int transparency)//This box executes a "fade out" effect as the transparency will decrease
    
    {
        dissapearing = true;//If this is true the box will "disappear"
        this.speed = speed;
        this.transparency = transparency;
        image = new GreenfootImage(width, height);
        image.setColor(color);
        image.fill();
        this.setImage(image);
    }
    
    public void dissapear()//Transparency decreases at the rate speed/act
    {
        if(transparency > 255) image.setTransparency(255);
        else image.setTransparency(transparency);
        transparency -= speed;
        if(transparency < 0) transparency = 0;
        
    }
}
