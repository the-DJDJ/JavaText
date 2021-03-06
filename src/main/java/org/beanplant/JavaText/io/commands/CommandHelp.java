package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;

/**
 * The help command. Let's see what happens when users run me!
 *
 * @author the_DJDJ
 */
public class CommandHelp implements Command {

    @Override
    public void execute(String arguments) {
        
        commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getHelpMessage(), WidthLimitedOutputStream.BOTH);
    
    }
    
}
