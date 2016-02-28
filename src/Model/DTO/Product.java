package Model.DTO;

public class Product {

    private String productID;
    private String productName;
    private String productDescription;
    private String productBrand;
    private int productStrength;
    private String productType;
    private int productMinStock;
    private int productStock;

    public Product(String productID, String productName, String productDescription, String productBrand,
            int productStrength, String productType, int productMinStock, int productStock) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productBrand = productBrand;
        this.productStrength = productStrength;
        this.productType = productType;
        this.productMinStock = productMinStock;
        this.productStock = productStock;
    }

    public Product(String productID, String productName, int productStock, int productMinStock) {
        this.productID = productID;
        this.productName = productName;
        this.productStock = productStock;
        this.productMinStock = productMinStock;
    }

    public Product() {
        this.productID = "";
        this.productName = "";
        this.productDescription = "";
        this.productBrand = "";
        this.productStrength = 0;
        this.productType = "";
        this.productMinStock = 0;
        this.productStock = 0;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public int getProductStrength() {
        return productStrength;
    }

    public void setProductStrength(int productStrength) {
        this.productStrength = productStrength;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductMinStock() {
        return productMinStock;
    }

    public void setProductMinStock(int productMinStock) {
        this.productMinStock = productMinStock;
    }
    
    public int getProductStock() {
        return productStock;
    }
    
    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
}
