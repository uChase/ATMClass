import java.util.HashMap; // import the HashMap class
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Map;

import java.io.FileReader;

public class ATM {
    HashMap<String, Double> accounts = new HashMap<String, Double>();

    public static void main(String[] args) {

        ATM atm = new ATM();
        atm.openAccount("chase", 0.0);
        atm.closeAccount("chase");
        atm.openAccount("chase", 1.0);
        System.out.println(atm.checkBalance("chase"));
        atm.depositMoney("chase", 2.0);
        System.out.println(atm.checkBalance("chase"));
        atm.withdrawMoney("chase", 2.0);
        System.out.println(atm.checkBalance("chase"));
        atm.openAccount("aaron", 5.0);
        atm.transferMoney("aaron", "chase", 4.0);
        System.out.println(atm.checkBalance("chase"));
        System.out.println(atm.checkBalance("aaron"));
        atm.audit();

    }

    public void openAccount(String userId, Double amount) {

        if (accounts.putIfAbsent(userId, amount) != null) {
            throw new Error("user already exists", null);
        }

    }

    public void closeAccount(String userId) {
        Double balance = accounts.get(userId);
        if (balance == null) {
            throw new Error("user does not exist", null);
        }
        if (balance != 0) {
            throw new Error("need to withdraw all funds first", null);

        }
        accounts.remove(userId);
    }

    public Double checkBalance(String userId) {
        Double balance = accounts.get(userId);
        if (balance == null) {
            throw new Error("user does not exist", null);
        }
        return balance;
    }

    public Double depositMoney(String userId, Double money) {
        Double balance = accounts.get(userId);
        if (balance == null) {
            throw new Error("user does not exist", null);
        }
        Double newMoney = balance + money;
        accounts.put(userId, newMoney);
        return newMoney;
    }

    public Double withdrawMoney(String userId, Double money) {
        Double balance = accounts.get(userId);
        if (balance == null) {
            throw new Error("user does not exist", null);
        }
        Double newMoney = balance - money;
        if (newMoney < 0) {
            throw new Error("you too broke lmao", null);
        }
        accounts.put(userId, newMoney);
        return newMoney;
    }

    public Boolean transferMoney(String from, String to, Double money) {
        Double fromBalance = accounts.get(from);
        Double toBalance = accounts.get(to);

        if (fromBalance == null || toBalance == null) {
            throw new Error("user does not exist", null);
        }

        Double newFromBalance = fromBalance - money;
        if (newFromBalance < 0) {
            throw new Error("you too broke lmao", null);
        }
        Double newToBalance = toBalance + money;
        accounts.put(from, newFromBalance);
        accounts.put(to, newToBalance);

        return true;

    }

    public void audit() {
        try {
            FileWriter fileWriter = new FileWriter("AccountAudit.txt");
            BufferedWriter b = new BufferedWriter(fileWriter);
            for (Map.Entry<String, Double> entry : accounts.entrySet()) {
                String key = entry.getKey();
                Double value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
                b.write("Account: " + key + ", Balance: " + value);
                b.write('\n');
            }
            b.close();
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}