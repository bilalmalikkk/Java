#  Incident Management System-IMS
The Incident Management System-IMS is a software application that helps efficiently manage and track incidents reported by users. This system provides a user-friendly interface to report incidents, assign resources, update incident details, and display incident information. It is designed to streamline the incident management process, making it easier for organizations to handle and resolve incidents effectively.

# Features

Create Incident: 
Users can create new incidents by providing incident details, reporter information, date, time, and GPS location.
Assign Resource: Users can assign resources to specific incidents to facilitate effective incident resolution.
Update Incident: 
Users can update incident details, reporter information, date, time, GPS location, and incident descriptions as needed.
Display Incidents: 
The system allows users to view a list of all incidents and their details.
Save and Load from CSV: Users can save incident and resource data to a CSV file and load it back into the system.

## How to Use

Clone the repository to your local machine.
Compile the Java files using a Java compiler:

javac Main.java
javac Incident.java
javac GPSLocation.java
javac Resource.java
javac UserInterface.java
javac IncidentManagementSystem.java

### Run the Main class:

java Main

The IMS will display the main menu with different options to manage incidents.
Choose the desired option from the menu to perform specific tasks, such as creating incidents, assigning resources, updating incident details, displaying incidents, and saving/loading incidents from CSV files.
Follow the prompts to input the required information for each task.

### Input Validation

The IMS includes input validation for various fields, such as reporter's name, phone number, date, time, and GPS location. Users will be prompted to re-enter invalid inputs until valid data is provided.

### Testing

The system undergoes rigorous testing to ensure reliability and functionality. The test plan, test data, and expected results are documented to verify that the system works as intended.

### CSV Data

The system allows users to save incident and resource data to CSV files and load them back into the system. The CSV format follows the comma-separated values standard for easy integration with other applications.

### Dependencies:

The Incident Management System is built using Java programming language and does not require any external dependencies beyond the standard Java libraries.

