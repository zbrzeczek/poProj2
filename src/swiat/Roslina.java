package swiat;

import other.Point;

import static java.lang.Math.random;

public abstract class Roslina extends Organizm{

    static final int DOMYSLNA_INICJATYWA = 0;
    static final double P_ROZSIANIA = 0.05;

    public Roslina(Point polozenie, int sila) {
        super(polozenie, sila, DOMYSLNA_INICJATYWA);
    }

    @Override
    public void akcja() {
        rozsiej();
    }

    @Override
    public void kolizja() {
    }

    @Override
    public void nowaTura() {
    }

    protected void rozsiej(){
        if(random() < P_ROZSIANIA){
            Point pNowy = swiat.getWolnePoleObok(polozenie);

            if(pNowy == polozenie) return;

            Organizm org = kopia();
            org.setWiek(0);

            org.setPolozenie(pNowy);

            swiat.addOrganizm(org);
        }
    }
}

