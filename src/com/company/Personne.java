package com.company;

import java.io.*;
import java.util.Scanner;

public class Personne {
    Integer identificateur;
    String nom;
    String prenoms;
    String sexe;
    String date_de_naissance;
    String parent;
    Personne(Integer id,String noms,String prenom,String sexes,String date_de_naissances,String parents){
        identificateur = id;
        nom =noms;
        prenoms= prenom;
        sexe = sexes;
        date_de_naissance = date_de_naissances;
        parent = parents;

    }
    void Ajouter(){
        File file = new File("C://Users/nanan/IdeaProjects/ProjetJava/Personne/"+nom+".txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
           try {
               FileWriter writer = new FileWriter(file);
               BufferedWriter bw = new BufferedWriter(writer);
               bw.write(identificateur);
               bw.write(nom+"\n");
               bw.write(prenoms+"\n");
               bw.write(sexe+"\n");
               bw.write(date_de_naissance+"\n");
               bw.write(parent+"\n");
               bw.close();
               writer.close();
           } catch (IOException e){
               e.printStackTrace();
           }
        }
    }

}

