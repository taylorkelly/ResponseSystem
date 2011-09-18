package response.server.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import response.shared.QuestionType;
import response.server.ResponseServer;

/**
 *
 * @author taylor
 */
public class QuestionOptionPanel extends JPanel {
    private JButton multiChoice = null;
    private ResponseServerGUI main = null;

    public QuestionOptionPanel(ResponseServerGUI main) {
        this.main = main;
        multiChoice = new JButton("Multiple Choice");
        multiChoice.addActionListener(new ButtonListener());
        this.add(multiChoice);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == multiChoice) {
                if(ResponseServer.getServer().setQuestion(QuestionType.MULTI_CHOICE)) {
                    main.updateDisplay(QuestionType.MULTI_CHOICE);
                }
            }
        }
    }
}
