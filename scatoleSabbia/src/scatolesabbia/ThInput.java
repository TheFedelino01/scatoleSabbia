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
            for (Scatola s : ptrDati.getScatole()) {
                s.setInclinazioneX(x);
                s.setInclinazioneY(y);
            }
        }
    }
}
