import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Write a description of class RecordingSystem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecordingSystem
{
    private UserInput ui = new UserInput();
    private FileOutputStream fop;
    private BookingHistory[] history;
    private int totalNumberOfBooked;
    private Seat[][] seats;

    /**  
     * constructor
     *  @param <history> 
     *  @param <seats>
     *  @param <totalNumberOfBooked>
     *  @param <fileName>
     */
    public RecordingSystem(BookingHistory[] history, Seat[][] seats, int totalNumberOfBooked, String fileName)
    {
        this.history = history;
        this.totalNumberOfBooked = totalNumberOfBooked;
        this.seats = seats;
        recordOriginalFile(fileName);
        recordNewLog();
    }

    /**  
     *  This method is to override the original file
     *  @param <fileName> 
     */
    private void recordOriginalFile(String fileName)
    {
        try
        {
            fop = new FileOutputStream(fileName);
            writeALLBookingHistoryIntoFile();
        }
        catch(IOException e)
        {
            throw new RuntimeException("Fail to wirte file!");
        }
        finally
        {
            try
            {
                if(fop != null) fop.close();
            }
            catch(IOException e)
            {
                ui.sopln("Fail to close file!");
            }
        }
    }

    /**  
     *  This method is to write into new report file
     */
    private void recordNewLog()
    {
        try{
            fop = new FileOutputStream("Record.txt");
            writeHeaderInReport();
            writeToNewFile();
        }
        catch(IOException e)
        {
            throw new RuntimeException("Fail to write file!");
        }
        finally
        {
            try
            {
                if(fop != null) fop.close();
            }
            catch(IOException e)
            {
                ui.sopln("Fail to close the file!");
            }
        }
    }

    /**  
     *  This method is to write header
     */
    private void writeHeaderInReport()throws IOException
    {
        writeEachLine("--> The seats' reservations on this plane");
        writeEachLine("--> Total number of reservations: " + totalNumberOfBooked);
        writeEachLine("\t  " + String.format("%-20s%-22s%7s%10s%18s%14s", 
        "-TIME OF BOOKING-", "         -NAME-", "       -AGE-", "-FOOD-", "-ROW-", "-COLUMN-"));
    }

    /**  
     *  This method is to write content into original file
     */
    private void writeALLBookingHistoryIntoFile()throws IOException
    {
        writeEachLine(seats.length); //write row number
        writeEachSeatIntoFile(); //write seats
        writeEachLine(totalNumberOfBooked); //write total number of booking
        writeBookingHistoryIntoFileOfAllClients(); //write simple booking information
    }

    /**  
     *  This method is to write content into new report file from history
     */
    private void writeToNewFile()throws IOException
    {
        for(BookingHistory record: history)
        {
            if(isEmpty(record)) break;
            else writeBookingHistoryIntoReport(record);
        }
    }

    /**  
     *  This method is to This method is to override content from history
     */
    private void writeBookingHistoryIntoFileOfAllClients()throws IOException
    {
        for(BookingHistory record: history)
        {
            if(isEmpty(record)) break;
            else writeBookingHistoryOfOneClient(record);
        }
    }

    /**  
     *  This method is to write single information
     *  @param <history> 
     */
    private void writeBookingHistoryOfOneClient(BookingHistory history)throws IOException
    {
        writeSameLine(history.getPerson());
        writeSameLine(" " + history.getFood());
        writeSameLine(" " + history.getCoordinateOfSeats()[0] + " " + history.getCoordinateOfSeats()[1]);
        writeEachLine(" " + history.getTimeStamp().trim());
    }

    /**  
     *  This method is to write single information
     *  @param <history> 
     */
    private void writeBookingHistoryIntoReport(BookingHistory history)throws IOException
    {
        writeEachLine(history); 
    }

    /**  
     *  This method is to write the seats
     */
    private void writeEachSeatIntoFile()throws IOException
    {
        for(int i = 0; i < seats.length; i++)
        {
            for(int j = 0; j < seats[i].length; j++)
            {
                fop.write(seats[i][j].getSingleSeat());
            }
            returnLine();
        }
    }

    /**  
     *  This method is to wirte everything at same line
     *  @param <obj> 
     */
    private void writeSameLine(Object content)throws IOException
    {
        fop.write(content.toString().getBytes());
    }

    /**  
     *  This method is to wirte everything at each line
     *  @param <obj> 
     */
    private void writeEachLine(Object content)throws IOException
    {
        writeSameLine(content);
        returnLine();
    }

    /**  
     *  This method is to return a line while writing information
     */
    private void returnLine()throws IOException
    {
        final String NEW_LINE = "\r\n";
        fop.write(NEW_LINE.getBytes());
    }

    /**  
     *  This method is to check if there is information in the history
     *  @param <history> 
     *  @return <true if there is information in the history>
     */
    private boolean isEmpty(BookingHistory history){return history == null;}
}