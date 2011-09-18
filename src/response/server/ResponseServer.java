package response.server;

import response.shared.PacketReceiver;
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
        server.start();
    }

    public static ResponseServer getServer() {
        if (server == null) {
            server = new ResponseServer();
        }
        return server;
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
    private PacketReceiver receiver = null;
    private ServerPacketInterpreter interpreter = null;
    private UserKeeper keeper = null;
    private ResponseServerGUI gui = null;

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
        interpreter = new ServerPacketInterpreter(socket);
        receiver = new PacketReceiver(socket, interpreter);
        gui = new ResponseServerGUI();
    }

    public void start() {
        receiver.start();
        gui.setVisible(true);
    }

    public boolean login(InetAddress address) {
        boolean login = keeper.login(address);
        gui.updateUserCount(keeper.onlineUsers());
        return login;
    }

    public boolean logout(InetAddress address) {
        boolean logout = keeper.logout(address);
        gui.updateUserCount(keeper.onlineUsers());
        return logout;
    }

    public String userData(InetAddress address) {
        return keeper.userData(address);
    }

    public boolean isLoggedIn(InetAddress address) {
        return keeper.isLoggedIn(address);
    }

    public void isWaitingForQuestion(InetAddress address, int port) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
