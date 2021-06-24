package com.company;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.FileNotFoundException;

public class Personne implements Serializable {
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    private static final long serialVersionUID = 1L;
    Integer identificateur;
    String nom;
    String prenoms;
    String sexe;
    String date_de_naissance;
    Integer parent ;
    Boolean aDejaParent = false;
    LinkedList<Integer> enfants;
    LinkedList<Integer> frereousoeur;
    Boolean dejaParcourir = false;

    Personne() {
    }
    Personne(Integer id,String noms, String prenom, String sexes, String date_de_naissances) {
        enfants = new LinkedList<>();
        frereousoeur = new LinkedList<>();
        identificateur = id++;
        nom = noms;
        prenoms = prenom;
        sexe = sexes;
        date_de_naissance = date_de_naissances;
        parent = -1;

    }
    void setID(Integer id){
       this.identificateur = id;
    }
    void setNom(String nom){
        this.nom = nom;
    }
    void setprenom(String prenom){
        this.prenoms = prenom;
    }
    void setSexe(String sexe){
        this.sexe = sexe;
    }
    void setDate(String date){
        this.date_de_naissance = date;
    }

    void affiche() {

        System.out.println(" " + identificateur + " " + nom + " " + prenoms +" " + sexe +" "+ date_de_naissance );

    }
    void afficheLien() {
        System.out.println("-----------------------------------------");
        System.out.println(" id : " + identificateur+"\n");
        System.out.println(" nom : " +nom+"\n");
        System.out.println(" prenoms : " + prenoms+"\n");
        System.out.println(" date Naissance : " + date_de_naissance+"\n");
        System.out.println("-----------------------------------------");


    }

    void afficheLien2(){
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("| id : " + identificateur+ " | nom : " +nom+" | prenoms : " + prenoms+
                            " | date Naissance : " + date_de_naissance+
                            " | enfants : " + enfants);
    }


    static Boolean VerificationID(LinkedList<Integer> l, int id) {
        for (int i : l) {
            if (i == id)
                return true;
        }
        return false;

    }

    int CalculAge(String dateN) {

        int annees = 0;
        try {
            Date naissance = new SimpleDateFormat("dd-MM-yyyy").parse(dateN);
            // Calendrier pour la naissance.
            Calendar calendrierNaissance = Calendar.getInstance();
            calendrierNaissance.setTimeInMillis(naissance.getTime());
            // Calendrier pour le jour courant.
            Date maintenant = new Date();
            Calendar calendrierMaintenant = Calendar.getInstance();
            calendrierMaintenant.setTimeInMillis(maintenant.getTime());
            // Calcul du nombre d’années.
            annees = calendrierMaintenant.get(Calendar.YEAR) - calendrierNaissance.get(Calendar.YEAR);
        } catch (Exception e) {
            System.out.print("\t\t| probleme avec l'age : ");
            return annees;
        }
        return annees;


    }

    void LienFraternel(LinkedList<Integer> l) {
        frereousoeur.clear();
        for (int i : l) {
            if (i != identificateur)
                frereousoeur.add(i);
        }

    }

    void afficheEnfantsPersonne(HashMap<Integer, Personne> p) {

        for (int i : enfants) {
            p.get(i).affiche();

        }

    }

    void afficheFrereouSoeur(HashMap<Integer, Personne> p) {
        for (int i : frereousoeur) {
            p.get(i).affiche();

        }

    }

    void afficherAscendant(HashMap<Integer, Personne> p,int i) {
            i = parent ;
            while(p.get(i)!=null){
                p.get(i).affiche();
                i = p.get(i).parent;
            }

    }

   int afficherAscendantDepuispremiereG(HashMap<Integer, Personne> p,int i) {
        i = parent;
        int id = -1;
        while (p.get(i) != null) {
            id = p.get(i).identificateur;
            i = p.get(i).parent;
        }
        return id;
    }
        int NombreAscendant(HashMap<Integer, Personne> p,int i) {
        int cpt = 0;
        i = parent ;
        while(p.get(i)!=null){
            cpt = cpt + 1 ;
            i = p.get(i).parent;

        }
     return cpt;
    }

    void afficherCousin(HashMap<Integer, Personne> p,int i){
        i = parent;
        for( int j : p.get(i).frereousoeur){
            for(int k : p.get(j).enfants)
                p.get(k).affiche();
        }
    }
    void OrdonneEnfants(HashMap<Integer, Personne> p){

        LinkedList<Integer> p1 = new LinkedList<>();
        int age = -1 ;
        int index=0 ;
        int id =0 ;
     while(!enfants.isEmpty()) {
         for (int i : enfants) {
             if (this.CalculAge(p.get(i).date_de_naissance) > age) {
                 age = this.CalculAge(p.get(i).date_de_naissance);
                 index = enfants.indexOf(i);
                 id = i;

             }

         }
         p1.addLast(id);
         enfants.remove(index);
         age = -1;
     }
      enfants = p1;

    }


    void RetournerAine(HashMap<Integer, Personne> p){

        p.get(enfants.getFirst()).affiche();
    }

    void AffichageArbre(HashMap<Integer, Personne> p,int j){
       for(int i =0;i<p.get(j).NombreAscendant(p,j);i++){
           System.out.print("\t");
       }
       p.get(j).affiche();
       p.get(j).dejaParcourir = true;

       for (Personne personne : p.values()){
           if (personne.dejaParcourir == false && personne.parent == j){
               AffichageArbre(p,personne.identificateur);
           }

       }
        p.get(j).dejaParcourir =false;
    }

}