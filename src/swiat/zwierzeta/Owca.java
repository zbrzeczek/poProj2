package swiat.zwierzeta;

import swiat.Organizm;
import swiat.Zwierze;
import other.Point;

import java.awt.*;

public class Owca extends Zwierze {

    public static final int SILA = 4;
    public static final int INICJATYWA = 4;

    public Owca(Point polozenie) {
        super(polozenie, SILA, INICJATYWA);
    }

    @Override
    public String toString() {
        return "OWCA";
    }

    @Override
    public Color rysowanie() {
        return Color.GRAY;
    }

    @Override
    public Organizm kopia() {
        return new Owca(polozenie);
    }
}

