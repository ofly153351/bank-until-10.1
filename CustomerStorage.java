import java.io.*;

public class CustomerStorage {
    public static double[] getBalance(int customerId) {
        FileInputStream fin = null;
        DataInputStream din = null;
        double balance = 0.0;
        double credit = 0.0;
        try {
            // Open the file for reading
            fin = new FileInputStream("Customer_" + customerId + ".dat");
            din = new DataInputStream(fin);
            // Read the balance value
            balance = din.readDouble();
            // Close the streams
            din.close();
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new double[] { balance, credit };
    }

    public static void saveBalance(int customerId, double balance) {
        FileOutputStream fout = null;
        DataOutputStream dout = null;
        try {
            fout = new FileOutputStream("Customer_" + customerId + ".dat");
            dout = new DataOutputStream(fout);
            dout.writeDouble(balance);
            fout.close();
            dout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}