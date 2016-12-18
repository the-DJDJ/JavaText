# JavaText
A text based Java games engine!

JavaText is a simple, Java-based game engine designed to make creating your 2D game easily! Download the engine, build your game around it, compile it, and you're good to go!

Oh, and of course, don't forget to send us a copy of your awesome game!

## Getting started

First of all, you're gonna need a copy of our engine:
```shell
git clone https://github.com/the-DJDJ/JavaText.git
```

Now comes the tricky bit; you're gonna need to extend our level class to create your level. Create your new class in 
```
<root>/src/main/levels/
```
and replicate the following template:
```java
package levels;

import org.beanplant.JavaText.world.World;

/**
 * Your demo level.
 *
 * @author the_DJDJ
 */
public class YourLevel extends World {

    public YourLevel () {
    
    }
    
}
```
And you've got your basic game!

## Sprucing it up a little
Next of all, lets add a little bit of information about your level. You can do this using some of the methods already build in to the World class. We'll be using twp of them in your level constructor to make your basic game:
```java
public YourLevel () {
    
    // Create the title and description of the level
    setTitle("Your game's title");
    setDescription("Put a little bit of info, like a background story, here for your players.");
    
}
```
Now obviously a game consists of more than just a title and a description, so it's time to create your first location! A location is, well, a location, in the game. It is a place that a player can visit, and can either lead to someplace new, or be home to some animals or even a boss or two.
To create your first location, you need to create a new instance of the `Location` object, like so:
```java
Location place = new Location("Forest pathway", "A winding pathway that leads you through the woods");
```
For a location to lead somewhere, it needs an `Exit`. An Exit is a sort of tunnel linking two locations. How it works is you add an exit to a location, and then give the exit someplace to go. One location can have many exits, so it's important that you specify which way the exit goes. Sound confusing? Here's how its done:
```java
// Create your exit
Exit exit = new Exit(Exit.SOUTHEAST, secondLocation);

// Assign the exit to a location
firstLocation.addExit(exit);
```
In this little snippet, the `firstLocation` has an exit that leads South West to the `secondLocation`. It's important to note that exits don't work both ways, which is very useful for creating traps! You'll need to create a second exit that leads North East to act as a way back.

And just to recap, this is what we have so far:
```java
package levels;

import org.beanplant.JavaText.world.World;

/**
 * Your demo level.
 *
 * @author the_DJDJ
 */
public class YourLevel extends World {

    // Create our locations
    Location firstPlace = new Location("Forest pathway", "A winding pathway that leads you through the woods");
    Location secondPlace = new Location("Dusty field", "A dusty field of flowers, rustling in the breeze.");
    
    // Make a little exit
    Exit exit = new Exit(Exit.SOUTHEAST, secondPlace);

    public YourLevel () {
    
        // Make the level interesting
        setTitle("The Scenic Walk");
        setDescription("Strap on your boots and join us for a scenic walk through the woods.");
    
        // Attach the exits
        firstPlace.addExit(exit);
    
    }
    
}
```
## Messing with the player
Great! So now that we have a simple little level, we need to do something with it, right? For instance, where will the player spawn? How much life should he have? Is he good at math, etc.?
First of all, you'll need to give the player somewhere to start:
```java
getPlayer().setLocation(startingPlace);
```
The `Player` object returned by `getPlayer()` has lost of interesting little tidbits you can play with, but for now we'll only be changing the spawnpoint and the health.
Maybe your player needs to be super-strong, or sometimes just the average Joe. Either way, you'll need to give them a certain amount of health to start with. A player will by default have 100 health points, but you can easily change this like so:
```java
getPlayer().setHealth(101);
```
## Things the player can't control
NPCs! No game would be complete without them! As you've probably realised, the `Location` object is pretty simple to the mechanics of the game engine, so it only makes sense that we will place our NPCs in a specific `Location`. Now, there are many different types of NPC, so we'll lay them out to you in this neat little tree:
* Entity
  * Boss
  * EntityCollection
  * MobileEntity
    * EntityShadow
    
Just to make things a little more complicated though, as all of the items extend `Entity`, they can all act as each other! I know, it's confusing.
For every kind of entity there is a unique way to add it, to add to the mix:
#### Entities and MobileEntities
```java
location.addEntity(entity);
```
A MobileEntity is the same as an Entity, except for the fact that it has a slight chance of going down an exit after every player turn.
#### Bosses
```java
location.setBoss(boss);
```
A boss is a special type of entity that the player can interact with, usually kill. They have their own properties that you can explore, but there can only be one boss per location
#### Entity Collections
```java
location.addEntity(entity, 4);
```
These are pretty self-explanatory. An entity collection is literally a collection of entities, that is, an easy way to denote that there is more than one, like a flock of sheep. An entity collection can also store a collection of `MobileEntities`, or anything else that extends `Entity`.
#### EntityShadows
```java
location.addEntityShadow(entityShadow);
```
An EntityShadow is a 'shadow' of a MobileEntity, that is, they are left behind when MobileEntities move and only persist for one turn. They are useful for tracking things.
