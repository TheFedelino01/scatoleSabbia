/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

import java.awt.Color;
import java.awt.Point;

import processing.core.PApplet;

/**
 * @author Samuele Peduzzi
 */

public final class JPallina {

    /**
     * @brief L'attributo indica la posizione iniziale della pallina all'interno della scatola
     * @author Peduzzi Samuele
     * @version 1.0
     */
    private Point posScatola; //angolo sx scatola


    /**
     * @brief L'attributo rappresenta il canvas da utilizzare per il rendering della pallina
     * @author Peduzzi Samuele
     * @version 1.0
     */

    private PApplet processingSketch;

    /**
     * @brief L'attributo rappresenta la velocità di spostamento della pallina
     * @author Peduzzi Samuele
     * @version 1.0
     */

    private float velocitaSpostamento; //valore tra 0 e 1

    /**
     * @brief L'attributo indica la posizione della pallina all'interno della scatola
     * @author Peduzzi Samuele
     * @version 1.0
     */
    private Point Posizione; //Indicante le coordinate all'interno della scatola dell'oggetto

    /**
     * @brief L'attributo indica se la pallina è presente all'interno della scatola o viceversa
     * @author Peduzzi Samuele
     * @version 1.0
     */
    private boolean Presente; //Indicante la presenza della pallina o viceversa


    /**
     * @brief L'attributo indica le dimensioni della pallina
     * @author Peduzzi Samuele
     * @version 1.0
     */

    private float Dimensioni; //Indicante la lunghezza del diametro della pallina

    /**
     * @brief L'attributo indica il colore con il quale verrà renderizzata la pallina
     * @author Peduzzi Samuele
     * @version 1.0
     */

    private Color Colore; //Colore della pallina 

//    /**
//     * @brief Gli attributi indicano la posizione della pallina all'interno della matrice di scatola
//     * @author Peduzzi Samuele
//     * @version 1.0
//     */
//
//    private int r,c; //Posizione scatola nella quale è presente la pallina

    /**
     * @brief scatola in cui è contenuta
     */
    private Scatola scatola;

    /**
     * id univoco della pallina
     */
    private final int id;

    /**
     * @param velocitaSpostamento Velocità iniziale pallina
     * @param Posizione           Indicante le coordinate iniziali all'interno della scatola
     * @param Presente            Indicante se la pallinaè presente oppure no
     * @param Dimensioni          Indicante le dimensioni della pallina
     * @param Colore              Indicante il colore della pallina
     * @param scatola
     * @param id
     * @brief Costruttore
     * @author Peduzzi Samuele
     * @version 1.0
     */


    public JPallina(float velocitaSpostamento, Point Posizione, boolean Presente, float Dimensioni, Color Colore, Scatola scatola, int id) {
        this.velocitaSpostamento = velocitaSpostamento;
        this.Posizione = Posizione; //La posizione andrà impostata al centro della scatola
        this.Presente = Presente;
        this.Dimensioni = Dimensioni;
        this.Colore = Colore;
        this.scatola = scatola;
        this.id = id;
    }


    /**
     * @brief Costruttore vuoto
     * @author Peduzzi Samuele
     * @version 1.0
     */


    public JPallina(PApplet processingSketch, Point posScatola, Scatola scatola, int id) {

        this.processingSketch = processingSketch;
        this.posScatola = posScatola;
        this.scatola = scatola;
        this.id = id;
        reset();
    }


    /**
     * @brief permette di reimpostare gli attributi ai valori di default
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public void reset() {

        velocitaSpostamento = 0f;
        Presente = false;
        Dimensioni = 10.0f;
        //Posizione = new Point(); //La posizione andrà impostata al centro della scatola
        Posizione= new Point((int)Dimensioni,(int)Dimensioni); //faccio in modo che sia posizionata nell'angolo in alto a sinistra, ma non sovrapposta ai bordi

        Colore = new Color(0, 0, 255);

    }

    public void visualizza() {
        //TO DO
    }

    /**
     * @brief Permette di rimuovere la pallina dalla scatola di appartenenza.
     * All'effettivo l'oggetto non viene deistanziato, semplicemente non viene più visualizzato.
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public void rimuoviPallina() { //La pallina viene rimossa

        Presente = false;
    }

    /**
     * @brief Permette di aggiungere la pallina alla scatola di appartenenza.
     * All'effettivo l'oggetto non viene istanziato, semplicemente inizia ad essere visualizzato.
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public void mostraPallina() { //Viene aggiunta la pallina

        Presente = true;
    }

    /**
     * @param nuovaPos Point indicante la posizione specifica che andrà ad essere assegnata alla pallina
     * @brief Sposta la pallina in una posizione specifica all'interno della scatola
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public void sposta(Point nuovaPos) {
        Posizione = nuovaPos;
    }

    /**
     * @param inclinazioneScatolaX Inclinazione sull'asse delle x della scatola
     * @brief Sposta la pallina in base all'inclinazione sull'asse delle x della scatola
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public void spostaX(float inclinazioneScatolaX) {

        velocitaSpostamento = Math.abs(inclinazioneScatolaX); //La velocità è direttamente proporzionale all'inclinazione della scatola

        if (inclinazioneScatolaX > 0) //Se la scatola è inclinata verso destra la scatola si spostaX verso destra
            Posizione.x -= (0.5 * velocitaSpostamento);
        else
            Posizione.x += (0.5 * velocitaSpostamento);
    }

    /**
     * @param inclinazioneScatolaY Inclinazione sull'asse delle y della scatola
     * @brief Sposta la pallina in base all'inclinazione sull'asse delle y della scatola
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public void spostaY(float inclinazioneScatolaY) {

        velocitaSpostamento = Math.abs(inclinazioneScatolaY); //La velocità è direttamente proporzionale all'inclinazione della scatola

        if (inclinazioneScatolaY > 0) //Se la scatola è inclinata verso destra la scatola si spostaX verso destra
            Posizione.y += (0.5 * velocitaSpostamento);
        else
            Posizione.y -= (0.5 * velocitaSpostamento);
    }


    /**
     * @brief Renderizza la pallina mediante il core processing.
     * Viene richiamato ripetutamente (in base al framerate) dal Thread pallina
     * @author Peduzzi Samuele
     * @version 1.0
     */

    public void draw() {

        if (Presente) {
            processingSketch.fill(Colore.getRGB());
            processingSketch.ellipse(Posizione.x + posScatola.x, Posizione.y + posScatola.y, Dimensioni, Dimensioni);

            System.out.println(Posizione.toString());
        }


    }

    public boolean isPresente() {
        return Presente;
    }

    public Point getPosizione() {
        return Posizione;
    }

    public float getDimensioni() {
        return Dimensioni;
    }

    public Color getColore() {
        return Colore;
    }

    public void setColore(Color Colore) {
        this.Colore = Colore;
    }
    
    

    public int getId() {
        return id;
    }


    public Scatola getScatola() {
        return scatola;
    }

    public void setScatola(Scatola scatola) {
        this.scatola = scatola;
        posScatola = scatola.getPosizione();
    }
}
