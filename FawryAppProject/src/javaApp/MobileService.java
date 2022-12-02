package javaApp;

import java.util.HashMap;
import java.util.Map;

public class MobileService extends Services{
	public static HashMap<String, String[]> serviceProviders;
	public MobileService() {
		serviceProviders = new HashMap<String, String[]>();
	}
	public void add(String serP, String[] form) {
	}
	public void here() {
		
		System.out.print("hi");
	}
	public FinancialServices MobileServiceProvider(String type) {
		if(type=="we") 
			return new We();
		else{
			return null;
		}
	}
}
