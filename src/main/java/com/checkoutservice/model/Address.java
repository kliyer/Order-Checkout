package com.checkoutservice.model;

public class Address {
	
	int zip;
	String city;
	String state;
	String street;
	String country;
	String aptnumber;
	
	public Address(int zip,String city,String state,String street,String country,String aptnumber){
		
		this.aptnumber = aptnumber;
		this.city = city;
		this.country = country;
		this.state = state;
		this.zip = zip;
		this.street = street;
		
	}

}
