package BeanPlant.the_DJDJ.JavaText.io;

import BeanPlant.the_DJDJ.JavaText.world.exit.Exit;
import BeanPlant.the_DJDJ.JavaText.world.World;
import BeanPlant.the_DJDJ.JavaText.user.Item;
import BeanPlant.the_DJDJ.JavaText.user.ItemStack;

/**
 * Parses the commands, and executes activities performed in the game.
 *
 * @author the_djdj
 */
public class CommandParser {
    
    /** The world that all commands are executed on. */
    private World world;
    
    /** The String that stores the command the user entered. */
    private String command = new String();
    
    /** The String that stores the arguments for the user-entered command. */
    private String arguments = new String();
    
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
        
        if(input.length() <= 2){
        
            go(input);
        
        } else {
        
            try {

                command = input.substring(0, input.indexOf(" ")).trim();

            } catch (StringIndexOutOfBoundsException ex){

                command = input;        

            } finally{

                try {

                    arguments = input.substring(input.lastIndexOf(" "), input.length()).trim();

                } catch (StringIndexOutOfBoundsException ex){}

            }

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

                default:
                    this.unknown();
                    break;

            }
        
        }
        
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
        for (Exit exit : world.getCurrentLocation().getExits()) {
            
            if(arguments.equals(exit.getDirectionName()) || arguments.equals(exit.getShortDirectionName())){
                
                if((!world.getCurrentLocation().hasBoss()) || (world.getCurrentLocation().hasBoss() && world.getCurrentLocation().getBoss().isAvoidable())){
                
                    if(!exit.isLocked()){

                        // Set location to the location pointed to by exit
                        world.setCurrentLocation(exit.getLeadsTo());

                        // Show new location
                        world.showLocation(false);

                    } else {

                        world.getOutputStream().println();
                        world.getOutputStream().println(exit.getType().getLockedMessage());
                        world.getOutputStream().println();

                    }
                
                } else {
                    
                    world.getOutputStream().println();
                    
                    if(world.getCurrentLocation().getBoss().getName().contains(" ")){
                        
                        world.getOutputStream().println("The " + world.getCurrentLocation().getBoss().getName().toLowerCase().substring(world.getCurrentLocation().getBoss().getName().lastIndexOf(" ")) + " attacks you! You lose " + world.getCurrentLocation().getBoss().getDamage() + "HP!");
                    
                        
                    } else {
                        
                        world.getOutputStream().println("The " + world.getCurrentLocation().getBoss().getName().toLowerCase() + " attacks you! You lose " + world.getCurrentLocation().getBoss().getDamage() + "HP!");
                        
                    }
                    
                    world.getOutputStream().println();
                    
                    world.setPlayerHealth(world.getPlayerHealth() - world.getCurrentLocation().getBoss().getDamage());
                    
                }
                
                valid = true;

            }
        
        }
        
        if(!valid){
            
            world.getOutputStream().println();
            world.getOutputStream().println("You cannot go that way.");
            world.getOutputStream().println();
            
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
        
        for (int i = 0; i < world.getCurrentLocation().getItems().size(); i++) {
            
            try{
            
                if(world.getCurrentLocation().getItems().get(i).getName().equals(item.getName())){

                    valid = true;
                    index = i;

                }
            
            } catch (NullPointerException ex){}
            
        }
        
        if(valid){
            
            if(world.getInventory().addItem(item)){
                
                if(world.getCurrentLocation().getItems().get(index).isStack()){
                    
                    world.getCurrentLocation().getItems().set(index, ((ItemStack) world.getCurrentLocation().getItems().get(index)).remove(1));
                            
                } else {
            
                    world.getCurrentLocation().getItems().remove(item);
                    
                }
            
                world.getOutputStream().println();
                world.getOutputStream().println("You picked up the " + item.getName().toLowerCase());
                world.getOutputStream().println();
                
            } else {
                
                world.getOutputStream().println();
                world.getOutputStream().println("You don't have enough space left in your inventory to pick up the " + item.getName().toLowerCase());
                world.getOutputStream().println();
                
            }
            
        } else {
            
            world.getOutputStream().println();
            world.getOutputStream().println("I don't see a " + arguments.toLowerCase() + " here...");
            world.getOutputStream().println();
            
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
        
        if(world.getInventory().contains(item)){
            
            world.getInventory().removeItem(item);
            
            boolean present = false;
            int index = 0;
            
            for (int i = 0; i < world.getCurrentLocation().getItems().size(); i++) {
                
                if(world.getCurrentLocation().getItems().get(i).getName().equals(item.getName())){
                    
                    present = true;
                    index = i;
                    
                }
                
            }
            
            if(present){
                
                if(world.getCurrentLocation().getItems().get(index).isStack()){
                    
                    world.getCurrentLocation().getItems().set(index, ((ItemStack) world.getCurrentLocation().getItems().get(index)).add(1));
                    
                } else {
                    
                    world.getCurrentLocation().getItems().set(index, new ItemStack(item, 2));
                    
                }
                
            } else {
            
                world.getCurrentLocation().addItem(item);
                
            }
            
            world.getOutputStream().println();
            world.getOutputStream().println("You dropped the " + item.getName().toLowerCase());
            world.getOutputStream().println();
            
        } else if (item != null) {
            
            world.getOutputStream().println();
            world.getOutputStream().println("You don't own " + item.getSingleName());
            world.getOutputStream().println();
            
        } else {
            
            world.getOutputStream().println();
            world.getOutputStream().println("I don't know what a " + arguments.toLowerCase() + " is.");
            world.getOutputStream().println();
            
        }
        
    }
    
    /**
     * The inventory command. This displays the contents of the user's inventory
     * to them.
     */
    private void inventory(){
        
        world.getOutputStream().println();
        world.getOutputStream().println(world.getInventory().toString());
        world.getOutputStream().println();
        
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

                if(world.getInventory().contains(item) || world.getCurrentLocation().getItems().contains(item)) {

                    world.getOutputStream().println();
                    world.getOutputStream().println(item.getDescription());
                    world.getOutputStream().println();

                } else {
                    
                    boolean present = false;
                    
                    for (int i = 0; i < world.getCurrentLocation().getItems().size(); i++) {
                        
                        if(world.getCurrentLocation().getItems().get(i).getName().equals(item.getName())){
                            
                            world.getOutputStream().println();
                            world.getOutputStream().println(item.getDescription());
                            world.getOutputStream().println();
                            
                            present = true;
                            
                        }
                        
                    }
                    
                    if(!present){

                        world.getOutputStream().println();
                        world.getOutputStream().println("I don't see " + item.getSingleName() + " here.");
                        world.getOutputStream().println();
                    
                    }

                }
            
            } else {
                
                world.getOutputStream().println();
                world.getOutputStream().println("What is a " + arguments.toLowerCase() + "?");
                world.getOutputStream().println();
                
            }
            
        } else {
            
            world.getOutputStream().println();
            world.getOutputStream().println("Inspect what?");
            world.getOutputStream().println();
            
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
        
        for (int i = 0; i < world.getCurrentLocation().getExits().size(); i++) {
            
            if(world.getCurrentLocation().getExits().get(i).getType().getName().equalsIgnoreCase(arguments)){
                
                if(world.getCurrentLocation().getExits().get(i).isLocked()){
                    
                    if(world.getCurrentLocation().getExits().get(i).isLockable()){
                    
                        if(world.getInventory().contains(world.getCurrentLocation().getExits().get(i).getType().getKey())){

                            world.getCurrentLocation().getExits().get(i).setLocked(false);

                            world.getOutputStream().println();
                            world.getOutputStream().println(world.getCurrentLocation().getExits().get(i).getType().getUnlockMessage());
                            world.getOutputStream().println();

                        } else {

                            world.getOutputStream().println();
                            world.getOutputStream().println("You'll need " + world.getCurrentLocation().getExits().get(i).getType().getKey().get(0).getSingleName() + " to do that");
                            world.getOutputStream().println();

                        }
                    
                    } else {
                        
                        world.getOutputStream().println();
                        world.getOutputStream().println(world.getCurrentLocation().getExits().get(i).getType().getLockedMessage());
                        world.getOutputStream().println();
                        
                    }
                    
                } else {
                    
                    world.getOutputStream().println();
                    world.getOutputStream().println(world.getCurrentLocation().getExits().get(i).getType().getUnlockedMessage());
                    world.getOutputStream().println();
                    
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
        
        world.getOutputStream().println();
        world.getOutputStream().println("You currently have " + world.getPlayerHealth() + "HP.");
        world.getOutputStream().println();
        
    }
    
    /**
     * The save command. This saves the user's game to a file so that it can be
     * loaded later.
     */
    private void save(){
        
        GameData.save(world);
        
    }
    
    /**
     * The load command. This restores the user's game state to what it was when
     * they saved the game.
     */
    private void load(){
        
        world = GameData.load();
        world.showLocation(false);
        
    }
    
    /**
     * The help command. Let's see what happens when users run me!
     */
    private void help(){
        
        world.getOutputStream().println();
        world.getOutputStream().println("Ummm, no.");
        world.getOutputStream().println();  
        
    }
    
    /**
     * The quit command. This is called if the user wishes to close the
     * application.
     */
    private void quit(){
        
        world.getOutputStream().println();
        world.getOutputStream().println("Hmmmph, good riddance.");
        world.getOutputStream().println();  
        
        System.exit(0);
        
    }
    
    /**
     * The unknown command. This is called if the user enters an unrecognised
     * command.
     */
    private void unknown(){
        
        world.getOutputStream().println();
        world.getOutputStream().println("Hmm, I expect to see a verb as the first word...");
        world.getOutputStream().println();    
    
    }
    
}
