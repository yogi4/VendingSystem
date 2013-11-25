package com.orbitz.vending;

import com.orbitz.vending.dto.Coin;
import com.orbitz.vending.dto.VendItem;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: yogi
 * Date: 11/23/13
 * Time: 10:21 PM
 * This interface provides a pattern for Vending Machine implementation.
 * Having this interface provides flexibility to enhance this application
 * in the future to work for different Vending Machnies ( Candy etc)
 */
public interface VendingMachine {

    /**
     * Method to insert money into TestProcess Machine
     * @param value  double value
     */
    public void insertMoney(double value);

    /**
     * Get Current Balance
     * @return balance in Currency terms
     */
    public String getBalance();

    /**
     * Used Coin object to extend the application to USD or Euro or any other coins
     * @return  Collection of coins to be dispensed
     */
    public Collection<Coin>  dispenseCoins();

    /**
     * Dispense VendItem - Can be soda or any other item
     * @param itemType type of item to be vended
     * @return VendItem , SodaCan in this application
     * @see implementation SodaVendingMachine.dispense()
     */
    public VendItem dispense(String itemType);

    /**
     * Get Transaction Message
     * @return string Any messages related to transaction
     */
    public String getTransactionMessage();

    /**
     * Resets the process
     */
    public void reset();
}
