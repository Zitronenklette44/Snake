package gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Color backgroundColor = Color.BLACK;

    public Frame(int width, int height){
        setSize(width, height);
        setLayout(null);

        JLabel background = new JLabel();
        background.setBackground(backgroundColor);
        background.setOpaque(true);
        background.setBounds(0, 0, width, height);
        add(background);

        setTitle("Snake");
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addListeners();
    }

    private void addListeners() {

    }
}
