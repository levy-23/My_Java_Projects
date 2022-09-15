import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameWorld here.
 * 
 * @author (Fatima Yahya, Quinton Mak, Levent Eren, Vivian Luo) 
 * @version (April 2021)
 */
public class GameWorld extends World
{
    // Class Variables
    private GreenfootImage image;
    private int actCounter;
    public int[][] houseCoordinates = {{322, 265}, {558, 410}, {322, 136}, {472, 136}, {168, 136}, {168, 356}, {168, 476}, {558, 625}, {13, 350}, {85, 760}, {322, 335}, {472, 206}, {705, 136}, {168, 556}, {258, 626}, {558, 485}, {405, 625}, {472, 12}, {705, 12}, {230, 12}, {13, 480},{322, 410}, {412, 410}, {472, 286}, {168, 286}, {822, 136}, {168, 626}, {558, 560}, {552, 12}, {320, 12}, {13, 415}};
    
    private ScoreBar scoreBar;
    private boolean scoreShowing;
    public int numRobbers;
    public int numArsonists;
    public int numArrests;
    public int numFires;
    public int numRobberies;
    
    private int initialRobbers, initialArsonists, maxFireTrucks, maxPoliceCars;
    
    // Declare a variable to store background music. 
    private GreenfootSound trafficMusic; 
    /**
     * Constructor for objects of class GameWorld.
     * 
     */
    public GameWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        image = new GreenfootImage("BackgroundTwo.png");
        setBackground (new GreenfootImage(image)); 
        actCounter = 1;
        addObject(new Jail(), 87, 758);
        addObject(new CriminalDepot(), 728, 775);
        addObject(new FireStation(10), 280, 740);
        addObject(new PoliceStation(10), 510, 740);
        addCurbs();
        setPaintOrder(ScoreBar.class, StatBar.class, Curb.class, Arsonists.class, PoliceMan.class, Vehicles.class, Alarm.class, WaterSpray.class, Fire.class, Buildings.class, Jail.class, CriminalDepot.class);
        // Mr Cohen's Code - Add the scorebar to the top of the screen and give it a starting state
        scoreBar = new ScoreBar (800);
        addObject(scoreBar, 400, 785);
        scoreBar.update("Welcome to *insert town name*");
        scoreShowing = false;
        
        numRobbers = 0;
        numArsonists = 0;
        numArrests = 0;
        numFires = 0;
        numRobberies = 0;
        
        // Add mp3 traffic sound file as background music 
        trafficMusic = new GreenfootSound ("Ambience4.mp3"); 
    }
    
    public GameWorld(int initialArsonists, int initialRobbers, int maxFireTrucks, int maxPoliceCars)
    {    
        
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        image = new GreenfootImage("BackgroundTwo.png");
        setBackground (new GreenfootImage(image)); 
        actCounter = 1;
        addObject(new Jail(), 87, 758);
        addObject(new CriminalDepot(), 728, 775);
        addObject(new FireStation(maxFireTrucks), 270, 740);
        addObject(new PoliceStation(maxPoliceCars), 510, 740);
        addCurbs();
        setPaintOrder(ScoreBar.class, StatBar.class, Curb.class, Arsonists.class, PoliceMan.class, Vehicles.class, Alarm.class, WaterSpray.class, Fire.class, Buildings.class, Jail.class, CriminalDepot.class);
        // Mr Cohen's Code - Add the scorebar to the top of the screen and give it a starting state
        scoreBar = new ScoreBar (800);
        addObject(scoreBar, 400, 785);
        scoreBar.update("Welcome to *insert town name*");
        scoreShowing = false;
        
        numRobbers = 0;
        numArsonists = 0;
        numArrests = 0;
        numFires = 0;
        numRobberies = 0;
        
        this.initialArsonists = initialArsonists;
        this.initialRobbers = initialRobbers;
        this.maxFireTrucks = maxFireTrucks;
        this.maxPoliceCars = maxPoliceCars;
        
        // Add mp3 traffic sound file as background music 
        trafficMusic = new GreenfootSound ("Ambience4.mp3"); 
    }
    
    /**
     *  starts the ambience music 
     */
    public void startMusic () {
        // Start playing traffic background music 
        trafficMusic.playLoop(); 
    }
    
    /**
     * Called automatically by Greenfoot. 
     */
    public void stopped () {
        // Stop playing traffic background music 
        trafficMusic.stop(); 
    }
    
    public void act()
    {
        startMusic();
        setPaintOrder(ScoreBar.class, StatBar.class, Curb.class, Arsonists.class, PoliceMan.class, Vehicles.class, Alarm.class, WaterSpray.class, Fire.class, Buildings.class, Jail.class, CriminalDepot.class);
        
        // Adds Houses to the map
        spawnHouses();
        // Creates and adds criminals (arsonists and robbers) after a certain interval (350 acts))
        //spawnCriminals();
        if (actCounter > 340)
        {
            String key = Greenfoot.getKey();
            if ("a".equals(key)) 
            {
                addObject (new Arsonists(), 718, 690);
                scoreShowing = true;
            }
            else if ("r".equals(key)) 
            {
                addObject (new Robbers(), 718, 690);
                scoreShowing = true;
            }
            
        }
        // Mr Cohen's Code - Updates every 30 acts to avoid lag.
        if (actCounter % 2 == 0)
        {
            statUpdates();
            if (scoreShowing)
            {
                scoreBar.update(numRobbers, numArsonists, numArrests, (numFires), numRobberies);
            }        
        }
        // act counter is incremented
        actCounter++;
    }
    
    /**
     * Adds rectangles on all the green areas of the map so that the vehicles and people are bale to navigate the map without bumping into houses.
     */
    public void addCurbs()
    {
        addObject(new Curb(108, 226), 322, 336);
        addObject(new Curb(108, 300), 172, 245);
        addObject(new Curb(9, 175), 113, 307);
        addObject(new Curb(72, 50), 73, 245);
        addObject(new Curb(90, 82), 416, 408);
        addObject(new Curb(108, 82), 322, 138);
        addObject(new Curb(102, 80), 407, 625);
        addObject(new Curb(104, 300), 556, 518);
        addObject(new Curb(5, 175), 502, 575);
        addObject(new Curb(83, 50), 459, 518);
        addObject(new Curb(108, 50), 323, 518);
        addObject(new Curb(115, 226), 168, 552);
        addObject(new Curb(88, 78), 267, 626);
        addObject(new Curb(106, 226), 472, 210);
        addObject(new Curb(82, 197), 693, 413);
        addObject(new Curb(24, 197), 788, 413);
        addObject(new Curb(150, 54), 725, 27);
        addObject(new Curb(150, 82), 725, 138);
        addObject(new Curb(148, 46), 726, 245);
        addObject(new Curb(150, 108), 725, 611);
        addObject(new Curb(150, 90), 725, 755);
        addObject(new Curb(192, 90), 512, 755);
        addObject(new Curb(192, 90), 279, 755);
        addObject(new Curb(104, 90), 89, 755);
        addObject(new Curb(192, 54), 512, 27);
        addObject(new Curb(192, 54), 279, 27);
        addObject(new Curb(104, 54), 89, 27);
        addObject(new Curb(40, 227), 588, 210);
        addObject(new Curb(80, 80), 40, 137);
        addObject(new Curb(68, 200), 34, 412);
        addObject(new Curb(68, 115), 34, 610);
    }
    
    /**
     * Creates houses on most of the green areas on the map.
     */
    public void spawnHouses()
    {
        if (actCounter == 30)
        {
            addObject(new House(), 322, 265);
            addObject(new House(), 558, 410);
            addObject(new House(), 322, 136);
            addObject(new House(), 472, 136);
            addObject(new House(), 168, 136);
            addObject(new House(), 168, 356);
            addObject(new House(), 168, 476);
            addObject(new House(), 558, 625);
            addObject(new House(), 89, 12);
            addObject(new House(), 13, 350);
        }  
        if (actCounter == 190)
        {
            addObject(new House(), 322, 335);
            addObject(new House(), 472, 206); 
            addObject(new House(), 168, 206);
            addObject(new House(), 705, 136);
            addObject(new House(), 168, 556);
            addObject(new House(), 258, 626);
            addObject(new House(), 558, 485);
            addObject(new House(), 405, 625);
            addObject(new House(), 472, 12);
            addObject(new House(), 705, 12);
            addObject(new House(), 230, 12);
            addObject(new House(), 13, 480);
        }
        if (actCounter == 340)
        {
            addObject(new House(), 322, 410);
            addObject(new House(), 412, 410);
            addObject(new House(), 472, 286); 
            addObject(new House(), 168, 286);
            addObject(new House(), 822, 136);
            addObject(new House(), 168, 626);
            addObject(new House(), 558, 560);
            addObject(new House(), 552, 12);
            addObject(new House(), 320, 12);
            addObject(new House(), 22, 136);
            addObject(new House(), 13, 415);
            
            for(int i = 0; i < initialArsonists + initialRobbers; i++){
                if(i < initialArsonists){
                    addObject(new Arsonists(), 760 - i*40, 690);
                }
                else addObject(new Robbers(), 760 - i*40, 690);
            }
        }
    }
    
    /**
     * Spawns robbers every 350 acts and spawns arsonists every 700 acts.
     * They are spawned from the criminal depot.
     */
    public void spawnCriminals()
    {
        if (actCounter % 350 == 0)
        {
            addObject (new Robbers(), 718, 690);
            scoreShowing = true;
        }
        
        if (actCounter % 700 == 0)
        {
            addObject (new Arsonists(), 718, 690);
            scoreShowing = true;
        }
    }
    
    
    /**
     * Get the distance (in pixels) between two actors. The two actors must be in the world.
     * 
     * @param a The first actor 
     * @param b The second actor
     * @return The distance between actor a and b
     */
    public static double distanceBetween(Actor a, Actor b)
    {
        return Math.sqrt((a.getX() - b.getX())*(a.getX() - b.getX()) + (a.getY() - b.getY())*(a.getY() - b.getY()));
    }
    
    /**
     * Get the distance (in pixels) between two points in the world)
     * 
     * @param x1 The x coordintate of the first point 
     * @param y1 The y coordinate of the first point
     * @param x2 The x coordintate of the second point 
     * @param y2 The y coordintate of the second point
     * @return The distance between points (x1, y1) and (x2, y2)
     */
    public static double distanceBetween(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt((x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2));
    }
    
    /**
     * Gets the Y coordinate of a random house
     * @param index The random number which acts as the index in the 2D array.
     * @return The X coordinate of the random house
     */
    public int returnCoordinatesX(int index)
    {
        return houseCoordinates[index][0];
    }
    
    /**
     * Gets the Y of a random house
     * @param index The random number which acts as the index in the 2D array.
     * @return The Y coordinate of the random house
     */
    public int returnCoordinatesY(int index)
    {
        return houseCoordinates[index][1];
    }
    
    
    // Method that updates variables, needed for StatBar class.
    private void statUpdates ()
    {
        numRobbers = getObjects(Robbers.class).size();
        numArsonists = getObjects(Arsonists.class).size();
        numFires = getObjects(Fire.class).size();
    }
    
}
