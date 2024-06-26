package swiat.rosliny;

import other.Point;
import swiat.Roslina;
import swiat.Organizm;

import java.awt.*;
public class WilczeJagody extends Roslina {

    public WilczeJagody(Point polozenie) {
        super(polozenie, 0);
    }

    @Override
    public void dodajModyfikator(Organizm org){
        org.zabij();
    }

    @Override
    public String toString() {
        return "WILCZE_JAGODY";
    }

    @Override
    public Color rysowanie() {
        return new Color(60, 25, 115);
    }

    @Override
    public WilczeJagody kopia() {
        return new WilczeJagody(polozenie);
    }
}
