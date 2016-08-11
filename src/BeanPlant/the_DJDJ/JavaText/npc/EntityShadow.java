package BeanPlant.the_DJDJ.JavaText.npc;

/**
 * A class that simply leaves a message if a mobile entity has moved away from
 * their previous location
 *
 * @author the_DJDJ
 */
public class EntityShadow extends MobileEntity {
    
    private final boolean collection;
    private final int size;
    
    public EntityShadow(MobileEntity entity){
        
        this(entity, false, 0);
        
    }
    
    public EntityShadow(MobileEntity entity, boolean collection, int size){
        
        super(entity);
        
        this.collection = collection;
        this.size = size;
        
    }
    
    @Override
    public boolean isCollection(){
        
        return this.collection;
        
    }
    
    public int getCollectionLength(){
        
        return this.size;
        
    }
    
}
