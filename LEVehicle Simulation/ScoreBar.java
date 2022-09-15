import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
 // Need Color and Font for drawing ScoreBar



/**
 * ScoreBar is a Greenfoot Actor that displays text. 
 * The text is white on a reddish brown
 * background and designed to work in a scenario that is
 * 800x600 pixels. 
 * <p>
 * Display any String that fits, or send
 * game info straight to customized method.
 * <p>
 * Edited by Levent for this specific scenerio. Credits to: Jordan Cohen.
 * @author Jordan Cohen
 * @version April 2013
 */
public class ScoreBar extends Actor
{
    // Declare Objects
    private GreenfootImage scoreBoard;
    private Color background;
    private Color foreground;
    private Font textFont;
    private String text;

    // Declare Variables:
    private int width;
    /**
     * Construct a ScoreBar of the appropriate size.
     * 
     * @param width     width of the World where the
     *                  ScoreBar will be placed
     */
    public ScoreBar (int width)
    {
        //Change slightly for my specific scenerio
        scoreBoard = new GreenfootImage (width, 30);
        background = new Color (255, 75, 20);
        foreground = new Color (255, 255, 255);
        textFont = new Font ("Courier New", 24);
        scoreBoard.setColor(background);
        scoreBoard.fill();
        this.setImage (scoreBoard);
        scoreBoard.setFont(textFont);

        this.width = width;
    } 
    
    public ScoreBar (int width, int c11, int c12, int c13, int size)
    {
        //Change slightly for my specific scenerio
        scoreBoard = new GreenfootImage (width, 30);
        background = new Color (c11, c12, c13);
        foreground = new Color (255, 255, 255);
        textFont = new Font ("Arial", size);
        scoreBoard.setColor(background);
        scoreBoard.fill();
        this.setImage (scoreBoard);
        scoreBoard.setFont(textFont);

        this.width = width;
    }

    /**
     * Updates this ScoreBar with game stats. This method should be
     * re-written to work with your specific labels/values (I edited this methode to fit my assignment)
     * 
     * @param protesters     current number of protesters arrived safely
     * 
     */
    public void update (String line, int protesters)
    {
        // In order to make uniform sizes and preceding zeros:
        String protestersString;
        // If there is only one digit

        protestersString = zeroAdder (protesters, 1);
        

        
        text = "Protesters: " + protestersString;
        // Now that we have built the text to output...
        // this.update (String) calls the other version of update(), in this case
        // update(String) - see below
        this.update (text);
    }

    /**
     * Takes a String and displays it centered to the screen.
     * 
     * @param output    Text for displaying. 
     */
    public void update (String output)
    {
        // Refill the background with background color
        scoreBoard.setColor(background);
        scoreBoard.fill();

        // Write text over the solid background
        scoreBoard.setColor(foreground);  
        // Smart piece of code that centers text
        int centeredY = (width/2) - ((output.length() * 14)/2);
        // Draw the text onto the image
        scoreBoard.drawString(output, centeredY, 22);
    }

    /**
     * Method that aids in the appearance of the scoreboard by generating
     * Strings that fill in zeros before the score. For example:
     * 
     * 27 ===> to 5 digits ===> 00027
     * 
     * @param   value   integer value to use for score output
     * @param   digits   number of zeros desired in the return String
     * @return  String  built score, ready for display
     */
    public static String zeroAdder (int value, int digits)
    {
        // Figure out how many digits the number is
        int numDigits = digitCounter(value);

        // If not extra digits are needed
        if (numDigits >= digits)
            return Integer.toString(value);

        else // Build the number with zeroes for extra place values:
        {
            String zeroes = "";
            for (int i = 0; i < (digits - numDigits); i++)
            {
                zeroes += "0";
            }
            return (zeroes + value);
        }

    }
    
    /**
     * Useful private method that counts the digit in any integer.
     * 
     * @param number    The number whose digits you want to count
     * @return  int     The number of digits in the given number
     */
    private static int digitCounter (int number)
    {
        if (number < 10) {
            return 1;
        }
        int count = 0;
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count;
    }

}
