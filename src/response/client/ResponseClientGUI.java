package response.client;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import javax.swing.*;
import response.client.packets.LogoutPacket;

/**
 *
 * @author taylor
 */
public class ResponseClientGUI extends JFrame {
    private InetAddress address;
    private int port;

    public ResponseClientGUI(InetAddress address, int port) {
        super("ResponseClient");
        this.address = address;
        this.port = port;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new CloseListener());
        this.pack();
    }

    private class CloseListener implements WindowListener {
        public void windowOpened(WindowEvent we) {
        }

        public void windowClosing(WindowEvent we) {
            new LogoutPacket(address, port).send();
        }

        public void windowClosed(WindowEvent we) {
        }

        public void windowIconified(WindowEvent we) {
        }

        public void windowDeiconified(WindowEvent we) {
        }

        public void windowActivated(WindowEvent we) {
        }

        public void windowDeactivated(WindowEvent we) {
        }
    }
}
