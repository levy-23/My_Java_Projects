import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A value bar class that displays values written in text form. 
 * 
 * @author (Fatima Yahya, Vivian Luo) 
 * @version June 2021
 */
public class ValueBar extends Actor 
{
    // Declare instance variables 
    private static final Color COLOR_TEXT = new Color (214,163,59);
    private static final Color COLOR_BG = new Color (255,255,255);
    private static final int FONT_SIZE = 30;
    private String title;
    private String finalString;
    private int value;
    
    /**
     * Creates a value bar with a title and a value. 
     * 
     * @param title     Title of the value bar 
     * @param val       Value of the value bar 
     */
    public ValueBar (String title, int val) 
    {
        this.title = title;
        value = val;
        finalString = title + value;
        setImage(new GreenfootImage(finalString, FONT_SIZE, COLOR_BG, COLOR_TEXT));
    } 
    
    /**
     * Creates a value bar with a title. 
     * 
     * @param title    Title of the value bar 
     */
    public ValueBar (String title) 
    {
        this.title = title;
        finalString = title;
        setImage(new GreenfootImage(finalString, FONT_SIZE, COLOR_BG, COLOR_TEXT));
    } 
   
    /**
     * Sets the title and value of the value bar every act. 
     */
    public void act() 
    {
        // If value is not zero 
        if (value != 0)
        {
            // Sets the title and value 
            finalString = title + value;
            setImage(new GreenfootImage(finalString, FONT_SIZE, COLOR_BG, COLOR_TEXT));
        }
        else
        {
            // Sets only the title 
            finalString = title;
            setImage(new GreenfootImage(finalString, FONT_SIZE, COLOR_BG, COLOR_TEXT));
        }
    }
    
    /**
     * Returns the value of the value bar. 
     */
    public int getValue() 
    {
        return value;
    }
    
    /**
     * Adds the valueToAdd to the value bar. 
     * 
     * @param valueToAdd     Value to be added to the value bar. 
     */
    public void addValue(int valueToAdd) 
    {
        value = valueToAdd + value;
    }
    
    /**
     * Updates the value of the value bar. 
     * 
     * @param val      Value to be set to the value bar 
     */
    public void update(int val)
    {
        value = val;
    }

    /**
     * Updates the title of the value bar. 
     * 
     * @param info     Title to be set to the value bar
     */
    public void update(String info)
    {
        title = info;
    }
}
