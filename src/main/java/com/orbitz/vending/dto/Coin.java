package com.orbitz.vending.dto;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 10:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Coin {

    private String coinType;
    private int count;
    private double value;


    public Coin(String coinType, int count)
    {
        this.setCount(count);
        this.setCoinType(coinType);
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }




}
