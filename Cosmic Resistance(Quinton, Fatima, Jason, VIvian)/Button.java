import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A rectangular button that the user can press
 * 
 * @author Quinton Mak
 * @version June 2021
 */
public class Button extends Actor
{
    private GreenfootImage textImage, image, innerImage;
    
    private Color black = new Color(0, 0, 0);
    private Color transparent = new Color(0, 0, 0, 0);
    private Color gray = new Color(129, 125, 150);
    private Color green = new Color(0, 255, 0);
    
    private Color active, passive;
    
    private Font f;
    private String text;
    
    private boolean pressed;
    
    private int width, height;
    /**
     * Makes a button that the user can press with an image inside (intead of text). The image will be centered in the button
     * 
     * @param width The width of the button in pixels (greater than 0)
     * @param height The height of the button in pixels (greater than 0)
     * @param imageName The fileName of the image used
     * @param passsiveColor The color of the button when not pressed
     * @param activeColor The color of the button when in the pressed state
     * @param font the font of the text in the button
     */
    public Button(int width, int height, String imageName, Color passiveColor, Color activeColor)//Makes a button with text inside
    {
        this.width = width;
        this.height = height;
        
        image = new GreenfootImage(width, height);
        image.setColor(passiveColor);
        image.fill();
        
        innerImage = new GreenfootImage(imageName); 
        
        image.drawImage(innerImage, (width - innerImage.getWidth())/2, (height - innerImage.getHeight())/2);
        pressed = false;
        
        active = activeColor;
        passive = passiveColor;
        
        this.setImage(image);
    }
    /**
     * A simpler constructir for making a button that the user can press with an image inside (intead of text).
     * Default colors are gray when not active and green when pressed.
     * 
     * @param width The width of the button in pixels (greater than 0)
     * @param height The height of the button in pixels (greater than 0)
     * @param imageName The fileName of the image used
     * 
     */
    public Button(int width, int height, String imageName){
        this(width, height, imageName, new Color(129, 125, 150), new Color(0, 255, 0));
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
     * @param font the font of the text in the button
     */
    public Button(int width, int height, String text, int textSize, int xAdj, Color passiveColor, Color activeColor, Font font)//Makes a button with text inside
    {
        this.width = width;
        this.height = height;
        
        image = new GreenfootImage(width, height);
        image.setColor(passiveColor);
        image.fill();
        
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
        
        this.setImage(image);
    }
    
    /**
     * Makes a button that the user can press. Default text font is times new roman.
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
        this(width, height, text, textSize, xAdj, passiveColor, activeColor, new Font("Times New Roman", textSize));
    }
    
    /**
     * Makes a button that the user can press. Default colors are gray when not active and green when pressed. Default text font is times new roman.
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
    /**
     * Sets the button to the "not pressed" state
     */
    public void resetButton()//Resets the button color
    {
        image.setColor(passive);
        pressed = false;
    }
    
    public void act() 
    {
        //Gets if it has been pressed (mouse hold down)
        if(Greenfoot.mousePressed(this)){
            if(!pressed){
                if(getWorld() instanceof MyWorld) ((MyWorld) getWorld()).deselectButtons();//implement this from the world, if you don't want 2 buttons to be "on" at the same time
                pressed = true;
            }
            else{
                
                pressed = false;
            }
        }
        
        if(Greenfoot.mouseDragEnded(this)) //If the mouse is dragged off the button and let go (not clicked), set color back to gray
        {
            if(!Greenfoot.mouseClicked(this)){
                image.setColor(passive);
                pressed = false;
            }
        }
        
        if(pressed) image.setColor(active);//If the button is pressed, set color to the active color
        else image.setColor(passive);//else, set to the passive color
        
        image.fill();
        if(innerImage == null){
            image.drawImage(textImage, 0, 0);
        }
        else{
            image.drawImage(innerImage, (width - innerImage.getWidth())/2, (height - innerImage.getHeight())/2);
        }
        setImage(image);
    }    
    /**
     * @return true if the button is pressed, false if not
     */
    public boolean isPressed(){
        return pressed;
    }
    /**
     * Set pressed to true (press the button)
     */
    public void pressButton(){
        pressed = true;
    }
}
