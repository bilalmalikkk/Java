import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IncidentManagementSystem 
{
    private List<Incident> incidents;
    private List<Resource> uniqueResources;

    public IncidentManagementSystem() {
        this.incidents = new ArrayList<>();
        this.uniqueResources = new ArrayList<>();
    }

    // Add a new incident to the list
    public void addIncident(Incident incident) 
    {
        incidents.add(incident);
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    // Get all incidents stored in the system
    public List<Incident> getAllIncidents() 
    {
        return incidents;
    }

    public void addIncident(Incident incident, String filename) 
    {
        incidents.add(incident);
        incident.writeToCSV(filename);
    }

    // Save incidents to a CSV file
    public void saveIncidentsToCSV(String filename) 
    {
        try (PrintWriter writer = new PrintWriter(new File(filename))) 
        {
            writer.println("Incident No, Reporter No, Date, Time, GPS-What3Words, Incident Details, Ongoing");
            for (Incident incident : incidents) 
            {
                writer.println(incident.toCSVString());
            }
        } catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    // Load incidents from a CSV file
    public void loadIncidentsFromCSV(String filename) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) 
        {
            String line;
            boolean firstLine = true; // Skip the header line
            while ((line = reader.readLine()) != null) 
            {
                if (firstLine) 
                {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                incidents.add(new Incident(data));
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Save resources to a CSV file
    public void saveResourcesToCSV(String filename) 
    {
        try (PrintWriter writer = new PrintWriter(new File(filename))) 
        {
            writer.println("Resource No, Resource Code, Resource Description, Number of Units");
    
            // Loop through all incidents and save their resources
            for (Incident incident : incidents) 
            {
                for (Resource resource : incident.getResources()) 
                {
                    writer.println(resource.toCSVString());
                }
            }
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
    
    // Load resources from a CSV file
    public void loadResourcesFromCSV(String filename) 
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filename)))) 
        {
            String line;
            boolean firstLine = true; // Skip the header line
            while ((line = reader.readLine()) != null) 
            {
                if (firstLine) 
                {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                int resourceNumber = Integer.parseInt(data[0].trim());
                String resourceCode = data[1].trim();
                String resourceDescription = data[2].trim();
                int numUnits = Integer.parseInt(data[3].trim());

                // Create a new Resource object with the gathered information
                Resource resource = new Resource(resourceNumber, resourceCode, resourceDescription, numUnits);
                // Add the resource to the unique resources list
                addUniqueResource(resource);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Add a resource to the list of unique resources
    private void addUniqueResource(Resource resource) 
    {
        if (!uniqueResources.contains(resource)) 
        {
            uniqueResources.add(resource);
        }
    }

    // Find an incident by its incident number
    public Incident findIncidentByNumber(int incidentNumber) 
    {
        for (Incident incident : incidents) 
        {
            if (incident.getIncidentNumber() == incidentNumber) 
            {
                return incident;
            }
        }
        return null; // Incident not found
    }

    // Update the details of an existing incident
    public boolean updateIncident(Incident updatedIncident) 
    {
        int index = incidents.indexOf(updatedIncident);
        if (index != -1) 
        {
            incidents.set(index, updatedIncident);
            return true; // Update successful
        }
        return false; // Incident not found, update failed
    }

    // Display a list of all incidents with their details
    public void displayIncidents() 
    {
        System.out.println("=== List of Incidents ===");
        for (Incident incident : incidents) 
        {
            System.out.println(incident);
        }
    }

    // Assign a resource to an existing incident
    public boolean assignResourceToIncident(int incidentNumber, Resource resource) 
    {
        Incident incident = findIncidentByNumber(incidentNumber);
        if (incident != null) 
        {
            incident.addResource(resource);
            return true; // Resource assigned successfully
        }
        return false; // Incident not found, resource assignment failed
    }
}
