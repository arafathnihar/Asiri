package Model.DTO;

public class BillItem {

    private int billNo;
    private String billItemNo;
    private String productID;
    private double unitPrice;
    private int quantity;
    private double total;
    private String productName;

    public BillItem(int billNo, String billItemNo, String productID, double unitPrice, int quantity, double total) {
        this.billNo = billNo;
        this.billItemNo = billItemNo;
        this.productID = productID;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.total = total;
    }

    public BillItem() {
        this.billNo = 0;
        this.billItemNo = "";
        this.productID = "";
        this.unitPrice = 0.00;
        this.quantity = 0;
        this.total = 0.00;
    }

    public int getBillNo() {
        return billNo;
    }

    public void setBillNo(int billNo) {
        this.billNo = billNo;
    }

    public String getBillItemNo() {
        return billItemNo;
    }

    public void setBillItemNo(String billItemNo) {
        this.billItemNo = billItemNo;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
