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
            Shapes.drawShape(symbol, logic.cardC, g, logic, new Point(position.x + logic.cardWidth / 3, position.y + logic.cardHeight / 3));
        }else{
            Shapes.drawQuestionmark(logic.cardC, g, logic, position);
        }


    }

}
