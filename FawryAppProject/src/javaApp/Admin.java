package javaApp;

import java.util.*;

public class Admin implements Subject{
	public static ArrayList<Obj> reqRefundList = new ArrayList<Obj>();
	static ArrayList<Observer> systemUsers = new ArrayList<Observer>();
	public boolean signIn(String email, String pass)
	{
		if(Account.adminEmail.equals(email)&& Account.adminPass.equals(pass))
			return true;
		return false;
	}
	
	public void addDiscount(String service, int dis)
	{
		SpecificDiscount.serviceDiscount.put(service,dis);
		Notify(dis+" is applied on "+ service);
	}


	public boolean proccessRefund(Obj obj)
	{
		boolean found = false;
		for(Obj o : Transactions.transactions)
		{
			if(o.transId == obj.transId && o.amount == obj.amount && o.serviceName.equals(obj.serviceName) && o.userEmail.equals(obj.userEmail))
			{
				found = true;
				break;
			}
		}
		reqRefundList.remove(obj);
		return found;
	}

	@Override
	public void Notify(String note) {
		for(int i=0;i<systemUsers.size();i++) {
			systemUsers.get(i).update(note);
		}
		
	}

	@Override
	public void subscribe(Observer ob) {
		systemUsers.add(ob);
	}
	
}
