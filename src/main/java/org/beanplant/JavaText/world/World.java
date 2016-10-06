package org.beanplant.JavaText.world;

import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.beanplant.JavaText.io.WidthLimitedOutputStream;
import org.beanplant.JavaText.net.NetworkController;
import org.beanplant.JavaText.user.Player;
import org.beanplant.JavaText.util.StringTools;

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
    
    /** The player in the world. */
    private Player player;
	
    /** List of Location objects. */
    private final List<Location> locations;

    /** Output stream for gaming system. */
    transient private WidthLimitedOutputStream output;
    
    /** The network controller for multiplayer games. */
    transient private NetworkController networkController;

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
            
        // Instantiate lists for location
        this.locations = new ArrayList();
        
        // Create a new player object
        this.player = new Player(null, 100, null);

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
     * Returns the player object.
     * 
     * @return the current player.
     */
    public Player getPlayer() {
        
        return this.player;
        
    }

    /**
     * Adds new location(s) to the gaming system.
     * 
     * @param location The location(s) to add.
     */
    public void addLocation(Location... location){
        
        for (Location currentLocation : location) {
            
            // Check if location list already contains location
            if (!locations.contains(currentLocation)) {
                
                // Location doesn't exist, and must be added
                locations.add(currentLocation);
            
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
        
        this.getOutputStream().reset();
        
    }

    /**
     * Shows the game's current location.
     * 
     * @param fullVisibility Whether or not the location having been visited
     * should be overridden
     */
    public void showLocation(boolean fullVisibility){
        
        // Show room title
	output.printSpaced(this.getPlayer().getLocation().getTitle(), WidthLimitedOutputStream.ABOVE);
		
	// Show room description 
        if(!this.getPlayer().getLocation().hasBeenVisited() || fullVisibility){
	
            output.printSpaced(this.getPlayer().getLocation().getDescription(), WidthLimitedOutputStream.ABOVE);
            this.getPlayer().getLocation().visit();
            
        }
        
        // Show any information on entities
        if(this.getPlayer().getLocation().hasEntities()){
            
            String entities = "There is ";
            
            for (int i = this.getPlayer().getLocation().getEntities().size() - 1; i >= 0; i--) {
                        
                if(i != this.getPlayer().getLocation().getEntities().size() - 1){

                    if(i == 0){

                        entities += " and ";

                    } else {

                        entities += ", ";

                    }

                }

                if(this.getPlayer().getLocation().getEntities().get(i).isCollection()){

                    entities += this.getPlayer().getLocation().getEntities().get(i).getPluralName();

                } else {

                    entities += this.getPlayer().getLocation().getEntities().get(i).getSingleName();

                }

            }
            
            output.printSpaced(entities + " lingering about.", WidthLimitedOutputStream.ABOVE);
            
        }
        
        // Show information on entities that have moved away
        if(this.getPlayer().getLocation().hasEntityShadows()){
            
            String entities = "The ";
            
            for (int i = this.getPlayer().getLocation().getEntityShadows().size() - 1; i >= 0; i--) {
                        
                if(i != this.getPlayer().getLocation().getEntityShadows().size() - 1){

                    if(i == 0){

                        entities += " and the ";

                    } else {

                        entities += ", the ";

                    }

                }

                if(this.getPlayer().getLocation().getEntityShadows().get(i).isCollection()){

                    entities += (this.getPlayer().getLocation().getEntityShadows().get(i).getPluralName().startsWith("a ")) ?
                                 this.getPlayer().getLocation().getEntityShadows().get(i).getPluralName().replaceFirst("a ", "") :
                                 this.getPlayer().getLocation().getEntityShadows().get(i).getPluralName();

                } else {

                    entities += (this.getPlayer().getLocation().getEntityShadows().get(i).getSingleName().startsWith("a ")) ?
                                 this.getPlayer().getLocation().getEntityShadows().get(i).getSingleName().replaceFirst("a ", "") :
                                 this.getPlayer().getLocation().getEntityShadows().get(i).getSingleName();

                }

            }
            
            output.printSpaced(entities + ((this.getPlayer().getLocation().getEntityShadows().size() > 1) ? " have " : " has ") + " moved away.", WidthLimitedOutputStream.ABOVE);
            
            // And clear all shadows
            this.getPlayer().getLocation().removeEntityShadows();
            
        }
        
        // Display information on bosses
        if(this.getPlayer().getLocation().hasBoss()){          
            
            output.printSpaced("A" + (StringTools.startsWithVowel(this.getPlayer().getLocation().getBoss().getName()) ? "n" : "") + " " + this.getPlayer().getLocation().getBoss().getName().toLowerCase() + " (" + this.getPlayer().getLocation().getBoss().getHealth() + " HP) lurks menacingly...", WidthLimitedOutputStream.ABOVE);
            
        }
        
        // Show items
        if(!this.getPlayer().getLocation().getItems().isEmpty()){
            
            String items = "You can see ";
            
            for (int i = this.getPlayer().getLocation().getItems().size() - 1; i >= 0; i--) {
                        
                if(i != this.getPlayer().getLocation().getItems().size() - 1){

                    if(i == 0){

                        items += " and ";

                    } else {

                        items += ", ";

                    }

                }

                if(this.getPlayer().getLocation().getItems().get(i).isStack()){

                    items += this.getPlayer().getLocation().getItems().get(i).getPluralName();

                } else {

                    items += this.getPlayer().getLocation().getItems().get(i).getSingleName();

                }

            }
            
            output.printSpaced(items + ".", WidthLimitedOutputStream.ABOVE);
            
        }
        
        // Show available exits        
        String exits = "You can go ";
        
	switch(this.getPlayer().getLocation().getExits().size()){
            
            case 0:
                exits += "nowhere";
                break;
                
            case 1:
                exits += String.valueOf(this.getPlayer().getLocation().getExits().get(0));
                break;
                
            case 2:
                exits += this.getPlayer().getLocation().getExits().get(0) + " or " + this.getPlayer().getLocation().getExits().get(1);
                break;
                
            default:
                for (int i = 0; i < this.getPlayer().getLocation().getExits().size(); i++) {
                    
                    if(i != this.getPlayer().getLocation().getExits().size() - 2){
                        
                        exits += this.getPlayer().getLocation().getExits().get(i) + ", ";
                        
                    } else {
                        
                        exits += this.getPlayer().getLocation().getExits().get(this.getPlayer().getLocation().getExits().size() - 2) + " or " + this.getPlayer().getLocation().getExits().get(this.getPlayer().getLocation().getExits().size() - 1);
                        break;
                        
                    }
                    
                }
            break;
            
        }
        
        output.printSpaced(exits + " from here.", WidthLimitedOutputStream.BOTH);
        
    }
    
    /**
     * A method used in finalising a loaded world. This fixes any potential
     * NullPointerExceptions and errors that may occur as a result of loading
     * transient objects from the disk.
     * 
     * At present, this method will remove entity shadows from each location so
     * as to avoid a NullPointerException, as EntityShadows are not written to
     * disk
     * 
     * @return This World object
     */
    public World load(){
        
        // Reset all entity shadows
        for (Location location : this.locations) {
            
            location.removeEntityShadows();
        
        }
        
        return this;
        
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