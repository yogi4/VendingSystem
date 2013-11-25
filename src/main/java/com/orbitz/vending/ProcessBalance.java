package com.orbitz.vending;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Yogi
 * Date: 11/23/13
 * Time: 2:59 PM
 * This class has process in dealing with balance ; accepts currency as input and has helper methods to process balance
 * Irrespective of user interface , Swing UI or webservice or command line , this class
 * has the business logic to handle balance.
 *
 */
 class ProcessBalance {

    /* Modify locale to take values dynamically */
     private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
     private double currentBalance  = 0;

    /**
     * Helper method : Total Value
     * @return double Total Value
     */
    protected double getCurrentBalance()
    {
        return currentBalance;
    }

    /**
     * Set total value
     * @param value  double
     */
    protected void setCurrentBalance(double value) {

        currentBalance =value;
    }

    /**
     * Add value to current balance
     * @param value
     */
    protected void addValue(double value)
    {
        double addedvalue = getCurrentBalance() + value;
        setCurrentBalance(addedvalue);
    }

    /**
     * Return the value in currency format
     * @return String currency format $#.##
     */
    protected String getBalanceValue()
    {

        return   currencyFormatter.format(getCurrentBalance()) ;
    }

    /**
     * Remove the value from currentbalance
     * @param value  double currencyvalue
     */

    protected void removeItemValue(double value)
    {

        double resultValue = getCurrentBalance() - value;

        if(resultValue > 0)
        {
            setCurrentBalance(resultValue);
        } else
        {
            setCurrentBalance(0);
        }
    }
    /**
     * Validate currency depending on the locale and injected object
     * @param value double currencyValue
     * @return  boolean true if valid or false
     */
    protected boolean validateCurrency(double value)
    {
            return true;
       //TODO implement this

    }


    protected void reset() {
        setCurrentBalance(0);
    }


    /**
     * Checks to see if there is enough balance to vend
     * @param itemValue  itemValue
     * @return  boolean true if there is enough balance
     */
    protected boolean hasSufficientBalance( double itemValue)
    {
        double totalValue = getCurrentBalance();
        return (Double.compare(totalValue,itemValue) > 0);
    }

}
