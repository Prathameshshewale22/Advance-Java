package tester;

import java.util.List;
import java.util.Scanner;

import DButils.DataBaseConnection;
import dal.AccountDAL;
import dol.Account;

public class TestCrudOnAccount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		try {
			DataBaseConnection.connect();
			System.out.println("1.Display all account/2.Add new Account/3.delete account/4.update account/5.Deposite amt/6.withdraw amt/7.Money transfer");
			AccountDAL acc=new AccountDAL();
		  Scanner sc=new Scanner(System.in);
		  int ch=0;
		  
		  do {
			  System.out.println("enter choice");
			  ch=sc.nextInt();
			 switch (ch) {
			case 1:
				List<Account> accs=acc.AllAccounts();
				accs.forEach(a -> System.out.println(a));
				break;

			case 2:
				System.out.println("enter id,name,type,bal");
				Account newacc=new Account(sc.nextInt(),sc.next(),sc.next(),sc.nextDouble());
				acc.insertNewAcc(newacc);
				
				break;
				
			case 3:
				System.out.println("enter the account id");
				acc.DeleteAcc(sc.nextInt());
				break;
			case 4:
				System.out.println("Edit:Enter id,Name,type,balance");
				Account acc1=new Account(sc.nextInt(),sc.next() ,sc.next(),sc.nextDouble());
				acc.UpdateAcc(acc1);
				break;
			case 5:
				System.out.println("enter account amt and acc id");
				acc.DepositeAmt(sc.nextDouble(),sc.nextInt());
				break;
			case 6:
				System.out.println("Enter Account amt and id");
				acc.WithdrawAmt(sc.nextDouble(),sc.nextInt());
				break;
			case 7:
				System.out.println("enter sender accid,reciver accid,amount");
				acc.MoneyTransfer(sc.nextInt(),sc.nextInt(),sc.nextDouble());
				break;
			}
		  }while(ch!=10);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
