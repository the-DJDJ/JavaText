package org.beanplant.JavaText.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.beanplant.JavaText.handlers.CommandLockHandler;
import org.beanplant.JavaText.handlers.EventHandler;
import org.beanplant.JavaText.io.commands.CommandConfirm;
import org.beanplant.JavaText.io.commands.CommandDeny;
import org.beanplant.JavaText.io.commands.CommandDrop;
import org.beanplant.JavaText.io.commands.CommandGo;
import org.beanplant.JavaText.io.commands.CommandHealth;
import org.beanplant.JavaText.io.commands.CommandHelp;
import org.beanplant.JavaText.io.commands.CommandHit;
import org.beanplant.JavaText.io.commands.CommandInspect;
import org.beanplant.JavaText.io.commands.CommandInventory;
import org.beanplant.JavaText.io.commands.CommandLock;
import org.beanplant.JavaText.io.commands.CommandLook;
import org.beanplant.JavaText.io.commands.CommandQuit;
import org.beanplant.JavaText.io.commands.CommandTake;
import org.beanplant.JavaText.io.commands.CommandUnknown;
import org.beanplant.JavaText.io.commands.CommandUnlock;
import org.beanplant.JavaText.net.Message;
import org.beanplant.JavaText.npc.Entity;
import org.beanplant.JavaText.world.World;
import org.beanplant.JavaText.user.Item;

/**
 * Parses the commands, and executes activities performed in the game.
 *
 * @author the_DJDJ
 */
public final class CommandParser {
    
    /** The world that all commands are executed on. */
    private World world;
    
    /** The previous world, used for when the user switches worlds. */
    private World oldWorld;
    
    /** The String that stores the command the user entered. */
    private String command = new String();
    
    /** The String that stores the arguments for the user-entered command. */
    private String arguments = new String();
    
    private HashMap<String, Command> commands = new HashMap<>();
    
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
        
        this.registerCommand(new CommandGo(),        "GO", "MOVE");
        this.registerCommand(new CommandTake(),      "TAKE");
        this.registerCommand(new CommandDrop(),      "DROP");
        this.registerCommand(new CommandInventory(), "LOOT", "INVENTORY");
        this.registerCommand(new CommandHealth(),    "HEALTH");
        this.registerCommand(new CommandLook(),      "LOOK");
        this.registerCommand(new CommandInspect(),   "INSPECT");
        this.registerCommand(new CommandHit(),       "HIT", "KILL");
        this.registerCommand(new CommandLock(),      "LOCK");
        this.registerCommand(new CommandUnlock(),    "LOCK", "CLEAR");
        this.registerCommand(new CommandConfirm(),   "YES", "CONFIRM");
        this.registerCommand(new CommandDeny(),      "NO", "DENY");
        this.registerCommand(new CommandHelp(),      "HELP");
        this.registerCommand(new CommandUnknown(),   "UNKNOWN");
        this.registerCommand(new CommandQuit(),      "QUIT", "EXIT");
        
    }
    
    public void registerCommand(Command command, String... names) {
        
        for (String name : names) {
            
            this.commands.put(name, command);
        
        }
        
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
        
            this.commands.get("GO").execute(input);
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
            
            if(this.commands.containsKey(command)) {
            
                this.commands.get(command).execute(arguments);
                
            } else {
                
                this.commands.get("UNKNOWN").execute(arguments);
                
            }
            
            // Notify event listeners
            triggerEvent(command);

            switch(command){
                    
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

            }
        
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
        
        try {
        
            if(world.getNetworkController().isActive()) {
                
                world.getNetworkController().getMessageSender().sendMessage(new Message(Message.STOP, null));

                world.getNetworkController().close();
                world.getNetworkController().setActive(false);
                
                world.getOutputStream().printSpaced(world.getMessageBuilder().getHostDisconnectSuccessMessage(), WidthLimitedOutputStream.BOTH);

            } else {

                world.getOutputStream().printSpaced(world.getMessageBuilder().getHostNotSharingErrorMessage(), WidthLimitedOutputStream.BOTH);

            }       
            
        } catch (NullPointerException ex){
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getHostNotSharingErrorMessage(), WidthLimitedOutputStream.BOTH);
            
        } catch (IOException ex) {
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getHostDisconnectErrorMessage(), WidthLimitedOutputStream.BOTH);
            
        }
        
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
                
                world.getNetworkController().getMessageSender().sendMessage(new Message(Message.LOGOFF, null));

                world.getNetworkController().close();
                world.getNetworkController().setActive(false);
                                
                world = oldWorld;                
                world.getOutputStream().printSpaced(world.getMessageBuilder().getMultiplayerDisconnectSuccessMessage(), WidthLimitedOutputStream.BOTH);

            } else {
                
                world.getOutputStream().printSpaced(world.getMessageBuilder().getMultiplayerNotInGameErrorMessage(), WidthLimitedOutputStream.BOTH);

            }       
            
        } catch (NullPointerException ex) {
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getMultiplayerNotInGameErrorMessage(), WidthLimitedOutputStream.BOTH);
            
        } catch (IOException ex) {
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getMultiplayerDisconnectErrorMessage(), WidthLimitedOutputStream.BOTH);
            
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
        
        this.commandHandlers.clear();
        
    }
    
    /**
     * A simple method that fetches all the lock handlers.
     * 
     * @return a list of all lock handlers
     */
    public List<CommandLockHandler> getAllLockHandlers() {
        
        return this.commandHandlers;
        
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
        
        eventHandlers.clear();
        
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
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getGameLoadMessage(), WidthLimitedOutputStream.ABOVE);
            world.getOutputStream().printAcross("=");
            
            world.showLocation(false);
        
        }
        
    }
    
}
