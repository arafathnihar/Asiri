package Model.Service;

import Model.*;
import Model.DTO.Invoice;
import Model.DTO.InvoiceItem;
import java.sql.*;
import java.time.LocalDate;
import javax.sql.*;
import javafx.collections.*;

public class InvoiceItemModel {

    DataSource ds = DatabaseSource.getMySQLDataSource();

    public Date getSqlDate(LocalDate localDate) {
        Date date = Date.valueOf(localDate);
        return date;
    }
    
    public int getInvoiceId(){
        String query;
        PreparedStatement pStmt;
        ResultSet rs;
        try (Connection con = ds.getConnection()) {
            query = "SHOW TABLE STATUS WHERE `Name` = 'invoice'";
            pStmt = con.prepareStatement(query);
            rs = pStmt.executeQuery();
            rs.next();
            return Integer.parseInt(rs.getString("Auto_increment"));
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public void addInvoice(Invoice i, ObservableList<InvoiceItem> items) {
        try (Connection con = ds.getConnection()) {
            String query = "INSERT INTO invoiceitem (invoiceID, productID, packSize, "
                    + "quantity, free, price, margin, expireDate, discount) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pStmt = con.prepareStatement(query);
            for (InvoiceItem record : items) {
                pStmt.setInt(1, getInvoiceId());
                pStmt.setString(2, record.getProductID());
                pStmt.setInt(3, record.getPackSize());
                pStmt.setInt(4, record.getQuantity());
                pStmt.setInt(5, record.getFree());
                pStmt.setDouble(6, record.getPrice());
                pStmt.setDouble(7, record.getMargin());
                pStmt.setDate(8, getSqlDate(record.getExpireDate()));
                pStmt.setDouble(9, record.getDiscount());
                pStmt.addBatch();
            }
            pStmt.executeBatch();
            for (InvoiceItem record : items) {
                updateProduct(record.getProductID(), record.getQuantity());
            }
            String query1 = "INSERT INTO invoice (dCode, invoiceDate, invoiceNote, "
                    + "invoicePayMode, invoiceTotal) VALUES (?,?,?,?,?)";
            PreparedStatement pStmt1 = con.prepareStatement(query1);
            pStmt1.setString(1, i.getDistibutorCode());
            pStmt1.setDate(2, getSqlDate(i.getInvoiceDate()));
            pStmt1.setString(3, i.getInvoiceNote());
            pStmt1.setString(4, i.getInvoicePayMode());
            pStmt1.setDouble(5, i.getInvoiceTotal());
            pStmt1.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateProduct(String productID, int quantity) {
        String query;
        PreparedStatement pStmt;
        try (Connection con = ds.getConnection()) {
            query = "UPDATE product SET productStock = productStock + ? WHERE productID = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setInt(1, quantity);
            pStmt.setString(2, productID);
            pStmt.executeUpdate();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public ObservableList<String> getProductID() {
        try (Connection con = ds.getConnection()) {
            ObservableList<String> ol = FXCollections.observableArrayList();
            String query = "SELECT productID FROM product";
            PreparedStatement pStmt = con.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                ol.add(rs.getString(1));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObservableList<String> getDistributerCode() {
        try (Connection con = ds.getConnection()) {
            ObservableList<String> ol = FXCollections.observableArrayList();
            String query = "SELECT dCode FROM distributor";
            PreparedStatement pStmt = con.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                ol.add(rs.getString(1));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
