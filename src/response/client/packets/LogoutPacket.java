package response.client.packets;

import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public class LogoutPacket extends Packet {

    public LogoutPacket(InetAddress address, int port) {
        super(address, port);
    }
    
    @Override
    protected String getMessage() {
        return "2{}";
    }

}
