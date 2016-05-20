package com.checkoutservice.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClientPost {

	public static void main(String[] args) {

		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/CheckoutService/checkout/completeOrder");

			String input = "{\"email\":\"abc@gmail.com\",\"cardnumber\":\"124567890\",\"cvv\":\"368\",\"expiry\":\"7/2019\",\"cardzipcode\":\"85284\",\"amount\":\"125\",\"giftcardCode\":\"12345a\",\"shippingOption\":\"2 day\",\"Items\":[{\"id\":\"101\",\"name\":\"nike shoes\",\"cost\":\"200\",\"quantity\":\"1\"},{\"id\":\"101\",\"name\":\"adidas shoes\",\"cost\":\"300\",\"quantity\":\"1\"}],\"zip\":\"98124\",\"city\":\"Tempe\",\"street\":\"1234 N market street\",\"state\":\"AZ\",\"country\":\"US\",\"aptnumber\":\"397\"}";

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
