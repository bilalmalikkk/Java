public class Resource 
{
    private int resourceNumber;
    private String resourceCode;
    private String resourceDescription;
    private int numUnits;

    public Resource(int resourceNumber, String resourceCode, String resourceDescription, int numUnits) 
    {
        this.resourceNumber = resourceNumber;
        this.resourceCode = resourceCode;
        this.resourceDescription = resourceDescription;
        this.numUnits = numUnits;
    }

    public Resource(String[] csvData) 
    {
        this.resourceNumber = Integer.parseInt(csvData[0].trim());
        this.resourceCode = csvData[1].trim();
        this.resourceDescription = csvData[2].trim();
        this.numUnits = Integer.parseInt(csvData[3].trim());
    }

    //method to parse CSV data and create a Resource instance
    public static Resource fromCSVString(String csvString) 
    {
        String[] csvData = csvString.split(",");
        return new Resource(csvData);
    }
    

    // Getters and setters for all attributes
    public int getResourceNumber() 
    {
        return resourceNumber;
    }

    public String getResourceCode() 
    {
        return resourceCode;
    }

    public String getResourceDescription() 
    {
        return resourceDescription;
    }

    public int getNumUnits() 
    {
        return numUnits;
    }


    public String toCSVString() 
    {
        StringBuilder csvString = new StringBuilder();
        csvString.append(resourceNumber).append(", ")
                .append(resourceCode).append(", ")
                .append(resourceDescription).append(", ")
                .append(numUnits);
    
        return csvString.toString();
    }


    @Override
    public String toString() 
    {
        return "Resource Number: " + resourceNumber +
                ", Resource Code: " + resourceCode +
                ", Description: " + resourceDescription +
                ", Number of Units: " + numUnits;
    }
}
