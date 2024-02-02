
package com.mycompany.gestionefile;

import java.util.ArrayList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

public class User implements Serializable {
    
    
    private int id;
    private String name;
    private String surname;
    private String role;

    //COSTRUTTORE PARAMETRICO CLASSICO
  
    public User(int id, String name, String surname, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.role = role;
    } 
    
    //COSTRUTTORE CHE UTILIZZA LA CLASSE OBJECTINPUTSTREAM PER RICREARE UN OGGETTO IMPORTANDOLO DA UN FILE
    
     public User(String nomeFile){
       try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(nomeFile))){
            User user = (User) in.readObject();
            this.id = user.id;
            this.name = user.name;
            this.surname = user.surname;
            this.role = user.role;
       } catch (FileNotFoundException ex) {
            System.out.println("errore nella serializzazione! ");
       } catch (IOException | ClassNotFoundException ex) {
            System.out.println("errore nella serializzazione! ");
       }
    }
     
     //METODO CHE UTILIZZA LA CLASSE OBJECTOUTPUTSTREAM PER CREARE UN OGGETTO ESPORTANDOLO POI IN UN FILE
     
     public void esportaUser(String nomeFile){
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(nomeFile))) {
            writer.writeObject(this);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.err.println("Errore in scrittura!");
        }
    }
     
     //VARI METODI GET PER STAMPARE I VALORI NEL MAIN
    
public int getId() {
    return id;
}

public String getName() {
    return name;
}

public String getSurname() {
    return surname;
}

public String getRole() {
    return role;
}
}
