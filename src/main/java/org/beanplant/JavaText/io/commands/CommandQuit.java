package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;

/**
 * The quit command. This is called if the user wishes to close the
 * application.
 *
 * @author the_DJDJ
 */
public class CommandQuit implements Command {

    @Override
    public void execute(String arguments) {
    
        world.getOutputStream().printSpaced(world.getMessageBuilder().getQuitMessage(), WidthLimitedOutputStream.BOTH);
        
        System.exit(0);
        
    }
    
}
