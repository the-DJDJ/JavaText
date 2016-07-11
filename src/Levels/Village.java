package Levels;

import BeanPlant.the_DJDJ.JavaText.npc.Boss;
import BeanPlant.the_DJDJ.JavaText.npc.Entity;
import BeanPlant.the_DJDJ.JavaText.world.exit.Exit;
import BeanPlant.the_DJDJ.JavaText.world.Location;
import BeanPlant.the_DJDJ.JavaText.world.World;
import BeanPlant.the_DJDJ.JavaText.user.Item;
import BeanPlant.the_DJDJ.JavaText.world.exit.Type;

/**
 * The village level. This is a more advanced level, and the first official one
 * to be added to the game. Please don't judge me if it's bad!
 *
 * @author the_djdj
 */
public class Village extends World {
    
    // Create room objects
    private final Location l01 = new Location ("Derelict Trainstation", "You stand on the platform of an old, empty trainstation. The tracks are rusty and studded with weeds, and it is obvious that no trains have been through here in a while. Turning around, you glance at the clock which has now become a home for the mice. The absence of its ticking creates an eery, spooky silence, and a breeze draws through the abandonded corridors, pulling your attention east, onto a road leading to the nearby village.");
    private final Location l02 = new Location ("Village Road", "After walking for a few minutes, you come across a log blocking the road between the village and the train station. On closer inspection, you find that the log appears to have been placed across the road intentionally, acting as a block on the road and halting vehicle access to the village. Is it there to keep something in, or out? The log isn't particularly big and shouldn't be very hard to climb over, but there is also a path that goes around it that seems to lead up a rough dirt track through the trees.");
    private final Location l03 = new Location ("", "");
    private final Location l04 = new Location ("", "");

    // Create exit objects
    private final Exit e01 = new Exit (Exit.EAST, l02, Type.PATHWAY);
    private final Exit e02 = new Exit (Exit.NORTHEAST, l03, Type.PATHWAY);
    private final Exit e03 = new Exit (Exit.EAST, l04);
    private final Exit e04 = new Exit (Exit.WEST, l01);
    
    public Village(){
        
        // Set the title and description of the level
        setTitle("The Village");
        setDescription("You received a call a couple of weeks ago about something strange going on in a little village about fifty miles south of you. Something eerie. Newspaper reports haven't shown anything new, but even they cannot be depended on anymore. It's obvious that nobody has entered or left the village in a while, and the roads surrounding it are unnaturally quiet. Your taxi dropped you off here, on the outskirts, as you realise your mistake. Maybe you should've thought this one through a little more...");
        setPlayerHealth(100);
        
        // Add items
        l01.addItem(Item.newspaper, 2);
        
        // Attach exits to locations
        l01.addExit(e01);
        l02.addExit(e02);
        l02.addExit(e03);
        l02.addExit(e04);
        
        // Add bosses
        l02.setBoss(Boss.wombat);
        
        // Add an entity or two
        l02.addEntity(Entity.sheep, 2);
        
        // Add locations to our game lists
        addLocation (l01);
        addLocation (l02);
        addLocation (l03);
        addLocation (l04);

        // Add exits to our game lists
        addExit (e01);
        addExit (e02);
        addExit (e03);
        addExit (e04);
        
        // Set current location
        setCurrentLocation (l01);
        
    }
    
}
