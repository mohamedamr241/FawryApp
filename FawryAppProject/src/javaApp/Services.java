package javaApp;

import java.util.ArrayList;
import java.util.HashMap;

public  class Services {
	ArrayList<Services> AllServicesObjects = new ArrayList<Services>();
	ArrayList<String> AllServicesNames = new ArrayList<String>();
	MobileService mobile = new MobileService();
	public Services() {
		AllServicesObjects.add(mobile);
		AllServicesNames.add("mobile service");
	}
	public void getAllServices(){
		for(String name: AllServicesNames)
        {
            System.out.println(name);
        }
	}
	//public static HashMap<String, String[]> services=new HashMap<String, String[]>();
	public Services search(String type) {
		for(int i=0;i<AllServicesNames.size();i++) {
			if(AllServicesNames.contains(type)) {
				return AllServicesObjects.get(i);
			}
		}
		return null;
	}
}
