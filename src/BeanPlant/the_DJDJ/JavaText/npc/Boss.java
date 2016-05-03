package BeanPlant.the_DJDJ.JavaText.npc;

import java.io.Serializable;

/**
 *
 * @author the_djdj
 */
public class Boss implements Serializable {
    
    public static final Boss wombat = new Boss("Verocious Wombat", 250, 5, false);
    
    private String name;
    private int health;
    private int damage;
    private boolean avoidable;
    private boolean aggressive;
    
    public Boss(String name, int health, int damage){
        
        this.name = name;
        this.health = health;
        this.damage = damage;
        
        this.avoidable = true;
        
    }
    
    public Boss(String name, int health, int damage, boolean avoidable){
        
        this.name = name;
        this.health = health;
        this.damage = damage;
        
        this.avoidable = avoidable;
        
    }
    
    public String getName(){
        
        return this.name;
        
    }
    
    public void setName(String name){
        
        this.name = name;
        
    }
    
    public int getHealth(){
        
        return this.health;
        
    }
    
    public void setHealth(int health){
        
        this.health = health;
        
    }
    
    public int getDamage(){
        
        return this.damage;
        
    }
    
    public void setDamage(int damage){
        
        this.damage = damage;
        
    }
    
    public boolean isAvoidable(){
        
        return this.avoidable;
        
    }
    
    public void setAvoidable(boolean avoidable){
        
        this.avoidable = avoidable;
        
    }
    
    public boolean isAggressive(){
        
        return this.aggressive;
        
    }
    
    public void setAggressive(boolean aggressive){
        
        this.aggressive = aggressive;
        
    }
    
}
