package javaApp;
import java.util.*;

public class SpecificDiscount extends Discount {
	Map<String, Integer> map = new HashMap<String, Integer>() ; //to store service and its applied discount
	{
		{  
	        map.put("mobile service",20);
		}
	}
	
}