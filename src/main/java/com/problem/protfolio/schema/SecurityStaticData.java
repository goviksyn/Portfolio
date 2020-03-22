package com.problem.protfolio.schema;

import javax.persistence.*;

@Entity
public class SecurityStaticData {
    @Id
    private String ticker;
    @Column(nullable = true)
    private Long strike;
    @Column(nullable = true)
    private Integer maturity;
    private String type;

    @Column(nullable = true)
    private String underlying;

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setStrike(Long strike) {
        this.strike = strike;
    }

    public String getTicker() {
        return ticker;
    }

    public Long getStrike() {
        return strike;
    }

    public Integer getMaturity() {
        return maturity;
    }

    public void setMaturity(Integer maturity) {
        this.maturity = maturity;
    }

    @Override
    public String toString() {
        return "SecurityStaticData{" +
                "ticker='" + ticker + '\'' +
                ", strike=" + strike +
                ", maturity=" + maturity +
                ", type='" + type + '\'' +
                '}';
    }
}
