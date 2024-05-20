package swiat.zwierzeta;

import swiat.Zwierze;
import other.Point;

import java.awt.*;

public class Wilk extends Zwierze {

    public Wilk(Point polozenie) {
        super(polozenie, 9, 5);
    }

    @Override
    public String toString() {
        return "WILK";
    }

    @Override
    public Color rysowanie() {
        return new Color(105,105,105);
    }

    @Override
    public Wilk kopia() {
        return new Wilk(polozenie);
    }
}

