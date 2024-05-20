package swiat.zwierzeta;

import swiat.Organizm;
import swiat.Zwierze;
import other.Point;

import java.awt.*;

public class Owca extends Zwierze {
    public Owca(Point polozenie) {
        super(polozenie, 4, 4);
    }

    @Override
    public String toString() {
        return "OWCA";
    }

    @Override
    public Color rysowanie() {
        return new Color(230,210,105);
    }

    @Override
    public Organizm kopia() {
        return new Owca(polozenie);
    }
}

