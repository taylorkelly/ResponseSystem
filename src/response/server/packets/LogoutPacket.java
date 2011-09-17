package response.server.packets;

import java.net.DatagramSocket;
import java.net.InetAddress;
import response.server.ResponseSender;
import response.server.ResponseServer;

/**
 *
 * @author taylor
 */
public class LogoutPacket extends Packet {
    public LogoutPacket(String data, DatagramSocket socket, InetAddress address, int port) {
        super(data, socket, address, port);
    }

    @Override
    protected void parseData(String data) {
    }

    @Override
    public void reconcile() {
        if (ResponseServer.getServer().logout(address)) {
            new ResponseSender(socket, "1", address, port).start();
        } else {
            new ResponseSender(socket, "0", address, port).start();
        }
    }
}
