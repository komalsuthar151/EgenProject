package com.egen.demo.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders", schema = "public")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "subtotal")
    private double subtotal;
    @Column(name = "tax")
    private double tax;
    @Column(name = "total")
    private double total;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Long getPaymentConfirmatioNumber() {
        return paymentConfirmatioNumber;
    }

    public void setPaymentConfirmatioNumber(Long paymentConfirmatioNumber) {
        this.paymentConfirmatioNumber = paymentConfirmatioNumber;
    }

    @Column(name = "payment_confirmation_number")
    private Long paymentConfirmatioNumber;

}
