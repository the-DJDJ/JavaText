package BeanPlant.the_DJDJ.JavaText.npc;

import java.io.Serializable;

/**
 * An entity object. An entity is an NPC that populates the landscape, and can
 * either be attacked, interacted with, or ignored.
 *
 * @author the_DJDJ
 */
public class Entity implements Serializable {
    
    /** The name of the entity. */
    private String name;
    
    /** The name of a single on of this entity. */
    private String single;
    
    /** The name given to many of this entity. */
    private String plural;
    
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
     * A slightly more detailed constructor. This assigns all of the names, as
     * well as the amount of health to the entity.
     * 
     * @param name The name of the entity
     * @param single The name given to a single unit of this entity
     * @param plural The name given to multiple units of this entity
     * @param health The amount of health the entity has.
     */
    public Entity(String name, String single, String plural, int health){
        
        this.name = name;
        this.single = single;
        this.plural = plural;
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
     * Another slightly more detailed constructor. This assigns the name, as
     * well as the single and plural names of the entity, the amount of health
     * the entity has, as well as whether or not the entity is avoidable.
     * 
     * @param name The name of the entity
     * @param single The name given to a single unit of this entity
     * @param plural The name given to multiple units of this entity
     * @param health The amount of health the entity has.
     * @param avoidable Whether or not the entity is avoidable.
     */
    public Entity(String name, String single, String plural, int health, boolean avoidable){
        
        this.name = name;
        this.single = single;
        this.plural = plural;
        this.health = health;
        
        this.avoidable = avoidable;
        
    }
    
    /**
     * An empty constructor. This creates a new entity, only as a placeholder
     * variable for use in entity collections
     */
    public Entity(){
        
        this.name = new String();
        this.health = -1;
        this.avoidable = true;
        
    }
    
    /**
     * A slightly random constructor, used so that EntityShadows can register
     * themselves as entities
     * 
     * @param entity the entity to create
     */
    public Entity(Entity entity){
        
        this.name = entity.getName();
        this.single = entity.getSingleName();
        this.plural = entity.getPluralName();
        this.health = entity.getHealth();
        this.avoidable = entity.isAvoidable();
        
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
     * Returns the name of one unit of this entity.
     * 
     * @return the name of one unit of this entity
     */
    public String getSingleName(){
        
        return this.single;
        
    }
    
    /**
     * Sets the name of one unit of this entity
     * 
     * @param single the new name of one unit of this entity
     */
    public void setSingleName(String single){
        
        this.single = single;
        
    }
    
    /**
     * Returns the name of many units of this entity.
     * 
     * @return the name of many units of this entity
     */
    public String getPluralName(){
        
        return this.plural;
        
    }
    
    /**
     * Sets the name of many units of this entity
     * 
     * @param name the new name of many units of this entity
     */
    public void setPluralName(String plural){
        
        this.plural = plural;
        
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
    
    /**
     * Returns whether or not this Entity is a collection or a single NPC.
     * 
     * @see EntityStack
     * @return if this is a collection or a single entity.
     */
    public boolean isCollection(){
        
        return false;
        
    }
    
}
