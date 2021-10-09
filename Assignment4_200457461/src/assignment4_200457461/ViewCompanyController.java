/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_200457461;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ViewCompanyController implements Initializable {

    @FXML
    ImageView companyLogo;
    @FXML
    Label companyName;
    @FXML
    Label companyStreet;
    @FXML
    Label companyCity;
    @FXML
    Label companyProvince;
    @FXML
    Label companyPostal;
    @FXML
    Label soldCount;
    @FXML
    Label profitAmount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./datafiles/companyData.txt"));
            String companyLine = bufferedReader.readLine();
            companyName.setText(companyLine.split(",")[0]);
            companyStreet.setText(companyLine.split(",")[1]);
            companyCity.setText(companyLine.split(",")[2]);
            companyProvince.setText(companyLine.split(",")[3]);
            companyPostal.setText(companyLine.split(",")[4]);
            companyLogo.setImage(new Image(new FileInputStream(companyLine.split(",")[5])));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    public void setDetails(String sold, String profit) {
        soldCount.setText(sold);
        profitAmount.setText(profit);

    }
}
