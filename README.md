# FawryApp
FawryApp is a payment system inspired by the well-known Fawry system. It allows users to pay for various services including mobile recharge, internet payments, landline services, and donations. The app also offers functionalities such as user authentication, payment processing, refund requests, and discounts.

# Project Overview
In this project, a payment system similar to Fawry is built. Users can pay for different services using the system, which supports mobile recharge, internet payments, landline services, and donations. Admins can manage services, discounts, and refund requests.

# Description
## The system offers the following initial services:

### Mobile Recharge Services
### Internet Payment Services
### Landline Services
### Donations

# Features
## User Features:

### User Authentication:
Users can sign in with their email and password to access system functionalities.
Users can sign up with a username, email, and password. The system checks for pre-existing usernames or emails before completing registration.

### Service Search:
Users can search for any service in the system by typing the service name, and the system will return relevant results.

### Service Payment:
Users can pay for any service through a payment form, using a credit card or wallet balance.
If a service supports cash on delivery, this option is also visible to users.

### Refund Requests:
Users can request a refund for any completed transaction. Refund requests are sent to the admin for approval.

### Wallet:
Users have a wallet balance in the system and can add funds using a credit card.

### Discounts:
Users can check for any applicable discounts on services. Discounts are added by the admin and could be overall discounts or specific service-related discounts.

## Admin Features:
### Service Provider Management:
Admins can add new service providers, which include a form to collect necessary user inputs and a handler to process the form's data. Forms can contain text fields and dropdown fields.

### Discount Management:
Admins can add two types of discounts:
Overall discounts (e.g., 10% off the first transaction)
Specific discounts for particular services (e.g., 20% off mobile recharge services)

### Refund Management:
Admins can view and manage all refund requests. They can approve or reject refunds, and if approved, a refund transaction will be processed.

## Notes:
1 – Admin has fixed user name -> admin@gmail.com, password -> 0000

2 – Search for any service should be done by its name (MobileRecharge, InternetPayment, Landline, Donations).

3- There’s an interface class Handler, handler of each provider service form should implement this class but we didn’t do all handlers   to avoid class explosion.

4- User must know his transaction ID to be able to request refund.

5- Money of the refund process will be back in user’s wallet.

6- “Each refund request should contain the related service and the amount to be refunded” -> amount to be refunded is the amount after discount is applied (amount that user actually paid).

7- Pay by wallet applied in mobile recharge service.

8- Providers of mobile recharge service (We, Vodafone, Orange, and Etisalat) have mobile number field in their form, it must contain 11 numbers and starts with 015 for We, 011 for Etisalat, etc.. (Handler of each form validates the input).
