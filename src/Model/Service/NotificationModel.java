package Model.Service;

import Model.*;
import Model.DTO.InvoiceItem;
import Model.DTO.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        CallableStatement stmt = null;
        try (Connection con = ds.getConnection()) {
            ObservableList<Product> ol = FXCollections.observableArrayList();
            String sql = "{call getproductscurrentstock}";
            stmt = con.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            Map<String, Integer> map = new HashMap<>();
            while(rs.next()){
                map.put(rs.getString(1), rs.getInt(2));
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()){
                String sql1 = "{call minstocknotification(?,?)}";
                stmt = con.prepareCall(sql1);
                stmt.setString(1, entry.getKey());
                stmt.setInt(2, entry.getValue());
                ResultSet rs1 = stmt.executeQuery();
                while (rs1.next()) {
                    ol.add(new Product(rs1.getString(1), rs1.getString(2), entry.getValue(), rs1.getInt(3)));
                }
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObservableList expireNotification() {
        CallableStatement stmt = null;
        try (Connection con = ds.getConnection()) {
            ObservableList<InvoiceItem> ol = FXCollections.observableArrayList();
            String sql = "{call expirenotification}";
            stmt = con.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ol.add(new InvoiceItem(rs.getString(1), rs.getString(2), rs.getInt(3), getLocalDate(rs.getDate(4))));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
      
}
