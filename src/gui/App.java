package gui;

import other.MenadzerPlikow;
import swiat.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
public class App extends JFrame{
    public static final String TYTUL = "Symulacja swiata";
    public static final int HEIGHT = 600;
    public static final int WIDTH = 600;

    private final Menadzer menadzer;
    private final MenadzerPlikow menedzerPlikow;

    private JButton turaButton;
    private JButton dziennikButton;
    private JMenuItem menuItemBazowy;

    private JMenuItem menuItemBazowyHex;

    private JMenuItem menuItemWczytaj;
    private JMenuItem menuItemZapisz;


    public App(int wysokosc, int szerokosc){
        setSize(szerokosc,wysokosc);
        setMinimumSize(new Dimension(szerokosc,wysokosc));

        revalidate();

        setTitle(TYTUL);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menadzer = new Menadzer(HEIGHT * 8/10, Swiat.Bazowy(Swiat.Typ.Hex));
        menedzerPlikow = new MenadzerPlikow();

        menuGorne();
        inicjujPanelGlowny();
    }


    public void start(){
        setVisible(true);
    }

    private void menuGorne(){
        JMenuBar menuBar = new JMenuBar();

        JMenu menuNowy = new JMenu("Nowa Gra");
        JMenu menuPlik = new JMenu("Plik");

        guzikiMenuGornego();

        menuNowy.add(menuItemBazowy);
        menuNowy.add(menuItemBazowyHex);
        menuPlik.add(menuItemWczytaj);
        menuPlik.add(menuItemZapisz);

        menuBar.add(menuNowy);
        menuBar.add(menuPlik);

        setJMenuBar(menuBar);
    }

    private void guzikiMenuGornego(){

        menuItemBazowy = new JMenuItem("pola kwadratowe");
        menuItemBazowyHex = new JMenuItem("pola hex");
        menuItemWczytaj = new JMenuItem("wczytaj");
        menuItemZapisz = new JMenuItem("zapisz");

        menuItemBazowy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menadzer.setSwiat(Swiat.Bazowy(Swiat.Typ.Kartezjanski));
            }
        });


        menuItemBazowyHex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menadzer.setSwiat(Swiat.Bazowy(Swiat.Typ.Hex));
            }
        });

        menuItemWczytaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("wybierz plik do wczytania");

                int rv = fc.showOpenDialog(null);

                if(rv == JFileChooser.APPROVE_OPTION){
                    File plik = fc.getSelectedFile();
                    Swiat sw = menedzerPlikow.wczytaj(plik);
                    if(sw != null){
                        menadzer.setSwiat(sw);
                    }
                }
            }
        });

        menuItemZapisz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                fc.setDialogTitle("wybierz plik do zapisu");

                int rv = fc.showOpenDialog(null);

                if(rv == JFileChooser.APPROVE_OPTION){
                    File plik = fc.getSelectedFile();
                    menedzerPlikow.zapisz(menadzer.getSwiat(), plik);
                }
            }
        });
    }


    private void inicjujPanelGlowny(){
        inicjujGuziki();

        JPanel panelGuziki = new JPanel();

        GridLayout layout = new GridLayout(0,2);
        panelGuziki.setLayout(layout);

        panelGuziki.add(turaButton);
        panelGuziki.add(dziennikButton);

        JSplitPane splitPane = new JSplitPane();

        splitPane.setEnabled(false);
        splitPane.setDividerLocation( HEIGHT * 8 / 10);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);

        splitPane.addMouseListener(menadzer);

        splitPane.setTopComponent(menadzer);
        splitPane.setBottomComponent(panelGuziki);

        add(splitPane);
    }

    private void inicjujGuziki(){
        turaButton = new JButton("nastepna tura");
        dziennikButton = new JButton("dziennik");

        turaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                menadzer.nastepnaTura();
            }
        });

        dziennikButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showConfirmDialog(null, menadzer.getDziennik().wypisz(),"Dziennik", JOptionPane.DEFAULT_OPTION);
            }
        });
    }

}
