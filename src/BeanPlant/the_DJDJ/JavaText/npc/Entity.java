package BeanPlant.the_DJDJ.JavaText.npc;

import java.io.Serializable;

/**
 * An entity object. An entity is an NPC that populates the landscape, and can
 * either be attacked, interacted with, or ignored.
 *
 * @author the_DJDJ
 */
public class Entity implements Serializable {
    
    public static final Entity sheep = new Entity("Harmless Sheep", 10);
    
    /** The name of the entity. */
    private String name;
    
    /** How much health the entity has. */
    private int health;
    
    /** Whether or not the player can run away from the entity. */
    private boolean avoidable;
    
    /**
     * The default constructor. This assigns the name and the amount of health
     * to the entity
     * 
     * @param name The name of the entity
     * @param health The amount of health the entity has
     */
    public Entity(String name, int health){
        
        this.name = name;
        this.health = health;
        
        this.avoidable = true;
        
    }
    
    /**
     * The second constructor. This assigns the name, the amount of health, and
     * whether or not the entity is avoidable.
     * 
     * @param name The name of the entity
     * @param health The amount of health the entity has
     * @param avoidable Whether or not the player can run away from the entity
     */
    public Entity(String name, int health, boolean avoidable){
        
        this.name = name;
        this.health = health;
        
        this.avoidable = avoidable;
        
    }
    
    /**
     * Returns the name of the entity.
     * 
     * @return the name of the entity
     */
    public String getName(){
        
        return this.name;
        
    }
    
    /**
     * Sets the name of the entity
     * 
     * @param name the new name of the entity
     */
    public void setName(String name){
        
        this.name = name;
        
    }
    
    /**
     * Returns the amount of health that the entity has.
     * 
     * @return the amount of health that the entity has
     */
    public int getHealth(){
        
        return this.health;
        
    }
    
    /**
     * Sets the amount of health that the entity has.
     * 
     * @param health the new amount of health that the entity has
     */
    public void setHealth(int health){
        
        this.health = health;
        
    }
    
    /**
     * Returns whether or not the entity can be avoided by the player.
     * 
     * @return whether or not the entity can be avoided by the player
     */
    public boolean isAvoidable(){
        
        return this.avoidable;
        
    }
    
    /**
     * Sets whether or not the entity can be avoided by the player.
     * 
     * @param avoidable whether or not the entity can be avoided by the player
     */
    public void setAvoidable(boolean avoidable){
        
        this.avoidable = avoidable;
        
    }
    
}
