package BeanPlant.the_DJDJ.JavaText.world;

import BeanPlant.the_DJDJ.JavaText.world.exit.Exit;
import BeanPlant.the_DJDJ.JavaText.io.WidthLimitedOutputStream;
import BeanPlant.the_DJDJ.JavaText.user.Inventory;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The player world. This houses all locations and all exits and stuffs.
 * 
 * @author the_DJDJ
 */
public class World implements Serializable {
    
    /** The title of the location. */
    private String m_title;
    
    /** The description of the location. */
    private String m_description;
    
    /** The amount of health that the player has. */
    private int m_playerHealth = 100;
	
    /** List of Location objects. */
    private final List<Location> locations;

    /** List of Exit objects. */
    private final List<Exit> exits;

    /** The current location of the player. */
    private Location currentLocation;
    
    /** The player's inventory. */
    private final Inventory inventory;

    /** Output stream for gaming system. */
    transient private WidthLimitedOutputStream output;

    /**
     * The default constructor. Assigns default values to all of the
     * variables
     */
    public World(){
        
        this(new String(), new String());
    
    }
    
    /**
     * A constructor. This assigns default values to all of the
     * variables, but with a specified title
     * 
     * @param title The title of the world
     */
    public World(String title){
        
        this(title, new String());
        
    }
    
    /**
     * The full constructor. This assigns default values to all of the
     * variables, but with a specified title and description
     * 
     * @param title The title of the world
     * @param description The description of the world
     */
    public World(String title, String description){
        
        // Set the title and description
        this.m_title = title;
        this.m_description = description;
            
        // Instantiate lists for location/exits
        this.locations = new ArrayList();
        this.exits = new ArrayList();
        
        // Create the inventory
        this.inventory = new Inventory();

        // The default location of a player isn't known
        this.currentLocation = null;

        // By default, use standard output
        this.setOutputStream(System.out, 72);
    
    }
    
    /**
     * Returns the title of the world.
     * 
     * @return The title of the world
     */
    public String getTitle(){
            
        return this.m_title;
    
    }

    /**
     * Updates or sets the title of the world.
     * 
     * @param title The new title of the world.
     */
    public void setTitle(String title) {
            
        this.m_title = title;
    
    }

    /**
     * Returns the description of the world.
     * 
     * @return The description of the world
     */
    public String getDescription() {
            
        return this.m_description;
    
    }

    /**
     * Updates or sets the description of the world.
     * 
     * @param description The new description of the world
     */
    public void setDescription(String description) {
            
        this.m_description = description;
    
    }
    
    /**
     * Returns the current health of the player.
     * 
     * @return the current health of the player
     */
    public int getPlayerHealth(){
        
        return this.m_playerHealth;
        
    }
    
    /**
     * Updates or sets the health of the player.
     * 
     * @param health The new health of the player
     */
    public void setPlayerHealth(int health){
        
        this.m_playerHealth = health;
        
    }

    /**
     * Returns the current location of the player.
     * 
     * @return The player's current location
     */
    public Location getCurrentLocation(){
        
        return this.currentLocation;
    
    }

    /**
     * Assigns a new location to the current location of the player.
     * 
     * @param newLocation The new location that the player is at.
     */
    public void setCurrentLocation(Location newLocation){
        
        this.currentLocation = newLocation;
    
    }

    /** 
     * Adds new exit(s) to the gaming system.
     * 
     * @param exit The exit(s) to add
     */
    public void addExit(Exit... exit){
        
        for (int i = 0; i < exit.length; i++) {
            
            // Check if exit list already contains exit 
            if (!exits.contains(exit[i])){
            
                // Exit doesn't exist, and must be added
                exits.add(exit[i]);
            
            }
            
        }
                    
    }

    /**
     * Adds new location(s) to the gaming system.
     * 
     * @param location The location(s) to add.
     */
    public void addLocation(Location... location){
        
        for (int i = 0; i < location.length; i++) {
            
            // Check if location list already contains location 
            if (!locations.contains(location[i])){

                // Location doesn't exist, and must be added
                locations.add(location[i]);

            }
            
        }
    
    }

    /**
     * Sets the output stream for the gaming system.
     * 
     * @param out The OutputStream to use
     * @param width The width for the OutputStream to use.
     * 
     * @return The updated instance of the World
     */
    public World setOutputStream(OutputStream out, int width){
        
        this.output = new WidthLimitedOutputStream(out, width);
        return this;
    
    }
    
    /**
     * Sets the output stream for the gaming system to a specific
     * WidthLimitedOutputStream
     * 
     * @param output The WidthLimitedOutputStream to use
     * 
     * @return  The updated instance of the World
     */
    public World setOutputStream(WidthLimitedOutputStream output){
        
        this.output = output;
        return this;
        
    }
    
    /**
     * Returns the output stream of the gaming system.
     * 
     * @return The OutputStream
     */
    public WidthLimitedOutputStream getOutputStream(){
        
        return this.output;
        
    }
    
    /**
     * Returns the user's inventory
     * 
     * @return The user's inventory
     */
    public Inventory getInventory(){
        
        return this.inventory;
        
    }
    
    /**
     * Shows the game's current information.
     */
    public void showInformation(){
        
        if(!this.getTitle().isEmpty()){
            
            this.getOutputStream().printSpaced(this.getTitle(), WidthLimitedOutputStream.ABOVE);
            
        }
        
        if(!this.getDescription().isEmpty()){
            
            this.getOutputStream().printSpaced(this.getDescription(), WidthLimitedOutputStream.ABOVE);
            
        }
        
        if(!this.getTitle().isEmpty() || !this.getDescription().isEmpty()){
            
            this.getOutputStream().printAcross("=");
            
        }
        
    }

    /**
     * Shows the game's current location.
     * 
     * @param fullVisibility Whether or not the location having been visited
     * should be overridden
     */
    public void showLocation(boolean fullVisibility){
        
        // Show room title
	output.printSpaced(this.getCurrentLocation().getTitle(), WidthLimitedOutputStream.ABOVE);
		
	// Show room description 
        if(!this.getCurrentLocation().hasBeenVisited() || fullVisibility){
	
            output.printSpaced(this.getCurrentLocation().getDescription(), WidthLimitedOutputStream.ABOVE);
            this.getCurrentLocation().visit();
            
        }
        
        // Show any information on entities
        if(this.getCurrentLocation().hasEntities()){
            
            String entities = "There is ";
            
            for (int i = this.getCurrentLocation().getEntities().size() - 1; i >= 0; i--) {
                        
                if(i != this.getCurrentLocation().getEntities().size() - 1){

                    if(i == 0){

                        entities += " and ";

                    } else {

                        entities += ", ";

                    }

                }

                if(this.getCurrentLocation().getEntities().get(i).isCollection()){

                    entities += this.getCurrentLocation().getEntities().get(i).getPluralName();

                } else {

                    entities += this.getCurrentLocation().getEntities().get(i).getSingleName();

                }

            }
            
            output.printSpaced(entities + " lingering about.", WidthLimitedOutputStream.ABOVE);
            
        }
        
        // Show information on entities that have moved away
        if(this.getCurrentLocation().hasEntityShadows()){
            
            String entities = "The ";
            
            for (int i = this.getCurrentLocation().getEntityShadows().size() - 1; i >= 0; i--) {
                        
                if(i != this.getCurrentLocation().getEntityShadows().size() - 1){

                    if(i == 0){

                        entities += " and the ";

                    } else {

                        entities += ", the ";

                    }

                }

                if(this.getCurrentLocation().getEntityShadows().get(i).isCollection()){

                    entities += (this.getCurrentLocation().getEntityShadows().get(i).getPluralName().startsWith("a ")) ?
                                 this.getCurrentLocation().getEntityShadows().get(i).getPluralName().replaceFirst("a ", "") :
                                 this.getCurrentLocation().getEntityShadows().get(i).getPluralName();

                } else {

                    entities += (this.getCurrentLocation().getEntityShadows().get(i).getSingleName().startsWith("a ")) ?
                                 this.getCurrentLocation().getEntityShadows().get(i).getSingleName().replaceFirst("a ", "") :
                                 this.getCurrentLocation().getEntityShadows().get(i).getSingleName();

                }

            }
            
            output.printSpaced(entities + ((this.getCurrentLocation().getEntityShadows().size() > 1) ? " have " : " has ") + " moved away.", WidthLimitedOutputStream.ABOVE);
            
            // And clear all shadows
            this.getCurrentLocation().removeEntityShadows();
            
        }
        
        // Display information on bosses
        if(this.getCurrentLocation().hasBoss()){
            
            String prefix = "A";            
            
            switch(this.getCurrentLocation().getBoss().getName().toUpperCase().charAt(0)){
            
                case 'A':
                case 'E':
                case 'I':
                case 'O':
                case 'U':
                    prefix += "n";
                    break;
            
            }
            
            output.printSpaced(prefix + " " + this.getCurrentLocation().getBoss().getName().toLowerCase() + " (" + this.getCurrentLocation().getBoss().getHealth() + " HP) lurks menacingly...", WidthLimitedOutputStream.ABOVE);
            
        }
        
        // Show items
        if(!this.getCurrentLocation().getItems().isEmpty()){
            
            String items = "You can see ";
            
            for (int i = this.getCurrentLocation().getItems().size() - 1; i >= 0; i--) {
                        
                if(i != this.getCurrentLocation().getItems().size() - 1){

                    if(i == 0){

                        items += " and ";

                    } else {

                        items += ", ";

                    }

                }

                if(this.getCurrentLocation().getItems().get(i).isStack()){

                    items += this.getCurrentLocation().getItems().get(i).getPluralName();

                } else {

                    items += this.getCurrentLocation().getItems().get(i).getSingleName();

                }

            }
            
            output.printSpaced(items + ".", WidthLimitedOutputStream.ABOVE);
            
        }
        
        // Show available exits        
        String exits = "You can go ";
        
	switch(this.getCurrentLocation().getExits().size()){
            
            case 0:
                exits += "nowhere";
                break;
                
            case 1:
                exits += String.valueOf(this.getCurrentLocation().getExits().get(0));
                break;
                
            case 2:
                exits += this.getCurrentLocation().getExits().get(0) + " or " + this.getCurrentLocation().getExits().get(1);
                break;
                
            default:
                for (int i = 0; i < this.getCurrentLocation().getExits().size(); i++) {
                    
                    if(i != this.getCurrentLocation().getExits().size() - 2){
                        
                        exits += this.getCurrentLocation().getExits().get(i) + ", ";
                        
                    } else {
                        
                        exits += this.getCurrentLocation().getExits().get(this.getCurrentLocation().getExits().size() - 2) + " or " + this.getCurrentLocation().getExits().get(this.getCurrentLocation().getExits().size() - 1);
                        break;
                        
                    }
                    
                }
            break;
            
        }
        
        output.printSpaced(exits + " from here.", WidthLimitedOutputStream.BOTH);
        
    }
    
    
    /**
     * A simple method that sets the width of the output stream of the world,
     * whilst returning the world object for code streamlining
     * 
     * @param width the new width to use in the output stream
     * 
     * @return this World object
     */
    public World setOutputStreamWidth(int width){
        
        // Update our output width
        if(width != -1) this.getOutputStream().setWidth(width);
        
        // And return our World object
        return this;
        
    }

}