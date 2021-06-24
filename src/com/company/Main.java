package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.*;
import java.util.Scanner;
import java.io.FileNotFoundException;

import static com.company.Personne.CYAN;

public class Main {

    static void listePersonnes(){
        stockage s = new stockage();
        System.out.println("  Voici la liste des personnes déjà enrégistrés ");
        HashMap <Integer,Personne> personneHashMap = s.deserialiser();
        clearConsole();
        for (int h : personneHashMap.keySet()){
            personneHashMap.get(h).afficheLien2();
        }
    }
    public final static void clearConsole(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}

    }


    static void CreerLien(){
        clearConsole();
     stockage s = new stockage();
        System.out.println("Créons des liens ");
        HashMap <Integer,Personne> personneHashMap = s.deserialiser();
        clearConsole();

       for (int h : personneHashMap.keySet()){
            personneHashMap.get(h).afficheLien();
       }

        Scanner sc = new Scanner(System.in);
       System.out.println("");
       System.out.println("================================================================");
       System.out.println("1 : Créer lien Parent - Enfant        2 : Annuler");
       int choix = sc.nextInt();
       System.out.println(choix);

        switch (choix){
            case 1:

                try {
                    Integer id1, id2;
                    Scanner sc1 = new Scanner(System.in);
                    System.out.println("Entrez l'identifiant  du fils  : ");
                    id1 = sc1.nextInt();
                    System.out.println("Entrez l'identifiant du père : ");
                    id2 = sc1.nextInt();
                    Personne personne1 = personneHashMap.get(id1);
                    Personne personne2 = personneHashMap.get(id2);


                    if (personne2.VerificationID(personne2.enfants, personne1.identificateur) == false && personne2.CalculAge(personne2.date_de_naissance) > personne1.CalculAge(personne1.date_de_naissance) && personne1.aDejaParent == false && personne1.nom.equalsIgnoreCase(personne2.nom)){
                        personne1.parent = personne2.identificateur;
                        personne2.enfants.add(personne1.identificateur);
                        personne1.aDejaParent = true;
                        personne2.OrdonneEnfants(personneHashMap);
                    for (int i : personne2.enfants) {
                        personneHashMap.get(i).LienFraternel(personne2.enfants);

                    }
                }
                    else
                    {
                        System.out.println(personne1.prenoms+ "  Est déja un enfant de ou est plus agé que son père "+personne2.prenoms);}
                    personneHashMap.remove(personne1.identificateur);
                    personneHashMap.remove(personne2.identificateur);


                    personneHashMap.put(personne1.identificateur, personne1);
                    personneHashMap.put(personne2.identificateur, personne2);

                } catch (Exception e){

                    System.out.println(e);
                }

                if(s.serialiser(personneHashMap) == 1){
                    System.out.println("succès");
                } else {
                    System.out.println("echec");
                }

                break;
            case 2:
                clearConsole();
                menu();
                break;
        }

    }

    static int retouverIdPersonne(HashMap<Integer,Personne> p){
         LinkedList<Integer> Idpersonne  = new LinkedList<>();
         String nom , prenom;
         Scanner sc1 = new Scanner(System.in);
         System.out.println("Entrez le nom : ");
         nom = sc1.nextLine();
         System.out.println("Entrez le prenom : ");
         prenom = sc1.nextLine();

         for (Personne personne :p.values()){
             if(personne.nom.equalsIgnoreCase(nom) && personne.prenoms.equalsIgnoreCase(prenom))
                 Idpersonne.add(personne.identificateur);

         }
         if(Idpersonne.isEmpty()) {
             System.out.println("Cette personne n'existe pas dans notre liste de Personne");
             return -1;

         }if (Idpersonne.size() == 1)
             return Idpersonne.get(0);
          else{
              System.out.println("Voici les personnes retrouvés  ");
              for(int i : Idpersonne){
                  p.get(i).affiche();

              }
              int id =-1;
              while (Personne.VerificationID(Idpersonne,id)==false) {
                  System.out.println("veillez saisir l'id");
                  id = sc1.nextInt();
              }
             return id;
         }


     }

     static void afficherLesenfantsPersonnes(){
         stockage s = new stockage();
         HashMap <Integer,Personne> personneHashMap = s.deserialiser();
         clearConsole();
         int i = retouverIdPersonne(personneHashMap);
         personneHashMap.get(i).afficheEnfantsPersonne(personneHashMap);

     }

    static void afficherLesFrereouSoeurPersonnes(){
        stockage s = new stockage();
        HashMap <Integer,Personne> personneHashMap = s.deserialiser();
        clearConsole();
        int i = retouverIdPersonne(personneHashMap);
        personneHashMap.get(i).afficheFrereouSoeur(personneHashMap);

    }
    static void afficherAscendantsPersonnes(){
        stockage s = new stockage();
        HashMap <Integer,Personne> personneHashMap = s.deserialiser();
        clearConsole();
        int i = retouverIdPersonne(personneHashMap);
        personneHashMap.get(i).afficherAscendant(personneHashMap,i);

    }
    static void afficherCousinsPersonnes(){
        stockage s = new stockage();
        HashMap <Integer,Personne> personneHashMap = s.deserialiser();
        clearConsole();
        int i = retouverIdPersonne(personneHashMap);
        personneHashMap.get(i).afficherCousin(personneHashMap,i);

    }
    static void afficherArbrePersonnes(){
        clearConsole();
        stockage s = new stockage();
        HashMap <Integer,Personne> personneHashMap = s.deserialiser();
        clearConsole();
        int i = retouverIdPersonne(personneHashMap);
        personneHashMap.get(i).AffichageArbre(personneHashMap,i);

    }
    static void afficherNombreAscePersonnes(){
        clearConsole();
        stockage s = new stockage();
        HashMap <Integer,Personne> personneHashMap = s.deserialiser();
        clearConsole();
        int i = retouverIdPersonne(personneHashMap);
        System.out.println(personneHashMap.get(i).NombreAscendant(personneHashMap,i));

    }
    static  void AffichageArbrePersonnesDepuis1ereG(){
         clearConsole();
        stockage s = new stockage();
        HashMap <Integer,Personne> personneHashMap = s.deserialiser();
        clearConsole();
        int i = retouverIdPersonne(personneHashMap);
        personneHashMap.get(i).AffichageArbre(personneHashMap,personneHashMap.get(i).afficherAscendantDepuispremiereG(personneHashMap,i));
    }

    static Personne creerPersonne(){
        String nom, prenoms, dateNaiss, sexe;
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrez le prenoms : ");
        prenoms = sc.nextLine();
        System.out.println("Entrez le nom : ");
        nom = sc.nextLine();
        System.out.println("Entrez la date de naissance au format jj-mm-aaaa");
        dateNaiss = sc.nextLine();
        System.out.println("Entrez le sexe :\n F pour Féminin \n M pour Masculin");
        sexe = sc.nextLine();
        Personne p = new Personne(0,nom,prenoms,sexe,dateNaiss);
        return p;
    }

    static void choix0(){
        clearConsole();
        afficherArbrePersonnes();
        System.out.println("Désirez vous afficher un autre arbre ?" +
                            "\n          1 : Oui \n          2 : Non");
        Scanner reponse = new Scanner(System.in);
        int choix = reponse.nextInt();
        if (choix == 1){
            choix0();
        }
    }

    static void choix1(){
        clearConsole();
        CreerLien();
        System.out.println("Désirez vous créer un autre lien ?" +
                            "\n          1 : Oui \n          2 : Non");
        Scanner reponse = new Scanner(System.in);
        int choix = reponse.nextInt();
        if (choix == 1){
            choix1();
        }
    }

    static void choix2(){
        clearConsole();
        afficherLesenfantsPersonnes();
        System.out.println("Désirez vous d'autre enfant d'une autre personne ?" +
                            "\n          1 : Oui \n          2 : Non");
        Scanner reponse = new Scanner(System.in);
        int choix = reponse.nextInt();
        if (choix == 1){
            choix2();
        }
    }

    static void choix3(){
        clearConsole();
        afficherLesFrereouSoeurPersonnes();
        System.out.println("Désirez vous créer afficher les frères et soeurs d'une autre personne ?" +
                            "\n          1 : Oui \n          2 : Non");
        Scanner reponse = new Scanner(System.in);
        int choix = reponse.nextInt();
        if (choix == 1){
            choix3();
        }
    }

    static void choix4(){
        clearConsole();
        afficherAscendantsPersonnes();
        System.out.println("Désirez vous créer afficher les ascendants d'une autre personne ?" +
                            "\n          1 : Oui \n          2 : Non");
        Scanner reponse = new Scanner(System.in);
        int choix = reponse.nextInt();
        if (choix == 1){
            choix4();
        }
    }

    static void choix5(){
        clearConsole();
        afficherCousinsPersonnes();
        System.out.println("Désirez vous afficher les cousins d'une autre personne ?" +
                            "\n          1 : Oui \n          2 : Non");
        Scanner reponse = new Scanner(System.in);
        int choix = reponse.nextInt();
        if (choix == 1){
            choix5();
        }
    }

    static void choix6(){
        clearConsole();
        afficherNombreAscePersonnes();
        System.out.println("Désirez vous visualiser le nombre d'ascendant d'une autre personne ?" +
                            "\n          1 : Oui \n          2 : Non");
        Scanner reponse = new Scanner(System.in);
        int choix = reponse.nextInt();
        if (choix == 1){
            choix6();
        }
    }

    static void choix7(){
        clearConsole();
        AffichageArbrePersonnesDepuis1ereG();
        System.out.println("Désirez vous afficher la première génération d'une autre personne ?"  +
                        "\n          1 : Oui \n          2 : Non");
        Scanner reponse = new Scanner(System.in);
        int choix = reponse.nextInt();
        if (choix == 1){
            choix7();
        }
    }

    static void choix8(){
        clearConsole();
        stockage s = new stockage();
        Scanner reponse = new Scanner(System.in);
        String choix = "1";

        while (choix.equalsIgnoreCase("1")){
            Personne p = creerPersonne();
            p.setID(s.TailleTableau());
            s.stocker(p);
            clearConsole();
            System.out.println("Personne Créer avec succès");
        System.out.println("Désirez vous ajouter une autre personne ?"  +
                        "\n          1 : Oui \n          2 : Non");
         choix = reponse.nextLine();
            if (choix == "1"){
                choix8();
            }
        }

    }

    static void choix9(){
        clearConsole();
        listePersonnes();
        System.out.println("Entrez l'identifiant de la personne que vous désirez modifier");
        Scanner reponse1 = new Scanner(System.in);
        int id = reponse1.nextInt();
        stockage s = new stockage();
        Personne p = s.deserialiser().get(id);
        clearConsole();
        System.out.println("1-nom 2-prenoms 3-sexe ");
        Scanner reponse2 = new Scanner(System.in);
        String choi = reponse2.nextLine();
        switch (choi){
            case "1" :{
                clearConsole();
                System.out.println("etes vous sur de modifier le nom \n");
                System.out.println("1-oui  2-non");
                String e = reponse2.nextLine();
                if(e.equalsIgnoreCase("1")){
                    System.out.println("Entrer le nom");
                    String e1 = reponse2.nextLine();
                    p.setNom(e1);
                    s.stocker(p);
                 System.out.println("modification effective avec succès ");
                }
            }
            break;

            case "2" :{
                clearConsole();
                System.out.println("etes vous sur de modifier le prenom \n");
                System.out.println("1-oui  2-non");
                String e = reponse2.nextLine();
                if(e.equalsIgnoreCase("1")){
                    System.out.println("Entrer le prenom");
                    String e1 = reponse2.nextLine();
                    p.setprenom(e1);
                    s.stocker(p);
                }

            }
            break;
            case "3" :{
                clearConsole();
                System.out.println("etes vous sur de modifier le Sexe \n");
                System.out.println("1-oui  2-non");
                String e = reponse2.nextLine();
                if(e.equalsIgnoreCase("1")){
                    System.out.println("Entrer le Sexe");
                    String e1 = reponse2.nextLine();
                    p.setSexe(e1);
                    s.stocker(p);
                }
            }
            break;

        }





    }


    static void menu(){

        System.out.println("          Veuillez effectuer un choix :\n" +
                "\n          0: Afficher un arbre \n          1: Creer un lien"+
                "\n          2: Afficher les enfants \n          3: Afficher les Freres et soeurs" +
                "\n          4: Affichier les ascendants d'une personne \n          5: Afficher les cousins" +
                "\n          6: Visualiser le nombre d'ascendant \n          7: Afficher la premiere generation " +
                "\n          8: Ajouter une nouvelle personne\n          9: Modifier une personne");

        Scanner reponse = new Scanner(System.in);
        int choix = reponse.nextInt() ;

        switch (choix){
            case 0:
                choix0();
                break;
            case 1:
                choix1();
                break;
            case 2:
                choix2();
                break;
            case 3:
                choix3();
                break;
            case 4:
                choix4();
                break;
            case 5:
                choix5();
                break;
            case 6:
                choix6();
                break;
            case 7:
                choix7();
                break;
            case 8:
                choix8();
                break;
            case 9:
                choix9();
                break;

        }
        clearConsole();
        System.out.println("      Désirez-vous quitter l'application ?" +
                            "\n          1 : Oui \n          2 : Non");
        choix = reponse.nextInt();
        if (choix == 2){
            clearConsole();
            menu();
        }
    }


    public static void main(String[] args) {
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|-********************************** BIENVENUE DANS LE GENERATEUR D'ARBRE GENEALOGIQUE **********************************-|");
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------|");
        System.out.println();
        System.out.println();
        menu();



    }

}
