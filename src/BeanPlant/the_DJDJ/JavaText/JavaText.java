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

        world = new LevelSelector().getNewWorld();
        
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
