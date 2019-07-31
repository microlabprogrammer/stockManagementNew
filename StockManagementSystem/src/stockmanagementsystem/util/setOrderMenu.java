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
public class setOrderMenu {
    String Brand, Model, Color, Qty, Status;

    public setOrderMenu(String Brand, String Model, String Color, String Qty, String Status) {
        this.Brand = Brand;
        this.Model = Model;
        this.Color = Color;
        this.Qty = Qty;
        this.Status = Status;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String Qty) {
        this.Qty = Qty;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

}
