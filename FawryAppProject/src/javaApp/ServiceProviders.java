package javaApp;

import java.util.ArrayList;
import java.util.Scanner;

public interface ServiceProviders {
	Scanner scan = new Scanner(System.in);
	ArrayList<String> serviceProviderForm = new ArrayList<String>();
	public ArrayList<String> getForm();
}
