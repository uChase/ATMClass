import java.util.HashMap; // import the HashMap class

public class ATM {
    HashMap<String, Double> accounts = new HashMap<String, Double>();

    public static void main(String[] args) {

        ATM atm = new ATM();
        atm.openAccount("chase", 2);
        atm.openAccount("chase", 1);

    }

    public void openAccount(String userId, double amount) {
        if (accounts.putIfAbsent(userId, amount) != null) {
            throw new Error("user already exists", null);
        }

    }

}