package org.iut.mastermind.domain.partie;

import org.iut.mastermind.domain.proposition.MotSecret;
import org.iut.mastermind.domain.proposition.Reponse;

public class Partie {
    private static final int NB_ESSAIS_MAX = 5;
    private final Joueur joueur;
    private final String motADeviner;
    private int nbEssais;
    private boolean partieTerminee;

    private Partie(Joueur joueur, String motADeviner, int nbEssais, boolean partieTerminee) {
        this.joueur = joueur;
        this.motADeviner = motADeviner;
        this.nbEssais = nbEssais;
        this.partieTerminee = partieTerminee;
    }

    public static Partie nouvelle(Joueur joueur, String motADeviner) {
        return new Partie(joueur, motADeviner, 0, false);
    }

    public static Partie reprendre(Joueur joueur, String motADeviner, int nbEssais) {
        return new Partie(joueur, motADeviner, nbEssais, false);
    }

    // getter joueur
    public Joueur getJoueur() {
        return joueur;
    }

    // getter nombre d'essais
    public int getNbEssais() {
        return nbEssais;
    }

    // getter mot à deviner
    public String getMot() {
        return motADeviner;
    }

    // si le nombre max d'essais n'est pas atteint,
    // on compare la proposition au mot secret
    // et on construit et on renvoie la réponse
    // si toutes les lettres sont correctement placées,
    // on a terminé la partie
    public Reponse tourDeJeu(String motPropose) {
        if (nbEssais>=NB_ESSAIS_MAX) {
            throw new IllegalArgumentException("Vous avez dépasser le nombre d'essaies");
        }
        nbEssais++;
        MotSecret motSecret = new MotSecret(motADeviner);
        Reponse reponse = motSecret.compareProposition(motPropose);
        if (isTerminee()) {
            done();
        }
        return reponse;
    }

    // getter pour savoir si la partie est terminée
    public boolean isTerminee() {
        return partieTerminee;
    }

    // indique que la partie est terminée
    void done() {
        partieTerminee = true;
    }
}
