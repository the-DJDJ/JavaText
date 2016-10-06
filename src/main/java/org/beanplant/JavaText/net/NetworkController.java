package org.beanplant.JavaText.net;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is the class which sends messages on the network. This does not make a
 * distinction as to which messages are being send, it merely handles the
 * sending and receiving of messages
 *
 * @author the_DJDJ
 */
public class NetworkController {
    
    /** The port over which network interactions will occur. */
    private final int PORT;
    
    /** The IP address over which network interactions will occur. */
    private final String HOST;
    
    /** The MessageSender for this network. */
    private final MessageSender sender;
    
    /** The MessageReceiver for this network. */
    private final MessageReceiver receiver;
    
    /** Whether or not this NetworkController is active. */
    private boolean active;
    
    /**
     * The default constructor. This initialises variables and prepares the
     * class for connecting to the network
     * 
     * @param port The port on which to connect
     * @param host The IP to connect to
     * 
     * @throws java.io.IOException
     * @throws java.net.SocketException
     * @throws java.net.UnknownHostException
     */
    public NetworkController(int port, String host) throws SocketException, UnknownHostException, IOException {
        
        // Capture network details
        this.PORT = port;
        this.HOST = host;
        
        // Prepare the network
        this.sender = new MessageSender(PORT, HOST);
        this.receiver = new MessageReceiver(PORT, HOST);
        
        this.active = false;
        
    }
    
    /**
     * Returns whether or not this network controller is active.
     * 
     * @return whether or not this network controller is active.
     */
    public boolean isActive() {
        
        return this.active;
        
    }
    
    /**
     * Sets whether or not this network controller is active.
     * 
     * @param active whether or not the controller is active
     */
    public void setActive(boolean active){
        
        this.active = active;
        
    }
    
    /**
     * The method responsible for properly closing all network connections.
     */
    public void close() {
        
        try {
            
            this.sender.close();
            this.receiver.close();
        
        } catch (IOException ex) {
           
            Logger.getLogger(NetworkController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }
    
}
