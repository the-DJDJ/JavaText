package BeanPlant.the_DJDJ.JavaText.npc;

/**
 * A class that simply leaves a message if a mobile entity has moved away from
 * their previous location
 *
 * @author the_DJDJ
 */
public class EntityShadow extends MobileEntity {
        
    /** If this is a collection, how many MobileEntities are there? */
    private final int size;
    
    /**
     * The default constructor. This creates a standard EntityShadow for one
     * MobileEntity
     * 
     * @param entity the entity that has moved away
     */
    public EntityShadow(MobileEntity entity){
        
        this(entity, 1);
        
    }
    
    /**
     * The second constructor. This creates an EntityShadow for a collection of
     * MobileEntities.
     * 
     * @param entity the entity that has moved away
     * @param size the number of entities that there were
     */
    public EntityShadow(MobileEntity entity, int size){
        
        super(entity);
        this.size = size;
        
    }
    
    @Override
    public boolean isCollection(){
        
        return this.size != 1;
        
    }
    
    /**
     * Returns the total number of entities that have moved away.
     * 
     * @return how many entities this shadow represents
     */
    public int getCollectionLength(){
        
        return this.size;
        
    }
    
}
