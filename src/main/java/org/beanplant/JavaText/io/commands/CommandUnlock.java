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
    
        for (int i = 0; i < world.getPlayer().getLocation().getExits().size(); i++) {
            
            if(world.getPlayer().getLocation().getExits().get(i).getType().getName().equalsIgnoreCase(arguments)){
                
                if(world.getPlayer().getLocation().getExits().get(i).isLocked()){
                    
                    if(world.getPlayer().getLocation().getExits().get(i).isLockable()){
                    
                        if(world.getPlayer().getInventory().contains(world.getPlayer().getLocation().getExits().get(i).getType().getKey())){

                            world.getPlayer().getLocation().getExits().get(i).setLocked(false);
                            world.getOutputStream().printSpaced(world.getPlayer().getLocation().getExits().get(i).getType().getUnlockMessage(), WidthLimitedOutputStream.BOTH);

                        } else {

                            world.getOutputStream().printSpaced(world.getMessageBuilder().getUnlockKeyNotOwnedMessage(world.getPlayer().getLocation().getExits().get(i).getType().getKey().get(0).getSingleName()), WidthLimitedOutputStream.BOTH);

                        }
                    
                    } else {
                        
                        world.getOutputStream().printSpaced(world.getPlayer().getLocation().getExits().get(i).getType().getLockedMessage(), WidthLimitedOutputStream.BOTH);
                        
                    }
                    
                } else {
                    
                    world.getOutputStream().printSpaced(world.getPlayer().getLocation().getExits().get(i).getType().getUnlockedMessage(), WidthLimitedOutputStream.BOTH);
                    
                }
                
                break;
                
            }
            
        }
        
    }
    
}
