import java.util.HashMap; // import the HashMap class

public class ATM {
    HashMap<String, Double> accounts = new HashMap<String, Double>();

    public static void main(String[] args) {

        ATM atm = new ATM();
        atm.openAccount("chase", 0.0);
        atm.closeAccount("chase");
        atm.openAccount("chase", 1.0);

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

}