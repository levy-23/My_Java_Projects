import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Upon arriving at active crime scene, police men heroically put themselves in the 
 * line of fire to rush no good robbers and arrest them. After arresting the robber, 
 * the cops hop back into their car to return to the station. If the robber has escaped,
 * they hop back into their car and continue the chase on the road.
 * 
 * @author (Levent Eren, Fatima Yahya) 
 * @version V4
 */
public class PoliceMan extends Officers
{
    private Criminals c;
    private Robbers r;
    private PoliceCar policecar ;
    private boolean robberCaught;
    private boolean goingToCar;
    private boolean remove;
    private boolean robberEscaped;
    public PoliceMan(PoliceCar p)
    {
        super(0.2);
        getImage().scale(28, 36);
        policecar = p;
        robberCaught = false;
        goingToCar = false;
        remove = false;
        robberEscaped = false;
    }

    /**
     * Act - do whatever the PoliceMan wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //If Robber exists and is shooting, rush them 
        if(checkIfRobbersShooting())
        {
            turnTowards(r.getX(), r.getY());
            //walkTo(r.getX(), r.getY());
            move(speed);
            setRotation(0);

            //if (isTouching(Robber))
        }
        //If robber no longer exists, robber must be caught
        if (r == null)
        {
            robberCaught = true;
        }
        else
        {
            //If robber does exist, they either got caught or escaped
            robberCaught = r.getCriminalCaught(); 
            robberEscaped = r.getEscape();
        }
        //If rush was successful and police reached the robber, then arrest them
        if(isTouching(Robbers.class))
        {
            r.sendToJail();
            r.setCriminalCaught(true);
            robberCaught = r.getCriminalCaught();            
            r.arrest();
            //Walk back to cop car after arresting robber
            turnTowards(policecar.getX(), policecar.getY());
            move(speed);
            setRotation(0);
            goingToCar = true;
        }
        //If cop dies, remove it from world
        if (ifDead)
        {
            getWorld().removeObject(this);
        }
        //If the robber was caught and the cop walked back to the car, cop enters car and car goes back to station
        if (isTouching(PoliceCar.class) && robberCaught)
        {
            policecar.setCopsAreBack(true);
            remove = true;
        }
        //If Robber is gone, go back to car.
        if (robberCaught || robberEscaped)
        {
            turnTowards(policecar.getX(), policecar.getY());
            move(speed);
            setRotation(0);
            goingToCar = true;
            remove = true;
        }
        //If true, remove police
        if (remove)
        {
            getWorld().removeObject(this);
        }
    }   

    /**
     * Checks if robber exists and if it is shooting
     * 
     * @return boolean True if robber exists and is shooting, false otherwise.
     */
    public boolean checkIfRobbersShooting()
    {
        ArrayList<Robbers> robbersShooting = (ArrayList<Robbers>) getObjectsInRange(140, Robbers.class);
        if(!robbersShooting.isEmpty())
        {
            r = robbersShooting.get(0);
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Calls Officer's added to World method
     */
    public void addedToWorld(World w)
    {
        super.addedToWorld(w);
    }
}

