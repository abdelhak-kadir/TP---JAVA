import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Filiere {
    private String nom;
    private ArrayList<Etudiant> etudiants;

    public Filiere(String nom) {
        this.nom = nom;
        this.etudiants = new ArrayList<>();
    }

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public ArrayList<Etudiant> getEtudiants() {
        return etudiants;
    }

    // Affichage des informations des étudiants
    public void afficherInformationsFiliere() {
        System.out.println("\nInformations sur la filière : " + nom);
        Collections.sort(etudiants, Comparator.comparing(Etudiant::getPrenom)); // Trie par prénom
        for (Etudiant e : etudiants) {
            System.out.println(e);
        }
    }

    // Ajout d'un étudiant
    public void ajouterEtudiant(Etudiant e) {
        if (!etudiants.contains(e)) {
            etudiants.add(e);
            e.setFiliere(this); // L'étudiant appartient maintenant à cette filière
        } else {
            System.out.println("L'étudiant est déjà inscrit dans cette filière.");
        }
    }

    // Recherche d'un étudiant par son CNE
    public Etudiant rechercherEtudiantParCNE(String cne) {
        for (Etudiant e : etudiants) {
            if (e.getCne().equals(cne)) {
                return e;
            }
        }
        return null;
    }

    // Modification des informations d'un étudiant
    public void modifierEtudiant(String cne, String nom, String prenom, String dateNaissance) {
        Etudiant e = rechercherEtudiantParCNE(cne);
        if (e != null) {
            e.modifierInformations(nom, prenom, dateNaissance);
            System.out.println("Les informations de l'étudiant " + cne + " ont été modifiées.");
        } else {
            System.out.println("Étudiant non trouvé.");
        }
    }

    // Suppression d'un étudiant
    public void supprimerEtudiant(Etudiant e) {
        etudiants.remove(e);
        e.setFiliere(null); // L'étudiant ne fait plus partie de cette filière
        System.out.println("L'étudiant " + e.getCne() + " a été supprimé.");
    }
}
