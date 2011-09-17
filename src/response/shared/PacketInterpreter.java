package response.shared;

import java.net.InetAddress;

/**
 *
 * @author taylor
 */
public interface PacketInterpreter {
    public void interpret(String data, InetAddress address, int port);
}
