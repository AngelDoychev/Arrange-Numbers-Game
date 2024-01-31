package ArrangeNumbersGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGame implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton button;
    private JTextField textField;
    private int size;

    public JFrame getFrame() {
        return frame;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public StartGame() {
        setFrame(new JFrame("Start Game"));
        getFrame().setVisible(true);
        getFrame().setSize(500, 500);
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getFrame().setLayout(new FlowLayout());
        setPanel(new JPanel());
        getFrame().add(getPanel());
        getFrame().setLocationRelativeTo(null);
        setLabel(new JLabel("Select the size of the game."));
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public void run(){
        getFrame().add(this.label);
        setTextField(new JTextField(15));
        getTextField().setPreferredSize(new Dimension(250,40));
        getFrame().add(this.textField);
        setButton(new JButton("Choose"));
        getFrame().add(getButton());
        getFrame().pack();
        chooseSize();
    }
    public void chooseSize(){
        getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSize(Integer.parseInt(getTextField().getText()));
                DisplayImpl display3x3 = new DisplayImpl(size);
                display3x3.start();
                getFrame().dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.size = Integer.parseInt(getTextField().getText());
        getFrame().dispose();
    }
}
