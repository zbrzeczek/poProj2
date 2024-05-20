package swiat.rosliny;

import other.Point;
import swiat.Roslina;
import swiat.Organizm;

import java.awt.*;
public class Guarana extends Roslina {
    public static final int ZWIEKSZENIE_SILY = 3;

    public Guarana(Point polozenie) {
        super(polozenie, 0);
    }

    @Override
    public void dodajModyfikator(Organizm organizm){
        organizm.setSila(organizm.getSila() + ZWIEKSZENIE_SILY);
    }

    @Override
    public String toString() {
        return "GUARANA";
    }

    @Override
    public Color rysowanie() {
        return Color.magenta;
    }

    @Override
    public Guarana kopia() {
        return new Guarana(polozenie);
    }
}

