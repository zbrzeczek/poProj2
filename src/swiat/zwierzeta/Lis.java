package swiat.zwierzeta;

import swiat.Organizm;
import swiat.Zwierze;
import other.Point;

import java.awt.*;
public class Lis extends Zwierze {

    public static final int SILA = 3;
    public static final int INICJATYWA = 7;

    public Lis(Point polozenie) {
        super(polozenie, SILA, INICJATYWA);
    }

    @Override
    public String toString() {
        return "LIS";
    }

    @Override
    public Color rysowanie() {
        return Color.ORANGE;
    }

    @Override
    public Organizm kopia() {
        return new Lis(polozenie);
    }

    @Override
    protected boolean czyMaDobryWech(){
        return true;
    }
}
