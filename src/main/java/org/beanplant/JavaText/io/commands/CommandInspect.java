package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;
import org.beanplant.JavaText.user.Item;

/**
 * The inspect command. This displays information about the selected item.
 *
 * @author the_DJDJ
 */
public class CommandInspect implements Command {

    @Override
    public void execute(String arguments) {
    
        if(!arguments.isEmpty()){
            
            if(new Item().isValidItem(arguments)){
        
                Item item = new Item().getItem(arguments);

                if(commandParser.getWorld().getPlayer().getInventory().contains(item) || commandParser.getWorld().getPlayer().getLocation().getItems().contains(item)) {

                    commandParser.getWorld().getOutputStream().printSpaced(item.getDescription(), WidthLimitedOutputStream.BOTH);

                } else {
                    
                    boolean present = false;
                    
                    for (int i = 0; i < commandParser.getWorld().getPlayer().getLocation().getItems().size(); i++) {
                        
                        if(commandParser.getWorld().getPlayer().getLocation().getItems().get(i).getName().equals(item.getName())){
                            
                            commandParser.getWorld().getOutputStream().printSpaced(item.getDescription(), WidthLimitedOutputStream.BOTH);
                            
                            present = true;
                            
                        }
                        
                    }
                    
                    if(!present){

                        commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getInspectItemNotPresentMessage(item.getSingleName()), WidthLimitedOutputStream.BOTH);
                    
                    }

                }
            
            } else {
                
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getInspectUnknownMessage(arguments), WidthLimitedOutputStream.BOTH);
                
            }
            
        } else {
            
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getInspectNullMessage(), WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
}
