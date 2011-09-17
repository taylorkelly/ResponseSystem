package response.server.packets;

import java.net.DatagramSocket;
import java.net.InetAddress;
import response.server.ResponseSender;

/**
 *
 * @author taylor
 */
public class PingPacket extends Packet {
    public PingPacket(String data, DatagramSocket socket, InetAddress address, int port) {
        super(data, socket, address, port);
    }

    @Override
    protected void parseData(String data) {
    }

    @Override
    public void reconcile() {
        new ResponseSender(socket, "1", address, port).start();
    }
}
