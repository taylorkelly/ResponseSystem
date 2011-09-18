/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package response.server.gui;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author taylor
 */
public class StatsPanel extends JPanel {
    private JLabel onlineCount;
    private JLabel waitingCount;

    public StatsPanel() {
        super(new BorderLayout());
        onlineCount = new JLabel("Online: 0");
        waitingCount = new JLabel("Waiting: 0");

        this.add(onlineCount, BorderLayout.WEST);
        this.add(waitingCount, BorderLayout.EAST);
    }

    public void updateUserCount(int num) {
        onlineCount.setText("Online: " + num);
    }

    public void updateWaitingCount(int num) {
        waitingCount.setText("Waiting: " + num);
    }

}
