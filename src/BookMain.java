/**
 *
 * @author Jen Balsi
 */
import java.util.Scanner;

public class BookMain extends Application{
    
    
    
    public static void main(String[] args){
        
        Scanner choice = new Scanner(System.in);
        System.out.println("What would you like to do? "
                         + "\n 1. Find books older than date."
                          + "\n 2. Find books newer than date."
                            + "\n 3. Find books with title like."
                              + "\n 4. Find books with author like.");
        readChoice(choice);
        
        
        
    }//end main
    
    
    //takes in choice and sends to selected method
    public static void readChoice(int x){
        
        int x = reader.nextInt();
         
        switch(x){
            case 1: 
                System.out.println("find books older than date");
            case 2:
                System.out.println("find books newer than date");
            case 3:
                System.out.println("find books with title like");
            case 4:
                System.out.println("find books with author like");
            default:
                System.out.println("You did not make a valid selection.");
        
        
        }//end switch
        
        
        
    }//end read choice
    
    
}end class
