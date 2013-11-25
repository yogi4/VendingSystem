package com.orbitz.vending.view;

import com.orbitz.vending.*;
import com.orbitz.vending.dto.Coin;
import com.orbitz.vending.dto.VendItem;
import com.orbitz.vending.enums.SodaMenuItems;
import com.orbitz.vending.enums.USCurrencyVal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yogi
 * Date: 11/23/13
 * Time: 4:49 PM
 * Used Swing as UI , this class acts as View and Controller
 * This class can be tweaked significantly to extract controller part by using
 * Observer pattern , but because of time limitations I chose to go with combining
 * both View and Controller into one class.
 */
public class OrbitzSodaVending extends JFrame implements ActionListener {

    private static int gridSize = 4;
    private JFrame vendFrame = new JFrame("Orbitz Vending Machine");
    private JLabel balanceLabel;
    private JLabel balanceHeaderLabel = new JLabel("BALANCE: ");
    private JLabel coinDispenseLabel;
    private JLabel sodaDispensed;
    private JButton dispenseButton;
    private JButton resetButton;
    private JButton cancelButton;
    private static  String INIT_SELECTION = "Please Make a Selection";
    private static String INITIAL_BALANCE_VALUE = "$0.00";
    private static String COIN_RETURN_MESSAGE="Press Button for Coin Return";
    private VendingMachine sodaVendingMachine = new SodaVendingMachine();
    private  List<JButton> buttons = new ArrayList<JButton>();
    public OrbitzSodaVending()
    {
        vendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vendFrame.setVisible(true);
        vendFrame.setResizable(false);
        vendFrame.setLayout(new BorderLayout());
        vendFrame.setSize(600, 600);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        JPanel labelPanel = new JPanel();
        balanceLabel = new JLabel("Balance: ", JLabel.LEFT);
        balanceLabel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        balanceLabel.setText(INITIAL_BALANCE_VALUE);
        JPanel buttonLeftPanel = new JPanel();
        buttonLeftPanel.setLayout(new GridLayout(1, 1, 5, 5));
        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                dispenseButton.doClick();
                balanceLabel.setText(INITIAL_BALANCE_VALUE);
                sodaDispensed.setText(INIT_SELECTION);
                // coinDispenseLabel.setText(INITIAL_BALANCE_VALUE);
                sodaVendingMachine.reset();
            }
        });
        cancelButton = new JButton("Cancel");
        labelPanel.add(balanceHeaderLabel);
        labelPanel.add(balanceLabel);
        buttonLeftPanel.add(resetButton);
        buttonLeftPanel.add(cancelButton);
        leftPanel.add(labelPanel);
        leftPanel.add(buttonLeftPanel);
        contentPane.add(leftPanel);
        contentPane.add(getCurrencyPanel());
        contentPane.add(getSodaContentPane());
        contentPane.add(getDispensePanel());
        vendFrame.setContentPane(contentPane);
        pack();
    }

    /**
     * Currency panel
     * Dynamically takes the values from ENUM or can be tweaked to get data from
     * @return   JPanel with currency values
     */
    public JPanel getCurrencyPanel()
    {
        JPanel currencyPanel = new JPanel();
        currencyPanel.setLayout(new GridLayout(gridSize, gridSize, 5, 5));

        for(USCurrencyVal currencyVal: USCurrencyVal.values()){
            String buttonName = currencyVal.name();
            final double value = currencyVal.getValue();
            JButton currencyButton = new JButton(buttonName);

            currencyButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    if(Double.compare(value ,0.05) < 0)
                    {
                        JOptionPane.showMessageDialog(vendFrame, " This Machine does not accept inserted currency ");
                    }
                    else
                    {
                        sodaVendingMachine.insertMoney(value);
                        refreshBalanceLabel();
                    }
             }
            });
            currencyPanel.add(currencyButton);
        }

        return currencyPanel;
    }


    public JPanel getSodaContentPane()
    {
        JPanel sodaContentPanel = new JPanel();
        sodaContentPanel.setLayout(new GridLayout(4, 3, 5, 5));

        for( SodaMenuItems sodaItems: SodaMenuItems.values()){
            final String itemName = sodaItems.name();
            final String buttonName = itemName+ "(" + sodaItems.getValue() + ")";
            JButton sodaButton = new JButton(buttonName);

            sodaButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    VendItem item =  sodaVendingMachine.dispense(itemName);

                    if(item!= null)
                    {
                        refreshBalanceLabel();
                        refreshSodaDispensedLabel(item.getItemName());
                        JOptionPane.showMessageDialog(vendFrame,  item.getItemName() + " Transaction successful, please enjoy your drink. ");
                    }
                    else
                    {
                        String status =  sodaVendingMachine.getTransactionMessage();
                        refreshSodaDispensedLabel(status);
                        JOptionPane.showMessageDialog(vendFrame,  status);
                    }
                }
            });
            sodaContentPanel.add(sodaButton);
        }
        return sodaContentPanel;
    }

    /**
     * Panel used to display Dispensed Soda and Dispensed Coins
     * @return  JPanel
     */
    public JPanel getDispensePanel() {
        JPanel dispensePanel = new JPanel();
        JLabel dispenseLabel = new JLabel("Dispense");
        dispenseLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        dispenseLabel.setLayout(new BoxLayout(dispenseLabel, BoxLayout.Y_AXIS));
        JPanel sodaDispensePanel = new JPanel();
        sodaDispensePanel.setLayout(new GridLayout(2, 2, 5, 5));
        sodaDispensePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        JLabel sodaLabel = new JLabel("Soda Dispense");
        sodaDispensed = new JLabel(INIT_SELECTION);
        sodaDispensePanel.add(sodaLabel);
        sodaDispensePanel.add(sodaDispensed);
        sodaDispensePanel.setSize(75, 150);
        JPanel coinDispensePanel = new JPanel();
        coinDispensePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        coinDispensePanel.setLayout(new GridLayout(2, 2, 5, 5));
        coinDispenseLabel = new JLabel(COIN_RETURN_MESSAGE, JLabel.CENTER);
        dispenseButton = new JButton("COIN RETURN");
        dispenseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Collection<Coin> items = sodaVendingMachine.dispenseCoins();
                refreshBalanceLabel();
                refreshCoinLabelBalance(items);
            }
        });
        coinDispensePanel.add(coinDispenseLabel);
        coinDispensePanel.add(dispenseButton);
        dispensePanel.add(sodaDispensePanel);
        dispensePanel.add(coinDispensePanel);
        return dispensePanel;
    }


    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * Refresh Label
     */
    public void refreshBalanceLabel()
    {
        String balance =  sodaVendingMachine.getBalance();
        balanceLabel.setText(balance);
        coinDispenseLabel.setText(COIN_RETURN_MESSAGE);
    }

    /**
     * Refresh Dispense Label
     * @param str
     */
    public void refreshSodaDispensedLabel(String str)
    {
        sodaDispensed.setText(str);
    }

    public void refreshCoinLabelBalance(Collection<Coin>  items)
    {
        String dispense = "";
        if (items.isEmpty())
        {
            dispense = "No Balance";
        }
        for(Coin c : items)
        {
            dispense+= " \n " + c.getCount() + " " +c.getCoinType() + " ";
        }

        coinDispenseLabel.setText(dispense);
    }

    public static void main(String[] args) {

        new OrbitzSodaVending();

    }

}
