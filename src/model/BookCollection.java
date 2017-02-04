
package model;

import java.util.Vector;

/**
 *
 * @author JJ
 */
public class BookCollection extends EntityBase {

    private String updateStatusMessage = "";
    private Vector<Book> bookList;
    private static final String myTableName = "Book";

    public BookCollection() {
        super("Book");
        
    }
    
}//end class