import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A square
 * 
 * @author Quinton Mak
 * @version June 2021
 */
public class Square extends Actor
{
    GreenfootImage image;
    /**
     * Act - do whatever the Square wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    /**
     * Makes a square 
     * @param sideLength the sideLength of the square in pixels (>= 0)
     * @param imageName the image inside the square
     */
    public Square(int sideLength, String imageName){
        image = new GreenfootImage(sideLength, sideLength);
        image.drawImage(new GreenfootImage(imageName), 0, 0);
        setImage(image);
    }
    /**
     * Makes a square 
     * @param sideLength the sideLength of the square in pixels (>= 0)
     * @param fill whether to fill the square of not
     * @param background whether to use the background color or not
     */
    public Square (int sideLength, boolean fill, boolean background){
        image = new GreenfootImage(sideLength, sideLength);
        
        Color black = new Color(255,255,255);
        Color bckg = new Color(37,150,33);
        Color mse = new Color(177,3,252);
        Color pth = new Color(217, 188, 46);
        image = new GreenfootImage(sideLength-1, sideLength-1);
        // for(int i =1; i <= 1; i++){
            // image.drawRect(0, 0, sideLength -i , sideLength - i);
        // }
        image.drawRect(0, 0, sideLength , sideLength);
        if(fill){
            if(background){
                image.setColor(bckg);
            }
            else{
                image.setColor(pth);
            }
            image.fill();
        }
        
        setImage(image);
    }
    /**
     * Makes a square 
     * @param sideLength the sideLength of the square in pixels (>= 0)
     * @param fill whether to fill the square or not
     * @param test 0 or 1 to determine which color to use
     */
    public Square (int sideLength, boolean fill, int test){
        image = new GreenfootImage(sideLength, sideLength);
        
        Color black = new Color(255,255,255);
        Color bckg = new Color(37,150,33);
        Color mse = new Color(153, 155, 163, 160);
        Color pth = new Color(217, 188, 46);
        Color nd = new Color(217, 46, 97);
        
        image = new GreenfootImage(sideLength, sideLength);
        // for(int i =1; i <= 1; i++){
            // image.drawRect(0, 0, sideLength -i , sideLength - i);
        // }
        image.drawRect(0, 0, sideLength , sideLength);
        if(fill){
            if(test==0){
                image.setColor(mse);
            }
            else if(test ==1){
                image.setColor(nd);
            }
            image.fill();
        }
        
        setImage(image);
    }
}
