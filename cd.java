
public class cd extends product{
    
    private String artist;


    /**
     * creating the constructor for the cd object
     * @param albumTitle
     * @param artist
     * @param genre
     * @param price
     * @param numInStock
     */
    public cd(String title, String artist, String genre, double price, int numInStock, int itemId){
        super(title, genre, price, numInStock, itemId);
        this.artist = artist;
    }

    /**
     * setting the artist
     * @param artist
     */
    public void setArtist(String artist){
        this.artist = artist;
    }
    /**
     * returning the artist
     * @return
     */
    public String getArtist(){
        return this.artist;
    }

    public String display(){
        return "title: " + super.getTitle() + ", artist: " + artist 
                + ", price: " + super.getPrice() + ", numInStock: " + super.getNumInStock() 
                        + ", itemId: " + super.getItemId();
    }
    public String printToCSV(){
        return super.getTitle() + "; "  + artist + "; " + super.getGenre() + "; " + super.getPrice() + "; "  + super.getNumInStock() + "; "  + super.getItemId();
    }

}
