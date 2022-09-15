import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shows the info of all the towers
 * 
 * @author Quinton Mak
 * @version June 2021
 */
public class InfoWorld extends World
{
    private MyWorld game;
    private Button backButton;
    /**
     * Constructor for objects of class InfoWorld.
     * 
     * @param game the current MyWorld the user is playing in 
     * 
     */
    public InfoWorld(MyWorld game)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        this.game = game;
        setBackground("Info.png");
        backButton = new Button(300, 40, "Back To Game", 24, 75, Color.GRAY, new Color(0, 255, 0));
        addObject(backButton, 500, 550);
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(backButton) && backButton.isPressed()){
            Greenfoot.setWorld(game);//go back to the game if user doesn't want to read info anymore
        }
    }
}
