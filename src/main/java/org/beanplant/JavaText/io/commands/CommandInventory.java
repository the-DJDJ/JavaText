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
    
        if(world.getPlayer().getInventory().isEmpty()) {
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getEmptyInventoryMessage(), WidthLimitedOutputStream.BOTH);
            
        } else {
        
            world.getOutputStream().printSpaced(world.getPlayer().getInventory().toString(), WidthLimitedOutputStream.BOTH);
        
        }
        
    }
    
}
