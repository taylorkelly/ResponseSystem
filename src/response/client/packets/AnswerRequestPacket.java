package response.client.packets;

import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public class AnswerRequestPacket extends Packet {

    public AnswerRequestPacket(InetAddress address, int port) {
        super(address, port);
    }
    
    @Override
    protected String getMessage() {
        return "6{}";
    }

}
