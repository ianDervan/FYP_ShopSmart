package com.corservlets;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;


public class SendRota implements Serializable {

	JSONArray sendRota;
	JSONArray sendRotaNw;


	public SendRota(JSONArray jsArray,JSONArray jsArray1) {

		sendRota = jsArray; 
		sendRotaNw = jsArray1;

	}


	public JSONArray getRota() {

		return sendRota;
	}
	public JSONArray getRotaNW() {

		return sendRotaNw;
	}

}
