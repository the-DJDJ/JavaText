package org.beanplant.JavaText.io;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * A width limited output stream provides a string printing method that will
 * automatically insert line breaks, wrapping text to the next line.
 *
 * @author the_DJDJ
 */
public class WidthLimitedOutputStream {
	
    /** The output stream. */
    private final PrintStream m_out;
    
    /** The width of the output stream. */
    private int width;
    
    /** The length of the printed output. */
    private int length;
    
    /** The constant for spaced printing above the line. */
    public final static int ABOVE = 0;
    
    /** The constant for spaced printing below the line. */
    public final static int BELOW = 1;
    
    /** The constant for spaced printing above and below the line. */
    public final static int BOTH = -1;

    /**
     * The default constructor. Gets an output stream, and the width with 
     * which to associate it.
     * 
     * @param out The OutputStream to use
     * @param width The width to use
     */
    public WidthLimitedOutputStream (OutputStream out, int width){

        m_out = new PrintStream (out);
        
        this.width = width;
        this.length = 0;

    }

    /**
     * The method that prints Strings whilst automatically inserting line
     * breaks
     * 
     * @param string The String to print.
     */
    public void print(String string){
        
        // Start at zero
        int currentWidth = 0;

        // Create a string tokenizer object
        StringTokenizer tokeniser = new StringTokenizer (string);

        // While words remain
        while (tokeniser.hasMoreTokens()){

            // Get the next token
            String token = tokeniser.nextToken() + " ";

            // If word would exceed width limit
            if (currentWidth + token.length() >= width || token.trim().equals("<token:newline>")
                                                       || token.trim().equals("<token:paragraph>")){

                // Print a newline
                println ();
                currentWidth = 0;

            }

            // Print token            
            if(token.trim().equals("<token:paragraph>")) {
                
                m_out.print("    ");
                
            } else if(!token.trim().equals("<token:newline>")) m_out.print(token);

            currentWidth += token.length();

        }

        m_out.flush();

    }

    /** string printing method that will automatically insert
    line breaks. The println version appends a newline. */
    /**
     * Prints the given String, whilst automatically appending line breaks
     * and moving to a new line once the String has ended.
     * 
     * @param string The String to print
     */
    public void println(String string){

        print(string);
        println();

    }

    /**
     * Prints a blank line.
     */
    public void println(){
        
        length++;
        m_out.println();

    }
    
    /**
     * Prints a specified character across the screen.
     * 
     * @param character The character to print
     */
    public void printAcross(String character){
        
        String sequence = "";
        
        do {
            
            sequence += character;
            
        } while (sequence.length() < this.width);
        
        println(sequence.substring(0, this.width));
        
    }
    
    /**
     * Prints a string, but with an open line above and below it.
     * 
     * @param string the string to print.
     * @param type where to put spaces
     */
    public void printSpaced(String string, int type){
        
        if((type == ABOVE) || (type == BOTH)) println();
        
        println(string);
        
        if((type == BELOW) || (type == BOTH)) println();
        
    }
    
    /**
     * A method to reset the length of what has been printed for clearing
     */
    public void reset() {
        
        this.length = 1;
        
    }
    
    /**
     * A method that clears what has already been printed to the console up to a
     * certain point
     */
    public void initialisePrint() {
        
        for (int i = 0; i < this.length; i++) {
            
            this.m_out.print(String.format("\033[%dA", 1));
            this.m_out.print("\033[2K");
            
        }
        
        this.m_out.flush();
        this.reset();
        
    }
    
    /**
     * Sets the width of the output stream to the specified amount
     * 
     * @param width the new width
     */
    public void setWidth(int width){
        
        this.width = width;
        
    }
    
    /**
     * A simple method that prints a bit of help on how to use JavaText to the
     * command line
     */
    public static void printHelp(){
        
        System.out.println("");
        System.out.println("JavaText, a simple, text based game engine written entirely in Java!");
        System.out.println("");
        System.out.println("Usage: java -jar JavaText.jar [help] [world] [output width]");
        System.out.println("");
        System.out.println("If you want to load a random level and start the game with the default configuration, use:");
        System.out.println("\tjava -jar JavaText.jar");
        System.out.println("If you want to load a specific level, but still start the game with the default configuration, use:");
        System.out.println("\tjava -jar JavaText.jar <\"World Name\">\t\tWhere \"World Name\" is the name of the level you wish to load");
        System.out.println("Alternatively, to use a custom output width, but load a random level, use:");
        System.out.println("\tjava -jar JavaText.jar <output width>\t\tWith the output width being maximum number of characters per line.");
        System.out.println("");
        System.out.println("Of course, these commands can be used in conjunction with one another! To load a specific level and use a custom output width, use:");
        System.out.println("\tjava -jar JavaText.jar <\"World Name\"> <output width>");
        System.out.println("");
        System.out.println("If you're at any time stuck, simply use:");
        System.out.println("\tjava -jar JavaText.jar help\t\t\t\tto show this help message (:");
        System.out.println("");
        System.out.println("");
        System.out.println("Still confused? Head over to https://github.com/the-DJDJ/JavaText and give me a shout!");
        System.out.println("\t- the_DJDJ");
        System.out.println("");
        
    }

}
