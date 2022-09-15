import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author Tyler Simmonds (tylers) 
 * @author Jordan Cohen
 * @author Fatima Yahya
 * @version 1.01
 * 
 */
public class Video extends Actor
{
    // Instance variables
    int frame2 = 0;
    int frame3 = 0;
    int frame = 0;
    int frameH = 9;
    int frame2H = 100;
    int time = 0;
    boolean stop = false;
    
    /**
     * Act - do whatever the video wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {        
        // if not stopped
        if(!stop)
            try
            {
                // increase the frame at which you are currently at
                frame ++;
                String name = zeroAdder(frame, 4); // use zeroadder to make the same name format for the image name
                GreenfootImage image = new GreenfootImage (name+".jpg"); // create a new image with the above image format
                image.scale(1000, 600); // increase the size of the picture
                setImage(image); // set it as the image
            }
            catch (java.lang.IllegalArgumentException iae)
            {
                stop = true; // stop
        }


        if("space".equals(Greenfoot.getKey()))    // if space is clicked stop but if it is already stopped then unstop
        {
            if(!stop){
                stop = true; 
            }
            else
            {
                stop = false;
            }
        }

        getImage().scale(1000, 600); // increase the size of the image 
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
