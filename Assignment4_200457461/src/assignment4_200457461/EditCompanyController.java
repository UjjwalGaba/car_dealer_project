/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_200457461;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditCompanyController implements Initializable {

    @FXML
    TextField companyName;
    @FXML
    TextField companyStreet;
    @FXML
    TextField companyCity;
    @FXML
    TextField companyProvince;
    @FXML
    TextField companyPostal;
    @FXML
    Label error;

    public String imagePath = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCurrentData();
    }

    public void loadCurrentData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./datafiles/companyData.txt"));
            String companyLine = br.readLine();
            companyName.setText(companyLine.split(",")[0]);
            companyStreet.setText(companyLine.split(",")[1]);
            companyCity.setText(companyLine.split(",")[2]);
            companyProvince.setText(companyLine.split(",")[3]);
            companyPostal.setText(companyLine.split(",")[4]);
            imagePath = companyLine.split(",")[5];

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void Update(ActionEvent event) {
        if (companyName.getText().equals("")) {
            error.setText("Please enter Company Name");
        } else if (companyStreet.getText().equals("")) {
            error.setText("Please enter Company Street");
        } else if (companyCity.getText().equals("")) {
            error.setText("Please enter Company City");
        } else if (companyProvince.getText().equals("")) {
            error.setText("Please enter Company Province");
        } else if (companyPostal.getText().equals("")) {
            error.setText("Please enter Company Postal");
        } else if (imagePath.equals("")) {
            error.setText("Please upload Company Logo Image");
        } else {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("./datafiles/companyData.txt"));
                writer.append(companyName.getText() + "," + companyStreet.getText() + "," + companyCity.getText() + "," + companyProvince.getText() + "," + companyPostal.getText() + "," + imagePath);
                writer.close();
                Stage stage = (Stage) companyName.getScene().getWindow();
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
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}
