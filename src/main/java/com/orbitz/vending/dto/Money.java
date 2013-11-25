package com.orbitz.vending.dto;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 2:44 PM
 * Money data transfer object
 * The assumptions are that this is in USD but this class can be used to
 * suit global currencies incase Orbitz wants to expand their TestProcess to
 * different currency zones
 * This can be a currency note or a coin
 */
public class Money {

    /* Boolean to check if the value is Valid currency */
    private boolean isValidCurrency;

    /* Actual currency value  can be a bill or a coin */
    private double value = 0.0;



    /**
     * Retrieves currency value
     * @return double value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets currency value
     * @param  value  double
     */
    public void setValue(double value) {
        this.value = value;
    }


    /**
     * Retrieves if currency is valid
     * @return  boolean - true if valid
     */
    public boolean isValidCurrency() {
        return isValidCurrency;
    }

    /**
     * Sets if the currency is valid
     * @param validCurrency boolean
     */
    public void setValidCurrency(boolean validCurrency) {
        isValidCurrency = validCurrency;
    }


}
