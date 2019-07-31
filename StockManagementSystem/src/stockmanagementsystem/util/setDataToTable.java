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
public class setDataToTable {
    String paymentDueDate,paymentDate,amount,invoiceNumber;

    public setDataToTable(String paymentDueDate, String paymentDate, String amount, String invoiceNumber) {
        this.paymentDueDate = paymentDueDate;
        this.paymentDate = paymentDate;
        this.amount = amount;
        this.invoiceNumber = invoiceNumber;
    }

    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(String paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    
    
}
