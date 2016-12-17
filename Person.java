/**
 * This class is for creating a person
 * @author  Zilong Wang 
 * @version 1.0 
 * Last Modified: <10-19-2015> - <adding comments> <Zilong Wang>                          
 */
public class Person
{
    private String name;
    private int age;

    /**  
     *  constructor
     *  @param <name> 
     *  @param <age>
     */
    public Person(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    /**  
     *  getter
     *  @return <name>
     */
    public String getName(){return name;}

    /**  
     *  getter
     *  @return <age>
     */
    public int getAge(){return age;}

    /**  
     *  this method is to check if person is same people with target one
     *  @param <person>
     *  @return <true if they are same>
     */
    public boolean isSame(Person person)
    {
        return this.name.equals(person.name) && this.age == person.age; //make sure the person want to cancel is the same one booked
    }

    /**  
     *  present a person with name and age
     *  @return <String type infomation of a person>
     */
    public String toString(){return name + " " + age;}
}
