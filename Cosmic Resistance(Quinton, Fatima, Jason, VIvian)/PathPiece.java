import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A piece of the path, enemies can use this to determine where to go
 * 
 * @author Quinton Mak, Jordan Cohen
 * @version June 2021
 */
public class PathPiece extends Square
{
    private int myX, myY;
    private PathPiece next;
    
    
    public PathPiece(){
        super(50, "pathPiece.png");
    }
    /**
     * @param next the next piece in the path
     */
    public void setNext(PathPiece next){
        this.next = next;
    }
    
    public int getMyX(){
        return myX;
    }
    
    public int getMyY(){
        return myY;
    }
    /**
     * @return the next piece in the path
     */
    public PathPiece getNextPath(){
        return next;
    }
}
