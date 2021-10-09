/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment4_200457461;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @FXML
    TableView<Vehicle> vehicleTableView;
    @FXML
    TableColumn<Vehicle, String> makeColumn;
    @FXML
    TableColumn<Vehicle, String> modelColumn;
    @FXML
    TableColumn<Vehicle, String> conditionColumn;
    @FXML
    TableColumn<Vehicle, String> cylinderColumn;
    @FXML
    TableColumn<Vehicle, String> colorColumn;
    @FXML
    TableColumn<Vehicle, String> soldOnColumn;
    @FXML
    TableColumn<Vehicle, Integer> yearColumn;
    @FXML
    TableColumn<Vehicle, Integer> doorColumn;
    @FXML
    TableColumn<Vehicle, Integer> priceColumn;
    @FXML
    TableColumn<Vehicle, ImageView> imageColumn;

    ObservableList<Vehicle> list;
    int count;
    int total;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list = FXCollections.observableArrayList();
        makeColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        conditionColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("condition"));
        cylinderColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("Cylinders"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("Color"));
        soldOnColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("soldOn"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("Year"));
        doorColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("Doors"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("Price"));
        imageColumn.setCellValueFactory(new PropertyValueFactory<Vehicle, ImageView>("ImageView"));
        readData();
    }

    public void readData() {
        try {
            list.clear();
            BufferedReader br = new BufferedReader(new FileReader("./datafiles/carsData.txt"));

            String vehicle = br.readLine();
            while (vehicle != null) {
                list.add(new Vehicle(vehicle.split(",")[0], vehicle.split(",")[1], vehicle.split(",")[2], vehicle.split(",")[3], vehicle.split(",")[7], vehicle.split(",")[9], vehicle.split(",")[8], Integer.parseInt(vehicle.split(",")[4]), Integer.parseInt(vehicle.split(",")[5]),
                        Integer.parseInt(vehicle.split(",")[6])
                ));
                vehicle = br.readLine();
            }

            Predicate<Vehicle> sold = v -> !v.getSoldOn().equals("Available");
            vehicleTableView.setItems(list);

            count = 0;
            total = 0;
            for (Vehicle v : list) {
                if (!"Available".equals(v.getSoldOn())) {
                    count++;
                    total = total + v.getPrice();
                }
            }
            vehicleTableView.getSelectionModel().select(0);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public void showSoldVehicles() {

        Predicate<Vehicle> sold = v -> !v.getSoldOn().equals("Available");
        vehicleTableView.setItems(list.filtered(sold));
        vehicleTableView.getSelectionModel().select(0);
    }

    public void showAvailableVehicles() {

        Predicate<Vehicle> available = v -> v.getSoldOn().equals("Available");
        vehicleTableView.setItems(list.filtered(available));
        vehicleTableView.getSelectionModel().select(0);
    }

    public void showAllVehicles() {
        readData();
    }

    public void viewCompanyDetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewCompany.fxml"));
            Parent parent = loader.load();
            ViewCompanyController vcc = loader.getController();
            vcc.setDetails("Total " + count + " Vehicles", "Total $" + total + " is profit till now.");
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }

    public void editCompanyDetails() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditCompany.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }

    public void newVehicle() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("NewVehicle.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }

    public void viewVehicle() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewVehicle.fxml"));
            Parent parent = loader.load();
            ViewVehicleController vvc = loader.getController();
            vvc.ShowVehicle(vehicleTableView.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }

    public void editVehicle() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditVehicle.fxml"));
            Parent parent = loader.load();
            EditVehicleController evc = loader.getController();
            evc.setVehicle(vehicleTableView.getSelectionModel().getSelectedItem());
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }

}
