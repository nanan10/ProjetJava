package com.company;

public class EnfantPersonne extends Personne {
    EnfantPersonne(Personne parent){
        super(parent.identificateur, parent.nom, parent.prenoms,parent.sexe,parent.date_de_naissance,parent.parent);

    }
}
