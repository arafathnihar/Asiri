package Controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class Validator {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            Logger.getLogger(Asiri.class.getName()).log(Level.INFO, null, e);
            return false;
        }
        return true;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            Logger.getLogger(Asiri.class.getName()).log(Level.INFO, null, e);
            return false;
        }
        return true;
    }

    public static boolean isValidNotEmpty(TextField txtFeild, Label lbl) {
        if (txtFeild.getText().isEmpty()) {
            lbl.setText("Requird !");
            return false;
        } else {
            return true;
        }
    }

    public static void isEmpty(Pane p) {
        for (Node node : p.getChildren()) {
            System.out.println("Id: " + node.getId());
            if (node instanceof TextField) {
                ((TextField) node).setText("xxxxxx");
            }
        }
    }

}
