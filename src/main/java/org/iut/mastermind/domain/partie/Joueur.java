package org.iut.mastermind.domain.partie;

public record Joueur(String nom) {

    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        return nom.equals(((Joueur) o).getNom());
    }

}
