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

                if(world.getPlayer().getInventory().contains(item) || world.getPlayer().getLocation().getItems().contains(item)) {

                    world.getOutputStream().printSpaced(item.getDescription(), WidthLimitedOutputStream.BOTH);

                } else {
                    
                    boolean present = false;
                    
                    for (int i = 0; i < world.getPlayer().getLocation().getItems().size(); i++) {
                        
                        if(world.getPlayer().getLocation().getItems().get(i).getName().equals(item.getName())){
                            
                            world.getOutputStream().printSpaced(item.getDescription(), WidthLimitedOutputStream.BOTH);
                            
                            present = true;
                            
                        }
                        
                    }
                    
                    if(!present){

                        world.getOutputStream().printSpaced(world.getMessageBuilder().getInspectItemNotPresentMessage(item.getSingleName()), WidthLimitedOutputStream.BOTH);
                    
                    }

                }
            
            } else {
                
                world.getOutputStream().printSpaced(world.getMessageBuilder().getInspectUnknownMessage(arguments), WidthLimitedOutputStream.BOTH);
                
            }
            
        } else {
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getInspectNullMessage(), WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
}
