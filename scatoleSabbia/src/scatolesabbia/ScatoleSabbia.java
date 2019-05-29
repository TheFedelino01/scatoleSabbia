package scatolesabbia;

import SwingGui.InputManagerGUI;
import processing.core.PApplet;

import java.util.List;

/**
 * @author Saccani Federico
 * @version 1.0
 * @brief Il main del programma
 */
public class ScatoleSabbia extends PApplet {

     /**
     * @brief L'attributo rappresenta i dati condivisi
     * @author Saccani Federico
     * @version 1.0
     */
    private static DatiCondivisi dati;
    
     /**
     * @brief L'attributo contiene tutti i thread della scatole presenti nel gioco
     * @author Saccani Federico
     * @version 1.0
     */
    private static ThScatola[] thScatole;
    
    /**
     * @brief L'attributo contiene tutti i thread della palline presenti nel gioco
     * @author Saccani Federico
     * @version 1.0
     */
    private static ThPallina[] thPalline;
    
    /**
     * @brief L'attributo indica il numero di scatole che dovranno essere create
     * @author Saccani Federico
     * @version 1.0
     */
    private static int numScatole = 5;

    
    /**
     * @brief Costruttore della classe
     * Il Costruttore permette di inizializzare i vari attributi della classe andando quindi
     * a creare i thread scatola e pallina necessari
     * @author Saccani Federico
     * @version 1.0
     */
    public ScatoleSabbia() {
        //Inizializzo i dati condivisi e il numero di th

        dati = new DatiCondivisi(numScatole, this, 100, 100, 500, 1000);
        thScatole = new ThScatola[numScatole];
        thPalline = new ThPallina[numScatole];

        //Assegno al vettore con i th i thread delle scatole
        List<Scatola> scatole = dati.ottieniListaConScatole();
        
        for (int i = 0; i < scatole.size(); i++) {
            thScatole[i] = new ThScatola(dati, scatole.get(i));
            
            Scatola scatolaTmp = scatole.get(i);
            JPallina p = new JPallina(this,scatolaTmp,i);
            p.mostraPallina();
            
            scatolaTmp.addPallina(p);
            
            
            thPalline[i] = new ThPallina(dati, p);
        }
    }

        
    /**
     * @brief Main
     * Collega il PApplet al main di seguito fa partire tutti i thread e visualizza
     * l'interfaccia per la gestione delle inclinazioni
     * @author Saccani Federico
     * @version 1.0
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{"scatolesabbia.ScatoleSabbia"});//Imposto questo come main principale

        faiPartireITh();

        //new SwingGui.InputAccelerometro(dati).setVisible(true);
        new InputManagerGUI(dati).setVisible(true);
        
     
    }

        
    /**
     * @brief Setting Processing
     * Imposta le dimensioni della finestra di processing
     * @author Saccani Federico
     * @version 1.0
     */
    public void settings() {
        size(dati.getDimensioneSchermoX(), dati.getDimensioneSchermoY());
    }

    /**
     * @brief Fa partire i thread
     * Il metodo esegue .start() su tutti i th pallina e scatola presenti nei 2 attributi vettori
     * @author Saccani Federico
     * @version 1.0
     */
    private static void faiPartireITh() {
        //Faccio partire i th
        for (int i = 0; i < numScatole; i++) {
            thScatole[i].start();
            thPalline[i].start();
        }
       
    }

    /**
     * @brief Setup Processing
     * Imposta il frame rate e elimina tutti i bordi dalle figure che si disegneranno
     * @author Saccani Federico
     * @version 1.0
     */
    public void setup() {
        noStroke();
        frameRate(100);
        ellipseMode(RADIUS);
    }

    /**
     * @brief Draw Processing
     * Per ogni scatola presente richiama il metodo per disegnarsi
     * @author Saccani Federico
     * @version 1.0
     */
    public void draw() {
        background(255, 255, 255);
        List<Scatola> scatole = dati.ottieniListaConScatole();
        
        for (int i = 0; i<scatole.size();i++){
                scatole.get(i).draw();
        }
    }
}

