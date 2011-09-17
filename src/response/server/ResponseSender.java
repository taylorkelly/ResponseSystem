package response.server;

import java.io.*;
import java.net.*;

/**
 *
 * @author taylor
 */
public class ResponseSender extends Thread {
    protected DatagramSocket socket = null;
    protected String response = null;
    protected InetAddress address = null;
    protected int port;

    public ResponseSender(DatagramSocket socket, String response, DatagramPacket origin) {
        this(socket, response, origin.getAddress(), origin.getPort());
    }

    public ResponseSender(DatagramSocket socket, String response, InetAddress address, int port) {
        super("ResponseSender");
        this.socket = socket;
        this.response = response;
        this.address = address;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            byte[] buf = response.getBytes();

            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
