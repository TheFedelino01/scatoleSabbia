package scatolesabbia;

import java.util.Scanner;

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
            float x = sc.nextFloat();
            System.out.print("Inclinazione Y: ");
            float y = sc.nextFloat();
            
            System.out.println("\n\n-------------");
            for (int i=0; i<ptrDati.getNumScatole();i++) {
                Scatola s = ptrDati.getScatola(i);
                s.setInclinazioneX(x);
                s.setInclinazioneY(y);
                s.muovi();
                System.out.println("SCATOLA ID: "+i);
                System.out.println(s.getSabbiaPresente().toString());
            }
        }
    }
}
