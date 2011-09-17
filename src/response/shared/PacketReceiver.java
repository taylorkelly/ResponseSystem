package response.shared;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author taylor
 */
public class PacketReceiver extends Thread {
    protected DatagramSocket socket = null;
    protected boolean running;
    protected PacketInterpreter interpreter;

    public PacketReceiver(DatagramSocket socket, PacketInterpreter interpreter) {
        this("PacketReceiver", socket, interpreter);
    }

    public PacketReceiver(String name, DatagramSocket socket, PacketInterpreter interpreter) {
        super(name);
        running = true;
        this.socket = socket;
        this.interpreter = interpreter;
    }

    @Override
    public void run() {
        while (running) {
            try {
                byte[] buf = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                
                System.out.println("Recieved request from: " + packet.getAddress() + " @ " + new Date().toString());
                String data = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Request: " + data);

                interpreter.interpret(data, packet.getAddress(), packet.getPort());

            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
                running = false;
            }
        }
        socket.close();
    }

    public void stopRunning() {
        running = false;
    }
}
