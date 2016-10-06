package org.beanplant.JavaText.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores the user inventory of items. Everything that the user holds or can use
 * is stored here.
 *
 * @author the_DJDJ
 */
public class Inventory implements Serializable {
    
    /** The actual list that stores the items. */
    private final List<Item> inventory;
    
    /** A list that only stores one copy of an item to avoid duplicates. */
    private final List<Item> condensedInventory;
    
    /** The maximum size that the inventory can be. */
    private final int maximumSize = 5;
    
    /**
     * The default constructor. Instantiates a new inventory List
     */
    public Inventory(){
        
        this.inventory = new ArrayList<>();
        this.condensedInventory = new ArrayList<>();
        
    }
    
    /**
     * Checks if the user's inventory has enough space to store an item, and
     * then adds that item to the user's inventory so that it can be used later.
     * 
     * @param item The item to add.
     * 
     * @return Whether or not the addition was successful
     */
    public boolean addItem(Item item){
        
        if(inventory.size() < maximumSize){
            
            this.inventory.add(item);
        
            if(!this.condensedInventory.contains(item)){
            
                this.condensedInventory.add(item);
            
            }
            
            return true;
            
        }
        
        return false;
        
    }
    
    /**
     * Checks if the user currently owns an item, and if so, removes it.
     * 
     * @param item The item to remove.
     */
    public void removeItem(Item item){
        
        if(this.inventory.contains(item)){
        
            this.inventory.remove(item);
            
            // Check again to see if there were any duplicates
            if(!this.inventory.contains(item)){
                
                this.condensedInventory.remove(item);
                
            }
        
        }
        
    }
    
    /**
     * Checks if the user currently owns an item, and if so, uses it.
     * 
     * @param item The item to use.
     */
    public void useItem(Item item){
        
        if(this.inventory.contains(item)){
            
            item.use();
            
        }
        
    }
    
    /**
     * Returns the amount of a specific item that is currently in the user's
     * inventory.
     * 
     * @param item The item to search for.
     * 
     * @return The amount of that item that is in the user's inventory.
     */
    public int getAmount(Item item){
        
        int amount = 0;
        
        for (int i = 0; i < this.inventory.size(); i++) {
            
            if(this.inventory.get(i).equals(item)){
                
                amount++;
                
            }
            
        }
        
        return amount;
        
    }
    
    /**
     * Returns the maximum amount of any item that is in the user's inventory.
     * 
     * @return the maximum amount of any item
     */
    public int getMaximumAmount(){
        
        int maximum = Integer.MIN_VALUE;
        
        for (int i = 0; i < this.condensedInventory.size(); i++) {
            
            if(this.getAmount(this.condensedInventory.get(i)) > maximum){
                
                maximum = this.getAmount(this.condensedInventory.get(i));
                
            }
            
        }
        
        return maximum;
        
    }
    
    /**
     * Returns whether or not the user already has the specified item in their
     * inventory.
     * 
     * @param item The item to check for.
     * @return Whether or not the user has this item
     */
    public boolean contains(Item item){
        
        return this.inventory.contains(item);
        
    }
    
    /**
     * Returns whether or not the user already has one of the specified items in
     * their inventory
     * 
     * @param items the items to check for.
     * @return Whether or not the user has this idea
     */
    public boolean contains(List<Item> items){
        
        if(items != null){
        
            for (int i = 0; i < items.size(); i++) {

                if(this.contains(items.get(i))){

                    return true;

                }

            }

            return false;
            
        } else {
            
            return true;
            
        }
        
    }
    
    @Override
    public String toString(){
        
        String string = new String();
        
        if(!this.condensedInventory.isEmpty()){
            
            int length = String.valueOf(this.getMaximumAmount()).length();
        
            for (int i = 0; i < this.condensedInventory.size(); i++) {

                string += String.format("%-" + length + "dx %s", this.getAmount(this.condensedInventory.get(i)), this.condensedInventory.get(i).getName());

                if(i != this.condensedInventory.size() - 1){

                    string += " <token:newline> ";

                }

            }
        
        } else {
            
            string += "You don't have anything!";
            
        }
        
        return string;
        
    }
    
}
