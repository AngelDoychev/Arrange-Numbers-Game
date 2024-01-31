package ArrangeNumbersGame;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DisplayImpl implements Display, ActionListener {
    private JButton[][] matrix;
    private JFrame frame;
    private JPanel panel;
    private boolean hasWon;

    public DisplayImpl(int count) {
        setHasWon(false);
        setMatrix(new JButton[count][count]);
        int numbersCount = 0;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (numbersCount == 0) {
                    setMatrixButton(i, j, new JButton());
                    numbersCount++;
                } else {
                    setMatrixButton(i, j, new JButton(Integer.toString(numbersCount++)));
                }
                this.matrix[i][j].setFont(new FontUIResource("Helvetica", 1, count > 5 ? 25 : 50));
            }
        }
        setFrame(new JFrame("ArrangeNumbersGame"));
        getFrame().setVisible(true);
        getFrame().setSize(500, 500);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPanel(new JPanel());
        getFrame().add(getPanel());
        getPanel().setLayout(new GridLayout(count, count));
        getFrame().setLocationRelativeTo(null);
    }

    public JButton getMatrixButton(int i, int j) {
        return matrix[i][j];
    }

    public JButton[][] getMatrix() {
        return matrix;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean isHasWon() {
        return hasWon;
    }

    public void setMatrix(JButton[][] matrix) {
        this.matrix = matrix;
    }

    public void setMatrixButton(int i, int j, JButton button) {
        this.matrix[i][j] = button;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }

    @Override
    public boolean isSolved() {
        boolean isCorrect = true;
        int correctNum = 1;
        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < getMatrix().length; j++) {
                if (i == getMatrix().length - 1 && j == getMatrix().length - 1) {
                    if (!getMatrixButton(i, j).getLabel().equals("")) {
                        isCorrect = false;
                    }
                } else if (!String.valueOf(correctNum++).equals(getMatrixButton(i, j).getLabel())) {
                    isCorrect = false;
                }
            }
        }
        return isCorrect;
    }

    @Override
    public void start() {
        List<JButton[]> buttonsList = Arrays.asList(getMatrix());
        Collections.shuffle(buttonsList);
        buttonsList.toArray(getMatrix());
        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < getMatrix().length; j++) {
                getPanel().add(getMatrixButton(i, j));
                getMatrixButton(i, j).setPreferredSize(new Dimension(120, 120));
                getMatrixButton(i, j).addActionListener(this);
            }
        }
    }

    @Override
    public void win() {
        JFrame winFrame = new JFrame("You win!");
        JPanel winPanel = new JPanel();
        winFrame.setVisible(true);
        winFrame.setLocationRelativeTo(null);
        winFrame.setSize(400, 200);
        winFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winFrame.add(winPanel);
        JButton closeButton = new JButton("Close");
        JLabel winLabel = new JLabel("You Win!");
        winPanel.add(winLabel);
        winPanel.add(closeButton);
        setHasWon(true);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winFrame.dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent num) {
        if (isSolved()) {
            win();
        }
        if (isHasWon()) {
            getFrame().dispose();
        }
        for (int i = 0; i < getMatrix().length; i++) {
            for (int j = 0; j < getMatrix().length; j++) {
                if (getMatrixButton(i, j).getText().equals(num.getActionCommand())) {
                    JButton current = getMatrixButton(i, j);
                    if (isValidIndex(i - 1) && getMatrixButton(i - 1, j).getLabel().equals("")) {
                        String temp = current.getText();
                        getMatrixButton(i, j).setLabel(getMatrixButton(i - 1, j).getText());
                        getMatrixButton(i - 1, j).setLabel(temp);
                        return;
                    } else if (isValidIndex(i + 1) && getMatrixButton(i + 1, j).getLabel().equals("")) {
                        String temp = current.getText();
                        getMatrixButton(i, j).setLabel(getMatrixButton(i + 1, j).getText());
                        matrix[i + 1][j].setLabel(temp);
                        return;
                    } else if (isValidIndex(j - 1) && getMatrixButton(i, j - 1).getLabel().equals("")) {
                        String temp = current.getText();
                        getMatrixButton(i, j).setLabel(getMatrixButton(i, j - 1).getText());
                        getMatrixButton(i, j - 1).setLabel(temp);
                        return;
                    } else if (isValidIndex(j + 1) && getMatrixButton(i, j + 1).getLabel().equals("")) {
                        String temp = current.getText();
                        getMatrixButton(i, j).setLabel(getMatrixButton(i, j + 1).getText());
                        getMatrixButton(i, j + 1).setLabel(temp);
                        return;
                    }
                }
            }
        }
    }
    private boolean isValidIndex(int index) {
        return index >= 0 && index < getMatrix().length;
    }
}
