package com.checkoutservice.rest;

import java.util.ArrayList;

import com.checkoutservice.model.Address;
import com.checkoutservice.model.Item;
import com.checkoutservice.model.Order;

public class JobHandler {
	BusinessLogic businessLogic;	
	public JobHandler(){
		this.businessLogic = new BusinessLogic();

	}
	public String handleOrder(Order order,ArrayList<Item> items,Address address,int amount)	{

		try {
			//int amount = businessLogic.computeAmount(order, items);
			boolean payment = businessLogic.makePayment(amount, order);
			boolean ordersuccessful = businessLogic.updateOrderTable(amount,items,address,order);

			if(payment&& ordersuccessful)
				return "success";	
			return "failure";
		}
		catch (Exception ex){
			System.out.println(ex);
			return "failure";	
		}

	}
	public int computeGiftCard(String giftcardcode){
		int amount = businessLogic.getAmountforGiftCard( giftcardcode);
		return amount;
	}

	public String addToCart(int itemID,int quantity){

		String condition = businessLogic.inventoryCheck(itemID,quantity);

		if (condition.equals("success")){
			return "success";
		}
		return "failure";
	}
	public int computeTax(String zip){	
		int zipc = Integer.parseInt(zip);
		int rate = businessLogic.getTaxforZip(zipc);	
		return rate;
	}
}
