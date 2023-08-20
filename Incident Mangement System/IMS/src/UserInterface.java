import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInterface
{
    private IncidentManagementSystem ims;
    private Scanner scanner;
    private static final String INCIDENTS_CSV_FILE = "incidents.csv";
    private static final String RESOURCES_CSV_FILE = "resources.csv";

    private boolean isValidTextInput(String input) 
    {
        // Basic validation - check if the input contains only alphabetic characters and spaces
        return input != null && input.matches("[a-zA-Z ]+");
    }
    
    private boolean incidentExistsInCSV(int incidentNumber) 
    {
        List<Incident> incidents = ims.getIncidents();
        for (Incident incident : incidents) 
        {
            if (incident.getIncidentNumber() == incidentNumber) 
            {
                return true;
            }
        }
        return false;
    }

    private void saveToCSV() 
    {
        ims.saveIncidentsToCSV(INCIDENTS_CSV_FILE);
        ims.saveResourcesToCSV(RESOURCES_CSV_FILE);
        System.out.println("Incidents and Resources saved to CSV files successfully!");
    }

    private void loadFromCSV() {
        ims.loadIncidentsFromCSV(INCIDENTS_CSV_FILE);
        ims.loadResourcesFromCSV(RESOURCES_CSV_FILE);
        System.out.println("Incidents and Resources loaded from CSV files successfully!");
    }

    public UserInterface(IncidentManagementSystem ims) 
    {
        this.ims = ims;
        this.scanner = new Scanner(System.in);
    }

    public void displayMainMenu() 
    {
        System.out.println("=== Incident Management System ===");
        System.out.println("1. Create Incident");
        System.out.println("2. Update Incident");
        System.out.println("3. Assign Resource");
        System.out.println("4. Display Incidents");
        System.out.println("5. Save Incidents and Resources to CSV");
        System.out.println("6. Load Incidents and Resources from CSV");
        System.out.println("0. Exit");
    }

    public void createIncident() 
    {
        System.out.println("Enter incident number:");
        int incidentNumber = 0;
    while (true) 
    {
        try 
        {
            incidentNumber = scanner.nextInt();
            scanner.nextLine();
            break;
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Invalid input. Please enter a valid incident number:");
            scanner.nextLine();
        }
    }

    // Check if the incident number already exists in the CSV file
    if (incidentExistsInCSV(incidentNumber)) 
    {
        System.out.println("Incident with the entered ID already exists. Please enter a different ID.");
        return;
    }
    
        String reporterName;
        do 
        {
            System.out.println("Enter reporter's name:");
            reporterName = scanner.nextLine();
            if (!isValidName(reporterName)) 
            {
                System.out.println("Invalid reporter name. Please enter a valid name.");
            }
        } 
        while (!isValidName(reporterName));
    
        String reporterPhoneNumber;
        do 
        {
            System.out.println("Enter reporter's phone number:");
            reporterPhoneNumber = scanner.nextLine();
            if (!isValidPhoneNumber(reporterPhoneNumber)) 
            {
                System.out.println("Invalid phone number. Please enter a valid phone number.");
            }
        } 
        while (!isValidPhoneNumber(reporterPhoneNumber));
    
        String date;
        do 
        {
            System.out.println("Enter date of the incident (format: dd/mm/yyyy):");
            date = scanner.nextLine();
            if (!isValidDate(date)) 
            {
                System.out.println("Invalid date. Please enter a valid date in the format dd/mm/yyyy.");
            }
        } 
        while (!isValidDate(date));
    
        String time;
        do 
        {
            System.out.println("Enter time of the incident (format: hh:mm):");
            time = scanner.nextLine();
            if (!isValidTime(time)) 
            {
                System.out.println("Invalid time. Please enter a valid time in the format hh:mm.");
            }
        } 
        while (!isValidTime(time));
    
        String gpsLocation;
        do 
        {
            System.out.println("Enter GPS location (e.g., latitude, longitude):");
            gpsLocation = scanner.nextLine();
            if (!isValidGPSLocation(gpsLocation)) 
            {
                System.out.println("Invalid GPS location. Please enter a valid location in the format latitude, longitude.");
            }
        } 
        while (!isValidGPSLocation(gpsLocation));
    
        System.out.println("Enter incident details:");
        String incidentDetails;

        do {
            incidentDetails = scanner.nextLine();
            if (!isValidTextInput(incidentDetails)) {
                System.out.println("Invalid input. Please enter only text.");
            }
        } while (!isValidTextInput(incidentDetails));



    
        // Create a new GPSLocation object using the input coordinates
        GPSLocation location = new GPSLocation(gpsLocation);
    
        // Create a new Incident object with the gathered information
        Incident incident = new Incident(incidentNumber, reporterName, reporterPhoneNumber,
                date, time, location, incidentDetails);
    
        // Add the new incident to the IncidentManagementSystem
        ims.addIncident(incident);
    
        System.out.println("Incident created successfully!");
    }

    public boolean isValidName(String name) 
    {
        // Basic validation - assumes a name should contain only alphabetic characters
        return name != null && name.matches("[a-zA-Z]+");
    }
    
    public boolean isValidPhoneNumber(String phoneNumber) 
    {
        // Basic validation - assumes a phone number should contain at least 11 digits
        return phoneNumber != null && phoneNumber.matches("\\d{11,}");
    }
    
    public boolean isValidDate(String date) 
    {
        if (date == null || !date.matches("\\d{2}/\\d{2}/\\d{4}")) 
        {
            return false;
        }
        String[] parts = date.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if (year < 1000 || year > 9999) 
        {
            return false;
        }
        if (month < 1 || month > 12) 
        {
            return false;
        }
        int maxDays = 31;
        if (month == 2) 
        {
            if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) 
            {
                maxDays = 29;
            } 
            else 
            {
                maxDays = 28;
            }
        } 
        else if (month == 4 || month == 6 || month == 9 || month == 11) 
        {
            maxDays = 30;
        }
    
        if (day < 1 || day > maxDays) 
        {
            return false;
        }
    
        return true;
    }
    
    
    public boolean isValidTime(String time) 
    {
        // Basic validation - assumes a time should be in the format hh:mm
        return time != null && time.matches("\\d{2}:\\d{2}");
    }
    
    public boolean isValidGPSLocation(String gpsLocation) 
    {
        // Basic validation - assumes a GPS location should be in the format latitude, longitude
        return gpsLocation != null && gpsLocation.matches("-?\\d+(\\.\\d+)?,\\s*-?\\d+(\\.\\d+)?");
    }
    
    

    public void assignResource() 
    {
    System.out.println("Enter the incident number of the incident to assign a resource:");
    int incidentNumber = 0;
    while (true) 
    {
        try 
        {
            incidentNumber = scanner.nextInt();
            scanner.nextLine();
            break;
        } 
        catch (InputMismatchException e) 
        {
            System.out.println("Invalid input. Please enter a valid incident number:");
            scanner.nextLine();
        }
    }

    // Find the incident by incident number
    Incident incident = ims.findIncidentByNumber(incidentNumber);

    if (incident != null) 
    {
        int resourceNumber = 0;
        while (true) 
        {
            try 
            {
                System.out.println("Enter resource number:");
                resourceNumber = scanner.nextInt();
                scanner.nextLine();
                break;
            } 
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input. Please enter a valid resource number:");
                scanner.nextLine();
            }
        }

        System.out.println("Enter resource code:");
        String resourceCode = scanner.nextLine();

        System.out.println("Enter resource description:");
        String resourceDescription = scanner.nextLine();

        int numUnits = 0;
        while (true) 
        {
            try 
            {
                System.out.println("Enter number of resource units:");
                numUnits = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) 
            {
                System.out.println("Invalid input. Please enter a valid number of resource units:");
                scanner.nextLine();
            }
        }

        // Create a new Resource object with the gathered information
        Resource resource = new Resource(resourceNumber, resourceCode, resourceDescription, numUnits);

        // Assign the resource to the incident
        incident.addResource(resource);

        System.out.println("Resource assigned to the incident successfully!");
    } 
    else 
    {
        System.out.println("Incident not found!");
    }
}

    
    public void updateIncident() 
    {
        System.out.println("Enter the incident number of the incident to update:");
        int incidentNumber = scanner.nextInt();
        scanner.nextLine();
    
        // Find the incident by incident number
        Incident incident = ims.findIncidentByNumber(incidentNumber);
    
        if (incident != null) 
        {
            System.out.println("Enter updated reporter's name (or press Enter to skip):");
            String updatedReporterName;
            do 
            {
                updatedReporterName = scanner.nextLine();
                if (!updatedReporterName.isEmpty() && !isValidName(updatedReporterName)) 
                {
                    System.out.println("Invalid reporter name. Please enter a valid name.");
                }
            } 
            while (!updatedReporterName.isEmpty() && !isValidName(updatedReporterName));
    
            if (!updatedReporterName.isEmpty()) 
            {
                incident.setReporterName(updatedReporterName);
            }
    
            System.out.println("Enter updated reporter's phone number (or press Enter to skip):");
            String updatedReporterPhoneNumber;
            do 
            {
                updatedReporterPhoneNumber = scanner.nextLine();
                if (!updatedReporterPhoneNumber.isEmpty() && !isValidPhoneNumber(updatedReporterPhoneNumber)) 
                {
                    System.out.println("Invalid phone number. Please enter a valid phone number.");
                }
            } 
            while (!updatedReporterPhoneNumber.isEmpty() && !isValidPhoneNumber(updatedReporterPhoneNumber));
    
            if (!updatedReporterPhoneNumber.isEmpty()) 
            {
                incident.setReporterPhoneNumber(updatedReporterPhoneNumber);
            }
    
            System.out.println("Enter updated date of the incident (format: dd/mm/yyyy) (or press Enter to skip):");
            String updatedDate;
            // Assuming isValidDate() is a helper function to validate date format
            do 
            {
                updatedDate = scanner.nextLine();
                if (!updatedDate.isEmpty() && !isValidDate(updatedDate)) 
                {
                    System.out.println("Invalid date format. Please enter a date in dd/mm/yyyy format.");
                }
            } 
            while (!updatedDate.isEmpty() && !isValidDate(updatedDate));
    
            if (!updatedDate.isEmpty()) 
            {
                incident.setDate(updatedDate);
            }
    
            System.out.println("Enter updated time of the incident (format: hh:mm) (or press Enter to skip):");
            String updatedTime;
            // Assuming isValidTime() is a helper function to validate time format
            do 
            {
                updatedTime = scanner.nextLine();
                if (!updatedTime.isEmpty() && !isValidTime(updatedTime)) 
                {
                    System.out.println("Invalid time format. Please enter a time in hh:mm format.");
                }
            } 
            while (!updatedTime.isEmpty() && !isValidTime(updatedTime));
    
            if (!updatedTime.isEmpty()) 
            {
                incident.setTime(updatedTime);
            }
    
            System.out.println("Enter updated GPS location (e.g., latitude, longitude) (or press Enter to skip):");
            String updatedGPSLocation = scanner.nextLine();
            if (!updatedGPSLocation.isEmpty()) 
            {
                GPSLocation location = new GPSLocation(updatedGPSLocation);
                incident.setGpsLocation(location);
            }
    
            System.out.println("Enter updated incident details (or press Enter to skip):");
            String updatedIncidentDetails = scanner.nextLine();
            if (!updatedIncidentDetails.isEmpty()) 
            {
                incident.setIncidentDetails(updatedIncidentDetails);
            }
    
            System.out.println("Incident updated successfully!");
        } 
        else 
        {
            System.out.println("Incident not found!");
        }
    }
    

    public void displayIncidents() 
    {
        // Display a list of all incidents with their details
        System.out.println("=== List of Incidents ===");
        for (Incident incident : ims.getAllIncidents()) 
        {
            System.out.println(incident);
        }
    }

    public void run() 
    {
        int choice;

        do 
        {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) 
            {
                case 1:
                    createIncident();
                    break;
                case 2:
                    updateIncident();
                    break;
                case 3:
                    assignResource();
                    break;
                case 4:
                    displayIncidents();
                    break;
                case 5: 
                    saveToCSV();
                    break;
                case 6: 
                    loadFromCSV();
                    break;
                case 0:
                    System.out.println("Exiting Incident Management System...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }

    
}
