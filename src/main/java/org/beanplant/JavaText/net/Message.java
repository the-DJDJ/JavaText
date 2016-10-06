package org.beanplant.JavaText.net;

import java.util.Arrays;

/**
 * A Messages class, used for forging messages that can be sent over the network
 *
 * @author the_DJDJ
 */
public class Message {
    
    public static byte PLAIN  = (byte) 0;
    public static byte ALIVE  = (byte) 1;
    public static byte LOGIN  = (byte) 2;
    public static byte LOGOFF = (byte) 3;
    public static byte START  = (byte) 4;
    public static byte STOP   = (byte) 5;
    public static byte WORLD  = (byte) 6;
    public static byte MSG    = (byte) 7;
    
    /** The entire contents of the message. */
    private final byte[] contents;
        
    /**
     * The default constructor. This takes the type of the message and it's
     * payload as parameters, and constructs a byte message to send over the
     * network.
     * 
     * @param type The type of message that this is.
     * @param payload The contents of the message
     */
    public Message(byte type, byte[] payload) {
        
        this.contents = new byte[payload.length + 1];
        
        this.contents[0] = type;
        System.arraycopy(payload, 0, this.contents, 1, payload.length);
        
        System.out.println(Arrays.toString(this.contents));
        
    }
    
    /**
     * Returns which type of message this is.
     * 
     * @return the message type.
     */
    public byte getType() {
        
        return this.contents[0];
        
    }
    
    /**
     * Returns the payload of the message.
     * 
     * @return the message payload
     */
    public byte[] getPayload() {
        
        return Arrays.copyOfRange(this.contents, 1, this.contents.length);
        
    }
    
    /**
     * Returns the entire contents of the message, including the type
     * 
     * @return the contents of the message
     */
    public byte[] getContents() {
        
        return this.contents;
        
    }
    
}
