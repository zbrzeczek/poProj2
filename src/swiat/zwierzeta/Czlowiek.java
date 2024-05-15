package swiat.zwierzeta;

import swiat.Organizm;
import swiat.Zwierze;
import other.Point;

import java.awt.*;
import static java.lang.Math.random;

public class Czlowiek extends Zwierze {

    private int turySpecjalne = 0;
    public static final int SILA = 5;
    public static final int INICJATYWA = 4;
    public static final int SPECJALNY_TURY = 5;
    public static final int SPECJALNY_MNIEJ = 2;
    public static final double P_MNIEJ = 0.5;


    public Czlowiek(Point polozenie) {
        super(polozenie, SILA, INICJATYWA);
    }

    @Override
    public void akcja(){

        int zasieg;

        if(turySpecjalne == 0){
            zasieg = 1;
        }
        else if(turySpecjalne > SPECJALNY_MNIEJ){
            zasieg = Antylopa.ZASIEG;
            turySpecjalne--;
        }
        else {
            if(random() < P_MNIEJ){
                zasieg = Antylopa.ZASIEG;
            }
            else {
                zasieg = 1;
            }
            turySpecjalne--;
        }

        switch(swiat.popRuch()){

            case GORA:
                zmienPolozenie(new Point(zasieg * -1, 0));
                break;

            case DOL:
                zmienPolozenie(new Point(zasieg, 0));
                break;

            case PRAWO:
                zmienPolozenie(new Point(0, zasieg));
                break;

            case LEWO:
                zmienPolozenie(new Point(0, -1 * zasieg));
                break;

            case SPECJALNY:
                if(turySpecjalne == 0){
                    turySpecjalne = SPECJALNY_TURY;
                }
                break;
        }
    }

    public void setTurySpecjalne(int turySpecjalne){
        this.turySpecjalne = turySpecjalne;
    }


    @Override
    public String toString() {
        return "CZLOWIEK";
    }

    @Override
    public Color rysowanie() {
        return new Color(255,253,150);
    }

    public int getTurySpecjalne() {
        return turySpecjalne;
    }

    @Override
    public Organizm kopia() {
        return new Czlowiek(polozenie);
    }
}
