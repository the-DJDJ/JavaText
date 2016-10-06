package org.beanplant.JavaText.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * The class responsible for receiving messages on the network during gameplay.
 *
 * @author the_DJDJ
 */
public class MessageReceiver {
    
    /** The port to receive messages on. */
    private final int port;
    
    /** The socket used for receiving messages. */
    private final MulticastSocket socket;
    
    /** The address that packets are received from. */
    private final InetAddress address;
    
    /** The paket that is sent over the network. */
    private final DatagramPacket packet;
    
    /**
     * The default constructor. This creates a new instance of the
     * MessageReceiver with the specified port and host address.
     * 
     * @param port The port to connect to.
     * @param host The host to connect to.
     * 
     * @throws java.io.IOException
     * @throws java.net.UnknownHostException
     */
    public MessageReceiver(int port, String host) throws UnknownHostException, IOException {
        
        this.port = port;
        
        this.socket = new MulticastSocket(port);
        this.address = InetAddress.getByName(host);
        
        this.packet = new DatagramPacket(new byte[1024], 1024);
        
        this.socket.joinGroup(address);
        
    }
    
    /**
     * The method that receives a message on the network. This isn't finished
     * yet, messages still need to be handled
     * 
     * @throws java.io.IOException
     */
    public void receive() throws IOException{
        
        this.socket.receive(packet);
        
        // Perhaps handle this a bit
        // Also, this needs it's own thread
        
    }
    
    /**
     * The method responsible for properly closing all network connections.
     * 
     * @throws java.io.IOException
     */
    public void close() throws IOException{
        
        this.socket.leaveGroup(address);
        this.socket.close();
        
    }
    
}
