package BeanPlant.the_DJDJ.JavaText.util;

/**
 * A collection of simple static methods for string manipulation
 *
 * @author the_DJDJ
 */
public class StringTools {
    
    /**
     * A very basic method to capitalise the first letter of a string
     * 
     * @param string the string to capitalise
     * 
     * @return The same string, with the first letter capitalised
     */
    public static String capitaliseFirstLetter(String string){
        
        return string.substring(0, 1).toUpperCase() + string.substring(1);
        
    }
    
}
