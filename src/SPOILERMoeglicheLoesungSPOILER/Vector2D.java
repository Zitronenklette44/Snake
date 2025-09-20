package SPOILERMoeglicheLoesungSPOILER;

import java.util.UUID;

public class Vector2D {
    public int x, y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D vec){
        this.x += vec.x;
        this.y += vec.y;
    }

    public void add(int x, int y){
        this.x += x;
        this.y += y;
    }

    public float dist(Vector2D vec){
        return (float) Math.sqrt(vec.x * vec.x + vec.y * vec.y);
    }

    public void set(Vector2D vector2D) {
        x = vector2D.x;
        y = vector2D.y;
    }

    public Vector2D cpy(){
        return new Vector2D(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                // same object
        if (obj == null || getClass() != obj.getClass()) return false;

        Vector2D test = (Vector2D) obj;
        return test.x == x && test.y == y;
        //return test.x <= x + 0.1 && test.x >= x-0.1 && test.y <= y + 0.1 && test.y >= y - 0.1;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "->(" + x + " | " + y + ")";
    }
}
