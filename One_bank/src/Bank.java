import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Bank {
    private String bankName;
    private Map<Account, Client> accounts;

    public Bank(String bankName) {
        this.bankName = bankName;
        accounts = new TreeMap<>();
    }

    public boolean searchAccount(long accountNumber) {
        boolean flag = false;
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
            if(map.getKey().getBankAccountNumber() == accountNumber) {
                System.out.println(map.getKey());
                System.out.println("Owner" + map.getValue());
                flag = true;
            }
        }
        return flag;
    }

    public List<Account> searchAccount(String passportID) {
        List<Account>clientAccounts = new ArrayList<>();
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
            if(map.getValue().getPassportID().equals(passportID)) {
                clientAccounts.add(map.getKey());
            }
        }
        return clientAccounts;
    }

    public long calculationSumOfAccounts() {
        long sum = 0;
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
                sum += map.getKey().getBalance();
        }
        return sum;
    }

    public long calculationSumOfAccounts(String passportID) {
        Client client;
        long sum = 0;
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
            client = map.getValue();
            if(client.getPassportID().equals(passportID)) {
                sum += map.getKey().getBalance();
            }
        }
        return sum;
    }

    public long calculationSumOfPositiveAccounts() {
        long sum = 0;
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
            if(map.getKey().getBalance() > 0)
                sum += map.getKey().getBalance();
        }
        return sum;
    }

    public long calculationSumOfNegativeAccounts() {
        long sum = 0;
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
            if(map.getKey().getBalance() < 0)
                sum += map.getKey().getBalance();
        }
        return sum;
    }

    public void addClients(Client client, long balance) {
        accounts.put(new Account(balance), client);
    }

    public void creditBalance(long accountNumber, long sum) {
        long balance;
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
            if(map.getKey().getBankAccountNumber() == accountNumber) {
                balance = map.getKey().getBalance() - sum;
                map.getKey().setBalance(balance);
            }
        }
    }

    public void debitBalance(long accountNumber, long sum) {
        long balance;
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
            if(map.getKey().getBankAccountNumber() == accountNumber) {
                balance = map.getKey().getBalance() + sum;
                map.getKey().setBalance(balance);
            }
        }
    }

    public void blockingAccounts(long accountNumber) {
        Account account;
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
            account = map.getKey();
            if(account.getBankAccountNumber() == accountNumber)
                System.out.println("Account " + account.getBankAccountNumber() + " is blocked");
        }
    }

    public void unBlockingAccounts(long accountNumber) {
        Account account;
        for (Map.Entry<Account, Client> map : accounts.entrySet()) {
            account = map.getKey();
            if(account.getBankAccountNumber() == accountNumber)
                System.out.println("Account " + account.getBankAccountNumber() + " is unblocked");
        }
    }
}
