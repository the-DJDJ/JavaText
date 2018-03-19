package org.beanplant.JavaText.io.commands;

import java.io.IOException;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;
import org.beanplant.JavaText.net.Message;

/**
 * The stop command. This stops a hosted savegame and restores it to a
 * standard singleplayer game
 *
 * @author the_DJDJ
 */
public class CommandStop implements Command {

    @Override
    public void execute(String arguments) {
        
        try {
        
            if(commandParser.getWorld().getNetworkController().isActive()) {
                
                commandParser.getWorld().getNetworkController().getMessageSender().sendMessage(new Message(Message.STOP, null));

                commandParser.getWorld().getNetworkController().close();
                commandParser.getWorld().getNetworkController().setActive(false);
                
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getHostDisconnectSuccessMessage(), WidthLimitedOutputStream.BOTH);

            } else {

                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getHostNotSharingErrorMessage(), WidthLimitedOutputStream.BOTH);

            }       
            
        } catch (NullPointerException ex){
            
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getHostNotSharingErrorMessage(), WidthLimitedOutputStream.BOTH);
            
        } catch (IOException ex) {
            
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getHostDisconnectErrorMessage(), WidthLimitedOutputStream.BOTH);
            
        }
    
    }
    
}
