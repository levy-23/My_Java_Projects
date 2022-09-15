
/**
 * A collection of methods allowing for the conversion between grid coordinates (the index "coordinates" of an object in the 2d array, representing which square the object is in) 
 * and world coordinates (the coordinates of said object in the world, in pixels)
 *
 * @author Quinton Mak
 * @version June 2021
 */
public class GridConverter
{
    private static int unitLength = 50;//The length of each square in the grid, in (world) pixels
    /**
     * Converts between grid coordinates to world coordinates, assuming the location of the origin is retained
     * 
     * @param xGrid the first index of the object in the 2d grid array
     * @param yGrid the second index of the object in the 2d grid array
     * @returns The coordinates in the world as an array {x, y}
     */
    public static int[] gridToWorld(int xGrid, int yGrid){
        int[] world = new int[2];
        
        world[0] = (xGrid) * unitLength + unitLength/2;
        world[1] = (yGrid) * unitLength + unitLength/2;
        
        return world;
    }
    /**
     * Converts between world coordinates to grid coordinates, assuming the location of the origin is retained
     * 
     * @param xWorld the x coordinate of the object in the world
     * @param yWorld the x coordinate of the object in the world
     * @returns The coordinates in the grid as an array {x-index, y-index}
     */
    public static int[] worldToGrid(int xWorld, int yWorld){
        int[] grid = new int[2];
        
        grid[0] = (xWorld)/unitLength;
        grid[1] = (yWorld)/unitLength;
        
        return grid;
    }
    /**
     * Converts world coordinates to the coordinatees of the nearest square
     */
    public static int[] worldToSquareWorld(int xWorld, int yWorld){
        int[] coords = worldToGrid(xWorld, yWorld);
        coords = gridToWorld(coords[0], coords[1]);
        return coords;
    }
}
