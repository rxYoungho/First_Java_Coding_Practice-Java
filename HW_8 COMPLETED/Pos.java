 

public class Pos implements Comparable<Pos> {
    public int x, y, d;
    
    public Pos(int x, int y)        { this(x, y, 0); }
    public Pos(int x, int y, int d) { this.x = x; this.y = y; this.d = d; }
    
    public boolean equals(Pos pos)  { return x == pos.x && y == pos.y; }
    public int compareTo(Pos pos)   { return d - pos.d; }
}
