package response.client;

import java.net.*;
import javax.swing.JOptionPane;
import response.client.packets.LoginPacket;
import response.client.packets.PingPacket;

/**
 *
 * @author taylor
 */
public class ResponseClient {
    public static void main(String[] args) {
        try {
            ResponseClient client = new ResponseClient();
            client.start();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Unkown Host Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
    private ResponseClientGUI gui = null;
    private int port;
    private InetAddress address = null;

    public ResponseClient() throws UnknownHostException {
        String response = null;
        while (response == null || !response.equals("1")) {
            address = InetAddress.getByName(getValidInetAddress());
            port = getValidPort();
            response = new PingPacket(address, port).sendAndWaitForResponse(port);
            if (response == null) {
                JOptionPane.showMessageDialog(null,
                        "No response from the server\nVerify that the address+port are correct",
                        "No response",
                        JOptionPane.ERROR_MESSAGE);
                int n = JOptionPane.showConfirmDialog(
                        null,
                        "Would you like to try another connection?",
                        "Retry?",
                        JOptionPane.YES_NO_OPTION);
                if (n == 1) {
                    System.exit(0);
                }
            } else if (!response.equals("1")) {
                JOptionPane.showMessageDialog(null,
                        "Invalid response from the server.",
                        "Invalid Response",
                        JOptionPane.ERROR_MESSAGE);
                int n = JOptionPane.showConfirmDialog(
                        null,
                        "Would you like to try another connection?",
                        "Retry?",
                        JOptionPane.YES_NO_OPTION);
                if (n == 1) {
                    System.exit(0);
                }
            }
        }
        gui = new ResponseClientGUI(address, port);
    }

    public void start() {
        new LoginPacket(address, port).send();
        gui.setVisible(true);
    }

    public static int getValidPort() {
        int port = -1;

        while (port == -1) {
            String s = (String) JOptionPane.showInputDialog(
                    null,
                    "Enter the port to connect to:",
                    "Port",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "4445");

            if ((s != null) && (s.length() > 0)) {
                try {
                    if (Integer.parseInt(s) > 0) {
                        port = Integer.parseInt(s);
                    }
                } catch (Exception e) {
                }
            }
        }
        return port;
    }

    public static String getValidInetAddress() {
        String address = "";

        while (address.equals("")) {
            String s = (String) JOptionPane.showInputDialog(
                    null,
                    "Enter the address to connect to:",
                    "Address",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");

            if ((s != null) && (s.length() > 0)) {
                address = s;
            }
        }
        return address;
    }
}
