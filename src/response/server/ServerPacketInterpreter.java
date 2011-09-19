package response.server;

import response.server.packets.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import response.shared.PacketInterpreter;

/**
 *
 * @author taylor
 */
public class ServerPacketInterpreter implements PacketInterpreter {
    protected DatagramSocket socket;

    public ServerPacketInterpreter(DatagramSocket socket) {
        this.socket = socket;
    }

    public void interpret(String data, InetAddress address, int port) {
        String[] pieces = data.split("\\{");

        int packetType = -1;
        try {
            packetType = Integer.parseInt(pieces[0]);
        } catch (Exception e) {
            ResponseSender sender = new ResponseSender(socket, "0", address, port);
            sender.start();
            return;
        }

        Packet specialPacket = null;

        switch (packetType) {
            case 1:
                specialPacket = new LoginPacket(data, socket, address, port);
                break;
            case 2:
                specialPacket = new LogoutPacket(data, socket, address, port);
                break;
            case 3:
                specialPacket = new PingPacket(data, socket, address, port);
                break;
            case 4:
                specialPacket = new QuestionRequestPacket(data, socket, address, port);
                break;
            case 5:
                specialPacket = new QuestionResponsePacket(data, socket, address, port);
                break;
            case 6:
                specialPacket = new AnswerRequestPacket(data, socket, address, port);
                break;
        }

        if (specialPacket != null) {
            specialPacket.reconcile();
        } else {
            ResponseSender sender = new ResponseSender(socket, "0", address, port);
        }
    }
}
