package response.server;

import java.net.InetAddress;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 *
 * @author taylor
 */
public class UserKeeper {
    ConcurrentHashMap<InetAddress, UserData> map = null;
    ConcurrentSkipListSet<InetAddress> online = null;

    public UserKeeper() {
        map = new ConcurrentHashMap<InetAddress, UserData>();
        online = new ConcurrentSkipListSet<InetAddress>(new AddressComparator());
    }

    public boolean login(InetAddress address) {
        online.add(address);
        if (map.containsKey(address)) {
            return false;
        } else {
            map.put(address, new UserData());
            return true;
        }
    }

    public String userData(InetAddress address) {
        if (map.containsKey(address)) {
            return map.get(address).toString();
        } else {
            return null;
        }
    }

    public boolean logout(InetAddress address) {
        return online.remove(address);
    }

    public int onlineUsers() {
        return online.size();
    }

    boolean isLoggedIn(InetAddress address) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class AddressComparator implements Comparator<InetAddress> {
        public int compare(InetAddress t, InetAddress t1) {
            byte[] taddress = t.getAddress();
            byte[] t1address = t1.getAddress();

            for (int i = 0; i < taddress.length; i++) {
                if (taddress[i] != t1address[i]) {
                    return (taddress[i] & 0xff) - (t1address[i] & 0xff);
                }
            }
            return 0;
        }
    }
}
