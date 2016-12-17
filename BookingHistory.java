/**
 * This class is for holding the booking information of each person
 * @author  Zilong Wang 
 * @version 1.0 
 * Last Modified: <10-19-2015> - <adding comments> <Zilong Wang>   
                  <11-02-2015> - <adding format function in the toString()> <Zilong Wang>
 */
public class BookingHistory
{
    private int[] coordinateOfSeats;
    private Food food;
    private Person person;
    private String timeStamp;

    /**  
     *  constructor
     *  @param <file> 
     *  @param <food>
     *  @param <person>
     *  @param <coordinateOfSeats>
     *  @param <timeStamp>
     */
    public BookingHistory(Food food, Person person, int[] coordinateOfSeats, String timeStamp)
    {
        this.coordinateOfSeats = coordinateOfSeats;
        this.food = food;
        this.person = person;
        this.timeStamp = timeStamp;
    }

    /**  
     *  setter
     *  @param <coordinateOfSeats> 
     */
    public void setCoordinateOfSeats(int[] coordinateOfSeats){this.coordinateOfSeats = coordinateOfSeats;}

    /**  
     *  getter
     *  @return <coordinateOfSeats> 
     */
    public int[] getCoordinateOfSeats(){return coordinateOfSeats;}

    /**  
     *  setter
     *  @param <person>
     */
    public void setPerson(Person person){this.person = person;}

    /**  
     *  getter
     *  @return <person> 
     */
    public Person getPerson(){return person;}

    /**  
     *  setter
     *  @param <food> 
     */
    public void setFood(Food food){this.food = food;}

    /**  
     *  getter
     *  @return <food> 
     */
    public Food getFood(){return food;}

    /**  
     *  setter
     *  @param <timeStamp> 
     */
    public void setTimeStamp(String timeStamp){this.timeStamp = timeStamp;}

    /**  
     *  getter
     *  @return <timeStamp> 
     */
    public String getTimeStamp(){return timeStamp;}

    /**  
     *  this method is to check seat is occupied
     *  by checking seats is the same one with the seat in the histoty
     *  @param <coordinateOfSeats>
     *  @return <true if two seat are the same one> 
     */
    public boolean isSameSeatInHistory(int[] coordinateOfSeats)
    {
        return this.coordinateOfSeats[0] == coordinateOfSeats[0] 
        && this.coordinateOfSeats[1] == coordinateOfSeats[1];
    }

    /**  
     *  this method is to present the information of booking for one person
     *  @return <String type that shows the detail information of booking> 
     */
    public String toString()
    {
        int row = coordinateOfSeats[0], col = coordinateOfSeats[1];
        return "\t" + String.format("%-28s%-24s%-7s%-22s%-12s%-14s", 
                      timeStamp.trim(), person.getName(), person.getAge(), food, ++row, ++col);
    }
}
