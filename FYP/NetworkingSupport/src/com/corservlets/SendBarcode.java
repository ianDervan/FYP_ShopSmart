package com.corservlets;
import java.io.Serializable;

import org.json.JSONArray;


public class SendBarcode implements Serializable {
    private final String itemTxt,itemPrice;  
    
   

    
    public SendBarcode(String p1,String p2) {
        itemTxt = p1;
        itemPrice = p2;


    }
  

	public String getItemTxt() {

		return itemTxt;
	}
	public String getItemPrice() {

		return itemPrice;
	}
	

}
