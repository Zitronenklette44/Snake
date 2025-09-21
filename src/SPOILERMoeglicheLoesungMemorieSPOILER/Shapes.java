package SPOILERMoeglicheLoesungMemorieSPOILER;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class Shapes {

    public static void drawShape(int id, Color color, Graphics2D g, Logic logic, Point point){
        switch (id){
            case 0:     //empty circle
                drawCircle(false, color, g, logic, point);
                break;
            case 1:     //empty rectangle
                drawRectangle(false, color, g, logic, point);
                break;
            case 2:     //empty trangle
                drawTriangle(false, color, g, logic, point);
                break;
            case 3:     //empty star
                drawStar(false, color, g, logic, point);
                break;
            case 4:     //filled circle
                drawCircle(true, color, g, logic, point);
                break;
            case 5:     //filled rectangle
                drawRectangle(true, color, g, logic, point);
                break;
            case 6:     //filled trangle
                drawTriangle(true, color, g, logic, point);
                break;
            case 7:     //filled star
                drawStar(true, color, g, logic, point);
                break;

        }
    }

    private static void drawStar(boolean filled, Color color, Graphics2D g, Logic logic, Point point) {
        double w = logic.symbolWidth;
        double h = logic.symbolHeight;
        double cx = point.x + w / 2.0;
        double cy = point.y + h / 2.0;
        double rOuter = Math.min(w, h) / 2.0;
        double rInner = rOuter / 2.5;

        Path2D star = new Path2D.Double();
        for (int i = 0; i < 10; i++) {
            double angle = Math.toRadians(i * 36);
            double r = (i % 2 == 0) ? rOuter : rInner;
            double px = cx + Math.cos(angle - Math.PI / 2) * r;
            double py = cy + Math.sin(angle - Math.PI / 2) * r;
            if (i == 0) star.moveTo(px, py);
            else star.lineTo(px, py);
        }
        star.closePath();

        g.setColor(color);
        if (filled) g.fill(star);
        else g.draw(star);
    }

    private static void drawTriangle(boolean filled, Color color, Graphics2D g, Logic logic, Point point) {
        double w = logic.symbolWidth;
        double h = logic.symbolHeight;

        Path2D triangle = new Path2D.Double();
        triangle.moveTo(point.x + w / 2.0, point.y);     // top
        triangle.lineTo(point.x + w, point.y + h);       // bottom right
        triangle.lineTo(point.x, point.y + h);           // bottom left
        triangle.closePath();

        g.setColor(color);
        if (filled) g.fill(triangle);
        else g.draw(triangle);
    }

    private static void drawRectangle(boolean filled, Color color, Graphics2D g, Logic logic, Point point) {
        double w = logic.symbolWidth;
        double h = logic.symbolHeight;

        Rectangle2D rect = new Rectangle2D.Double(point.x, point.y, w, h);

        g.setColor(color);
        if (filled) g.fill(rect);
        else g.draw(rect);
    }

    private static void drawCircle(boolean filled, Color color, Graphics2D g, Logic logic, Point point) {
        double w = logic.symbolWidth;
        double h = logic.symbolHeight;

        Ellipse2D circle = new Ellipse2D.Double(point.x, point.y, w, h);

        g.setColor(color);
        if (filled) g.fill(circle);
        else g.draw(circle);
    }

    public static void drawQuestionmark(Color color, Graphics2D g, Logic logic, Point point) {
        double w = logic.symbolWidth;
        double h = logic.symbolHeight;

        g.setColor(color);
        g.setStroke(new BasicStroke(3f));

        // upper curve
        Arc2D arc = new Arc2D.Double(point.x, point.y, w, h * 0.8, 0, 180, Arc2D.OPEN);
        g.draw(arc);

        // vertical stem
        g.drawLine((int)(point.x + w / 2), (int)(point.y + h * 0.4),
                (int)(point.x + w / 2), (int)(point.y + h * 0.65));

        // dot
        Ellipse2D dot = new Ellipse2D.Double(
                point.x + w / 2.0 - w * 0.05,
                point.y + h * 0.8,
                w * 0.1,
                w * 0.1
        );
        g.fill(dot);
    }

}
