package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;

/**
 * The health command. This displays the amount of health that the user
 * currently has.
 *
 * @author the_DJDJ
 */
public class CommandHealth implements Command {

    @Override
    public void execute(String arguments) {
    
        commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getHealthMessage(commandParser.getWorld().getPlayer().getHealth()), WidthLimitedOutputStream.BOTH);
        
    }
    
}
