package response.server.gui;

import java.awt.BorderLayout;
import javax.swing.*;
import response.shared.QuestionType;

/**
 *
 * @author taylor
 */
public class ResponseServerGUI extends JFrame {
    private StatsPanel stats = null;
    private QuestionOptionPanel qOPanel = null;
    private QuestionPanel qPanel = null;
    
    public ResponseServerGUI() {
        super("ResponseServer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stats = new StatsPanel();
        qOPanel = new QuestionOptionPanel(this);
        
        this.add(qOPanel, BorderLayout.EAST);
        this.add(stats, BorderLayout.SOUTH);
        this.pack();
    }

    public void updateUserCount(int num) {
        stats.updateUserCount(num);
    }

    public void updateWaitingCount(int num) {
        stats.updateWaitingCount(num);
    }

    public void updateDisplay(QuestionType questionType) {
        switch(questionType) {
            case MULTI_CHOICE:
                qPanel = new MultiChoicePanel();
                this.add(qPanel, BorderLayout.CENTER);
                break;
        }
    }
}
