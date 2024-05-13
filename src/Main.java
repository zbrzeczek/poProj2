import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Main {
    private static final int HEX = 1;
    private static final int NORM = 2;
    private static int result = 0;

    public static int oknoPoczatek() {
        JFrame f = new JFrame("Wybor trybu");
        JLabel label = new JLabel("Wybierz tryb gry");
        label.setBounds(150, 50, 150, 20);
        f.add(label);

        JButton a = new JButton("Hex");
        a.setBounds(100, 100, 100, 40);
        JButton b = new JButton("Zwyczajny");
        b.setBounds(200, 100, 100, 40);

        // Adding ActionListener to the "Hex" button
        a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform actions when "Hex" button is pressed
                result = HEX;
            }
        });

        // Adding ActionListener to the "Zwyczajny" button
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform actions when "Zwyczajny" button is pressed
                result = NORM;
            }
        });

        f.add(a);
        f.add(b);

        f.setSize(400, 300);
        f.setLayout(null);
        f.setVisible(true);

        return result;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        int tryb = oknoPoczatek();

        if (tryb == 1) {
            System.out.printf("HEX");
        }
        else if (tryb == 2) {
            System.out.printf("ZWYB");
        }
        else{
            System.out.printf("false");
        }
    }
}