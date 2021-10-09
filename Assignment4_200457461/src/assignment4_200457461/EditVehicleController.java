/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_200457461;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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

public class EditVehicleController implements Initializable {

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
        if (price.getText().equals("")) {
            error.setText("Please enter Price");
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

    public void setVehicle(Vehicle vehicle) {
        make.setText(vehicle.getMake());
        model.setText(vehicle.getModel());
        condition.setText(vehicle.getCondition());
        cylinder.setText(vehicle.getCylinders());
        year.setText(String.valueOf(vehicle.getYear()));
        doors.setText(String.valueOf(vehicle.getDoors()));
        price.setText(String.valueOf(vehicle.getPrice()));
        color.setText(vehicle.getColor());
        imagePath = vehicle.getImage();
        make.setDisable(true);
        model.setDisable(true);
        condition.setDisable(true);
        cylinder.setDisable(true);
        year.setDisable(true);
        doors.setDisable(true);
        color.setDisable(true);
        if (!"Available".equals(vehicle.getSoldOn())) {
            soldOn.setDisable(true);
            price.setDisable(true);
        } else {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader("./datafiles/carsData.txt"));
                String line = bufferedReader.readLine();
                String data = "";
                int x = 0;
                while (line != null) {
                    if (!line.equals(vehicle.getVehicleString())) {
                        if (x == 0) {
                            data = line;
                        } else {
                            data = data + "\n" + line;
                        }
                        x++;
                    }
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./datafiles/carsData.txt"));
                bufferedWriter.write(data);
                bufferedWriter.close();

            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
    }
}
