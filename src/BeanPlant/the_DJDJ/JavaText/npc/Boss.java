package BeanPlant.the_DJDJ.JavaText.npc;

import java.io.Serializable;

/**
 * A boss object. A boss is an NPC that a player must either kill or escape in
 * order to progress.
 *
 * @author the_DJDJ
 */
public class Boss extends Entity implements Serializable {
    
    public static final Boss wombat = new Boss("Verocious Wombat", 250, 5, true);
    
    /** How much damage the boss does everytime they hurt you. */
    private int damage;

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
        
        super(name, health);
        
        this.damage = damage;
        
    }
    
    /**
     * The second constructor. This assigns the name, the amount of health, the
     * damage that the boss has, and whether or not the boss is avoidable.
     * 
     * @param name The name of the boss
     * @param health The amount of health the boss has
     * @param damage The amount of damage the boss does per hit
     * @param avoidable Whether or not the player can run away from the boss
     */
    public Boss(String name, int health, int damage, boolean avoidable){
        
        super(name, health, avoidable);
        
        this.damage = damage;
        
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
