package Model.Service;

import Model.*;
import Model.DTO.InvoiceItem;
import Model.DTO.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;

public class NotificationModel {

    DataSource ds = DatabaseSource.getMySQLDataSource();

    public LocalDate getLocalDate(Date date) {
        LocalDate localD = date.toLocalDate();
        return localD;
    }

    public ObservableList minStockNotification() {
        try (Connection con = ds.getConnection()) {
            ObservableList<Product> ol = FXCollections.observableArrayList();
            ProductModel pm = new ProductModel();
            Map<String, Integer> map = getProductsCurrentStock();
            for (Map.Entry<String, Integer> entry : map.entrySet()){
                String query = "SELECT  productID, productName, productMinStock FROM product WHERE productID = '"+entry.getKey()+"' AND productMinStock <= '"+ entry.getValue() +"'";
                PreparedStatement pStmt = con.prepareStatement(query);
                ResultSet rs = pStmt.executeQuery();
                while (rs.next()) {
                    ol.add(new Product(rs.getString(1), rs.getString(2), entry.getValue(), rs.getInt(3)));
                }
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObservableList expireNotification() {
        try (Connection con = ds.getConnection()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, +30);
            String timeStamp = sdf.format(calendar.getTime());
            java.util.Date date = sdf.parse(timeStamp);
            java.sql.Date sqlDate = new Date(date.getTime());
            ObservableList<InvoiceItem> ol = FXCollections.observableArrayList();
            String query = "SELECT productID, (SELECT productName FROM product WHERE productID = invoiceitem.productID) AS productName, "
                    + "quantity, expireDate FROM invoiceitem WHERE quantity > 0 and expireDate ='" + sqlDate + "'";
            PreparedStatement pStmt = con.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                ol.add(new InvoiceItem(rs.getString(1), rs.getString(2), rs.getInt(3), getLocalDate(rs.getDate(4))));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public Map<String, Integer> getProductsCurrentStock() {
        CallableStatement stmt = null;
        try (Connection con = ds.getConnection()) {
            String sql = "{call getproductscurrentstock}";
            stmt = con.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            Map<String, Integer> map = new HashMap<String, Integer>();
            while(rs.next()){
                map.put(rs.getString(1), rs.getInt(2));
            }
            return map;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
