package BeanPlant.the_DJDJ.JavaText.world;

import BeanPlant.the_DJDJ.JavaText.npc.Boss;
import BeanPlant.the_DJDJ.JavaText.npc.Entity;
import BeanPlant.the_DJDJ.JavaText.npc.EntityCollection;
import BeanPlant.the_DJDJ.JavaText.npc.EntityShadow;
import BeanPlant.the_DJDJ.JavaText.world.exit.Exit;
import BeanPlant.the_DJDJ.JavaText.user.Item;
import BeanPlant.the_DJDJ.JavaText.user.ItemStack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A location that the player can be in. This is a room, with a title and a 
 * description, that the user sees.
 *
 * @author the_DJDJ
 */
public class Location implements Serializable {
	
    /** The title of the location. */
    private String m_title;
    
    /** The description of the location. */
    private String m_description;
    
    /** Whether or not the user has visited this location yet. */
    private transient boolean m_visited;
    
    /** The items in the location. */
    private final List<Item> m_items;
    
    /** The exits associated with the location. */
    private final List<Exit> m_exits;
    
    /** The entities in this location. */
    private List<Entity> entities;
    
    /** The EntityShadows in this location. */
    private List<EntityShadow> entityShadows;
    
    /** The boss in this location. */
    private Boss boss = null;

    /**
     * The default constructor. Initialises all variables and sets them to the
     * default values.
     */
    public Location() {
        
        this(new String(), new String());
    
    }

    /**
     * A constructor. Creates a new Location object with the specified title.
     * 
     * @param title The title of the room. 
     */
    public Location(String title) {
        
        this(title, new String());
    
    }

    /**
     * The full constructor. This creates a new Location object with the
     * specified title and description.
     * 
     * @param title The title of the room.
     * @param description The description of the room.
     */
    public Location(String title, String description) {
        
        this.m_title = title;
        this.m_description = description;
        this.m_visited = false;

        this.m_items = new ArrayList<>();
        this.m_exits = new ArrayList();
        
        this.entities = new ArrayList<>();
        this.entityShadows = new ArrayList<>();
    
    }

    /**
     * Adds new exit(s) to the room
     * 
     * @param exit The exit(s) to add
     */
    public void addExit(Exit... exit) {
        
        for (int i = 0; i < exit.length; i++) {
            
            this.m_exits.add(exit[i]);
            
        }
    
    }

    /**
     * Removes exit(s) from the room
     * 
     * @param exit The exit(s) to remove
     */
    public void removeExit (Exit... exit) {
        
        for (int i = 0; i < exit.length; i++) {
            
            if (this.m_exits.contains(exit[i])) {
                    
                this.m_exits.remove(exit[i]);
            
            }
            
        }
    
    }

    /**
     * Returns a List representation of the exits.
     * 
     * @return The exits that this room has
     */
    public List<Exit> getExits () {
        
        return this.m_exits;
    
    }
    
    /**
     * Adds new item(s) to the room
     * 
     * @param item The item(s) to add.
     */
    public void addItem(Item item) {
        
        this.m_items.add(item);
    
    }
    
    /**
     * Adds a specific amount of a new item to the room
     * 
     * @param item The item to add.
     * @param amount The amount of the item to add
     */
    public void addItem(Item item, int amount){
        
        boolean present = false;
        int index = 0;
        
        for (int i = 0; i < this.m_items.size(); i++) {
            
            if(this.m_items.get(i).getName().equals(item.getName())) {
                
                present = true;
                index = i;
            
            }
            
        }
        
        if(present){
            
            if(this.m_items.get(index).isStack()){
                
                this.m_items.set(index, new ItemStack(item, ((ItemStack) this.m_items.get(index)).getAmount() + amount));
                
            } else {
                
                this.m_items.set(index, new ItemStack(item, amount + 1));
                
            }
            
        } else {
            
            this.m_items.add(new ItemStack(item, amount));
            
        }
        
    }

    /**
     * Removes item(s) from the room
     * 
     * @param item The item(s) to remove
     */
    public void removeItem(Item... item) {
        
        for (int i = 0; i < item.length; i++) {
            
            if (this.m_items.contains(item[i])) {
                    
                this.m_items.remove(item[i]);
            
            }
            
        }
    
    }
    
    /**
     * Removes a specific amount of an item from the room
     * 
     * @param item The item to remove
     * @param amount The amount of items to remove
     */
    public void removeItem(Item item, int amount){
        
        for (int i = 0; i < amount; i++) {
            
            this.removeItem(item);
            
        }
        
    }

    /**
     * Returns a List representation of the items.
     * 
     * @return The items that this room has
     */
    public List<Item> getItems() {
        
        return this.m_items;
    
    }

    /**
     * Returns the title of the room.
     * 
     * @return The title of the room
     */
    public String getTitle(){
            
        return this.m_title;
    
    }

    /**
     * Updates or sets the title of the room.
     * 
     * @param title The new title of the room.
     */
    public void setTitle(String title) {
            
        this.m_title = title;
    
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
     * Returns whether or not the user has yet visited this room.
     * 
     * @return If the room has been visited
     */
    public boolean hasBeenVisited(){
        
        return this.m_visited;
        
    }
    
    /**
     * Sets the location so that it has been visited. This method can be 
     * overridden so that custom events can be called when a user visits the
     * location.
     */
    public void visit(){
        
        this.m_visited = true;
        
    }
    
    /**
     * Adds new entity(ies) to the room
     * 
     * @param entity The entity(ies) to add.
     */
    public void addEntity(Entity entity) {
        
        this.entities.add(entity);
    
    }
    
    /**
     * Adds a specific number of new entities to the room
     * 
     * @param entity The entity to add.
     * @param amount The number of the entity to add
     */
    public void addEntity(Entity entity, int amount){
        
        boolean present = false;
        int index = 0;
        
        for (int i = 0; i < this.entities.size(); i++) {
            
            if(this.entities.get(i).getName().equals(entity.getName())) {
                
                present = true;
                index = i;
            
            }
            
        }
        
        if(present){
            
            if(this.entities.get(index).isCollection()){
                
                this.entities.set(index, new EntityCollection(entity, ((EntityCollection) this.entities.get(index)).getAmount() + amount));
                
            } else {
                
                this.entities.set(index, new EntityCollection(entity, amount + 1));
                
            }
            
        } else {
            
            this.entities.add(new EntityCollection(entity, amount));
            
        }
        
    }

    /**
     * Removes entities from the room
     * 
     * @param entity The entities(s) to remove
     */
    public void removeEntity(Entity... entity) {
        
        for (int i = 0; i < entity.length; i++) {
            
            if (this.entities.contains(entity[i])) {
                    
                this.entities.remove(entity[i]);
            
            }
            
        }
    
    }
    
    /**
     * Removes a specific number of an entity from the room
     * 
     * @param entity The entity to remove
     * @param amount The amount of entities to remove
     */
    public void removeEntity(Entity entity, int amount){
        
        for (int i = 0; i < amount; i++) {
            
            this.removeEntity(entity);
            
        }
        
    }
    
    /**
     * Adds an EntityShadow to the room
     * 
     * @param shadow The EntityShadow to add
     */
    public void addEntityShadow(EntityShadow shadow){
        
        this.entityShadows.add(shadow);
        
    }
    
    /**
     * A method to clear all remaining EntityShadows from the room
     */
    public void removeEntityShadows(){
        
        this.entityShadows.clear();
        
    }

    /**
     * Returns a List representation of the entities.
     * 
     * @return The entities that this room has
     */
    public List<Entity> getEntities() {
        
        return this.entities;
    
    }
    
    /**
     * Returns whether or not there are any entities in this location, by
     * checking whether or not the entities list is empty.
     * 
     * @return if there are entities in this location
     */
    public boolean hasEntities(){
        
        return !this.getEntities().isEmpty();
        
    }
    
    /**
     * Returns a list of all of the EntityShadows in this room.
     * 
     * @return a list of all the EntityShadows in this location.
     */
    public List<EntityShadow> getEntityShadows(){
        
        return this.entityShadows;
        
    }
    
    /**
     * Returns whether or not there are any EntityShadows in this location, by
     * checking whether or not the entityShadows list is empty.
     * 
     * @return if there are EntityShadows in this location
     */
    public boolean hasEntityShadows(){
        
        return !this.getEntityShadows().isEmpty();
        
    }
    
    /**
     * Returns the boss of this location, as is.
     * 
     * @return the boss of this location
     */
    public Boss getBoss(){
        
        return this.boss;
        
    }
    
    /**
     * Sets or updates the boss of this location
     * 
     * @param boss the boss of this location
     */
    public void setBoss(Boss boss){
        
        this.boss = boss;
                
    }
    
    /**
     * Returns whether or not this location has a boss. This is used for
     * determining whether or not boss information should be displayed.
     * 
     * @return whether or not this location has a boss
     */
    public boolean hasBoss(){
        
        return this.boss != null;
        
    }
    
    /**
     * @inheritDoc
     * 
     * @return A string representation of the room
     */
    @Override
    public String toString() {
            
        return this.m_title;
    
    }

}
