/*Счета. Клиент может иметь несколько счетов в банке.
Учитывать возможность блокировки/разблокировки счета. Реализовать поиск и сортировку счетов.
Вычисление общей суммы по счетам. Вычисление суммы по всем счетам,
имеющим положительный и отрицательный балансы отдельно.*/

public class Account implements Comparable<Account>{
    private static long count = 4001000000000000L;
    private long bankAccountNumber;
    private long balance;

    public Account() {
        bankAccountNumber = count;
        balance = 0;
        count++;
    }

    public Account(long balance) {
        bankAccountNumber = count;
        this.balance = balance;
        count++;
    }

    public long getBankAccountNumber() {
        return bankAccountNumber;
    }

    public long getBalance() {
        return this.balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }



    @Override
    public int compareTo(Account o) {
        return (int) (this.getBankAccountNumber() - o.getBankAccountNumber());
    }

    @Override
    public String toString() {
        return "Account number: " + this.getBankAccountNumber() +
                ", balance: " + balance;
    }
}
