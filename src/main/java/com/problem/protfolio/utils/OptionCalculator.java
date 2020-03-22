package com.problem.protfolio.utils;


import com.problem.protfolio.schema.SecurityStaticData;

//Logic for Cumulative prob borrowed from internet.
public class OptionCalculator {

    public static Double getOptionPrice(String tickers, Double underLyingPrice, SecurityStaticData securityStaticData) {
        double stockPrice = underLyingPrice;
        double strikePrice = securityStaticData.getStrike();
        double interest = 0.02; //2 percent
        double volatility = 0.2; //assumed
        double maturity = securityStaticData.getMaturity();

        Double d1 = (Math.log(stockPrice / strikePrice) + (interest + Math.pow(volatility, 2) / 2) * maturity) / (volatility * Math.sqrt(maturity));
        Double d2 = d1 - (volatility * Math.sqrt(maturity));

        double result;
        if (securityStaticData.getType().equalsIgnoreCase("Call"))
            result = (stockPrice * cumProb(d1)) - (strikePrice * Math.exp(-interest * maturity) * cumProb(d2));
        else
            result = (strikePrice * Math.exp(-interest * maturity) * (cumProb(-d2))) - (stockPrice * (cumProb(-d1)));

        return result;
    }

    private static double cumProb(double x) {
        return 0.5 * (1 + erf(x / Math.sqrt(2)));
    }

    private static double erf(double z) {
        int nTerms = 315;
        double runningSum = 0;
        for (int n = 0; n < nTerms; n++) {
            runningSum += Math.pow(-1, n) * Math.pow(z, 2 * n + 1) / (factorial(n) * (2 * n + 1));
        }
        return (2 / Math.sqrt(Math.PI)) * runningSum;
    }

    private static double factorial(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        return n * factorial(n - 1);
    }
}
