package response.server.packets;

import java.net.DatagramSocket;
import java.net.InetAddress;
import response.server.ResponseSender;
import response.server.ResponseServer;

/**
 *
 * @author taylor
 */
public class QuestionResponsePacket extends Packet {
    private String answer;

    public QuestionResponsePacket(String data, DatagramSocket socket, InetAddress address, int port) {
        super(data, socket, address, port);
    }

    @Override
    protected void parseData(String data) {
        int index1 = data.indexOf("{");
        int index2 = data.indexOf("}");
        System.out.println(data.substring(index1 + 1, index2));
        answer = data.substring(index1 + 1, index2);
    }

    @Override
    public void reconcile() {
        if (ResponseServer.getServer().isWaitingForAnswer(address)) {
            ResponseServer.getServer().setAnswer(address, answer);
            new ResponseSender(socket, "1", address, port).start();
        } else {
            new ResponseSender(socket, "0", address, port).start();
        }
    }
}
