package javaApp;

import java.util.*;


public class Main {
	public static void main (String [] args) {
		Scanner scan = new Scanner(System.in);
//		Account acc = new Account();
		boolean activation=true;
		while(activation) {
			System.out.println("[1] User:");
			System.out.println("[2] Admin:");
			int option = scan.nextInt();
			if(option == 1)
			{
				User user = new User();
				boolean cont=true;
				while(cont) {
//					for (Map.Entry<String, String> entry : Account.userAccounts.entrySet())
//					{
//						System.out.println(entry.getKey());
//						System.out.println(entry.getValue());
//					}
					System.out.println("[1] Sign-in:");
					System.out.println("[2] Sign-up:");
					System.out.println("[3] logout:");
					int option2 = scan.nextInt();
					switch (option2) {
					case 1:
						
						System.out.println("Enter email:");
						String e = scan.next();
						System.out.println("Enter password:");
						String p = scan.next();
						boolean ok = user.signIn(e, p);
						if(ok)
						{
							UserSystemBoundary userBoundary = new UserSystemBoundary(e);
							userBoundary.Display();
						}
						else
						{
							System.out.println("Email or password incorrent, try agin");
						}
						
						break;
						
					case 2:
						System.out.println("Enter username:");
						String u = scan.next();
						System.out.println("Enter email:");
						String e1 = scan.next();
						System.out.println("Enter password:");
						String p1 = scan.next();
						String response = user.signUp(u, e1, p1);
						System.out.println(response);
						break;
					case 3:
						cont=false;
						break;
					}
				}	
			}
			//Admin email -> "admin@gmail.com", Admin Password -> "0000";
			else
			{
				Admin admin = new Admin();
				System.out.println("Enter email:");
				String e = scan.next();
				System.out.println("Enter password:");
				String p = scan.next();
				boolean ok = admin.signIn(e, p);
				if(ok)
				{
					AdminSystemBoundry adminBoundary = new AdminSystemBoundry();
					adminBoundary.Display();
				}
				else
				{
					System.out.println("Email or password incorrent, try agin");
				}
							
			}
		}
		
	}
}
