package com.mycompany.gestionefile;

import java.io.FileReader;
import java.io.IOException;


public class Lettore extends Thread{
    String nomeFile;
    
    public Lettore(String nomeFile){
        this.nomeFile = nomeFile;
    }
    
    public String leggi() {
           StringBuilder stringBuilder = new StringBuilder();

        try (FileReader fr = new FileReader(nomeFile)) {
            int i;

            while ((i = fr.read()) != -1) {
                stringBuilder.append((char) i);
            }
        } catch (IOException ex) {
            System.err.println("Errore in lettura!");
        }

        // Restituisco la stringa risultante
        return stringBuilder.toString();
    }
    
    

    public void run(){
        leggi();
    }
}