/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author testing
 */
public class Validator {
    /**
     *
     * @param s
     * @return
     */
    
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    
    /**
     *
     * @param s
     * @return
     */
    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    
    /**
     *
     * @return
     */
    public static boolean isValidNotEmpty(TextField txtFeild, Label lbl) {
        if (txtFeild.getText().isEmpty()) {
            lbl.setText("Requird !");
            return false;
        } else {
            return true;
        }
    }
    
    /**
     *
     * @return
     */
    public static void isEmpty(Pane p) {
        
       for (Node node : p.getChildren()) {
            System.out.println("Id: " + node.getId());
        if (node instanceof TextField) {
        // clear
        ((TextField)node).setText("xxxxxx");
    }
    }
   }

    
}
