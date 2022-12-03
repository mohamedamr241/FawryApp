package javaApp;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class ServiceProviders {
	public ServiceProviders()
	{
		setDataForm();
	}
	ArrayList<String> serviceProviderForm = new ArrayList<String>();
	protected abstract void setDataForm();
	public ArrayList<String> getForm() {
		return serviceProviderForm;
	}
}
