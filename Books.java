package main;
/**
 *
 * @author kardelen çetin
 */

// data class
public class Books {
    
    // The class where we keep the ids of the books as common data.
    private int bookId;
    
    private int bookId1 = 1;
    private int bookId2 = 2;
    private int bookId3 = 3;
    private int bookId4 = 4;
    private int bookId5 = 5;
    private int bookId6 = 6;
    private boolean bookIsFree = true;
            
            
    // constructor for books 
     public Books(int bookId,boolean bookIsFree) {
        this.bookId = bookId;
        this.bookIsFree = bookIsFree;
    }
    // getter and setter methods
    public int getBookId1() {
        return bookId1;
    }

    public void setBookId1(int bookId1) {
        this.bookId1 = bookId1;
    }

   
    public int getBookId2() {
        return bookId2;
    }

    public void setBookId2(int bookId2) {
        this.bookId2 = bookId2;
    }

    public int getBookId3() {
        return bookId3;
    }

    public void setBookId3(int bookId3) {
        this.bookId3 = bookId3;
    }

    public int getBookId4() {
        return bookId4;
    }

    public void setBookId4(int bookId4) {
        this.bookId4 = bookId4;
    }

    public int getBookId5() {
        return bookId5;
    }

    public void setBookId5(int bookId5) {
        this.bookId5 = bookId5;
    }

    public int getBookId6() {
        return bookId6;
    }

    public void setBookId6(int bookId6) {
        this.bookId6 = bookId6;
    }

    public boolean isBookIsFree() {
        return bookIsFree;
    }

    public void setBookIsFree(boolean bookIsFree) {
        this.bookIsFree = bookIsFree;
    }  
    
}
