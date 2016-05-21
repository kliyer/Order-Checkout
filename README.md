# Order-Checkout

Assumptions

1.Tax rate varies according to zipcode
2.No database calls expected
3.No payment gateway implemented
4.Placeholder business logic not actual logic in backend
5.Json input and output 
6.No Security Implications 

API Documentaion:

1) This data endpoint is used to make payments and complete order using card credentials and other information mentioned below
POST http://localhost:8080/CheckoutService/checkout/completeOrder
Request Body:
{
	"email":String,               
	"cardnumber":String,
	"cvv":String,
	"expiry":String,
	"cardzipcode":String,
	"amount":String,
	"giftcardCode":String,
	"shippingOption":String,
	"Items":[
		{
		"id":String,
		"name":String,
		"cost":String,
		"quantity":String
		},	
	],
	"zip":String,
	"city":String,
	"street":String,
	"state":String,
	"country":String,
	"aptnumber":String
};

Request Body Example:
{
	"email":"abc@gmail.com",
	"cardnumber":"124567890",
	"cvv":"368","expiry":"7/2019",
	"cardzipcode":"85284","amount":"125",
	"giftcardCode":"12345a",
	"shippingOption":"2 day",
	"Items":[
		{
		"id":"101",
		"name":"nike shoes"
		,"cost":"200","quantity":"1"
		},
		{
		"id":"101",
		"name":"adidas shoes",
		"cost":"300",
		"quantity":"1"
		}
	],
	"zip":"98124",
	"city":"Tempe",
	"street":"1234 N market street",
	"state":"AZ",
	"country":"US",
	"aptnumber":"397"
};
Response:
200
{
“success” :"Order Completed SuccessFully"

}

ERRORS 
400 for Json Format Exception
400 for Order Failure
400 for invalid Parameters

2) This data endpoint is used check if inventory has the required quantity to process the order
GET http://localhost:8080/CheckoutService/checkout/inventoryCheck
Request Body: No request body is required
Parameters:
itemID : String
quantity : int
Sample Request:
GET http://localhost:8080/CheckoutService/checkout/inventotyCheck?itemID=12&quantity:3

Response:
200
{
“success” :"Inventory Check Successful"

}

ERRORS 
400 for inventory check failure

3) This data endpoint is used retrieve amount for a particular gift code
GET http://localhost:8080/CheckoutService/checkout/giftCard
Request Body: No request body is required
Parameters:
giftCardCode: String

Sample Request:
GET http://localhost:8080/CheckoutService/checkout/giftCard?giftCardcode=1234df

Response:
200
{
"amount":"50"

}

ERRORS 
400 for giftcard code failure

4) This data endpoint is used to get tax rate for a particular zip code
GET http://localhost:8080/CheckoutService/checkout/taxrate
Request Body: No request body is required
Parameters:
zip: String

Sample Request:
http://localhost:8080/CheckoutService/checkout/taxrate?zip=85281

Response:
200
{
“rate” :"15"

}

ERRORS 
400 for wrong code