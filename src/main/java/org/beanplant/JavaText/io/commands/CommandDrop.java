package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;
import org.beanplant.JavaText.user.Item;
import org.beanplant.JavaText.user.ItemStack;

/**
 * The command to drop an item from the user's inventory. This checks if the
 * user has the item in their inventory, and then removes the item from the
 * inventory whilst adding it from the current location in the world.
 *
 * @author the_DJDJ
 */
public class CommandDrop implements Command {

    @Override
    public void execute(String arguments) {
    
        Item item = new Item().getItem(arguments);
        
        if(commandParser.getWorld().getPlayer().getInventory().contains(item)){
            
            commandParser.getWorld().getPlayer().getInventory().removeItem(item);
            
            boolean present = false;
            int index = 0;
            
            for (int i = 0; i < commandParser.getWorld().getPlayer().getLocation().getItems().size(); i++) {
                
                if(commandParser.getWorld().getPlayer().getLocation().getItems().get(i).getName().equals(item.getName())){
                    
                    present = true;
                    index = i;
                    
                }
                
            }
            
            if(present){
                
                if(commandParser.getWorld().getPlayer().getLocation().getItems().get(index).isStack()){
                    
                    commandParser.getWorld().getPlayer().getLocation().getItems().set(index, ((ItemStack) commandParser.getWorld().getPlayer().getLocation().getItems().get(index)).add(1));
                    
                } else {
                    
                    commandParser.getWorld().getPlayer().getLocation().getItems().set(index, new ItemStack(item, 2));
                    
                }
                
            } else {
            
                commandParser.getWorld().getPlayer().getLocation().addItem(item);
                
            }
            
            commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getDropMessage(item.getName()), WidthLimitedOutputStream.BOTH);
            
        } else {  
            
            if(arguments.isEmpty()){
                
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getDropNullMessage(), WidthLimitedOutputStream.BOTH);
                
            } else if (item != null) {
            
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getDropUnownedMessage(item.getSingleName()), WidthLimitedOutputStream.BOTH);
            
            } else {
            
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getDropUnknownMessage(item.getSingleName()), WidthLimitedOutputStream.BOTH);
            
            }   
        
        }
        
    }
    
}
