import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Fire here.
 * 
 * @author (Fatima Yahya, Quinton Mak) 
 * @version (April 2021)
 */
public class Fire extends Actor
{
    // Instance Variables
    private Animator a;
    private String[] images;
    private FeatureBar timer;
    private boolean hasBeenPutOut;
    
    private boolean fireTruckComing;
    private int fireCounter;
    private WaterSpray water;
    public GreenfootImage image;
    /**
     * Constructor for Fire - sets images and initial values
     */
    public Fire()
    {
        images = new String[13];
        for(int i = 0; i < 13; i++){
            images[i] = "Fire" + i + ".png";
            getImage().scale(100, 100);
            getImage().setTransparency(40);
        }
        a = new Animator(this, images[0]);
        timer = new FeatureBar("", this, null, 5, false, false, true);
        hasBeenPutOut = false;
        fireTruckComing = false;
        water = new WaterSpray();
        image = new GreenfootImage("firess.png");
        setImage(image);
        getImage().setTransparency(120);
    }
    /**
     * Act - do whatever the Fire wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        // the images rotates to give the appearance of an animation.
        //a.animate(images, 6);
        if (image.getTransparency() < 240)
        {
            //getWorld().setPaintOrder(Fire.class, House.class);
            image.setTransparency (image.getTransparency() + 1);
            setImage(image);
        }
        else if (image.getTransparency() == 240)
        {
            image.setTransparency(120);
            setImage(image);
        }
        // if the timer is initialised and is finished then the fire has been put out
        if ((timer.getValue() / 60 ) == 0)
        {
            hasBeenPutOut = true;
        }
        // if the fire has been put out, it has been removed from the world
        if (hasBeenPutOut)
        {
            getWorld().removeObject(this);
        }
    }    
    
    /**
     * Sets variable to value of given parameter
     * @param firePutOut    Changes the value of if the fire has been put out or not
     */
    public void setHasBeenPutOut(boolean firePutOut)
    {
        hasBeenPutOut = firePutOut;
    }
    
    /**
     * Gets if the fire has been put out or not
     * @return true if the fire has been put out
     */
    public boolean getHasBeenPutOut()
    {
        return hasBeenPutOut;
    }
    
    /**
     * Set whether a firetruck is coming
     * @param fireTruckComing whether a firetruck is coming towards the house or not
     */
    public void setFireTruckComing(boolean fireTruckComing){
        this.fireTruckComing = fireTruckComing;
    }
    
    /**
     * Returns whether a firetruck is approaching this house
     * @return true if a firetruck is coming, false if not
     */
    public boolean getFireTruckComing(){
        return fireTruckComing;
    }
    
    /**
     * If the fire needs to be put out, a timer is initialised and a waterspray animation is also added
     */
    public void putOutFire()
    {
        getWorld().addObject (timer, getX(), (getY() - timer.getOffset()));
        getWorld().addObject(water, getX(), getY());
        //GreenfootImage image = new GreenfootImage("firess.png");
        //getImage().setTransparency(120);
        // can do smth like if timer.getValue() == 0, then remove water from world too lets see
    }
    
}
