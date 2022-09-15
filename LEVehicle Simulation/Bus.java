import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Bus extends Vehicle
{
    private int counter;
    
    public Bus ()
    {
        maxSpeed = 2;
        speed = maxSpeed;
        counter = 0;
    }

    public void act()
    {
        //If there is a protest, the vehicle should stop whatever it is doing
        countProtest--;
        if(countProtest <= 0){
            checkHitPedestrian ();  // not implemented 
            drive();
            checkEdges();
        }
    }

    public void drive()
    {
        // If there is a wave, vehicles no longer drive forward, but move in the same direction of wave.
        Wave w = (Wave) getOneIntersectingObject(Wave.class);
        if(w != null){
            setLocation(getX(), getY() + 2);
        }
        else{
            //If bus is stopped for person, dont drive forward.
            counter--;
            if(counter <= 0)
            {
                Vehicle ahead = (Vehicle) getOneObjectAtOffset (speed + getImage().getWidth()/2 + 2, 0, Vehicle.class);
                if (ahead == null)
                {   
                    //Nothing in front of you, go your max speed.
                    speed = maxSpeed;
                } else {
                    if(ahead.getRotation() == 180){
                        //If a vehicle is driving the other way, join them. Vehicles are scared of super turtles and need to drive to where they came from.
                        setRotation(180);
                        speed = maxSpeed;
                    }
                    else{
                        //If no wave, check if another vehicle is infront of you, if so match their speed.
                        speed = ahead.getSpeed();
                }
    
                }
                move (speed);
            }
            else {
                speed = 0;
            }
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
        //If person, pick them up and stop bus
        Person p = (Person)getOneObjectAtOffset((this.getImage().getWidth() / 2) + speed, 0, Person.class);
        if (p != null && p.standingUp())
        {

            p.pickMeUp();
            stopBus();
            
        }
        // Check if person is below bus and do the same
        Person pBelow = (Person)getOneObjectAtOffset((this.getImage().getWidth() / 2), + 30, Person.class);
        if (pBelow != null && pBelow.standingUp())
        {

            pBelow.pickMeUp();
            stopBus();
            
        }
        //Kill turtle
        Turtle t = (Turtle)getOneObjectAtOffset((this.getImage().getWidth() / 2) + speed, 0, Turtle.class);
        if(t != null)
        {
            t.knockMeOver();
        }
    }
    //Stop bus for 1 second while people get on
    public void stopBus()
    {
        counter = 60;
    }
}
