package com.checkoutservice.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.checkoutservice.model.Address;
import com.checkoutservice.model.Item;
import com.checkoutservice.model.Order;
import com.checkoutservice.rest.Validator;

@Path("/checkout")
public class JSONService {
	JobHandler jobHandler = new JobHandler();
	Validator validator = new Validator();

	@GET
	@Path("/inventoryCheck")

	public Response checkInventory(@Context UriInfo info) {

		String itemID = info.getQueryParameters().getFirst("itemID");
		String quantity = info.getQueryParameters().getFirst("quantity");
		String success = jobHandler.addToCart(Integer.parseInt(itemID), Integer.parseInt(quantity));
		if(success.equals("success"))
			return Response
					.status(200)
					.entity("{\"Success\":\"Inventory Check Success\"}" ).build();
		return Response.status(400).entity("{\"Error\":\"Inventory Check Failed\"}").build();

	}
	@GET
	@Path("/taxrate")

	public Response getTaxrate(@Context UriInfo info) {

		String zip = info.getQueryParameters().getFirst("zip");
		int success = jobHandler.computeTax(zip);
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("taxrate", success);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(success!=-1)
			return Response
					.status(200)
					.entity(jsonObj.toString()).build();
		else
			return Response
					.status(400)
					.entity("{\"Error\":\"Invalid Zipcode\"}").build();
	}
	@GET
	@Path("/giftcard")

	public Response getGiftCardAmount(@Context UriInfo info) {

		String giftCardCode = info.getQueryParameters().getFirst("giftcardCode");
		int success = jobHandler.computeGiftCard(giftCardCode);
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("giftcardvalue", success);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(success!=-1)
			return Response
					.status(200)
					.entity(jsonObj.toString()).build();
		else
			return Response
					.status(400)
					.entity("{\"Error\":\"Invalid Gift Card\"}").build();
	}

	@POST
	@Path("/completeOrder")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response completeOrder(String track) {
		ArrayList <Item> items = new ArrayList<Item>();

		String result = "Track saved : " + track;
		try {
			JSONObject jsonObj = new JSONObject(track);
			String cardnumber = jsonObj.getString("cardnumber");
			String cvv = jsonObj.getString("cvv");
			String expiry = jsonObj.getString("expiry");
			String cardzipcode = jsonObj.getString("cardzipcode");
			int amount = jsonObj.getInt("amount");
			String giftcardCode = jsonObj.getString("giftcardCode");
			String email = jsonObj.getString("email");
			String shippingOption = jsonObj.getString("shippingOption");
			JSONArray jsonMainArr = (jsonObj.getJSONArray("Items"));
			for (int i = 0; i < jsonMainArr.length(); i++) {  // **line 2**
				JSONObject childJSONObject = jsonMainArr.getJSONObject(i);
				String name = childJSONObject.getString("name");
				int cost     = childJSONObject.getInt("cost");
				int quantity = childJSONObject.getInt("quantity");
				int id = childJSONObject.getInt("id");
				items.add(new Item(name,id,cost,quantity));
			}
			int zip = jsonObj.getInt("zip");
			String city = jsonObj.getString("city");
			String state = jsonObj.getString("state");
			String street = jsonObj.getString("street");
			String country = jsonObj.getString("country");
			String aptnumber = jsonObj.getString("aptnumber");
			Order order = new Order(cardnumber,cvv,expiry,cardzipcode,amount,items,giftcardCode,shippingOption,email);

			Address address = new Address(zip,city,state,street,country,aptnumber);
			Integer zips = new Integer(zip);

			if(validator.isValidDetails(cardnumber, cvv, email,zips.toString() , expiry))
				return Response.status(400).entity("{\"Error\":\"Invalid Input Parameters\"}").build();
			String success =jobHandler.handleOrder(order, items, address, amount);
			if (success.equals("success")){
				return Response.status(201).entity("{\"Success\":\"Order Placed Successfully\"}").build();
			}


		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(400).entity("{\"Error\":\"Json Format Exception\"}").build();
		}
		return Response.status(400).entity("{\"Error\":\"Order Failure\"}").build();


	}
}