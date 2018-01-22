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
        
        if(world.getPlayer().getInventory().contains(item)){
            
            world.getPlayer().getInventory().removeItem(item);
            
            boolean present = false;
            int index = 0;
            
            for (int i = 0; i < world.getPlayer().getLocation().getItems().size(); i++) {
                
                if(world.getPlayer().getLocation().getItems().get(i).getName().equals(item.getName())){
                    
                    present = true;
                    index = i;
                    
                }
                
            }
            
            if(present){
                
                if(world.getPlayer().getLocation().getItems().get(index).isStack()){
                    
                    world.getPlayer().getLocation().getItems().set(index, ((ItemStack) world.getPlayer().getLocation().getItems().get(index)).add(1));
                    
                } else {
                    
                    world.getPlayer().getLocation().getItems().set(index, new ItemStack(item, 2));
                    
                }
                
            } else {
            
                world.getPlayer().getLocation().addItem(item);
                
            }
            
            world.getOutputStream().printSpaced(world.getMessageBuilder().getDropMessage(item.getName()), WidthLimitedOutputStream.BOTH);
            
        } else {  
            
            if(arguments.isEmpty()){
                
                world.getOutputStream().printSpaced(world.getMessageBuilder().getDropNullMessage(), WidthLimitedOutputStream.BOTH);
                
            } else if (item != null) {
            
                world.getOutputStream().printSpaced(world.getMessageBuilder().getDropUnownedMessage(item.getSingleName()), WidthLimitedOutputStream.BOTH);
            
            } else {
            
                world.getOutputStream().printSpaced(world.getMessageBuilder().getDropUnknownMessage(item.getSingleName()), WidthLimitedOutputStream.BOTH);
            
            }   
        
        }
        
    }
    
}
