package org.beanplant.JavaText.util;

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
     * @return The same string, with the first letter capitalised
     */
    public static String capitaliseFirstLetter(String string){
        
        return string.substring(0, 1).toUpperCase() + string.substring(1);
        
    }
    
    /**
     * A simple boolean that determines whether or not a string starts with a
     * vowel or not.
     * 
     * @param string the string to test
     * @return whether or not it begins with a vowel.
     */
    public static boolean startsWithVowel(String string){
        
        if(string.isEmpty()) return false;
        
        switch(string.toLowerCase().charAt(0)){
            
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
                
            default:
                return false;
            
        }
        
    }
    
}
