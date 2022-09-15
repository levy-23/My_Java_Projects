import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Ending world, shows the user's final score as well as the high scores
 * 
 * @author Quinton Mak
 * @version June 2021
 */
public class GameOver extends World
{
    
    //private static UserInfo user;
    private QMTextbox scores;
    private ArrayList<UserInfo> highScoreUsers = new ArrayList<UserInfo>();
    private String[] highScores;
    private GreenfootSound bgm; 
    /**
     * Constructor for objects of class GameOver.
     * 
     * @param score the user's score for the just finished game (not high score)
     */
    public GameOver(int score)
    {    
        
        super(1000, 600, 1); 
        setBackground("background.png");
        highScoreUsers = (ArrayList) UserInfo.getTop(10); //Get the top players
        if(highScoreUsers != null) highScores = new String[highScoreUsers.size() + 3];//make array to put the top players
        else highScores = new String[3];
        highScores[0] = "HIGH SCORES";
        highScores[highScores.length - 2] = "--------------------------------------------------------------------------------------------------------------";
        for(int i = 1; i < highScores.length - 2; i++){
            highScores[i] = "#" + i + ": " + highScoreUsers.get(i-1).getUserName() + " - " + highScoreUsers.get(i-1).getScore() + " Pts"; //put top players into the array
        }
        if(UserInfo.isStorageAvailable()){
            UserInfo user = UserInfo.getMyInfo();
            //Display the user's score as well
            if(user != null){
                highScores[highScores.length - 1] = "Your score: " + score + " Pts.           High Score: " + user.getScore() + " Pts.            Rank: #" + user.getRank();
            }
            else{
                highScores[highScores.length - 1] = "Your score: " + score + " Pts. Please log in for high scores.";
            }
            
            
        }
        
        else{
            highScores[highScores.length - 1] = "Your score: " + score + " Pts. Please log in for high scores.";//If cannot get user's score, show this instead
        }
        //Create textbox showing the high scores
        scores = new QMTextbox(highScores, 1000, 600, null, Color.MAGENTA, null, new Font(false, false, 28));
        addObject(scores, 500, 300);
        
        scores.update(highScores[0], 1, Color.MAGENTA, new Font(true, false, 35));
        //scores.update(highScores[highScores.length - 1], highScores.length, null, new Font(true, false, 28));
        
        bgm = new GreenfootSound ("TheBoss.mp3");
    }
    
    public void act()
    {
        bgm.play(); 
    }
}
