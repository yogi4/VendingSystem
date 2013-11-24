package com.orbitz.vending;

import com.orbitz.vending.dto.Coin;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public interface VendingMachine {

    void insertMoney(double value);

    String getBalance();

    Collection<Coin>  dispenseCoins();


    VendItem dispense(String itemType);

     String getTransactionMessage();
}
