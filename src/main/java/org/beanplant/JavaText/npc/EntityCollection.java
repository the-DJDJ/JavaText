package org.beanplant.JavaText.npc;

/**
 * A simple class that stores a 'collection' of entities. This only contains an
 * Entity, and the amount of that entity
 *
 * @author the_DJDJ
 */
public class EntityCollection extends Entity {
    
    /** The amount of entities in this collection. */
    private int amount;
    
    /**
     * The default constructor. This gets the entity that this collection
     * contains, as well as the amount of entities in the collection.
     * 
     * @param entity The entity that this collection contains.
     * @param amount The amount of entities in this collection.
     */
    public EntityCollection(Entity entity, int amount){
        
        super(entity);
        this.amount = amount;
        
    }
    
    /**
     * Gets the amount of entities that are in this collection
     * 
     * @return the amount of entities that are in this collection
     */
    public int getAmount(){
        
        return this.amount;
        
    }
    
    /**
     * Adds a specific amount of entities to this collection
     * 
     * @param amount the amount of entities to add to this collection
     * @return the updated stack
     */
    public EntityCollection add(int amount){
        
        this.amount += amount;
        return this;
        
    }
    
    /**
     * Removes a specific amount of entities from this collection, or returns
     * null if there are not any entities left
     * 
     * @param amount the amount of entities to remove from this collection
     * @return the updated stack
     */
    public Entity remove(int amount){
        
        if(this.amount - amount == 1){
            
            this.amount = 1;
            return this;
            
        } else if(this.amount - amount > 0){
            
            this.amount -= amount;
            return this;
            
        } else {
            
            this.amount = 0;
            return null;
            
        }
        
    }
    
    /**
     * Sets the amount of entities that are in this collection
     * 
     * @param amount the new amount in this collection.
     * @return this updated collection.
     */
    public EntityCollection setAmount(int amount){
        
        this.amount = amount;
        return this;
        
    }
    
    @Override
    public boolean isCollection(){
        
        return true;
        
    }
    
}
