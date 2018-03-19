package org.beanplant.JavaText.user;

import org.beanplant.JavaText.world.Location;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A player object. This stores all of the information about a specific player.
 *
 * @author the_DJDJ
 */
public class Player implements Serializable {
    
    /** The name of the player. */
    private String name;
    
    /** A hashmap of custom player properties. */
    private HashMap<String, Object> properties;
    
    /** The amount of health that the player has. */
    private int health;
    
    /** The player's current location in the world. */
    private Location location;
    
    /** The player's inventory. */
    private final Inventory inventory;
    
    /**
     * The default constructor. This assigns all of the default values and
     * instantiates a new player object.
     * 
     * @param name The name of the player
     * @param health The amount of health that the player has
     * @param location The current location of the player
     */
    public Player(String name, int health, Location location) {
        
        this.name = name;
        this.health = health;
        this.location = location;
        this.inventory = new Inventory();
        this.properties = new HashMap<>();
        
    }
    
    /**
     * Sets the name of the player, visible to other players.
     * 
     * @param name the new name of the player
     */
    public void setName(String name) {
        
        this.name = name;
        
    }
    
    /**
     * Returns the name of the player, visible to other players.
     * 
     * @return the name of the player
     */
    public String getName() {
        
        return this.name;
        
    }
    
    /**
     * Updates or sets the amount of health that the player has.
     * 
     * @param health the new amount of health that the player has.
     */
    public void setHealth(int health) {
        
        this.health = health;
        
    }
    
    /**
     * Returns the amount of health that the player currently has.
     * 
     * @return the amount of health the player has.
     */
    public int getHealth() {
        
        return this.health;
        
    }
    
    /**
     * Sets the current location of the player, useful when moving the player
     * around in the world.
     * 
     * @param location the new location of the player
     */
    public void setLocation(Location location) {
        
        this.location = location;
        
    }
    
    /**
     * Returns the current location of the player in the world.
     * 
     * @return the current location of the player
     */
    public Location getLocation() {
        
        return this.location;
        
    }
    
    /**
     * Returns the player's inventory
     * 
     * @return the player's inventory
     */
    public Inventory getInventory() {
        
        return this.inventory;
        
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
        
        return type.cast(this.properties.containsKey(property));
        
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
    
}
