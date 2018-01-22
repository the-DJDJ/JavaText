package org.beanplant.JavaText.io;

import org.beanplant.JavaText.JavaText;
import org.beanplant.JavaText.world.World;

/**
 * The interface extended when a user creates a custom command. This is also
 * used by JavaText itself, and includes the two most commonly used variables in
 * commands, as well as provides a method that must be overridden in order for
 * the command to work.
 *
 * @author the_DJDJ
 */
public interface Command {
    
    public World world                 = JavaText.getWorld();
    public CommandParser commandParser = JavaText.getCommandParser();
    
    public void execute(String arguments);
    
}
