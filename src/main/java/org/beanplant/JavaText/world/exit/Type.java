package org.beanplant.JavaText.world.exit;

import org.beanplant.JavaText.user.Item;

import java.util.Arrays;
import java.util.List;

/**
 * The enum type for what kind of exit it is.
 *
 * @author the_DJDJ
 */
public enum Type {
    
    UNDEFINED("UNDEFINED", "You cannot go this way.", "The way is already clear.", "You have cleared the way.", null),
    PATHWAY("Pathway", "The pathway appears to be blocked.", "The pathway is already clear", "The pathway is now clear.", null),
    DOOR("Door", "The door appears to be locked.", "The door isn't locked.",  "You unlocked the door.", Arrays.asList(Item.key, Item.crowbar)),
    GATE("Gate", "The gate appears to be locked.", "The gate isn't locked. ", "You unlocked the gate", Arrays.asList(Item.key)),
    WINDOW("Window", "The window appears to be closed.", "The window isn't closed.", "You pried the window open.", Arrays.asList(Item.crowbar, Item.rock)),
    LADDER("Ladder", "The ladder's steps are broken.", "The ladder isn't broken", "You repaired the ladder's steps.", Arrays.asList(Item.stick)),
    ROPE("Rope", "The rope is frayed, and won't support your weight.", "The rope doesn't need replacing", "You replaced the rope.", Arrays.asList(Item.rope));
    
    /** The name of this type of exit. */
    private String name;
    
    /** The message to display if this type of exit is locked. */
    private String lockedMessage;
    
    /** The message to display if this type of exit is already unlocked. */
    private String unlockedMessage;
    
    /** The message to display when the user unlocks this type of exit. */
    private String unlockMessage;
    
    /** The item needed to unlock this type of exit. */
    private List<Item> key;
    
    /**
     * The constructor. Creates a new type of exit, and assigns messages to it.
     * 
     * @param name The name of this type of exit.
     * @param lockedMessage The message to display if this type of exit is locked.
     * @param unlockedMessage The message to display if this type of exit is already unlocked.
     * @param unlockMessage The message to display when the user unlocks this type of exit.
     * @param key The item needed to unlock this type of exit
     */
    Type(String name, String lockedMessage, String unlockedMessage, String unlockMessage, List<Item> key){
        
        this.name = name;
        this.lockedMessage = lockedMessage;
        this.unlockedMessage = unlockedMessage;
        this.unlockMessage = unlockMessage;
        this.key = key;
        
    }
    
    /**
     * Sets a new name for this type of exit.
     * 
     * @param name the new name
     */
    public void setName(String name){
        
        this.name = name;
        
    }
    
    /**
     * Returns the name of this type of exit.
     * 
     * @return The name of this type of exit.
     */
    public String getName(){
        
        return this.name;
        
    }
    
    /**
     * Sets the message to display if this type of exit is locked.
     * 
     * @param lockedMessage the message to display if this type of exit is locked.
     */
    public void setLockedMessage(String lockedMessage){
        
        this.lockedMessage = lockedMessage;
        
    }
    
    /**
     * Returns the message to display if this type of exit is locked.
     * 
     * @return the message to display if this type of exit is locked.
     */
    public String getLockedMessage(){
        
        return this.lockedMessage;
        
    }
    
    /**
     * Sets the message to display if this type of exit is already unlocked.
     * 
     * @param unlockedMessage the message to display if this type of exit is already unlocked.
     */
    public void setUnlockedMessage(String unlockedMessage){
        
        this.unlockedMessage = unlockedMessage;
        
    }
    
    /**
     * Returns the message to display if this type of exit is already unlocked.
     * 
     * @return the message to display if this type of exit is already unlocked.
     */
    public String getUnlockedMessage(){
        
        return this.unlockedMessage;
        
    }
    
    /**
     * Sets the message to display when this type of exit is unlocked.
     * 
     * @param unlockMessage the message to display when this type of exit is unlocked.
     */
    public void setUnlockMessage(String unlockMessage){
        
        this.unlockMessage = unlockMessage;
        
    }
    
    /**
     * Returns the message to display when this type of exit is unlocked.
     * 
     * @return the message to display when this type of exit is unlocked.
     */
    public String getUnlockMessage(){
        
        return this.unlockMessage;
        
    }
    
    /**
     * Sets list of all items that will unlock this type of exit.
     * 
     * @param key the list of all items that will unlock this type of exit.
     */
    public void setKey(List<Item> key){
        
        this.key = key;
        
    }
    
    /**
     * Returns a list of all items that will unlock this type of exit.
     * 
     * @return a list of all items that will unlock this type of exit.
     */
    public List<Item> getKey(){
        
        return this.key;
        
    }
    
}
