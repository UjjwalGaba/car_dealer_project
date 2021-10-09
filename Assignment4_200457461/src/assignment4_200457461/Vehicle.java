package assignment4_200457461;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Vehicle {

    private String Make, Model, Condition, Cylinders, Color, soldOn, Image;
    private int Year, Doors, Price;

    public Vehicle(String Make, String Model, String Condition, String Cylinders, String Color, String soldOn, String Image, int Year, int Doors, int Price) {
        this.Make = Make;
        this.Model = Model;
        this.Condition = Condition;
        this.Cylinders = Cylinders;
        this.Color = Color;
        this.soldOn = soldOn;
        this.Image = Image;
        this.Year = Year;
        this.Doors = Doors;
        this.Price = Price;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String Make) {
        this.Make = Make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String Condition) {
        this.Condition = Condition;
    }

    public String getCylinders() {
        return Cylinders;
    }

    public void setCylinders(String Cylinders) {
        this.Cylinders = Cylinders;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getSoldOn() {
        return soldOn;
    }

    public void setSoldOn(String soldOn) {
        this.soldOn = soldOn;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public int getDoors() {
        return Doors;
    }

    public void setDoors(int Doors) {
        this.Doors = Doors;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public ImageView getImageView() {
        ImageView imageView = null;
        try {
            imageView = new ImageView(new Image(new FileInputStream(Image)));
            imageView.setFitWidth(80);
            imageView.setFitHeight(60);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return imageView;
    }

    public void saveVehicle() {
        try {
            String Vehicle = getMake() + "," + getModel() + "," + getCondition() + "," + getCylinders() + "," + getYear() + "," + getDoors() + "," + getPrice() + "," + getColor() + "," + getImage() + "," + getSoldOn();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./datafiles/carsData.txt", true));
            bufferedWriter.newLine();
            bufferedWriter.append(Vehicle);
            bufferedWriter.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public String getVehicleString() {
        String Vehicle = getMake() + "," + getModel() + "," + getCondition() + "," + getCylinders() + "," + getYear() + "," + getDoors() + "," + getPrice() + "," + getColor() + "," + getImage() + "," + getSoldOn();
        return Vehicle;
    }
}
