package swiat.rosliny;

import other.Point;
import swiat.Roslina;
import swiat.Organizm;

import java.awt.*;
public class Trawa extends Roslina {

    public Trawa(Point polozenie) {
        super(polozenie, 0);
    }

    @Override
    public String toString(){
        return "TRAWA";
    }

    @Override
    public Color rysowanie() {
        return new Color(50,240,50);
    }

    @Override
    public Organizm kopia() {
        return new Trawa(polozenie);
    }

}
