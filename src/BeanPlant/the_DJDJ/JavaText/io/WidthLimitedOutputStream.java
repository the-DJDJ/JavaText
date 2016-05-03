package BeanPlant.the_DJDJ.JavaText.io;

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
    private final int width;

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
            String token = tokeniser.nextToken();

            // If word would exceed width limit
            if (currentWidth + token.length() >= width || token.equals("<token:newline>")){

                // Print a newline
                m_out.println ();
                currentWidth = 0;

            }

            // Print token
            if(!token.equals("<token:newline>")) m_out.print(token + " ");

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
        m_out.println();

    }

    /**
     * Prints a blank line.
     */
    public void println(){

        m_out.println();

    }
    
    /**
     * Prints a specified character across the screen.
     * 
     * @param character The character to print
     */
    public void printAccross(String character){
        
        m_out.println();
        
        for (int i = 0; i < this.width; i++) {
            
            m_out.print(character);
            
        }
        
        m_out.println();
        
    }

}
