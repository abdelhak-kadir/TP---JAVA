import java.util.Scanner;
import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Création de l'école et des filières
        Ecole ecole = new Ecole("École ENSAA");

        // Add some filières to test
        Filiere filiere1 = new Filiere("Informatique");
        Filiere filiere2 = new Filiere("Génie Civil");
        ecole.ajouterFiliere(filiere1);
        ecole.ajouterFiliere(filiere2);

        while (true) {
            System.out.println("\nMenu principal:");
            System.out.println("1. Ajouter un étudiant");
            System.out.println("2. Modifier un étudiant");
            System.out.println("3. Supprimer un étudiant");
            System.out.println("4. Trouver un étudiant");
            System.out.println("5. Afficher les étudiants d'une filière");
            System.out.println("6. Changer la filière d'un étudiant");
            System.out.println("7. Ajouter une filière");
            System.out.println("8. Afficher toutes les filières");
            System.out.println("9. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Ignorer le saut de ligne

            switch (choix) {
                case 1:
                    addStudent(ecole, scanner, sdf);
                    break;
                case 2:
                    updateStudent(ecole, scanner, sdf);
                    break;
                case 3:
                    deleteStudent(ecole, scanner);
                    break;
                case 4:
                    findStudent(ecole, scanner);
                    break;
                case 5:
                    displayStudents(ecole, scanner);
                    break;
                case 6:
                    changeFiliere(ecole, scanner);
                    break;
                case 7:
                    addFiliere(ecole, scanner);
                    break;
                case 8:
                    displayFilieres(ecole);
                    break;
                case 9:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    // Method to add a student
    public static void addStudent(Ecole ecole, Scanner scanner, SimpleDateFormat sdf) {
        System.out.print("Entrez le CNE de l'étudiant : ");
        String cne = scanner.nextLine();
        System.out.print("Entrez le nom de l'étudiant : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le prénom de l'étudiant : ");
        String prenom = scanner.nextLine();
        System.out.print("Entrez la date de naissance de l'étudiant (dd/MM/yyyy) : ");
        String dateNaissance = scanner.nextLine();

        try {
            Etudiant etudiant = new Etudiant(cne, nom, prenom, dateNaissance);
            System.out.println("Entrez la filière (1 pour Informatique, 2 pour Génie Civil) : ");
            int filiereChoix = scanner.nextInt();
            scanner.nextLine(); // Ignorer le saut de ligne
            if (filiereChoix == 1) {
                Filiere filiere1 = ecole.getFilieres().get(0);
                filiere1.ajouterEtudiant(etudiant);
            } else if (filiereChoix == 2) {
                Filiere filiere2 = ecole.getFilieres().get(1);
                filiere2.ajouterEtudiant(etudiant);
            } else {
                System.out.println("Choix de filière invalide.");
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de l'étudiant : " + e.getMessage());
        }
    }

    // Method to update a student's information
    public static void updateStudent(Ecole ecole, Scanner scanner, SimpleDateFormat sdf) {
        System.out.print("Entrez le CNE de l'étudiant à modifier : ");
        String cneModifier = scanner.nextLine();
        System.out.print("Entrez le nouveau nom de l'étudiant : ");
        String nouveauNom = scanner.nextLine();
        System.out.print("Entrez le nouveau prénom de l'étudiant : ");
        String nouveauPrenom = scanner.nextLine();
        System.out.print("Entrez la nouvelle date de naissance de l'étudiant (dd/MM/yyyy) : ");
        String nouvelleDateNaissance = scanner.nextLine();

        boolean found = false;
        for (Filiere filiere : ecole.getFilieres()) {
            filiere.modifierEtudiant(cneModifier, nouveauNom, nouveauPrenom, nouvelleDateNaissance);
            found = true;
        }
        if (!found) {
            System.out.println("Étudiant non trouvé.");
        }
    }

    // Method to delete a student
    public static void deleteStudent(Ecole ecole, Scanner scanner) {
        System.out.print("Entrez le CNE de l'étudiant à supprimer : ");
        String cneSupprimer = scanner.nextLine();
        boolean found = false;
        for (Filiere filiere : ecole.getFilieres()) {
            Etudiant etudiant = filiere.rechercherEtudiantParCNE(cneSupprimer);
            if (etudiant != null) {
                filiere.supprimerEtudiant(etudiant);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Étudiant non trouvé.");
        }
    }

    // Method to find a student by CNE
    public static void findStudent(Ecole ecole, Scanner scanner) {
        System.out.print("Entrez le CNE de l'étudiant à rechercher : ");
        String cneRechercher = scanner.nextLine();
        boolean found = false;
        for (Filiere filiere : ecole.getFilieres()) {
            Etudiant etudiant = filiere.rechercherEtudiantParCNE(cneRechercher);
            if (etudiant != null) {
                System.out.println("Étudiant trouvé : " + etudiant);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Étudiant non trouvé.");
        }
    }

    // Method to display students of a specific filière
    public static void displayStudents(Ecole ecole, Scanner scanner) {
        System.out.println("Entrez le nom de la filière à afficher les étudiants : ");
        String filiereNom = scanner.nextLine();
        boolean found = false;
        for (Filiere filiere : ecole.getFilieres()) {
            if (filiere.getNom().equalsIgnoreCase(filiereNom)) {
                filiere.afficherInformationsFiliere();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Filière non trouvée.");
        }
    }

    // Method to change a student's filière
    public static void changeFiliere(Ecole ecole, Scanner scanner) {
        System.out.print("Entrez le CNE de l'étudiant à transférer : ");
        String cneTransferer = scanner.nextLine();
        Etudiant etudiantTransferer = null;
        for (Filiere filiere : ecole.getFilieres()) {
            etudiantTransferer = filiere.rechercherEtudiantParCNE(cneTransferer);
            if (etudiantTransferer != null) {
                break;
            }
        }

        if (etudiantTransferer != null) {
            System.out.println("Entrez la nouvelle filière (1 pour Informatique, 2 pour Génie Civil) : ");
            int nouvelleFiliereChoix = scanner.nextInt();
            scanner.nextLine(); // Ignorer le saut de ligne
            Filiere nouvelleFiliere = (nouvelleFiliereChoix == 1) ? ecole.getFilieres().get(0)
                    : ecole.getFilieres().get(1);
            ecole.changerFiliere(etudiantTransferer, nouvelleFiliere);
        } else {
            System.out.println("Étudiant non trouvé.");
        }
    }

    // Method to add a new filière
    public static void addFiliere(Ecole ecole, Scanner scanner) {
        System.out.print("Entrez le nom de la nouvelle filière : ");
        String nomFiliere = scanner.nextLine();
        Filiere filiere = new Filiere(nomFiliere);
        ecole.ajouterFiliere(filiere);
        System.out.println("Filière ajoutée avec succès.");
    }

    // Method to display all filières
    public static void displayFilieres(Ecole ecole) {
        ecole.afficherToutesLesFiliares();
    }
}
