package com.checkoutservice.rest;

import java.util.ArrayList;

import com.checkoutservice.model.Address;
import com.checkoutservice.model.Item;
import com.checkoutservice.model.Order;

public class BusinessLogic {

	public int computeAmount(Order order,ArrayList<Item> items){

		//compute gift card value
		//compute tax
		//compute final amount
		int amount = 1000;
		int tax = 90;
		int giftCard = 10;
		return  amount + tax - giftCard;
	}
	public boolean makePayment(int amount,Order order){
		return true;
		//payment gateway logic here
	}
	public boolean updateOrderTable(int amount,ArrayList<Item> items,Address address,Order order){
		int orderID = 10 ;// Generate unique orderID
		return true;//insert into  orders table orderID,Address,items,shippingOption,email
	}
	public String inventoryCheck(int itemId,int quantity){
		//Check DB for item 
		return "success";
	}
	public int getTaxforZip(int zip){
		if(true)// todo :check tax rate logic
			return 10;
		return -1;// else return -1
	}
	public int getAmountforGiftCard(String giftcardcode){

		if(true)// check giftcard amount
			return 25;
		return -1;// else return -1
	}
}