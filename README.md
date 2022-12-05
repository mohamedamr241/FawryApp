# FawryApp
software Engineering project.
1 – Admin has fixed user name -> admin@gmail.com, password -> 0000
2 – Search for any service should be done by its name (MobileRecharge, InternetPayment, Landline, Donations).

3- There’s an interface class Handler, handler of each provider service form should implement this class but we didn’t do all handlers   to avoid class explosion.

4- User must know his transaction ID to be able to request refund.

5- Money of the refund process will be back in user’s wallet.

6- “Each refund request should contain the related service and the amount to be refunded” -> amount to be refunded is the amount after discount is applied (amount that user actually paid).

7- Pay by wallet applied in mobile recharge service.
