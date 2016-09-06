package BeanPlant.the_DJDJ.JavaText.handlers;

/**
 * A simple interface for calling a method when a locked command is entered.
 *
 * @author the_DJDJ
 */
public interface CommandLockHandler {
    
    /**
     * The method for dealing with a locked command. This is overridden by
     * whatever locked the CommandParser
     * 
     * @param command the command to handle
     */
    public void handleCommand(String command);
    
}
