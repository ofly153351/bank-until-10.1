import java.awt.*;
import java.awt.event.*;

import javax.swing.JComboBox;

public class tellerGUII implements ActionListener {
    private Button bn1, bn2, bn3;
    private TextField tf1, tf2, tf3;
    private Label l1, l2, l3;
    private TextField err;
    private Panel p1, p2, p3, p4;
    private Frame fr;
    private Customer cust;
    private JComboBox<String> c1;

    /**
     * 
     */
    public void init() {

        tf1 = new TextField();
        tf2 = new TextField();
        tf3 = new TextField();
        err = new TextField();

        l1 = new Label("Name");
        l2 = new Label("Balacne");
        l3 = new Label("Amount");

        bn1 = new Button("Deposit");
        bn2 = new Button("Withdraw");
        bn3 = new Button("Exit");

        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        p1.setLayout(new GridLayout(4, 2));

        c1 = new JComboBox<>();
        for (int i = 1; i <= cust.getNumOfAccount(); i++) {
            c1.addItem(" Account " + i);
        }
        p1.add(l1);
        p1.add(tf1);
        p1.add(l2);
        p1.add(tf2);
        p1.add(l3);
        p1.add(tf3);

        p2.add(bn1);
        p2.add(bn2);
        p2.add(bn3);

        p3.setLayout(new BorderLayout());
        p3.add(p1, BorderLayout.CENTER);
        p3.add(p2, BorderLayout.SOUTH);

        fr.add(p4);
        fr = new Frame("Bank 7.2 ");
        fr.add(p3, BorderLayout.CENTER);
        fr.add(err, BorderLayout.SOUTH);
        tf1.setEditable(false);
        tf2.setEditable(false);
        err.setEditable(false);
        tf2.setText(cust.getAccount(0).getBalance() + "");
        tf1.setText(cust.getFirstName() + " " + cust.getLastName());

        tf1.setBackground(new Color(123, 104, 238));
        tf2.setBackground(new Color(123, 104, 238));
        tf3.setBackground(new Color(123, 104, 238));

        bn1.setBackground(new Color(123, 104, 238));
        bn2.setBackground(new Color(123, 104, 238));
        bn3.setBackground(new Color(123, 104, 238));

        fr.setBackground(new Color(72, 61, 139));
        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);

        tf1.setForeground(Color.WHITE);
        tf2.setForeground(Color.WHITE);
        tf3.setForeground(Color.WHITE);

        bn1.setForeground(Color.WHITE);
        bn2.setForeground(Color.WHITE);
        bn3.setForeground(Color.WHITE);

        fr.pack();
        fr.show();

        bn1.addActionListener(this);
        bn2.addActionListener(this);

        bn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }

        });

    }

    public tellerGUII(Customer c) {
        cust = c;
    }

    public static void main(String[] args) {
        Customer cust = new Customer("Sommai", "Summut");
        Account ac1 = new SavingAccount(5000);
        Account ac2 = new SavingAccount(2500);
        Account ac3 = new SavingAccount(2000);
        cust.addAccount(ac1);
        cust.addAccount(ac2);
        cust.addAccount(ac3);
        tellerGUII tl = new tellerGUII(cust);
        tl.init();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String cmd = ev.getActionCommand();
        double amt = Double.parseDouble(tf3.getText());
        if (cmd == "Exit") {
            System.exit(0);
        } else if (cmd == "Withdraw") {
            try {
                if (cust.getAccount(0).withdraw(amt)) {
                    tf2.setText(cust.getAccount(0).getBalance() + "");
                    err.setText(" ");
                } else {
                    err.setText("Not Enough Money");
                }
            } catch (WithdrawException e) {
                e.printStackTrace();
            }
        } else if (cmd == "Deposit") {
            err.setText(" ");
            cust.getAccount(0).deposit(amt);
            tf2.setText(cust.getAccount(0).getBalance() + "");

        }
    }
}
