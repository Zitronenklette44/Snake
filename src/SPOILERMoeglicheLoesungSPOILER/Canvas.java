package SPOILERMoeglicheLoesungSPOILER;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JLabel {

    private Color backgroundC;
    private Color snakeC;
    private final Logik logic;
    private Color appleC;

    public Canvas(Color backgroundC, Color SnakeC, Color appleC, Logik logic){
        this.backgroundC = backgroundC;
        snakeC = SnakeC;
        this.appleC = appleC;
        this.logic = logic;
    }

    @Override
    protected void paintComponent(Graphics gd) {
        //super.paintComponent(gd);
        Graphics2D g = (Graphics2D) gd;

        if(logic.gameOver){
            g.setColor(Color.WHITE);

            //set bigger font
            g.setFont(g.getFont().deriveFont(Font.BOLD, 48f));

            //center text
            FontMetrics fm = g.getFontMetrics();
            String text = "Game Over";
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();

            g.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2);

            return;
        }

        //background
        g.setColor(backgroundC);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawInformation(g);

        //border
        g.setColor(Color.WHITE);
        g.drawRect(logic.padding, logic.padding, getWidth() - logic.padding * 2, getHeight() - logic.padding * 2 );

        g.setColor(appleC);
        for(Vector2D vec : logic.apples){
            g.fillRect(vec.x * logic.blockSize + logic.padding, vec.y * logic.blockSize + logic.padding, logic.blockSize, logic.blockSize);
        }
        drawSnake(g);

        repaint();
    }

    private void drawInformation(Graphics2D g) {
        //time
        g.setFont(g.getFont().deriveFont(Font.BOLD, 24f));
        g.setColor(Color.WHITE);
        int min = logic.time / 60;
        int sec = logic.time % 60;
        g.drawString(String.format("Zeit: %02d:%02d", min, sec), logic.padding, 20);

        g.drawString("Punkte: " + logic.points, logic.padding + logic.blockSize * logic.width - g.getFontMetrics().stringWidth("Punkte: "+ logic.points), 20);
    }

    void drawSnake(Graphics2D g){
        g.setColor(snakeC);
        for (int i = 0; i < logic.positions.size(); i++) {
            Vector2D vec = logic.positions.get(i);  //current Snake Segment
            int x = vec.x * logic.blockSize + logic.padding;    //get paint Cords
            int y = vec.y * logic.blockSize + logic.padding;

            //snake body
            g.setColor(snakeC);
            g.fillRect(x, y, logic.blockSize, logic.blockSize);

            if(logic.hadApple && i == logic.positions.size() - 2) continue;

            //border color
            g.setColor(Color.GRAY);

            //neighbors
            Vector2D prev = (i > 0) ? logic.positions.get(i - 1) : null;    //if it is the first element set to null
            Vector2D next = (i < logic.positions.size() - 1) ? logic.positions.get(i + 1) : null;   //if it is the last Element set to null

            if(logic.hadApple && i == logic.positions.size() - 1) prev = logic.positions.get(i - 2);
            // Top border
            if (prev == null || prev.y != vec.y - 1 || prev.x != vec.x) {
                if (next == null || next.y != vec.y - 1 || next.x != vec.x) {
                    g.drawLine(x, y, x + logic.blockSize, y);
                }
            }

            // Bottom border
            if (prev == null || prev.y != vec.y + 1 || prev.x != vec.x) {
                if (next == null || next.y != vec.y + 1 || next.x != vec.x) {
                    g.drawLine(x, y + logic.blockSize, x + logic.blockSize, y + logic.blockSize);
                }
            }

            // Left border
            if (prev == null || prev.x != vec.x - 1 || prev.y != vec.y) {
                if (next == null || next.x != vec.x - 1 || next.y != vec.y) {
                    g.drawLine(x, y, x, y + logic.blockSize);
                }
            }

            // Right border
            if (prev == null || prev.x != vec.x + 1 || prev.y != vec.y) {
                if (next == null || next.x != vec.x + 1 || next.y != vec.y) {
                    g.drawLine(x + logic.blockSize, y, x + logic.blockSize, y + logic.blockSize);
                }
            }

            if(i == 0) drawEyes(g, x, y);

        }
    }

    void drawEyes(Graphics2D g, int x, int y){
            int eyeSize = logic.blockSize / 3;
            int pupilSize = logic.blockSize / 6;

            int leftEyeX = x, leftEyeY = y;
            int rightEyeX = x, rightEyeY = y;

            switch (logic.direction) {
                case 0: // Up
                    leftEyeX = x + logic.blockSize / 4;
                    leftEyeY = y + logic.blockSize / 4;
                    rightEyeX = x + (logic.blockSize / 4) * 3 - eyeSize/2;
                    rightEyeY = y + logic.blockSize / 4;
                    break;
                case 1: // Down
                    leftEyeX = x + logic.blockSize / 4;
                    leftEyeY = y + (logic.blockSize / 2);
                    rightEyeX = x + (logic.blockSize / 4) * 3 - eyeSize/2;
                    rightEyeY = y + (logic.blockSize / 2);
                    break;
                case 2: // Left
                    leftEyeX = x + logic.blockSize / 4;
                    leftEyeY = y + logic.blockSize / 4;
                    rightEyeX = x + logic.blockSize / 4;
                    rightEyeY = y + (logic.blockSize / 4) * 3 - eyeSize/2;
                    break;
                case 3: //right
                    leftEyeX = x + (logic.blockSize / 2);
                    leftEyeY = y + logic.blockSize / 4;
                    rightEyeX = x + (logic.blockSize / 2);
                    rightEyeY = y + (logic.blockSize / 4) * 3 - eyeSize/2;
                    break;
            }

            //black eyes
            g.setColor(Color.BLACK);
            g.fillOval(leftEyeX, leftEyeY, eyeSize, eyeSize);
            g.fillOval(rightEyeX, rightEyeY, eyeSize, eyeSize);

            // white pupils
            g.setColor(Color.WHITE);
            int pupilOffset = eyeSize / 4; // smaller shift so it stays inside the black eye

            switch (logic.direction) {
                case 0: // Up
                    g.fillOval(leftEyeX + pupilOffset, leftEyeY, pupilSize, pupilSize);
                    g.fillOval(rightEyeX + pupilOffset, rightEyeY, pupilSize, pupilSize);
                    break;
                case 1: // Down
                    g.fillOval(leftEyeX + pupilOffset, leftEyeY + pupilOffset * 2, pupilSize, pupilSize);
                    g.fillOval(rightEyeX + pupilOffset, rightEyeY + pupilOffset * 2, pupilSize, pupilSize);
                    break;
                case 2: // Left
                    g.fillOval(leftEyeX, leftEyeY + pupilOffset, pupilSize, pupilSize);
                    g.fillOval(rightEyeX, rightEyeY + pupilOffset, pupilSize, pupilSize);
                    break;
                case 3: // Right
                    g.fillOval(leftEyeX + pupilOffset * 2, leftEyeY + pupilOffset, pupilSize, pupilSize);
                    g.fillOval(rightEyeX + pupilOffset * 2, rightEyeY + pupilOffset, pupilSize, pupilSize);
                    break;
            }

    }
}
