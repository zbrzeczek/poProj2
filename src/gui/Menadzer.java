package gui;

import other.Point;
import other.Tekst;
import swiat.Swiat;
import swiat.Organizm;
import swiat.rosliny.*;
import swiat.zwierzeta.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menadzer extends JPanel implements MouseListener, KeyListener{
    private static final Color KOLOR_TLA = new Color(0,0,0);
    private static final Color KOLOR_INFO = new Color(255,200,200);

    private JPopupMenu nowyOrganizmMenu;

    private Swiat swiat;
    private int height;
    private int width;

    private Point nowePolozenie;

    private final int wysokoscOkienka;

    private int rozmiarZwierzecia;

    public Menadzer(int wysokoscOkienka, Swiat swiat) {
        this.height = swiat.getHeight();
        this.width = swiat.getWidth();
        this.wysokoscOkienka = wysokoscOkienka;
        this.swiat = swiat;

        this.nowePolozenie = new Point(0,0);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        popupMenu();
    }

    private void popupMenu(){

        nowyOrganizmMenu = new JPopupMenu();

        Point p0 = new Point(0,0);

        Organizm[] organizmy = {
                new Wilk(p0),
                new Owca(p0),
                new Lis(p0),
                new Zolw(p0),
                new Antylopa(p0),
                new Trawa(p0),
                new Mlecz(p0),
                new Guarana(p0),
                new WilczeJagody(p0),
                new BarszczSosnowskiego(p0)
        };


        for(Organizm el : organizmy){

            JMenuItem elMenu = new JMenuItem(el.toString());

            elMenu.setBackground(el.rysowanie());

            elMenu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    polozOrganizm(el);
                }

            });
            nowyOrganizmMenu.add(elMenu);
        }
    }


    public Tekst getDziennik(){
        return swiat.getDziennik();
    }

    public void setSwiat(Swiat swiat) {
        this.swiat = swiat;
        this.height = swiat.getHeight();
        this.width = swiat.getWidth();

        paint(this.getGraphics());
    }

    public void nastepnaTura(){
        swiat.wykonajTure();
        paint(this.getGraphics());
        System.out.print(swiat.getDziennik().wypisz());

        requestFocus();
    }

    public boolean maCzlowieka(){
        for(Organizm org : swiat.getOrganizmy()){
            if(org instanceof Czlowiek) return true;
        }
        return false;
    }


    @Override
    public void paint(Graphics g){
        g.setColor(KOLOR_TLA);

        rozmiarZwierzecia = wysokoscOkienka/height;
        g.fillRect(0,0,width * rozmiarZwierzecia,height * rozmiarZwierzecia);

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                Organizm org = swiat.getOrganizmNaPozycji(new Point(y,x));

                if(org != null){

                    g.setColor(org.rysowanie());

                    if(swiat.getTyp() == Swiat.Typ.Kartezjanski){
                        g.fillRect(x* rozmiarZwierzecia,y* rozmiarZwierzecia, rozmiarZwierzecia, rozmiarZwierzecia);
                    }
                    else {
                        int[] xPoints = new int[6];
                        int[] yPoints = new int[6];

                        double xtemp = x;

                        if(y %2 == 0){
                            xtemp = x + 0.5;
                        }

                        for (int i = 0; i < 6; i++) {
                            int xval = (int) (xtemp * rozmiarZwierzecia + rozmiarZwierzecia/2
                                    * Math.sin(i * 2 * Math.PI / 6D));
                            int yval = (int) (y * rozmiarZwierzecia + rozmiarZwierzecia/2
                                    * Math.cos(i * 2 * Math.PI / 6D));

                            xPoints[i] = xval;
                            yPoints[i] = yval;
                        }

                        g.fillPolygon(xPoints, yPoints, yPoints.length);
                    }

                }

            }

        }

        if(maCzlowieka()){
            czlowiekInfo(g);
        }
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();

        nowePolozenie = new Point(y/rozmiarZwierzecia,x/rozmiarZwierzecia);

        nowyOrganizmMenu.show(this,x,y);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        System.out.println("ruch");

        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_UP:
                swiat.setRuch(Swiat.Ruch.GORA);
                break;

            case KeyEvent.VK_DOWN:
                swiat.setRuch(Swiat.Ruch.DOL);
                break;

            case KeyEvent.VK_LEFT:
                swiat.setRuch(Swiat.Ruch.LEWO);
                break;

            case KeyEvent.VK_RIGHT:
                swiat.setRuch(Swiat.Ruch.PRAWO);
                break;

            case KeyEvent.VK_Z:
                swiat.setRuch(Swiat.Ruch.SPECJALNY);
                break;

        }
        paint(getGraphics());
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }


    public Swiat getSwiat() {
        return swiat;
    }


    private void czlowiekInfo(Graphics g){
        g.setColor(KOLOR_INFO);
        String komunikat = "Ruch czlowieka: ";

        switch(swiat.getRuch()){
            case GORA:
                komunikat+="do gory";
                break;
            case DOL:
                komunikat+= "na dol";
                break;
            case STOJ:
                komunikat+="bedzie stal";
                break;
            case LEWO:
                komunikat+="w lewo";
                break;
            case PRAWO:
                komunikat+="w prawo";
                break;
            case SPECJALNY:
                komunikat+="uruchomi umiejetnosc specjalna";
                break;

        }
        g.drawString(komunikat,0,10);
    }


    private void polozOrganizm(Organizm org){

        Organizm kolidujacy = swiat.getOrganizmNaPozycji(nowePolozenie);

        while(kolidujacy != null){
            kolidujacy.zabij();
            kolidujacy = swiat.getOrganizmNaPozycji(nowePolozenie);
        }

        org.setPolozenie(nowePolozenie);
        swiat.addOrganizm(org.kopia());

        paint(getGraphics());
    }
}
