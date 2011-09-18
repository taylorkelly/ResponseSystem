/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package response.server.packets;

import java.net.DatagramSocket;
import java.net.InetAddress;
import response.server.ResponseSender;
import response.server.ResponseServer;

/**
 *
 * @author taylor
 */
public class QuestionRequestPacket extends Packet {
    public QuestionRequestPacket(String data, DatagramSocket socket, InetAddress address, int port) {
        super(data, socket, address, port);
    }

    @Override
    protected void parseData(String data) {
    }

    @Override
    public void reconcile() {
        if (ResponseServer.getServer().isLoggedIn(address)) {
            ResponseServer.getServer().isWaitingForQuestion(address, port);
        } else {
            new ResponseSender(socket, "0", address, port).start();
        }
    }
}
