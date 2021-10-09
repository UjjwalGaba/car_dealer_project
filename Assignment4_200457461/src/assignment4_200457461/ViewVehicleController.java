/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_200457461;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Window;

public class ViewVehicleController implements Initializable {

    @FXML
    Label make;
    @FXML
    Label model;
    @FXML
    Label condition;
    @FXML
    Label doors;
    @FXML
    Label year  ;
    @FXML
    Label price;
    @FXML
    Label cylinder;
    @FXML
    Label color;
    @FXML
    Label soldOn;
    @FXML
    ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void ShowVehicle(Vehicle vehicle) {
        make.setText(vehicle.getMake());
        model.setText(vehicle.getModel());
        condition.setText(vehicle.getCondition());
        cylinder.setText(vehicle.getCylinders());
        year.setText(String.valueOf(vehicle.getYear()));
        doors.setText(String.valueOf(vehicle.getDoors()));
        price.setText(String.valueOf(vehicle.getPrice()));
        color.setText(vehicle.getColor());
        soldOn.setText(vehicle.getSoldOn());
        image.setImage(vehicle.getImageView().getImage());
    }

}
