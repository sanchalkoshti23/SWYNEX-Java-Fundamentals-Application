package service;

import model.SecurityIncident;

import java.util.ArrayList;
import java.util.List;

public class IncidentService {
    private List<SecurityIncident> incidents = new ArrayList<>();
    private int nextIncidentId = 1;

    public SecurityIncident addIncident(String title, String description, String category,
                                        String severity) {
        SecurityIncident incident = new SecurityIncident(
                nextIncidentId,
                title,
                description,
                category,
                severity,
                "OPEN"
        );
        incidents.add(incident);
        nextIncidentId++;
        return incident;
    }

    public List<SecurityIncident> getAllIncidents() {
        return new ArrayList<>(incidents);
    }

    public SecurityIncident findIncidentById(int id) {
        for (SecurityIncident incident : incidents) {
            if (incident.getId() == id) {
                return incident;
            }
        }
        return null;
    }

    public boolean deleteIncident(int id) {
        SecurityIncident incident = findIncidentById(id);
        if (incident == null) {
            return false;
        }

        incidents.remove(incident);
        return true;
    }

    public boolean updateIncidentStatus(int id, String newStatus) {
        SecurityIncident incident = findIncidentById(id);
        if (incident == null || newStatus == null) {
            return false;
        }

        String requestedStatus = newStatus.trim();
        String currentStatus = incident.getStatus();
        if (currentStatus == null) {
            return false;
        }

        currentStatus = currentStatus.trim();
        if (currentStatus.equalsIgnoreCase("OPEN")
                && requestedStatus.equalsIgnoreCase("INVESTIGATING")) {
            incident.setStatus("INVESTIGATING");
            return true;
        }

        if (currentStatus.equalsIgnoreCase("INVESTIGATING")
                && requestedStatus.equalsIgnoreCase("RESOLVED")) {
            incident.setStatus("RESOLVED");
            return true;
        }

        return false;
    }

    public int countBySeverity(String severity) {
        if (severity == null) {
            return 0;
        }

        int count = 0;
        for (SecurityIncident incident : incidents) {
            String incidentSeverity = incident.getSeverity();
            if (incidentSeverity != null
                    && incidentSeverity.trim().equalsIgnoreCase(severity.trim())) {
                count++;
            }
        }
        return count;
    }

    public int countByStatus(String status) {
        if (status == null) {
            return 0;
        }

        int count = 0;
        for (SecurityIncident incident : incidents) {
            String incidentStatus = incident.getStatus();
            if (incidentStatus != null
                    && incidentStatus.trim().equalsIgnoreCase(status.trim())) {
                count++;
            }
        }
        return count;
    }
}
