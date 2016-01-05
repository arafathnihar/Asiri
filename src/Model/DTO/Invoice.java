package Model.DTO;

import java.time.LocalDate;
import javafx.collections.ObservableList;

public class Invoice {

    private String invoiceID;
    private String distibutorCode;
    private LocalDate invoiceDate;
    private String invoiceNote;
    private String invoicePayMode;
    private double invoiceTotal;

    private ObservableList<InvoiceItem> items;

    public Invoice(String invoiceID, String distibutorCode, LocalDate invoiceDate, String invoiceNote,
            String invoicePayMode, double invoiceTotal) {
        this.invoiceID = invoiceID;
        this.distibutorCode = distibutorCode;
        this.invoiceDate = invoiceDate;
        this.invoiceNote = invoiceNote;
        this.invoicePayMode = invoicePayMode;
        this.invoiceTotal = invoiceTotal;
    }

    public Invoice() {
        this.invoiceID = "";
        this.distibutorCode = "";
        this.invoiceDate = null;
        this.invoiceNote = "";
        this.invoicePayMode = "";
        this.invoiceTotal = 0.0;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getDistibutorCode() {
        return distibutorCode;
    }

    public void setDistibutorCode(String distibutorCode) {
        this.distibutorCode = distibutorCode;
    }

    public String getInvoiceNote() {
        return invoiceNote;
    }

    public void setInvoiceNote(String invoiceNote) {
        this.invoiceNote = invoiceNote;
    }

    public String getInvoicePayMode() {
        return invoicePayMode;
    }

    public void setInvoicePayMode(String invoicePayMode) {
        this.invoicePayMode = invoicePayMode;
    }

    public double getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(double invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public ObservableList<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(ObservableList<InvoiceItem> items) {
        this.items = items;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
