package swiat.zwierzeta;

import swiat.Organizm;
import swiat.Zwierze;
import other.Point;

import java.awt.*;
import static java.lang.Math.random;

public class Czlowiek extends Zwierze {

    private int turySpecjalne = 0;
    public static final int SPECJALNY_TURY = 5;

    public Czlowiek(Point polozenie) {
        super(polozenie, 5, 4);
    }

    @Override
    public void akcja(){
        if(turySpecjalne == 0){
            setSila(5);
        }
        else {
            setSila(getSila()-1);
            turySpecjalne--;
        }

        switch(swiat.popRuch()){
            case GORA:
                zmienPolozenie(new Point( -1, 0));
                break;

            case DOL:
                zmienPolozenie(new Point(1, 0));
                break;

            case PRAWO:
                zmienPolozenie(new Point(0, 1));
                break;

            case LEWO:
                zmienPolozenie(new Point(0, -1));
                break;

            case SPECJALNY:
                if(turySpecjalne == 0){
                    setSila(10);
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
        return new Color(0,0,0);
    }

    public int getTurySpecjalne() {
        return turySpecjalne;
    }

    @Override
    public Organizm kopia() {
        return new Czlowiek(polozenie);
    }
}
