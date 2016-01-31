package Model.Service;

import Model.DTO.Bill;
import Model.DTO.Invoice;
import Model.DatabaseSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.sql.DataSource;

public class BillAndInvoiceModel {
    
    DataSource ds = DatabaseSource.getMySQLDataSource();

    public LocalDate getLocalDate(Date date) {
        LocalDate localD = date.toLocalDate();
        return localD;
    }
    
    public ObservableList givenDayBillReport(Date billDate1) {
        CallableStatement stmt = null;
        try (Connection con = ds.getConnection()) {
            ObservableList<Bill> ol = FXCollections.observableArrayList();
            String sql = "{call givendaybillreport (?)}";
            stmt = con.prepareCall(sql);
            stmt.setDate(1, billDate1);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ol.add(new Bill(rs.getInt(1),getLocalDate(rs.getDate(2)),
                        rs.getString(3),rs.getDouble(4)));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObservableList daysRangeBillReport(Date billDate1, Date billDate2) {
        CallableStatement stmt = null;
        try (Connection con = ds.getConnection()) {
            ObservableList<Bill> ol = FXCollections.observableArrayList();
            String sql = "{call daysrangebillreport (?, ?)}";
            stmt = con.prepareCall(sql);
            stmt.setDate(1, billDate1);
            stmt.setDate(2, billDate2);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ol.add(new Bill(rs.getInt(1),getLocalDate(rs.getDate(2)),
                        rs.getString(3),rs.getDouble(4)));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObservableList givenDayInvoiceReport(Date invDate1) {
        CallableStatement stmt = null;
        try (Connection con = ds.getConnection()) {
            ObservableList<Invoice> ol = FXCollections.observableArrayList();
            String sql = "{call givendayinvoicereport (?)}";
            stmt = con.prepareCall(sql);
            stmt.setDate(1, invDate1);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ol.add(new Invoice(rs.getInt(1),rs.getString(2),getLocalDate(rs.getDate(3)),
                        rs.getString(4),rs.getString(5),rs.getDouble(6)));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ObservableList daysRangeInvoiceReport(Date invDate1, Date invDate2) {
        CallableStatement stmt = null;
        try (Connection con = ds.getConnection()) {
            ObservableList<Invoice> ol = FXCollections.observableArrayList();
            String sql = "{call daysrangeinvoicereport (?, ?)}";
            stmt = con.prepareCall(sql);
            stmt.setDate(1, invDate1);
            stmt.setDate(2, invDate2);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                ol.add(new Invoice(rs.getInt(1),rs.getString(2),getLocalDate(rs.getDate(3)),
                        rs.getString(4),rs.getString(5),rs.getDouble(6)));
            }
            return ol;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
