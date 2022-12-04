package javaApp;
import java.util.*;
public abstract class Discount implements Payment{

	Map<String, Integer> map = new HashMap<String, Integer>() ;
	{
		{  
	        map.put("OverAll discount", 10);
	        map.put("Specific discount", 20);
		}
	}
	public abstract double pay(double price);
}
