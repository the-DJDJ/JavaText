package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.handlers.CommandLockHandler;
import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;

/**
 * The no command. This is usually used when a command has been locked, for
 * instance if the player needs to deny something.
 *
 * @author the_DJDJ
 */
public class CommandDeny implements Command {

    @Override
    public void execute(String arguments) {
    
        if(commandParser.getAllLockHandlers().isEmpty()) {
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getYesMessage(), WidthLimitedOutputStream.BOTH);
            
        } else {
            
            for (CommandLockHandler commandHandler : commandParser.getAllLockHandlers()) {
                
                commandHandler.handleCommand("NO");
            
            }
            
        }
        
        commandParser.removeAllLockHandlers();
        
    }
    
}
