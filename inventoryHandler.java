import java.util.ArrayList;
import java.util.Scanner;

public class inventoryHandler implements inventorySpecification{

    bookStore bookStore = new bookStore();
    Scanner input = new Scanner(System.in);
    
    /**
     * method that adds all producs to one inventory list and returns that list
     * 
     * @return a list of all producs in inventory
     */
    public ArrayList<product> getInventory(){

        ArrayList<product> inventory = new ArrayList<product>();

        for(int i = 0; i < bookStore.getBookShelf().size(); i ++){
            inventory.add(bookStore.getBookShelf().get(i));
        }
        for(int i = 0; i < bookStore.getCdShelf().size(); i ++){
            inventory.add(bookStore.getCdShelf().get(i));
        }
        for(int i = 0; i < bookStore.getDvdShelf().size(); i ++){
            inventory.add(bookStore.getDvdShelf().get(i));
        }

        return inventory;

    }

    private ArrayList<product> inventoryList = getInventory();


    /**
     * method that takes an item id and an integer of new stock and adds it to the item with that id's current stock
     */
    public void restockProduct(int id, int newStock){

        for(product i : inventoryList){

            if(i.getItemId() == id){
                int totalStock = newStock + i.getNumInStock();
                i.setNumInStock(totalStock);

                System.out.println("Success! Item : " + i.getTitle() + " now has " + i.getNumInStock() + " in stock.");
            }
        }
    }

    /**
     * method to add up the price of all inventory items and return it as a double
     * 
     * @return the total price of all items in inventory as a double
     */
    public double inventoryValue(){
        double inventoryTotalValue = 0.00;

        for(product i : inventoryList){
            inventoryTotalValue += i.getPrice() * i.getNumInStock();

        }
        return inventoryTotalValue;
    }
    
}
