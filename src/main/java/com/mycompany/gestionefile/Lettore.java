package com.mycompany.gestionefile;

import java.io.FileReader;
import java.io.IOException;


public class Lettore extends Thread{
    String nomeFile;
    
    public Lettore(String nomeFile){
        this.nomeFile = nomeFile;
    }
    
    /**
     * Legge il file senza tener conto del tipo di file
     * e lo mostra in output
     */
    public String leggi() {
        FileReader fr;
        int i;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            //1) apro il file
            fr = new FileReader(nomeFile);
           
            while ((i = fr.read()) != -1) {
                stringBuilder.append((char) i);
            }

            //3) chiudo il file
            fr.close();
        } catch (IOException ex) {
            System.err.println("Errore in lettura!");
        }

        // restituisco la stringa risultante
        return stringBuilder.toString();
    }
    
    

    public void run(){
        leggi();
    }
}