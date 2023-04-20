
public class dvd extends product{
        


    /**
     * constructor for dvd object
     * @param title
     * @param genre
     * @param price
     * @param numInStock
     */
    public dvd(String title, String genre, double price, int numInStock, int itemId){
        super(title, genre, price, numInStock, itemId);
    }

    public String display(){
        return "title: " + super.getTitle() + ", price: " + super.getPrice() 
                + ", numInStock: " + super.getNumInStock() 
                        + ", itemId: " + super.getItemId();
    }
    public String printToCSV(){
        return super.getTitle() + "; " + super.getGenre() + "; " + super.getPrice() + "; "  + super.getNumInStock() + "; "  + super.getItemId();
    }

}
