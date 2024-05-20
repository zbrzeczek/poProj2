package swiat.zwierzeta;

import java.awt.*;
import swiat.Organizm;
import swiat.Zwierze;
import other.Point;

import static java.lang.Math.random;

public class Zolw extends Zwierze {
    public static final double P_RUCHU = 0.25;
    public static final double OBRONA = 5;


    public Zolw(Point polozenie) {
        super(polozenie, 2, 1);
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
        return new Color(50,150,77);
    }

    @Override
    public Zolw kopia() {
        return new Zolw(polozenie);
    }
}
