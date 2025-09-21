package SPOILERMoeglicheLoesungMemorieSPOILER;

import java.awt.*;
import java.util.ArrayList;

public class Logic {

    public final int width;
    public final int height;

    public final int cardWidth = 50;
    public final int cardHeight = 100;
    public final int padding = 50;
    public final int symbolWidth = 20;
    public final int symbolHeight = 20;

    public Color backgroundC = Color.black;
    public Color cardC = Color.white;

    public ArrayList<Card> cards = new ArrayList<>();


    public Logic(int width, int height){
        this.width = width;
        this.height = height;

        cards.add(new Card(7, new Point(padding, padding)));
    }

}
