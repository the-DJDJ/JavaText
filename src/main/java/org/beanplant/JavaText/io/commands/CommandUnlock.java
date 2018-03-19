package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;

/**
 * The unlock command. This locks an exit so that the user can no longer
 * move through it.
 *
 * @author the_DJDJ
 */
public class CommandUnlock implements Command {

    @Override
    public void execute(String arguments) {
    
        for (int i = 0; i < commandParser.getWorld().getPlayer().getLocation().getExits().size(); i++) {
            
            if(commandParser.getWorld().getPlayer().getLocation().getExits().get(i).getType().getName().equalsIgnoreCase(arguments)){
                
                if(commandParser.getWorld().getPlayer().getLocation().getExits().get(i).isLocked()){
                    
                    if(commandParser.getWorld().getPlayer().getLocation().getExits().get(i).isLockable()){
                    
                        if(commandParser.getWorld().getPlayer().getInventory().contains(commandParser.getWorld().getPlayer().getLocation().getExits().get(i).getType().getKey())){

                            commandParser.getWorld().getPlayer().getLocation().getExits().get(i).setLocked(false);
                            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getPlayer().getLocation().getExits().get(i).getType().getUnlockMessage(), WidthLimitedOutputStream.BOTH);

                        } else {

                            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getUnlockKeyNotOwnedMessage(commandParser.getWorld().getPlayer().getLocation().getExits().get(i).getType().getKey().get(0).getSingleName()), WidthLimitedOutputStream.BOTH);

                        }
                    
                    } else {
                        
                        commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getPlayer().getLocation().getExits().get(i).getType().getLockedMessage(), WidthLimitedOutputStream.BOTH);
                        
                    }
                    
                } else {
                    
                    commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getPlayer().getLocation().getExits().get(i).getType().getUnlockedMessage(), WidthLimitedOutputStream.BOTH);
                    
                }
                
                break;
                
            }
            
        }
        
    }
    
}
