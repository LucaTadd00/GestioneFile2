package com.mycompany.gestionefile;

import java.io.FileReader;
import java.io.IOException;
import java.io.*;


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
    
public String leggiCSV() {
    StringBuilder content = new StringBuilder();

    try (DataInputStream lettore = new DataInputStream(new FileInputStream(nomeFile))) {
        String line;

        while ((line = lettore.readUTF()) != null) {
            content.append(line);
            if(line == "\n") {
            content.append("\n");}
        }

    } catch (EOFException ignored) {
        System.err.println("Lettura effettuata!");
    } catch (IOException ex) {
        System.err.println(ex.getMessage());
        System.err.println("Errore in lettura!");
    } finally {
        System.out.println(content.toString());
    }

    return content.toString();
}

    
    

    public void run(){
        leggi();
    }
}