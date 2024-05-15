package other;

public class Point {
    private int x, y;

    public Point(int y, int x){
        this.y = y;
        this.x = x;
    }

    //gettery
    public int getY(){
        return y;
    }
    public int getX(){
        return x;
    }

    public boolean equals(Point other){
        return y == other.y && x == other.x;
    }

    public Point dodaj(Point other){
        return new Point(other.y + y, other.x + x);
    }

    public void dodajEq(Point other){
        y += other.y;
        x += other.x;
    }

    public boolean pozaGranicami(int wysokosc, int szerokosc) {
        return y < 0 || x < 0 || y >= wysokosc || x >= szerokosc;
    }
}
