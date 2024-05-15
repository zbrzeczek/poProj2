package swiat.rosliny;

import other.Point;
import swiat.Roslina;
import swiat.Organizm;

import java.awt.*;
public class Trawa extends Roslina {

    public static final int SILA = 0;

    public Trawa(Point polozenie) {
        super(polozenie, SILA);
    }

    @Override
    public String toString(){

        return "TRAWA";

    }

    @Override
    public Color rysowanie() {

        return new Color(72,111,56);

    }

    @Override
    public Organizm kopia() {
        return new Trawa(polozenie);
    }

}
