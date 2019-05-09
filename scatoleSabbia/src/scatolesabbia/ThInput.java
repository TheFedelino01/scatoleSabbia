package scatolesabbia;

import java.util.Scanner;
import java.util.Vector;

public class ThInput extends Thread {
    private DatiCondivisi ptrDati;
    private final Scanner sc = new Scanner(System.in);

    public ThInput(DatiCondivisi ptrDati) {
        this.ptrDati = ptrDati;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.print("Inclinazione X: ");
            int x = sc.nextInt();
            System.out.print("Inclinazione Y: ");
            int y = sc.nextInt();

            System.out.println("\n\n-------------");

            ptrDati.setInclinazioneTavoloDiGiocoX(x);
            ptrDati.setInclinazioneTavoloDiGiocoY(y);

            for (Vector<Scatola> v : ptrDati.getScatole())
                for (Scatola s : v) {
                    s.muovi(x, y);
                    System.out.println(s.getSabbiaPresente().toString());
                }

        }
    }
}
