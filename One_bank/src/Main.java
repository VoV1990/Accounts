import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        Bank belarusbank = new Bank("Belarusbank");
        Client ivanov = new Client("Ivanov", "Ivan", "3110689E001MP5");
        Client petrov = new Client("Petrov", "Petr", "3240790E003MP5");
        Client sidorov = new Client("Sidorov", "Ivan", "3170488E001PB5");
        Client sergeev = new Client("Sergeev", "Sergey", "3260592E001MP5");
        Client kalinin = new Client("Kalinin", "Andrey", "3231290E001MP6");
        Client lopata = new Client("Lopata", "Vadim", "3070285E001MP5");

        belarusbank.addClients(ivanov, 1400);
        belarusbank.addClients(petrov, 15000);
        belarusbank.addClients(sidorov, 0);
        belarusbank.addClients(sergeev, 160000000);
        belarusbank.addClients(kalinin, 2000);
        belarusbank.addClients(lopata, 87000);
        belarusbank.addClients(ivanov, -5000);
        belarusbank.addClients(ivanov, 10000);
        belarusbank.addClients(sergeev, 1500000);

        List<Account> clientAccount = belarusbank.searchAccount(ivanov.getPassportID());
        System.out.println(ivanov + ":");
        for (Account account : clientAccount) System.out.println(account);
        clientAccount = belarusbank.searchAccount(lopata.getPassportID());
        System.out.println(lopata + ":");
        for (Account account : clientAccount) belarusbank.blockingAccounts(account.getBankAccountNumber());
        System.out.println("Sum of all accounts: " + belarusbank.calculationSumOfAccounts());
        System.out.println("Sum of all positive accounts: " + belarusbank.calculationSumOfPositiveAccounts());
        System.out.println("Sum of all negative accounts: " + belarusbank.calculationSumOfNegativeAccounts());
        chooseOption(belarusbank);
    }

    private static void chooseOption(Bank bank) throws IOException {
        boolean stop = false;
        int option = 0;
        do{
            System.out.println("Please choose one of the suggested options and enter its number.");
            System.out.println("1. Open a Bank account.");
            System.out.println("2. Get a list of your accounts.");
            System.out.println("3. Top up your balance.");
            System.out.println("4. Withdraw money from your account.");
            System.out.println("5. To block the account.");
            System.out.println("6. To unlock the account.");
            System.out.println("7. End the session.");
            try {
                option = Integer.parseInt(reader.readLine());
            } catch (Exception e) {
                System.out.println("Invalid data.");
            }
            if(option >= 1 && option <= 7) stop = true;
            else System.out.println("Please try again.");
        } while (!stop);
        implementRequest(option, bank);
    }

    private static void implementRequest(int option, Bank bank) throws IOException {
        switch (option) {
            case 1 -> {
                String lastName;
                String firstName;
                String passportID;
                long balance;
                System.out.println("Please enter your last name:");
                lastName = reader.readLine();
                System.out.println("Please enter your first name:");
                firstName = reader.readLine();
                System.out.println("Please enter your passport ID:");
                passportID = reader.readLine();
                System.out.println("Please enter the amount you would like to deposit to your account:");
                balance = Long.parseLong(reader.readLine());
                if(balance < 0) {
                    System.out.println("The amount cannot have a negative value.");
                    System.out.println("Please try again.");
                } else {
                    bank.addClients(new Client(lastName, firstName, passportID), balance);
                    System.out.println("The account is opened.");
                }
            }
            case 2 -> {
                System.out.println("Please enter your passport ID:");
                String passportID = reader.readLine();
                List<Account> clientAccount = bank.searchAccount(passportID);
                System.out.println("Your accounts:");
                for (Account account : clientAccount) System.out.println(account);
            }
            case 3 -> {
                System.out.println("Please enter number of account:");
                long accountNumber = Long.parseLong(reader.readLine());
                if (bank.searchAccount(accountNumber)) {
                    System.out.println("Please enter the amount (more than 1) you want to add to account:");
                    long amount = Long.parseLong(reader.readLine());
                    if(amount < 1) {
                        System.out.println("The amount cannot cannot be less than 1.");
                        System.out.println("Please try again.");
                    }
                    bank.debitBalance(accountNumber, amount);
                    System.out.println("Operation completed successfully.");
                } else {
                    System.out.println("The account does not exist.");
                    System.out.println("Please try again.");
                }
            }
            case 4 -> {
                System.out.println("Please enter number of account:");
                long accountNumber = Long.parseLong(reader.readLine());
                if (bank.searchAccount(accountNumber)) {
                    System.out.println("Please enter the amount you want to withdraw from account:");
                    long amount = Long.parseLong(reader.readLine());
                    if(amount < 1) {
                        System.out.println("The amount cannot cannot be less than 1.");
                        System.out.println("Please try again.");
                    }
                    bank.creditBalance(accountNumber, amount);
                    System.out.println("Operation completed successfully.");
                } else {
                    System.out.println("The account does not exist");
                    System.out.println("Please try again.");
                }
            }
            case 5 -> {
                System.out.println("Please enter number of account:");
                long accountNumber = Long.parseLong(reader.readLine());
                if (bank.searchAccount(accountNumber)) {
                    bank.blockingAccounts(accountNumber);
                    System.out.println("Account is blocked.");
                } else {
                    System.out.println("The account does not exist");
                    System.out.println("Please try again.");
                }
            }
            case 6 -> {
                System.out.println("Please enter number of account:");
                long accountNumber = Long.parseLong(reader.readLine());
                if (bank.searchAccount(accountNumber)) {
                    bank.unBlockingAccounts(accountNumber);
                    System.out.println("Account is unblocked.");
                } else {
                    System.out.println("The account does not exist");
                    System.out.println("Please try again.");
                }
            }
            case 7 -> {
                reader.close();
                System.out.println("Goodbye!");
            }
        }
    }
}