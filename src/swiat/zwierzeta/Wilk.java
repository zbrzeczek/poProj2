package swiat.zwierzeta;

import swiat.Organizm;
import swiat.Zwierze;
import other.Point;

import java.awt.*;

public class Wilk extends Zwierze {

    public static final int SILA = 9;
    public static final int INICJATYWA = 5;



    public Wilk(Point polozenie) {
        super(polozenie, SILA, INICJATYWA);
    }

    @Override
    public String toString() {
        return "WILK";
    }

    @Override
    public Color rysowanie() {
        return new Color(244,0,0);
    }

    @Override
    public Wilk kopia() {
        return new Wilk(polozenie);
    }
}

