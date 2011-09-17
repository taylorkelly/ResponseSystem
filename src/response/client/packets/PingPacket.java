package response.client.packets;

import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public class PingPacket extends Packet {

    public PingPacket(InetAddress address, int port) {
        super(address, port);
    }
    
    @Override
    protected String getMessage() {
        return "3{}";
    }

}
