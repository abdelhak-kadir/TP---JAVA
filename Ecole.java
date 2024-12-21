import java.util.ArrayList;

public class Ecole {
    private String nom;
    private ArrayList<Filiere> filieres;

    public Ecole(String nom) {
        this.nom = nom;
        this.filieres = new ArrayList<>();
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public ArrayList<Filiere> getFilieres() {
        return filieres;
    }

    // Affichage de toutes les filières
    public void afficherToutesLesFiliares() {
        System.out.println("Liste des filières de l'école :");
        for (Filiere f : filieres) {
            System.out.println(f.getNom());
        }
    }

    // Ajouter une nouvelle filière
    public void ajouterFiliere(Filiere f) {
        if (!filieres.contains(f)) {
            filieres.add(f);
        } else {
            System.out.println("La filière existe déjà.");
        }
    }

    // Permettre à un étudiant de changer de filière
    public void changerFiliere(Etudiant e, Filiere nouvelleFiliere) {
        if (e.getFiliere() != null) {
            e.getFiliere().supprimerEtudiant(e); // Supprimer l'étudiant de l'ancienne filière
        }
        nouvelleFiliere.ajouterEtudiant(e); // Ajouter l'étudiant à la nouvelle filière
        System.out.println(
                "L'étudiant " + e.getCne() + " a été transféré vers la filière " + nouvelleFiliere.getNom() + ".");
    }
}
