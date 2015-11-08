package Model;

import java.sql.*;
import javax.sql.*;
import javafx.collections.*;

public class BillItemModel {

    DataSource ds = DatabaseSource.getMySQLDataSource();

    public void addBill(Bill b, ObservableList<BillItem> items) {
        String query;
        PreparedStatement pStmt;
        ResultSet rs;
        try (Connection con = ds.getConnection()) {
            boolean empty = true;
            query = "SELECT * FROM bill";
            pStmt= con.prepareStatement(query);
            rs = pStmt.executeQuery();
            String bName = "Bill-";
            while (rs.next()) {
                rs.last();
                String temp1 = rs.getString("billNo");
                int bNo = Integer.parseInt(temp1.substring(5, temp1.length()));
                bNo++;
                bName = bName + Integer.toString(bNo);
                b.setBillNo(bName);
                empty = false;
            }
            if (empty) {
                bName = bName + Integer.toString(1);
                b.setBillNo(bName);
            }
            for (int i = 0; i < items.size(); i++) {
                items.get(i).setBillNo(bName);
            }

            query = "INSERT INTO billitem" + " VALUES (?,?,?,?,?,?)";
            pStmt = con.prepareStatement(query);
            for (BillItem record : items) {
                pStmt.setString(1, record.getBillNo());
                pStmt.setString(2, record.getBillItemNo());
                pStmt.setString(3, record.getProductID());
                pStmt.setDouble(4, record.getUnitPrice());
                pStmt.setInt(5, record.getQuantity());
                pStmt.setDouble(6, record.getTotal());
                pStmt.addBatch();
            }
            pStmt.executeBatch();

            query = "UPDATE product SET productStock = productStock - ? WHERE productID = ? ";
            pStmt = con.prepareStatement(query);
            for (BillItem record : items) {
                pStmt.setInt(1, record.getQuantity());
                pStmt.setString(2, record.getProductID());
                pStmt.addBatch();
            }
            pStmt.executeBatch();

            for (int i = 0; i < items.size(); i++) {
                updateInvoice(items.get(i));
            }

            query = "INSERT INTO bill" + " VALUES (?,?,?,?)";
            pStmt = con.prepareStatement(query);
            pStmt.setString(1, b.getBillNo());
            pStmt.setDate(2, (Date) b.getBillDate());
            pStmt.setString(3, b.getBillNote());
            pStmt.setDouble(4, b.getBillAmount());
            pStmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateInvoice(BillItem bi) {
        String query;
        PreparedStatement pStmt;
        ResultSet rs;
        try (Connection con = ds.getConnection()) {
            query = "SELECT invoiceItemID, productID, quantity, sold FROM invoiceitem WHERE productID='" + bi.getProductID() + "' and quantity>0 ORDER BY expireDate";
            pStmt = con.prepareStatement(query);
            rs = pStmt.executeQuery();
            ObservableList<InvoiceItem> ol = FXCollections.observableArrayList();
            while (rs.next()) {
                ol.add(new InvoiceItem(rs.getString("invoiceItemID"), rs.getString("productID"), rs.getInt("quantity"), rs.getInt("sold")));
            }
            int q = bi.getQuantity();
            int i = 0;
            boolean flag = true;
            while (flag) {
                if (q > ol.get(i).getQuantity()) {
                    q = q - ol.get(i).getQuantity();
                    ol.get(i).setSold(ol.get(i).getSold()+ol.get(i).getQuantity());
                    ol.get(i).setQuantity(0);
                    i++;
                }
                if (q <= ol.get(i).getQuantity()) {
                    ol.get(i).setQuantity(ol.get(i).getQuantity() - q);
                    ol.get(i).setSold(ol.get(i).getSold()+q);
                    q = 0;
                    flag = false;
                }
            }
            query = "UPDATE invoiceitem SET quantity = ? , sold = ? WHERE invoiceItemID = ? AND productID = ?";
            pStmt = con.prepareStatement(query);
            for (InvoiceItem record : ol) {
                pStmt.setInt(1, record.getQuantity());
                pStmt.setInt(2, record.getSold());
                pStmt.setString(3, record.getItemID());
                pStmt.setString(4, record.getProductID());
                pStmt.addBatch();
            }
            pStmt.executeBatch();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ObservableList<String> getProductsID() {
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
