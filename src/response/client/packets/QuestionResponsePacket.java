package response.client.packets;

import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public class QuestionResponsePacket extends Packet {
    private String answer;

    public QuestionResponsePacket(String answer, InetAddress address, int port) {
        super(address, port);
        this.answer = answer;
    }
    
    @Override
    protected String getMessage() {
        return "5{" + answer + "}";
    }

}
