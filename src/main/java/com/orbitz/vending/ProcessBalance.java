package com.orbitz.vending;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 2:59 PM
 * This class accepts currency as input and has helper methods to process balance
 * Irrespective of user interface , Swing UI or webservice or command line , this class
 * has the business logic to handle balance.
 *
 */
public class ProcessBalance {

    /* Modify locale to take values dynamically */
    Locale locale = Locale.US;

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
    private double currentBalance  = 0;

    /**
     * Helper method : Total Value
     * @return double Total Value
     */
    public double getCurrentBalance()
    {
        return currentBalance;
    }

    /**
     * Set total value
     * @param value  double
     */
    private void setCurrentBalance(double value) {

        currentBalance =value;
    }

    /**
     * Add value to current balance
     * @param value
     */
    public void addValue(double value)
    {
        double addedvalue = getCurrentBalance() + value;
        setCurrentBalance(addedvalue);
    }

    /**
     * Return the value in currency format
     * @return
     */
    public String getBalanceValue()
    {

        return   currencyFormatter.format(getCurrentBalance()) ;
    }

    /**
     * Remove the value from currentbalance
     * @param value  double currencyvalue
     */

    public void removeItemValue(double value)
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
    public boolean validateCurrency(double value)
    {
            return true;
        /* TODO */

    }


    public static void main(String[] args) {
        Locale locale = Locale.US;
        Currency curr = Currency.getInstance(locale);
        System.out.println("Locale's currency code:" + curr.getCurrencyCode());
        Scanner input = new Scanner(System.in);


     }

    public void reset() {
            /** TODO */
        //Vend return the balance if any
        setCurrentBalance(0);
    }




    public boolean hasSufficientBalance(double totalValue, double itemValue)
    {
        return (Double.compare(totalValue,itemValue) > 0);
    }

}
