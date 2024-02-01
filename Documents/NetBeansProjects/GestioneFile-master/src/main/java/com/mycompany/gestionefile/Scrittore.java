package com.mycompany.gestionefile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;


public class Scrittore implements Runnable{

    String nomeFile;
    int check;
    int id;
    
    public Scrittore(String nomeFile){
        this.nomeFile = nomeFile;
        this.check = 0;
        this.id = 1;
        
    }
    
    @Override
    public void run() {
        //scrivi();
    }

    public void scrivi(String user, String pass){
try (BufferedWriter br = new BufferedWriter(new FileWriter(nomeFile, true))) {
            if (check == 0) {
                
              try (BufferedWriter bc = new BufferedWriter(new FileWriter(nomeFile))) {
              bc.write("");
              bc.flush();
              } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
            } 
         }

            br.write(user);
            br.write(";");
            br.write(pass);
            br.write("\n\r");

            br.flush();
            check++;
        } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        }
    
     public void scriviCSV(String[] elementi) {
        /* utilizzando la classe DataOutputStream */
        try (DataOutputStream scrittore = new DataOutputStream(new FileOutputStream(nomeFile, true))){
            
             if (id == 0) {
               try (DataOutputStream ds = new DataOutputStream(new FileOutputStream(nomeFile))) {
                ds.writeUTF("");
                ds.flush();
               } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
             
            scrittore.writeInt(id);
            for (String elemento : elementi) {
                scrittore.writeUTF(elemento);
                scrittore.writeUTF(";");
            }
            scrittore.writeUTF("\n"); // Modificato per la nuova riga
            id = id + 1;
        } catch (IOException ex) {
            Logger.getLogger(Scrittore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
