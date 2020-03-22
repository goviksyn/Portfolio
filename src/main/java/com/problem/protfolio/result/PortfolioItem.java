package com.problem.protfolio.result;

public class PortfolioItem {
    public String ticker;
    public Integer quantity;
    public Double price ;
    public String side = "Buy";
    public String underlying;

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }


    public Double getPrice() {
        if (side.equalsIgnoreCase("Buy"))
            return price;
        else
            return price * -1;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PortfolioItem{" +
                "ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", side='" + side + '\'' +
                ", underlying='" + underlying + '\'' +
                '}';
    }
}
