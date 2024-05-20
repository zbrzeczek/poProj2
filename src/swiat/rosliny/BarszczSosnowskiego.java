package swiat.rosliny;

import other.Point;
import swiat.Roslina;
import swiat.Organizm;
import swiat.Zwierze;

import java.awt.*;

public class BarszczSosnowskiego extends Roslina {
    public BarszczSosnowskiego(Point polozenie) {
        super(polozenie, 0);
    }

    @Override
    public void dodajModyfikator(Organizm org){
        org.zabij();
    }

    @Override
    public void akcja(){
        for(int y = -1; y <= 1; y++){
            for(int x = -1; x <= 1; x++){
                Organizm org = swiat.getOrganizmNaPozycji(polozenie.dodaj(new Point(y,x)));
                if(org instanceof Zwierze){
                    org.zabij();
                }
            }
        }
        super.akcja();
    }

    @Override
    public String toString() {
        return "BARSZCZ_SOSNOWSKIEGO";
    }

    @Override
    public Color rysowanie() {
        return new Color(155,185,232);
    }

    @Override
    public BarszczSosnowskiego kopia() {
        return new BarszczSosnowskiego(polozenie);
    }
}
