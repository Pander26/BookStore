public abstract class product implements Comparable<product> {

            
    private String title;
    private String genre;
    private double price;
    private int numInStock;
    private int itemId;

    public product(String title, String genre, double price, int numInStock, int itemId){
        this.title = title;
        this.genre = genre;
        this.price = price;
        this.numInStock = numInStock;
        this.itemId = itemId;

    }

        /**
     * setting the title
     * @param title
     */
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * returning the title
     * @return
     */
    public String getTitle(){
        return this.title; 
    }
    /**
     * setting the genre of 
     * @param genre
     */
    public void setGenre(String genre){
        this.genre = genre;
    }
    /**
     * returning the genre 
     * @return
     */
    public String getGenre(){
        return this.genre;
    }
    /**
     * setting the price 
     * @param price
     */
    public void setPrice(int price){
        this.price = price;
    }
    /**
     * returnig the price
     * @return
     */
    public double getPrice(){
        return this.price;
    }
    /**
     * setting the number left in stock
     * @param numInStock
     */
    public void setNumInStock(int numInStock){
        this.numInStock = numInStock;
    }
    /**
     * returning the number left in stock
     * @return
     */
    public int getNumInStock(){
        return this.numInStock;
    }

    public void setItemId(int itemId){
        this.itemId = itemId;
    }
    public int getItemId(){
        return this.itemId;
    }

    @Override
    public int compareTo(product p){
        if (this.price > p.price) {
 
            // if current object is greater,then return 1
            return 1;
        }
        else if (this.price < p.price) {
 
            // if current object is greater,then return -1
            return -1;
        }
        else {
 
            // if current object is equal to o,then return 0
            return 0;
        }
    }
    
}
