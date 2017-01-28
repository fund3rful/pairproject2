
package model;

import impresario.IView;


public class Book{
    
    public int bookId;				
    public String author;	
    public String title;	   
    public int pubYear;	    
    public String status;
    
/**
*open constructor initializes default values
*@param  bookID, author, title, pubYear, status
* (int, string, string, string, int, string)
*/
     public Book(){
        bookId=0000;				
        author="";	
        title="";	   
        pubYear=9999;	    
        status="";
        
    }//end constructor
 
     
    
    
}// end class
