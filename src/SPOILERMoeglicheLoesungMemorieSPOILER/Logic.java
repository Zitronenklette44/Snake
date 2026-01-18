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

        ArrayList<Integer> usedSymbole = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for(int j = 0; j < width;j++) {
                    int temp = -1;
                do {
                    temp = (int) (Math.random() * 7);
                } while(usedSymbole.contains(temp));
                    usedSymbole.add(temp);
                    cards.add(new Card(temp, new Point(padding + j * cardWidth + j * padding / 2, padding + i * cardHeight + i * padding / 2)));
            }
        }
    }

}
