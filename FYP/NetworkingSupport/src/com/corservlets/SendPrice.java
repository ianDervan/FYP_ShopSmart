package com.corservlets;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class SendPrice implements Serializable {
    private final String itemTxt,itemPrice;  

    
    public SendPrice(String p1,String p2) {
        itemTxt = p1;
        itemPrice = p2;
       
   //System.out.println("itemPrice" + itemPrice);
    }

	public String getItemTxt() {

		return itemTxt;
	}
	public String getItemPrice() {

		return itemPrice;
	}


}
