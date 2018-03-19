package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;

/**
 * The statistics command. This displays the user's statistics
 * to them.
 *
 * @author the_DJDJ
 */
public class CommandStatistics implements Command {

    @Override
    public void execute(String arguments) {
    
        if(commandParser.getWorld().getPlayer().getStatistics().isEmpty()) {
            
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getEmptyStatisticsMessage(), WidthLimitedOutputStream.BOTH);
            
        } else {
        
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getPlayer().getStatistics().toString(), WidthLimitedOutputStream.ABOVE);
        
        }
        
    }
    
}
