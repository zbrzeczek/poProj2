package swiat;

import other.Point;
import other.Tekst;
import swiat.rosliny.*;
import swiat.zwierzeta.*;

import java.util.Vector;

public class Swiat {

    private final int height;
    private final int width;

    private int nrTury;
    private Ruch ruch = Ruch.STOJ;
    private Typ typ = Typ.Kartezjanski;

    private Tekst dziennik;

    Vector<Organizm> organizmy;
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Organizm getKolidujacy(Organizm org) {
        for(Organizm organizm : organizmy){
            if(org.getPolozenie().equals(organizm.getPolozenie()) && org != organizm && org.isZywy()){
                return organizm;
            }
        }
        return null;
    }

    public Point getWolnePoleObok(Point p) {
        for(int dy = -1; dy <= 1; dy++){
            for(int dx = -1; dx <= 1; dx++){
                Point sprawdzanyPunkt = new Point(p.getY() + dy, p.getX() + dx);

                if(typ == Typ.Hex && ((dy == -1 && dx == -1) || (dy == 1 && dx == -1))){
                    continue;
                }

                if(!sprawdzanyPunkt.equals(p)
                        && getOrganizmNaPozycji(sprawdzanyPunkt) == null
                        && !sprawdzanyPunkt.pozaGranicami(height,width)){
                    return sprawdzanyPunkt;
                }
            }
        }
        return p;
    }

    public Tekst getDziennik() {
        return dziennik;
    }

    public int getNrTury() {
        return nrTury;
    }

    public void setNrTury(int nrTury) {
        this.nrTury = nrTury;
    }

    public Typ getTyp() {
        return typ;
    }

    public enum Ruch {
        GORA,
        DOL,
        LEWO,
        PRAWO,
        SPECJALNY,
        STOJ

    }

    public enum Typ {
        Kartezjanski,
        Hex

    }


    public Swiat(int height, int width, Typ typ){
        this.height = height;
        this.width = width;
        organizmy = new Vector<>();
        dziennik = new Tekst();

        this.typ = typ;
    }




    public void wykonajTure(){
        oglosNowaTure();
        nrTury++;
        ruchOrganizmow();
        pozbadzSieZwlok();
    }

    public void addOrganizm(Organizm organizm){
        organizm.setWiek(organizm.getWiek()+1);
        organizm.setSwiat(this);

        organizmy.add(organizm);
    }

    public Organizm getOrganizmNaPozycji(Point p){
        Organizm szukany = null;

        for(Organizm org : organizmy){
            if(org.getPolozenie().equals(p) && org.isZywy()){
                if(szukany == null || szukany.getSila() < org.getSila()){
                    szukany = org;
                }
            }
        }
        return szukany;
    }

    public void setRuch(Ruch ruch){
        this.ruch = ruch;
    }

    public Ruch popRuch(){
        Ruch obecny = ruch;
        ruch = Ruch.STOJ;

        return obecny;
    }

    public Ruch getRuch(){
        return ruch;
    }

    public Vector<Organizm> getOrganizmy() {
        return organizmy;
    }

    public static Swiat Bazowy(Typ typ){
        Swiat swiat = new Swiat(
                30,
                30, typ);

        swiat.addOrganizm(new Wilk(new Point(0,3)));
        swiat.addOrganizm(new Wilk(new Point(0,4)));

        swiat.addOrganizm(new Owca(new Point(6,6)));
        swiat.addOrganizm(new Owca(new Point(7,6)));
        swiat.addOrganizm(new Mlecz(new Point(10,10)));
        swiat.addOrganizm(new Lis(new Point(10,5)));
        swiat.addOrganizm(new Lis(new Point(10,6)));
        swiat.addOrganizm(new Lis(new Point(10,7)));
        swiat.addOrganizm(new Zolw(new Point(20,20)));
        swiat.addOrganizm(new Trawa(new Point(20,4)));
        swiat.addOrganizm(new Antylopa(new Point(30,5)));
        swiat.addOrganizm(new Antylopa(new Point(30,6)));
        swiat.addOrganizm(new Guarana(new Point(25,5)));
        swiat.addOrganizm(new Guarana(new Point(25,6)));

        swiat.addOrganizm(new WilczeJagody(new Point(0,0)));
        swiat.addOrganizm(new WilczeJagody(new Point(1,0)));

        swiat.addOrganizm(new BarszczSosnowskiego(new Point(21,24)));
        swiat.addOrganizm(new Czlowiek(new Point(25,25)));

        return swiat;
    }

    private void ruchOrganizmow(){
        organizmy.sort((Organizm o1, Organizm o2) -> {

            if(o1.getInicjatywa() == o2.getInicjatywa()){
                return o2.getWiek() - o1.getWiek();
            }

            return o2.getInicjatywa() - o1.getInicjatywa() ;
        });

        for(int i = 0; i < organizmy.size(); i++){
            Organizm organizm = organizmy.get(i);

            if(organizm.isZywy() ){
                organizm.akcja();
                organizm.kolizja();
            }
            organizm.starzejSie();
        }
    }

    private void pozbadzSieZwlok(){
        for(int i = 0; i < organizmy.size(); i++){
            if(!organizmy.get(i).isZywy()){
                organizmy.remove(i);
                pozbadzSieZwlok();
                break;
            }
        }
    }

    private void oglosNowaTure(){
        dziennik.czysc();

        for(Organizm organizm : organizmy){
            organizm.nowaTura();
        }
    }
}
