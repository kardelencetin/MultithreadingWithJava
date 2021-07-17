package main;

// libraries needed

import static java.lang.Thread.currentThread;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author kardelen çetin
 */

//It implements the class runnable to be used as a thread.
public class Students implements Runnable{
    

     
    private Thread Students; //Define thread 
    private String students; //Define threadName
    
    private int bookId; // define 1-6 bookId
    
    private int librarians;
    
    private boolean bookIsFree = false; 
    
    public int unavailable; //used to check book state

     
    // Constructor is class of students.
    public Students(String name) {
       this.students = name;
       //System.out.println(students+ ": came the library.");
    }
    
    //It was created to lock the books.
    private Semaphore semaphore = new Semaphore(6); 
    
    @Override
    public void run(){
        //In order for the functions written below to work, we would run them in the run class.
        try {
            randomArrival();
            takeBook();
            //takesBook();
            finishedRead(bookId);
            
        } catch (InterruptedException ex) {
           
        }
        //The student who finishes the books leaves the library.
        System.out.println(students+ " exit the library");
    }
    
    
    //A function that keeps the number of books and allows us to understand whether they are finished or not.
    public synchronized int finishedRead(int bookId){
        notify();
        //System.out.println(students + " started reading");
        
       
        for (bookId = 1; bookId <= 6; bookId++) {
            try {
                Thread.sleep((long)(1500*Math.random()));
                System.out.println(students + "  book count is " + bookId);
            } catch (InterruptedException e) {
                System.out.println(students + " is interrupted");
            }
        }
      
        System.out.println(students + " is finished all the books.");
        
        return bookId;
        

    }
    
    
    //The function where we enable students to take and return the books and return them to the queue if the book is full.
    public synchronized void takeBook() throws InterruptedException{
        notify();
        Random random = new Random();
        semaphore.acquire();
        
        
        unavailable = random.nextInt(6) + 1;
        int rand = ThreadLocalRandom.current().nextInt(1,3);
   
        ReentrantLock lock = new ReentrantLock();
        if (lock.tryLock()) {
            boolean True = true;
            while(True){
            for(bookId = 1; bookId <= 6; bookId++){

               //int a = ((int)(Math.random()*6 + 1));
               
               
               if(bookId != unavailable){
                   
                    System.out.println(students +" requested book: "+bookId+" from Librarian: "+ rand);
                    System.out.printf(students +" is taking book: "+bookId+ "\n");
                    System.out.println(students+" left to read the book: " + bookId);

                    long start = System.currentTimeMillis();
                    Thread.sleep((long)(2000*Math.random()));
                    //System.out.println((System.currentTimeMillis()-start)+" saniye sonra "+students+" "+bookId+". kitabı geri verdi");
                    //System.out.println("Returned " + students + " book :" +bookId + " after "+(System.currentTimeMillis()-start)+" milliseconds.");
                    System.out.println(students + " gave back the book "+bookId + " after "+(System.currentTimeMillis()-start)+" milliseconds to Librarian: " +rand);
                    True = false;
               }else{
                   System.out.println(currentThread().getName()+" requested book: "+unavailable+" from Librarian: "+ rand);
                   System.out.println("Librarian " + rand +" could not find the book: " + unavailable);
                   System.out.println(students +" didn't take book:" + unavailable);
                   System.out.println(students+" turned to queue again");
                   /*System.out.println(currentThread().getName()+" requested book: "+secilen+" from Librarian: "+ rand);
                   System.out.println(students +" requested book: "+bookId+" from Librarian: "+ rand);
                   System.out.println(students +" didn't take book:" + secilen);*/
                   long start = System.currentTimeMillis();
                   Thread.sleep((long)(2000*Math.random()));
                   System.out.println("After " + (System.currentTimeMillis()-start) + " milliseconds " + students +" is taking book: "+unavailable+ " again.");
                   System.out.println(students + " gave back the book "+unavailable + " after "+(System.currentTimeMillis()-start)+" milliseconds.");
               }
              
               
               }
            }
               }
        lock.unlock();
       
    }
            
        
       

    
    public void takesBook() throws InterruptedException{
        
        semaphore.acquire();

        try {
            takesBook();
        } finally {
            semaphore.release();
        }
    }

    

    //Function that allows students to randomly come to the library.
    public synchronized void randomArrival() throws InterruptedException{
        notify();
         ReentrantLock lock = new ReentrantLock();
         int rand = ThreadLocalRandom.current().nextInt(1,3); 
         
         if (lock.tryLock()) {
            for(rand = 1; rand <= 3; rand++){
               long start = System.currentTimeMillis();
               Thread.sleep((long)(2000*Math.random()));
               System.out.println(currentThread().getName()+  " came the library " +(System.currentTimeMillis()-start)+  " milliseconds later\n" +"Librarian "+ rand + " welcome to " + students);

           }
          lock.unlock();
        }
             
    }  

    //The start function required for the thread to run.
    public void start(){
        if(Students == null){
            Students = new Thread(this,students);
            Students.start();
        }
    
    } 
}
