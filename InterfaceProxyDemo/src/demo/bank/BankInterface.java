package demo.bank;

import demo.annotations.DebugParam;
import demo.annotations.DebugProxy;

public interface BankInterface {

	@DebugProxy
	public void deposit(@DebugParam int i);

	@DebugProxy
	public void withdraw(int i);

}