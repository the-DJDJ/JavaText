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
        
        for (int i = 0; i < world.getPlayer().getLocation().getItems().size(); i++) {
            
            try{
            
                if(world.getPlayer().getLocation().getItems().get(i).getName().equals(item.getName())){

                    valid = true;
                    index = i;

                }
            
            } catch (NullPointerException ex){}
            
        }
        
        if(valid){
            
            if(world.getPlayer().getInventory().addItem(item)){
                
                if(world.getPlayer().getLocation().getItems().get(index).isStack()){
                    
                    world.getPlayer().getLocation().getItems().set(index, ((ItemStack) world.getPlayer().getLocation().getItems().get(index)).remove(1));
                            
                } else {
            
                    world.getPlayer().getLocation().getItems().remove(item);
                    
                }
            
                world.getOutputStream().printSpaced(world.getMessageBuilder().getTakeMessage(item.getName()), WidthLimitedOutputStream.BOTH);
                
            } else {
                
                world.getOutputStream().printSpaced(world.getMessageBuilder().getInventoryFullMessage(item.getName()), WidthLimitedOutputStream.BOTH);
                
            }
            
        } else {
            
            if(arguments.isEmpty()){
                
                world.getOutputStream().printSpaced(world.getMessageBuilder().getTakeNullMessage(), WidthLimitedOutputStream.BOTH);
                
            } else if (item == null) {
                
                world.getOutputStream().printSpaced(world.getMessageBuilder().getTakeUnknownMessage(arguments), WidthLimitedOutputStream.BOTH);
                
            } else {
            
                world.getOutputStream().printSpaced(world.getMessageBuilder().getTakeNotPresentMessage(item.getSingleName()), WidthLimitedOutputStream.BOTH);
            
            }
            
        }
        
    }
    
}
