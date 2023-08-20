public class Main 
{
    public static void main(String[] args) 
    {
        IncidentManagementSystem ims = new IncidentManagementSystem();
        UserInterface ui = new UserInterface(ims);
        ui.run();
    }
}
