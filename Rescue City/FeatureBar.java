import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * A bar that can be positioned relative to an actor, or simply as a static object in a world.
 * 
 * The bar can be used in two different ways. 
 * Either as a count down timer. This means that it will count down from a given number of seconds.
 * The second way is as a "count up" timer. This counts up till a given number of seconds.
 * 
 * If following an actor, the bar can appear at a position relative to the actor for a certain period of time.
 * 
 * This can be used for many different purposes. An example is if a player picks up a speed booster, a feature bar can be initialised for 5
 * seconds to give an actor super speed ability for 5 seconds. A count up timer can be used as a maximum speed limit for a race.
 * 
 * The bar itself does not actually provide super speed, it is simply a visual measure for time for any abillity or feature.
 * 
 * If not following an actor, it can be initialised at any location in the world. 
 * 
 * @author (Fatima Yahya Tumbi) 
 * @version (April 2021)
 */
public class FeatureBar extends Actor
{
    // Variable for maximum value for the bar
    private int maximumValue;
    
    // Variable relating to the size of the bar itself
    private int barWidth; // width of the bar
    private int barHeight; // height of the bar
    
    // Variables to hold colors for the background of the bar and the bar itself.
    private Color backgroundColour; // background colour for the object
    private Color colourBar; // colour of the bar
    
    // Variables relating to the text next to the bar.
    private int fontSize; 
    private Color textColour; // colour of the text next to the bar
    
    // Variables relating to the value of what the bar holds.
    private int value; // current value of the bar    
    private String units; // the bar's unit of measure
    private boolean showUnits; // whether or not to show units
    private boolean featureIsImage; // if the feature is shown in an image or not
    private String featureName; // the name of the feature
    private String imageName; // the image that shows what feature the bar is counting down
    
    // Variables relating to the creation of the image of the bar.
    private int barValue;
    private int maximumX;
    private int maximumY;
    private int sumXValues;
    private GreenfootImage featureImage;
    private GreenfootImage valueImage;
    private GreenfootImage barImage;
    private GreenfootImage imageOne;
    private GreenfootImage imageTwo;
    
    private int offset; // how much "offset" there should be relative to the target
    private Actor targetActor;
    private boolean isInWorld;
    private boolean barCountDown;
    /**
     * A simple constructor - Takes in 7 parameters and is slightly customizable.
     * 
     * @param nameOfFeature The name of the feature the bar is being used for. This can be displayed to the left of the bar itself.
     * @param owner The actor that the Bar should follow.
     * @param fileName  The name of the file, needs to be enclosed in quaotation marks that can be displayed to the left of the bar. It describes what the bar is being used for. If none, then should be "null".
     * @param maxValue  The number of seconds that the bar needs to be displayed for.
     * @param unitsShown  Whether it should should show how many seconds are left to the right of the bar itself.
     * @param imageFeature  Whether the description of what the bar is being used for, is an image or not. If an image, then should be "true"
     * @param addOrSubtractTime Whether to be countdown timer or to track time. "true" for count down.
     */
    public FeatureBar(String nameOfFeature, Actor owner, String fileName, int maxValue, boolean unitsShown, boolean imageFeature, boolean countDownTimer)
    {        
        barWidth = 35;
        barHeight = 5;
        
        backgroundColour = new Color(0, 0, 0, 0); 
        textColour = Color.BLACK; 
        colourBar = Color.BLACK; 
        
        fontSize = 10;
    
        imageName = fileName;
        units = "seconds";
        maximumValue = maxValue * 60;
        value = maxValue * 60;
        showUnits = unitsShown;
        featureIsImage = imageFeature;
        featureName = nameOfFeature;
        
        offset = 22;
        targetActor = (Actor) owner;
        isInWorld = true;
        barCountDown = countDownTimer;
        
        if (barCountDown)
        {
            value = maxValue * 60;
        }
        else
        {
            value = 1;
        }
        
        createNewImage();
    }
        
    /**
     * A more complex constructor - Takes in 13 parameters and is quite customizable.
     * 
     * @param nameOfFeature The name of the feature the bar is being used for. This can be displayed to the left of the bar itself.
     * @param owner The actor that the Bar should follow.
     * @param offsetValue   How far in terms of Y coordinate the bar should be from the center of the actor it is following.
     * @param widthBar  The width of the inside portion of the bar.
     * @param heightBar The height of the inside portion of the bar.
     * @param colourText    The colour of the text that is next to the bar image.
     * @param barColour The colour of the inside portion of the bar.
     * @param sizeFont  The size of the text that is next to the bar image.
     * @param fileName  The name of the file, needs to be enclosed in quaotation marks that can be displayed to the left of the bar. It describes what the bar is being used for. If none, then should be "null".
     * @param maxValue  The number of seconds that the bar needs to be displayed for.
     * @param unitsShown  Whether it should should show how many seconds are left to the right of the bar itself.
     * @param imageFeature  Whether the description of what the bar is being used for, is an image or not. If an image, then should be "true"
     * @param addOrSubtractTime Whether to be countdown timer or to track time. "true" for count down.
     */
    public FeatureBar(String nameOfFeature, Actor owner, int offsetValue, int widthBar, int heightBar, Color colourText, Color barColour, int sizeFont, String fileName, int maxValue, boolean unitsShown, boolean imageFeature, boolean countDownTimer)
    {        
        barWidth = widthBar;
        barHeight = heightBar;
        
        backgroundColour = new Color(0, 0, 0, 0); 
        textColour = colourText; 
        colourBar = barColour; 
        
        fontSize = sizeFont;
    
        imageName = fileName;
        units = "seconds";
        maximumValue = maxValue * 60;
        value = maxValue * 60;
        showUnits = unitsShown;
        featureIsImage = imageFeature;
        featureName = nameOfFeature;
        
        offset = offsetValue;
        targetActor = (Actor) owner;
        isInWorld = true;
        barCountDown = countDownTimer;
        
        if (barCountDown)
        {
            value = maxValue * 60;
        }
        else
        {
            value = 1;
        }
        
        createNewImage();
    }
    
    /**
     * A more complex constructor - Takes in 11 parameters and is quite customizable. 
     * This constructor creates a static object or bar that does not follow an actor. 
     * This can be used for a multitude of things. 
     * For example it can be used as a countdown timer or a simple timer for a game.
     * 
     * @param nameOfFeature The name of the feature the bar is being used for. This can be displayed to the left of the bar itself.
     * @param owner The actor that the Bar should follow.
     * @param offsetValue   How far in terms of Y coordinate the bar should be from the center of the actor it is following.
     * @param widthBar  The width of the inside portion of the bar.
     * @param heightBar The height of the inside portion of the bar.
     * @param colourText    The colour of the text that is next to the bar image.
     * @param barColour The colour of the inside portion of the bar.
     * @param sizeFont  The size of the text that is next to the bar image.
     * @param fileName  The name of the file, needs to be enclosed in quaotation marks that can be displayed to the left of the bar. It describes what the bar is being used for. If none, then should be "null".
     * @param maxValue  The number of seconds that the bar needs to be displayed for if count down. If count up, how long to count till.
     * @param unitsShown  Whether it should should show how many seconds are left to the right of the bar itself.
     * @param imageFeature  Whether the description of what the bar is being used for, is an image or not. If an image, then should be "true"
     * @param addOrSubtractTime Whether to be countdown timer or to track time. "true" for count down. 
     */
    public FeatureBar(String nameOfFeature, int widthBar, int heightBar, Color colourText, Color barColour, int sizeFont, String fileName, int maxValue, boolean unitsShown, boolean imageFeature, boolean countDownTimer)
    {        
        barWidth = widthBar;
        barHeight = heightBar;
        
        backgroundColour = new Color(0, 0, 0, 0); 
        textColour = colourText; 
        colourBar = barColour; 
        
        fontSize = sizeFont;
    
        imageName = fileName;
        units = "seconds";
        maximumValue = maxValue * 60;
        
        showUnits = unitsShown;
        featureIsImage = imageFeature;
        featureName = nameOfFeature;
        
        isInWorld = true;
        barCountDown = countDownTimer;
        
        if (barCountDown)
        {
            value = maxValue * 60;
        }
        else
        {
            value = 1;
        }
        
        createNewImage();
    }

     
    // Methods
    /**
     * Act - This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        // Creates new image of bar every act to show as that the time left for the feature is decreasing.
        createNewImage();
        
        // If the countdown is over or count up is over, the bar is removed.
        if (value == 0 || value == (maximumValue + 1))
        {
            getWorld().removeObject(this);
            isInWorld = false;
        }
        
        // depending on whether it is a countdown or count up timer, value is incremented or reduced by 1
        if (barCountDown)
        {
            value--;
        }
        else
        {
            value++;
        }

    }    
    
    /**
     * Creates an image which is a combination of three different images. If needed, the two side images can be blank and only the bar image can be shown.
     * This can be done by naming the Feature  "" and setting showUnits to false.
     * 
     */
    private void createNewImage()
    {
        
        // the image of the feature - describes which feature this bar is being timed for
        if (featureIsImage)
        {
            // if yes, then number of seconds remaining is sent to display
            featureImage = new GreenfootImage(imageName);
        }
        else 
        {
            // otherwise a transparent 1 x 1 image is set
            featureImage = new GreenfootImage(featureName + " ", fontSize, textColour, backgroundColour);
        }
        
        
        // client is able to choose if they want to show how much time is remaining
        if (showUnits)
        {
           // if yes, then number of seconds remaining is sent to display
           valueImage = new GreenfootImage(" " + (value / 60) + " " + units, fontSize, textColour, backgroundColour);    
        }
        else 
        {
           // otherwise a transparent 1 x 1 image (smallest size so does not impact maximumY and maximumX) is set
           valueImage = new GreenfootImage(1, 1);
        }
        
        // IF statement to find which image (featureImage or valueImage) has the largest width
        if (featureImage.getWidth() > valueImage.getWidth())
        {
            maximumX = featureImage.getWidth();
        }
        else
        {
            maximumX = valueImage.getWidth();
        }
        
        // finds how "filled" in the bar should be according to the maximum value.    
        barValue = (int) (barWidth * (value) / (maximumValue));
 
        // creating an image of the bar itself        
        GreenfootImage imageOne = new GreenfootImage(barWidth, barHeight);
        imageOne.setColor(colourBar);
        imageOne.fill();
        
        GreenfootImage imageTwo = new GreenfootImage(barWidth, barHeight);
        imageTwo.drawImage(imageOne, barValue - barWidth, 0);
        
        GreenfootImage barImage = new GreenfootImage(barWidth + 4, barHeight + 4);
        barImage.setColor(backgroundColour);
        barImage.fill();
        barImage.setColor(textColour);
        barImage.drawRect(0, 0, barWidth + 3, barHeight +3);
        barImage.drawImage(imageTwo, 2, 2);
       
        // finding the sum of X values to create one large image with the three smaller images
        sumXValues = (2 * maximumX) + barImage.getWidth();
        maximumY = 0;
        
        // IF statement to find the largest Y so that value can be used to create one image with the same Y value
        if (featureImage.getHeight() > maximumY) 
        {
            maximumY = featureImage.getHeight();
        }
        
        if (barImage.getHeight() > maximumY) 
        {
            maximumY = barImage.getHeight();
        }
        
        if (valueImage.getHeight() > maximumY) 
        {
            maximumY = valueImage.getHeight();
        }
        
        // Creating one big image with the three smaller images (featureImage, barImage and valueImage)
        GreenfootImage image = new GreenfootImage(sumXValues, maximumY);
        image.setColor(backgroundColour);
        image.fill();
        
        image.drawImage(featureImage, maximumX - featureImage.getWidth(), (image.getHeight() - featureImage.getHeight()) / 2);
        image.drawImage(barImage, maximumX, (image.getHeight() - barImage.getHeight()) / 2);
        image.drawImage(valueImage, maximumX + barImage.getWidth(), (image.getHeight() - valueImage.getHeight()) / 2);
        
        // sets location according to offset - Mr Cohen's code
        if (targetActor != null && getWorld() != null){
            if (targetActor.getWorld() != null)
            {
                setLocation (targetActor.getX(), targetActor.getY() - offset);
            }
            else
            {
                getWorld().removeObject(this);
                isInWorld = false;
            }
        }
        
        // Sets the actual image
        setImage(image);
        
    }
    
    /**
     * @return whether the FeatureBar is in the world or not.
     */
    public boolean getIsInWorld()
    {
        return isInWorld;
    }
    
    /**
     * @return the number of acts left that the FeatureBar will stay in the world for.
     */
    public int getValue()
    {
        return value;
    }
    
    /**
     * @return the value in terms of Y coordinates from which the bar should be above from the center of the actor.
     */
    public int getOffset()
    {
        return offset;
    }
    
    /**
     * Updates whether to show units to the right of the bar or not
     */
    public void update(boolean unitShow)
    {
        showUnits = unitShow;
    }
    
    /**
     * Updates the colour of the inside portion of the bar.
     */
    public void update(Color barColour)
    {
        colourBar = barColour;
    }
}
