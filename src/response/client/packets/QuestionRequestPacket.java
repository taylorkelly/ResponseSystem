package response.client.packets;

import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public class QuestionRequestPacket extends Packet {

    public QuestionRequestPacket(InetAddress address, int port) {
        super(address, port);
    }
    
    @Override
    protected String getMessage() {
        return "4{}";
    }

}
