/**
 * This class is the blue print of seat 
 * @author  Zilong Wang 
 * @version 1.0 
 * Last Modified: <10-19-2015> - <adding comments> <Zilong Wang>                          
 */
public class Seat
{
    private char singleSeat;

    /**  
     *  constructor
     *  @param <singleSeat>
     */
    public Seat(char singleSeat){this.singleSeat = singleSeat;} 

    /**  
     *  getter
     *  @return <singleSeat>
     */
    public char getSingleSeat(){return singleSeat;}
    
    /**
     *  This method is to check if seat types are same
     *  @param <seat>
     *  @return <ture if two seats' type are same>
     */
    public boolean isSameTypeOfSeat(char seat)
    {
        return this.singleSeat == seat;
    }

    /**  
     *  present a single seat
     *  @return <|A|, |W|, |O| or |M|>
     */
    public String toString(){return "|" + singleSeat + "| ";}
}
