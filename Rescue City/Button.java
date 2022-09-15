import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author Quinton Mak
 * @version 4/26/2021
 */
public class Button extends Box
{
    private GreenfootImage textImage;
    
    private Color black = new Color(0, 0, 0);
    private Color transparent = new Color(0, 0, 0, 0);
    private Color gray = new Color(129, 125, 150);
    private Color green = new Color(0, 255, 0);
    
    private Color active, passive;
    
    private Font f;
    private String text;
    
    private boolean pressed;
    /**
     * Makes a button that the user can press
     * 
     * @param width The width of the button in pixels (greater than 0)
     * @param height The height of the button in pixels (greater than 0)
     * @param text The text to be shown in the button
     * @param textSize The font size of the text
     * @param xAdj How far to the left the bottom left corner of the text should be. A value of 0 makes the text start in the center. 
     * @param passsiveColor The color of the button when not pressed
     * @param activeColor The color of the button when in the pressed state
     * @param font the font of the text in the button
     */
    public Button(int width, int height, String text, int textSize, int xAdj, Color passiveColor, Color activeColor, Font font)//Makes a button with text inside
    {
        super(width, height, passiveColor);//Makes a box as the background for the button
        this.text = text;
        textImage = new GreenfootImage(width, height);
        f = font;
        textImage.setFont(f);
        textImage.drawString(text, width/2 - xAdj, height/2 + textSize/3);//xAdj is used to center the text for the button
        //As well, textSize/3 will approximately center the y component of the text since that is always proportional to textsize
        image.drawImage(textImage, 0, 0);
        pressed = false;
        
        active = activeColor;
        passive = passiveColor;
    }
    
    /**
     * Makes a button that the user can press
     * 
     * @param width The width of the button in pixels (greater than 0)
     * @param height The height of the button in pixels (greater than 0)
     * @param text The text to be shown in the button
     * @param textSize The font size of the text
     * @param xAdj How far to the left the bottom left corner of the text should be. A value of 0 makes the text start in the center. 
     * @param passsiveColor The color of the button when not pressed
     * @param activeColor The color of the button when in the pressed state
     */
    public Button(int width, int height, String text, int textSize, int xAdj, Color passiveColor, Color activeColor)//Makes a button with text inside
    {
        this(width, height, text, textSize, xAdj, passiveColor, activeColor, new Font("Times New Roman", 14));
    }
    
    /**
     * Makes a button that the user can press. Gray when not active and green when pressed.
     * 
     * @param width The width of the button in pixels (greater than 0)
     * @param height The height of the button in pixels (greater than 0)
     * @param text The text to be shown in the button
     * @param textSize The font size of the text
     * @param xAdj How far to the left the bottom left corner of the text should be. A value of 0 makes the text start in the center. 
     */
    
    public Button(int width, int height, String text, int textSize, int xAdj){
        this(width, height, text, textSize, xAdj, new Color(129, 125, 150), new Color(0, 255, 0));
    }
    
    public void resetButton()//Resets the button color
    {
        image.setColor(passive);
        pressed = false;
    }
    
    public void act() 
    {
        
        if(Greenfoot.mousePressed(this)){
            if(!pressed){
                
                pressed = true;
            }
            else{
                
                pressed = false;
            }
        }
        
        if(Greenfoot.mouseDragEnded(this)) //If the mouse is dragged off the button and let go, set color back to gray
        {
            if(!Greenfoot.mouseClicked(this)){
                image.setColor(passive);
                pressed = false;
            }
        }
        
        if(pressed) image.setColor(active);//If the button is pressed, set color to green
        else image.setColor(passive);
        
        image.fill();
        image.drawImage(textImage, 0, 0);
        setImage(image);
    }    
    
    public boolean isPressed(){
        return pressed;
    }
    
    public void pressButton(){
        pressed = true;
    }
}
