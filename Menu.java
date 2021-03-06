/**
 * This class is to create a menu and ask user to make choice
 * @author  Zilong Wang 
 * @version 1.0 
 * Last Modified: <10-19-2015> - <adding comments> <Zilong Wang>                          
 *                <10-28-2015> - <update the ending menu> <Zilong Wang>
 */
public class Menu
{
    private UserInput ui;
    private final int MAX_OPTION = 6; //max option on the menu

    /**  
     * constructor
     */
    public Menu(){if(ui == null) ui = new UserInput();}

    /**  
     *  This method is to ask user enter row and column number
     *  @param <coordinateOfSeats> 
     *  @param <message: could be use for modify, cancel and book>
     */
    public void chooseSeats(int[] coordinateOfSeats, String message)
    {
        coordinateOfSeats[0] = transferUserInput(ui.chooseNum("1.Please " + message + " seat by telling row: "));
        coordinateOfSeats[1] = transferUserInput(ui.chooseNum("2.Please " + message + " seat by telling col: "));
    }

    /**  
     *  This method is to convert number input to index in the any of array
     *  @param <num>
     *  @return <num - 1>
     */
    private int transferUserInput(int num){return num - 1;}

    /**  
     *  This method is to print food menu
     *  @param <foodList> 
     */
    private void foodMenu(Food[] foodList)
    {
        int index = 1;
        String div = dividerMaker(5); //**********
        ui.sopln(div + "FOOD MENU" + div);
        for(Food food: foodList)
        {
            ui.sopln(" " + index++ + ". " + food.getFoodName());
        }
        ui.sopln(dividerMaker(19)); //********
    }

    /**  
     *  This method is to print confirmation page
     *  @param <history> 
     */
    public void confirmationPage(BookingHistory history)
    {
        ui.sopln(dividerMaker(18));
        ui.sopln("Order confirmation");
        ui.sopln(dividerMaker(18));
        ui.sopln("Name: " + history.getPerson().getName());
        ui.sopln("Age: " + history.getPerson().getAge());
        ui.sopln("Seat Row: " + (history.getCoordinateOfSeats()[0] + 1));
        ui.sopln("Seat Column: " + (history.getCoordinateOfSeats()[1] + 1));
        ui.sopln("Food: " + history.getFood().getFoodName());
        ui.sopln("Time: " + history.getTimeStamp());
        ui.sopln(dividerMaker(18));
    }

    /**  
     *  This method is to print frames of the seats map
     *  @param <row> 
     *  @param <col>
     *  @param <maxLengthOfCol>
     */
    public void seatFrameForBothSide(int row, int col, int maxLengthOfCol)
    {
        if(isShowTopFrame(row, col)) //only have frame on the very top
        {
            printTopFrame(maxLengthOfCol);
        }
        printSideFrame(row, col);
    }

    /**  
     *  This method is to print frame for left side of seats map
     *  @param <row> 
     *  @param <col>
     */
    private void printSideFrame(int row, int col)
    {
        if(col == 0) ui.sop(intend(Integer.toString(row + 1)));
    }

    /**  
     *  This method is working as the print job for top frame
     *  @param <maxLengthOfCol> 
     */
    private void printTopFrame(int maxLengthOfCol)
    {
        String temp = "  ";
        for(int k = 0; k < maxLengthOfCol; k++)
        {
            temp += (k + 1 + "   ");
        }
        ui.sopln("► This is the current availability of seats on the plane\n\n" + intend(temp));
    }

    /**  
     *  This method is ask user enter the number on the food menu to choose food. Input must be number
     *  @param <foodList>
     *  @return <index of food order>
     */
    public int chooseFood(Food[] foodList)
    {
        foodMenu(foodList);
        return transferUserInput(misInput(ui.chooseNum("► Please choose index of food you like: "), foodList.length));
    }

    /**  
     *  This method is to ask user to enter their name and age, age must be number, name cannot be empty
     *  @return <new person>
     */
    public Person enterPersonalInfo()
    {
        return new Person(ui.chooseInfo("Client's full name: "), ui.chooseNum("Client's age: "));
    }

    /**  
     *  This method is to ask user to input their choice on main menu, input must exist and be number
     *  @return <number of choice>
     */
    public int enterChoiceFromMenu(){return misInput(ui.chooseNum(userChoiceMenu()), MAX_OPTION);}

    /**  
     *  This method is to ask user re-enter if they enter a number which is not existing on the menu
     *  @param <maxOption: the last number of option> 
     *  @param <input: from user>
     *  @return <correct input of choice>
     */
    private int misInput(int input, int maxOption)
    {
        while(input > maxOption)
        {
            input = ui.chooseNum("► Not on the menu, please re-enter: ");
        }
        return input;
    }

    /**  
     *  This method is to return a menu
     *  @return <String menu>
     */
    private String userChoiceMenu()
    {
        String divider = dividerMaker(5);

        return divider + "Top Menu" + divider 
        + "\n1. Book seats\n2. Cancel seats\n3. Modify Seats\n4. Search Seats\n5. Display Reservation\n6. Exit system\n"
        + dividerMaker(18) + "\n► Please enter your choice: ";
    }

    /**
     *  This method is to display information for modify
     *  @param <history>
     */
    public void displayDetailedBookingInformation(BookingHistory history)
    {
        ui.sopln("\n" + intend("----The detail of the reservation you selected----")); 
        printHeaderForSummary();
        ui.sopln(history);
        ui.sopln("");
    }

    /**
     *  This is the method to ask user to enter their preference of seat
     *  @return <M, A or W>
     */
    public char askUserEnterPreference()
    {
        return ui.chooseInfo("► Please enter your choice(M/A/W): ").toUpperCase().charAt(0);
    }

    /**
     *  This method is to confirm if user wants to do somthing
     *  @param <message>
     *  @return <true if user enter y>
     */
    public boolean confirmInfoToModify(String message){return ui.yesOrNo(message);}

    /**  
     *  This method is to intend the information
     *  @param <info> 
     *  @return <String has been formatted>
     */
    public String intend(String info){return "\t\t\t" + info;}

    /**  
     *  This method is to create a divider
     *  @param <numberOfDiver>
     *  @return <divider>
     */
    private String dividerMaker(int numberOfDiver)
    {
        String divider = "";
        for(int i = 0; i < numberOfDiver; i++)
        {
            divider += "*";
        }
        return divider;
    }

    /**  
     *  This method is to print welcome header
     */
    public void welcome()
    {
        ui.sopln(dividerMaker(108));
        ui.sopln("   ___   _______  __   _____  ______  ___  ____  ____  __ _______  _______  ______  __________________  ___");
        ui.sopln("  / _ | /  _/ _ \\/ /  /  _/ |/ / __/ / _ )/ __ \\/ __ \\/ //_/  _/ |/ / ___/ / __/\\ \\/ / __/_  __/ __/  |/  /");
        ui.sopln(" / __ |_/ // , _/ /___/ //    / _/  / _  / /_/ / /_/ / ,< _/ //    / (_ / _\\ \\   \\  /\\ \\  / / / _// /|_/ /");
        ui.sopln("/_/ |_/___/_/|_/____/___/_/|_/___/ /____/\\____/\\____/_/|_/___/_/|_/\\___/ /___/   /_/___/ /_/ /___/_/  /_/");
        ui.sopln("");
        ui.sopln(dividerMaker(108));
    }

    /**  
     *  This method is to print explaination of seat type
     */
    public void showSeatsExample()
    {
        ui.sopln("\n" + intend(dividerMaker(19)));
        ui.sopln(intend("'O': Occupied Seat"));
        ui.sopln(intend("'M': Middle Seat"));
        ui.sopln(intend("'W': Window Seat"));
        ui.sopln(intend("'A': Aisle Seat"));
        ui.sopln(intend(dividerMaker(19)));
    }

    /**  
     *  This method is to print ending page
     *  @param <numberOfBooking> 
     *  @param <history>
     *  @param <counter> 
     *  @param <foodList>
     */
    public void ending(int numberOfBooking, BookingHistory[] history, int[] counter, Food[] foodList)
    {
        ui.sopln("\n\t\t\t\t❀ The report: ❀");
        ui.sopln("\t\tThe number of booking: " + numberOfBooking);
        foodOrderSummary(counter, foodList);
        ui.sopln("");
        seatBookingSummary(history);
    }

    /**  
     *  This method is to print amount of each food order on the ending page
     *  @param <foodList> 
     *  @param <counter>
     */
    private void foodOrderSummary(int[] counter, Food[] foodList)
    {
        ui.sopln("\n\t\tThe number of each food order:");
        for(int i = 0; i < counter.length; i++)
        {
            ui.sopln("\t\t-" + String.format("%-14s" ,foodList[i] + ":") + " " + counter[i]);
        }
    }

    /**  
     *  This method is to print detailed booking information on the screen
     *  @param <history> 
     */
    public void seatBookingSummary(BookingHistory[] history)
    {
        printHeaderForSummary();
        for(BookingHistory record: history)
            if(record != null) ui.sopln(record);
    }

    /**  
     *  This method is to print header for booking information
     */
    public void printHeaderForSummary()
    {
        ui.sopln("\t  " + String.format("%-20s%-22s%7s%10s%18s%14s", 
        "-TIME OF BOOKING-", "         -NAME-", "       -AGE-", "-FOOD-", "-ROW-", "-COLUMN-")); 
    }

    /**  
     *  This method is to print a seat which being booked
     *  @param <occupiedSeat> 
     */
    public void presentOccupiedSeat(Seat occupiedSeat){ui.sop(occupiedSeat);}

    /**  
     *  This method is to print a single seat on screen
     *  @param <seats> 
     *  @param <row>
     *  param <col>
     */
    public void presentSingleSeat(Seat[][] seats, int row, int col){ui.sop(seats[row][col]);}

    /**  
     *  This method is to pause the system
     */
    public void pause(String message){ui.pause(message);}

    /**  
     *  This method is to print empty space instead of seat on the screen
     */
    public void printEmptySpace() {ui.sop("    "); }

    /**  
     *  This method is to flush the screen
     */
    public void flush(){ui.sop("\f");}

    /**  
     *  This method is to return line on the screen
     */
    public void newLine(){ui.sopln("\n");}

    /**  
     *  This method is to print information showing user if seats are full booked
     */
    public void showBookFull(){ui.sopln("Full, sorry!");}

    /**  
     *  getter
     *  @return <ui>
     */
    public UserInput getUi(){return ui;}

    /**  
     *  This method is to check if it is at the top of seat map
     *  @param <row> 
     *  @param <col>
     *  @return <true if it is at the top of seat map>
     */
    private boolean isShowTopFrame(int row, int col){return row == 0 && col == 0;}

    /**  
     *  This method is to print warnning
     *  @param <messagee> 
     */
    public void warnning(String message){ui.pause(message + " Please hit 'enter' to try again -->");}
}