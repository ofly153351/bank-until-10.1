import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.File;

public class TellerGUISwing implements ActionListener {
    private JButton bn1, bn2, bn3;
    private JTextField tf1, tf2, tf3, tf4;
    private JLabel l1, l2, l3, l4, l5;
    private JTextField err;
    private JPanel p1, p2, p3;
    private JFrame fr;
    private Customer cust;
    private JComboBox<String> c1;
    private File f;

    /**
     * 
     */
    public void init() {
        if (cust.getNumOfAccount() == 0) {
            Account acc = new Account(4000);
            cust.addAccount(acc);
        }
        try {
            int i = 0;
            do {
                f = new File("Customer_" + i + ".dat");
                if (f.exists()) {
                    double[] initAmount = CustomerStorage.getBalance(i);
                    if (cust.getAccount(i) instanceof CheckingAccount) {
                        ((CheckingAccount) cust.getAccount(i)).updateBalance(initAmount[0]);
                    } else if (cust.getAccount(i) instanceof Account || cust.getAccount(i) instanceof SavingAccount) {
                        cust.getAccount(i).updateBalance(initAmount[0]);
                    }

                }
                i++;
            } while (i < cust.getNumOfAccount());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int x = 1;
        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        err = new JTextField();
        c1 = new JComboBox<>();

        for (x = 1; x <= cust.getNumOfAccount(); x++) {
            c1.addItem(" Account " + x);
        }

        l1 = new JLabel("Name :");
        l2 = new JLabel("Balacne :");
        l3 = new JLabel("Amount :");
        l4 = new JLabel(" Account Type :");
        l5 = new JLabel("Credit :");

        bn1 = new JButton("Deposit");
        bn2 = new JButton("Withdraw");
        bn3 = new JButton("Exit");

        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p1.setLayout(new GridLayout(5, 2));

        p1.add(l1); // name
        p1.add(tf1); // tfname
        p1.add(l2);// Balance
        p1.add(tf2);// tfBalance
        p1.add(l5);// credit
        p1.add(tf4);// tfcredit
        p1.add(l3);// Amount
        p1.add(tf3);// tfamount
        p1.add(l4);// accounttype
        p1.add(c1);// combobox

        p2.add(bn1);
        p2.add(bn2);
        p2.add(bn3);

        p3.setLayout(new BorderLayout());
        p3.add(p1, BorderLayout.CENTER);
        p3.add(p2, BorderLayout.SOUTH);

        fr = new JFrame("Bank");

        fr.getContentPane().add(p3, BorderLayout.CENTER);
        fr.getContentPane().add(err, BorderLayout.SOUTH);

        tf1.setEditable(false);
        tf2.setEditable(false);
        err.setEditable(false);

        tf2.setText(cust.getAccount(getNumOfAccount(x)).getBalance() + "");
        tf1.setText(cust.getFirstName() + " " + cust.getLastName());

        tf1.setBackground(new Color(123, 104, 238));
        tf2.setBackground(new Color(123, 104, 238));
        tf3.setBackground(new Color(123, 104, 238));
        tf4.setBackground(new Color(123, 104, 238));

        bn1.setBackground(new Color(123, 104, 238));
        bn2.setBackground(new Color(123, 104, 238));
        bn3.setBackground(new Color(123, 104, 238));

        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);
        l5.setForeground(Color.WHITE);

        tf1.setForeground(Color.WHITE);
        tf2.setForeground(Color.WHITE);
        tf3.setForeground(Color.WHITE);
        tf4.setForeground(Color.WHITE);
        err.setForeground(Color.WHITE);

        bn1.setForeground(Color.WHITE);
        bn2.setForeground(Color.WHITE);
        bn3.setForeground(Color.WHITE);

        bn1.addActionListener(this);
        bn2.addActionListener(this);
        bn3.addActionListener(this);

        if (cust.getAccount(c1.getSelectedIndex()) instanceof CheckingAccount) {
            tf4.setText(((CheckingAccount) cust.getAccount(c1.getSelectedIndex())).getCredit() + "");
        } else {
            tf4.setText("0");
        }

        c1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int id = c1.getSelectedIndex();
                Account sec = cust.getAccount(id);
                tf1.setText(cust.getFirstName() + " " + cust.getLastName());
                tf2.setText(sec.getBalance() + "");
                if (cust.getAccount(id) instanceof CheckingAccount) {
                    tf4.setText(((CheckingAccount) cust.getAccount(id)).getCredit() + "");
                } else
                    tf4.setText("0");
            }

        });
        p1.setBackground(new Color(72, 61, 139));
        p2.setBackground(new Color(72, 61, 139));
        p2.setBackground(new Color(72, 61, 139));
        err.setBackground(new Color(72, 61, 139));

        fr.pack();
        fr.show();
        fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private int getNumOfAccount(int i) {
        return 0;
    }

    public TellerGUISwing(Customer c) {
        cust = c;
    }

    public static void main(String[] args) {
        Customer cust = new Customer("peerapat", "klintan");
        Account ac1 = new SavingAccount(5000);
        Account ac2 = new SavingAccount(2500);
        CheckingAccount ac3 = new CheckingAccount(2000, 2000);

        cust.addAccount(ac1);
        cust.addAccount(ac2);
        cust.addAccount(ac3);
        TellerGUISwing tl = new TellerGUISwing(cust);
        tl.init();
    }

    @Override
    public void actionPerformed(ActionEvent ed) {
        String cmd = ed.getActionCommand();
        double amt = Double.parseDouble(tf3.getText());
        // double cre = Double.parseDouble(tf4.getText());
        try {
            if (cmd == "Exit") {
                for (int i = 0; i < cust.getNumOfAccount() || i < 1; i++) {
                    if ((cust.getAccount(i) instanceof CheckingAccount)) {
                        CustomerStorage.saveBalance(i, cust.getAccount(i).getBalance());
                    } else if (cust.getAccount(i) instanceof Account || cust.getAccount(i) instanceof SavingAccount) {
                        CustomerStorage.saveBalance(i, cust.getAccount(i).getBalance());

                    }
                }
                System.exit(0);
            } else if (cmd == "Withdraw") {

                cust.getAccount(c1.getSelectedIndex()).withdraw(amt);
                if (cust.getAccount(c1.getSelectedIndex()) instanceof Account) { // ใช้account ไม่ได้ โปรแกรมเอ๋อแดก!
                    tf2.setText(cust.getAccount(c1.getSelectedIndex()).getBalance() + "");
                }
                if (cust.getAccount(c1.getSelectedIndex()) instanceof CheckingAccount) {
                    tf2.setText(cust.getAccount(c1.getSelectedIndex()).getBalance() + "");
                    tf4.setText(((CheckingAccount) cust.getAccount(c1.getSelectedIndex())).getCredit() + "");
                    err.setText(null);
                }
                err.setText(null);
            } else if (cmd == "Deposit") {

                cust.getAccount(c1.getSelectedIndex()).deposit(amt);
                tf2.setText(cust.getAccount(c1.getSelectedIndex()).getBalance() + "");
                err.setText(" ");

            }

        } catch (

        WithdrawException ex) {
            err.setText("Not Enough Money");
            JOptionPane.showMessageDialog(null, "Not Enough Money " + cust.getFirstName() + " " + cust.getLastName(),
                    "olo",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ee) {
            err.setText("Please enter a number");
            JOptionPane.showMessageDialog(null,
                    "Please enter a number" + cust.getFirstName() + " " + cust.getLastName(),
                    "olo",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
