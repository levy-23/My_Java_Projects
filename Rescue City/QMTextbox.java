import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A container to display one or multiple lines of text. The textbox is a rectangular shape which contains text within it.
 * 
 * @author Quinton Mak
 * @version 4/6/2021
 */
public class QMTextbox extends Actor
{
    private GreenfootImage mainImage, textImage;
    private String text;
    private String[] textArray;
    private int width;
    private int height;
    private Color backgroundColor, textColor, borderColor;
    private Font font;
    private int xOffset, yOffset;
    
    private boolean sizeSet;
    
    public void act() 
    {
        // Add your action code here.
    }    
    
    
    
    /**
     * Displays a string at the top left corner of the box in default Greenfoot font (size 14 Sans Serif black text). 
     * Users may select the background color and the width and height of the textbox.
     * <p>
     * By default, the top left corner of the text is 6 pixels from the left edge of the textbox and 3 pixels from the top of the textbox.
     * 
     * @param text The string that is to be displayed
     * @param width The width of the textbox, in pixels
     * @param height The height of the textbox, in pixels
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     */
    public QMTextbox(String text, int width, int height, Color backgroundColor){ 
        this(text, width, height, backgroundColor, null);
        
    }
    
    /**
     * Displays a string at the top left corner of the box in default font of a certain color with a certain background color. 
     * Users may select the width and height of the textbox, background color, and text color. 
     * <p> By default, the top left corner of the text is offset 6 units right and 3 units down.
     * 
     * @param text The string that is to be displayed
     * @param width The width of the textbox, in pixels
     * @param height The height of the textbox, in pixels
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     * @param textColor The color of the text - passing null will set this to black
     * 
     */
    public QMTextbox(String text, int width, int height, Color backgroundColor, Color textColor){
        this(text, width, height, backgroundColor, textColor, null);
        
    }
    
    /**
     * Displays a string at the top left corner of the box in default font of a certain text color, background color and border color. 
     * Users may select the width and height of the textbox, background color, text color, and border color.  <p>
     * By default, the top left corner of the text is offset 6 units right and 3 units down.
     * 
     * @param text The string that is to be displayed
     * @param width The width of the textbox, in pixels
     * @param height The height of the textbox, in pixels
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     * @param textColor The color of the text - passing null will set this to black
     * @param borderColor The color of the textbox's border - passing null will make no border
     */
    public QMTextbox(String text, int width, int height, Color backgroundColor, Color textColor, Color borderColor){
        this(text, width, height, backgroundColor, textColor, borderColor, null);
        
    }
    /**
     * Displays a string at the top left corner of the box of a certain text color, background color and border color.
     * Users may select the width and height of the textbox, background color, text color, border color and the font.  <p>
     * By default, the top left corner of the text is offset 6 units right and 3 units down.
     * 
     * @param text The string that is to be displayed
     * @param width The width of the textbox, in pixels (greater than 0)
     * @param height The height of the textbox, in pixels (greater than 0)
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     * @param textColor The color of the text - passing null will set this to black
     * @param borderColor The color of the textbox's border - passing null will make no border
     * @param font The font of the text - passing null will set this to the default font (Sans Serif size 14)
     * 
     */
    public QMTextbox(String text, int width, int height, Color backgroundColor, Color textColor, Color borderColor, Font font){
        this(text, width, height, backgroundColor, textColor, borderColor, font, 3, 3);
        
    }
    /**
     * Displays a string at the top left corner of the textbox, allowing the user to select the width, height, background color, text color, border color, font, and text offsets. 
     * 
     * @param text The string that is to be displayed
     * @param width The width of the textbox, in pixels (greater than 0)
     * @param height The height of the textbox, in pixels (greater than 0)
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     * @param textColor The color of the text - passing null will set this to black
     * @param borderColor The color of the textbox's border - passing null will make no border
     * @param font The font of the text - passing null will set this to the default font
     * @param xOffset The number of pixels the top left corner of the text is positioned from the left edge of the textbox (greater than 0)
     * @param yOffset The number of pixels the top left corner of the text is positioned from the top of the textbox (greater than 0)
     * 
     * 
     */
    public QMTextbox(String text, int width, int height, Color backgroundColor, Color textColor, Color borderColor, Font font, int xOffset, int yOffset){
        //Initialize values
        textArray = new String[1];
        textArray[0] = text;
        
        this.text = text;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.borderColor = borderColor;
        this.font = font;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        
        
        
        //Set main box image - this will act as the container
        mainImage = new GreenfootImage(width, height);
        
        //Set background color
        if(backgroundColor != null){
            mainImage.setColor(backgroundColor);
            mainImage.fill();
        }
        
        //Set default text color and font, if null
        if(textColor == null) textColor = Color.BLACK;
        
        if(font == null) font = new Font(false, false, 14);
        
        //Create text image
        textImage = new GreenfootImage(width, height);
        textImage.setFont(font);
        textImage.setColor(textColor);
        textImage.drawString(text, 0, (int) (0.8*font.getSize()));//Center the text vertically
        
        //Draw text image onto text box
        mainImage.drawImage(textImage, xOffset, yOffset);
        
        //Draw border
        if(borderColor != null){
            mainImage.setColor(borderColor);
            mainImage.drawRect(0, 0, width - 1, height - 1);
        }
        
        setImage(mainImage);
        
    }
    
    /**
     * Displays multiple lines of text left justified, in default Greenfoot font (size 14 Sans Serif black text). 
     * Users may select the width and height of the textbox and the background color.
     * <p>
     * By default, the top left corner of the text is 6 pixels from the left edge of the textbox and 3 pixels from the top of the textbox.
     * 
     * @param textArray An array of strings, with each element being a new line of text. 
     * @param width The width of the textbox, in pixels
     * @param height The height of the textbox, in pixels
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     */
    public QMTextbox(String[] textArray, int width, int height, Color backgroundColor){ 
        this(textArray, width, height, backgroundColor, null);
        
    }
    
    /**
     * Displays multiple lines of text left justified in default Greenfoot font.
     * Users may select the width and height of the textbox, background color, and text color. 
     * <p> By default, the top left corner of the text is offset 6 units right and 3 units down.
     * 
     * @param textArray An array of strings, with each element being a new line of text. 
     * @param width The width of the textbox, in pixels
     * @param height The height of the textbox, in pixels
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     * @param textColor The color of the text - passing null will set this to black
     * 
     */
    public QMTextbox(String[] textArray, int width, int height, Color backgroundColor, Color textColor){
        this(textArray, width, height, backgroundColor, textColor, null);
        
    }
    
    /**
     * Displays multiple lines of text left justified in default Greenfoot font.
     * Users may select the width and height of the textbox, background color, text color, and border color. <p>
     * By default, the top left corner of the text is offset 6 units right and 3 units down.
     * 
     * @param textArray An array of strings, with each element being a new line of text. 
     * @param width The width of the textbox, in pixels
     * @param height The height of the textbox, in pixels
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     * @param textColor The color of the text - passing null will set this to black
     * @param borderColor The color of the textbox's border - passing null will make no border
     */
    public QMTextbox(String[] textArray, int width, int height, Color backgroundColor, Color textColor, Color borderColor){
        this(textArray, width, height, backgroundColor, textColor, borderColor, null);
        
    }
    /**
     * Displays multiple lines of text left justified,
     * allowing the user to select the width and height of the textbox, background color, text color, border color and the font. <p>
     * By default, the top left corner of the text is offset 6 units right and 3 units down.
     * 
     * @param text The string that is to be displayed
     * @param width The width of the textbox, in pixels (greater than 0)
     * @param height The height of the textbox, in pixels (greater than 0)
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     * @param textColor The color of the text - passing null will set this to black
     * @param borderColor The color of the textbox's border - passing null will make no border
     * @param font The font of the text - passing null will set this to the default font (Sans Serif size 14)
     * 
     */
    public QMTextbox(String[] textArray, int width, int height, Color backgroundColor, Color textColor, Color borderColor, Font font){
        this(textArray, width, height, backgroundColor, textColor, borderColor, font, 3, 3);
        
    }
    /**
     * 
     * Displays multiple lines of text left justified, allowing the user to select the width, height, background color, text color, border color, font, and text offsets. 
     * 
     * @param textArray An array of strings, with each element being a new line of text. 
     * @param width The width of the textbox, in pixels (greater than 0)
     * @param height The height of the textbox, in pixels (greater than 0)
     * @param backgroundColor The background color of the textbox - passing null will set this to transparent
     * @param textColor The color of the text - passing null will set this to black
     * @param borderColor The color of the textbox's border - passing null will make no border
     * @param font The font of the text - passing null will set this to the default font
     * @param xOffset The number of pixels the top left corner of the text is positioned from the left edge of the textbox (greater than 0)
     * @param yOffset The number of pixels the top left corner of the text is positioned from the top of the textbox (greater than 0)
     * 
     */
    public QMTextbox(String[] textArray, int width, int height, Color backgroundColor, Color textColor, Color borderColor, Font font, int xOffset, int yOffset){
        //Most of this is the same as the single line constructor, so comments will be limited
        
        //Initialize values
        this.textArray = textArray;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.borderColor = borderColor;
        this.font = font;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        
        mainImage = new GreenfootImage(width, height);
        if(backgroundColor != null){
            mainImage.setColor(backgroundColor);
            mainImage.fill();
        }
        if(textColor == null) textColor = Color.BLACK;
        
        
        if(font == null) font = new Font(false, false, 14);

        textImage = new GreenfootImage(width, height);
        textImage.setFont(font);    
        textImage.setColor(textColor);
        
        //Iterate through the text array, drawing each string on a new line
        for(int i = 0; i < textArray.length; i++){
            textImage.drawString(textArray[i], 0, (int) ( (0.8+i)*font.getSize() ) ); //Center the text vertically
        }
        
        mainImage.drawImage(textImage, xOffset, yOffset);
        if(borderColor != null){
            mainImage.setColor(borderColor);
            mainImage.drawRect(0, 0, width - 1, height - 1);
        }
        setImage(mainImage);
        
        
    }
    
    /*
     *Update Methods! 
     */
   
    /**
     * Adds text onto the end of the first line of preexisting text, keeping all other properties the same
     * 
     * @param addText The text that is added onto the existing text
     */
    public void append(String addText){
        textArray[0] += addText;
        update(textArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    /**
     * Changes the textbox's width and height
     * 
     * @param width The new width of the textbox, in pixels (greater than 0)
     * @param height The new height of the textbox, in pixels (greater than 0)
     */
    public void update(int width, int height){
        update(textArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Changes the font and the text color
     * 
     * @param textColor The new text color - passing null will set this to black
     * @param font The new font of the text - passing null will set this to the default font
     */
    public void update(Color textColor, Font font){
        update(textArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Updates the color of the background, text, and border.
     * 
     * 
     * @param backgroundColor The new background color of the textbox - passing null will set this to transparent
     * @param textColor The new text color - passing null will set this to black
     * @param borderColor The new border color - passing null will make no border
     */
    public void update(Color backgroundColor, Color textColor, Color borderColor){
        update(textArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Completely replaces the current text in the box, keeping all other properties the same
     * 
     * @param updateText The new string that is to be displayed.
     */
    
    public void update(String updateText){
        update(updateText, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Completely replaces the current text in the box. Allows the user to update the text's font and color
     * 
     * @param updateText The new string that is to be displayed.
     * @param textColor The new text color - passing null will set this to black
     * @param font The new font of the text - passing null will set this to the default font
     */
    public void update(String updateText, Color textColor, Font font){
        update(updateText, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Completely replaces the current text in the box. Allows the user to update the width and height, font, and text color
     * 
     * @param updateText The new string that is to be displayed.
     * @param width The new width of the textbox, in pixels (greater than 0)
     * @param height The new height of the textbox, in pixels (greater than 0)
     * @param textColor The new text color - passing null will set this to black
     * @param font The new font of the text - passing null will set this to the default font
     */
    public void update(String updateText, int width, int height, Color textColor, Font font){
        update(updateText, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Completely replaces the current text in the box. <p>
     * Allows the user to update the width, height, background color, text color, border color, font, and text offsets
     * 
     * @param updateText The new string that is to be displayed
     * @param width The new width of the textbox, in pixels (greater than 0)
     * @param height The new height of the textbox, in pixels (greater than 0)
     * @param backgroundColor The new background color of the textbox - passing null will set this to transparent
     * @param textColor The new text color - passing null will set this to black
     * @param borderColor The new border color - passing null will make no border
     * @param font The new font of the text - passing null will set this to the default font
     * @param xOffset The number of pixels the top left corner of the text is positioned from the left edge of the textbox (greater than 0)
     * @param yOffset The number of pixels the top left corner of the text is positioned from the top of the textbox (greater than 0)
     */
    public void update(String updateText, int width, int height, Color backgroundColor, Color textColor, Color borderColor, Font font, int xOffset, int yOffset){
        text = updateText;
        textArray = new String[1];//Make textArray an array of length 1, that contains the updateText in it
        textArray[0] = updateText;
        update(textArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
        
    }
    
    /**
     * Completely replaces one line of text, keeping all other properties the same
     * 
     * @param updateText The new text to be displayed
     * @param lineNum The line of text to be changed. lineNum = 1 is the first line. Should be between 1 and the total number of lines in the textbox
     */
    public void update(String updateText, int lineNum){
        textArray[lineNum - 1] = updateText;
        update(textArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Completely replaces one line of text. Allows the user to update the font and text color.
     * 
     * @param updateText The new text to be displayed
     * @param lineNum The line of text to be changed. lineNum = 1 is the first line. Should be between 1 and the total number of lines in the textbox
     * @param textColor The new text color - passing null will set this to black
     * @param font The new font of the text - passing null will set this to the default font
     */
    public void update(String updateText, int lineNum, Color textColor, Font font){
        textArray[lineNum - 1] = updateText;
        update(textArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Completely replaces one line of text. Allows the user to update the width, height, font and text color.
     * 
     * @param updateText The new text to be displayed
     * @param lineNum The line of text to be changed. lineNum = 1 is the first line. Should be between 1 and the total number of lines in the textbox
     * @param width The new width of the textbox, in pixels (greater than 0)
     * @param height The new height of the textbox, in pixels (greater than 0)
     * @param textColor The new text color - passing null will set this to black
     * @param font The new font of the text - passing null will set this to the default font
     */
    public void update(String updateText, int lineNum, int width, int height, Color textColor, Font font){
        textArray[lineNum - 1] = updateText;
        update(textArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Completely replaces one line of text. <p>
     * Allows the user to update the width, height, background color, text color, border color, font, and text offsets
     * 
     * @param updateText The new string that is to be displayed
     * @param lineNum The line of text to be changed. lineNum = 1 is the first line. Should be between 1 and the total number of lines in the textbox
     * @param width The new width of the textbox, in pixels (greater than 0)
     * @param height The new height of the textbox, in pixels (greater than 0)
     * @param backgroundColor The new background color of the textbox - passing null will set this to transparent
     * @param textColor The new text color - passing null will set this to black
     * @param borderColor The new border color - passing null will make no border
     * @param font The new font of the text - passing null will set this to the default font
     * @param xOffset The number of pixels the top left corner of the text is positioned from the left edge of the textbox (greater than 0)
     * @param yOffset The number of pixels the top left corner of the text is positioned from the top of the textbox (greater than 0)
     */
    public void update(String updateText, int lineNum, int width, int height, Color backgroundColor, Color textColor, Color borderColor, Font font, int xOffset, int yOffset){
        textArray[lineNum - 1] = updateText;
        update(textArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
        
    }
    
    /**
     * Completely replaces all lines of text, keeping all other properties the same
     * 
     * @param updateArray An array of strings, which is the new text to be displayed, with each element being a new line of text.
     */
    public void update(String[] updateArray){
       update(updateArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
     
    /**
     * Completely replaces all lines of text. Allows the user to update the font and text color.
     * 
     * @param updateArray An array of strings, which is the new text to be displayed, with each element being a new line of text.
     * @param textColor The new text color - passing null will set this to black
     * @param font The new font of the text - passing null will set this to the default font
     */
    public void update(String[] updateArray, Color textColor, Font font){
        update(updateArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Completely replaces all lines of text.  Allows the user to update the width, height, font, and text color
     * 
     * @param updateArray An array of strings, which is the new text to be displayed, with each element being a new line of text.
     * @param width The new width of the textbox, in pixels (greater than 0)
     * @param height The new height of the textbox, in pixels (greater than 0)
     * @param textColor The new text color - passing null will set this to black
     * @param font The new font of the text - passing null will set this to the default font
     */
    public void update(String[] updateArray, int width, int height, Color textColor, Font font){
       update(updateArray, width, height, backgroundColor, textColor, borderColor, font, xOffset, yOffset);
    }
    
    /**
     * Completely replaces all lines of text. <p>
     * Allows the user to update the width, height, background color, text color, border color, font, and text offsets
     * 
     * @param updateArray An array of strings, which is the new text to be displayed, with each element being a new line of text. 
     * @param width The new width of the textbox, in pixels (greater than 0)
     * @param height The new height of the textbox, in pixels (greater than 0)
     * @param backgroundColor The new background color of the textbox - passing null will set this to transparent
     * @param textColor The new text color - passing null will set this to black
     * @param borderColor The new border color - passing null will make no border
     * @param font The new font of the text - passing null will set this to the default font
     * @param xOffset The number of pixels the top left corner of the text is positioned from the left edge of the textbox (greater than 0)
     * @param yOffset The number of pixels the top left corner of the text is positioned from the top of the textbox (greater than 0)
     */
    
    public void update(String[] updateArray, int width, int height, Color backgroundColor, Color textColor, Color borderColor, Font font, int xOffset, int yOffset){
        //Initialize values
        this.textArray = updateArray;
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.borderColor = borderColor;
        this.font = font;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        
        //Clear and resize the main image
        mainImage.clear();
        mainImage.scale(width, height); 
        
        //Set new color values and font
        if(backgroundColor != null){
            mainImage.setColor(backgroundColor);
            mainImage.fill();
        }
        if(textColor == null) textColor = Color.BLACK;
        
        
        
        if(font == null) font = new Font(false, false, 14);
        
        textImage = new GreenfootImage(width, height);
        textImage.setFont(font);    
        textImage.setColor(textColor);
        
        //Create new text image
        for(int i = 0; i < textArray.length; i++){
            textImage.drawString(textArray[i], 0, (int) ( (0.8+i)*font.getSize() ) ); //Center the text vertically
        }
        
        //Draw new text and border
        mainImage.drawImage(textImage, xOffset, yOffset);
        if(borderColor != null){
            mainImage.setColor(borderColor);
            mainImage.drawRect(0, 0, width - 1, height - 1);
        }
        setImage(mainImage);
        
        
    }
    /**
     * Gets the text currently displayed in the textbox
     * 
     * @return textArray The text currently displayed, with each element being a new line of text. 
     */
    public String[] getText(){
        return textArray;
    }
    /**
     * Returns the width of the textbox
     * 
     * @return width The width of the textbox, in pixels
     */
    public int getWidth(){
        return width;
    }
    /**
     * Returns the height of the textbox
     * 
     * @return height The height of the textbox, in pixels
     */
    public int getHeight(){
        return height;
    }
}
