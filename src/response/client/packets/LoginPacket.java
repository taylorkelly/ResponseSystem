package response.client.packets;

import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public class LoginPacket extends Packet {

    public LoginPacket(InetAddress address, int port) {
        super(address, port);
    }
    
    @Override
    protected String getMessage() {
        return "1{}";
    }

}
