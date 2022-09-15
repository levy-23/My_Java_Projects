import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Jail here.
 * 
 * @author (Fatima Yahya) 
 * @version (a version number or a date)
 */
public class Jail extends Buildings
{
    // Instance variables
    private GreenfootImage image;
    private GreenfootImage jailOnePerson = new GreenfootImage("jailOnePerson.png");
    private GreenfootImage jailTwoPerson = new GreenfootImage("jailTwoPerson.png");
    private GreenfootImage jailThreePerson = new GreenfootImage("jailThreePerson.png");
    private GreenfootImage jailFourPerson = new GreenfootImage("jailFourPerson.png"); // different images to set according to number of people in jail

    private int numInJail;
    /**
     * Basic Constructor for Jail - sets image and initial value for number of people in jail to 0
     */
    public Jail()
    {
        GreenfootImage image = new GreenfootImage("jail.png");
        numInJail = 0;
    }
    /**
     * Act - do whatever the Jail wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
        changeImage();
        // If the jail is full, then it "emptied" to allow more criminals to fit in.
        if (numInJail == 5)
        {
            numInJail = 1;
        }
    }    
    
    /**
     * Changes the image depending on the number of criminals in jail at the time
     */
    public void changeImage()
    {
        if (numInJail == 1)
        {
            this.setImage(jailOnePerson);
        }
        else if (numInJail == 2)
        {
            this.setImage(jailTwoPerson);
        }
        else if (numInJail == 3)
        {
            this.setImage(jailThreePerson);
        }
        else if (numInJail == 4)
        {
            this.setImage(jailThreePerson);
        }

    }
    
    /**
     * Gets the number of criminals in jail
     * @return The number of people in jail
     */
    public int getNoInJail()
    {
        return numInJail;
    }
    
    /**
     * Increase the number of criminals in jail by one
     */
    public void increaseNumInJail()
    {
         numInJail++;
    }
}
