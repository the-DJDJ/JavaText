package BeanPlant.the_DJDJ.JavaText.npc;

import java.io.Serializable;

/**
 * A boss object. A boss is an NPC that a player must either kill or escape in
 * order to progress.
 *
 * @author the_djdj
 */
public class Boss implements Serializable {
    
    public static final Boss wombat = new Boss("Verocious Wombat", 250, 5, false);
    
    /** The name of the boss. */
    private String name;
    /** How much health the boss has. */
    private int health;
    /** How much damage the boss does everytime they hurt you. */
    private int damage;
    /** Whether or not the player can run away from the boss. */
    private boolean avoidable;
    /** Whether or not the boss is aggressive by default. */
    private boolean aggressive;
    
    /**
     * The default constructor. This assigns the name, the amount of health, and
     * the damage that the boss has.
     * 
     * @param name The name of the boss
     * @param health The amount of health the boss has
     * @param damage The amount of damage that the boss does per hit
     */
    public Boss(String name, int health, int damage){
        
        this.name = name;
        this.health = health;
        this.damage = damage;
        
        this.avoidable = true;
        
    }
    
    /**
     * The second constructor. This assigns the name, the amount of health, the
     * damage that the boss has, and whether or not the boss is avoidable.
     * 
     * @param name
     * @param health
     * @param damage
     * @param avoidable 
     */
    public Boss(String name, int health, int damage, boolean avoidable){
        
        this.name = name;
        this.health = health;
        this.damage = damage;
        
        this.avoidable = avoidable;
        
    }
    
    /**
     * Returns the name of the boss.
     * 
     * @return the name of the boss
     */
    public String getName(){
        
        return this.name;
        
    }
    
    /**
     * Sets the name of the boss
     * 
     * @param name the new name of the boss
     */
    public void setName(String name){
        
        this.name = name;
        
    }
    
    /**
     * Returns the amount of health that the boss.
     * 
     * @return the amount of health that the boss has
     */
    public int getHealth(){
        
        return this.health;
        
    }
    
    /**
     * Sets the amount of health that the boss has
     * 
     * @param health the new amount of health that the boss has
     */
    public void setHealth(int health){
        
        this.health = health;
        
    }
    
    /**
     * Returns the amount of damage that the boss does per hit
     * 
     * @return the amount of damage that the boss does per hit
     */
    public int getDamage(){
        
        return this.damage;
        
    }
    
    /**
     * Sets the amount of damage that the boss does per hit.
     * 
     * @param damage the new amount of damage that the boss does per hit
     */
    public void setDamage(int damage){
        
        this.damage = damage;
        
    }
    
    /**
     * Returns whether or not the boss can be avoided by the player.
     * 
     * @return whether or not the boss can be avoided by the player
     */
    public boolean isAvoidable(){
        
        return this.avoidable;
        
    }
    
    /**
     * Sets whether or not the boss can be avoided by the player.
     * 
     * @param avoidable whether or not the boss can be avoided by the player
     */
    public void setAvoidable(boolean avoidable){
        
        this.avoidable = avoidable;
        
    }
    
    /**
     * Returns whether or not the boss is aggressive.
     * 
     * @return whether or not the boss is aggressive
     */
    public boolean isAggressive(){
        
        return this.aggressive;
        
    }
    
    /**
     * Sets whether or not the boss is aggressive.
     * 
     * @param aggressive whether or not the boss is aggressive
     */
    public void setAggressive(boolean aggressive){
        
        this.aggressive = aggressive;
        
    }
    
}
