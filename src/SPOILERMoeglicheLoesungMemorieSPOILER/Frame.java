package SPOILERMoeglicheLoesungMemorieSPOILER;

import javax.swing.*;

public class Frame extends JFrame {

    private final Logic logic;

    public Frame(Logic logic){
        this.logic = logic;
        setSize(logic.cardWidth * logic.width + logic.padding * 2 + logic.padding * logic.width / 2, logic.cardHeight * logic.height + logic.padding * 2 + logic.padding * logic.height / 2);
        setVisible(true);
        setLayout(null);

        Canvas canvas = new Canvas(getWidth(), getHeight(), logic);
        canvas.setLocation(0, 0);
        add(canvas);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

    }

}
