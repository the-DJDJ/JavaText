package org.beanplant.JavaText.io;

import org.beanplant.JavaText.handlers.CommandLockHandler;
import org.beanplant.JavaText.handlers.EventHandler;
import org.beanplant.JavaText.npc.Entity;
import org.beanplant.JavaText.world.exit.Exit;
import org.beanplant.JavaText.world.World;
import org.beanplant.JavaText.user.Item;
import org.beanplant.JavaText.user.ItemStack;
import org.beanplant.JavaText.util.StringTools;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses the commands, and executes activities performed in the game.
 *
 * @author the_DJDJ
 */
public class CommandParser {
    
    /** The world that all commands are executed on. */
    private World world;
    
    /** The previous world, used for when the user switches worlds. */
    private World oldWorld;
    
    /** The String that stores the command the user entered. */
    private String command = new String();
    
    /** The String that stores the arguments for the user-entered command. */
    private String arguments = new String();
    
    /** A list of all of the lock handlers currently active. */
    private final List<CommandLockHandler> commandHandlers = new ArrayList<>();
    
    /** A list of all the EventHandlers currently active. */
    private static final List<EventHandler> eventHandlers = new ArrayList<>();
    
    /**
     * The default constructor. This creates the environment in which to
     * interpret commands, as well as gets the world to execute commands on.
     * 
     * @param world The world to execute commands on
     */
    public CommandParser(World world){
        
        this.world = world;
        
    }
    
    /**
     * The method that actually executes the command. This method gets the
     * command that the user entered, and splits it into the command and its
     * arguments. Once the command has been split up, this calls the appropriate
     * method to execute the instructions.
     * 
     * @param input The command to execute
     */
    public void parse(String input){
        
        // Clear our output screen
        world.getOutputStream().initialisePrint();
        
        if(input.length() <= 2 && !input.equals("NO")){
        
            go(input);
            triggerEvent("GO");
        
        } else {
        
            try {

                command = input.substring(0, input.indexOf(" ")).trim();

            } catch (StringIndexOutOfBoundsException ex){

                command = input;        

            } finally {

                try {

                    arguments = input.substring(input.indexOf(" "), input.length()).trim();

                } catch (StringIndexOutOfBoundsException ex){}

            }
            
            if(!command.equals("YES") && !command.equals("NO")) this.removeAllLockHandlers();

            switch(command){

                case "GO":
                    this.go(arguments);
                    break;

                case "TAKE":
                    this.take(arguments);
                    break;

                case "DROP":
                    this.drop(arguments);
                    break;

                case "LOOT":
                    this.inventory();
                    break;

                case "INSPECT":
                    this.inspect(arguments);
                    break;

                case "LOOK":
                    this.look();
                    break;
                    
                case "UNLOCK":
                    this.unlock(arguments);
                    break;
                    
                case "LOCK":
                    break;
                    
                case "HEALTH":
                    this.health();
                    break;
                    
                case "HIT":
                case "KILL":
                    this.hit(arguments);
                    break;
                    
                case "HOST":
                case "START":
                    this.host(arguments);
                    break;
                    
                case "STOP":
                    this.stop();
                    break;
                    
                case "CONNECT":
                    this.connect(arguments);
                    break;
                    
                case "DISCONNECT":
                    this.disconnect();
                    break;

                case "SAVE":
                    this.save();
                    break;

                case "LOAD":
                case "RESTORE":
                    this.load();
                    break;

                case "HELP":
                    this.help();
                    break;

                case "QUIT":
                    this.quit();
                    break;
                    
                case "YES":
                    this.confirm();
                    break;
                    
                case "NO":
                    this.deny();
                    break;
                    
                default:
                    this.unknown();
                    break;

            }
            
            // Notify event listeners
            triggerEvent(command);
        
        }
        
    }
    
    /**
     * The yes command. This is usually used when a command has been locked, for
     * instance if the player needs to confirm something.
     */
    private void confirm(){
        
        if(this.commandHandlers.isEmpty()) {
            
            world.getOutputStream().printSpaced("No.", WidthLimitedOutputStream.BOTH);
            
        } else {
            
            for (CommandLockHandler commandHandler : this.commandHandlers) {
                
                commandHandler.handleCommand("YES");
            
            }
            
        }
        
        this.removeAllLockHandlers();
        
    }
    
    /**
     * The no command. This is usually used when a command has been locked, for
     * instance if the player needs to deny something.
     */
    private void deny(){
        
        if(this.commandHandlers.isEmpty()) {
            
            world.getOutputStream().printSpaced("Yes.", WidthLimitedOutputStream.BOTH);
            
        } else {
            
            for (CommandLockHandler commandHandler : this.commandHandlers) {
                
                commandHandler.handleCommand("NO");
            
            }
            
        }
        
        this.removeAllLockHandlers();
        
    }
    
    /**
     * The command that allows a user to move from one location to another. This
     * takes the direction as a parameter, and sets the world's current location
     * to whatever is in that direction.
     * 
     * @param arguments The direction for the user to go.
     */
    private void go(String arguments){
        
        boolean valid = false;

        // Check if the direction is valid
        for (Exit exit : world.getPlayer().getLocation().getExits()) {
            
            if(arguments.equals(exit.getDirectionName()) || arguments.equals(exit.getShortDirectionName())){
                
                if((!world.getPlayer().getLocation().hasBoss()) || (world.getPlayer().getLocation().hasBoss() && world.getPlayer().getLocation().getBoss().isAvoidable())){
                
                    if(!exit.isLocked()){

                        // Set location to the location pointed to by exit
                        world.getPlayer().setLocation(exit.getLeadsTo());

                        // Show new location
                        world.showLocation(false);

                    } else {

                        world.getOutputStream().printSpaced(exit.getType().getLockedMessage(), WidthLimitedOutputStream.BOTH);

                    }
                
                } else {
                    
                    if(world.getPlayer().getLocation().getBoss().getName().contains(" ")){
                        
                        world.getOutputStream().printSpaced("The " + world.getPlayer().getLocation().getBoss().getName().toLowerCase().substring(world.getPlayer().getLocation().getBoss().getName().lastIndexOf(" ")) + " attacks you! You lose " + world.getPlayer().getLocation().getBoss().getDamage() + "HP!", WidthLimitedOutputStream.BOTH);
                    
                        
                    } else {
                        
                        world.getOutputStream().printSpaced("The " + world.getPlayer().getLocation().getBoss().getName().toLowerCase() + " attacks you! You lose " + world.getPlayer().getLocation().getBoss().getDamage() + "HP!", WidthLimitedOutputStream.BOTH);
                        
                    }
                    
                    world.getPlayer().setHealth(world.getPlayer().getHealth() - world.getPlayer().getLocation().getBoss().getDamage());
                    
                }
                
                valid = true;

            }
        
        }
        
        if(!valid){
            
            world.getOutputStream().printSpaced("You cannot go that way.", WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
    /**
     * The command to pick an item up from the world. This checks if the user
     * has enough space in their inventory, and then adds the item to the
     * inventory whilst removing it from the current location in the world.
     * 
     * @param arguments The name of the item to take
     */
    private void take(String arguments){
        
        Item item = new Item().getItem(arguments);
        
        boolean valid = false;
        int index = 0;
        
        for (int i = 0; i < world.getPlayer().getLocation().getItems().size(); i++) {
            
            try{
            
                if(world.getPlayer().getLocation().getItems().get(i).getName().equals(item.getName())){

                    valid = true;
                    index = i;

                }
            
            } catch (NullPointerException ex){}
            
        }
        
        if(valid){
            
            if(world.getPlayer().getInventory().addItem(item)){
                
                if(world.getPlayer().getLocation().getItems().get(index).isStack()){
                    
                    world.getPlayer().getLocation().getItems().set(index, ((ItemStack) world.getPlayer().getLocation().getItems().get(index)).remove(1));
                            
                } else {
            
                    world.getPlayer().getLocation().getItems().remove(item);
                    
                }
            
                world.getOutputStream().printSpaced("You picked up the " + item.getName().toLowerCase(), WidthLimitedOutputStream.BOTH);
                
            } else {
                
                world.getOutputStream().printSpaced("You don't have enough space left in your inventory to pick up the " + item.getName().toLowerCase(), WidthLimitedOutputStream.BOTH);
                
            }
            
        } else {
            
            if(arguments.isEmpty()){
                
                world.getOutputStream().printSpaced("Take what?", WidthLimitedOutputStream.BOTH);
                
            } else if (item == null) {
                
                world.getOutputStream().printSpaced("I don't know what a" + (StringTools.startsWithVowel(arguments) ? "n " : " ") + arguments.toLowerCase() + " is.", WidthLimitedOutputStream.BOTH);
                
            } else {
            
                world.getOutputStream().printSpaced("I don't see " + item.getSingleName() + " here...", WidthLimitedOutputStream.BOTH);
            
            }
            
        }
        
    }
    
    /**
     * The command to drop an item from the user's inventory. This checks if the
     * user has the item in their inventory, and then removes the item from the
     * inventory whilst adding it from the current location in the world.
     * 
     * @param arguments The name of the item to drop
     */
    private void drop(String arguments){
        
        Item item = new Item().getItem(arguments);
        
        if(world.getPlayer().getInventory().contains(item)){
            
            world.getPlayer().getInventory().removeItem(item);
            
            boolean present = false;
            int index = 0;
            
            for (int i = 0; i < world.getPlayer().getLocation().getItems().size(); i++) {
                
                if(world.getPlayer().getLocation().getItems().get(i).getName().equals(item.getName())){
                    
                    present = true;
                    index = i;
                    
                }
                
            }
            
            if(present){
                
                if(world.getPlayer().getLocation().getItems().get(index).isStack()){
                    
                    world.getPlayer().getLocation().getItems().set(index, ((ItemStack) world.getPlayer().getLocation().getItems().get(index)).add(1));
                    
                } else {
                    
                    world.getPlayer().getLocation().getItems().set(index, new ItemStack(item, 2));
                    
                }
                
            } else {
            
                world.getPlayer().getLocation().addItem(item);
                
            }
            
            world.getOutputStream().printSpaced("You dropped the " + item.getName().toLowerCase(), WidthLimitedOutputStream.BOTH);
            
        } else {  
            
            if(arguments.isEmpty()){
                
                world.getOutputStream().printSpaced("Drop what?", WidthLimitedOutputStream.BOTH);
                
            } else if (item != null) {
            
                world.getOutputStream().printSpaced("You don't own " + item.getSingleName(), WidthLimitedOutputStream.BOTH);
            
            } else {
            
                world.getOutputStream().printSpaced("I don't know what a" + (StringTools.startsWithVowel(arguments) ? "n " : " ") + arguments.toLowerCase() + " is.", WidthLimitedOutputStream.BOTH);
            
            }   
        
        }
        
    }
    
    /**
     * The inventory command. This displays the contents of the user's inventory
     * to them.
     */
    private void inventory(){
        
        world.getOutputStream().printSpaced(world.getPlayer().getInventory().toString(), WidthLimitedOutputStream.BOTH);
        
    }
    
    /**
     * The inspect command. This displays information about the selected item.
     * 
     * @param arguments The item to inspect.
     */
    private void inspect(String arguments){
        
        if(!arguments.isEmpty()){
            
            if(new Item().isValidItem(arguments)){
        
                Item item = new Item().getItem(arguments);

                if(world.getPlayer().getInventory().contains(item) || world.getPlayer().getLocation().getItems().contains(item)) {

                    world.getOutputStream().printSpaced(item.getDescription(), WidthLimitedOutputStream.BOTH);

                } else {
                    
                    boolean present = false;
                    
                    for (int i = 0; i < world.getPlayer().getLocation().getItems().size(); i++) {
                        
                        if(world.getPlayer().getLocation().getItems().get(i).getName().equals(item.getName())){
                            
                            world.getOutputStream().printSpaced(item.getDescription(), WidthLimitedOutputStream.BOTH);
                            
                            present = true;
                            
                        }
                        
                    }
                    
                    if(!present){

                        world.getOutputStream().printSpaced("I don't see " + item.getSingleName() + " here.", WidthLimitedOutputStream.BOTH);
                    
                    }

                }
            
            } else {
                
                world.getOutputStream().printSpaced("What is a " + arguments.toLowerCase() + "?", WidthLimitedOutputStream.BOTH);
                
            }
            
        } else {
            
            world.getOutputStream().printSpaced("Inspect what?", WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
    /**
     * The look command. This shows the user the current location that they are
     * in in case they forget for some reason.
     */
    private void look(){
        
        world.showLocation(true);
        
    }
    
    /**
     * The unlock command. This locks an exit so that the user can no longer
     * move through it.
     * 
     * @param command
     * @param arguments 
     */
    private void unlock(String arguments){
        
        for (int i = 0; i < world.getPlayer().getLocation().getExits().size(); i++) {
            
            if(world.getPlayer().getLocation().getExits().get(i).getType().getName().equalsIgnoreCase(arguments)){
                
                if(world.getPlayer().getLocation().getExits().get(i).isLocked()){
                    
                    if(world.getPlayer().getLocation().getExits().get(i).isLockable()){
                    
                        if(world.getPlayer().getInventory().contains(world.getPlayer().getLocation().getExits().get(i).getType().getKey())){

                            world.getPlayer().getLocation().getExits().get(i).setLocked(false);
                            world.getOutputStream().printSpaced(world.getPlayer().getLocation().getExits().get(i).getType().getUnlockMessage(), WidthLimitedOutputStream.BOTH);

                        } else {

                            world.getOutputStream().printSpaced("You'll need " + world.getPlayer().getLocation().getExits().get(i).getType().getKey().get(0).getSingleName() + " to do that", WidthLimitedOutputStream.BOTH);

                        }
                    
                    } else {
                        
                        world.getOutputStream().printSpaced(world.getPlayer().getLocation().getExits().get(i).getType().getLockedMessage(), WidthLimitedOutputStream.BOTH);
                        
                    }
                    
                } else {
                    
                    world.getOutputStream().printSpaced(world.getPlayer().getLocation().getExits().get(i).getType().getUnlockedMessage(), WidthLimitedOutputStream.BOTH);
                    
                }
                
                break;
                
            }
            
        }
        
    }
    
    /**
     * The health command. This displays the amount of health that the user
     * currently has.
     */
    private void health(){
        
        world.getOutputStream().printSpaced("You currently have " + world.getPlayer().getHealth() + "HP.", WidthLimitedOutputStream.BOTH);
        
    }
    
    /**
     * The hit command. This deals damage to a specific entity, either by
     * hitting it with the player's fist, or a weapon
     * 
     * @param arguments the entity to hit, and, if applicable, the weapon to use
     */
    private void hit(String arguments){
        
        if(!arguments.trim().isEmpty()){
            
            // The entity to hit
            Entity entity = null;
            
            // The name of the entity to search for
            String name = ((arguments.contains(" WITH ")) ? arguments.substring(0, arguments.indexOf(" WITH ")) : arguments).trim();
            
            // First find which entity to hit
            if(world.getPlayer().getLocation().hasBoss() && world.getPlayer().getLocation().getBoss().getName().equalsIgnoreCase(name)){
                
                entity = world.getPlayer().getLocation().getBoss();
                
            } else {
            
                for (int i = 0; i < world.getPlayer().getLocation().getEntities().size(); i++) {

                    if(world.getPlayer().getLocation().getEntities().get(i).getName()
                            .equalsIgnoreCase(name)){

                        entity = world.getPlayer().getLocation().getEntities().get(i);

                    }

                }
                            
            }
            
            // Check that it has been found
            if(entity == null){
                    
                world.getOutputStream().printSpaced("Hmm, there doesn't seem to be a " + name.toLowerCase() + " here...", WidthLimitedOutputStream.BOTH);
                    
            } else {
                
                // Set the default damage that your fist deals
                int damage = 1;
                
                // Check if the user can hit with something more powerful
                if(arguments.contains(" WITH ")){
                    
                    String itemName = arguments.substring(arguments.indexOf(" WITH ") + 6).toLowerCase().trim();
                    
                    if(new Item().isValidItem(itemName)){
                    
                        if(world.getPlayer().getInventory().contains(new Item().getItem(itemName))){

                            // Work out the damage
                            damage = new Item().getItem(itemName).getDamage();
                            
                            // Hurt the entity
                            entity.setHealth(entity.getHealth() - damage);

                            // Check if the entity is still alive
                            if(entity.getHealth() > 0){

                                world.getOutputStream().printSpaced("You dealt " + damage + "HP to the " + name.toLowerCase() + ".", WidthLimitedOutputStream.BOTH);

                            } else {

                                world.getOutputStream().printSpaced("You killed the " + name.toLowerCase() + ".", WidthLimitedOutputStream.BOTH);
                                world.getPlayer().getLocation().getEntities().remove(entity);

                            }

                        } else {
                            
                            world.getOutputStream().printSpaced("You don't have a " + (StringTools.startsWithVowel(itemName) ? "n " : " ") + itemName + ".", WidthLimitedOutputStream.BOTH);
                            
                        }
                    
                    } else {
                        
                        world.getOutputStream().printSpaced("I don't know what a" + (StringTools.startsWithVowel(itemName) ? "n " : " ") + itemName + " is.", WidthLimitedOutputStream.BOTH);
                        
                    }
                    
                } else {
                    
                    // Hurt the entity
                    entity.setHealth(entity.getHealth() - damage);

                    // Check if the entity is still alive
                    if(entity.getHealth() > 0){

                        world.getOutputStream().printSpaced("You dealt " + damage + "HP to the " + name.toLowerCase() + ".", WidthLimitedOutputStream.BOTH);

                    } else {

                        world.getOutputStream().printSpaced("You killed the " + name.toLowerCase() + ".", WidthLimitedOutputStream.BOTH);
                        world.getPlayer().getLocation().getEntities().remove(entity);

                    }
                    
                }
                                
            }
                    
            
        } else {
            
            world.getOutputStream().printSpaced("Hit what?", WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
    /**
     * The host command. This opens up a user's savegame to other people so that
     * they can join the game later.
     * 
     * @param arguments The port on which to host the game
     */
    private void host(String arguments){
        
        
        
    }
    
    /**
     * The stop command. This stops a hosted savegame and restores it to a
     * standard singleplayer game
     */
    private void stop(){
        
        
        
    }
    
    /** 
     * The connect command. This connects to a hosted game on a specific port
     * 
     * @param arguments The port to connect to
     */
    private void connect(String arguments){
        
        
        
    }
    
    /**
     * The disconnect command. This disconnects from a save game and goes back
     * to normal singleplayer
     */
    private void disconnect(){
        
        try {
        
            if(world.getNetworkController().isActive()) {

                world.getNetworkController().close();
                world = oldWorld;
                
                world.getOutputStream().printSpaced("You have been disconnected from the server.", WidthLimitedOutputStream.BOTH);

            } else {

                world.getOutputStream().printSpaced("You aren't playing a multiplayer game!", WidthLimitedOutputStream.BOTH);

            }       
            
        } catch (NullPointerException ex){
            
            world.getOutputStream().printSpaced("You aren't playing a multiplayer game!", WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
    /**
     * The save command. This saves the user's game to a file so that it can be
     * loaded later.
     */
    private void save(){
        
        GameData.save(world);
        
    }
    
    /**
     * The method that adds a lock handler to the CommandParser, so that locked
     * commands do something
     * 
     * @param handler the handler to add
     */
    public void addLockHandler(CommandLockHandler handler){
        
        this.commandHandlers.add(handler);
        
    }
    
    /**
     * The method that removes all lock handlers, essentially removing all locks
     * on commands
     */
    public void removeAllLockHandlers(){
        
        for (int i = 0; i < this.commandHandlers.size(); i++) {
            
            this.commandHandlers.remove(0);
            
        }
        
    }
    
    /**
     * The method that adds an event handler to the CommandParser, so that
     * events can be triggered
     * 
     * @param handler the handler to add
     */
    public static void addEventHandler(EventHandler handler){
        
        eventHandlers.add(handler);
        
    }
    
    /**
     * The method that removes an event handler from the CommandParser, so that
     * events relying on that handler are no longer triggered.
     * 
     * @param handler the handler to remove
     * @return whether or not the remove was successful
     */
    public static boolean removeEventHandler(EventHandler handler){
        
        return eventHandlers.remove(handler);
        
    }
    
    /**
     * The method that removes all event handlers from the CommandParser, so
     * that no new events will be trigger
     */
    public static void removeAllEventHandlers(){
        
        for (int i = 0; i < eventHandlers.size(); i++) {
            
            eventHandlers.remove(0);
            
        }
        
    }
    
    /**
     * The method used when an event is to be triggered. This goes through all
     * of the event handlers and fires the event to each one in turn.
     * 
     * @param event the event to fire
     */
    private void triggerEvent(String event){
        
        for (EventHandler eventHandler : eventHandlers) {
            
            eventHandler.fireEvent(event);
        
        }
        
    }
    
    /**
     * The load command. This restores the user's game state to what it was when
     * they saved the game.
     */
    private void load(){
        
        oldWorld = world;
        world = GameData.load(world);
        
        if(world != null){
            
            world.getOutputStream().printSpaced("Your game has been loaded!", WidthLimitedOutputStream.ABOVE);
            world.getOutputStream().printAcross("=");
            
            world.showLocation(false);
        
        }
        
    }
    
    /**
     * The help command. Let's see what happens when users run me!
     */
    private void help(){
        
        world.getOutputStream().printSpaced("Ummm, no.", WidthLimitedOutputStream.BOTH);
        
    }
    
    /**
     * The quit command. This is called if the user wishes to close the
     * application.
     */
    private void quit(){
        
        world.getOutputStream().printSpaced("Hmmmph, good riddance.", WidthLimitedOutputStream.BOTH);
        
        System.exit(0);
        
    }
    
    /**
     * The unknown command. This is called if the user enters an unrecognised
     * command.
     */
    private void unknown(){
        
        world.getOutputStream().printSpaced("Hmm, I expect to see a verb as the first word...", WidthLimitedOutputStream.BOTH); 
    
    }
    
}
