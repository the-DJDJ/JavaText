package BeanPlant.the_DJDJ.JavaText.io;

import BeanPlant.the_DJDJ.JavaText.handlers.CommandLockHandler;
import BeanPlant.the_DJDJ.JavaText.JavaText;
import BeanPlant.the_DJDJ.JavaText.world.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * A basic class that handles all of the saving and loading operations of the
 * world for the application. All methods herein are static, and can be
 * referenced from anywhere, as long as the main method has already been called,
 * to avoid NullPointerExceptions. This is not a problem however, as that method
 * is called whenever the application starts up.
 *
 * @author the_DJDJ
 */
public class GameData implements CommandLockHandler{
    
    /** The filename for the game's savedata. */
    private static final String filename = "gamedata.dat";
    
    /** The stream to create the output file. */
    private static FileOutputStream outputFile;
    
    /** The stream to write the object output. */
    private static ObjectOutputStream outputObject;
    
    /** The stream to read the input file. */
    private static FileInputStream inputFile;
    
    /** The stream to read the object input. */
    private static ObjectInputStream inputObject;
    
    /** The place to temporarily store the world to save while we wait for confirmation. */
    private static World temporaryWorld;

    /**
     * The method that saves the game state. This saves all objects associated
     * with the current specified world, and writes them to the disk.
     * 
     * @param world The world to write to disk.
     */
    public static void save(World world){
        
        temporaryWorld = world;
          
        if(new File(filename).exists()) {
                
            world.getOutputStream().printSpaced("You already have a saved game... Do you want to overwrite it?", WidthLimitedOutputStream.BOTH);

            JavaText.getCommandParser().addLockHandler(new GameData());
                
        } else {
            
            continueSave(world);
            
        }
            
    }
    
    /**
     * The method to continue saving the world if the file does not exist, or
     * the user stupidly types yes
     * 
     * @param world the world to save
     */
    private static void continueSave(World world){
        
        try {
            
            // Create a file to write game system
            outputFile = new FileOutputStream (filename);
            
            // Create an object output stream, linked to out
            outputObject = new ObjectOutputStream (outputFile);
            
            // Write game system to object store
            outputObject.writeObject(world);
            
            // Close object output stream
            outputObject.close();
            
            world.getOutputStream().printSpaced("Your game has been saved!", WidthLimitedOutputStream.BOTH);
        
        } catch (IOException ex) {
            
            world.getOutputStream().printSpaced("Hmm, for some reason your game couldn't be saved...", WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
    /**
     * The method that loads the game state. This reads the gamedata file, and
     * recreates all needed objects in the specified world variable.
     * 
     * @param world the world to get an output stream to print to if something
     *              goes wrong.
     * 
     * @return The loaded world
     */
    public static World load(World world){
        
        try {
            
            // Create a file input stream
            inputFile = new FileInputStream(filename);
            
            // Create an object input stream
            inputObject = new ObjectInputStream(inputFile);
            
            // Read an object in from object store, and cast it to a World
            return ((World) inputObject.readObject()).setOutputStream(world.getOutputStream());
        
        } catch (InvalidClassException ex) {
            
            world.getOutputStream().printSpaced("Your saved game appears to be from an older version of JavaText...", WidthLimitedOutputStream.BOTH);
            
            return null;
            
        } catch (IOException | ClassNotFoundException ex) {
            
            world.getOutputStream().printSpaced("Could not load your game", WidthLimitedOutputStream.BOTH);
            
            return null;
        
        }
        
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public void handleCommand(String command) {
    
        switch(command){
            
            case "YES":
                continueSave(temporaryWorld);
                break;
                
            case "NO":
                temporaryWorld.getOutputStream().printSpaced("Well, okay then.", WidthLimitedOutputStream.BOTH);
                break;
            
        }
        
    }
    
}
