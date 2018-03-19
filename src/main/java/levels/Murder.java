package levels;

import org.beanplant.JavaText.world.Location;
import org.beanplant.JavaText.world.World;

/**
 *
 * @author the_DJDJ
 */
public class Murder extends World {
    
    // Create room objects
    private final Location l001 = new Location ("Prologue", "On a pleasant summer day in London, you decide to visit your mentor, Sherlock Holmes, and your cousin, Dr. John Watson. Perhaps today Mr. Holmes will hand you a case to solve yourself."
            + " <token:paragraph> Upon reaching 221B Baker Street, Mrs. Hudson admits you with a smile, although obviously in the midst of a busy cleaning session. \"How nice to see you again,\" says Holmes' landlady. \"Mr. Holmes and Doctor Watson are in their rooms. You know the way.\""
            + " <token:paragraph> \"Yes ma'am,\" you reply, turning toward the stair."
            + " <token:paragraph> \"You might do me a favor,\" she continues. You pause at the words. \"Dr. Watson's newspaper just came; it would save a trip if you'd take it with you. I just came down from carrying the doctor a telegram, and I've a thousand things to do today.\""
            + " <token:paragraph> Naturally, you agree. At your knock, Mr. Holmes invites you in, and both men greet you brusquely. Doctor Watson sits at his desk, writing, a telegram sticking out of his vest pocket. His pipe drawing well, Holmes relaxes by the window. After returning their greetings, you hand your cousin his newspaper and sit to talk with Holmes. Although rarely given easily, the detective's thoughts are always worth hearing. As you talk, you notice Watson turn to the back page of his paper, look at it intently for a moment, and then toss it aside with a grunt of disgust."
            + " <token:paragraph> Holmes chuckles. \"You're quite right, Watson. You will make more money telling the story of Silver Blaze than betting any of tomorrow's races.\""
            + " <token:paragraph> \"That's what I've decided, Holmes,\" Watson replies. \"Those horses would have trouble - how did you know what I was thinking?\""
            + " <token:paragraph> \"I knew the same way I always know,\" Holmes replies. \"By observation and reasoning. Besides, Watson, you make so much from my analysis of the trivial that you hardly have the right to complain.\""
            + " <token:paragraph> \"All right, Holmes, the point is well taken,\" Watson agrees, blushing as he smiles. \"But for the life of me, I don't see how you did it this time. I hadn't even told you that I was writing one of your adventures, much less which case.\""
            + " <token:paragraph> Suddenly nervous, you marshall your thoughts, trying to remember every significant detail of the morning."
            + " <token:paragraph> \"Where shall I start?\" you ask, stalling for time."
            + " <token:paragraph> \"Why don't you try the telegram first?\" Holmes suggests. \"Just before you arrived, Watson received a telegram which he stuck in his pocket without reading. What does that suggest to you?\""
            + " <token:paragraph> \"That he was in a hurry,\" you reply, covering your mouth in dismay at speaking without thinking. \"No, how could he be in a hurry when he's sitting at his desk? It must be something else.\"", true);
    private final Location l323 = new Location ("", "");
    private final Location l638 = new Location ("", "");
    
    // Create exit objects

    public Murder(){
        
        // Set the title and description of the level
        setTitle("Murder at the Diogenes Club");
        setDescription("by Gerald Lientz");
        getPlayer().setHealth(100);
        
        // Add items
        
        // Attach exits to locations
        
        // Add bosses
        
        // Place MobileEntities
        
        // Add an entity or two
        
        // Add locations to our game lists
        addLocation (l001);
        addLocation (l323);
        addLocation (l638);
        
        // Set current location
        getPlayer().setLocation(l001);
        
    }
    
}
