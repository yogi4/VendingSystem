package com.orbitz.vending

import com.orbitz.vending.ProcessBalance

/**
 * Created with IntelliJ IDEA.
 * User: Yogi
 * Date: 11/24/13
 * Time: 7:49 PM
 * Spock Rules
 */
class TestProcessBalance extends spock.lang.Specification {

    def  balance = new ProcessBalance();


    def "test getCurrentBalance"() {
        given:

        def processBalance = new ProcessBalance();

        when:
         processBalance.addValue(0.5)
        then:

         processBalance.getCurrentBalance() == 0.5;
    }

    def "test addValue"() {
        given:

         balance.currentBalance =3.45
        when:

        balance.addValue(21.0299999999999999)
        then:
       assert balance.currentBalance == 24.48
       assert balance.currentBalance!= 24.0299999999999999
    }


    def "test getBalanceValue"() {
        given:
          balance = new ProcessBalance();
        when:
          balance.setCurrentBalance(3.66)
        then:
          balance.getBalanceValue() == "\$3.66"

    }

    def "test removeItemValue"() {
        given:

        balance.currentBalance = 5

        when:
        balance.removeItemValue(1.5)

        then:
        balance.getCurrentBalance() == 3.5

    }



   def "test reset"() {
        given:
        balance.currentBalance = 22.45
        when:
         balance.reset()
        then:
        assert balance.getCurrentBalance()==0.0
       assert balance.getBalanceValue() == "\$0.00"
    }

    def "test hasSufficientBalance when itemValue is less than total Balance"() {
        given:
           balance.currentBalance = 5.35
        when:
            def itemValue = 3.0

        then:
           assert balance.hasSufficientBalance(itemValue) == Boolean.TRUE
    }

    def "test hasSufficientBalance when itemValue is greater than total Balance"() {
        given:
        balance.currentBalance = 5.35
        when:
        def itemValue = 7.0

        then:
        assert balance.hasSufficientBalance(itemValue) == Boolean.FALSE
    }

    def cleanup() {
        balance =null
    }

}
