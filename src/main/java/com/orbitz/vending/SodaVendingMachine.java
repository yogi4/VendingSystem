package com.orbitz.vending;

import com.orbitz.vending.dto.Coin;
import com.orbitz.vending.dto.SodaCan;
import com.orbitz.vending.enums.SodaMenuItems;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SodaVendingMachine implements VendingMachine{
    Map<String,SodaCan> sodaCanMap = new HashMap<String, SodaCan>();
    ProcessBalance processBalance = new ProcessBalance();
    String transactionMessage;
    public SodaVendingMachine()
    {
        for(SodaMenuItems sodaMenuItem : SodaMenuItems.values())
        {
            AtomicReference<SodaCan> sd = new AtomicReference<SodaCan>();
            sd.set(new SodaCan(sodaMenuItem.name(), sodaMenuItem.getValue()));
            sodaCanMap.put(sodaMenuItem.name(), sd.get());
            transactionMessage= "";
        }

    }



    @Override
    public void insertMoney(double value) {
        processBalance.addValue(value);

    }

    @Override
    public String getBalance() {
        return processBalance.getBalanceValue();
    }



    @Override
    public VendItem dispense(String itemType) {

        SodaCan item = sodaCanMap.get(itemType);
        int itemCount = item.getItemCount();

        if (processBalance.hasSufficientBalance(processBalance.getCurrentBalance(), item.getItemPrice())) {
             if (itemCount > 0)
             {
                processBalance.removeItemValue(item.getItemPrice());
                item.setItemCount(itemCount - 1);
                updateItemInventory(item);
                transactionMessage = item.getItemName() + " Successfully Dispensed";
                return item;
              }
              else
             {
               transactionMessage = "We are sorry; we ran out of selected item, Please pick other item or hit cancel ";
             }
        } else {
            transactionMessage = "You do not have enough balance to complete this transaction";
        }
        return null;
    }

    @Override
    public String getTransactionMessage() {
        return transactionMessage;
    }



    @Override
    public Collection<Coin> dispenseCoins() {
        return null;
    }

    private void updateItemInventory(VendItem item)
    {
        String itemType = item.getItemName();
        sodaCanMap.remove(itemType);              // Constant time
        sodaCanMap.put(itemType, (SodaCan)item);  //Constant time
    }


    public void reset() {
        processBalance.reset();
    }
}
