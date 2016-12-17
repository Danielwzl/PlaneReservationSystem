import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This is the main class to start the program, this program provides air plane seats booking, cancelling and modify function
 * The client will input their name and age as identity into system, and choose seat and food
 * How this program run: 
 - 1. main for reading file, file goes into plane 
 - 2. plane read the file and create seat array 
 - 3. array and some related information goes into BookingSystem
 - 4. BookingSystem computing the logic part which are booking, cancelling and modify
 - 5. BookingHistory accepts all information from system and store inside of array
 - 6. After client quit, then transfers history into RecordingSystem to write into database and report
 * @author  Zilong Wang 
 * Last Modified: <10-19-2015> - <adding comments> <Zilong Wang>
 * @version 1.0
 */
public class Main
{
    /**  
     *  This method is to start the program
     *  @param <args> <just wait input, but in this program, it is not useful>
     */
    public static void main(String[] args)
    {
        Menu menu = new Menu();    
        menu.welcome(); //print welcome 
        String name = menu.getUi().chooseInfo("► Please enter the plane you need get on (without .txt): ");
        try
        {
            new Plane(new FileInputStream(name += ".txt"), menu, name); //get full name of file in order to override it
        }
        catch(FileNotFoundException e)
        {
            menu.getUi().sopln("Your plane is not on the list");
        }
    }
}