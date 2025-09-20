package SPOILERMoeglicheLoesungSPOILER;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Logik {
    public int width;
    public int height;
    public boolean hadApple;
    ArrayList<Vector2D> positions = new ArrayList<>();
    public KeyListener keyListener;
    int direction = -1;
    int maxApples = 1;
    ArrayList<Vector2D> apples = new ArrayList<>();
    public final int blockSize = 32;
    public final int padding = 50;
    public final int startSize = 2;
    public int GameSpeed = 400;
    Timer timer = new Timer(500, null);
    boolean gameOver= false;

    int points = 0;
    int time = 0;
    long startTime;

    public Logik(int width, int height){
        this.width = width;
        this.height = height;

        keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int value = e.getKeyCode();
                switch (value){
                    case KeyEvent.VK_W:    // "W"
                        if(direction == 1) break;
                        direction = 0;
                        break;
                    case KeyEvent.VK_S:     // "S"
                        if(direction == 0) break;
                        direction = 1;
                        break;
                    case KeyEvent.VK_A:     // "A"
                        if(direction == 3) break;
                        direction = 2;
                        break;
                    case KeyEvent.VK_D:     // "D"
                        if(direction == 2) break;
                        direction = 3;
                        break;
                }
            }
        };

        start();

        timer.addActionListener(e -> {
            hadApple = false;

            if(direction == -1) return;     //stop Game from running while not moved

            if(startTime == 0) startTime = System.currentTimeMillis() / 1000;       //set StartTime
            time = Math.toIntExact(System.currentTimeMillis() / 1000 - startTime);

            // move Segments
            for (int i = positions.size() - 1; i > 0; i--) {
                positions.get(i).set(positions.get(i - 1));
            }

            //move head
            Vector2D head = positions.get(0);
            if (direction == 0) head.add(0, -1);
            if (direction == 1) head.add(0, 1);
            if (direction == 2) head.add(-1, 0);
            if (direction == 3) head.add(1, 0);

            //spawn Apples
            if(apples.size() < maxApples) {
                Vector2D vec;
                do {
                    vec = new Vector2D((int) (Math.random() * width), (int) (Math.random() * height));
                } while (positions.contains(vec) || apples.contains(vec));  //only when Space is empty
                apples.add(vec);
            }

            //check self Collision
            for (int i = 1; i < positions.size(); i++) {
                if(positions.get(0).equals(positions.get(i))) {
                    gameOver(0);
                }
                //System.out.println("Snake->"+ positions.get(i));
            }

            //check border Collision
            if(positions.get(0).x < 0 || positions.get(0).y < 0 || positions.get(0).x >= width || positions.get(0).y >= height){
                gameOver(1);
            }

            //check apple collision
            for (int i = 0; i < apples.size(); i++) {
                if(positions.get(0).equals(apples.get(i))) {
                    positions.add(positions.get(positions.size() -1).cpy());
                    apples.remove(i);
                    maxApples = Math.max(positions.size() / 5, maxApples);
                    GameSpeed = Math.max(Math.min(500 - positions.size() * 10, GameSpeed), 100);
                    timer.setDelay(GameSpeed);
                    System.out.println("GameSpeed->" + GameSpeed);
                    hadApple = true;
                    points++;
                }
            }
        });
        timer.start();
    }

    public void start(){
        positions.add(new Vector2D(width / 2, height / 2));
        for (int i = 0; i < startSize; i++) {
            positions.add(new Vector2D(-10, -10));
        }
    }

    void gameOver(int reason){
        if(positions.size() == startSize + 1 && reason != 1) return;
        timer.stop();
        gameOver = true;
        System.out.println("Game Over");
    }

}
