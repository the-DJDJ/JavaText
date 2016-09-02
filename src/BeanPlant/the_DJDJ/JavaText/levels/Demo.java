package BeanPlant.the_DJDJ.JavaText.levels;

import BeanPlant.the_DJDJ.JavaText.world.exit.Exit;
import BeanPlant.the_DJDJ.JavaText.world.Location;
import BeanPlant.the_DJDJ.JavaText.world.World;
import BeanPlant.the_DJDJ.JavaText.user.Item;

/**
 * This class is the demo world that can be loaded within the game. I would
 * recommend that you look at this class and see how things are done before you
 * create your own class (:
 * 
 * Remember, the class must extend World, otherwise it won't actually be a
 * world that players can play in! If you have any problems, feel free to
 * contact me for help!
 *
 * @author the_DJDJ
 */
public class Demo extends World {
    
    // Create room objects
    private final Location l1 = new Location ("Bottom of the well", "You have reached the bottom of a deep and rather smelly well. Less than a foot of water remains, and it looks undrinkable.");
    private final Location l2 = new Location ("Courtyard", "At the centre of the courtyard is an old stone well. A strong and sturdy rope is attached to the well, and descends into the darkness. The only other items of interest is the farmhouse to the north, and a path to the east.");
    private final Location l3 = new Location ("Farmhouse entrance", "The door to the farmhouse hangs crooked, and is slightly ajar. Obviously no one has lived here for some time, and you can only guess at what lies within.");
    private final Location l4 = new Location ("Blood-stained room", "Dried blood stains can be seen on the walls and stone floor of the farmhouse. Whatever massacre occured here long ago, you can only guess. With the abscence of bodies, however, you may never know.");
    private final Location l5 = new Location ("Long windy path", "You are standing on a long, windy path, leading from the mountains in the far east, to a small farm that lies to the west.");
    private final Location l6 = new Location ("Base of the mountain", "At the base of the mountain is a path that leads westward beyond a large boulder. Climbing such a mountain would be difficult - if not impossible.");
    private final Location l7 = new Location ("Top of the mountain", "From this vantage point, you can see all that lies on the plains below. Large boulders dot the landscape, and just within view to the west you make out some sort of a building - though its details are too hard to make out from this distance.");

    // Create exit objects
    private final Exit e1 = new Exit ( Exit.UP, l2 );
    private final Exit e2 = new Exit ( Exit.DOWN, l1 );
    private final Exit e3 = new Exit ( Exit.NORTH, l3 );
    private final Exit e4 = new Exit ( Exit.SOUTH, l2 );
    private final Exit e5 = new Exit ( Exit.NORTH, l4 );
    private final Exit e6 = new Exit ( Exit.SOUTH, l3 );
    private final Exit e7 = new Exit ( Exit.EAST, l5 );
    private final Exit e8 = new Exit ( Exit.WEST, l2 );
    private final Exit e9 = new Exit ( Exit.EAST, l6 );
    private final Exit e10 = new Exit ( Exit.WEST, l5 );
    private final Exit e11 = new Exit ( Exit.UP, l7 );
    private final Exit e12 = new Exit ( Exit.DOWN, l6 );
    
    /**
     * The main constructor. This creates the new World object to be used in the
     * game, and adds all the little stuffies to it.
     */
    public Demo(){
        
        // Add items
        l2.addItem(Item.stick);
        l3.addItem(Item.stick);
        l3.addItem(Item.rock);

        // Attach exits to locations
        l1.addExit ( e1 );
        l2.addExit ( e2 );
        l2.addExit ( e3 );
        l2.addExit ( e7 );
        l3.addExit ( e4 );
        l3.addExit ( e5 );
        l4.addExit ( e6 );
        l5.addExit ( e8 );
        l5.addExit ( e9 );
        l6.addExit ( e10 );
        l6.addExit ( e11 );
        l7.addExit ( e12 );

        // Add locations to our game lists
        addLocation (l1);
        addLocation (l2);
        addLocation (l3);
        addLocation (l4);

        // Set current location
        setCurrentLocation ( l2 );
        
    }
    
}
