package javaApp;
import java.util.*;

public class SpecificDiscount extends Discount {
	
	Payment payment;
	static String serviceTmp;
	public SpecificDiscount(Payment payment)
	{
		this.payment = payment;
	}
	
	public static boolean searchService(String service)
	{
		for(String s : serviceDiscount.keySet())
		{
			if(s.equals(service))
			{
				serviceTmp = service;
				return true;
			}
		}
		return false;
	}
	static Map<String, Integer> serviceDiscount = new HashMap<String, Integer>() ; //to store service and its applied discount
	{
		{  
	        map.put("Mobile recharge service",20);
		}
	}
	public double pay(double price) {
		int discount = serviceDiscount.get(serviceTmp);
		return payment.pay(price) - (price *(discount/100));
	}
	
}