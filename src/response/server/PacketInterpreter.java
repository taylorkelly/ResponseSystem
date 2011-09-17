package response.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public class PacketInterpreter {
    protected DatagramSocket socket;

    public PacketInterpreter(DatagramSocket socket) {
        this.socket = socket;
    }

    public void interpret(String data, InetAddress address, int port) {
        String[] pieces = data.split("{");

        int packetType = -1;
        try {
            packetType = Integer.parseInt(pieces[0]);
        } catch (Exception e) {
            ResponseSender sender = new ResponseSender(socket, "0", address, port);
            sender.start();
            return;
        }

        Packet specialPacket = null;

        switch(packetType) {
            case 1:
                specialPacket = new LoginPacket(data, socket, address, port);
                break;
        }

        if(specialPacket != null) {
            specialPacket.reconcile();
        }

    }
}
