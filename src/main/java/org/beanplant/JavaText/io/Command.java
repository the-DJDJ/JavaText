package org.beanplant.JavaText.io;

import org.beanplant.JavaText.JavaText;
import org.beanplant.JavaText.world.World;

/**
 *
 * @author the-djdj
 */
public interface Command {
    
    public World world                 = JavaText.getWorld();
    public CommandParser commandParser = JavaText.getCommandParser();
    
    public void execute(String arguments);
    
}
