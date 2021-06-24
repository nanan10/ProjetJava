package com.company;

import java.io.*;
import java.util.HashMap;
import java.io.FileNotFoundException;

public class stockage {
    stockage(){

    }
    String fichier = "Heritage.dat";
        void stocker(Personne p)  {

            HashMap <Integer,Personne> personneHashMap = deserialiser();

            personneHashMap.put(p.identificateur,p);

            try{FileOutputStream outputStream = new FileOutputStream(fichier);
                    ObjectOutputStream objectStream = new ObjectOutputStream(outputStream); {
                objectStream.writeObject(personneHashMap);
            }
            }catch (FileNotFoundException exc){
                System.out.println(exc);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    public Integer TailleTableau(){
        HashMap <Integer,Personne> personneHashMap = deserialiser();
        return personneHashMap.size();

    }


    HashMap <Integer,Personne> deserialiser() {

        try {
            InputStream outputStream = new FileInputStream(fichier);
            ObjectInputStream objectStream = new ObjectInputStream(outputStream);
            {
                HashMap<Integer, Personne> personneHashMap = (HashMap<Integer, Personne>) objectStream.readObject();
                System.out.println(personneHashMap.toString());

                return personneHashMap;

            }
        } catch (FileNotFoundException exc) {
            System.out.println(exc);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    int serialiser(HashMap <Integer,Personne> personneHashMap){

        String fichier = "Heritage.dat";

        try{FileOutputStream outputStream = new FileOutputStream(fichier);
            ObjectOutputStream objectStream = new ObjectOutputStream(outputStream);{
            objectStream.writeObject(personneHashMap);

            return 1;
        }}catch (FileNotFoundException exc){
            System.out.println(exc);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    void intialiser(){
        String fichier = "Heritage.dat";
        HashMap <Integer,Personne> personneHashMap = new HashMap<>();
        serialiser(personneHashMap);

    }



}
