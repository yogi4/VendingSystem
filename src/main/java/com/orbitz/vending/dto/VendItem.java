package com.orbitz.vending.dto;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VendItem
{
    public String getItemName();

    public void setItemName(String itemName);

    public double getItemPrice();

    public void setItemPrice(double itemPrice);

    public int getItemSize() ;

    public void setItemSize(int itemSize);

    public int getItemCount();

    public void setItemCount(int itemCount);

}
