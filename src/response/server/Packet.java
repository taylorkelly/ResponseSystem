package response.server;

import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public abstract class Packet {
    protected DatagramSocket socket;
    protected InetAddress address;
    protected int port;

    public Packet(String data, DatagramSocket socket, InetAddress address, int port) {
        this.socket = socket;
        this.address = address;
        this.port = port;
        parseData(data);
    }

    protected abstract void parseData(String data);

    public abstract void reconcile();
}
