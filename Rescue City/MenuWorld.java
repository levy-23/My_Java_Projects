import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MenuWorld here.
 * 
 * @author Quinton Mak 
 * @version 4/27/2021
 */
public class MenuWorld extends World
{
    private Button startButton;
    
    private Button[] arsonistButtons = new Button[6];
    private Button[] robberButtons = new Button[6];
    private Button[] fireTruckButtons = new Button[2];
    private Button[] policeCarButtons = new Button[2];
    
    //private boolean nonePressed;
    
    private int numArsonists, numRobbers, numFireTrucks, numPoliceCars;
    /**
     * Constructor for objects of class MenuWorld.
     * 
     */
    public MenuWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        
        startButton = new Button(200, 80, "START", 14, 30, Color.CYAN, Color.GREEN, new Font("Impact", 24));
        addObject(startButton, 500, 500);
        
        Font f = new Font("Times New Roman", false, false, 20);
        
        addObject(new QMTextbox("Select initial # of arsonists", 250, 30, null, null, null, f), 500, 50);
        addObject(new QMTextbox("Select initial # of robbers", 250, 30, null, null, null, f), 505, 150);
        addObject(new QMTextbox("Select max # of fire trucks", 280, 30, null, null, null, f), 520, 250);
        addObject(new QMTextbox("Select max # of police cars", 250, 30, null, null, null, f), 505, 350);
        for(int i = 0; i <= 5; i++){//Array of buttons, to get the number of starting criminals
            arsonistButtons[i] = new Button(40, 40, ""+(i), 14, 2);
            addObject(arsonistButtons[i], 300+i*80, 100);
            if(i == 0) arsonistButtons[i].pressButton();
            
            robberButtons[i] = new Button(40, 40, "" + (i), 14, 2);
            addObject(robberButtons[i], 300+i*80, 200);
            if(i == 0) robberButtons[i].pressButton();
        }
        for(int i = 0; i < 2; i++){
        
            fireTruckButtons[i] = new Button(40, 40, "" + 5*(i+1), 14, 2 + 5*i);
            addObject(fireTruckButtons[i], 460+i*80, 300);
            if(i == 0) fireTruckButtons[i].pressButton();
            
            policeCarButtons[i] = new Button(40, 40, "" + 5*(i+1), 14, 2 + 5*i);
            addObject(policeCarButtons[i], 460+i*80, 400);
            if(i == 0) policeCarButtons[i].pressButton();
        }
    }
    
    public void act(){
        numArsonists = manageButtons(arsonistButtons) ;
        numRobbers = manageButtons(robberButtons) ;
        numPoliceCars = 5*(manageButtons(policeCarButtons) + 1);
        numFireTrucks = 5*(manageButtons(fireTruckButtons) + 1);
        
       
        
        if(Greenfoot.mouseClicked(startButton)){
            Greenfoot.setWorld(new GameWorld(numArsonists, numRobbers, numFireTrucks, numPoliceCars));
            WelcomeWorld.stopStartingBgm();  
        }
        
            
        
    }
    
    public int manageButtons(Button[] buttons){
        
        
        for(int i = 0; i < buttons.length; i++){//Only one button in the array can be pressed
            if(Greenfoot.mousePressed(buttons[i])){
                
                for(Button button : buttons){
                    if(!button.equals(buttons[i])) button.resetButton();//reset all other buttons
                }
                return i; //return the index of the button
            }
            
        }
        for(int i = 0; i < buttons.length; i++){
            if(buttons[i].isPressed()) return i;
        }
        
        
        return 0;
    }
}
