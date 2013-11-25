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

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrbitzSodaVending extends JFrame implements ActionListener {

   private static int gridSize = 4;

    JFrame vendFrame = new JFrame("Yogi Rocks");
    JLabel balanceLabel;
    JLabel balanceHeaderLabel = new JLabel("BALANCE: ");
    JLabel coinDispenseLabel;
    JLabel sodaDispensed;
    JButton dispenseButton;

    JButton resetButton;
    JButton cancelButton;
    JTextArea coinsList;
    static  String INIT_SELECTION = "Please Make a Selection";
    static String INITIAL_BALANCE_VALUE = "$0.00";
    static String COIN_RETURN="Coin Return";


    SodaVendingMachine sodaVendingMachine = new SodaVendingMachine();
    ArrayList<JButton> buttons = new ArrayList<JButton>();
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
                    sodaVendingMachine.insertMoney(value);
                    refreshBalanceLabel();
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


    public JPanel getDispensePanel()
    {
        JPanel dispensePanel = new JPanel();

        JLabel dispenseLabel = new JLabel("Dispense");
        dispenseLabel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        dispenseLabel.setLayout(new BoxLayout(dispenseLabel, BoxLayout.X_AXIS));
        dispensePanel.setSize(150,400);


        JPanel sodaDispensePanel = new JPanel();
        sodaDispensePanel.setLayout(new GridLayout(2, 2, 5, 5));
        sodaDispensePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        JLabel sodaLabel = new JLabel("Soda Dispense");

        sodaDispensed = new JLabel(INIT_SELECTION);

        sodaDispensePanel.add(sodaLabel);
        //sodaDispensed.setText("Please Make a Selection");
         sodaDispensePanel.add(sodaDispensed);
        //sodaDispensePanel.setBackground(Color.blue);
        sodaDispensePanel.setSize(75,150);
       // sodaDispensePanel.setPreferredSize(new Dimension(150,150));








        JPanel coinDispensePanel = new JPanel();
        coinDispensePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        coinDispensePanel.setLayout(new GridLayout(2, 2, 5, 5));

        coinDispenseLabel   = new JLabel(INITIAL_BALANCE_VALUE, JLabel.CENTER);

        dispenseButton  = new JButton("COIN_RETURN");

        dispenseButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
               Collection<Coin> items = sodaVendingMachine.dispenseCoins();

                refreshLabelBalance(items);
                refreshBalanceLabel();
            }
        });


     //   coinsList = new JTextArea();
       // coinsList.setSize(40,40);

    //    coinsList.setText(coinsList.getText() + " \n " + "bazinga" + " \n " + "Ergo");


      //  coinDispensePanel.add(coinsList);
        coinDispensePanel.add(coinDispenseLabel);
        coinDispensePanel.add(dispenseButton);



         dispensePanel.add(sodaDispensePanel);

        dispensePanel.add(coinDispensePanel);
        return dispensePanel;


    }

    public void dotheHonors(VendItem item)
    {
        //make cool graphics of soda cans
        //TODO
    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (buttons.contains(e.getSource()) )
        {

            System.out.print(e.getID());

        }

    }


    public void refreshBalanceLabel()
    {
        balanceLabel.setText(sodaVendingMachine.getBalance());
    }

   public void refreshSodaDispensedLabel(String str)
    {
        sodaDispensed.setText(str);
    }

    public void refreshLabelBalance(Collection<Coin>  items)
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

        new YogiRocks();

    }

}
