/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_200457461;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewVehicleController implements Initializable {

    @FXML
    TextField make;
    @FXML
    TextField model;
    @FXML
    TextField condition;
    @FXML
    TextField cylinder;
    @FXML
    TextField year;
    @FXML
    TextField doors;
    @FXML
    TextField price;
    @FXML
    TextField color;
    @FXML
    DatePicker soldOn;
    @FXML
    Label error;

    String imagePath = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void addVehicle(ActionEvent event) {
        if (make.getText().equals("")) {
            error.setText("Please Enter Make");
        } else if (model.getText().equals("")) {
            error.setText("Please enter Model");
        } else if (condition.getText().equals("")) {
            error.setText("Please enter Condition");
        } else if (cylinder.getText().equals("")) {
            error.setText("Please enter Cylinders");
        } else if (year.getText().equals("")) {
            error.setText("Please enter Year");
        } else if (doors.getText().equals("")) {
            error.setText("Please enter Doors");
        } else if (price.getText().equals("")) {
            error.setText("Please enter Price");
        } else if (color.getText().equals("")) {
            error.setText("Please enter Color");
        } else if (imagePath.equals("")) {
            error.setText("Please Select Image");
        } else {
            String isSold = "";
            if (soldOn.getValue() == null) {
                isSold = "Available";
            } else {
                isSold = soldOn.getValue().toString();
            }
            try {
                Vehicle v = new Vehicle(make.getText(), model.getText(), condition.getText(), cylinder.getText(), color.getText(), isSold, imagePath, Integer.parseInt(year.getText()), Integer.parseInt(doors.getText()), Integer.parseInt(price.getText()));
                v.saveVehicle();
                Stage stage = (Stage) make.getScene().getWindow();
                stage.close();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }

        }
    }

    public void uploadImage() {
        try {
            FileChooser filechooser = new FileChooser();
            File imageFile = filechooser.showOpenDialog(null);
            if (imageFile != null) {
                Files.copy(Paths.get(imageFile.getAbsolutePath()), Paths.get("./datafiles/" + imageFile.getName()), StandardCopyOption.REPLACE_EXISTING);
            }
            imagePath = "./datafiles/" + imageFile.getName();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }
}
