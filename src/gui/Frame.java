package gui;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(int width, int height){
        setSize(width, height);
        setTitle("Snake");
        setVisible(true);
        setLayout(null);
        JLabel background = new JLabel();
        background.setBackground(Color.BLACK);
        background.setSize(width, height);
        background.setLocation(0, 0);
        background.setOpaque(true);
        add(background);
    }


}
