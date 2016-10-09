package envasilyev.zetaengine.math;

/**
 * Created by Zeejfps on 6/16/2016.
 */
public class Vec2i {

    public int x, y;

    public Vec2i() {
        this(0, 0);
    }

    public Vec2i(Vec2i v) {
        this(v.x, v.y);
    }

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vec2i vec) {
        x = vec.x;
        y = vec.y;
    }

    public void add(int amount) {
        this.x += amount;
        this.y += amount;
    }

    public void add(Vec2i vec) {
        this.x += vec.x;
        this.y += vec.y;
    }

    @Override
    public String toString() {
        return "Vec2i [" + x + ", " + y + "]";
    }

    public boolean equals(Vec2i other) {
        return other.x == x && other.y == y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vec2i)
            return equals((Vec2i)obj);
        else
            return false;
    }

    public static Vec2i negative(Vec2i vec) {
        return new Vec2i(-vec.x, -vec.y);
    }

}
