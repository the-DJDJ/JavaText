package org.beanplant.JavaText.util;

/**
 * The class that is responsible for building messages for users to read. This
 * class can very easily be overridden if the developer wishes to add messages
 * of their own, or if they want to add some translations.
 *
 * @author the_DJDJ
 */
public class MessageBuilder {
    
    /**
     * The message displayed for a 'Yes' interaction.
     * 
     * @return A feedback message.
     */
    public static String getYesMessage() {
        
        String[] messages = {"Yes."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed for a 'No' interaction.
     * 
     * @return A feedback message.
     */
    public static String getNoMessage() {
        
        String[] messages = {"No."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player moves but is attacked.
     * 
     * @param name The name parameter
     * @param damage The damage parameter
     * 
     * @return A feedback message.
     */
    public static String getMovePlayerAttackedMessage(String name, int damage) {
        
        String[] messages = {"The " + name + " attacks you! You lose " + damage + " HP!",
                             "You're attacked by the " + name +"! You lose " + damage + " HP."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player attempts an invalid move.
     * 
     * @return A feedback message.
     */
    public static String getMoveInvalidMessage() {
        
        String[] messages = {"You cannot go that way.",
                             "I wouldn't go that way if I were you."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player's inventory is full.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getInventoryFullMessage(String name) {
        
        String[] messages = {"You don't have enough space left in your inventory to pick up the " + name.toLowerCase(),
                             "Your inventory looks a little bit full for that."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player takes an item.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getTakeMessage(String name) {
        
        String[] messages = {"You picked up the " + name.toLowerCase(),
                             "You have taken the " + name.toLowerCase()};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player attempts to take an unknown item.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getTakeUnknownMessage(String name) {
        
        String[] messages = {"I don't know what a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + " is.",
                             "What is a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + "?"};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player attempts to take an item which is not
     * present.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getTakeNotPresentMessage(String name) {
        
        String[] messages = {"I don't see " + name.toLowerCase() + " here...",
                             "There isn't even " + name.toLowerCase() + " here."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player leaves the item parameter null.
     * 
     * @return A feedback message.
     */
    public static String getTakeNullMessage() {
        
        String[] messages = {"Take what?",
                             "Um. What exactly are you taking?"};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player drops an item.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getDropMessage(String name) {
        
        String[] messages = {"You dropped the " + name.toLowerCase() + "."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player drops an item they do not have.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getDropUnownedMessage(String name) {
        
        String[] messages = {"You don't own " + name.toLowerCase(),
                             "Sure, I see. Lets drop a whole load of items that you don't own. Great idea."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player drops an unknown item.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getDropUnknownMessage(String name) {
        
        String[] messages = {"I don't know what a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + " is.",
                             "You can't drop imaginary items, you know."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player leaves the item parameter null.
     * 
     * @return A feedback message.
     */
    public static String getDropNullMessage() {
        
        String[] messages = {"Drop what?",
                             "Are you going to finish that sentence?"};
    
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player inspects an item which is not there.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getInspectItemNotPresentMessage(String name) {
        
        String[] messages = {"I don't see " + name.toLowerCase() + " here.",
                             "There is no " + name.toLowerCase() + " here."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player inspects an unknown item.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getInspectUnknownMessage(String name) {
        
        String[] messages = {"What is a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + "?",
                             "You you even know what a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + " is?"};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player leaves the item parameter null.
     * 
     * @return A feedback message.
     */
    public static String getInspectNullMessage() {
        
        String[] messages = {"Inspect what?",
                            "I'm sorry, what exactly are we looking at?"};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player checks their health.
     * 
     * @param health The player's health.
     * 
     * @return A feedback message.
     */
    public static String getHealthMessage(int health) {
        
        String[] messages = {"You currently have " + health + "HP.",
                             "Your health is on " + health + "HP."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player attacks an entity which is not
     * present.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getEntityNotPresentMessage(String name) {
        
        String[] messages = {"Hmm, there doesn't seem to be a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + " here...",
                             "Are you sure there's a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + " here?",
                             "I don't see a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + "."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player attacks with a weapon they do not
     * have.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getWeaponUnownedMessage(String name) {
        
        String[] messages = {"You don't have a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + ".",
                             "Great idea! If only you had a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + "."};
                            
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player attacks with an unknown message.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getWeaponUnknownMessage(String name) {
        
        String[] messages = {"I don't know what a" + (StringTools.startsWithVowel(name) ? "n " : " ") + name.toLowerCase() + " is.",
                             "I'm half convinced that isn't a real thing."};
                        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player attacks an entity successfully.
     * 
     * @param name The name parameter
     * @param damage The damage parameter
     * 
     * @return A feedback message.
     */
    public static String getHitHurtMessage(String name, int damage) {
        
        String[] messages = {"You dealth " + damage + " HP to the " + name.toLowerCase() + ".",
                             "Ouch, you managed to inflict " + damage + " HP on the " + name.toLowerCase() + "."};

        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player kills an entity.
     * 
     * @param name The name parameter
     * 
     * @return A feedback message.
     */
    public static String getHitKillMessage(String name) {
        
        String[] messages = {"You killed the " + name.toLowerCase() + ".",
                             "You just slaughtered the " + name.toLowerCase() + ".",
                             "You killed it!"};

        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player leaves the item parameter nu;;.
     * 
     * @return A feedback message.
     */
    public static String getHitNullMessage() {
        
        String[] messages = {"Hit what?",
                             "Yes! Lets punch the sky a bit!"};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when the host disconnects from a server.
     * 
     * @return A feedback message.
     */
    public static String getHostDisconnectSuccessMessage() {
        
        String[] messages = {"You are no longer sharing your world!",
                             "Your world is now yours again you greedy bugger!"};
                
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a game is not hosted.
     * 
     * @return A feedback message.
     */
    public static String getHostNotSharingErrorMessage() {
        
        String[] messages = {"Your game isn't shared...",
                             "You know you're playing alone, right?"};
            
            
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when the host disconnects from a game.
     * 
     * @return A feedback message.
     */
    public static String getHostDisconnectErrorMessage() {
        
        String[] messages = {"There was a problem stopping your game... Oops...",
                             "Some games just aren't meant to be stopped."};
            
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player disconnects from a game.
     * 
     * @return A feedback message.
     */
    public static String getMultiplayerDisconnectSuccessMessage() {
        
        String[] messages = {"You have been disconnected from the server.",
                             "Come back again soon!",
                             "They might miss you, you know"};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player is not in a multiplayer game.
     * 
     * @return A feedback message.
     */
    public static String getMultiplayerNotInGameErrorMessage() {
        
        String[] messages = {"You aren't playing a multiplayer game!",
                             "Maybe you should join a game before you try to leave one!"};
            
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when something goes wrong disconnecting from a
     * game.
     * 
     * @return A feedback message.
     */
    public static String getMultiplayerDisconnectErrorMessage() {
        
        String[] messages = {"There was a problem disconnecting you from the game... Guess you're in limbo now!",
                             "Oh dear, it looks like something went wrong there... "};
            
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a game is loaded.
     * 
     * @return A feedback message.
     */
    public static String getGameLoadMessage() {
        
        String[] messages = {"Your game has been loaded!",
                             "We've loaded your game for you!"};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player wants help.
     * 
     * @return A feedback message.
     */
    public static String getHelpMessage() {
        
        String[] messages = {"Ummm, no.",
                             "Nice try."};
         
        return messages[getRandomIndex(messages.length)]; 
        
    }
    
    /**
     * The message displayed when a player quits.
     * 
     * @return A feedback message.
     */
    public static String getQuitMessage() {
        
        String[] messages = {"Hmmmph, good riddance.",
                             "You are the weakest link, goodbye.",
                             "Perhaps it's for the best."};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * The message displayed when a player enters an unknown command.
     * 
     * @return A feedback message.
     */
    public static String getUnknownCommandMessage() {
        
        String[] messages = {"Hmm, I expect to see a verb as the first word...",
                             "I'm sorry, but you'll have to speak English"};
        
        return messages[getRandomIndex(messages.length)];
        
    }
    
    /**
     * A simple method for generating a random index between two integers, one
     * of which is specified.
     * 
     * @param maximum The maximum number to generate
     * 
     * @return a random number
     */
    private static int getRandomIndex(int maximum) {
        
        return (int) (Math.random() * maximum);
        
    }
    
}
