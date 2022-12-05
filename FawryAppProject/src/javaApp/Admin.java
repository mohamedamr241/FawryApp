package javaApp;

import java.util.ArrayList;

public class Admin implements Subject{
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
	public void subscribe(Observer ob) {
		systemUsers.add(ob);
	}
	public void Notify(String note) {
		for(int i=0;i<systemUsers.size();i++) {
			systemUsers.get(i).update(note);
		}
	}
}
