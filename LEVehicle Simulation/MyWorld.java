import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Justice for Pedestrians Against Rutheless Vehicles - by Levent Eren
 * 
 * Credits:
 * Audio - (AmishRob, beepbeep),(Authorless from soundjay.com, wave),(IENBA, panic),(authorless from mixkit.co, whoosh), (authorless from suonoelletronico, whistle),(HowardV, crowd),(Tessa_Elief, traffic),(Authorless from findsounds, tire screetch)
 * Code - (Mr Cohen, ScoreBar),(Mr Cohen, start up code)
 * Images - (Google Images, every Image)
 * 
 * This World is a dangerous street with many different vehicles zooming past. 
 * Turtles and people attempt to cross this street, however most of them are 
 * killed. For justice against these reckless drivers, upon crossing the street, 
 * each pedestrian takes action. Turtles go to the right side, entering the lab, 
 * and thus temporarily making every turtle a super turtle, as well as bring dead 
 * turtles back to life. These Super turtles scare off vehicles and force them 
 * to go back to where they came from. People however join together and hold a protest. 
 * After aquiring enough memebers, their movement is powerful enough for an official 
 * to blow a whistle and force vehicles to temporarity come to a halt. Each type 
 * of pedestrian can wake up the dead and have at least one vehicle that repects them.
 * Ambulances heal and respects people. The lab heals turtles and eco friendly cars 
 * respect turtles. Car give a little warning honk to indicate that they are changing lanes. 
 * Additional to all this, occasionally a large wave comes and cleans the street 
 * of everything, vehicles, people, dead pedestrians. Only turtles can continue to cross
 * since they can swim.
 * 
 * Importance of Inheritence:
 * First of all, inheritence lowers the amount of physical work need, actually typing, 
 * and mental work, planning how to do what. Inhertience gives me a guidline and 
 * helps me organize what objects can do what and carry what. For example, promising 
 * that all vehicles have a checkHitPedestrian promise ensures that I know every vehicle
 * must have one. Inheritence also ensures the quality and simplicity of the final product.
 * Having all vehicles behave similarly (for example, drive along the street and react with pedestrians)
 * and inherit method and tools from eachother ensure that it is clear to the viewer
 * what that object is doing and what is its purpose. If I had to write each vehicle
 * individually, there would be a chance I would not write every vehicle the exact same way contrasting
 * to inheritence, this could lead to the viwers getting confused from the lack of 
 * identical behavious. Due to inheritence, the code runs smoothly and in an prganized fashion, ultimately 
 * making it easier for the developer, and clearer and in better quality for the viewer/user.
 * 
 * 
 * @version March 2021 
 * @since March 2015
 */
public class MyWorld extends World
{
    private int randomize;
    
    public static final int[] LANE_POSITIONS = {79, 127, 175, 222, 272, 320};
    
    /**
     * Spawn Rates:
     * Lower number means more spawns
     * 3:spawnRate chance per act of spawning a random Vehicle
     * 1:pedSpawn chance per act of spawning a Pedestrian
     */
    private int spawnRate = 100; // Rate at which vehicls spawn, must be higher than 4
    private int pedSpawn = 200; // Rate at which vehicls spawn, must be higher than 3
    private int numPeopleForProtest = 50; // How many people needed to cross to begin action (the protest), increments of 10.
    private int waveSpawnRate = 2500; // Approximately how often a wave random wave spawns.
    
    //Objects for display or pupose
    private LaneSpawnCheck[] laneChecks; // For purpose: to make sure lanes are clear to spawn into.
    private ScoreBar scoreBar; // Display and Purpose: To show the number of people turned into protesters.
    private ScoreBar lab; // Display: To show that the building on the right is a lab that makes turtles super.
    
    private int protesters;
    private int countSuperTurtle;
    private int countProtest;
    private boolean liveProtest;
    //Initialize sounds
    private GreenfootSound tireScreech;
    private GreenfootSound traffic;
    private GreenfootSound crowd;
    private GreenfootSound whistle;
    private GreenfootSound whoosh;
    private GreenfootSound panic;
    private GreenfootSound wave;
    //Boleans to solve some logic. specifically how to time different sounds
    private int playOnce;
    private int playOnceWhistle;
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(593, 455, 1, false); 

        // A simple, hidden object to determine if a lane is currently free for "spawning"
        laneChecks = new LaneSpawnCheck [6];
        for (int i = 0; i < 6; i++){
            laneChecks[i] = new LaneSpawnCheck();
            addObject (laneChecks[i], 40, getYPosition (i));
        }
        // To display the number of people that crossed the street and turned into protesters.
        scoreBar = new ScoreBar(212);
        addObject(scoreBar, 150, 27);
        protesters = 0;
        scoreBar.update("Protester: ", protesters);
        // To inform viewer the building on the right is a lab
        lab = new ScoreBar (100, 100, 200, 200, 18);
        addObject(lab, 450, 27);
        lab.update("Lab");
        // Created for logic, to make events last a certain period of time.
        countSuperTurtle = 0;
        countProtest = 0;
        liveProtest = false;
        playOnce = 0;
        playOnceWhistle = 0;
        // Sounds I used:
        tireScreech = new GreenfootSound("TireScreech2.wav");
        traffic = new GreenfootSound("traffic.wav");
        crowd = new GreenfootSound("crowd.wav");
        whistle = new GreenfootSound("whistle.wav");
        whoosh = new GreenfootSound("whoosh.wav");
        panic = new GreenfootSound("panic.wav");
        wave = new GreenfootSound("wave.wav");
        //Simple graphics to make it look a little nicer.
        addObject(new Tulip(), Greenfoot.getRandomNumber(590), 400);
        addObject(new Tulip(), Greenfoot.getRandomNumber(590), 430);
        addObject(new Tulip(), Greenfoot.getRandomNumber(590), 410);
        addObject(new Tulip(), Greenfoot.getRandomNumber(590), 420);
        addObject(new Tulip(), Greenfoot.getRandomNumber(590), 410);
        addObject(new Mushroom(), Greenfoot.getRandomNumber(590), 402);
        addObject(new Mushroom(), Greenfoot.getRandomNumber(590), 422);
        addObject(new Mushroom(), Greenfoot.getRandomNumber(590), 432);
        // To make Super Turtle look good, to make sure pedestrians got run OVER, and to make sure wave did not cover any objects.
        setPaintOrder(SuperAura.class, Pedestrian.class, Vehicle.class, ScoreBar.class, Wave.class);
        
    }

    public void act ()
    {
        // Add ambient sound to background
        traffic.playLoop();
        // Spawn pedestrians, vehicles and waves
        spawnPedestrians();
        spawnWave();
        //Make sure no vehicles spawn during protest
        countProtest--;
        if(countProtest <= 0){
            spawnVehicles(); // Becasue all vehicles are stopped
            //Becomes too overwhelming, need a cool down like the turtles
            checkProtesters(); // Check if any people arrived to the protest, update total, and start protest if enough people.
            
        }
        // Check if any turtles arrived to the lab, if so make them super for a bit.
        checkSuperTurtle();
        //Since multiple sounds used in protest (unlike the super turtles), needed to organize.
        if(liveProtest == true ){
            //Play protest crowd sounds once, make sure not looped.
            if ( playOnce == 1){
                crowd.setVolume(30);
                crowd.play();
                playOnce = 0;
            }
            //When crowd sounds are finished, play the whistle and tire screetch.
            boolean playing = crowd.isPlaying();
            if(playing == false){
                //Play whistle once, make sure not looped.
                if(playOnceWhistle == 1){
                    whistle.play();
                    playOnceWhistle = 0;
                }
                // When whistle is finished play tire screetch.
                if(whistle.isPlaying() == false){
                    //Make protest last for 4 seconds, used to control vehicles from moving and spawning
                    countProtest = 60*4;
                    ArrayList<Vehicle> v = (ArrayList<Vehicle>) getObjects(Vehicle.class);
                    for(Vehicle l : v){
                        //Stop each vehicle and screetch their tires
                        l.stop();
                        tireScreech.play();
                    }
                    playing = true; // only to stop loop
                    liveProtest = false; // to stop main loop
                }
            }
        }

    }
    
    private void spawnWave()
    {
        //Add a wave everyone once in a while
        if(Greenfoot.getRandomNumber(waveSpawnRate) == 1){
            addObject(new Wave(), 296, 0);
            wave.setVolume(45);
            wave.play();
        }
    }

    private void spawnVehicles()
    {
        // Generate a random number to add a random element
        // to Vehicle spawning
        randomize = Greenfoot.getRandomNumber(spawnRate);

        // Chose a random lane in case a vehicle spawns
        int lane = Greenfoot.getRandomNumber (6);
        

        if (laneChecks[lane].vehiclePresent() == false){
            // spawn vehicles
            int spawnY = getYPosition (lane);
            
            if (randomize == 1)
            {
                // spawn a Car
                addObject (new Car(), 10, spawnY);
            }
            else if (randomize == 2)
            {
                // spawn a Bus
                addObject (new Bus(), 10, spawnY);
            }

            else if (randomize == 3)
            {
                // spawn a Car
                addObject (new Ambulance(), 10, spawnY);
            }
            else if (randomize == 4)
            {
                //spawn eco friendly Car
                addObject(new EcoCar(), 10, spawnY);
            }
        }

    }
    private void spawnPedestrians()
    {
        // spawn pedestrians
       
        if (Greenfoot.getRandomNumber(pedSpawn) <= 2)
        {
            addObject (new Person(), Greenfoot.getRandomNumber(592), 454);
        }
        if (Greenfoot.getRandomNumber(pedSpawn) == 3)
        {
            addObject (new Turtle(), Greenfoot.getRandomNumber(592), 454);
        }
    }

    /**
     * Returns the appropriate y coordinate for a given lane
     */
    public int getYPosition (int inLane)
    {
        // Manually input values based on the background graphic
        switch (inLane)
        {
            case 0: 
            return 134;

            case 1:
            return 182;

            case 2:
            return 230;

            case 3:
            return 278;

            case 4:
            return 326;

            case 5: 
            return 374;

        }  
        // In case an invalid value is passed in
        return 0;
    }
    
    public void checkProtesters()
    {
        // Check if people reached the protest, a few pixels are past the protest so had to account for them as well. 
        //That is why I had to check twice, make sure they arrive in either place.
        ArrayList<Person> p = (ArrayList<Person>) getObjectsAt(0, 76, Person.class);
        ArrayList<Person> p2 = (ArrayList<Person>) getObjectsAt(34, 76, Person.class);
        for (Person i : p){
            if(p != null)
            {
                //If person arrive, update label and remove them
                protesters = protesters + 10;
                removeObject(i);
                scoreBar.update("Protesters: ",  protesters);
            }
        }
        for (Person j : p2){
            if(p2 != null)
            {
                //If person arrive, update and remove them
                protesters = protesters + 10;
                removeObject(j);
                scoreBar.update("Protesters: ",  protesters);
            }   
        }
        if(protesters >= numPeopleForProtest)
        {
            //set up variables for logic used in the act method. To make the sounds play in order w/o overlapping.
            liveProtest = true;
            playOnce = 1;
            playOnceWhistle = 1;
            protesters = 0;
        }
    }
    
    public void checkSuperTurtle()
    {
       // Counter to make sure turtles dont become super back to back, or else too much power up.
        countSuperTurtle--;
        if(countSuperTurtle <= 0)
       {
        // Check if turtles reached the lab, a few pixels are past the lab so had to account for them as well. 
        //That is why I had to check twice, make sure they arrive in either place.
        ArrayList<Turtle> t = (ArrayList<Turtle>) getObjectsAt(592, 75, Turtle.class);
        ArrayList<Turtle> t2 = (ArrayList<Turtle>) getObjectsAt(559, 75, Turtle.class);
        for (Turtle i : t){
            if(t != null)
            {
                ArrayList<Turtle> deadT = (ArrayList<Turtle>) getObjects(Turtle.class);
                for(Turtle l : deadT){
                    //If a turtle arrives to lab, heal all turtles and make them super.
                    l.healMe();
                    l.superTurtle();
                    whoosh.play();
                    panic.play();
                }
                //Cannot activate super for 15 more seconds
                countSuperTurtle = 60*15;
                break;
            }
        }
        if(countSuperTurtle <= 0){
            for (Turtle j : t2){
                if(t2 != null)
                {
                    ArrayList<Turtle> deadT = (ArrayList<Turtle>) getObjects(Turtle.class);
                    for(Turtle l : deadT){
                        //If a turtle arrives to lab, heal all turtles and make them super.
                        l.healMe();
                        l.superTurtle();
                        whoosh.play();
                        panic.play();
                    }
                    //Cannot activate super for 15 more seconds
                    countSuperTurtle = 60*15;
                    break;
                }   
            }
        }
        }
    }
}

