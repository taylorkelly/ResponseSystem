package response.server;

import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public class LoginPacket extends Packet {
    public LoginPacket(String data, DatagramSocket socket, InetAddress address, int port) {
        super(data, socket, address, port);
    }

    @Override
    protected void parseData(String data) {
    }

    @Override
    public void reconcile() {
        if (ResponseServer.getServer().login(address)) {
            new ResponseSender(socket, "1", address, port).start();
        } else {
            new ResponseSender(socket, ResponseServer.getServer().userData(address), address, port).start();
        }
    }
}
