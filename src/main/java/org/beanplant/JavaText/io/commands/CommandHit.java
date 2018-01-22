package org.beanplant.JavaText.io.commands;

import org.beanplant.JavaText.io.Command;
import org.beanplant.JavaText.io.WidthLimitedOutputStream;
import org.beanplant.JavaText.npc.Entity;
import org.beanplant.JavaText.user.Item;

/**
 * The hit command. This deals damage to a specific entity, either by
 * hitting it with the player's fist, or a weapon
 *
 * @author the_DJDJ
 */
public class CommandHit implements Command {

    @Override
    public void execute(String arguments) {
    
        if(!arguments.trim().isEmpty()){
            
            // The entity to hit
            Entity entity = null;
            
            // The name of the entity to search for
            String name = ((arguments.contains(" WITH ")) ? arguments.substring(0, arguments.indexOf(" WITH ")) : arguments).trim();
            
            // First find which entity to hit
            if(world.getPlayer().getLocation().hasBoss() && world.getPlayer().getLocation().getBoss().getName().equalsIgnoreCase(name)){
                
                entity = world.getPlayer().getLocation().getBoss();
                
            } else {
            
                for (int i = 0; i < world.getPlayer().getLocation().getEntities().size(); i++) {

                    if(world.getPlayer().getLocation().getEntities().get(i).getName()
                            .equalsIgnoreCase(name)){

                        entity = world.getPlayer().getLocation().getEntities().get(i);

                    }

                }
                            
            }
            
            // Check that it has been found
            if(entity == null){
                    
                world.getOutputStream().printSpaced(world.getMessageBuilder().getEntityNotPresentMessage(name), WidthLimitedOutputStream.BOTH);
                    
            } else {
                
                // Set the default damage that your fist deals
                int damage = 1;
                
                // Check if the user can hit with something more powerful
                if(arguments.contains(" WITH ")){
                    
                    String itemName = arguments.substring(arguments.indexOf(" WITH ") + 6).toLowerCase().trim();
                    
                    if(new Item().isValidItem(itemName)){
                    
                        if(world.getPlayer().getInventory().contains(new Item().getItem(itemName))){

                            // Work out the damage
                            damage = new Item().getItem(itemName).getDamage();
                            
                            // Hurt the entity
                            entity.setHealth(entity.getHealth() - damage);

                            // Check if the entity is still alive
                            if(entity.getHealth() > 0){

                                world.getOutputStream().printSpaced(world.getMessageBuilder().getHitHurtMessage(name, damage), WidthLimitedOutputStream.BOTH);

                            } else {
                                
                                world.getOutputStream().printSpaced(world.getMessageBuilder().getHitKillMessage(name), WidthLimitedOutputStream.BOTH);
                                world.getPlayer().getLocation().getEntities().remove(entity);

                            }

                        } else {
                            
                            world.getOutputStream().printSpaced(world.getMessageBuilder().getWeaponUnownedMessage(itemName), WidthLimitedOutputStream.BOTH);
                            
                        }
                    
                    } else {
                        
                        world.getOutputStream().printSpaced(world.getMessageBuilder().getWeaponUnknownMessage(itemName), WidthLimitedOutputStream.BOTH);
                        
                    }
                    
                } else {
                    
                    // Hurt the entity
                    entity.setHealth(entity.getHealth() - damage);

                    // Check if the entity is still alive
                    if(entity.getHealth() > 0){
                        
                        world.getOutputStream().printSpaced(world.getMessageBuilder().getHitHurtMessage(name, damage), WidthLimitedOutputStream.BOTH);

                    } else {
                        
                        world.getOutputStream().printSpaced(world.getMessageBuilder().getHitKillMessage(name.toLowerCase()), WidthLimitedOutputStream.BOTH);
                        world.getPlayer().getLocation().getEntities().remove(entity);

                    }
                    
                }
                                
            }
                    
            
        } else {
                        
            world.getOutputStream().printSpaced(world.getMessageBuilder().getHitNullMessage(), WidthLimitedOutputStream.BOTH);
            
        }
        
    }
    
}
