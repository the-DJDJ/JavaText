package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;

/**
 * The look command. This shows the user the current location that they are
 * in in case they forget for some reason.
 *
 * @author the_DJDJ
 */
public class CommandLook implements Command {

    @Override
    public void execute(String arguments) {
    
        commandParser.getWorld().showLocation(true);
        
    }
    
}
