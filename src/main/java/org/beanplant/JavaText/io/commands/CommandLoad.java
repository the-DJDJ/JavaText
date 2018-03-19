package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.GameData;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;

/**
 * The load command. This restores the user's game state to what it was when
 * they saved the game.
 * 
 * @author the_DJDJ
 */
public class CommandLoad implements Command {

    @Override
    public void execute(String arguments) {
    
        commandParser.updateWorld(GameData.load(commandParser.getWorld()));
        
        if(commandParser.getWorld() != null){
            
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getGameLoadMessage(), WidthLimitedOutputStream.ABOVE);
            commandParser.getWorld().getOutputStream().printAcross("=");
            
            commandParser.getWorld().showLocation(false);
        
        }
        
    }
    
}
