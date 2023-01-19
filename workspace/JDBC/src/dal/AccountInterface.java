package dal;

import java.util.List;

import dol.Account;

public interface AccountInterface {

   List<Account> AllAccounts();
   
   int insertNewAcc(Account acc);
   
   int DeleteAcc(int id);
   
   int UpdateAcc(Account acc);
   
}
