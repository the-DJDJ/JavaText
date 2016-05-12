package BeanPlant.the_DJDJ.JavaText;

import BeanPlant.the_DJDJ.JavaText.world.World;
import BeanPlant.the_DJDJ.JavaText.io.CommandParser;
import BeanPlant.the_DJDJ.JavaText.levels.LevelSelector;
import java.util.Scanner;

/**
 *
 * @author the_DJDJ
 */
public class JavaText {
    
    private static String command;
    private static final Scanner input = new Scanner(System.in);
    private static World world;
    private static CommandParser commandParser;

    /**
     * @param args the command line arguments
     * 
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        
        switch (args.length) {
            
            case 0:
                // No arguments given. Load normally
                world = new LevelSelector().getNewWorld();
                break;
            
            case 1:
                // One argument. It can either be a String or an int
                try {
                    
                    // Assume that the argument is an int
                    world = new LevelSelector(Integer.parseInt(args[0])).getNewWorld();
                    
                } catch (NumberFormatException ex){
                    
                    // Obviously not
                    world = new LevelSelector().getSpecificWorld(args[0]);
                    
                }
                break;
            
            default:
                // Any other argument combination
                try {
                    
                    // Assume that it is correct
                    world = new LevelSelector(Integer.parseInt(args[1])).getSpecificWorld(args[0]);
                    
                } catch (NumberFormatException ex) {
                    
                    // They made a mistake
                    System.out.println("Usage: java JavaText <level> <output width>");
                    System.exit(0);
                }
                break;
        
        }
        
        world.showInformation();
	world.showLocation(false);
        
        commandParser = new CommandParser(world);
        
        getInput();
                
    }
    
    private static void getInput(){
        
        while(true){
            
            world.getOutputStream().print("> ");
        
            // Obtain string, as well as convert it to uppercase and remove
            // leading and trailing spaces
            command = input.nextLine().toUpperCase().trim();

            // Don't parse blank commands
            if (command.length() != 0){
                
                commandParser.parse(command);

            }

        }
        
    }
    
    /**
     * Returns the CommandParser used throughout the engine
     * 
     * @return the CommandParser
     */
    public static CommandParser getCommandParser(){
        
        return commandParser;
        
    }
    
}
