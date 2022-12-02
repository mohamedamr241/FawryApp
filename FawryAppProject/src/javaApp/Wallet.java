package javaApp;

public class Wallet implements Payment{
	int price;
	public Wallet(int price) {
		this.price=price;
	}
	public void pay(int amount) {
		System.out.println("payment with wallet to this service is done successfully");
	}
}
