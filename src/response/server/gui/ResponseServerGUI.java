package response.server.gui;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author taylor
 */
public class ResponseServerGUI extends JFrame {
    StatsPanel stats = null;
    
    public ResponseServerGUI() {
        super("ResponseServer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stats = new StatsPanel();

        

        this.add(stats, BorderLayout.SOUTH);
        this.pack();
    }

    public void updateUserCount(int num) {
        stats.updateUserCount(num);
    }

    public void updateWaitingCount(int num) {
        stats.updateWaitingCount(num);
    }

}
