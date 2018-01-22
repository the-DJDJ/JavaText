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
    
        world.getOutputStream().printSpaced(world.getMessageBuilder().getHealthMessage(world.getPlayer().getHealth()), WidthLimitedOutputStream.BOTH);
        
    }
    
}
