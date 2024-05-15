package swiat.zwierzeta;

import java.awt.*;
import swiat.Organizm;
import swiat.Zwierze;
import other.Point;

import static java.lang.Math.random;

public class Zolw extends Zwierze {

    public static final int SILA = 2;
    public static final int INICJATYWA = 1;
    public static final double P_RUCHU = 0.25;
    public static final double OBRONA = 5;


    public Zolw(Point polozenie) {
        super(polozenie, SILA, INICJATYWA);
    }

    @Override
    public void akcja(){

        if(random() < P_RUCHU){

            losowyRuch(1);

        }

    }

    @Override
    public boolean czyOdbilAtak(Organizm drugi){

        return drugi.getSila() < OBRONA;

    }


    @Override
    public String toString() {
        return "ZOLW";
    }

    @Override
    public Color rysowanie() {
        return new Color(67,140,126);
    }

    @Override
    public Zolw kopia() {
        return new Zolw(polozenie);
    }
}
