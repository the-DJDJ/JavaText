package org.beanplant.JavaText.user;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * The class that represents an item that can be found and interacted with in
 * the game
 *
 * @author the_DJDJ
 */
public class Item implements Serializable {
    
    /** The proper name of the item. */
    private String m_name;
    
    /** The name of a single unit of the item. */
    private String m_single;
    
    /** The plural name of the units of the item. */
    private String m_plural;
    
    /** The description of the location. */
    private String m_description;
    
    /** The amount of damage this item does per hit. */
    private int m_damage;
    
    public final static Item crowbar = new Item("Crowbar", "a crowbar", "a pile of crowbars", "Ever played Half-Life? Yeah, you know exactly what this does!", 3);
    public final static Item key = new Item("Key", "a key", "some keys", "Maybe this unlocks something...");
    public final static Item rock = new Item("Rock", "a rock", "some rocks", "A rock! Make fire! Re-invent the wheel! Do whatever you like! It's just a rock...", 2);
    public final static Item rope = new Item("Rope", "a rope", "some rope", "Ever needed to climb out of a sticky situation? Our rope is always there for you!");
    public final static Item stick = new Item("Stick", "a stick", "some sticks", "This is a stick. Poke things with it, and stuff.", 2);
    public final static Item newspaper = new Item("Newspaper", "an old newspaper", "some old newspapers", "An old newspaper! From, like, 2000 years ago... I think something is growing on it");
    
    /** A list of all items so that they can be searched. */
    public static List<Item> items = Arrays.asList(Item.crowbar, 
                                            Item.key, 
                                            Item.rock, 
                                            Item.rope, 
                                            Item.stick, 
                                            Item.newspaper);
    
    /**
     * The default constructor. Initialises all variables and sets them to the
     * default values.
     */
    public Item(){
        
        this.m_name = new String();
        this.m_single = new String();
        this.m_plural = new String();
        this.m_description = new String();
        this.m_damage = 1;
        
    }
    
    /**
     * A constructor. Creates a new Item object with the specified name.
     * 
     * @param name The name of the Item.
     */
    public Item(String name){
        
        this.m_name = name;
        this.m_single = new String();
        this.m_plural = new String();
        this.m_description = new String();
        this.m_damage = 1;
        
    }
    
    /**
     * The next constructor. Creates a new Item object with the specified name and
     * description
     * 
     * @param name The name of the Item.
     * @param single The name of a single unit of the item
     * @param plural The name of many units of the item
     * @param description The description of the Item.
     */
    public Item(String name, String single, String plural, String description){
        
        this.m_name = name;
        this.m_single = single;
        this.m_plural = plural;
        this.m_description = description;
        this.m_damage = 1;
        
    }
    
    /**
     * The full constructor. Creates a new Item object with the specified name,
     * description and damage
     * 
     * @param name The name of the Item.
     * @param single The name of a single unit of the item
     * @param plural The name of many units of the item
     * @param description The description of the Item.
     * @param damage The amount of damage that this Item does per hit
     */
    public Item(String name, String single, String plural, String description, int damage){
        
        this.m_name = name;
        this.m_single = single;
        this.m_plural = plural;
        this.m_description = description;
        this.m_damage = damage;
        
    }
    
    /**
     * Uses the item. This is dependant on what the default action for the item
     * is.
     */
    public void use(){
        
        throw new UnsupportedOperationException("Hmmm, it looks like we haven't "
                + "quite finished this part of the game yet... We'll work on it "
                + "right away! Sorry for the inconvenience :)");
    
    }

    /**
     * Returns the name of the item.
     * 
     * @return The name of the item
     */
    public String getName(){
            
        return this.m_name;
    
    }

    /**
     * Updates or sets the name of the item
     * 
     * @param name The new name of the item.
     */
    public void setName(String name) {
            
        this.m_name = name;
    
    }
    
    /**
     * Returns the name of a single unit of the item.
     * 
     * @return The name of a single unit of the item
     */
    public String getSingleName(){
            
        return this.m_single;
    
    }

    /**
     * Updates or sets the name of a single unit of the item
     * 
     * @param name The new name of a singke unit of the item.
     */
    public void setSingleName(String name) {
            
        this.m_single = name;
    
    }
    
    /**
     * Returns the name of many units of the item.
     * 
     * @return The name of many units of the item
     */
    public String getPluralName(){
            
        return this.m_plural;
    
    }

    /**
     * Updates or sets the name of many units of the item
     * 
     * @param name The new name of many units of the item.
     */
    public void setPluralName(String name) {
            
        this.m_plural = name;
    
    }

    /**
     * Returns the description of the room.
     * 
     * @return The description of the room
     */
    public String getDescription() {
            
        return this.m_description;
    
    }

    /**
     * Updates or sets the description of the room.
     * 
     * @param description The new description of the room.
     */
    public void setDescription(String description) {
            
        this.m_description = description;
    
    }
    
    /**
     * Returns the amount of damage that this item does per hit
     * 
     * @return the amount of damage that this item does per hit
     */
    public int getDamage(){
        
        return this.m_damage;
        
    }
    
    /**
     * Updates or sets the amount of damage that this item does per hit/
     * 
     * @param damage The amount of damage this item does per hit
     */
    public void setDamage(int damage){
        
        this.m_damage = damage;
        
    }
    
    /**
     * Returns the item that has the same name as the given string. This allows
     * items that the user specifies to be interacted with.
     * 
     * @param name The name of the item to search for.
     * 
     * @return The item that has the same name as the string, or null if no
     * match is found
     */
    public Item getItem(String name){
        
        for (Item item : items) {
            
            if (item.getName().equalsIgnoreCase(name)) {
                
                return item;
            
            }
        
        }
        
        return null;
        
    }
    
    /**
     * A method that adds custom items to the item list. This allows games to
     * have custom items
     * 
     * @param item the item to add
     */
    public static void addItem(Item item){
        
        if(!items.contains(item)){
            
            items.add(item);
            
        }
        
    }
    
    /**
     * Returns whether or not the item specified actually exists. This allows
     * for the program to check if a user-specified item is valid.
     * 
     * @param name
     * @return 
     */
    public boolean isValidItem(String name){
        
        for (Item item : items) {
            
            if (item.getName().equalsIgnoreCase(name)) {
                
                return true;
            
            }
        
        }
        
        return false;
        
    }
    
    /**
     * Returns whether or not this Item is a stack or a single item.
     * 
     * @see ItemFrame
     * @return if this is a stack or a single item.
     */
    public boolean isStack(){
        
        return false;
        
    }
    
    @Override
    public String toString(){
        
        return this.getName();
        
    }
    
}
