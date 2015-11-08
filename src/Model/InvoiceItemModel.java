package Model;

import java.sql.*;
import java.time.LocalDate;
import javax.sql.*;
import javafx.collections.*;

public class InvoiceItemModel {

    DataSource ds = DatabaseSource.getMySQLDataSource();
    
    public Date getSqlDate(LocalDate localDate){
        Date date = Date.valueOf(localDate);
        return date;
    }
   
    public void addInvoice(Invoice i) {
        try (Connection con = ds.getConnection()) {
            String query = "INSERT INTO invoiceitem" + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pStmt = con.prepareStatement(query);
            for (InvoiceItem record : i.getItems()) {
                pStmt.setString(1, record.getItemID());
                pStmt.setString(2, record.getInvoiceID());
                pStmt.setString(3, record.getProductID());
                pStmt.setInt(4, record.getPackSize());
                pStmt.setInt(5, record.getQuantity());
                pStmt.setInt(6, record.getFree());
                pStmt.setDouble(7, record.getPrice());
                pStmt.setDouble(8, record.getMargin());
                pStmt.setDate(9, getSqlDate(record.getExpireDate()));
                pStmt.setDouble(10, record.getDiscount());
                pStmt.setInt(11, record.getSold());
                pStmt.addBatch();
            }
            pStmt.executeBatch();

            String query1 = "INSERT INTO invoice" + " VALUES (?,?,?,?,?,?)";
            PreparedStatement pStmt1 = con.prepareStatement(query1);
            pStmt1.setString(1, i.getInvoiceID());
            pStmt1.setString(2, i.getDistibutorCode());
            pStmt1.setDate(3, getSqlDate(i.getInvoiceDate()));
            pStmt1.setString(4, i.getInvoiceNote());
            pStmt1.setString(5, i.getInvoicePayMode());
            pStmt1.setDouble(6, i.getInvoiceTotal());
            pStmt1.executeUpdate();

            String query3 = "UPDATE product SET productStock = productStock + ? WHERE productID = ? ";
            PreparedStatement pStmt3 = con.prepareStatement(query3);
            for (InvoiceItem record : i.getItems()) {
                pStmt3.setInt(1, record.getQuantity());
                pStmt3.setString(2, record.getProductID());
                pStmt3.addBatch();
            }
            pStmt3.executeBatch();

        } catch (SQLException ex) {
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
