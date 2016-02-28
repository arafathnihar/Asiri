package Model.Service;

import Model.*;
import Model.DTO.Bill;
import Model.DTO.BillItem;
import Model.DTO.InvoiceItem;
import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.*;
import javafx.collections.*;

public class BillItemModel {
    
    DataSource ds = DatabaseSource.getMySQLDataSource();

    public Date getSqlDate(LocalDate localDate) {
        Date date = Date.valueOf(localDate);
        return date;
    }
    
    public int getBillId(){
        String query;
        PreparedStatement pStmt;
        ResultSet rs;
        try (Connection con = ds.getConnection()) {
            query = "SHOW TABLE STATUS WHERE `Name` = 'bill'";
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

    public void addBill(Bill b, ObservableList<BillItem> items) {
        String query;
        PreparedStatement pStmt;
        try (Connection con = ds.getConnection()) {
            query = "INSERT INTO billitem (billNo, productID, unitPrice, quantity, amount) "
                    + "VALUES (?,?,?,?,?)";
            pStmt = con.prepareStatement(query);
            for (BillItem record : items) {
                pStmt.setInt(1, getBillId());
                pStmt.setString(2, record.getProductID());
                pStmt.setDouble(3, record.getUnitPrice());
                pStmt.setInt(4, record.getQuantity());
                pStmt.setDouble(5, record.getTotal());
                pStmt.addBatch();
            }
            pStmt.executeBatch();
            for(int i=0; i< items.size(); i++){
                updateProduct(items.get(i).getProductID(),items.get(i).getQuantity());
            } 
            for (int i = 0; i < items.size(); i++) {
                updateInvoice(items.get(i));
            }
            query = "INSERT INTO bill (billDate, billNote, billAmount) VALUES (?,?,?)";
            pStmt = con.prepareStatement(query);
            pStmt.setDate(1, getSqlDate(b.getBillDate()));
            pStmt.setString(2, b.getBillNote());
            pStmt.setDouble(3, b.getBillAmount());
            pStmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateProduct(String productID, int quantity) {
        String query;
        PreparedStatement pStmt;
        try (Connection con = ds.getConnection()) {
            query = "UPDATE product SET productStock = productStock - ? WHERE productID = ?";
            pStmt = con.prepareStatement(query);
            pStmt.setInt(1, quantity);
            pStmt.setString(2, productID);
            pStmt.executeUpdate();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateInvoice(BillItem bi) {
        String query;
        PreparedStatement pStmt;
        ResultSet rs;
        try (Connection con = ds.getConnection()) {
            query = "SELECT invoiceItemID, productID, quantity FROM invoiceitem "
                    + "WHERE productID='" + bi.getProductID() + "' and "
                    + "quantity>0 ORDER BY expireDate";
            pStmt = con.prepareStatement(query);
            rs = pStmt.executeQuery();
            ObservableList<InvoiceItem> ol = FXCollections.observableArrayList();
            while (rs.next()) {
                ol.add(new InvoiceItem(rs.getString("invoiceItemID"), rs.getString("productID"), rs.getInt("quantity")));
            }
            int q = bi.getQuantity();
            int i = 0;
            boolean flag = true;
            while (flag) {
                if (q > ol.get(i).getQuantity()) {
                    q = q - ol.get(i).getQuantity();
                    ol.get(i).setQuantity(0);
                    i++;
                }
                if (q <= ol.get(i).getQuantity()) {
                    ol.get(i).setQuantity(ol.get(i).getQuantity() - q);
                    q = 0;
                    flag = false;
                }
            }
            query = "UPDATE invoiceitem SET quantity = ? WHERE "
                    + "invoiceItemID = ? AND productID = ?";
            pStmt = con.prepareStatement(query);
            for (InvoiceItem record : ol) {
                pStmt.setInt(1, record.getQuantity());
                pStmt.setString(2, record.getItemID());
                pStmt.setString(3, record.getProductID());
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

    public BillItem getBillItem(String productID) {
        if (productID != null) {
            try (Connection con = ds.getConnection()) {
                String query = "SELECT * FROM product WHERE productID='" + productID + "'";
                String sql = "{call getunitprice(?,?)}";
                PreparedStatement pStmt = con.prepareStatement(query);
                ResultSet rs = pStmt.executeQuery();
                BillItem bi = new BillItem();
                if (rs.next()) {
                    bi.setProductName(rs.getString(2));
                    CallableStatement callableStatement = con.prepareCall(sql);
                    callableStatement.setString(1, productID);
                    callableStatement.registerOutParameter(2, java.sql.Types.DOUBLE);
                    callableStatement.executeUpdate();
                    Double d = callableStatement.getDouble(2);
                    bi.setUnitPrice(d);
                }
                return bi;
            } catch (SQLException ex) {
                Logger.getLogger(BillItemModel.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            return null;
        }
    }
    
    public String getBillItemByName(String productName) {
        if (productName != null) {
            try (Connection con = ds.getConnection()) {
                String query = "SELECT productID FROM product WHERE productName='" + productName + "'";
                PreparedStatement pStmt = con.prepareStatement(query);
                ResultSet rs = pStmt.executeQuery();
                if (rs.next()) {
                    return rs.getString(1);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;
    }
      
}
