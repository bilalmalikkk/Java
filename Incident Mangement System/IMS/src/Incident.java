import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Incident {
    private int incidentNumber;
    private String reporterName;
    private String reporterPhoneNumber;
    private String date;
    private String time;
    private GPSLocation gpsLocation;
    private String incidentDetails;
    private boolean ongoing;
    private List<Resource> resources;

    public Incident(int incidentNumber, String reporterName, String reporterPhoneNumber, String date, String time,
                    GPSLocation gpsLocation, String incidentDetails) {
        this.incidentNumber = incidentNumber;
        setReporterName(reporterName);
        setReporterPhoneNumber(reporterPhoneNumber);
        this.date = date;
        this.time = time;
        this.gpsLocation = gpsLocation;
        this.incidentDetails = incidentDetails;
        this.ongoing = true;
        this.resources = new ArrayList<>();
    }

    //constructor to initialize from CSV data
    public Incident(String[] csvData) {
        this.incidentNumber = Integer.parseInt(csvData[0].trim());
        setReporterName(csvData[1].trim());
        setReporterPhoneNumber(csvData[2].trim());
        this.date = csvData[3].trim();
        this.time = csvData[4].trim();
        this.gpsLocation = new GPSLocation(csvData[5].trim());
        this.incidentDetails = csvData[6].trim();
        this.ongoing = Boolean.parseBoolean(csvData[7].trim());
        this.resources = new ArrayList<>();
    }

    // method to write incident data to a CSV file
    public void writeToCSV(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            StringBuilder csvString = new StringBuilder();
            csvString.append(incidentNumber).append(", ")
                    .append(reporterName).append(", ")
                    .append(reporterPhoneNumber).append(", ")
                    .append(date).append(", ")
                    .append(time).append(", ")
                    .append(gpsLocation).append(", ")
                    .append(incidentDetails).append(", ")
                    .append(ongoing).append("\n");

            writer.write(csvString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to parse CSV data and create an Incident instance
    public static Incident fromCSVString(String csvString) {
        String[] csvData = csvString.split(",");
        return new Incident(csvData);
    }


    // Getters and setters for all attributes
    public void setReporterName(String reporterName) {
            this.reporterName = reporterName;
        
    }

    public void setReporterPhoneNumber(String reporterPhoneNumber) {
            this.reporterPhoneNumber = reporterPhoneNumber;
    }

    public void setIncidentNumber(int incidentNumber) {
        if (incidentNumber > 0) {
            this.incidentNumber = incidentNumber;
        } else {
            System.out.println("Invalid incident number. Please enter a positive number.");
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setGpsLocation(GPSLocation gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public void setIncidentDetails(String incidentDetails) {
        this.incidentDetails = incidentDetails;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public int getIncidentNumber() {
        return incidentNumber;
    }

    public String getReporterName() {
        return reporterName;
    }

    public String getReporterPhoneNumber() {
        return reporterPhoneNumber;
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public String toCSVString() {
        StringBuilder csvString = new StringBuilder();
        csvString.append(incidentNumber).append(", ")
                .append(reporterName).append(", ")
                .append(reporterPhoneNumber).append(", ")
                .append(date).append(", ")
                .append(time).append(", ")
                .append(gpsLocation).append(", ")
                .append(incidentDetails).append(", ")
                .append(ongoing);

        return csvString.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Incident Number: ").append(incidentNumber).append("\n");
        sb.append("Reported by: ").append(reporterName).append("\n");
        sb.append("Date: ").append(date).append("\tTime: ").append(time).append("\n");
        sb.append("Location: ").append(gpsLocation).append("\n");
        sb.append("Incident Details: ").append(incidentDetails).append("\n");

        if (!resources.isEmpty()) {
            sb.append("Resources:\n");
            for (Resource resource : resources) {
                sb.append(resource).append("\n");
            }
        }

        sb.append("Ongoing: ").append(ongoing ? "YES" : "NO").append("\n");

        return sb.toString();
    }
}
