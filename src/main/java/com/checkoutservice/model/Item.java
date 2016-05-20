package com.checkoutservice.model;

public class Item {
	
	String name;
	int id;
	int cost;
	int quantity;
	public Item(String name, int id ,int cost,int quantity){
		this.name = name;
		this.id = id;
		this.cost = cost;
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id= id;
	}
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost= cost;
	}
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		
		this.quantity = quantity;
	}

}
