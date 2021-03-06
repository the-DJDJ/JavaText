package org.beanplant.JavaText.npc;

import org.beanplant.JavaText.handlers.EventHandler;
import org.beanplant.JavaText.io.CommandParser;
import org.beanplant.JavaText.world.Location;
import org.beanplant.JavaText.world.exit.Exit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A mobile entity object. This is exactly the same as an Entity, with the
 * exception that it has a chance of moving to another location every time a
 * command is entered.
 *
 * @author the_DJDJ
 */
public class MobileEntity extends Entity implements Serializable, EventHandler {
    
    public static final MobileEntity sheep = new MobileEntity("sheep", "a harmless sheep", "a flock of relatively harmless sheep", 10, 0.1f);
    
    /** What percentage chance the entity has of moving every time a command is entered. */
    private float movingChance;

    /** Whether or not the entity can use the up/down directions, ie climbing. */
    private boolean canClimb;
    
    /** The current location of this MobileEntity. */
    private Location location;
    
    /** Whether or not this MobileEntity moved on the last turn. */
    private boolean hasMovedRecently = false;
    
    /**
     * The default constructor. This assigns the name, the amount of health, and
     * the change of moving that the entity has.
     * 
     * @param name The name of the entity
     * @param single The name given to a single unit of this entity
     * @param plural The name given to multiple units of this entity
     * @param health The amount of health the entity has
     * @param movingChance the chance of moving to a random location that the
     *                     entity has.
     */
    public MobileEntity(String name, String single, String plural, int health, float movingChance){
                
        this(name, single, plural, health, movingChance, null, true);
        
    }
    
    /**
     * The default constructor. This assigns the name, the amount of health, and
     * the change of moving that the entity has.
     * 
     * @param name The name of the entity
     * @param single The name given to a single unit of this entity
     * @param plural The name given to multiple units of this entity
     * @param health The amount of health the entity has
     * @param movingChance the chance of moving to a random location that the
     *                     entity has.
     * @param location Where this MobileEntity currently is
     */
    public MobileEntity(String name, String single, String plural, int health, float movingChance, Location location){
        
        this(name, single, plural, health, movingChance, location, true);
        
    }
    
    /**
     * A slightly random constructor, used so that other objects can register
     * themselves as MobileEntities
     * 
     * @param entity the MobileEntity to register
     */
    public MobileEntity(MobileEntity entity){
        
        this(entity.getName(), entity.getSingleName(), entity.getPluralName(), entity.getHealth(), entity.getMovingChance(), entity.isAvoidable());
        
    }
    
    /**
     * The second constructor. This assigns the name, the amount of health, the
     * chance of moving that the entity has, and whether or not the entity is
     * avoidable.
     * 
     * @param name The name of the entity
     * @param single The name given to a single unit of this entity
     * @param plural The name given to multiple units of this entity
     * @param health The amount of health the entity has
     * @param movingChance the chance of moving to a random location that the
     *                     entity has.
     * @param avoidable Whether or not the player can run away from the entity
     */
    public MobileEntity(String name, String single, String plural, int health, float movingChance, boolean avoidable){
        
        this(name, single, plural, health, movingChance, null, avoidable);
        
    }
    
    /**
     * The second constructor. This assigns the name, the amount of health, the
     * chance of moving that the entity has, and whether or not the entity is
     * avoidable.
     * 
     * @param name The name of the entity
     * @param single The name given to a single unit of this entity
     * @param plural The name given to multiple units of this entity
     * @param health The amount of health the entity has
     * @param movingChance the chance of moving to a random location that the
     *                     entity has.
     * @param location Where this MobileEntity currently is
     * @param avoidable Whether or not the player can run away from the entity
     */
    public MobileEntity(String name, String single, String plural, int health, float movingChance, Location location, boolean avoidable){
        
        super(name, single, plural, health, avoidable);
        
        this.movingChance = movingChance;
        this.location = location;
        
        // Register this MobileEntity with the CommandParser
        if(location != null) CommandParser.addEventHandler(this);
        
    }
    
    /**
     * Returns the chance that the entity has of moving to a random location
     * after each entered command
     * 
     * @return the chance that the entity has of being of mobile
     */
    public float getMovingChance(){
        
        return this.movingChance;
        
    }
    
    /**
     * Sets the chance that the entity has of moving to a random location after
     * each entered command
     * 
     * @param movingChance the chance that the entity has of being of mobile
     */
    public void setMovingChance(float movingChance){
        
        this.movingChance = movingChance;
        
    }
    
    /**
     * Returns the current location of the entity.
     * 
     * @return Where the entity is at the moment
     */
    public Location getLocation(){
        
        return this.location;
        
    }
    
    /**
     * Sets the current location of the entity.
     * 
     * @param location Where the entity is at the moment
     */
    public void setLocation(Location location){
        
        this.location = location;
        
    }
    
    /**
     * Returns whether or not the entity can climb upwards or downwards.
     * 
     * @return whether or not the entity can climb
     */
    public boolean canClimb(){
        
        return this.canClimb;
        
    }
    
    /**
     * Sets whether or not the entity is able to climb vertically
     * 
     * @param canClimb whether or not the entity is able to climb vertically
     */
    public void setCanClimb(boolean canClimb){
        
        this.canClimb = canClimb;
        
    }
    
    /**
     * The method that calculates whether or not the mobile entity will be
     * moving, and, if so, in which direction.
     */
    private void move(){
        
        // First check if our animal will move
        if(Math.random() <= this.movingChance){

            // A list of places where we can go
            List<Exit> possibleExits = new ArrayList<>();

            for (int i = 0; i < this.location.getExits().size(); i++) {

                switch(this.location.getExits().get(i).getDirectionName()){

                    case "UP":
                    case "DOWN":
                        if(canClimb) possibleExits.add(this.location.getExits().get(i));
                        break;

                    default:
                        possibleExits.add(this.location.getExits().get(i));
                        break;

                }

            }
            
            // Check that we can actually go somewhere
            if(possibleExits.size() > 0){

                // Now choose a random location to move to
                Location newLocation = possibleExits.get((int) (Math.random() * possibleExits.size())).getLeadsTo();

                // And do the move
                this.getLocation().removeEntity(this);
                this.getLocation().addEntityShadow(new EntityShadow(this));
                this.setLocation(newLocation);

                newLocation.addEntity(this);
                
                // Make sure this MobileEntity won't move immediately
                this.hasMovedRecently = true;
            
            }

        }
        
    }
    
    /**
     * A method that creates a duplicate copy of this MobileEntity, at a
     * specified location
     * 
     * @param location the location of this new MobileEntity
     * 
     * @return a new clone of this object, at a specified location
     */
    public MobileEntity place(Location location){
        
        return new MobileEntity(this.getName(), this.getSingleName(), this.getPluralName(), this.getHealth(), this.getMovingChance(), location, this.isAvoidable());
        
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void fireEvent(String event) {
        
        switch(event){
            
            case "GO":
                if(hasMovedRecently){
                    hasMovedRecently = false;
                } else this.move();
                break;
            
        }
        
    }
    
}
