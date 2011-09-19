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
public class AnswerRequestPacket extends Packet {
    public AnswerRequestPacket(String data, DatagramSocket socket, InetAddress address, int port) {
        super(data, socket, address, port);
    }

    @Override
    protected void parseData(String data) {
    }

    @Override
    public void reconcile() {
        if (!ResponseServer.getServer().isWaitingForAnswer(address)) {
            new ResponseSender(socket, "0", address, port).start();
        }
    }
}
