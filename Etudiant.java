public class Etudiant {
    private String cne;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private Filiere filiere; // La filière à laquelle l'étudiant appartient

    public Etudiant(String cne, String nom, String prenom, String dateNaissance) {
        this.cne = cne;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.filiere = null; // Initialement, l'étudiant n'appartient à aucune filière
    }

    // Getters et setters
    public String getCne() {
        return cne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    // Modification des informations d'un étudiant
    public void modifierInformations(String nom, String prenom, String dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    @Override
    public String toString() {
        return cne + " - " + nom + " " + prenom + " (" + dateNaissance + ")";
    }
}
