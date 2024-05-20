package swiat.zwierzeta;

import swiat.Organizm;
import swiat.Zwierze;
import other.Point;

import java.awt.*;
public class Lis extends Zwierze {
    public Lis(Point polozenie) {
        super(polozenie, 3, 7);
    }

    @Override
    public String toString() {
        return "LIS";
    }

    @Override
    public Color rysowanie() {
        return new Color(209,140,55);
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
