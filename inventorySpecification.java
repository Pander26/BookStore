public interface inventorySpecification {

    /**
     *  adding new stock b to item with item id a
     * 
     * @param a
     * @param b
     */
    public void restockProduct(int a, int b);


/**
 * returning the total value of all inventory added up
 * 
 * @return the inventory total value
 */
    public double inventoryValue();
    
}
