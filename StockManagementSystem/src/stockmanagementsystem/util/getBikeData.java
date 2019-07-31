/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem.util;

/**
 *
 * @author DEWSRI DE MEL
 */
public class getBikeData {
    String brand,model,value,chassicNumber;

    public getBikeData(String brand, String model, String value, String chassicNumber) {
        this.brand = brand;
        this.model = model;
        this.value = value;
        this.chassicNumber = chassicNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getChassicNumber() {
        return chassicNumber;
    }

    public void setChassicNumber(String chassicNumber) {
        this.chassicNumber = chassicNumber;
    }

}
