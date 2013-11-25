package com.orbitz.vending;

import com.orbitz.vending.dto.Coin;
import com.orbitz.vending.dto.SodaCan;
import com.orbitz.vending.dto.VendItem;
import com.orbitz.vending.enums.SodaMenuItems;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Yogi
 * Date: 11/23/13
 * Time: 10:37 PM
 * This class has the implemenation of Soda Vending Machine it implements VendingMachine
 * Extracted ProcessBalance as seperate class since it has some helper methods that can
 * be used by other impelementations of Vending Machine
 */
public class SodaVendingMachine implements VendingMachine{
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    //TODO move this to constants file
   private  static final String OUT_OF_STOCK_MESSAGE = "We are sorry; we ran out of selected item, Please pick other item or hit cancel";
   private  static final String NOT_ENOUGH_BALANCE   = "You do not have enough balance to complete this transaction";

    /* Using Map to store Soda Cans with their count , this is populated from SodaMenu Items */
    /* Chose Map because of the O(1) time for item retrieval and updating */
    /* Alternatively I Could use hybrid of Array and HashMap for indexing or retrieving or even better "ArrayHashMap"
    https://lucene.apache.org/core/3_5_0/api/all/org/apache/lucene/util/collections/ArrayHashMap.html */
    private  Map<String,SodaCan> sodaCanMap;

    /** Helper Class for Balance Transactons ,If application is running as multiple instance , we can make this a singleton */
    /* Extracted for resusability */
   private  ProcessBalance processBalance ;

    /** Helper module for dispense Coins */
    /* Extracted for resusability */
    DispenseCoins dispenseCoins;

    /* Transaction Message */
    String transactionMessage;

    /**
     * Initialize the Soda Menu Items
     */
    public SodaVendingMachine()
    {
        logger.setLevel(Level.WARNING);
        transactionMessage= "";
        processBalance = new ProcessBalance();
        dispenseCoins = new DispenseCoins();
        sodaCanMap = new HashMap<String, SodaCan>();

        for(SodaMenuItems sodaMenuItem : SodaMenuItems.values())
        {

            try{
            /* Can be customised to load from a text file , for simplicity using Enum values */
            AtomicReference<SodaCan> sd = new AtomicReference<SodaCan>();
            sd.set(new SodaCan(sodaMenuItem.name(), sodaMenuItem.getValue()));
            sodaCanMap.put(sodaMenuItem.name(), sd.get());
            } catch(Exception e)
            {
                logger.log(Level.SEVERE,e.getMessage());
            }

        }

    }




    /**
     *   Ability to insert money
     *   This can be called from any UI , currently using swing but can be extended to
     *   webservice with a wrapper
     *   @param value double currency value
     */
    @Override
    public void insertMoney(double value) {
        processBalance.addValue(value);

    }

    /**
     * Current balance in string format with Currency indicator Using Locale
     * @return  balance in String format
     * @serialData
     */
    @Override
    public String getBalance() {
        return processBalance.getBalanceValue();
    }


    /**
     * Method to dispense SodaCan
     * Checks to see if there is sufficient balance for the selected Item .
     * Checks to see if there is enough stock to Vend
     * @param itemType type of item to be vended
     * @return
     */
    @Override
    public VendItem dispense(String itemType) {

        VendItem sodaCan = null;  // null object pattern

        SodaCan item = sodaCanMap.get(itemType);      // Retrive element from the Map and look at the count of items.
         if (item == null)
         {
             transactionMessage = "Invalid Entry";    //never gets here but good to have this
             return sodaCan;
         }

        int itemCount = item.getItemCount();

        if (processBalance.hasSufficientBalance(item.getItemPrice()))
        {
             if (itemCount > 0)
             {
                processBalance.removeItemValue(item.getItemPrice());  //update the balance
                item.setItemCount(itemCount - 1);  // Decrease the count
                updateItemInventory(item);         // Constant Time
                transactionMessage = item.getItemName() + " Successfully Dispensed";
                return item;
              }
              else
             {
               transactionMessage = OUT_OF_STOCK_MESSAGE;
             }
        } else {
                transactionMessage = NOT_ENOUGH_BALANCE;
        }
        return sodaCan;
    }

    @Override
    public String getTransactionMessage() {
        return transactionMessage;
    }


    /**
     * Uses helper classes to dispense coins
     * @return collection of Coin objects with coin types and count
     */
    @Override
    public Collection<Coin> dispenseCoins() {

        double currBalance = processBalance.getCurrentBalance();
        reset();
        return dispenseCoins.dispenseCoins(currBalance);
    }


    /**
     * Update Vending machine inventory
     * Uses map so the transactions are fast
     * this can be extended to a web service to persist the data or
     * since we are using objects it's simple to Map them using ORM
     * @throws  UnsupportedOperationException, ClassCastException , NullPointerException
     * @param item  SodaCan
     * @warn Using Java 1.7 which provides capability for multi catch exceptions.
     */
    private void updateItemInventory(VendItem item)
    {   try
        {
            String itemType = item.getItemName();
            sodaCanMap.remove(itemType);              // Constant time
            sodaCanMap.put(itemType, (SodaCan)item);  //Constant time
         }  //catch (UnsupportedOperationException | ClassCastException | NullPointerException ex)
    catch (UnsupportedOperationException ex)
         {
             logger.log(Level.SEVERE, ex.getMessage());
         }

    }


    private static void main(String[] args) {



    }
    /**
     * Reset Balance to zero
     */
    public void reset() {
        processBalance.reset();
    }
}
