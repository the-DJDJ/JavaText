package org.beanplant.JavaText.user;

import org.beanplant.JavaText.world.Location;

import java.io.Serializable;

/**
 * A player object. This stores all of the information about a specific player.
 *
 * @author the_DJDJ
 */
public class Player implements Serializable {
    
    /** The name of the player. */
    private String name;
    
    /** The custom player properties. */
    private final Properties properties;
    
    /** The numerical player statistics. */
    private final Statistics statistics;
    
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
        this.properties = new Properties();
        this.statistics = new Statistics();
        
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
     * Returns the player's properties
     * 
     * @return the player's properties
     */
    public Properties getProperties() {
        
        return this.properties;
        
    }
    
    /**
     * Returns the player's statistics
     * 
     * @return the player's statistics
     */
    public Statistics getStatistics() {
        
        return this.statistics;
        
    }
    
}
