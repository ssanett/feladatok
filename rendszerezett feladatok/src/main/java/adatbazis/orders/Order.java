package adatbazis.orders;

public class Order {

    private long id;
    private String productName;
    private int productCount;
    private int pricePerProduct;

    public Order(long id, String productName, int productCount, int pricePerProduct) {
        this.id = id;
        this.productName = productName;
        this.productCount = productCount;
        this.pricePerProduct = pricePerProduct;
    }

    public Order(String productName, int productCount, int pricePerProduct) {
        this.productName = productName;
        this.productCount = productCount;
        this.pricePerProduct = pricePerProduct;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductCount() {
        return productCount;
    }

    public int getPricePerProduct() {
        return pricePerProduct;
    }
}
