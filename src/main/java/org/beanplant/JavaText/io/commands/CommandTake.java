package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;
import org.beanplant.JavaText.user.Item;
import org.beanplant.JavaText.user.ItemStack;

/**
 * The command to pick an item up from the world. This checks if the user
 * has enough space in their inventory, and then adds the item to the
 * inventory whilst removing it from the current location in the world.
 *
 * @author the_DJDJ
 */
public class CommandTake implements Command {

    @Override
    public void execute(String arguments) {
    
        Item item = new Item().getItem(arguments);
        
        boolean valid = false;
        int index = 0;
        
        for (int i = 0; i < commandParser.getWorld().getPlayer().getLocation().getItems().size(); i++) {
            
            try{
            
                if(commandParser.getWorld().getPlayer().getLocation().getItems().get(i).getName().equals(item.getName())){

                    valid = true;
                    index = i;

                }
            
            } catch (NullPointerException ex){}
            
        }
        
        if(valid){
            
            if(commandParser.getWorld().getPlayer().getInventory().addItem(item)){
                
                if(commandParser.getWorld().getPlayer().getLocation().getItems().get(index).isStack()){
                    
                    commandParser.getWorld().getPlayer().getLocation().getItems().set(index, ((ItemStack) commandParser.getWorld().getPlayer().getLocation().getItems().get(index)).remove(1));
                            
                } else {
            
                    commandParser.getWorld().getPlayer().getLocation().getItems().remove(item);
                    
                }
            
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getTakeMessage(item.getName()), WidthLimitedOutputStream.BOTH);
                
            } else {
                
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getInventoryFullMessage(item.getName()), WidthLimitedOutputStream.BOTH);
                
            }
            
        } else {
            
            if(arguments.isEmpty()){
                
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getTakeNullMessage(), WidthLimitedOutputStream.BOTH);
                
            } else if (item == null) {
                
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getTakeUnknownMessage(arguments), WidthLimitedOutputStream.BOTH);
                
            } else {
            
                commandParser.getWorld().getOutputStream().printSpaced(commandParser.getWorld().getMessageBuilder().getTakeNotPresentMessage(item.getSingleName()), WidthLimitedOutputStream.BOTH);
            
            }
            
        }
        
    }
    
}
