package BeanPlant.the_DJDJ.JavaText.io;

import BeanPlant.the_DJDJ.JavaText.world.World;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
public class GameData {
    
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

    /**
     * The method that saves the game state. This saves all objects associated
     * with the current specified world, and writes them to the disk.
     * 
     * @param world The world to write to disk.
     */
    public static void save(World world){
          
        try {
            
            // Create a file to write game system
            outputFile = new FileOutputStream (filename);
            
            // Create an object output stream, linked to out
            outputObject = new ObjectOutputStream (outputFile);
            
            // Write game system to object store
            outputObject.writeObject(world);
            
            // Close object output stream
            outputObject.close();
            
            world.getOutputStream().println();
            world.getOutputStream().println("Your game has been saved!");
            world.getOutputStream().println();
        
        } catch (IOException ex) {
            
            world.getOutputStream().println();
            world.getOutputStream().println("Hmm, for some reason your game couldn't be saved...");
            world.getOutputStream().println();
            
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
            return ((World) inputObject.readObject()).setOutputStream(System.out, 64);
        
        } catch (IOException | ClassNotFoundException ex) {
            
            world.getOutputStream().println();
            world.getOutputStream().println("Could not load your game");
            world.getOutputStream().println("");
            
            return null;
        
        }
        
    }
    
}
