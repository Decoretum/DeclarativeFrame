package demo.bank;

public class Bank implements BankInterface
{
	int balance;
	
	
	@Override
	public void deposit(int i)
	{
		System.out.println("Depositing: "+i);
		balance += i;
	}
	
	@Override
	public void withdraw(int i)
	{
		System.out.println("Withdraw: "+i);
		balance -= i;
	}

	@Override
	public String toString() {
		return String.format("Bank [balance=%s]", balance);
	}

	
	
}
