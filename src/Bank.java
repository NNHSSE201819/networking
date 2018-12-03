public class Bank
{
    private BankAccount[] accounts;

    public Bank(int size)
    {
        accounts = new BankAccount[size];
        for (int i = 0; i < accounts.length; i++)
        {
            accounts[i] = new BankAccount();
        }
    }

    public void deposit(int accouuntNumber, double amount)
    {
        BankAccount account = accounts[accountNumber];
        account.deposit(amount);
    }
    public void widthdraw(int accountNumber, double amount)
    {
        BankAccount account = accounts[accountNumber];
        account.widthdraw(amount);
    }

    public double getBalance(int accountNumber)
    {
        BankAccount account = accounts[accountNumber];
        return account.getBalance();
    }
}
