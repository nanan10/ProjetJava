package com.company;

public class FrereouSoeurPersonne extends Personne{
    FrereouSoeurPersonne(Personne FrereouSoeur){
        super(FrereouSoeur.identificateur, FrereouSoeur.nom, FrereouSoeur.prenoms,FrereouSoeur.sexe,FrereouSoeur.date_de_naissance,FrereouSoeur.parent);
    }
}
