package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;

/**
 * The unknown command. This is called if the user enters an unrecognised
 * command.
 *
 * @author the_DJDJ
 */
public class CommandUnknown implements Command {

    @Override
    public void execute(String arguments) {
    
        world.getOutputStream().printSpaced(world.getMessageBuilder().getUnknownCommandMessage(), WidthLimitedOutputStream.BOTH); 
        
    }
    
}
