import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Houses fill most of the game map. They can be constructed, burnt or robbed. 
 * Citizens fill up the town building houses to on this amazing land. However, 
 * unfortunately many arsonists and robbers like to cause havoc on the citizens of
 * this town. These criminals evny the prosperity of these citizens, some try to steal
 * some wealth for them selves, and others just seek to destroy the houses. Luckily
 * for the rescue team, burnt houses can be re built, and robberies can be stopped 
 * before they do much harm.
 * 
 * @author (Fatima Yahya, Quinton Mak, Levent Eren) 
 * @version (April 2021)
 */
public class House extends Buildings
{
    // Instance Variables
    private Animator a;
    private String[] images;
    private boolean animated;
    
    private Animator a2;
    private String[] images2;
    private boolean animated2;
    
    private boolean onFire;
    private Fire fire;
    private boolean underConstruction;
    private boolean burntBeyondRepair;
    
    private boolean robbed;
    private boolean policeCarComing;
    private Alarm alarm;
    /**
     * Basic Constructor - sets the three different images and sets initial values
     */
    public House()
    {
        images = new String[3];
        for(int i = 0; i < 3; i++){
            images[i] = "House" + (i+1) + ".png";
            //getImage().scale(100, 75);
        }
        animated = true;
        a = new Animator(this, images[0]);
        
        images2 = new String[3];
        for(int i = 0; i < 3; i++)
        {
            images2[i] = "House" + (i + 1) + ".png";
            //getImage().scale(100, 75);
        }
        animated2 = true;
        a2 = new Animator(this, images2[0]);
        
        onFire = false;
        fire = new Fire();
        underConstruction = true;
        burntBeyondRepair = false;
        robbed = false;
        alarm = new Alarm();
    }
    /**
     * Act - do whatever the House wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        // If it needs to be animated then animate the images - images change every 120 acts.
        if (animated)
        {
            a.animate(images, 120);
        }
        // if it has reached the third image then it should not animate any more
        if (a.getIndexImages() == images.length)
        {
            animated = false;
            underConstruction = false;
        }
        // sets the house on fire
        setHouseOnFire();
        // if there fire has been put out then it is not on fire anymore
        if (fire.getHasBeenPutOut())
        {
            reBuild();
            underConstruction = true;
            onFire = false;
        }
        
        
    }    
    
    /**
     * Re Builds house
     */
    public void reBuild()
    {
        if (animated2)
        {
            a2.animate(images2, 200);
        }
        // if it has reached the third image then it should not animate any more
        if (a2.getIndexImages() == images2.length)
        {
            animated2 = false;
            underConstruction = false;
        }
    }
    
    /**
     * If the house is on fire, adds the object Fire
     */
    public void setHouseOnFire()
    {
        if (onFire)
        {
            
            getWorld().addObject(fire, getX(), getY());
            //((GameWorld)getWorld()).numFires += 1;
            //getWorld().setPaintOrder(House.class, Fire.class);
        }
        
    }
    
    
    /**
     * Gets if the house is on fire
     * 
     * @return true if the house is on fire, false if not
     */
    public boolean isOnFire(){
        return onFire;
    }
    
    /**
     * Set whether the building is on fire or not
     * 
     * @param onFire true to set the house is on fire, false to put out a fire
     */
    public void setOnFire(boolean onFire){
        this.onFire = onFire;
        if (onFire)
        {
            getWorld().addObject(fire, getX(), getY());
            //getWorld().setPaintOrder(House.class, Fire.class);
        }
    }
    
    /**
     * Gets if the house is under construction
     * 
     * @return true if the house is under construction, false if not
     */
    public boolean getIfUnderConstruction()
    {
        return underConstruction;
    }
    
    /**
     * Gets if the house is burnt beyond repair
     * 
     * @return true if the house is burnt beyond repair, false if not
     */
    public boolean getBurntBeyondRepair()
    {
        return burntBeyondRepair;
    }
    
    /**
     * Gets if the house is on fire 
     * 
     * @return true if the house is on fire, false if not
     */
    public boolean isRobbed()
    {
        return robbed;
    }
    
    /**
     * Sets robbed, whether the house is robbed or not
     * 
     * @param robbed If house is robbed or not
     */
    public void setRobbed(boolean robbed)
    {
       this.robbed = robbed; 
       if (robbed)
        {
            getWorld().addObject(alarm, getX(), getY());
        }
    }
    
    /**
     * Sets policeCarComing, whether a police car is coming to the house or not
     * 
     * @param robbed If a police car is coming to the house or not
     */
    public void setPoliceCarComing(boolean policeCarComing)
    {
        this.policeCarComing = policeCarComing;
    }
    
    /**
     * Gets if a police car is coming to the house
     * 
     * @return true if a police car is coming to the house, false if not
     */
    public boolean getPoliceCarComing()
    {
        return policeCarComing;
    }
    

}
