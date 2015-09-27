package Model;

import java.sql.*;
import javax.sql.*;
import javafx.collections.*;

public class BillItemModel {

    static DataSource ds = DatabaseSource.getMySQLDataSource();

    public void addBill(Bill b, ObservableList<BillItem> items) {
        try (Connection con = ds.getConnection()) {
            boolean empty = true;
            String query1 = "SELECT * FROM bill";
            PreparedStatement pStmt1 = con.prepareStatement(query1);
            ResultSet rs1 = pStmt1.executeQuery();
            String bName = "Bill-";
            while (rs1.next()) {
                rs1.last();
                String temp1 = rs1.getString("billNo");
                int bNo = Integer.parseInt(temp1.substring(5));
                bNo++;
                bName = bName + Integer.toString(bNo);
                b.setBillNo(bName);
                empty = false;
            }
            if (empty) {
                bName = bName + Integer.toString(1);
                b.setBillNo(bName);
            }
            
            for(int i=0; i<items.size(); i++){
                items.get(i).setBillNo(bName);
            }
            
            String query2 = "INSERT INTO billitem" + " VALUES (?,?,?,?,?,?)";
            PreparedStatement pStmt2 = con.prepareStatement(query2);            
            for (BillItem record : items) {
                pStmt2.setString(1, record.getBillNo());
                pStmt2.setString(2, record.getBillItemNo());
                pStmt2.setString(3, record.getProductID());
                pStmt2.setDouble(4, record.getUnitPrice());
                pStmt2.setInt(5, record.getQuantity());
                pStmt2.setDouble(6, record.getTotal());
                pStmt2.addBatch();
            }
            pStmt2.executeBatch();

            String query = "INSERT INTO bill" + " VALUES (?,?,?,?)";
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setString(1, b.getBillNo());
            pStmt.setDate(2, (Date)b.getBillDate());
            pStmt.setString(3, b.getBillNote());
            pStmt.setDouble(4, b.getBillAmount());
            pStmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /*public static ObservableList getBills() {
        try (Connection con = ds.getConnection()) {
            ObservableList<Bill> ol = FXCollections.observableArrayList();
            String query = "SELECT * FROM bill";
            PreparedStatement pStmt = con.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                ol.add(new Bill(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getDouble(4)));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }*/
    
    /*public ObservableList getBillItems(Bill b) {
        try (Connection con = ds.getConnection()) {
            ObservableList<BillItem> ol = FXCollections.observableArrayList();
            String query = "SELECT * FROM billitem WHERE billNo='"+b.getBillNo()+"'";
            PreparedStatement pStmt = con.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                ol.add(new BillItem(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4),rs.getInt(5),rs.getDouble(6)));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }*/
  
}
