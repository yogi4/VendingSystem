package com.orbitz.vending
import com.orbitz.vending.dto.Coin
import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: Yogi
 * Date: 11/24/13
 * Time: 9:14 PM
 * Spock Rules
 */
class TestSodaVendingMachine extends Specification {


    def sodaVendingMachine = new SodaVendingMachine();
    def processBalance = new ProcessBalance();


    def "test insertMoney"() {
        given:
             processBalance.currentBalance=0
        when:
             sodaVendingMachine.insertMoney(5.00)
        then:
             sodaVendingMachine.getBalance()== "\$5.00"

    }

    def "test getBalance"() {
        given:

            sodaVendingMachine.insertMoney(25.00)

        when:
             def val = sodaVendingMachine.getBalance()

        then:
          assert val == "\$25.00"
    }

    def "test dispense valid item"() {
        given:
        sodaVendingMachine = new SodaVendingMachine()
        def itemType = "PEPSI"
        sodaVendingMachine.insertMoney(10)

        when:

        def item = sodaVendingMachine.dispense(itemType)

        then:
       item.itemName == "PEPSI"
       item.itemPrice == 0.75
       item.itemCount == 24
    }


    def "test dispense invalid item"(){
        given:
        sodaVendingMachine = new SodaVendingMachine()
        def itemType = "BLUE"
        sodaVendingMachine.insertMoney(10)

        when:

        def item = sodaVendingMachine.dispense(itemType)

        then:
        item == null

    }
    def "test dispense valid item without balance"() {
        given:
        sodaVendingMachine = new SodaVendingMachine()
        def itemType = "PEPSI"
        sodaVendingMachine.insertMoney(0)

        when:

        def item = sodaVendingMachine.dispense(itemType)

        then:
         item ==null
        sodaVendingMachine.getTransactionMessage()=="You do not have enough balance to complete this transaction"
    }


    def "test getTransactionMessage"() {
        given:
        sodaVendingMachine = new SodaVendingMachine()
        def itemType = "PEPSI"
        sodaVendingMachine.insertMoney(10)

        when:

        def item = sodaVendingMachine.dispense(itemType)

        then:
        sodaVendingMachine.getTransactionMessage()=="PEPSI Successfully Dispensed"
    }

    def "test dispenseCoins"() {
        given:
           sodaVendingMachine.insertMoney(2.10)

        when:
         sodaVendingMachine.dispense("PEPSI")
          def result = sodaVendingMachine.dispenseCoins()
        then:
        result.size() == 2
        result.each
                {
                    it instanceof Coin
                }

    }

    def "test dispenseCoins count"() {
        given:
        sodaVendingMachine.insertMoney(0.90)

        when:
        sodaVendingMachine.dispense("PEPSI")
        def result = sodaVendingMachine.dispenseCoins()
        then:
        result.size() == 2

        for( Coin c : result.iterator())
        {
            when:
            c.coinType == "DIME"
            then:
            assert c.count ==1
        }

    }

   def "test reset"() {
        given:
           sodaVendingMachine.insertMoney(3)
        when:
          sodaVendingMachine.getBalance() == "\$3.00"
          sodaVendingMachine.reset()
        then:
          assert sodaVendingMachine.getBalance()=="\$0.00"
   }
}
