package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.GameData;

/**
 * The save command. This saves the user's game to a file so that it can be
 * loaded later.
 * 
 * @author the_DJDJ
 */
public class CommandSave implements Command {

    @Override
    public void execute(String arguments) {
    
        GameData.save(commandParser.getWorld());
        
    }
    
}
