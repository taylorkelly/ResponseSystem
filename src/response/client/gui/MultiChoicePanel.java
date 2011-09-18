/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package response.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author taylor
 */
public class MultiChoicePanel extends QuestionPanel {
    private JButton answerOne = null;
    private JButton answerTwo = null;
    private JButton answerThree = null;
    private JButton answerFour = null;

    public MultiChoicePanel(MainPanel panel) {
        super(panel);

        answerOne = new JButton("One");
        answerTwo = new JButton("Two");
        answerThree = new JButton("Three");
        answerFour = new JButton("Four");

        AnswerListener listener = new AnswerListener();
        answerOne.addActionListener(listener);
        answerTwo.addActionListener(listener);
        answerThree.addActionListener(listener);
        answerFour.addActionListener(listener);

        this.add(answerOne);
        this.add(answerTwo);
        this.add(answerThree);
        this.add(answerFour);
    }

    @Override
    public void reportSuccess() {
    }

    private class AnswerListener implements ActionListener {
        private JButton disabled;

        public void actionPerformed(ActionEvent ae) {
            if (disabled != null)
                disabled.setEnabled(true);
            if (ae.getSource() == answerOne) {
                setAnswer("1");
                answerOne.setEnabled(false);
                disabled = answerOne;
            } else if (ae.getSource() == answerTwo) {
                setAnswer("2");
                answerTwo.setEnabled(false);
                disabled = answerTwo;
            } else if (ae.getSource() == answerThree) {
                setAnswer("3");
                answerThree.setEnabled(false);
                disabled = answerThree;
            } else if (ae.getSource() == answerFour) {
                setAnswer("4");
                answerFour.setEnabled(false);
                disabled = answerFour;
            }
        }
    }
}
