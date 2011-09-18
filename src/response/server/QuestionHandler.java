package response.server;

import response.shared.QuestionType;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
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

    ConcurrentSkipListSet<InetAddress> waitingForAnswer = null;
    ConcurrentHashMap<InetAddress, String> userAnswers = null;


    public QuestionHandler(DatagramSocket socket) {
        this.socket = socket;
        waitingForQuestion = new ConcurrentSkipListSet<InetAddress>(new AddressComparator());
        waitingForAnswer = new ConcurrentSkipListSet<InetAddress>(new AddressComparator());
        portMap = new ConcurrentHashMap<InetAddress, Integer>();
        userAnswers = new ConcurrentHashMap<InetAddress, String>();
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

    public boolean setQuestion(QuestionType questionType) {
        if (waitingForQuestion.size() > 0) {
            ArrayList<InetAddress> addresses = new ArrayList<InetAddress>();
            addresses.addAll(waitingForQuestion);
            waitingForAnswer.addAll(waitingForQuestion);
            waitingForQuestion.clear();

            for (InetAddress address : addresses) {
                int port = portMap.get(address);
                new ResponseSender(socket, questionType.toString(), address, port).start();
            }

            portMap.clear();
            return true;
        } else {
            return false;
        }

    }

    public boolean isWaitingForAnswer(InetAddress address) {
        return waitingForAnswer.contains(address);
    }

    public void setAnswer(InetAddress address, String answer) {
        userAnswers.put(address, answer);
    }
}
