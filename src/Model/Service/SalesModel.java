package Model.Service;

import Model.*;
import Model.DTO.Bill;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;

public class SalesModel {

    DataSource ds = DatabaseSource.getMySQLDataSource();

    public LocalDate getLocalDate(Date date) {
        LocalDate localD = date.toLocalDate();
        return localD;
    }

    public ObservableList monthlyBillSum() {
        CallableStatement stmt = null;
        try (Connection con = ds.getConnection()) {
            ObservableList<Bill> ol = FXCollections.observableArrayList();
            String sql = "{call monthlybillsum}";
            stmt = con.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ol.add(new Bill(getLocalDate(rs.getDate(1)), rs.getDouble(2)));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } 
    }

}
