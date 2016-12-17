import java.util.Scanner;
import java.io.FileInputStream;

/**
 * This class is for reading the file and put seat object into 2D array
 * This class also read updated information from the data file and store them into history array, passing into system
 * @author  Zilong Wang 
 * @version 1.0 
 * Last Modified: <10-19-2015> - <adding comments> <Zilong Wang>                           
 */
public class Plane
{
    private Scanner scan;
    private Seat[][] seats;
    private int maxLengthOfCol, index, totalSeats, totalBooked;
    private BookingHistory[] history;

    /**  
     *  constructor
     *  @param <file> 
     *  @param <menu: pass to system>
     *  @param <fileName: pass to recording system>
     */
    public Plane(FileInputStream file, Menu menu, String fileName)
    {
        scan = new Scanner(file);
        constructSeats(); //read the seats in the file
        updateNewDataInfomationOnPlane(); //keep reading the file if original file has been updated
        new BookSystem(menu, seats, maxLengthOfCol, fileName, history, totalBooked);
    }

    /**  
     *  This method is to put seats all together into 2D array
     */
    private void constructSeats()
    {
        seats = new Seat[showRow()][]; //the length of the second dimension could be different
        String seatsOneRow = null;
        index = 0; //reset it to 0, re-use it as an index of seat in the 2D array
        do{
            seatsOneRow = oneColOfseats(); //scan each line of seats from the file
            installAllRows(seatsOneRow, lengthOfCol(seatsOneRow.length())); //make each seats into 2D array
        }while(!isReachTheLastSeat() && hasNextRowSeats()); //when it is not reaching the last seat and must have seats, it will read seats 
    }

    /**  
     *  This method is to get the column has most seats, return number back
     *  The number will be passed into system and menu class for printing the frame of seats
     *  @param <file> 
     *  @param <menu: pass to system>
     *  @param <fileName: pass to recording system>
     *  @return <length: just return the parameter while get the max length>
     */
    private int lengthOfCol(int length)
    {
        maxLengthOfCol = (maxLengthOfCol < length) ? length: maxLengthOfCol;
        return length;
    }

    /**  
     *  This method is to put one row seats into 2D array
     *  Becasue the number of seats in one row are different
     *  @param <seatsOneRow: a String of seats in one row from the file> 
     *  @param <numOfSeatsOneRow: different number of seats in one row>
     */
    private void installAllRows(String seatsOneRow, int numOfSeatsOneRow)
    {
        Seat[] oneRow = new Seat[numOfSeatsOneRow]; //creat 1D array with different length
        seats[index++] = installSeatsOnEachRow(seatsOneRow, oneRow); //put seats of one row into 2D
    }

    /**  
     *  This method is to put single seat into each 1D array
     *  Becasue the number of seats in one row are different
     *  @param <seatsOneRow: a String of seats in one row from the file> 
     *  @param <oneRow: a 1D array has one row seats>
     *  @return <oneRow: the array contains one row seats, each elements in this 1D array has pointed to seat objects>
     */
    private Seat[] installSeatsOnEachRow(String seatsOneRow, Seat[] oneRow)
    {
        for(int i = 0; i < oneRow.length; i++)
        {
            oneRow[i] = new Seat(oneSeat(seatsOneRow, i)); //creat new Seat objects as the single element in the array
            totalSeats++; //get how many seats in total for generating the history array 
        }

        return oneRow;
    }

    /**  
     *  This method is to put updated information into history array
     *  In order to start the system at the time end last time
     *  If there is no record, then history array will contain null, the system will not read it unless new booking coming
     *  And the dimension of history array will be the total seats in the plane
     */
    private void updateNewDataInfomationOnPlane()
    {
        history = new BookingHistory[totalSeats]; //history array must be intialized with the size of exsiting number of seats
        index = 0; //reset it to 0, re-use it as an index of seat in the 1D history array
        final int DIMENSION = 2;
        Person person = null;
        Food food = null;
        int[] coordinateOfSeats = null;
        if(isReachTheLastSeat()) //if reach the last seat, then can scan the update information, the one behind last seat is the number of seats' booking
        {
            totalBooked = scan.nextInt();
            while(scan.hasNext() && totalBooked > 0)
            {
                person = updatePersonInformation();
                food = updateFoodOrder();
                coordinateOfSeats = new int[DIMENSION];
                updateSeatBooked(coordinateOfSeats);
                history[index++] = new BookingHistory(food, person, coordinateOfSeats, scan.nextLine()); //last one is String time
            }
        }
    }

    /**  
     *  This method is to scan the persons' name, age and return person object
     *  @return <new Person(name, age)>
     */
    private Person updatePersonInformation()
    {
        String name = scan.next();
        while(!scan.hasNextInt()) //if it is not reaching the age which is number
        {
            name += (" " + scan.next()); //scan the name, in case people has very long name
        }

        return new Person(name, scan.nextInt()); //new Person(name, age)
    }

    /**  
     *  This method is to scan the foods' name
     *  @return <new Food(name)>
     */
    private Food updateFoodOrder()
    {
        String food = scan.next();
        while(!scan.hasNextInt()) //if it is not reaching the row which is number
        {
            food += (" " + scan.next()); //scan the food name, in case food has very long name
        }

        return new Food(food);
    }

    /**  
     *  This method is to scan the row and col number, elements in the array of coordinateOfSeats will change
     *  @param <coordinateOfSeats>
     */
    private void updateSeatBooked(int[] coordinateOfSeats)
    {
        coordinateOfSeats[0] = scan.nextInt();
        coordinateOfSeats[1] = scan.nextInt();
    }

    /**  
     *  This method is to scan total row of plane has
     *  @return <index: just need return a value for another method use, avoid multiple return>
     */
    private int showRow()
    {
        if(scan.hasNextInt()) index = scan.nextInt();
        return index;
    }
    
    /**  
     *  This method is to split one row seats to single seat
     *  @param <oneColOfSeats: String value of one row seats>
     *  @param <indexOfSeat: index of the char in the string, it will be increasing in the loop>
     *  @return <char type value: the letter of single seat>
     */
    private char oneSeat(String oneColOfSeats, int indexOfSeat){return oneColOfSeats.charAt(indexOfSeat);}
    
    /**  
     *  This method is to scan one row seats
     *  @return <String type value: one row seats>
     */
    private String oneColOfseats(){return scan.next().toUpperCase();}
    
    /**  
     *  This method is to make sure reach the last row of seats
     *  @return <boolean type value: if next token is number>
     */
    private boolean isReachTheLastSeat(){return scan.hasNextInt();}
    
    /**  
     *  This method is to make sure to have next row of seats
     *  @return <boolean type value: if has next token>
     */
    private boolean hasNextRowSeats(){return scan.hasNext();}
}
