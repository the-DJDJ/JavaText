package org.beanplant.JavaText.user;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Stores the user's statistics. This is used to store information about how the
 * player is progressing through the game,
 *
 * @author the_DJDJ
 */
public class Statistics implements Serializable {
    
    /** The statistics map. */
    private final HashMap<String, Integer> statistics;
    
    /**
     * The default constructor. Instantiates a new statistics HashMap
     */
    public Statistics(){
        
        this.statistics = new HashMap<>();
        
    }
    
    /**
     * A method that checks whether or not the specified statistic has been
     * defined for the player.
     * 
     * @param statistic the statistic to check
     * 
     * @return whether or not this statistic has been defined
     */
    public boolean contains(String statistic) {
        
        return this.statistics.containsKey(statistic);
        
    }
    
    /**
     * The method used to retrieve a specific statistic from the statistic set.
     * 
     * @param statistic the value to return
     * 
     * @return the value of the statistic, in the specified format
     */
    public int get(String statistic) {
        
        return this.statistics.get(statistic);
        
    }
    
    /**
     * The method used to add, update, or overwrite a statistic in the player's
     * stats list.
     * 
     * @param statistic the statistic to add
     * @param value that statistic's value
     */
    public void set(String statistic, int value) {
        
        this.statistics.put(statistic, value);
        
    }
    
    /**
     * A method which quickly updates a statistic without the need for too much
     * boilerplate code
     * 
     * @param statistic the statistic to update
     * @param value the value to update the statistic by
     */
    public void increase(String statistic, int value) {
                
        this.set(statistic, this.get(statistic) + value);
        
    }
    
    /**
     * Returns whether or not the player has any set statistcs.
     * 
     * @return Whether or not there are stored statistics
     */
    public boolean isEmpty() {
        
        return this.statistics.isEmpty();
        
    }
    
    @Override
    public String toString(){
        
        String string = new String();
        
        for (String key : this.statistics.keySet()) {
            
            string += String.format("%-4dx %s", this.statistics.get(key), key) + " <token:newline> ";
            
        }
        
        return string;
        
    }
    
}
