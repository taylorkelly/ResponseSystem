package response.server;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author taylor
 */
public class ResponseServerGUI extends JFrame {
    private JLabel userCount;
    
    public ResponseServerGUI() {
        super("ResponseServer");
        userCount = new JLabel("0");
        JPanel southPanel = new JPanel();



        southPanel.add(userCount);
        this.add(southPanel, BorderLayout.SOUTH);
    }

    public void updateUserCount(int num) {
        userCount.setText(String.valueOf(num));
    }

    

}
