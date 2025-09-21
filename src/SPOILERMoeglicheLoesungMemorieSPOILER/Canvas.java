package SPOILERMoeglicheLoesungMemorieSPOILER;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JLabel {

    private final Logic logic;

    public Canvas(int width, int height, Logic logic){
        this.logic = logic;
        setSize(width, height);
    }

    @Override
    protected void paintComponent(Graphics gd) {
        Graphics2D g = (Graphics2D) gd;

        g.setColor(logic.backgroundC);
        g.fillRect(0, 0, getWidth(), getHeight());

        for(Card c : logic.cards){
            c.paintCard(g, logic);
        }


    }
}
