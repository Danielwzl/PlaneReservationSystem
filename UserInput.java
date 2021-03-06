import java.util.Scanner;
/**
 * This is ask user to enter their option
 * @author  Zilong Wang 
 * Last Modified: <10-19-2015> - <adding comments> <Zilong Wang>
 * @version 1.0
 */
public class UserInput
{
    private Scanner scan; //share scanner with each UserInput object
    /**  
     *  constructor
     */
    public UserInput()
    {
        if(scan == null) scan = new Scanner(System.in); //only creat one object
    }

    /**  
     *  This method is to ask user input something
     *  @param <message>
     *  @return <String type value: A line of user input>
     */
    public String chooseInfo(String message)
    {
        sop(message); 
        String info = scan.nextLine();
        while(info.length() == 0) //avoid enter "ENTER"
        {
            sop("Cannot be empty, please re-enter: ");
            info = scan.nextLine();
        }
        
        return info;
    }

    /**  
     *  This method is to ask user input something
     *  @param <message>
     *  @return <Integer type value>
     */
    public int chooseNum(String message)
    {
        sop(message);
        String num = scan.nextLine();
        //Check if input is number by check String input, and then casting it to number if it passes
        while(!num.matches("[0-9]+"))
        {
            sop("Wrong type! Please enter number:");
            num = scan.nextLine();
        }

        return Integer.valueOf(num); //casting to integer
    }

    /**
     *  This method is to ask user input y or n
     *  @return <true if enter y>
     */
    public boolean yesOrNo(String message)
    {
        sop(message + "(y/n)? ");
        String answer = scan.nextLine();
        while(!answer.equalsIgnoreCase("y") 
           && !answer.equalsIgnoreCase("n"))
        {
            sop("Please only enter y or n: ");
            answer = scan.nextLine();
        }
        
        if(answer.equalsIgnoreCase("y")) return true;
        return false;
    }

    /**  
     *  This method is to pause the program
     *  @param <message>
     */
    public void pause(String message)
    {
        sop(message);
        scan.nextLine(); //pause system before they hit "enter"
    }

    /**  
     *  This method is to print something at same line
     *  @param <obj: any type of object>
     */
    public void sop(Object obj)
    {
        System.out.print(obj);
    }

    /**  
     *  This method is to print something at different line
     *  @param <obj: any type of object>
     */
    public void sopln(Object obj)
    {
        System.out.println(obj);
    }
}