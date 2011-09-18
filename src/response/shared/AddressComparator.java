package response.shared;

import java.net.InetAddress;
import java.util.Comparator;


public class AddressComparator implements Comparator<InetAddress> {
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
