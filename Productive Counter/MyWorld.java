import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private Button buttonMain;
    private NumDisplay totalScore;
    private ClicksDisplay clicks;
    private TextDisplay instructrion;
    private RoundsDisplay rounds;
    private Timer timer;
    private MouseInfo mouse;
    private int time;
    private boolean increased;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        buttonMain = new Button();
        totalScore = new NumDisplay();
        clicks = new ClicksDisplay();
        instructrion = new TextDisplay();
        rounds = new RoundsDisplay();
        time = 1;
        timer = new Timer(time);
        addObject(buttonMain, 200, 300);
        addObject(clicks, 200, 340);
        addObject(totalScore, 80, 120);
        addObject(instructrion, 300, 50);
        addObject(timer, 400, 175);
        addObject(rounds, 500, 250);
        increased = false;
        
        if(UserInfo.isStorageAvailable()){
            UserInfo user = UserInfo.getMyInfo();
            if(user != null){
                totalScore.setScore(user.getInt(0));
            }
        }
    }
    public void act() 
    {
        mouse = Greenfoot.getMouseInfo();
        if(mouse != null){
            if(Greenfoot.mouseClicked(buttonMain) && buttonMain.getAvailable()){
                totalScore.update();
                clicks.update();
                if(!clicks.clicksLeft()){
                    buttonMain.unavailable();
                    increased = false;
                    timer.setDone(false);
                    timer.start();
                }
            }
        }
        if(timer.isDone()){
            buttonMain.available();
            timer.setMin(time);
            if(!increased){
                clicks.increase();
            }
        }
        
        if(UserInfo.isStorageAvailable()){
            UserInfo user = UserInfo.getMyInfo();
            if(user != null){
                if(totalScore.getScore() > user.getInt(0)){
                    user.setInt(0,totalScore.getScore());
                }
            }
        }
    }
    public RoundsDisplay getRounds(){
        return rounds;
    }
    public void isIncreased()
    {
        increased = true;
    }
}
