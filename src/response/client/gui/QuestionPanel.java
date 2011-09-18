/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package response.client.gui;

import javax.swing.JPanel;

/**
 *
 * @author taylor
 */
public abstract class QuestionPanel extends JPanel {
    private MainPanel panel;

    public QuestionPanel(MainPanel panel) {
        this.panel = panel;
    }

    protected final void setAnswer(String answer) {
        panel.setAnswer(answer);
    }

    public abstract void reportSuccess();
}
