
package response.server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author taylor
 */
public class ControlPanel extends JPanel {
    private ResponseServerGUI main = null;
    private JButton endQ = null;

    public ControlPanel(ResponseServerGUI main) {
        endQ = new JButton("End");
        endQ.addActionListener(new ButtonListener());
        this.add(endQ);
        this.main = main;
    }

    private void endQuestion() {

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() == endQ) {
                endQuestion();
            }
        }

    }



}
