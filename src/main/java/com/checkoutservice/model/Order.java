package com.checkoutservice.model;

import java.util.ArrayList;

public class Order {
	
	String cardnumber;
	String cvv;
	String expiry;
	String cardzipcode;
	int amount;
	ArrayList <Item> items;
	String giftcardcode;
	String shippingOption;
	String email;
	
	public Order(String cardnumber,String cvv,String expiry,String cardzipcode,int amount,ArrayList <Item> items,String giftcardcode,String shippingOption,String email){
		
		this.cardnumber= cardnumber;
		this.amount = amount;
		this.cardzipcode = cardzipcode;
		this.cvv = cvv;
		this.items = items;
		this.expiry = expiry;
		this.giftcardcode = giftcardcode;
		this.shippingOption = shippingOption;
		this.email = email;
	}
	String getShippingOption(){
		return this.shippingOption;
	}
	String getCvv(){
		return this.cvv;
	}
	String getCardNumber(){
		return this.cardnumber;
	}
	String getGiftCardCode(){
		return this.giftcardcode;
	}
	String getExpiry(){
		return this.expiry;
		}
	String getCardZipcode(){
		return this.cardzipcode;
	}
	String getEmail(){
		return this.email;
	}
	
	

}
