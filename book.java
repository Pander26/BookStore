
public class book extends product{

    private String author;


    public book(String title, String author, String genre, double price, int numInStock, int itemId){
        super(title, genre, price, numInStock, itemId);
        this.author = author; 
    }

    /**
     * 
     * @param author
     */
    public void setAuthor(String author){
        this.author = author;
    }
    /**
     * 
     * @return String author
     */
    public String getAuthor(){
        return this.author;
    }

    public String display(){
        return "title: " + super.getTitle() + ", author: " + author 
                + ", price: " + super.getPrice() + ", numInStock: " + super.getNumInStock() 
                        + ", itemId: " + super.getItemId();
    }
    public String printToCSV(){
        return super.getTitle() + "; " + author + "; " + super.getGenre() + "; " + super.getPrice() + "; "  + super.getNumInStock() + "; "  + super.getItemId();
    }
}
