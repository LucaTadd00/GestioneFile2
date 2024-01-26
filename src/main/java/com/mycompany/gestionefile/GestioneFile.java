package com.mycompany.gestionefile;

import java.util.Scanner;
import java.util.ArrayList;

public class GestioneFile {
  public static void main(String[] args) {
        
        Scanner s = new Scanner(System.in); 
        
        Lettore lettore = new Lettore("user.json");
        //lettore.start();

        Scrittore scrittore = new Scrittore("output.csv");
        Thread threadScrittore = new Thread(scrittore);
        
        System.out.println("inserisci la chiave di cifratura: ");
        String key = s.nextLine();
        
        Matrice matrix = new Matrice(key);
        
        ArrayList<Vigenere> quadranti = new ArrayList<>();
        Vigenere quad_1 = new Vigenere(0, 13, 0, 13, matrix);
        quadranti.add(quad_1);
        Vigenere quad_2 = new Vigenere(0, 13, 13, 26, matrix);
        quadranti.add(quad_2);
        Vigenere quad_3 = new Vigenere(13, 26, 0, 13, matrix);
        quadranti.add(quad_3);
        Vigenere quad_4 = new Vigenere(13, 26, 13, 26, matrix);
        quadranti.add(quad_4);
        
        for (Vigenere v : quadranti) {
          Thread t = new Thread(v);
          t.start();
          try {
              t.join();
          } catch (InterruptedException ex) {
              System.err.println("Errore metodo join");
          }
        }
                 
        //threadScrittore.start();
        
        System.out.println("inserisci l'username : ");
        String userName = s.nextLine();
        System.out.println("inserisci la password : ");
        String password = s.nextLine();
        
        String criptedUser = matrix.cifra(userName);
        String criptedPass = matrix.cifra(password);
            
        scrittore.scrivi(criptedUser);
        scrittore.scrivi(criptedPass);
        
        System.out.println("username criptato: " + criptedUser);
        System.out.println("password criptata: " + criptedPass);
         
        
    }
    
}