package com.checkoutservice.rest;

import java.util.regex.*;

public class Validator {

	public boolean isValidEmailAddress(String email) {

		String email_Pattern = "[a-zA-Z][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";
		Pattern p = Pattern.compile(email_Pattern);
		Matcher m = p.matcher(email);
		return m.matches();

	}

	public boolean isValidCardNumber(String cardNo) {

		String cardNoPattern = "[0-9]{16}";
		Pattern p = Pattern.compile(cardNoPattern);
		Matcher m = p.matcher(cardNo);
		return m.matches();

	}

	public boolean isValidPinCode(String pinCode) {

		String email_Pattern = "[0-9]{5}";
		Pattern p = Pattern.compile(email_Pattern);
		Matcher m = p.matcher(pinCode);
		return m.matches();

	}


	public boolean isValidCvvCode(String cvv) {

		String email_Pattern = "[0-9]{3}";
		Pattern p = Pattern.compile(email_Pattern);
		Matcher m = p.matcher(cvv);
		return m.matches();

	}

	public boolean isValidExpiryDate(String expMonth,String expYear) {

		String exp_Month_Pattern = "[1-12]";
		Pattern monthPattern = Pattern.compile(exp_Month_Pattern);
		Matcher monthMatcher = monthPattern.matcher(expMonth);

		String exp_Year_Pattern = "[2000-2100]";
		Pattern yearPattern = Pattern.compile(exp_Year_Pattern);
		Matcher yearMatcher = yearPattern.matcher(expMonth);

		if(monthMatcher.matches() && yearMatcher.matches())
			return true;
		return false;

	}
	public boolean isValidDetails(String cardnumber,String cvv,String email,String zipcode,String expiry){
		String [] date = expiry.split("/");
		if(!isValidExpiryDate(date[0], date[1]))
			return false;
		if(!isValidCardNumber(cardnumber))
			return false;
		if(!isValidCvvCode(cvv))
			return false;
		if(!isValidEmailAddress(email))
			return false;
		if(!isValidPinCode(zipcode))
			return false;	

		return true;	
	}


}

