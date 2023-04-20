import java.util.*;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;


public class bookStore {

    private ArrayList<book> bookShelf = new ArrayList<book>();
    private ArrayList<cd> cdShelf = new ArrayList<cd>();
    private ArrayList<dvd> dvdShelf = new ArrayList<dvd>();
    private ArrayList<book> bookOrder = new ArrayList<book>();
    private ArrayList<cd> cdOrder = new ArrayList<cd>();
    private ArrayList<dvd> dvdOrder = new ArrayList<dvd>();
    private ArrayList<ArrayList> cart = new ArrayList<ArrayList>();

    private ArrayList<book> listOfDaySalesBook = new ArrayList<book>();
    private ArrayList<cd> listOfDaySalesCd = new ArrayList<cd>();
    private ArrayList<dvd> listOfDaySalesDvd = new ArrayList<dvd>();


    //reading the inventory of book, cd, and dvd objects from the inventory day0 file
    public bookStore(){
        Scanner fileScanner;
        try{
            int currentFile = getCurrentDay();
            fileScanner = new Scanner(new File("inventoryReportDay" + currentFile + ".txt"));
            while(fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                //System.out.println(line);
                String[] parsedLine = line.split("; ");
                //checking the first line to properly populate the prodcut type lists
                if(parsedLine[0].equals("book")){
                    double price = Double.valueOf(parsedLine[4]);
                    int numInStock = Integer.valueOf(parsedLine[5]);
                    int id = Integer.valueOf(parsedLine[6]);
                    bookShelf.add(new book(parsedLine[1], 
                        parsedLine[2], parsedLine[3], price, numInStock, id));
                } else if(parsedLine[0].equals("cd")){
                    double price = Double.valueOf(parsedLine[4]);
                    int numInStock = Integer.valueOf(parsedLine[5]);
                    int id = Integer.valueOf(parsedLine[6]);
                    cdShelf.add(new cd(parsedLine[1], parsedLine[2], parsedLine[3], price, numInStock, id));
                } else if(parsedLine[0].equals("dvd")){
                    double price = Double.valueOf(parsedLine[3]);
                    int numInStock = Integer.valueOf(parsedLine[4]);
                    int id = Integer.valueOf(parsedLine[5]);
                    dvdShelf.add(new dvd (parsedLine[1], parsedLine[2], price, numInStock, id));

                }
            }
            fileScanner.close();
        } catch (FileNotFoundException ex){
            System.out.println("Caught FileNotFoundException. Try again making sure the file name and path are correct. ");
        }
    }

    //method that is using the dayTracker file to get the current day as of running the bookstore program
    public int getCurrentDay(){
        int currentDay = 0;
        Scanner fileScanner;
        try{
            fileScanner = new Scanner(new File("dayTracker.txt"));
            String line = fileScanner.nextLine();
            //System.out.println(line);
            String[] parsedLine = line.split("; ");

            //parsing line to find the int representing the current day
            currentDay = Integer.valueOf(parsedLine[0]);
            fileScanner.close();
        } catch (FileNotFoundException x){
            System.out.println("Please check the locations of the dayTracker File and try again.");
        }
        //returning the current day
        return currentDay;
    }
    
    //method to update the day in the daytracker file at the end of the running day
    public void setCurrentDay(){
        Scanner fileScanner;
        try{
            fileScanner = new Scanner(new File("dayTracker.txt"));
            String line = fileScanner.nextLine();
            String[] parsedLine = line.split("; ");

            //previous day is still the value of parsedLine[0]
            //setting previous day to the value of parsedLine[0]
            int previousDay = Integer.valueOf(parsedLine[0]);
            //setting the integer currentDay to be one more than the previous day
            int currentDay = previousDay+1;

            FileOutputStream stream = new FileOutputStream("dayTracker.txt");
            PrintWriter printer = new PrintWriter(stream);

            //updating the day in dayTracker.txt to be the current day
            printer.println(currentDay);

            fileScanner.close();
            printer.close();
            stream.close();
        } catch (FileNotFoundException x){
            System.out.println("Please check the locations of the dayTracker File and try again.");
        } catch (IOException x){
            System.out.println("Error Please Try Again.");
        }
    }

    //method to save the inventory list to the proper inventory report day
    public void saveInventory(){
        try {
            //getting the current day from the currentDay method
            int currentDay = getCurrentDay();

            //setting the file name of the new inventory file to be InventoryReportDay with the proper day attached
            String fileName = "InventoryReportDay" + currentDay + ".txt";
            FileOutputStream stream = new FileOutputStream(fileName);
            PrintWriter printer = new PrintWriter(stream);
            //looping through the book, cd, and dvd arrays to print the values into the inventory report
            for(int i = 0; i< bookShelf.size(); i++){
                printer.println("book; " + bookShelf.get(i).printToCSV());
            }
            for(int i = 0; i< cdShelf.size(); i++){
                printer.println("cd; " + cdShelf.get(i).printToCSV());
            }
            for(int i = 0; i< dvdShelf.size(); i++){
                printer.println("dvd; " + dvdShelf.get(i).printToCSV());
            }
            printer.close();
            stream.close();
        } catch (FileNotFoundException x){
            System.out.println("Please check the locations of the Reservations File and try again.");
        } catch (IOException x){
            System.out.println("Error Please Try Again.");
        }
    }

    //method to view the current inventory report
    public void viewInvReport(){
        for(int i = 0; i < bookShelf.size(); i++){
            System.out.println(bookShelf.get(i).display());
        }
        for(int i = 0; i < cdShelf.size(); i++){
            System.out.println(cdShelf.get(i).display());
        }
        for(int i = 0; i < dvdShelf.size(); i++){
            System.out.println(dvdShelf.get(i).display());
        }
    }

    //method to creat a file titled EODreprt which reports the stores closing information
    public void createEndOfDay(int registered, Double totalSales){
        try{
            FileOutputStream stream = new FileOutputStream("EODreport.txt");
            PrintWriter printer = new PrintWriter(stream);

            int totalItemsSold = 0;

            //printing the book sections title
            printer.println("Book Sales:");
            //looping through the list of book sales from that day and printing them to the EOD report while also tracking the amount of items sold
            for(int i = 0; i< listOfDaySalesBook.size(); i++){                
                printer.print(listOfDaySalesBook.get(i).getTitle() + ", ");
                totalItemsSold +=1;
            }

            //printing the cd sections title
            printer.println("\nCd Sales: ");
            //looping through the list of cd sales from that day and printing them to the EOD report while also tracking the amount of items sold
            for(int i = 0; i< listOfDaySalesCd.size(); i++){                
                printer.print(listOfDaySalesCd.get(i).getTitle() + ", ");
                totalItemsSold +=1;
            }
            //printing the dvd sections title
            printer.println("\nDvd Sales: ");
            //looping through the list of cd sales from that day and printing them to the EOD report while also tracking the amount of items sold
            for(int i = 0; i< listOfDaySalesDvd.size(); i++){                
                printer.print(listOfDaySalesDvd.get(i).getTitle() + ", ");
                totalItemsSold +=1;
            }
            
            //printing the number of new members registered, total items sold, and the total sales amount to the EOD report+
            printer.println("\ntotal new users registered today: " + registered);
            printer.println("total itmes sold today: " + totalItemsSold);
            printer.println("total sales today: " + totalSales);
            printer.close();
            stream.close();
        } catch (FileNotFoundException x){
            System.out.println("Please check the locations of the dayTracker File and try again.");
        } catch (IOException x){
            System.out.println("Error Please Try Again.");
        }

    }

    //find book search algorithm
    public book findBook(String title){
        book bookResult = bookShelf.get(0);
        for (int i =0; i < bookShelf.size(); i++){
            if (title.equals(bookShelf.get(i).getTitle().toLowerCase())){
                bookResult = bookShelf.get(i);
            }
        }
        return bookResult;
    }
    //find cd search algorithm
    public cd findCd(String albumTitle){
        cd cdResult = cdShelf.get(0);
        for (int i =0; i < cdShelf.size(); i++){
            if (albumTitle.equals(cdShelf.get(i).getTitle().toLowerCase())){
                cdResult = cdShelf.get(i);
            }
        }
        return cdResult;
    }
    //find dvd search algorithm
    public dvd findDvd(String title){
        dvd dvdResult = dvdShelf.get(0);
        for (int i =0; i < dvdShelf.size(); i++){
            if (title.equals(dvdShelf.get(i).getTitle().toLowerCase())){
                dvdResult = dvdShelf.get(i);
            }
        }
        return dvdResult;     
    }

    //method to add an item to the cart
    public int addToCart(String title){
        //initializing result
        int result = -1;
        //looping through bookshelf checking the title inputted by the user
        for (int i =0; i < bookShelf.size(); i++){
            if (title.equals(bookShelf.get(i).getTitle())){
                //setting succesful result to 1
                result = 1;
                //adding a new book to the order with the credentials of the one in the inventory
                bookOrder.add(new book(bookShelf.get(i).getTitle(), bookShelf.get(i).getAuthor(), bookShelf.get(i).getGenre(), bookShelf.get(i).getPrice(), bookShelf.get(i).getNumInStock(), bookShelf.get(i).getItemId()));
            }
            else if(title.equals(cdShelf.get(i).getTitle())){
                //setting succesful result to 1
                result = 1;
                //adding a new cd to the order with the credentials of the one in the inventory
                cdOrder.add(new cd(cdShelf.get(i).getTitle(), cdShelf.get(i).getArtist(), cdShelf.get(i).getGenre(), cdShelf.get(i).getPrice(), cdShelf.get(i).getNumInStock(), cdShelf.get(i).getItemId()));
            }
            else if(title.equals(dvdShelf.get(i).getTitle())){
                //setting succesful result to 1
                result = 1;
                //adding a new dvd to the order with the credentials of the one in the inventory
                dvdOrder.add(new dvd(dvdShelf.get(i).getTitle(), dvdShelf.get(i).getGenre(), dvdShelf.get(i).getPrice(), dvdShelf.get(i).getNumInStock(), dvdShelf.get(i).getItemId()));
            }
        }
        //adding the arrayLists of book, cd, and dvd objects to one arraylist to hold all items in cart
        cart.add(bookOrder);
        cart.add(cdOrder);
        cart.add(dvdOrder);
        return result;
    }
    //method to remove and item from the cart
    public int removeFromCart(String title, String itemType){
        //initializing result
        int result = -1;
        //user is removing book
        if(itemType.equals("book")){
            //looping through the order catalog of books
            for (int i =0; i < bookOrder.size(); i++){
                if (title.equals(bookOrder.get(i).getTitle())){
                    //setting succesful result to 1
                    result = 1;
                    //removing book from the order catalog
                    bookOrder.remove((bookOrder.get(i)));
                }
            }
        }
        //user is removing cd
        else if(itemType.equals("cd")){
            //looping through the order catalog of cds
            for (int i =0; i < cdOrder.size(); i++){
                if (title.equals(cdOrder.get(i).getTitle())){
                    //setting succesful result to 1
                    result = 1;
                    //removing cd from the order catalog
                    cdOrder.remove((cdOrder.get(i)));
                }
            }
        }
        //user is removing dvd
        else if(itemType.equals("dvd")){
            //looping through the order catalog of dvds
            for (int i =0; i < dvdOrder.size(); i++){
                if (title.equals(dvdOrder.get(i).getTitle())){
                    //setting succesful result to 1
                    result = 1;
                    //removing dvd from the order catalog
                    dvdOrder.remove((dvdOrder.get(i)));
                }
            }
        }
        //returning result
        return result;
    }
    //method to checkout users cart
    public double checkout(){ 
        //initializing charge and number of items available
        double charge = 0;
        int numAvailable = 0;
        //creating arraylist of books holding books found and used in removing multiple books at once from the cart
        ArrayList <book> foundBook = new ArrayList<book>();
        //looping through book order catalog
        for (int i = 0; i < bookOrder.size(); i++){
            //incrementing the charge for each book in cart
            charge += bookOrder.get(i).getPrice();
            //looping through the book inventory 
            for(int j = 0; j < bookShelf.size(); j++){
                //checking if book in inventory matches the book in the order
                if(bookShelf.get(j).getTitle() == bookOrder.get(i).getTitle()){
                    //removing the book from the inventory
                    numAvailable = bookShelf.get(j).getNumInStock() - 1;
                    bookShelf.get(j).setNumInStock(numAvailable);
                }
            }
            foundBook.add((bookOrder.get(i)));
            
        }
        //adding list of sold books to days sales before removing from inventory
        for(int i = 0; i < foundBook.size() ; i++){
            listOfDaySalesBook.add(foundBook.get(i));
        }
        //removing all the found books from the cart
        bookOrder.removeAll(foundBook);

        //creating arraylist of cds holding cds found and used in removing multiple cds at once from the cart
        ArrayList <cd> foundCd = new ArrayList<cd>();
        for (int i = 0; i < cdOrder.size(); i++){
            //incrementing the charge for each cd in cart
            charge += cdOrder.get(i).getPrice();
            //looping through the cd inventory
            for(int j = 0; j < cdShelf.size(); j++){
                //checking if cd in inventory matches the cd in the order
                if(cdShelf.get(j).getTitle() == cdOrder.get(i).getTitle()){
                    //removing the cd from the inventory
                    numAvailable = cdShelf.get(j).getNumInStock() - 1;
                    cdShelf.get(j).setNumInStock(numAvailable);
                }
            }
            foundCd.add((cdOrder.get(i)));
        }
        //adding the sold cds into the days sales before removing from inventory
        for(int i = 0; i < foundCd.size(); i++){
            listOfDaySalesCd.add(foundCd.get(i));
        }
        //removing all the found cds from the cart
        cdOrder.removeAll(foundCd);

        //creating arraylist of dvds holding dvds found and used in removing multiple dvds at once from the cart
        ArrayList <dvd> foundDvd = new ArrayList<dvd>();
        for (int i = 0; i < dvdOrder.size(); i++){
            //incrementing the charge for each dvd in cart
            charge += dvdOrder.get(i).getPrice();
            //looping through the dvd inventory
            for(int j = 0; j < dvdShelf.size(); j++){
                //checking if dvd in inventory matches the dvd in the order
                if(dvdShelf.get(j).getTitle() == dvdOrder.get(i).getTitle()){
                    //removing the dvd from the inventory
                    numAvailable = dvdShelf.get(j).getNumInStock() - 1;
                    dvdShelf.get(j).setNumInStock(numAvailable);
                }
            }
            foundDvd.add((dvdOrder.get(i)));
        }
        //adding list of sold dvds to days sales before removing from inventory
        for(int i = 0; i < foundDvd.size(); i++){
            listOfDaySalesDvd.add(foundDvd.get(i));
        }        //removing all the found dvds from the cart
        dvdOrder.removeAll(foundDvd);

        //returning the total of all items in the cart
        return charge;

    }
    //method retunring the bookShelf ArrayList
    public ArrayList <book> getBookShelf(){
        return bookShelf;
    }
    //method returing the cdShelf ArrayList
    public ArrayList <cd> getCdShelf(){
        return cdShelf;
    }
    //method returning the dvdShelf ArrayList
    public ArrayList <dvd> getDvdShelf(){
        return dvdShelf;
    }
    //method reurning the bookOrder ArrayList
    public ArrayList <book> getBookOrder(){
        return bookOrder;
    }
    //method reurning the cdOrder ArrayList
    public ArrayList <cd> getCdOrder(){
        return cdOrder;
    }
    //method reurning the dvdOrder ArrayList
    public ArrayList <dvd> getDvdOrder(){
        return dvdOrder;
    }
}
