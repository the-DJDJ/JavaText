package org.beanplant.JavaText.user;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Stores the user's unique properties. This is useful for if a user has a name
 * or any other custom properties applied.
 *
 * @author the_DJDJ
 */
public class Properties implements Serializable {
    
    /** The statistics map. */
    private final HashMap<String, Object> properties;
    
    /**
     * The default constructor. Instantiates a new properties HashMap
     */
    public Properties(){
        
        this.properties = new HashMap<>();
        
    }
    
    /**
     * The method that returns the entire set of properties unique to the
     * player.
     * 
     * @return the player properties
     */
    public HashMap<String, Object> getProperties() {
        
        return this.properties;
        
    }
    
    /**
     * A method that checks whether or not the specified property has been
     * defined for the player.
     * 
     * @param property the property to check
     * 
     * @return whether or not this property has been defined
     */
    public boolean hasProperty(String property) {
        
        return this.properties.containsKey(property);
        
    }
    
    /**
     * The method used to retrieve a specific property from the property set.
     * This is a generic method, so whatever type you've saved your property as,
     * you can retrieve it simply using one method.
     * 
     * @param <T> the type for the returning value
     * @param property the value to return
     * @param type the type for the returning value
     * 
     * @return the value of the property, in the specified format
     */
    public <T extends Object> T getProperty(String property, Class<T> type) {
        
        return type.cast(this.properties.get(property));
        
    }
    
    /**
     * The method used to add, update, or overwrite a property in the player's
     * property list.
     * 
     * @param property the property to add
     * @param value that property's value
     */
    public void setProperty(String property, Object value) {
        
        this.properties.put(property, value);
        
    }
    
    /**
     * Returns whether or not the player has any set properties.
     * 
     * @return Whether or not there are stored properties
     */
    public boolean isEmpty() {
        
        return this.properties.isEmpty();
        
    }
    
}
