package com.orbitz.vending;

import com.orbitz.vending.dto.Coin;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Yogi
 * Date: 11/24/13
 * Time: 12:09 PM
 * Helper class to break down coins
 * This can be reused for extension to different vending machines or coins
 * This class can be made dynamic by injecting an ENUM or values from a file for currency
 *
 */
public class DispenseCoins {


    private static double QUARTER = 0.25;
    private static double DIME = 0.10;
    private static double NICKEL = 0.05;


    /**
     * This method computes the minimal number of coins required for dispensing
     * Since the requirement states that QUARTER, DIME , NICKEL are valid coins
     * TO get minimal number of coins we start with QUARTER and then DIME and Nickel
     * @param balance  Balance to be dispensed
     * @return  Collection of Coins
     */
    public  Collection<Coin> dispenseCoins(double balance)
    {
        ArrayList<Coin> coins = new ArrayList<Coin>();

        double dispenseBalance =  Math.round(balance*100.00)/100.00;


        dispenseBalance =  breakDownCoins(QUARTER,dispenseBalance, coins);
        dispenseBalance =   breakDownCoins(DIME,dispenseBalance,coins);
        dispenseBalance = breakDownCoins(NICKEL,dispenseBalance,coins);

        if((Double.compare(dispenseBalance,dispenseBalance) >=0))
        {
            //Log this value
        }

        return coins;
    }






    public  double breakDownCoins(double coinValue, double balance, Collection<Coin> coins)
    {
        String coinType ="";
        if(coinValue == QUARTER)
        {
            coinType = "QUARTER";
        }else if(coinValue == DIME)
        {
            coinType = "DIME";
        }else
        {
            coinType = "NICKEL";
        }


        if((Double.compare(balance,coinValue) >=0))
        {
            int coinCount =  (int)(balance/coinValue);
            coins.add(new Coin(coinType,coinCount));
            balance = Math.round((balance % coinValue) *100.00)/100.00;

        }
        return balance;
    }


}
