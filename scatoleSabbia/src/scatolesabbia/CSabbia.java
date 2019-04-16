/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scatolesabbia;

/**
 *
 * @author feder
 */
public class CSabbia {
    private float movimentoX;
    //private float movimentoY;
    private float quantita;//QUANTITA MAX 100
    private float velocita;
    
    public CSabbia(){
        this.quantita=0;
        setup();
    }
    
    public CSabbia(float quantita){
        this.quantita=quantita;
        setup();
    }
    
    private void setup(){
        movimentoX=0;
        //movimentoY=0;
        velocita=0;
    }
    
    public void aggiungiSabbia(float aggiunta){
        quantita+=aggiunta;
    }
    
    public void rimuoviSabbia(float rimossa){
        quantita-=rimossa;
    }
    
    public void aggiornati(float inclinazioneX){
        //http://spartanita.altervista.org/download/manuali/livello.pdf
        
    }
    
    public boolean staUscendo(int altezzaDaSuperare){
        return false;
    }
    
    public int direzioneDiUscitaSabbia(){
        return 0;
    }
    
    //GET e SET DELLA CLASSE
    public float getMovimentoX() {
        return movimentoX;
    }
    public float getMovimentoY() {
        return movimentoY;
    }
    public float getQuantita() {
        return quantita;
    }
    public float getVelocita() {
        return velocita;
    }
}
