package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;
import org.beanplant.JavaText.world.exit.Exit;

/**
 * The command that allows a user to move from one location to another. This
 * takes the direction as a parameter, and sets the world's current location
 * to whatever is in that direction.
 *
 * @author the_DJDJ
 */
public class CommandGo implements Command {

    @Override
    public void execute(String arguments) {
    
        boolean valid = false;

        // Check if the direction is valid
        for (Exit exit : world.getPlayer().getLocation().getExits()) {
            
            if(arguments.equals(exit.getDirectionName()) || arguments.equals(exit.getShortDirectionName())){
                
                if((!world.getPlayer().getLocation().hasBoss()) || (world.getPlayer().getLocation().hasBoss() && world.getPlayer().getLocation().getBoss().isAvoidable())){
                
                    if(!exit.isLocked()){

                        // Set location to the location pointed to by exit
                        world.getPlayer().setLocation(exit.getLeadsTo());

                        // Show new location
                        world.showLocation(false);

                    } else {

                        world.getOutputStream().printSpaced(exit.getType().getLockedMessage(), WidthLimitedOutputStream.BOTH);

                    }
                
                } else {
                    
                    if(world.getPlayer().getLocation().getBoss().getName().contains(" ")){
                        
                        world.getOutputStream().printSpaced(world.getMessageBuilder().getMovePlayerAttackedMessage(world.getPlayer().getLocation().getBoss().getName().toLowerCase().substring(world.getPlayer().getLocation().getBoss().getName().lastIndexOf(" ")), world.getPlayer().getLocation().getBoss().getDamage()), WidthLimitedOutputStream.BOTH);
                    
                        
                    } else {
                        
                        world.getOutputStream().printSpaced(world.getMessageBuilder().getMovePlayerAttackedMessage(world.getPlayer().getLocation().getBoss().getName().toLowerCase(), world.getPlayer().getLocation().getBoss().getDamage()), WidthLimitedOutputStream.BOTH);
                        
                    }
                    
                    world.getPlayer().setHealth(world.getPlayer().getHealth() - world.getPlayer().getLocation().getBoss().getDamage());
                    
                }
                
                valid = true;

            }
        
        }
        
        if(!valid){
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getMoveInvalidMessage(), WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
}
