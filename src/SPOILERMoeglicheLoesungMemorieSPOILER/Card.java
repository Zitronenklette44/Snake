package SPOILERMoeglicheLoesungMemorieSPOILER;

import java.awt.*;

public class Card {
    public int symbol;
    public boolean selected = true;
    public Point position;


    public Card(int symbol, Point position){
        this.symbol = symbol;
        this.position = position;
    }

    public void paintCard(Graphics2D g, Logic logic){

        g.setColor(logic.cardC);
        g.drawRoundRect(position.x, position.y, logic.cardWidth, logic.cardHeight, 20, 20);

        if(selected){
            Shapes.drawShape(symbol, logic.cardC, g, logic, position);
        }else{
            Shapes.drawQuestionmark(logic.cardC, g, logic, position);
        }


    }

}
