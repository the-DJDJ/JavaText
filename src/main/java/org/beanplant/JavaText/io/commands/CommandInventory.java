package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;

/**
 * The inventory command. This displays the contents of the user's inventory
 * to them.
 *
 * @author the_DJDJ
 */
public class CommandInventory implements Command {

    @Override
    public void execute(String arguments) {
    
        if(commandParser.getWorld().getPlayer().getInventory().isEmpty()) {
            
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getEmptyInventoryMessage(), WidthLimitedOutputStream.BOTH);
            
        } else {
        
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getPlayer().getInventory().toString(), WidthLimitedOutputStream.BOTH);
        
        }
        
    }
    
}
