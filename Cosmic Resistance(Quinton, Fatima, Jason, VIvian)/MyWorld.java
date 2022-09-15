import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;
/**
 * MyWorld is where the main "action" of the game takes place. Users can place down towers in order to defend the base. But, enemies will travel down the path
 * and threaten to enter the base. The player must try to survive as long as they can, as the waves get progressively harder over time
 * 
 * Sounds Credit: https://mixkit.co/free-sound-effects/game/
 * 
 * @author Quinton Mak, Fatima Yahya, Levent Eren, Vivian Luo, Jason Wang
 * @version June 2021
 */
public class MyWorld extends World
{
    private Actor[][] grid; //THE GRID
    private ArrayList<PathPiece> paths; 
    private ArrayList<Tower> towers;
    private ArrayList<Button> buttons;
    
    //Static Variables
    
    private static int numSquaresX = 20;
    private static int numSquaresY = 10;
    private static int baseHP;
    private static int multiplier;
    private static int spawnRate;
    private static int score;
    private static int baseCount;
    
    //Instance variables
    private MouseInfo mouse;
    private int mouseButton;
    private Square s;//toggles over each grid square
    private Circle r;//shows the range of a tower to be placed
    private int[] coords;//array used to manage some coordinates
    
    
    private int[] mouseGridCoords, mouseSquareCoords; //respectively, the coordinates of the mouse in the grid, and the coordinates of the square the mouse is currently on
    private Button towerOneButton;
    private Button towerTwoButton;
    private Button towerThreeButton;
    private Button towerFourButton;
    private Button upgradeButton, sellButton, infoButton;
    
    private int enemyCounter;
    private boolean boss;
    
    private ValueBar moneyBar, scoreBar;
    private int money;
    private boolean upgraded;
    
    
    private QMTextbox wave;
    private ValueBar towerInfoOne, towerInfoTwo, towerInfoThree;
    
    private Counter baseCounter; 
    private Base base; 
    
    private GreenfootSound bgm; 
    /**
     * Changes the user's lives
     * 
     * @param change the amount the user's lives changes by (+ for increase, = for decrease)
     */
    public static void decreaseLives(int change){
        baseCount += change;
    }
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        grid = new Actor[numSquaresX][numSquaresY];//initialize grid
        money = 250;
        //Initialize textbox objects
        towerInfoOne = new ValueBar("Damage: ", 0);
        towerInfoTwo = new ValueBar("Cost: ", 0);
        towerInfoThree = new ValueBar("Upgrade Cost: ", 0);
        wave = new QMTextbox ("  WAVE 1 ", 150, 40, Color.YELLOW, Color.RED, null, new Font(true, false, 30), 0, 7);
        addObject(wave, 500, 50);
        //Create squares
        for(int i = 0; i < numSquaresX; i ++){
            for(int j = 0; j < numSquaresY; j++){
                coords = GridConverter.gridToWorld(i, j);
                if(/* End*/ ((j==4 && i==17) || (j==5 && i==17) || (j==5 && i==18)) || /* Entry to top row*/(j==3 && (i==2 || i==5 || i==8 || i==11 || i==14 || i==17)) || /* Top row*/ (j==2 && (i==2 || i==3 || i==4 || i==5 || i==8 || i==9 || i==10 || i==11 || i==14 || i==15 || i==16 || i==17) || /* Verticle*/((i==2 || i==5 || i ==8 || i ==11 || i ==14) && (j==4 || j==5 || j==6 || j==7)) || /* Bottom row*/(j==8 && (i==0 || i==1 || i == 2 || i==5 || i==6 || i==7 || i==8 || i==11 || i==12 || i==13 || i==14)))){
                    //grid[i][j] = new Square(50, true, false);
                    grid[i][j] = new PathPiece();
                    addObject(grid[i][j], coords[0], coords[1]);
                }
                else if(i==19 && (j==4 || j==5 || j==6)){
                    grid[i][j] = new Square(50, true, 1);
                    addObject(grid[i][j], coords[0], coords[1]);
                    
                }
                
            }
        }
        //Shows which grid user is hovering over, prepare the radius circle for user to know range of tower before placing it down
       
        r = new Circle(100);
        r.getImage().setTransparency(0);
        addObject(r, 25, 25);
        s = new Square(50, true, 0);
        s.getImage().setTransparency(0);
        addObject(s, 25, 25);
        //Add buttons (menu)
        towerOneButton = new Button(50, 50, "turret.png", new Color (214,163,59), new Color(0, 255, 0));
        addObject(towerOneButton, 50, 550);
        
        towerTwoButton = new Button(50, 50, "turretRocketPod.png", new Color (214,163,59), new Color(0, 255, 0));
        addObject(towerTwoButton, 120, 550);
        
        towerThreeButton = new Button(50, 50, "turret 3 button.png", new Color (214,163,59), new Color(0, 255, 0));
        addObject(towerThreeButton, 190, 550);
        
        towerFourButton = new Button(50, 50, "turret electric.png", new Color (214,163,59), new Color(0, 255, 0));
        addObject(towerFourButton, 260, 550);
        
        upgradeButton = new Button(50, 50, "upgradeArrow.png", new Color (214,163,59), new Color(0, 255, 0));
        addObject(upgradeButton, 330, 550);
        
        sellButton = new Button(50, 50, "selling icon.png", new Color (214,163,59), new Color(0, 255, 0));
        addObject(sellButton, 400, 550);
        
        infoButton = new Button(50, 50, "infoIcon.png", new Color (214,163,59), new Color(0, 255, 0));
        addObject(infoButton, 975, 25);
        grid[19][0] = infoButton;
        //Initialize instance variables
        mouseButton = 0;
        enemyCounter = 0;
        boss = false;
        
        
        moneyBar = new ValueBar("Money: ", money);
        addObject(moneyBar, 550, 530);
        
        scoreBar = new ValueBar("Score: ", 0);
        addObject(scoreBar, 550, 575);
        
        setPath();
        
        setPaintOrder(Button.class, StatBar.class, Enemy.class, Tower.class);
        
        setBackground("background.png");
        //Static Variables
        baseHP = 5;
        multiplier = 1;
        spawnRate = 3*60;
        score = 0;
        
        baseCount = 5; 
        baseCounter = new Counter ("Lives: "); 
        baseCounter.setValue(baseCount); 
        addObject(baseCounter, 950, 375); 
        
        base = new Base(); 
        addObject(base, 950, 210); 
        
        bgm = new GreenfootSound ("Roll.mp3"); 
        
        
    }
    
    public void act(){
        bgm.play(); 
        
        mouse = Greenfoot.getMouseInfo();
        
        
        checkIfPlaceTower();
        otherButtons();
        
        
        
        if(mouse != null){
            //Get where the mouse is in respect to the grid, and the coordinates of the square the mouse is in
            mouseGridCoords = GridConverter.worldToGrid(mouse.getX(), mouse.getY());
            mouseSquareCoords = GridConverter.worldToSquareWorld(mouse.getX(), mouse.getY());
                
            
            if(mouseGridCoords[1] < 10) {//if mouse on map, show square and set location of circle
                s.getImage().setTransparency(160);
                s.setLocation(mouseSquareCoords[0], mouseSquareCoords[1]);
                r.setLocation(mouseSquareCoords[0], mouseSquareCoords[1]);
                //r.getImage().setTransparency(85);
            }
            else {//else, make them transparent
                r.getImage().setTransparency(0);
                s.getImage().setTransparency(0);
            }
        }
        else{//if mouse is null, it is off the screen
            s.getImage().setTransparency(0);
            r.getImage().setTransparency(0);
        }
       
        
        
        
        //Update money and score
        moneyBar.update(money);
        scoreBar.update(score);
        spawnWaves();//spawn enemies
        
        //update lives
        baseCounter.setValue(baseCount); 
        
        //If user is dead
        if (baseCount <= 0)
        {
            if(UserInfo.isStorageAvailable()){//save the user's score
                UserInfo user = UserInfo.getMyInfo();
                if(user != null){    
                    if(score > user.getScore()){
                        
                        user.setScore(score);
                    }
                    
                    user.store();
                }
            }
            Greenfoot.setWorld (new GameOver(score));//go to game over world
            bgm.stop(); 
        }
        
    }
    /**
     * Spawn waves of enemies at predetermined intervals
     */
    public void spawnWaves(){
        enemyCounter++;
        //The logic is similar for all the if/else-if statements
        //wave 1
        if(enemyCounter <= 15*60){//If enemyCounter is in the right interval
            if(enemyCounter % spawnRate == 0){//If spawnRate acts has passed
                addObject(new StandardEnemy(multiplier), (1*50)-25, (9*50) - 25);//spawn enemy
                
                
            }
        }
        //wave 2
        else if(enemyCounter >= 18*60 && enemyCounter < 37*60){
            if(enemyCounter == 18*60) wave.update("  WAVE 2 ");//update wave
            if(enemyCounter % spawnRate == spawnRate/2){
                addObject(new FastEnemy(multiplier), (1*50)-25, (9*50) - 25);
            }
        }
        //wave 3
        else if(enemyCounter >= 40*60 && enemyCounter < 67*60){
            if(enemyCounter == 40*60) wave.update("  WAVE 3 ");
            if(enemyCounter % spawnRate == 0){
                addObject(new StandardEnemy(multiplier), (1*50)-25, (9*50) - 25);
                
                
            }
            if(enemyCounter % spawnRate == spawnRate/2){
                addObject(new FastEnemy(multiplier), (1*50)-25, (9*50) - 25);
            }
        }
        //wave 4
        else if(enemyCounter >= 70*60 && enemyCounter < 102*60){
            if(enemyCounter == 70*60) wave.update("  WAVE 4 ");
            if(enemyCounter % (2*spawnRate) == 0){
                addObject(new StandardEnemy(multiplier), (1*50)-25, (9*50) - 25);
            }
            if(enemyCounter % spawnRate == spawnRate/2){
                addObject(new ArmoredEnemy(multiplier), (1*50)-25, (9*50) - 25);
            }
        }
        //Wave 5
        else if(enemyCounter >= 105*60 && enemyCounter < 157*60){
            if(enemyCounter == 105*60) wave.update("  WAVE 5 ");
            if(enemyCounter % spawnRate == 0){
                addObject(new StandardEnemy(multiplier), (1*50)-25, (9*50) - 25);
            }
            if(enemyCounter % spawnRate == spawnRate/2){
                addObject(new FastEnemy(multiplier), (1*50)-25, (9*50) - 25);
            }
            if(enemyCounter % spawnRate == spawnRate/3){
                addObject(new ArmoredEnemy(multiplier), (1*50)-25, (9*50) - 25);
            }
        }
        //Wave 6
        else if(enemyCounter >= 160*60 && enemyCounter < 187*60){
            if(enemyCounter == 160*60) wave.update("  WAVE 6 ");
            if(enemyCounter % spawnRate == 0){
                addObject(new FlyingEnemy(multiplier), (1*50)-25, (6*50) - 25);
            } 
        }
        //Wave 7
        else if(enemyCounter >= 190*60 && enemyCounter < 247*60){
            if(enemyCounter == 190*60){
                spawnRate = 150;
                wave.update("  WAVE 7 ");
            }
            if(enemyCounter % spawnRate == 0){
                addObject(new StandardEnemy(multiplier), (1*50)-25, (9*50) - 25);
                
                
            }
            else if(enemyCounter % spawnRate == spawnRate/2 && enemyCounter >= 20*60){
                addObject(new FastEnemy(multiplier), (1*50)-25, (9*50) - 25);
            }
            else if(enemyCounter % spawnRate == spawnRate/4 && enemyCounter >= 80*60){
                addObject(new ArmoredEnemy(multiplier), (1*50)-25, (9*50) - 25);
            }
            else if(enemyCounter % (15*60) == 1){
                addObject(new FlyingEnemy(multiplier), (1*50)-25, (6*50) - 25);
                
            } 
        }
        //Wave 8 - BOSS
        else if(enemyCounter >= 250*60){
            if(enemyCounter == 250*60) wave.update("BOSS!", 150, 40, Color.YELLOW, Color.RED, null, new Font(true, false, 30), 28, 7);
            if(enemyCounter == 250*60){
                addObject(new BossEnemy(), (1*50)-25, (9*50) - 25);
                spawnRate = 2*60;
                boss = true;
            }
            if(boss){//IF a boss is added, don't spawn any more enemies
                boss = getObjects(BossEnemy.class).size() != 0;
                if(!boss) wave.update("ENDLESS!", 150, 40, Color.YELLOW, Color.RED, null, new Font(true, false, 30), 0, 7);
                else enemyCounter--;
            }
            //After boss, endless waves that get harder until player loses
            else{
                if(enemyCounter % (40*60) == 0){
                    multiplier++;//keep increasing hp until player loses
                }
                if(enemyCounter % spawnRate == 0){
                    addObject(new StandardEnemy(multiplier), (1*50)-25, (9*50) - 25);
                    
                    
                }
                else if(enemyCounter % spawnRate == spawnRate/2){
                    addObject(new FastEnemy(multiplier), (1*50)-25, (9*50) - 25);
                }
                else if(enemyCounter % spawnRate == spawnRate/4){
                    addObject(new ArmoredEnemy(multiplier), (1*50)-25, (9*50) - 25);
                }
                else if(enemyCounter % (8*60) == 1){
                    addObject(new FlyingEnemy(multiplier), (1*50)-25, (6*50) - 25);
                    
                } 
                if (enemyCounter == 550*60){//send in bosses if the player gets really far
                    addObject(new BossEnemy(2), (1*50)-25, (9*50) - 25);
                }
                else if(enemyCounter == 1000*60){
                    addObject(new BossEnemy(4), (1*50)-25, (9*50) - 25);
                }
            }
        }
        else{
            if(getObjects(Enemy.class).size() != 0) enemyCounter--; //wait until the current wave is completely finished before next wave starts
        }
    }
    /**
     * Checks if the tower buttons are pressed to see if and where the user wants to place a tower
     */
   public void checkIfPlaceTower(){
        //show info of tower to be placed 
        addObject(towerInfoOne, 800, 515);
        addObject(towerInfoTwo, 800, 550);
        addObject(towerInfoThree, 800, 585);
        if(towerOneButton.isPressed()){//similar logic for all 4 buttons
            deselectTowers();//deselect all towers, only the button is pressed
            r.updateRadius(120);//radius of tower one
            r.getImage().setTransparency(85);//set transparency of range circle
            towerInfoOne.update(12);//update base tower values to display
            towerInfoTwo.update(50);
            towerInfoThree.update(30);
            if(Greenfoot.mouseClicked(null) && (money - 50) >= 0){
                try{
                    if(grid[mouseGridCoords[0]][mouseGridCoords[1]] == null){//if there is an available space on the grid
                        TowerOne t = new TowerOne();
                        money = money - 50;
                        grid[mouseGridCoords[0]][mouseGridCoords[1]] = t;
                        addObject(t, mouseSquareCoords[0], mouseSquareCoords[1]);//add to proper place in the grid
                        towerOneButton.resetButton();
                        r.getImage().setTransparency(0);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    
                }
            }
        }
        
        else if(towerTwoButton.isPressed()){
            deselectTowers();
            r.updateRadius(120);
            r.getImage().setTransparency(85);
            towerInfoOne.update(30);
            towerInfoTwo.update(100);
            towerInfoThree.update(50);
            if(Greenfoot.mouseClicked(null) && (money - 100) >= 0){
                try{
                    if(grid[mouseGridCoords[0]][mouseGridCoords[1]] == null){
                        TowerTwo t = new TowerTwo();
                        money = money - 100;
                        grid[mouseGridCoords[0]][mouseGridCoords[1]] = t;
                        addObject(t, mouseSquareCoords[0], mouseSquareCoords[1]);
                        towerTwoButton.resetButton();
                        r.getImage().setTransparency(0);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    
                }
            }
        }

        else if(towerThreeButton.isPressed()){
            deselectTowers();
            r.updateRadius(150);
            r.getImage().setTransparency(85);
            towerInfoOne.update(50);
            towerInfoTwo.update(200);
            towerInfoThree.update(100);
            if(Greenfoot.mouseClicked(null) && (money - 200) >= 0){
                try{
                    if(grid[mouseGridCoords[0]][mouseGridCoords[1]] == null){
                        TowerThree t = new TowerThree();
                        money = money - 200;
                        grid[mouseGridCoords[0]][mouseGridCoords[1]] = t;
                        addObject(t, mouseSquareCoords[0], mouseSquareCoords[1]);
                        towerThreeButton.resetButton();
                        r.getImage().setTransparency(0);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    
                }
            }
        }
        
        else if(towerFourButton.isPressed()){
            deselectTowers();
            r.updateRadius(100);
            r.getImage().setTransparency(85);
            towerInfoOne.update(15);
            towerInfoTwo.update(200);
            towerInfoThree.update(100);
            if(Greenfoot.mouseClicked(null) && (money - 200) >= 0){
                try{
                    if(grid[mouseGridCoords[0]][mouseGridCoords[1]] == null){
                        TowerFour t = new TowerFour();
                        money = money - 200;
                        grid[mouseGridCoords[0]][mouseGridCoords[1]] = t;
                        addObject(t, mouseSquareCoords[0], mouseSquareCoords[1]);
                        towerThreeButton.resetButton();
                        r.getImage().setTransparency(0);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    
                }
            }
        }
        
        else{
            removeObject(towerInfoOne);
            removeObject(towerInfoTwo);
            removeObject(towerInfoThree);
            r.getImage().setTransparency(0);
        }
        
        
        
        
    }
   /**
    * Check the other buttons (upgrade, sell, info)
    */
   public void otherButtons(){
        
        if(upgradeButton.isPressed())
        {
            towers = (ArrayList) getObjects(Tower.class);//Get all towres
            Tower selectedTower = null;
            for(int i = 0; i < towers.size(); i++){
                if(towers.get(i).isSelected()) selectedTower = towers.get(i);//get the selected tower
            }
            
                try//upgrade the selected tower
                { 
                    if(Greenfoot.mouseClicked(null) && money >= selectedTower.getUpgradeCost() && selectedTower.getLevel() < 4){
                        money = money - selectedTower.getUpgradeCost(); 
                        selectedTower.upgrade();
                         
                        upgradeButton.resetButton();
                    }
                    else if(money < selectedTower.getUpgradeCost() || selectedTower.getLevel() >= 4){
                        upgradeButton.resetButton();
                    }
                }
                catch(NullPointerException e)//if no tower is selected, selectedTower is null
                {
                    upgradeButton.resetButton();
                }     
            
            
        }
        else if(sellButton.isPressed())//same logic as upgrade button
        {
            towers = (ArrayList) getObjects(Tower.class);
            Tower selectedTower = null;
            for(int i = 0; i < towers.size(); i++){
                if(towers.get(i).isSelected()) selectedTower = towers.get(i);
            }
            
                try
                { 
                    if(Greenfoot.mouseClicked(null)){
                        int selectedX = GridConverter.worldToGrid(selectedTower.getX(), selectedTower.getY())[0];
                        int selectedY = GridConverter.worldToGrid(selectedTower.getX(), selectedTower.getY())[1]; 
                        
                        selectedTower.sell();
                         
                        sellButton.resetButton();
                         
                        grid[selectedX][selectedY] = null;
                    }
                    
                }
                catch(NullPointerException e)
                {
                    sellButton.resetButton();
                }     
            
            
        }
        else if(Greenfoot.mouseClicked(infoButton) && infoButton.isPressed()){
            infoButton.resetButton();
            Greenfoot.setWorld(new InfoWorld(this));//go to info world to display info, while saving this world as a parameter
        }
    }
    /**
     * Deselects all towers
     */
    public void deselectTowers(){
        towers = (ArrayList) getObjects(Tower.class);
        for(Tower t : towers){
            t.setSelect(false);
        }
    }
    /**
     * Deselects all buttons
     */
    public void deselectButtons(){
        
        buttons = (ArrayList) getObjects(Button.class);
        for(Button b : buttons){
            b.resetButton();
        }
    }
    /**
     * Places all the pathpieces and initializes which pathpiece is next in line
     */
    public void setPath(){
        boolean up = true;
        int yIndex = 5;
        paths = new ArrayList<PathPiece>();
        //place them in the path's pattern
        for(int i = numSquaresX - 1; i >= 0; i --){
            if(grid[i][yIndex] instanceof PathPiece){
                paths.add((PathPiece) grid[i][yIndex]);
                if((i + 1) % 3 == 0){ //17, 14, 11, 8, 5, 2
                    if(up){
                        while(grid[i][yIndex - 1] instanceof PathPiece){
                            yIndex--;
                            paths.add((PathPiece) grid[i][yIndex]);
                        }
                        up = false;
                    }
                    else{
                        while(grid[i][yIndex + 1] instanceof PathPiece){
                            yIndex++;
                            paths.add((PathPiece) grid[i][yIndex]);
                        }
                        up = true;
                    }
                }
            }
            
        }
        //Since the pathPieces are in the arrayList from last to first, for each pathPiece, set the next one to be the one before
        for(int i = 1; i < paths.size(); i++){
            paths.get(i).setNext(paths.get(i-1));
        }
    }
    /**
     * @return the 2d array represnting the grid in the world
     */
    public Actor[][] getGrid(){
        return grid;
    }
    /**
     * @return the amount of money the user has
     */
    public int getMoney()
    {
        return money;
    }
    /**
     * @param val the new value of money
     */
    public void updateMoney(int val)
    {
        money = val;
    }
    /**
     * @param val the amount by how much money changes by
     */
    public void changeMoney(int val)
    {
        money += val;
    }
    /**
     * @param change the amount by which the score changes by
     */
    public static void changeScore(int change){
        score += change;
    }
    
    
}
