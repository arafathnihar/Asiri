package Controller;

import Model.NotificationModel;
import java.net.*;
import java.util.*;
import javafx.fxml.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.*;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author testing
 */
public class DashboardController implements Initializable {

    @FXML
    private VBox invoiceTabVbx;
    @FXML
    private VBox billTabVbx;
    @FXML
    private VBox productTabVbx;
    @FXML
    private VBox distributerTabVbx;
    @FXML
    private VBox notificationTabVbx;
    @FXML
    private VBox salesTabVbx;
    @FXML
    private ImageView notifyingImage;
    @FXML
    private Tab invoiceTab;
    @FXML
    private Tab billTab;
    @FXML
    private Tab productTab;
    @FXML
    private Tab distributerTab;
    @FXML
    private Tab notificationTab;
    @FXML
    private Tab saelsTab;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        invoiceTab.setOnSelectionChanged((Event t) -> {
            if (invoiceTab.isSelected()) {
                reloadInvoiceTab();
            }
        });
        billTab.setOnSelectionChanged((Event t) -> {
            if (billTab.isSelected()) {
                reloadBillTab();
            }
        });
        productTab.setOnSelectionChanged((Event t) -> {
            if (productTab.isSelected()) {
                reloadProductTab();
            }
        });
        distributerTab.setOnSelectionChanged((Event t) -> {
            if (distributerTab.isSelected()) {
                reloadDistributerTab();
            }
        });
        notificationTab.setOnSelectionChanged((Event t) -> {
            if (notificationTab.isSelected()) {
                reloadNotificationTab();
            }
        });
        saelsTab.setOnSelectionChanged((Event t) -> {
            if (saelsTab.isSelected()) {
                reloadSalesTab();
            }
        });
        try {
            invoiceTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Invoice.fxml")));
            billTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Bill.fxml")));
            productTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Product.fxml")));
            distributerTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Distributer.fxml")));
            NotificationModel nm = new NotificationModel();
            if ((nm.minStockNotification() != null && !nm.minStockNotification().isEmpty())
                || (nm.expireNotification()!= null && !nm.expireNotification().isEmpty())) {
                Image i = new Image("/resource/images/notificationNotChecked.png");
                notifyingImage.setImage(i);
            }
            notificationTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Notification.fxml")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    public void reloadInvoiceTab() {
        invoiceTabVbx.getChildren().clear();
        try {
            invoiceTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Invoice.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void reloadBillTab() {
        billTabVbx.getChildren().clear();
        try {
            billTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Bill.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void reloadProductTab() {
        productTabVbx.getChildren().clear();
        try {
            productTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Product.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void reloadDistributerTab() {
        distributerTabVbx.getChildren().clear();
        try {
            distributerTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Distributer.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     */
    public void reloadNotificationTab() {
        NotificationModel nm = new NotificationModel();
        if ((nm.minStockNotification() != null && !nm.minStockNotification().isEmpty())
                || (nm.expireNotification()!= null && !nm.expireNotification().isEmpty())) {
            Image i = new Image("/resource/images/notificationNotChecked.png");
            notifyingImage.setImage(i);
        } else {
            Image i = new Image("/resource/images/notificationChecked.png");
            notifyingImage.setImage(i);
        }
        notificationTabVbx.getChildren().clear();
        try {
            notificationTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Notification.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     */
    public void reloadSalesTab() {
        salesTabVbx.getChildren().clear();
        try {
            salesTabVbx.getChildren().setAll((Node) FXMLLoader.load(getClass().getResource("/resource/Sales.fxml")));
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
