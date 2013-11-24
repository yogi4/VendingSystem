package com.orbitz.vending.enums;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 8:37 PM
 * To change this template use File | Settings | File Templates.
 */
public enum SodaMenuItems {

    PEPSI(0.75),
    COKE(0.75),
    DIET_COKE(0.75),
    SPRITE(0.75),
    DEW(0.75),
    REDBULL(1.75),
    DRPEPPER(0.75),
    SEVENUP(0.75),
    MIST(0.15),
    ROOTBEER(0.75),
    CRUSH(0.75),
    JOLT(0.20);

    private double value;

    private SodaMenuItems(double value) {
        this.value = value;
    }

    public double getValue()
    {
        return this.value;
    }

}
