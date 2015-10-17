package Model;

import java.sql.*;
import javax.sql.*;
import javafx.collections.*;

public class InvoiceItemModel {

    DataSource ds = DatabaseSource.getMySQLDataSource();

    public void addInvoice(Invoice i, ObservableList<InvoiceItem> items) {
        try (Connection con = ds.getConnection()) {
            String query = "INSERT INTO invoiceitem" + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pStmt = con.prepareStatement(query);            
            for (InvoiceItem record : items) {
                pStmt.setString(1, record.getItemID());
                pStmt.setString(2, record.getInvoiceID());
                pStmt.setString(3, record.getProductID());
                pStmt.setInt(4, record.getPackSize());
                pStmt.setInt(5, record.getQuantity());
                pStmt.setInt(6, record.getFree());
                pStmt.setDouble(7, record.getPrice());
                pStmt.setInt(8, record.getMargin());
                pStmt.setDate(9, (Date) record.getExpireDate());
                pStmt.setDouble(10, record.getDiscount());
                pStmt.setInt(11, record.getSold());
                pStmt.addBatch();
            }
            pStmt.executeBatch();
            
            String query1 = "INSERT INTO invoice" + " VALUES (?,?,?,?,?,?)";
            PreparedStatement pStmt1 = con.prepareStatement(query1);
            pStmt1.setString(1, i.getInvoiceID());
            pStmt1.setString(2, i.getDistibutorCode());
            pStmt1.setDate(3, (Date)i.getInvoiceDate());
            pStmt1.setString(4, i.getInvoiceNote());
            pStmt1.setString(5, i.getInvoicePayMode());
            pStmt1.setDouble(6,i.getInvoiceTotal());
            pStmt1.executeUpdate();
            System.out.println(i.getInvoicePayMode());
            System.out.println(i.getInvoiceTotal());
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
