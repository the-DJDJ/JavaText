package org.beanplant.JavaText.io.commands;

import java.io.IOException;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;
import org.beanplant.JavaText.net.Message;

/**
 * The disconnect command. This disconnects from a save game and goes back
 * to normal singleplayer
 *
 * @author the_DJDJ
 */
public class CommandDisconnect implements Command {

    @Override
    public void execute(String arguments) {
    
        try {
        
            if(commandParser.getWorld().getNetworkController().isActive()) {
                
                commandParser.getWorld().getNetworkController().getMessageSender().sendMessage(new Message(Message.LOGOFF, null));

                commandParser.getWorld().getNetworkController().close();
                commandParser.getWorld().getNetworkController().setActive(false);
                
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getMultiplayerDisconnectSuccessMessage(), WidthLimitedOutputStream.BOTH);

            } else {
                
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getMultiplayerNotInGameErrorMessage(), WidthLimitedOutputStream.BOTH);

            }       
            
        } catch (NullPointerException ex) {
            
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getMultiplayerNotInGameErrorMessage(), WidthLimitedOutputStream.BOTH);
            
        } catch (IOException ex) {
            
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getMultiplayerDisconnectErrorMessage(), WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
}
