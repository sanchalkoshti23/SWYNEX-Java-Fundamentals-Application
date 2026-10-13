import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import model.SecurityIncident;
import service.IncidentService;
import util.InputValidator;

public class Main {
    public static void main(String[] args) {
        IncidentService incidentService = new IncidentService();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            try {
                while (running) {
                    displayMenu();
                    System.out.print("Choose an option: ");
                    String menuInput = scanner.nextLine();

                    int choice;
                    try {
                        choice = Integer.parseInt(menuInput.trim());
                    } catch (NumberFormatException exception) {
                        System.out.println("Please enter a valid number from 1 to 7.");
                        continue;
                    }

                    switch (choice) {
                        case 1:
                            addIncident(scanner, incidentService);
                            break;
                        case 2:
                            viewAllIncidents(incidentService);
                            break;
                        case 3:
                            findIncident(scanner, incidentService);
                            break;
                        case 4:
                            updateIncidentStatus(scanner, incidentService);
                            break;
                        case 5:
                            deleteIncident(scanner, incidentService);
                            break;
                        case 6:
                            generateIncidentReport(incidentService);
                            break;
                        case 7:
                            running = false;
                            System.out.println("Goodbye.");
                            break;
                        default:
                            System.out.println("Please choose an option from 1 to 7.");
                    }
                }
            } catch (NoSuchElementException exception) {
                System.out.println();
                System.out.println("Input ended. Exiting CyberShield.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println();
        System.out.println("CyberShield Incident Simulator");
        System.out.println("1. Add Incident");
        System.out.println("2. View All Incidents");
        System.out.println("3. Find Incident by ID");
        System.out.println("4. Update Incident Status");
        System.out.println("5. Delete Incident");
        System.out.println("6. Generate Incident Report");
        System.out.println("7. Exit");
    }

    private static void addIncident(Scanner scanner, IncidentService incidentService) {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Category (PHISHING, MALWARE, UNAUTHORIZED_ACCESS, DATA_BREACH): ");
        String category = scanner.nextLine();

        System.out.print("Severity (LOW, MEDIUM, HIGH): ");
        String severity = scanner.nextLine();

        boolean validInput = true;

        if (!InputValidator.isNotBlank(title)) {
            System.out.println("Title cannot be blank.");
            validInput = false;
        }
        if (!InputValidator.isNotBlank(description)) {
            System.out.println("Description cannot be blank.");
            validInput = false;
        }
        if (!InputValidator.isValidCategory(category)) {
            System.out.println("Please enter one of the listed categories.");
            validInput = false;
        }
        if (!InputValidator.isValidSeverity(severity)) {
            System.out.println("Please enter LOW, MEDIUM, or HIGH for severity.");
            validInput = false;
        }

        if (!validInput) {
            System.out.println("Incident was not added. Please try again.");
            return;
        }

        SecurityIncident incident = incidentService.addIncident(
                title.trim(),
                description.trim(),
                category.trim().toUpperCase(Locale.ROOT),
                severity.trim().toUpperCase(Locale.ROOT)
        );
        System.out.println("Incident added:");
        System.out.println(incident);
    }

    private static void viewAllIncidents(IncidentService incidentService) {
        List<SecurityIncident> incidents = incidentService.getAllIncidents();

        if (incidents.isEmpty()) {
            System.out.println("There are no incidents to display.");
            return;
        }

        System.out.println("All incidents:");
        for (SecurityIncident incident : incidents) {
            System.out.println(incident);
        }
    }

    private static void findIncident(Scanner scanner, IncidentService incidentService) {
        System.out.print("Enter incident ID: ");
        String idInput = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput.trim());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a valid numeric incident ID.");
            return;
        }

        SecurityIncident incident = incidentService.findIncidentById(id);
        if (incident == null) {
            System.out.println("No incident found with ID " + id + ".");
        } else {
            System.out.println(incident);
        }
    }

    private static void updateIncidentStatus(Scanner scanner, IncidentService incidentService) {
        System.out.print("Enter incident ID: ");
        String idInput = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput.trim());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a valid numeric incident ID.");
            return;
        }

        System.out.print("Enter the new status (INVESTIGATING or RESOLVED): ");
        String newStatus = scanner.nextLine();

        if (incidentService.updateIncidentStatus(id, newStatus)) {
            System.out.println("Incident status updated successfully.");
        } else {
            System.out.println("Status was not updated. The incident may not exist or the transition may be invalid.");
        }
    }

    private static void deleteIncident(Scanner scanner, IncidentService incidentService) {
        System.out.print("Enter incident ID: ");
        String idInput = scanner.nextLine();

        int id;
        try {
            id = Integer.parseInt(idInput.trim());
        } catch (NumberFormatException exception) {
            System.out.println("Please enter a valid numeric incident ID.");
            return;
        }

        if (incidentService.deleteIncident(id)) {
            System.out.println("Incident " + id + " was deleted successfully.");
        } else {
            System.out.println("No incident found with ID " + id + ".");
        }
    }

    private static void generateIncidentReport(IncidentService incidentService) {
        System.out.println();
        System.out.println("Incident Summary Report");
        System.out.println("Total incidents: " + incidentService.getAllIncidents().size());

        System.out.println("By severity:");
        System.out.println("LOW: " + incidentService.countBySeverity("LOW"));
        System.out.println("MEDIUM: " + incidentService.countBySeverity("MEDIUM"));
        System.out.println("HIGH: " + incidentService.countBySeverity("HIGH"));

        System.out.println("By status:");
        System.out.println("OPEN: " + incidentService.countByStatus("OPEN"));
        System.out.println("INVESTIGATING: " + incidentService.countByStatus("INVESTIGATING"));
        System.out.println("RESOLVED: " + incidentService.countByStatus("RESOLVED"));
    }
}
