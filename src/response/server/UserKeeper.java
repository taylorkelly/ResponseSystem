package response.server;

import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author taylor
 */
public class UserKeeper {
    ConcurrentHashMap<InetAddress, UserData> map = null;

    public UserKeeper() {
        map = new ConcurrentHashMap<InetAddress, UserData>();
    }

    public boolean login(InetAddress address) {
        if(map.containsKey(address)) {
            return false;
        } else {
            map.put(address, new UserData());
            return true;
        }
    }

    public String userData(InetAddress address) {
        if(map.containsKey(address)) {
            return map.get(address).toString();
        } else{
            return null;
        }
    }

}
