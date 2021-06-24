package com.company;

import java.io.*;
import java.util.HashMap;

public class stockage {
    stockage(){

    }
    String fichier = "Heritage.dat";
        void stocker(Personne p)  {

            HashMap <Integer,Personne> personneHashMap = deserialiser();

            personneHashMap.put(p.identificateur,p);

            try(FileOutputStream outputStream = new FileOutputStream(fichier);
                    ObjectOutputStream objectStream = new ObjectOutputStream(outputStream)) {
                objectStream.writeObject(personneHashMap);
            }catch (Exception e){
                System.out.println(e);

            }
    }


    public Integer TailleTableau(){
        HashMap <Integer,Personne> personneHashMap = deserialiser();
        return personneHashMap.size();

    }


    HashMap <Integer,Personne> deserialiser(){

        try(
                InputStream outputStream = new FileInputStream(fichier);
                ObjectInputStream objectStream = new ObjectInputStream(outputStream);) {
            HashMap <Integer,Personne> personneHashMap = (HashMap<Integer, Personne>) objectStream.readObject();
            System.out.println(personneHashMap.toString());

            return personneHashMap;

        }catch (Exception e){
            System.out.println(e);
            return null;

        }

    }

    int serialiser(HashMap <Integer,Personne> personneHashMap){

        String fichier = "Heritage.dat";

        try(FileOutputStream outputStream = new FileOutputStream(fichier);
            ObjectOutputStream objectStream = new ObjectOutputStream(outputStream)) {
            objectStream.writeObject(personneHashMap);

            return 1;
        }catch (Exception e){
            System.out.println(e);
            return 0;
        }
    }

    void intialiser(){
        String fichier = "Heritage.dat";
        HashMap <Integer,Personne> personneHashMap = new HashMap<>();
        serialiser(personneHashMap);

    }





}
