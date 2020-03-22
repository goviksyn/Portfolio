package com.problem.protfolio.message;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SecurityEvent {

    @Id
    String ticker;
    Double price;


    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SecurityEvent{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                '}';
    }

}
