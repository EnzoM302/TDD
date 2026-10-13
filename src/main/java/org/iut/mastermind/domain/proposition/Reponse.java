package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    // on récupère la lettre à la position spécifiée en paramètre dans le résultat
    public Lettre lettre(int position) {
        return resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        for (int position = 0; position < essai.length(); position++) {
            char carCourant = essai.charAt(position);
            Lettre lettre = evaluationCaractere(carCourant, position);
            resultat.add(lettre);
        }
    }

    // vrai si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        return resultat.stream().allMatch(lettre -> lettre == Lettre.PLACEE);
    }

	// méthode obligatoire - à ne pas supprimer
    public List<Lettre> lettresResultat() {
        return Collections.unmodifiableList(resultat);
    }

    // renvoie le statut du caractère (incorrect, mal placé, placé)
    private Lettre evaluationCaractere(char carCourant , int position) {
        if (estPlace(carCourant,position)) {
            return Lettre.PLACEE;
        } else if (estPresent(carCourant)) {
            return Lettre.NON_PLACEE;
        }
        return Lettre.INCORRECTE;
    }

    // vrai si le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        return motSecret.indexOf(carCourant) >= 0;
    }

    // vrai si le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant, int position) {
        return motSecret.charAt(position) == carCourant;
    }
}
