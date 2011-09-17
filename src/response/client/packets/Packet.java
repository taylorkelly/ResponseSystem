/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package response.client.packets;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taylor
 */
public abstract class Packet {
    protected InetAddress address;
    protected int port;
    private String response;

    public Packet(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public final void send() {
        response = null;
        PacketSender sender = new PacketSender(getMessage(), address, port, this);
        sender.start();
    }

    public final String sendAndWaitForResponse(int waitTime) {
        send();
        int SLEEP_TIME = 50;
        for (int i = 0; i < waitTime; i += SLEEP_TIME) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                return null;
            }
            if (getResponse() != null) {
                return getResponse();
            }
        }
        return getResponse();
    }

    public final synchronized void setResponse(String response) {
        this.response = response;
    }

    public final synchronized String getResponse() {
        return response;
    }

    final void response(String response) {
        setResponse(response);
    }

    protected abstract String getMessage();

    private static class PacketSender extends Thread {
        protected String message = null;
        protected InetAddress address = null;
        protected int port;
        protected Packet responsePacket = null;

        public PacketSender(String message, InetAddress address, int port, Packet packet) {
            super("PacketSender");
            this.message = message;
            this.address = address;
            this.port = port;
            this.responsePacket = packet;
        }

        @Override
        public void run() {
            try {
                DatagramSocket socket = new DatagramSocket();
                byte[] buf = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);

                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                responsePacket.response(received);

                socket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
