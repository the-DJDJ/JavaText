package BeanPlant.the_DJDJ.JavaText.npc;

/**
 * A simple class that stores a 'collection' of entities. This only contains an
 * Entity, and the amount of that entity
 *
 * @author the_DJDJ
 */
public class EntityCollection extends Entity {
    
    /** The Entity of this collection. */
    private Entity entity;
    
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
        
        this.entity = entity;
        this.amount = amount;
        
    }
    
    @Override
    public String getName(){
        
        return this.entity.getName();
        
    }
    
    @Override
    public String getSingleName(){
        
        return this.entity.getSingleName();
        
    }
    
    @Override
    public String getPluralName(){
        
        return this.entity.getPluralName();
        
    }
    
    /**
     * Returns the entity that this collection contains.
     * 
     * @return the entity of this collection
     */
    public Entity getEntity(){
        
        return this.entity;
        
    }
    
    /**
     * Sets the entity that this collection contains, and returns this
     * collection object.
     * 
     * @param entity the new entity of this collection
     * @return this EntityCollection object
     */
    public EntityCollection setEntity(Entity entity){
        
        this.entity = entity;
        return this;
        
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
            return this.getEntity();
            
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
