package com.orbitz.vending.dto;

import com.orbitz.vending.VendItem;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public  class SodaCan extends VendItem {

    private String itemName = "";
    private double itemPrice = 0;
    private int sodaitemSize =0;
    private int itemCount=0;
    static int MAX_COUNT =50;

    public SodaCan(String itemName, double itemPrice)
    {
       this.itemName = itemName;
       this.itemPrice = itemPrice;
       this.itemCount = MAX_COUNT;
    }

    public SodaCan(String itemName, double itemPrice, int itemCount)
    {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getSodaitemSize() {
        return sodaitemSize;
    }

    public void setSodaitemSize(int sodaitemSize) {
        this.sodaitemSize = sodaitemSize;
    }




    @Override
    public double getItemPrice() {
        return itemPrice;
    }

    @Override
    public void setItemPrice(double price) {
        this.itemPrice = price;
    }

    @Override
    public void setItemName(String name) {
        this.itemName = name;
    }

    @Override
    public String getItemName() {
        return itemName;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
