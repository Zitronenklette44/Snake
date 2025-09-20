package SPOILERMoeglicheLoesungSPOILER;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Color backgroundColor = Color.BLACK;
    public Color snakeColor = Color.GREEN;
    public Color appleColor = Color.RED;

    public Frame(Logik logic){
        setSize(logic.width * logic.blockSize + logic.padding * 3, logic.height * logic.blockSize + logic.padding * 3);
        setLayout(null);

        JLabel background = new JLabel();
        background.setBackground(backgroundColor);
        background.setOpaque(true);
        background.setBounds(0, 0, getWidth(), getHeight());
        add(background);

        Canvas canvas = new Canvas(backgroundColor, snakeColor, appleColor, logic);
        canvas.setBackground(backgroundColor);
        canvas.setOpaque(true);
        canvas.setBounds((int) (logic.padding / 2.5), (int) (logic.padding / 2.5), getWidth() - logic.padding, getHeight()- logic.padding);
        add(canvas);

        setTitle("Snake");
        setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addKeyListener(logic.keyListener);
    }


}
