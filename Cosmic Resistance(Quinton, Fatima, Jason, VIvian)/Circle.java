import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A circle used for shooting radius of towers
 * 
 * @author Quinton mak
 * @version (June 2021)
 */
public class Circle extends Actor
{
    // Delcare a greenfoot image 
    private GreenfootImage image;
    
    /**
     * Creates a circle with a radius. 
     * 
     * @param radius     Radius of the circle 
     */
    public Circle(int radius){
        image = new GreenfootImage(2*radius, 2*radius);
        image.setColor(Color.WHITE);
        image.fillOval(0, 0, 2*radius, 2*radius);
        image.setTransparency(85);
        
        setImage(image);
    }
    
    
    /**
     * Updates the radius of the circle. 
     * 
     * @param radius     Radius of the circle 
     */
    public void updateRadius(int radius){
        image.clear();
        image.scale(2*radius, 2*radius);
        image.setColor(Color.WHITE);
        image.fillOval(0, 0, 2*radius, 2*radius);
        image.setTransparency(85);
        setImage(image);
    }
}
