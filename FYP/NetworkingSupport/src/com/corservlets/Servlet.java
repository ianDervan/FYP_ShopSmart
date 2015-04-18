package com.corservlets; 

import org.json.*;

import java.io.*; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {



	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

		String inputB = request.getParameter("barcode");
		String inputM = request.getParameter("manage");
		String inputT = request.getParameter("user");
		String inputR = request.getParameter("rota");
		String inputP = request.getParameter("price");
		String inputS = request.getParameter("stock");
		String inputSE = request.getParameter("search");

		Map<String,String> mapTimes = new HashMap<String,String>(); 
		String temp1 = null;
		String temp2 = null;
		String temp3 = null;
		String temp4 = null;

		String startTime;
		String finishTime;
		String breakIn;
		String breakOut;
		String userName;

		///Rata Variables

		String rotaRecieved = null;
		String userNameFR;
		String swapDay = null;
		String withUser = null;
		String forDay = null;
		String day = null;

		///Price Variables
		String checkPrice;
		String barcodeRecieved;
		String itemTxtR;
		String newPriceR;
		String sendItem;
		String sendItemPrice;
	

		String setUpPrice;
		String setUpName;
		String setUpBar;
		String setUpQ;
		String setUpP;

		String deleteItem;
		String deleteItemTxt;

		///Stock Variables
		String updateStock;
		String addItem;
		String addToList;
		String addStock;
		String getBarS;
		String sendItemTFS;
		String sendItemSFS;
		String getItemFS;
		String getItemSTK;
		JSONArray empty = new JSONArray();

		ArrayList<String> getStockdata = new ArrayList<String>();
		ArrayList<String> getStockQty = new ArrayList<String>();

		///Manage Variables
		String manage;
		String staffName;
		String operation;

		String deliveryN;
		String deliveryD;
		String deliveryT;
		if(inputT != null)
		{

			try { 

				JSONObject inputTimes = new JSONObject(inputT); 


				if(inputTimes != null)
				{


					userName=inputTimes.getString("userName");
					temp1 = inputTimes.getString("TimeIn");
					temp2 = inputTimes.getString("BreakIn");
					temp3 = inputTimes.getString("BreakOut");
					temp4 = inputTimes.getString("TimeOut");

					if(temp4.equals("2") )
					{
						System.out.print("temp 4"+temp4 +"\n");
						GetUserData data1 = new GetUserData();
						data1.getData(userName); 
						data1.getStaffData(userName); 

						startTime = data1.getDataResult("StartTime");
						finishTime = data1.getDataResult("FinishTime");
						breakIn = data1.getDataResult("BreakIn");
						breakOut=data1.getDataResult("BreakOut");

						String start = startTime;
						String finish = finishTime;
						String breakI = breakIn;
						String breakO = breakOut;
						JSONArray jsArray = new JSONArray(data1.getUserDataResult());
						SendTimes info = new SendTimes(start, finish, breakI,breakO,jsArray);
						PrintWriter out = response.getWriter();
						out.println(new JSONObject(info));

					}
					else  if(temp1.equals("3") && temp4.equals("3"))
					{
						Database data = new Database(null,null,null,null);
						data.delete(userName);
					} 

					else if(temp1.equals("4") && temp4.equals("4"))
					{
						System.out.print("Delete table"+ "\n");
						Database data = new Database(null,null,null,null);
						data.deleteTable(userName);
					}

					else if(!temp1.equals("N")  &&temp3.equals("N"))
					{


						System.out.print("temp1 "+temp1 +"\n");
						Database data = new Database(temp1, null, null, null);
						data.readData(userName);

					}
					else if(!temp2.equals("N")  && temp3.equals("N"))
					{
						System.out.print("temp2 "+temp2 +"\n");
						Database data = new Database(null,temp2, null, null);
						data.readData(userName);


					}
					else if(!temp3.equals("N") &&temp2.equals("N"))
					{
						System.out.print("temp3 "+temp3 +"\n");
						Database data = new Database(null, null, temp3, null);
						data.readData(userName);
					}
					else if(!temp4.equals("N") && temp3.equals("N") )
					{
						System.out.print("temp4 "+temp4 +"\n");
						Database data = new Database(null, null, null, temp4);
						data.readData(userName);

						GetUserData data1 = new GetUserData();
						data1.getHoursDay(userName); 
					}

				}

			} catch (Exception e) { 

				System.out.print("\n exception ..." + e);

			}
		}
		if(inputR != null)
		{
			try{
				JSONObject inputRota = new JSONObject(inputR); 
				if(inputRota != null)
				{

					rotaRecieved = inputRota.getString("rotaRequested");


					if(rotaRecieved.equals("1"))
					{
						RotaData rota = new RotaData();
						rota.getRota(); 
						rota.getRotaNW(); 

						JSONArray jsArray = new JSONArray(rota.getRotaDataResult());
						JSONArray jsArray1 = new JSONArray(rota.getRotaNWResult());

						SendRota info = new SendRota(jsArray,jsArray1);

						PrintWriter out = response.getWriter();
						out.println(new JSONObject(info));
					}
					else if((rotaRecieved.equals("2")))
					{
						userNameFR=inputRota.getString("userNameFR");
						swapDay=inputRota.getString("swapDay");
						withUser=inputRota.getString("withUser");
						forDay=inputRota.getString("forUsersDay");

						RotaData rota = new RotaData();
						rota.swapDay(userNameFR,swapDay,withUser,forDay); 

						rota.getRota(); 
						JSONArray jsArray = new JSONArray(rota.getRotaDataResult());
						SendRota info = new SendRota(jsArray,empty);
						PrintWriter out = response.getWriter();
						out.println(new JSONObject(info));

					}
					else if((rotaRecieved.equals("3")))
					{
						userNameFR=inputRota.getString("userNameFR");
						day=inputRota.getString("Day");


						System.out.print("\n user is .." + userNameFR);
						System.out.print("\n day is .." + day);

						RotaData rota1 = new RotaData();
						rota1.requestDayOff(day,userNameFR);


						rota1.getRotaNW(); 


						JSONArray jsArray1 = new JSONArray(rota1.getRotaNWResult());

						SendRota info = new SendRota(empty,jsArray1);

						PrintWriter out = response.getWriter();
						out.println(new JSONObject(info)); 

					}
					else if((rotaRecieved.equals("4")))
					{
						String user1 =inputRota.getString("user1");
						String shift1 =inputRota.getString("shift1");
						String day1 =inputRota.getString("day1");

						String user2 =inputRota.getString("user2");
						String shift2 =inputRota.getString("shift2");
						String day2 =inputRota.getString("day2");

						String user3 =inputRota.getString("user3");
						String shift3 =inputRota.getString("shift3");
						String day3 =inputRota.getString("day3");

						Map<String,String> rota = new HashMap<String,String>(); 

						rota.put("user1",user1);
						rota.put("shift1",shift1);
						rota.put("day1",day1);
						rota.put("user2",user2);
						rota.put("shift2",shift2);
						rota.put("day2",day2);
						rota.put("user3",user3);
						rota.put("shift3",shift3);
						rota.put("day3",day3);


						RotaData rotaInsert = new RotaData();
						rotaInsert.insertShift(rota);

						rotaInsert.getRota(); 


						JSONArray jsArray = new JSONArray(rotaInsert.getRotaDataResult());


						SendRota info = new SendRota(jsArray,empty);
						PrintWriter out = response.getWriter();
						out.println(new JSONObject(info));

						//rota.swapDay(userNameFR,swapDay,withUser,forDay); 

					}
					else if((rotaRecieved.equals("5")))
					{
						String user1 =inputRota.getString("user1");
						String shift1 =inputRota.getString("shift1");
						String day1 =inputRota.getString("day1");

						String user2 =inputRota.getString("user2");
						String shift2 =inputRota.getString("shift2");
						String day2 =inputRota.getString("day2");

						String user3 =inputRota.getString("user3");
						String shift3 =inputRota.getString("shift3");
						String day3 =inputRota.getString("day3");

						Map<String,String> rota = new HashMap<String,String>(); 

						rota.put("user1",user1);
						rota.put("shift1",shift1);
						rota.put("day1",day1);
						rota.put("user2",user2);
						rota.put("shift2",shift2);
						rota.put("day2",day2);
						rota.put("user3",user3);
						rota.put("shift3",shift3);
						rota.put("day3",day3);


						RotaData rotaInsert = new RotaData();
						//	rotaInsert.insertShift(rota);

						rotaInsert.clearShift(rota);

						rotaInsert.getRota(); 


						JSONArray jsArray = new JSONArray(rotaInsert.getRotaDataResult());


						SendRota info = new SendRota(jsArray,empty);

						PrintWriter out = response.getWriter();
						out.println(new JSONObject(info));



						//rota.swapDay(userNameFR,swapDay,withUser,forDay); 

					}
				}
			} catch (Exception e) { 

				System.out.print("\n exception ..." + e);
			}
		}
		if(inputP != null)
		{

			try{
				JSONObject inputPrice = new JSONObject(inputP); 
				if(inputPrice != null)
				{

					checkPrice = inputPrice.getString("checkPrice");
					setUpPrice = inputPrice.getString("setUpPrice");
					deleteItem = inputPrice.getString("deleteItem");

					if(checkPrice.equals("1"))
					{
						barcodeRecieved = inputPrice.getString("barcode");
						itemTxtR = inputPrice.getString("checkPriceTxt");
						newPriceR = inputPrice.getString("newPrice");
						System.out.print("\n checkPrice" + checkPrice);

						if(!barcodeRecieved.equals("N") && itemTxtR.equals("N"))
						{
							System.out.print("\n barcode recievied = " +barcodeRecieved);
							PriceData price = new PriceData();
							price.getItem( barcodeRecieved);
							sendItem = price.getItemText();
							SendPrice info = new SendPrice(sendItem,"N");
							PrintWriter out = response.getWriter();
							out.println(new JSONObject(info));

						}
						else if(newPriceR.equals("N") && !itemTxtR.equals("N"))
						{
							System.out.print("\n itemRecieved = " +itemTxtR);
							PriceData price = new PriceData();
							price.getPrice(itemTxtR);
							sendItemPrice = price.getItemPrice();
							SendPrice info = new SendPrice("N",sendItemPrice);
							PrintWriter out = response.getWriter();
							out.println(new JSONObject(info));

						}
						else if(barcodeRecieved.equals("N") && !newPriceR.equals("N") && !itemTxtR.equals("N"))
						{
							System.out.print("\n new Price Recieved = " +newPriceR);
							System.out.print("\n new Item Recieved = " +itemTxtR);
							PriceData newPrice = new PriceData();
							newPrice.setNewPrice(itemTxtR,newPriceR);
						}	
					}
					if(setUpPrice.equals("1"))
					{
						setUpName = inputPrice.getString("inputName");
						setUpBar = inputPrice.getString("inputBar");
						setUpQ = inputPrice.getString("inputQuantity");
						setUpP= inputPrice.getString("inputPrice");
						PriceData setUp = new PriceData();
						setUp.setUpNewItem(setUpName,setUpBar,setUpP,setUpQ);
					}
					if(deleteItem.equals("1"))
					{
						deleteItemTxt = inputPrice.getString("deleteTxt");
						System.out.print("\n Item for delete= " + deleteItemTxt);
						PriceData delete = new PriceData();
						delete.deleteItem(deleteItemTxt);
					}
				}
			} catch (Exception e) { 

				System.out.print("\n exception ..." + e);
			}
		}
		if(inputS != null)
		{
			try{
				JSONObject inputStock = new JSONObject(inputS); 
				if(inputStock != null)
				{
					updateStock = inputStock.getString("updateStock");
					addItem = inputStock.getString("addItem");

					if(updateStock.equals("1"))
					{
						getBarS = inputStock.getString("barcodeS");
						getItemFS = inputStock.getString("itemTxt");
						getItemSTK =inputStock.getString("newStock");

						if(!getBarS.equals("N") && getItemFS.equals("N"))
						{
							System.out.print("\n Barcode" + getBarS);

							PriceData stock = new PriceData();
							stock.getItem( getBarS);
							sendItemTFS = stock.getItemText();

							SendStock infos = new SendStock(sendItemTFS, "",empty,empty);
							PrintWriter outs = response.getWriter();
							outs.println(new JSONObject(infos));
						}
						else if( !getItemFS.equals("N") && getItemSTK.equals("N"))
						{
							System.out.print("\n Barcode" + getItemFS);
							StockData ss = new StockData();
							ss.getStock(getItemFS);

							sendItemTFS =  ss.getStockNo();

							SendStock infos = new SendStock("",sendItemTFS,empty,empty);
							PrintWriter outs = response.getWriter();
							outs.println(new JSONObject(infos));


						}
						else if(getBarS.equals("N") && !getItemFS.equals("N") && !getItemSTK.equals("N"))
						{
							System.out.print("\n new Price Recieved = " +getItemFS);
							System.out.print("\n new Item Recieved = " +getItemSTK);

							StockData ssn = new StockData();
							ssn.setNewStock(getItemFS,getItemSTK);

						}	

						//System.out.print("\n Barcode" + sendItemFS);
					}
					if(addItem.equals("1"))
					{
						addToList = inputStock.getString("addItemTxt");
						addStock = inputStock.getString("addStockNo");

						StockData ssa = new StockData();
						ssa.addItem(addToList,addStock);

						ssa.getItem();

						getStockdata =ssa.getItemName();
						getStockQty = ssa.getItemStk();

						JSONArray jsArray = new JSONArray(getStockdata);
						JSONArray jsArray1 = new JSONArray(getStockQty);
						SendStock infos = new SendStock("","", jsArray,jsArray1);
						PrintWriter outs = response.getWriter();
						outs.println(new JSONObject(infos));
					}
					if(addItem.equals("2") && updateStock.equals("2"))
					{
						StockData ssa = new StockData();
						ssa.getItem();

						getStockdata =ssa.getItemName();
						getStockQty = ssa.getItemStk();

						JSONArray jsArray = new JSONArray(getStockdata);
						JSONArray jsArray1 = new JSONArray(getStockQty);
						SendStock infos = new SendStock("","", jsArray,jsArray1);
						PrintWriter outs = response.getWriter();
						outs.println(new JSONObject(infos));

					}
					if(addItem.equals("3") && updateStock.equals("3"))
					{

						System.out.print("\n DELETE");
						StockData clear = new StockData();
						clear.clearTable();



					}
				}
			} catch (Exception e) { 

				System.out.print("\n exception ..." + e);
			}

		}
		if(inputM != null)
		{

			try{
				JSONObject inputManage = new JSONObject(inputM); 
				if(inputManage != null)
				{
					manage = inputManage.getString("manage");

					if(manage.equals("1"))
					{
						staffName = inputManage.getString("StaffName");
						operation = inputManage.getString("Operation");

						System.out.print("\n staffName ..." + staffName);
						System.out.print("\n operation ..." + operation);

						ManageData md = new ManageData();
						md.insertOp(staffName,operation);

						md.getData();
						JSONArray jsArray = new JSONArray(md.getItemOp());
						SendManage infom = new SendManage( jsArray,empty);
						PrintWriter outm = response.getWriter();
						outm.println(new JSONObject(infom));
					}
					else if(manage.equals("2"))
					{

						ManageData md = new ManageData();
						md.getData();
						md.getDataFDT();
						JSONArray jsArray = new JSONArray(md.getItemOp());
						JSONArray jsArray1 = new JSONArray(md.getItemFDT());

						SendManage infom = new SendManage( jsArray,jsArray1);
						PrintWriter outm = response.getWriter();
						outm.println(new JSONObject(infom));
					}
					else if(manage.equals("3"))
					{

						deliveryN = inputManage.getString("delivery");
						deliveryD = inputManage.getString("day");
						deliveryT = inputManage.getString("time");

						System.out.print("\n name " + deliveryN);
						System.out.print("\n name " + deliveryD);
						System.out.print("\n name " + deliveryT + "\n");
						ManageData mdft = new ManageData();
						mdft.insertDelivery(deliveryN, deliveryD, deliveryT);
						mdft.getDataFDT();

						JSONArray jsArray1 = new JSONArray(mdft.getItemFDT());

						SendManage infom = new SendManage( empty,jsArray1);
						PrintWriter outm = response.getWriter();
						outm.println(new JSONObject(infom));

					}

					else if(manage.equals("4"))
					{

						deliveryN = inputManage.getString("delivery");
						deliveryD = inputManage.getString("day");
						JSONArray jsArray1 = null;

						System.out.print("\n name " + deliveryN);
						System.out.print("\n name " + deliveryD);

						if(deliveryN.equals("Clear All"))
						{
							ManageData mdft = new ManageData();
							mdft.clearADataFDT(deliveryD);

							mdft.getDataFDT();
							jsArray1 = new JSONArray(mdft.getItemFDT());
							SendManage infom = new SendManage( empty,jsArray1);
							PrintWriter outm = response.getWriter();
							outm.println(new JSONObject(infom));

						}
						else
						{

							ManageData mdft = new ManageData();
							mdft.clearDataFDT(deliveryN, deliveryD);

							mdft.getDataFDT();
							jsArray1 = new JSONArray(mdft.getItemFDT());
							SendManage infom = new SendManage( empty,jsArray1);
							PrintWriter outm = response.getWriter();
							outm.println(new JSONObject(infom));

						}
					}
					else if(manage.equals("5"))
					{

						staffName = inputManage.getString("StaffName");
						operation = inputManage.getString("Operation");

						System.out.print("\n staffName ..." + staffName);
						System.out.print("\n operation ..." + operation);

						ManageData md = new ManageData();
						md.clearData(staffName, operation);

						md.getData();

						JSONArray jsArray = new JSONArray(md.getItemOp());

						SendManage infom = new SendManage( jsArray,empty);
						PrintWriter outm = response.getWriter();
						outm.println(new JSONObject(infom));

					}

				}
			} catch (Exception e) { 

				System.out.print("\n exception ..." + e);
			}

		}
		if(inputB != null)
		{
			String barcode;
			String listName;
			String barcodeTxt;
			String barcodePrice;

			String item;
			String checkTable;


			try{
				JSONObject inputBarcodes = new JSONObject(inputB); 
				if(inputBarcodes != null)
				{
					barcode = inputBarcodes.getString("getItem");
					listName = inputBarcodes.getString("shopListName");

					if(listName.equals("GETITEM"))
					{
						System.out.print("\nBARCODE\n");

						PriceData price = new PriceData();
						price.getItem( barcode);
						barcodeTxt = price.getItemText();

					

						price.getPrice(barcodeTxt);
						barcodePrice =price.getItemPrice();
						
						System.out.print("\nBARCODE\n" + barcodePrice);
						System.out.print("\nBARCODE\n" + barcodeTxt);

					

						SendBarcode infob = new SendBarcode(barcodeTxt,barcodePrice);
						PrintWriter outb = response.getWriter();
						outb.println(new JSONObject(infob));
					}
//					if(barcode.equals("ADD"))
//					{
//
//						checkTable = inputBarcodes.getString("checkTable");
//						item = inputBarcodes.getString("data");
//
//						if(checkTable.equals("1"))
//						{
//							System.out.print("\nNEW TABLE\n");
//							ShoppingLists sp = new ShoppingLists();
//							sp.checkNew(item,listName);
//
//							Global g = new Global();
//
//							if(g.checkTableName == 1)
//							{
//
//								SendBarcode infob = new SendBarcode("","","nameTaken",empty);
//								PrintWriter outb = response.getWriter();
//								outb.println(new JSONObject(infob));
//							}
//							if(g.checkTableName == 0)
//							{
//								ShoppingLists sh = new ShoppingLists();
//								sh.getData(listName);
//
//								JSONArray jsArray = new JSONArray(sh.getList());
//
//								SendBarcode infob = new SendBarcode("","","", jsArray);
//								PrintWriter outb = response.getWriter();
//								outb.println(new JSONObject(infob));
//
//							}
//
//						}
//						else
//						{
//							System.out.print("\nOLD TABLE\n");
//							
//
//							ShoppingLists sh = new ShoppingLists();
//							sh.checkOld(item,listName);
//
//							Global g = new Global();
//
//							if(g.checkTableName1 == 1)
//							{
//								
//								SendBarcode infob = new SendBarcode("","","noColumn",empty);
//								PrintWriter outb = response.getWriter();
//								outb.println(new JSONObject(infob));
//							}
//							if(g.checkTableName1 == 0)
//							{
//								sh.getData(listName);
//
//								JSONArray jsArray = new JSONArray(sh.getList());
//
//								SendBarcode infob = new SendBarcode("","","", jsArray);
//								PrintWriter outb = response.getWriter();
//								outb.println(new JSONObject(infob));
//							}
//						}
//
//					}
//					
//					if(barcode.equals("SHOW"))
//					{
//						System.out.print("\nSHOW\n");
//						
//						ShoppingLists sh = new ShoppingLists();
//						
//						sh.checkOld("",listName);
//						
//						Global g = new Global();
//						
//						if(g.checkTableName1 == 1)
//						{
//							
//							SendBarcode infob = new SendBarcode("","","noShow",empty);
//							PrintWriter outb = response.getWriter();
//							outb.println(new JSONObject(infob));
//						}
//						if(g.checkTableName1 == 0)
//						{
//							System.out.print("\nGET SHOW");
//							sh.getData(listName);
//	
//							JSONArray jsArray = new JSONArray(sh.getList());
//	
//							SendBarcode infob = new SendBarcode("","","", jsArray);
//							PrintWriter outb = response.getWriter();
//							outb.println(new JSONObject(infob));
//						}
//					}
//
			}
			} catch (Exception e) { 

				System.out.print("\n exception ..." + e);
			}
		}
		if(inputSE != null)
		{
			String search;
			
			String x,y;
			try{
				JSONObject inputSearch = new JSONObject(inputSE); 
				if(inputSearch != null)
				{
					search= inputSearch.getString("GetSearch");
					System.out.print("\nGET SHOW" +search);
					
					Search s = new Search();
					s.getCoOrdinates(search);
					
					x = s.getSearch("searchX");
					y = s.getSearch("searchY");
					
					SendSearch infos = new SendSearch(x,y);
					PrintWriter outs = response.getWriter();
					outs.println(new JSONObject(infos));
					
					
					
				}
			} catch (Exception e) { 

				System.out.print("\n exception ..." + e);
			}
		}

	}
}
