package com.orbitz.vending.enums;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public enum USCurrencyVal {
    $1(1.00),
    $2(2.00),
    $5(5.00),
    $10(10.00),
    $20(20.00),
    $50(50.00),
    $100(100.00),
    PENNY(0.01),
    NICKEL(0.05),
    DIME(0.10),
    QUARTER(0.25),
    OTHER(0.0);

    private double value;

    private USCurrencyVal(double value) {
        this.value = value;
    }

    public double getValue()
    {
          return this.value;
    }
};

