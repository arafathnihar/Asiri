package Controller;

import Model.DTO.Bill;
import Model.Service.SalesModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;

public class SalesController implements Initializable {

    @FXML
    private BarChart<String, Double> barChart;
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<Bill> salesList = FXCollections.observableArrayList();
    private ObservableList<String> dateList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SalesModel sm = new SalesModel();
        salesList = sm.monthlyBillSum();
        for (Bill sale : salesList) {
            dateList.add(sale.getBillDate().toString());
        }
        xAxis.setCategories(dateList);
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (Bill sale : salesList) {
            series.getData().add(new XYChart.Data<>(sale.getBillDate().toString(), sale.getBillAmount()));
        }
        barChart.getData().add(series);
    }

}
