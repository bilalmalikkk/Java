public class GPSLocation 
{
    private String locationInfo;

    public GPSLocation(String locationInfo) 
    {
        this.locationInfo = locationInfo;
    }

    @Override
    public String toString() 
    {
        return locationInfo;
    }
}
