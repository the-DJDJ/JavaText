package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;

/**
 * The continue command. This will be used when there are dialogue moments, or
 * when input is not required from the user at the given time.
 *
 * @author the_DJDJ
 */
public class CommandContinue implements Command {

    @Override
    public void execute(String arguments) {
        
        commandParser.getRegisteredCommand("GO").execute("RANDOM");
        
    }
    
}