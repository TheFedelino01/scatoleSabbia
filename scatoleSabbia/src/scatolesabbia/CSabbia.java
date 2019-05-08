package scatolesabbia;

import java.awt.Point;

import javafx.scene.shape.Box;

import javax.swing.text.Position;

import processing.core.PApplet;

/**
 * @author Saccani Federico
 * @version 1.0
 * @brief La classe viene utilizzata per la gestione della Sabbia presente all'interno di una scatola
 */
public class CSabbia {
    /**
     * @brief L'attributo rappresenta la direzione di inclinazione del piano
     * L'attributo puo' assumere il valore:
     * - "+1" quando l'inclinazione e' positiva e quindi la sabbia si muove da DX verso la sponda SX;
     * - "-1" quando l'inclinazione e' negativa e quindi la sabbia si muove da SX verso DX.
     * @author Saccani Federico
     * @version 1.0
     */
    private float movimentoX;

    /**
     * @brief L'attributo rappresenta l'altezza massima raggiunta dalla sabbia sulla sponda maggiore
     * L'attributo indica il valore raggiunto massimo (in cm) della sabbia sulla "sponda di appoggio" superiore.
     * @author Saccani Federico
     * @version 1.0
     */
    private float altezzaLatoMaggiore;

    /**
     * @brief L'attributo rappresenta l'altezza minima raggiunta dalla sabbia sulla sponda minore
     * L'attributo indica il valore raggiunto minimo (in cm) della sabbia sulla "sponda di appoggio" inferiore.
     * @author Saccani Federico
     * @version 1.0
     */
    private float altezzaLatoMinore;

    /**
     * @brief L'attributo rappresenta la quantita' di sabbia presente all'interno della scatola
     * @author Saccani Federico
     * @version 1.0
     */
    private float quantitaSabbia;//QUANTITA MAX 100 litri di sabbia

    /**
     * @brief L'attributo rappresenta la velocita' di spostamento della sabbia
     * @author Saccani Federico
     * @version 1.0
     */
    private float velocita;

    /**
     * @brief L'attributo rappresenta "la tavola da disegno" utilizzata dal main per la visualizzazione dell'interfaccia grafica
     * Viene utilizzato per utilizzare i metodi di disegno utilizzati da processing
     * @author Saccani Federico
     * @version 1.0
     */
    private PApplet processingSketch;

    /**
     * @brief Costruttore senza parametri della classe
     * Il Costruttore vuoto della classe permette di avviare la fase di setup ed impostare a valori
     * di default gli attributi della classe
     * @author Saccani Federico
     * @version 1.0
     */
    public CSabbia(PApplet disegno) {
        processingSketch = disegno;
        this.quantitaSabbia = 10;
        setup();
    }

    /**
     * @param disegno puntatore che identifica quale "tavola da disegno" dovra' essere utilizzata per la visualizzazione
     *                della parte grafica della sabbia
     * @brief Costruttore con parametri della classe
     * Il Costruttore con parametri della classe permette di avviare la fase di setup ed impostare a valori
     * di default gli attributi della classe, inoltre gli attributi
     * quantita e processingSketch assumeranno i valori passati come parametro
     * @paran quantita quantita' presente di sabbia
     * @author Saccani Federico
     * @version 1.0
     */
    public CSabbia(float quantita, PApplet disegno) {
        this.quantitaSabbia = quantita;
        processingSketch = disegno;
        setup();
    }

    /**
     * @brief Metodo che imposta gli attributi movimentoX e velocita a valori di default
     * Il metodo viene richiamato da ogni costruttore per inizializzare gli attributi
     * @author Saccani Federico
     * @version 1.0
     */
    private void setup() {
        movimentoX = 0;
        //movimentoY=0;
        velocita = 0;
    }

    /**
     * @brief Il metodo permette di aumentare il volume della sabbia presente
     * @paran aggiunta la quantita' di sabbia che dovra' essere aggiunta all'attributo quantitaSabbia della classe
     * @author Saccani Federico
     * @version 1.0
     */
    public void aggiungiSabbia(float aggiunta) {
        quantitaSabbia += aggiunta;
    }

    /**
     * @brief Il metodo permette di diminuire il volume della sabbia presente
     * @paran rimossa la quantita' di sabbia che dovra' essere rimossa all'attributo quantitaSabbia della classe
     * @author Saccani Federico
     * @version 1.0
     */
    public void rimuoviSabbia(float rimossa) {
        quantitaSabbia -= rimossa;
    }

    /**
     * @param dimensioni le dimensioni della scatola dove e' presente la sabbia
     * @brief Il metodo permette di calcolare i valori degli attributi MovimentoX, altezzaLatoMaggiore e altezzaLatoMinore
     * Il metodo richiama alcune funzioni che calcolano, in base ad alcuni parametri, i valori che gli attributi dovranno assumere
     * @paran inclinazioneX l'inclinazione della sabbia
     * @author Saccani Federico
     * @version 1.0
     */
    public void aggiornati(float inclinazioneX, DimensioniScatola dimensioni) {
        impostaMovimentoX(inclinazioneX);

        calcolaLivelloDiRaggiungimentoMaggiore(inclinazioneX, dimensioni);
    }

    //DEPRECATO
//    private void calcolaLivelloDiRaggiungimentoMaggiore(float inclinazioneX){
//        //CALCOLO IL LIVELLO MASSIMO DI RAGGIUNGIMENTO DELLA SABBIA SULLA SPONDA VERSO LA QUALE E' ORIENTATA (la sabbia)
//        float valoreVirgola = inclinazioneX/100; //10 gradi ---> 0,1
//        float percentualeSabbiaMovente = quantitaSabbia*valoreVirgola;//La sabbia che si muove in base all'inclinazione
//        
//        //livello massimo a cui la sabbia arriva con l'inclinazione
//        //maggiore sara' il livello, e maggiore sara' l'altezza registrata
//        altezzaLatoMaggiore = (quantitaSabbia+percentualeSabbiaMovente/2); //diviso 2 una parte sale e una scende \ oppure /
//    }

    /**
     * @param dimensioni le dimensioni della scatola dove e' presente la sabbia
     * @brief Il metodo permette di calcolare i valori che altezzaLatoMaggiore e altezzaLatoMinore assumeranno
     * Tenendo conto dell'inclinazione e delle dimensioni della scatola, il metodo applica alcune formule matematiche
     * (seni, coseni, somma di angoli, etc.) e permette il calcolo dell'altezza della sabbia sulle due sponde della scatola.
     * I calcoli, essendo difficili da commentare, sono dimostrati su carta (chiedere a Saccani per lucidazioni)
     * @paran inclinazioneX l'inclinazione della sabbia
     * @author Saccani Federico
     * @version 1.0
     */
    private void calcolaLivelloDiRaggiungimentoMaggiore(float inclinazioneX, DimensioniScatola dimensioni) {
        double altezzaScatola;
        if (inclinazioneX > 0)
            altezzaScatola = dimensioni.getAltezzaDx();
        else
            altezzaScatola = dimensioni.getAltezzaSx();

        inclinazioneX = Math.abs(inclinazioneX);//La rendo sempre positiva

        double lato = dimensioni.getProfondita();
        double VolumeIniziale = ((quantitaSabbia * lato) * lato);


        double angoloAdiacente = 180 - (90 + inclinazioneX);
        double risCosAngA = Math.cos(trasformaARadianti(angoloAdiacente)) * altezzaScatola;
        double risSinAngA = Math.sin(trasformaARadianti(angoloAdiacente)) * altezzaScatola;

        double angoloSuperioreSX = Math.abs((180 - (angoloAdiacente + 90)) - 90);
        double angoloSuperioreDX = 180 - (angoloSuperioreSX + 90);

        double lunghezzaDiagonale = lato / (Math.cos(trasformaARadianti(angoloSuperioreDX)));


        double latoTriangolo = (Math.sin(trasformaARadianti(angoloSuperioreDX)) * lunghezzaDiagonale);
        double volumeTriangolo = ((lato * latoTriangolo) / 2) * lato;


        double altezzaRett = -((volumeTriangolo - VolumeIniziale) / (lato * lato));
        double volumeRettangolo = (altezzaRett * lato) * lato;

        double volumeTrapezio = volumeRettangolo + volumeTriangolo;


        altezzaLatoMaggiore = (float) (altezzaRett + latoTriangolo);
        altezzaLatoMinore = (float)altezzaRett;
    }

    /**
     * @return double valore corrispondente in radianti in base ai gradi passati da parametro
     * @brief Il metodo permette di convertire i gradi passati da parametro a radianti
     * @paran gradi i gradi da convertire
     * @author Saccani Federico
     * @version 1.0
     */
    private double trasformaARadianti(double gradi) {
        //180:gradi=pi:rad
        return ((gradi * Math.PI) / 180);
    }

    /**
     * @brief Il metodo permette di impostare l'attributo movimentoX della classe
     * Il metodo imposta in base al parametro passato, l'attributo movimentoX della classe.
     * Quando il parametro e' positivo (sabbia che si muove da DX a SX), il movimentoX viene impostato a +1;
     * se negativo (sabbia che si muove da SX a DX), movimentoX viene impostato a -1
     * @paran inclinazioneX l'inclinazione della sabbia
     * @author Saccani Federico
     * @version 1.0
     */
    private void impostaMovimentoX(float inclinazioneX) {
        //IMPOSTO IL VERSO DI MOVIMENTO/ORIENTAMENTO DELLA SABBIA (VERSO DX O VERSO SX)

        //Se l'inclinazione e' positiva, la sabbia si muove verso SX quindi il livello maggiore
        //registrato sara' sulla sponda SX della scatola
        if (inclinazioneX > 0) {
            movimentoX = 1;
        } else {
            //Se l'inclinazione e' negativa, la sabbia si muove verso DX quindi il livello maggiore
            //registrato sara' sulla sponda DX della scatola
            movimentoX = -1;
        }
    }

    /**
     * @return boolean True: esce la sabbia  | False: la sabbia non ha un inclinazione tale per uscire
     * @brief Il metodo dice se la sabbia sta uscendo dalla scatola
     * Il metodo controlla se l'altezza della sabbia sulla sua sponda maggiore (altezzaLatoMaggiore),
     * e' superiore rispetto all'altezza da superare; in tal caso la sabbia esce altrimenti la sabbia
     * non ha un inclinazione tale da permettere la sua fuoriuscita.
     * @paran altezzaDaSuperare l'altezza dopo la quale la sabbia esce
     * @author Saccani Federico
     * @version 1.0
     */
    private boolean staUscendo(int altezzaDaSuperare) {
        if (altezzaLatoMaggiore > altezzaDaSuperare) {
            //Significa che la sabbia sta' uscendo
            return true;
        } else {
            return false;//La sabbia ha un altezza inferiore e quindi non esce dalla scatola
        }

    }

    /**
     * @return Directions Rappresenta la direzione di uscita. Se e' uguale a NONE, significa che non sta uscendo.
     * @brief Il metodo ritorna la direzione di uscita della sabbia
     * Il metodo dice in che direzione (Destra,Dinistra,Sotto,Sopra,NONE) la sabbia sta uscendo.
     * @paran altezzaDaSuperare l'altezza dopo la quale la sabbia esce
     * @author Saccani Federico
     * @version 1.0
     */
    public Directions direzioneDiUscitaSabbia(int altezzaDaSuperare) {
        Directions direzione = Directions.NONE;//Parto dal presupposto che la sabbia non sta uscendo

        //Controllo se effettivamente sta uscendo la sabbia
        if (staUscendo(altezzaDaSuperare)) {

            //Sta uscendo, dico da dove esce
            if (movimentoX == 1) {
                direzione = Directions.SINISTRA;
            } else {
                //Movimento = -1
                direzione = Directions.DESTRA;
            }

        } else {
            //Non sta uscendo
            direzione = Directions.NONE;
        }

        return direzione;
    }

    /**
     * @brief Il metodo permette di disegnare la sabbia sulla tavola da disegno
     * Utilizzando l'attributo processingSketch, il metodo disegna in corrispondenza della scatola la sabbia presente.
     * @paran Scatola la scatola di appartenenza
     * @author Saccani Federico
     * @version 1.0
     */
    public void visualizza(Scatola scatola) {
        //TODO 
        processingSketch.fill(processingSketch.color(0, 255, 0));
        DimensioniScatola dim = scatola.getDimensioni();
        Point pos = scatola.getPosizioneCentrale();
        Float altezzaScatola = (float)dim.getAltezzaSotto();
        Float largezzaScatola = (float)dim.getLarghezza();
        //processingSketch.rect(pos.x,pos.y+altezzaScatola, altezzaScatola,-quantitaSabbia);

        float x2 = pos.x+largezzaScatola;
        float y1,y2;

        if(movimentoX==1){
            y1 = (pos.y+altezzaScatola)-altezzaLatoMaggiore;
            y2 = (pos.y+altezzaScatola)-altezzaLatoMinore;
        }else{
            y1 = (pos.y+altezzaScatola)-altezzaLatoMinore;
            y2 = (pos.y+altezzaScatola)-altezzaLatoMaggiore;
        }

        float x = largezzaScatola;

        if(altezzaLatoMinore<0 && movimentoX==1){
            // ---> \
            processingSketch.quad(pos.x,y1, (pos.x+largezzaScatola)+altezzaLatoMinore, pos.y+altezzaScatola, (pos.x+largezzaScatola)+altezzaLatoMinore, pos.y+altezzaScatola,pos.x, pos.y+altezzaScatola);
        }else if(altezzaLatoMinore<0 && movimentoX==-1){
            // ---> /
           processingSketch.quad(pos.x-altezzaLatoMinore,pos.y+altezzaScatola, x2, y2, x2, pos.y+altezzaScatola,pos.x-altezzaLatoMinore,pos.y+altezzaScatola);
        }

        if(altezzaLatoMinore>0){
             processingSketch.quad(pos.x,y1, x2, y2, x2, pos.y+altezzaScatola,pos.x, pos.y+altezzaScatola);
        }
        //processingSketch.quad(pos.x,y1, x2, y2, pos.x, pos.y+altezzaScatola, x2, pos.y+altezzaScatola);


    }

    //GET e SET DELLA CLASSE

    /**
     * @return movimentoX valore dell'attributo movimentoX della classe
     * @brief Il metodo permette di ottenere il valore dell'attributo movimentoX
     * @author Saccani Federico
     * @version 1.0
     */
    public float getMovimentoX() {
        return movimentoX;
    }

    /**
     * @return quantitaSabbia valore dell'attributo quantitaSabbia della classe
     * @brief Il metodo permette di ottenere il valore dell'attributo quantitaSabbia
     * @author Saccani Federico
     * @version 1.0
     */
    public float getQuantita() {
        return quantitaSabbia;
    }

    /**
     * @return velocita valore dell'attributo velocita della classe
     * @brief Il metodo permette di ottenere il valore dell'attributo velocita
     * @author Saccani Federico
     * @version 1.0
     */
    public float getVelocita() {
        return velocita;
    }

    /**
     * @return altezzaLatoMaggiore valore dell'attributo altezzaLatoMaggiore della classe
     * @brief Il metodo permette di ottenere il valore dell'attributo altezzaLatoMaggiore
     * @author Saccani Federico
     * @version 1.0
     */
    public float getAltezzaLatoMaggiore() {
        return altezzaLatoMaggiore;
    }

    /**
     * @return altezzaLatoMinore valore dell'attributo altezzaLatoMinore della classe
     * @brief Il metodo permette di ottenere il valore dell'attributo altezzaLatoMinore
     * @author Saccani Federico
     * @version 1.0
     */
    public float getAltezzaLatoMinore() {
        return altezzaLatoMinore;
    }

    /**
     * @return string stringa contenenti alcune informazioni importanti della classe
     * @brief Il metodo permette di ottenere il toString della classe
     * @author Saccani Federico
     * @version 1.0
     */
    public String toString() {
        return "Lato maggiore: " + altezzaLatoMaggiore + "\nLato minore: " + altezzaLatoMinore + "\nQuantita' sabbia: " + altezzaLatoMinore + "\nMovimentoX: " + movimentoX + "\n---------";
    }
}
