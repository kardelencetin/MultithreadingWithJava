package main;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;


/**
 *
 * @author kardelen çetin
 */

import java.util.concurrent.Semaphore;

public class Librarians implements Runnable{
    
    static Semaphore semaphore = new Semaphore(3);
    
    String name = "";
    
    public static Lock[] books;
    
    Books bookId;

    private List<Books> bookList;
    private int studentId;
    private Thread Librarians;
    private String librarians;
    
    private int librarianId;
     
    private boolean librariansIsFree;
    
     
    Books bookIsFree;
    
    
     
    Students students;
    public int unavailable;
    
    
    public Librarians(String librarians) {
        this.librarians = librarians;
        //System.out.println(librarians+ ": welcome to say to " + students);
    }
    

    @Override
    public void run(){
        //randomWelcome();
        //hasBookAvaible();
        //takeBook(bookId);
        //takeLibrarians();
        
        //we used semaphore for librarians.
        
        try {

                
                System.out.println(librarians + " : ready");
                
                semaphore.acquire();
                System.out.println(librarians + " : busy");

                try {

                    //for (int i = 1; i <= 6; i++) {

                        System.out.println(librarians + " : is give the book to student");

                        // sleep 1 second
                        Thread.sleep(1000);

                    //}

                } finally {

                    // calling release() after a successful acquire()
                    System.out.println(librarians + " : idle");
                    semaphore.release();
                   

                }

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    

    
    
   /* public boolean hasBookAvaible() throws InterruptedException{
      
    }*/
    
    // for librarians to welcome students
    
    public synchronized  void randomWelcome(){
        notify();
        
        try
        {
            
           //for(int studentId = 1; studentId <= 40; studentId++){
                //System.out.println(librarians + " welcome the " + studentId);
            Random random = new Random();
            unavailable = random.nextInt(6) + 1;
            int rand = ThreadLocalRandom.current().nextInt(1,3);
            System.out.println("Librarian " + rand +" could not find the book: " + unavailable);
                Thread.sleep(1000);
        //}
        }catch (InterruptedException exception){
            //System.out.println(librarians + " don't welcome the " + studentId );
        }
    }
        //for(int studentId = 1; studentId <= 40; studentId++){
           
              // System.out.println( librarians + " welcome the " + students);
            //System.out.println(librarians+ ": welcome to say to Student:" + studentId);
       // }
    
    
   /* public void checkBookStatus()throws InterruptedException{
  

    }*/
    
    /*
    public void borrowBook(){
    
    }*/
    
 /*public static boolean takeBook(int bookId) {
        books = new Lock[40];
        return books[bookId].tryLock();
    }*/
    
    // to catch the librarian
   /* private boolean takeLibrarians() {
        
        System.out.printf("küütphaneci öğrenciyle eşleşiyor\n", state);
        boolean librarianTaken = true;
        if (!librarianTaken) {
            System.out.printf("kütüphanecide çok sıra var...\n", state);
            return false;
        }

        System.out.printf("kütüphaneci öğrenciden talep bekliyor\n", state);
        boolean bookTaken = false;
        if (!bookTaken) {
            System.out.printf("istenilen kitap elimde yok\n", state);
            
            return false;
        }
        System.out.printf("kütüphaneci istenilen kitabı bulabildi\n", state);
        return true;
    }*/
    
    // kitapları kilitlemek için
    public static boolean takeBook(int bookId) {
        return books[bookId].tryLock();
    }

   
    public static void putDownBook(int bookId) {
        books[bookId].unlock();
    }
    
    public void start(){
        if(Librarians == null){
            Librarians = new Thread(this,librarians);
            Librarians.start();
        }

    } 
    
}
