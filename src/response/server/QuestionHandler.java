/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package response.server;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import response.shared.AddressComparator;

/**
 *
 * @author taylor
 */
public class QuestionHandler {
    private DatagramSocket socket;
    ConcurrentSkipListSet<InetAddress> waitingForQuestion = null;
    ConcurrentHashMap<InetAddress, Integer> portMap = null;

    
    public QuestionHandler(DatagramSocket socket) {
        this.socket = socket;
        waitingForQuestion = new ConcurrentSkipListSet<InetAddress>(new AddressComparator());
        portMap = new ConcurrentHashMap<InetAddress, Integer>();
    }

    public void waitingForQuestion(InetAddress address, int port) {
        waitingForQuestion.add(address);
        portMap.put(address, port);
    }

    public int waitingCount() {
        return waitingForQuestion.size();
    }

    public void remove(InetAddress address) {
        waitingForQuestion.remove(address);
        portMap.remove(address);
    }

}
