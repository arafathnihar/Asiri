package Model.DTO;

import java.time.LocalDate;

public class InvoiceItem {

    private String itemID;
    private String invoiceID;
    private String productID;
    private String productName;
    private int packSize;
    private int quantity;
    private int free;
    private double price;
    private double margin;
    private LocalDate expireDate;
    private double discount;
    private int sold;

    public InvoiceItem(String itemID, String invoiceID, String productID, int packSize,
            int quantity, int free, double price, double margin,
            LocalDate expireDate, double discount, int sold) {

        this.itemID = itemID;
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.packSize = packSize;
        this.quantity = quantity;
        this.free = free;
        this.price = price;
        this.margin = margin;
        this.expireDate = expireDate;
        this.discount = discount;
        this.sold = sold;
    }

    public InvoiceItem(String productID, String productName, int quantity, LocalDate expireDate) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.expireDate = expireDate;
    }

    public InvoiceItem(String itemID, String productID, int quantity, int sold) {
        this.itemID = itemID;
        this.productID = productID;
        this.quantity = quantity;
        this.sold = sold;
    }

    public InvoiceItem() {
        this.itemID = "";
        this.invoiceID = "";
        this.productID = "";
        this.packSize = 0;
        this.quantity = 0;
        this.free = 0;
        this.price = 0.0;
        this.margin = 0.0;
        this.expireDate = null;
        this.discount = 0.0;
        this.sold = 0;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getPackSize() {
        return packSize;
    }

    public void setPackSize(int packSize) {
        this.packSize = packSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getMargin() {
        return margin;
    }

    public void setMargin(double margin) {
        this.margin = margin;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
