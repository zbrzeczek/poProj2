package swiat.zwierzeta;

import swiat.Zwierze;
import other.Point;

import java.awt.*;
import static java.lang.Math.random;

public class Antylopa extends Zwierze {
    public static final int ZASIEG = 2;
    public static final double P_UCIECZKI = 0.5;

    public Antylopa(Point polozenie) {
        super(polozenie, 4, 4);
    }

    @Override
    public void akcja(){
        losowyRuch(ZASIEG);
    }

    @Override
    public String toString() {
        return "ANTYLOPA";
    }

    @Override
    public Color rysowanie() {
        return new Color(70,50,27);
    }

    @Override
    public Antylopa kopia() {
        return new Antylopa(polozenie);
    }

    @Override
    public boolean czyUciekl(){
        return random() < P_UCIECZKI;
    }
}
