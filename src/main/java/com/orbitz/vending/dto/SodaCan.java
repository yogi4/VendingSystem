package com.orbitz.vending.dto;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public  class SodaCan implements VendItem {

    private String itemName = "";
    private double itemPrice = 0;
    private int sodaitemSize =0;
    private int itemCount=0;
    private static final int MAX_COUNT =25;

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

   @Override
    public double getItemPrice() {
        return itemPrice;
    }

    @Override
    public void setItemPrice(double price) {
        this.itemPrice = price;
    }

    @Override
    public int getItemSize() {
        return sodaitemSize;
    }

    @Override
    public void setItemSize(int itemSize) {
        this.sodaitemSize = itemSize;
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
