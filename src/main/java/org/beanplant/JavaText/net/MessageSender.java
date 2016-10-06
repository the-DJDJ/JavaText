package org.beanplant.JavaText.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * The class responsible for sending messages on the network during gameplay.
 *
 * @author the_DJDJ
 */
public class MessageSender {
    
    /** The port to send messages to. */
    private final int port;
    
    /** The socket used for sending messages. */
    private final DatagramSocket socket;
    
    /** The address that packets are addressed to. */
    private final InetAddress address;
    
    /** The paket that is sent over the network. */
    private final DatagramPacket packet;
    
    /**
     * The default constructor. This creates a new instance of the MessageSender
     * with the specified port and host address.
     * 
     * @param port The port to connect to.
     * @param host The host to connect to.
     * 
     * @throws java.net.SocketException
     * @throws java.net.UnknownHostException
     */
    public MessageSender(int port, String host) throws SocketException, UnknownHostException {
        
        this.port = port;
        
        this.socket = new DatagramSocket();
        this.address = InetAddress.getByName(host);
        
        this.packet = new DatagramPacket(new byte[1024], 1024);
        
        this.packet.setAddress(address);
        this.packet.setPort(port);
        
    }
    
    /**
     * The method used to send a message over the network.
     * 
     * @param message The message, as a byte array
     * 
     * @throws java.io.IOException
     */
    public void sendMessage(Message message) throws IOException {
        
        this.packet.setData(message.getContents());
        this.packet.setLength(port);
                
        this.socket.send(packet);
        
    }
    
    /**
     * The method responsible for properly closing all network connections.
     */
    public void close(){
        
        this.socket.close();
        
    }
    
}
