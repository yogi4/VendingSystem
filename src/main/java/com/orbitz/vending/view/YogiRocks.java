package com.orbitz.vending.view;

import com.orbitz.vending.*;
import com.orbitz.vending.enums.SodaMenuItems;
import com.orbitz.vending.enums.USCurrencyVal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: bazinga
 * Date: 11/23/13
 * Time: 4:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class YogiRocks extends JFrame implements ActionListener {

   private static int gridSize = 4;

    JFrame vendFrame = new JFrame("Yogi Rocks");
    JLabel balanceLabel;
    final String balance_value = "0";

    //ProcessBalance processBalance = new ProcessBalance();
    SodaVendingMachine sodaVendingMachine = new SodaVendingMachine();
    ArrayList<JButton> buttons = new ArrayList<JButton>();
   public YogiRocks()
   {
       vendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       vendFrame.setVisible(true);
       vendFrame.setResizable(true);
       vendFrame.setLayout(new BorderLayout());
       vendFrame.setSize(500, 500);

       /* yogi workspace   */
        final JButton resetButton;

       /* yogi workspace */


       JPanel contentPane = new JPanel();
       contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
       contentPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

       JPanel leftPanel = new JPanel();
       leftPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
       leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));


       JPanel labelPanel = new JPanel();
       balanceLabel = new JLabel(balance_value, JLabel.CENTER);

       JPanel buttonLeftPanel = new JPanel();
       resetButton = new JButton("Reset");
       resetButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent ae)
           {
               balanceLabel.setText(balance_value);
               sodaVendingMachine.reset();
           }
       });
       labelPanel.add(balanceLabel);
       buttonLeftPanel.add(resetButton);
       leftPanel.add(labelPanel);
       leftPanel.add(buttonLeftPanel);

       contentPane.add(leftPanel);
       contentPane.add(getCurrencyPanel());

       contentPane.add(getSodaContentPane());
       //JPanel sodaPanel = new JPanel() ;

       //JPanel balance = new JPanel();

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
        sodaContentPanel.setLayout(new GridLayout(3, 4, 5, 5));

        for(SodaMenuItems sodaItems: SodaMenuItems.values()){
            final String buttonName = sodaItems.name();
            JButton sodaButton = new JButton(buttonName);

            sodaButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                  VendItem item =  sodaVendingMachine.dispense(buttonName);

                    if(item!= null)
                    {
                        JOptionPane.showMessageDialog(vendFrame,  item.getItemName() + " Eggs are not supposed to be green.");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(vendFrame,  sodaVendingMachine.getTransactionMessage());

                    }

                  //  System.out.println(item.getItemCount());



                  refreshBalanceLabel();
                }
            });
            sodaContentPanel.add(sodaButton);
        }

        return sodaContentPanel;
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


    public static void main(String[] args) {

        new YogiRocks();

    }

}
