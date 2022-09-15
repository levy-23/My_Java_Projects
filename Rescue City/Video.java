import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * 
 * @author tyler simmonds (tylers) 
 * @author jordan cohen
 * @author fatima yahya
 * @version 1.01
 * 
 */
public class Video extends Actor
{
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

        if(!stop)
            try
            {
                frame ++;
                String name = zeroAdder(frame, 4);
                GreenfootImage image = new GreenfootImage (name+".jpg");
                image.scale(1000, 600);
                setImage(image); 
            }
            catch (java.lang.IllegalArgumentException iae)
            {
                stop = true;
        }


        if("space".equals(Greenfoot.getKey()))    
        {
            if(!stop){
                stop = true;
            }
            else
            {
                stop = false;
            }
        }

        getImage().scale(1000, 600);
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
