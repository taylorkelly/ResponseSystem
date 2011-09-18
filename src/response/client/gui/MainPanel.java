/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package response.client.gui;

import java.net.InetAddress;
import java.util.*;
import javax.swing.JPanel;
import response.client.packets.QuestionRequestPacket;

/**
 *
 * @author taylor
 */
public class MainPanel extends JPanel {
    private static WaitingPanel waiting;
    private InetAddress address;
    private int port;

    public MainPanel(InetAddress address, int port) {
        this.address = address;
        this.port = port;
        waiting = new WaitingPanel();
        this.add(waiting);
        requestQuestion();
    }

    private void requestQuestion() {
        Timer timer = new Timer();
        timer.schedule(new RequestTask(this, address, port), 100);
    }


    private class RequestTask extends TimerTask {
        private JPanel panel;
        private InetAddress address;
        private int port;

        public RequestTask(JPanel panel, InetAddress address, int port) {
            this.panel = panel;
            this.address = address;
            this.port = port;
        }

        @Override
        public void run() {
            String response = new QuestionRequestPacket(address, port).sendAndWaitForResponse(-1);
            System.out.println(response);
            this.cancel();
        }

    }
}
