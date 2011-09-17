package response.server;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JOptionPane;

/**
 *
 * @author taylor
 */
public class ResponseServer {
    private static ResponseServer server;

    public static void main(String[] args) {
        getServer();
    }

    public static ResponseServer getServer() {
        if (server == null) {
            server = new ResponseServer();
        }
        return server;
    }
    private PacketReceiver receiver = null;
    private PacketInterpreter interpreter = null;
    private UserKeeper keeper = null;

    public ResponseServer() {
        DatagramSocket socket = null;
        int port = getValidPort();
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException ex) {
            System.out.println("Error: Unable to open socket on port " + port);
            return;
        }
        System.out.println("Successfully opened server on port " + port);

        keeper = new UserKeeper();
        interpreter = new PacketInterpreter(socket);
        receiver = new PacketReceiver(socket, interpreter);
        receiver.start();
    }

    public boolean login(InetAddress address) {
        return keeper.login(address);
    }

    public boolean logout(InetAddress address) {
        return keeper.logout(address);
    }

    public String userData(InetAddress address) {
        return keeper.userData(address);
    }

    public static int getValidPort() {
        int port = -1;

        while (port == -1) {
            String s = (String) JOptionPane.showInputDialog(
                    null,
                    "Enter the port to open to:",
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
}
