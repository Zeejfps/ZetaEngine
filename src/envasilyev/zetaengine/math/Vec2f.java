package envasilyev.zetaengine.math;

/**
 * Created by Zeejfps on 6/15/2016.
 */
public class Vec2f {

    public float x, y;

    public Vec2f() {
        this(0,0);
    }

    public Vec2f(Vec2f v) {
        this(v.x, v.y);
    }

    public Vec2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vec2f vec) {
        x = vec.x;
        y = vec.y;
    }

    public void add(float amount) {
        this.x += amount;
        this.y += amount;
    }

    public void add(Vec2f vec) {
        this.x += vec.x;
        this.y += vec.y;
    }

    @Override
    public String toString() {
        return "Vec2f [" + x + ", " + y + "]";
    }

    public boolean equals(Vec2f other) {
        return other.x == x && other.y == y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vec2f)
            return equals((Vec2f)obj);
        else
            return false;
    }

    public static Vec2f negative(Vec2f vec) {
        return new Vec2f(-vec.x, -vec.y);
    }

}
