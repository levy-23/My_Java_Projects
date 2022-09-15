import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Ambulance extends Vehicle
{

    public Ambulance ()
    {
        maxSpeed = 3;
        speed = maxSpeed;
    }

    public void act ()
    {
        //If there is a protest, the vehicle should stop whatever it is doing
        countProtest--;
        if(countProtest <= 0){
            checkHitPedestrian ();  // not implemented 
            drive();
            checkEdges();
        }

    }
    

    public void checkHitPedestrian ()
    {
        // If a turle has an aura (is therefore super) turn around because you are scared.
        SuperAura s = (SuperAura)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0 , SuperAura.class);
        if(s != null)
        {
            setRotation(180);
        }
        // Check collision for a pedestrian one pixel ahead
        // of the Vehicle
        //Heal people and kill turtles
        Person p = (Person)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0, Person.class);
        if (p != null)
        {
            p.healMe();
        }
        
        Turtle t = (Turtle)getOneObjectAtOffset((this.getImage().getWidth() / 2) + 1, 0 , Turtle.class);
        if(t != null)
        {
            t.knockMeOver();
        }
        
    }
}
