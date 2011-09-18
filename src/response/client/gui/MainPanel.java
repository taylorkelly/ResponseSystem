package response.client.gui;

import java.awt.Dimension;
import java.net.InetAddress;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import response.client.packets.*;
import response.shared.QuestionType;

/**
 *
 * @author taylor
 */
public class MainPanel extends JPanel {
    private static WaitingPanel waiting;
    private QuestionPanel qPanel = null;
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

    public void setAnswer(String answer) {
        String response = new QuestionResponsePacket(answer, address, port).sendAndWaitForResponse(1000);
        if (response != null && response.equals("1")) {
            qPanel.reportSuccess();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error sending answer to server.",
                    "Invalid Response",
                    JOptionPane.ERROR_MESSAGE);
        }
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
            if (response != null && !response.equals("0")) {
                QuestionType type = QuestionType.valueOf(response);
                remove(waiting);
                switch (type) {
                    case MULTI_CHOICE:
                        qPanel = new MultiChoicePanel(MainPanel.this);
                        add(qPanel);
                        revalidate();
                        repaint();
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Invalid Response for Question Type",
                        "Invalid Response",
                        JOptionPane.ERROR_MESSAGE);
            }
            this.cancel();
        }
    }
}
