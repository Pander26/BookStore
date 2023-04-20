import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class barnesannoble{

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        customer user = new customer();
        bookStore bookStore = new bookStore();
        inventoryHandler inventory = new inventoryHandler();

        ArrayList<book> bookOrder = bookStore.getBookOrder();
        ArrayList<cd> cdOrder = bookStore.getCdOrder();
        ArrayList<dvd> dvdOrder = bookStore.getDvdOrder();
        ArrayList<String> username = user.getUsername();
        ArrayList<String> premiumStatus = user.getPremiumStatus();

        String searchQuery;
        String cartQuery;
        String userName = "";

        int valid = 0;
        int result;

        int newMembers = 0;
        double totalSales = 0;




        for(int i = 0; i < 1; i++){
            System.out.println("enter '/ADDUSER' to register a new user" + "\n"
                    + "Or if a current user, please enter '/sign in'");
                    String desired = input.nextLine();
            // checking user input for desired sign in options
            if (desired.equals("/ADDUSER")){
                System.out.println("enter a desired username and password: ");
                System.out.print("Username: ");
                String newUser = input.nextLine();
                System.out.print("Password: ");
                String newPassword = input.nextLine();

                user.adduser(newUser, newPassword);

                newMembers += 1;

                System.out.println("Thank you for signing up for your new bookstore account!");
                i--;
            }
            else if(desired.equals("/sign in")){
                System.out.println("please enter your username and password: ");
                System.out.print("Username: ");
                userName = input.nextLine();
                System.out.print("Password: ");
                String password = input.nextLine();
                // validating user input username and password
                if (user.validateAdmin(userName, password) == true){
                    System.out.println("welcome: " + userName);
                    valid = 999;
                }
                else if (user.validateUser(userName, password) == true){
                    System.out.println("welcome valued customer" );
                    valid = 1;
                }
                else{
                    System.out.println("incorrect username or password");
                    i--;
                }
            }
            else{
                System.out.println("please enter a valid option");
                i--;
            }
        }
        /**
         * admin user : Admin1
         * admin pass : Admin@bookstore
         */
        //looping through menu options of admin
        while ( valid == 999){
            try{
                int num;
                System.out.print("\n");
                System.out.println("Please enter the number corresponding to the menu option desired:");
                System.out.println("\t 1. add stock to an item in inventory");
                System.out.println("\t 2. view the total price of all inventory");
                System.out.println("\t 3. compare the prices of two objects");
                System.out.println("\t 4. view current inventory report");
                System.out.println("\t 5. Exit");
                num = input.nextInt();
                input.nextLine();
                    switch (num) {
                        //admin is adding stock to an item
                        case 1: 
                        System.out.println("enter the itemid that has new stock: ");
                        int id = input.nextInt();
                        System.out.println("enter the amount of stock to add: ");
                        int newStock = input.nextInt();
                        //this method for some reason stores an input in like 93, thus breaking the smooth transition
                        //calling the method that adds the new stock to a specific items stock
                        inventory.restockProduct(id, newStock);

                        // this line is so the input is thrown away and line 93 will properly stop the user and wait for input before displaying the next line
                        input.nextLine(); 
                        System.out.println("\n" +"press enter to continue");
                        input.nextLine();
                        break;

                        //admin is checking total price of all inventory
                        case 2:
                        System.out.print("the total price of all items in iventory is: ");
                        //outputting and formatting the value that is returned by the inventoryvalue method
                        System.out.printf("%.2f" , inventory.inventoryValue());
                        System.out.println(" ");

                        System.out.println("\n" +"press enter to continue");
                        input.nextLine();
                        break;

                        //admin is comparing the price of two objects
                        case 3: 
                        //storing the two objects ids
                        System.out.println("enter the id of the first product you would like to compare: ");
                        int itemId1 = input.nextInt();
                        System.out.println("enter the id of the second item you would like to compare: ");
                        int itemId2 = input.nextInt();

                        //getting the list of inventory from the inventory handler class
                        ArrayList<product> inventoryList = inventory.getInventory();
                        int comparisonResult = 0;
                        product item1 = inventoryList.get(0);
                        product item2 = inventoryList.get(0);
                        //looping through the inventory looking for the item ids
                        for(product i : inventoryList){
                            if ( i.getItemId() == itemId1){
                                item1 = i;
                                for(product p : inventoryList){
                                    if (p.getItemId() == itemId2){
                                        item2 = p;
                                        //calling the new comparTo method in the product class to compare item1 and item2 and set the result to comarisonResult
                                        comparisonResult = i.compareTo(p);
                                    }
                                }
                            }
                        }

                        //checking what the comparison's result was and ouputting proper information
                        if(comparisonResult == 1){
                            System.out.println(item1.getTitle() + " (" + item1.getPrice() + ") " 
                                    + "is more expensive than " + item2.getTitle() + " (" + item2.getPrice() + ")");
                        }
                        else if (comparisonResult == 0){
                            System.out.println(item1.getTitle() +  " (" + item1.getPrice() + ") " 
                                    + "is the same price as " + item2.getTitle() + " ("  + item2.getPrice() + ")");
                        }
                        else{
                            System.out.println(item2.getTitle() + " (" + item2.getPrice() + ") " 
                                    + "is more expensive than " + item1.getTitle() + " (" + item1.getPrice() + ")");
                        }

                        input.nextLine();
                        System.out.println("\n" +"press enter to continue");
                        input.nextLine();
                        break;

                        case 4:
                        bookStore.viewInvReport();

                        break;

                        case 5:
                        bookStore.saveInventory();
                        
                        System.exit(0);
                        break;
                    default:
                        //outputting error if user did not enter a number between 1 and 3
                        System.out.println("Sorry, please enter a number between 1 and 3");

                }
            } catch (InputMismatchException ex){
                System.out.println("non number input detected. Please try again entering a valid number");
            }
        }
            
        // looping through menu options of verified customer
        while (valid == 1){
            try{

                System.out.print("\n");
                System.out.println("Please enter the number corresponding to the menu option desired:");
                System.out.println("\t 1. search for a book");
                System.out.println("\t 2. search for a cd");
                System.out.println("\t 3. search for a dvd");
                System.out.println("\t 4. view account details");
                System.out.println("\t 5. add item to cart");
                System.out.println("\t 6. remove item from cart");
                System.out.println("\t 7. manage cart");
                System.out.println("\t 8. checkout");
                System.out.println("\t 9. Exit");
                int num = input.nextInt();
                input.nextLine();
                switch (num) {
                    //user is searching for a book
                    case 1:
                    System.out.println("please enter the title of the book you would like to search for:");
                    System.out.print("book title: ");
                    searchQuery = input.nextLine().toLowerCase();
                    // calling the book search method
                    book bookResult = bookStore.findBook(searchQuery);
                    // outputting error if book not found
                    if (bookResult.getTitle().equals(searchQuery) == false){
                        System.out.println("No results found");
                    }
                    //outputting information if book is found
                    else{
                        System.out.print("\n");
                        System.out.println("info about " + bookResult.getTitle()
                                + "\n" + "Title: " + bookResult.getTitle()
                                        + "\n" + "Author: " + bookResult.getAuthor() 
                                                + "\n" + "Genre: " + bookResult.getGenre()
                                                        + "\n" + "Price: " + bookResult.getPrice()
                                                                + "\n" + "number in stock: " + bookResult.getNumInStock());
                    }
            
                    System.out.println("\n" +"press enter to continue");
                    input.nextLine();
                        break;
                    case 2:
                    //user is searching for cd
                    System.out.println("please enter the title of the cd you would like to search for:");
                    System.out.print("cd title: ");
                    searchQuery = input.nextLine().toLowerCase();
                    //calling the cd search method
                    cd cdResult = bookStore.findCd(searchQuery);
                    //outputting error if cd not found
                    if (cdResult.getTitle().equals(searchQuery) == false){
                        System.out.println("No results found");
                    }
                    //outputting information if book is found
                    else{
                        System.out.print("\n");
                        System.out.println("info about " + cdResult.getTitle()
                                + "\n" + "Title: " + cdResult.getTitle()
                                        + "\n" + "Artist: " + cdResult.getArtist() 
                                                + "\n" + "Genre: " + cdResult.getGenre()
                                                        + "\n" + "Price: " + cdResult.getPrice()
                                                                + "\n" + "number in stock: " + cdResult.getNumInStock());
                    }
            
                    System.out.println("\n" +"press enter to continue");
                    input.nextLine();
                        break;
                    case 3:
                    //user is searching for a dvd
                    System.out.println("please enter the title of the dvd you would like to search for:");
                    System.out.print("dvd title: ");
                    searchQuery = input.nextLine().toLowerCase();
                    //calling dvd search method
                    dvd dvdResult = bookStore.findDvd(searchQuery);
                    //outputting error if dvd is not found
                    if (dvdResult.getTitle().equals(searchQuery) == false){
                        System.out.println("No results found");
                    }
                    //outputting information if dvd is found
                    else{
                        System.out.print("\n");
                        System.out.println("info about " + dvdResult.getTitle()
                                + "\n" + "Title: " + dvdResult.getTitle()
                                        + "\n" + "Genre: " + dvdResult.getGenre() 
                                                + "\n" + "Price: " + dvdResult.getPrice()
                                                        + "\n" + "number in stock: " + dvdResult.getNumInStock());
                    }
            
                    System.out.println("\n" +"press enter to continue");
                    input.nextLine();
                        break;
                    case 4:
                    //user is requesting account information
                    System.out.println("username: " + userName);
                        //looping through usernames arraylist to find current users username
                        for(int i = 0; i < username.size(); i++){
                            if(username.get(i).equals(userName)){
                                //outputting found usernme's premium status
                                System.out.println("premium status: " + premiumStatus.get(i));
                                //if user does not hav premium status prompting them to sign up
                                if(premiumStatus.get(i).equals("no")){
                                System.out.println("would you like to sign up for premium for the small charge of 14.99 a month? (yes/no)");
                                String answer = input.nextLine();
                                    //checking users answer from prompt to sign up for premium status
                                    if(answer.equals("yes")){
                                        //user wants to sign up for premium
                                        System.out.println("please enter the desired payment method (debit/credit)");
                                        String payment = input.nextLine();
                                        //setting the premium status in the 2d arraylist to yes for the specific user
                                        premiumStatus.set(i, "yes");
                                        System.out.println(userName + " is now a premium member");
                                    }
                                    else{
                                        //sending user messsage telling them they are missing out without premium
                                    System.out.println("by not being a premium member you are missing out on tons of deals");
                                    }
                                }
                            }
                        }

                        System.out.println("\n" +"press enter to continue");
                        input.nextLine();
                        break;
                    case 5:
                    //user is adding item to cart
                    System.out.println("please enter the title of the item you would like to add to your cart: ");
                    System.out.print("title: ");
                    cartQuery = input.nextLine();
                    //calling addtocart method and setting the result to what was returned
                    result = bookStore.addToCart(cartQuery);
                    //result returned as -1, meaning that the item was not added to cart
                    if (result == -1){
                        System.out.println("operation failed, please try again and make sure title entered is correct.");
                    }
                    //result returned was not -1, meaning the item was succesfully added
                    else
                        System.out.println("Item added to cart");
            
                    System.out.println("\n" +"press enter to continue");
                    input.nextLine();
                        break;
                    case 6:
                    //user is removing item from cart
                    //prompting user for type of item they are removing
                    System.out.println("please enter the type of item and the title of the item you would like to remove from your cart: ");
                    System.out.print("item type: ");
                    String itemType = input.nextLine().toLowerCase();
                    //prompting user for the title of the item they are removing
                    System.out.print("title: ");
                    cartQuery = input.nextLine();
                    //calling removefromcart method and setting result to what was returned
                    result = bookStore.removeFromCart(cartQuery, itemType);
                    //result returned as -1, meaning the item was not removed
                    if (result == -1){
                        System.out.println("operation failed, please try again and make sure title entered is correct.");
                    }
                    //result returned was not -1, meaning the item was successfully removed
                    else
                        System.out.println("Item removed from cart");
            
                    System.out.println("\n" +"press enter to continue");
                    input.nextLine();
                        break;
                    case 7:
                    //user is managing their cart
                    Double total = 0.00;
                    System.out.println("List of items in cart: ");
                        //looping through the books in cart
                        for (int i=0, j=1; i < bookOrder.size(); i++ , j++){
                            System.out.print("item: " + j + " ");
                            System.out.println(bookOrder.get(i).getTitle() + " price: " + bookOrder.get(i).getPrice());
                            total += bookOrder.get(i).getPrice();
                        }
                        //looping through the cds in cart
                        for (int i=0, j=1; i < cdOrder.size(); i++ , j++){
                            System.out.print("item: " + j + " ");
                            System.out.println(cdOrder.get(i).getTitle() + " price: " + cdOrder.get(i).getPrice());
                            total += cdOrder.get(i).getPrice();
                        }
                        //looping through the dvds in cart
                        for (int i=0, j=1; i < dvdOrder.size(); i++ , j++){
                            System.out.print("item: " + j + " ");
                            System.out.println(dvdOrder.get(i).getTitle() + " price: " + dvdOrder.get(i).getPrice());
                            total += dvdOrder.get(i).getPrice();
                        }
                        //ouputting he total of items in the cart to two decimal points
                        System.out.print("Cart total: ");
                        System.out.printf("%.2f", total );
                        System.out.print("\n");
            
                        System.out.println("\n" + "press enter to continue");
                        input.nextLine();
                        break;
                    case 8:
                    //user is checking out
                    double cartPrice;
                    //prompting the user if they are ready to check out
                    System.out.println("Are you ready to check out?");
                    String ready = input.nextLine().toLowerCase();
                        //user is ready to check out 
                        //checkout method is called and the price of the cart is set to cartPrice
                        if(ready.equals("yes")){
                            cartPrice = bookStore.checkout();
                        }
                        else{
                            break;
                        }
                        //the total price of the cart is outputted to two decimals
                        totalSales += cartPrice;
                        System.out.println("Your total is: " + cartPrice);
                        System.out.println("Thank you for shopping with us today.");

                        System.out.println("\n" + "press enter to continue");
                        input.nextLine();
                        break;
                    case 9:
                    bookStore.setCurrentDay();
                    bookStore.saveInventory();
                    bookStore.createEndOfDay( newMembers, totalSales);
                    //exitting the program
                    System.exit(0);
                        break;
                    default:
                        //outputting error if user did not enter a number between 1 and 9
                        System.out.println("Sorry, please enter a number between 1 and 9");
                }
            } catch (Exception ex){
                System.out.println("non number input detected. Please try again entering a valid number");
                //consuming the token on the input so it doesnt go infinite
                input.nextLine();
            }       
            //closing scanner input
        }
        input.close();
    }
}