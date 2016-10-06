package org.beanplant.JavaText.handlers;

/**
 * The EventHandler interface. Any part of the application that wishes to catch
 * events that may be fired by a user must extend this class, and register
 * themselves as an EventHandler in the CommandParser class
 *
 * @author the_DJDJ
 */
public interface EventHandler {
    
    /**
     * The method called when an event is fired. Classes must extend this method
     * and then handle their event in their own way.
     * 
     * @param event a String with information on which event was fired 
     */
    public void fireEvent(String event);
    
}
