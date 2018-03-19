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
        
        // Check for possible random exits
        if(arguments.equals("RANDOM") || arguments.equals("R")) {
            
            this.execute(commandParser.getWorld().getPlayer().getLocation().getExits().get((int) (Math.random() * commandParser.getWorld().getPlayer().getLocation().getExits().size())).getDirectionName());
            
        } else {

            // Check if the direction is valid
            for (Exit exit : commandParser.getWorld().getPlayer().getLocation().getExits()) {

                if(arguments.equals(exit.getDirectionName()) || arguments.equals(exit.getShortDirectionName())){

                    if((!commandParser.getWorld().getPlayer().getLocation().hasBoss()) || (commandParser.getWorld().getPlayer().getLocation().hasBoss() && commandParser.getWorld().getPlayer().getLocation().getBoss().isAvoidable())){

                        if(!exit.isLocked()){

                            // Set location to the location pointed to by exit
                            commandParser.getWorld().getPlayer().setLocation(exit.getLeadsTo());

                            // Show new location
                            commandParser.getWorld().showLocation(false);
                            return;

                        } else {

                            commandParser.getWorld().getOutputStream().printSpaced(exit.getType().getLockedMessage(), WidthLimitedOutputStream.BOTH);

                        }

                    } else {

                        if(commandParser.getWorld().getPlayer().getLocation().getBoss().getName().contains(" ")){

                            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getMovePlayerAttackedMessage(commandParser.getWorld().getPlayer().getLocation().getBoss().getName().toLowerCase().substring(commandParser.getWorld().getPlayer().getLocation().getBoss().getName().lastIndexOf(" ")), commandParser.getWorld().getPlayer().getLocation().getBoss().getDamage()), WidthLimitedOutputStream.BOTH);


                        } else {

                            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getMovePlayerAttackedMessage(commandParser.getWorld().getPlayer().getLocation().getBoss().getName().toLowerCase(), commandParser.getWorld().getPlayer().getLocation().getBoss().getDamage()), WidthLimitedOutputStream.BOTH);

                        }

                        commandParser.getWorld().getPlayer().setHealth(commandParser.getWorld().getPlayer().getHealth() - commandParser.getWorld().getPlayer().getLocation().getBoss().getDamage());

                    }

                    valid = true;

                }

            }

            if(!valid){

                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getMoveInvalidMessage(), WidthLimitedOutputStream.BOTH);

            }
        
        }
        
    }
    
}
