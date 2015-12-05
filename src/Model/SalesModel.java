package Model;

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
        try (Connection con = ds.getConnection()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            String todayDate = sdf.format(calendar.getTime());
            java.util.Date tDate = sdf.parse(todayDate);
            java.sql.Date sqlTDate = new Date(tDate.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, -30);
            String beforeDate = sdf.format(calendar.getTime());
            java.util.Date bDate = sdf.parse(beforeDate);
            java.sql.Date sqlBDate = new Date(bDate.getTime());
            ObservableList<Bill> ol = FXCollections.observableArrayList();
            String query = "SELECT billDate, SUM(billAmount) AS total FROM bill WHERE billDate between '" + sqlBDate + "' and '" + sqlTDate + "' "
                    + "GROUP BY billDate";
            PreparedStatement pStmt = con.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                ol.add(new Bill(getLocalDate(rs.getDate(1)), rs.getDouble(2)));
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

}
