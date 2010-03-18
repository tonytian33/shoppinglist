package com.wicket_projects.shoppinglist.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.infomata.data.CSVFormat;
import com.infomata.data.DataFile;
import com.infomata.data.DataRow;
import com.wicket_projects.shoppinglist.model.ShopItem;
import com.wicket_projects.shoppinglist.model.ShoppingListModel;


public class ItemListManager {
	
	private String strErrMsg;
	
	//constructor
	public ItemListManager(){
	}
	
	/**
	 * read default shopping list
	 * @param shoppingListModel
	 * @return
	 */
	public static boolean readDefaultList(Collection<ShopItem> defaultList){
		boolean rtv = true;
		
		return rtv;
	}
	
	//read list from file 
	public static boolean readList(ShoppingListModel shoppingListModel){
		boolean succeed = false; 
		
		List items;
		
		items = readFile(".\\MyList.csv");
		if (items != null){
			shoppingListModel.setShopItems(items);
			succeed = true; 
		} else {
			setToDefaultList(shoppingListModel);
		}
		
		return succeed; 
	}

	private static List readFile(String fileName) {
		DataFile read =
			  DataFile.createReader("8859_1");
		ArrayList<ShopItem> resultList = new ArrayList<ShopItem>(20);
		
		read.setDataFormat(new CSVFormat());
		// no column header
		read.containsHeader(false);
		try {
			File file = new File(fileName);
			read.open(file);
			for (DataRow row = read.next();
				row != null;
			    row = read.next()) {
				// retrieval using column header
			    String name = row.getString(0);				    
			    int qty = row.getInt(1);
			    String timeAdded = row.getString(2);
			    // use the retrieved data ...
			    ShopItem newItem = new ShopItem();
			    newItem.setChecked(false);
			    newItem.setQty(qty);
			    newItem.setName(name);
			    newItem.setTimeAdded(timeAdded);
			    resultList.add(newItem);
			}
		} catch (Exception e) {
			resultList = null;
		}
		finally {
			read.close();
		}
		return resultList;
	}
	
	/**
	 * write list to a csv file 
	 * @return number of rows have been written
	 */
	private static int writeFile(String fileName, List<ShopItem> listToSave){
		
		int rtv = 0;
		
		DataFile write =
			  DataFile.createWriter("8859_2", false);

		write.setDataFormat(new CSVFormat());
		// no column header
		write.containsHeader(true);
		
		DataRow newRow = null;
		
		try{
			write.open(new File(fileName)); 
			
			for(Iterator iter=listToSave.iterator(); iter.hasNext();){
				ShopItem tempItem = (ShopItem)iter.next();
				newRow = write.next();
				newRow.add(tempItem.getName());
				newRow.add(tempItem.getQty());
				newRow.add(tempItem.getTimeAdded());
				rtv++;
				newRow = null;
			}
			
			if (newRow != null){
				write.next(); //write the last row 
				rtv++;
			}
			
		}catch (Exception e) {
			rtv = -1;
		}finally{
			write.close();
		}
		
		return rtv; 
	}
	
	/** 
	 * save list to the file
	 * @return true if succeed
	 */
	public static boolean writeList(List<ShopItem> listToSave){
		
		return writeFile("MyList.csv", listToSave)> -1;
		
	}
	
	//get error message
	public String getErrMsg(){
		return strErrMsg;
	}
	
	//set to default list if could not read from file
	//	3 x Apples
	//  5 x Milk
	private static void setToDefaultList(ShoppingListModel shoppingListModel){
		shoppingListModel.clearList();
		
		ShopItem nItem = new ShopItem();
		
		nItem.setChecked(false);
		nItem.setQty(3);
		nItem.setName("Apples");
		nItem.setTimeAdded(CurrentTime.getCurrentTime());
		shoppingListModel.addShopItem(nItem);
		
		nItem = new ShopItem();
		nItem.setChecked(false);
		nItem.setQty(5);
		nItem.setName("Milk");
		nItem.setTimeAdded(CurrentTime.getCurrentTime());
		shoppingListModel.addShopItem(nItem);
	}

	public static List<ShopItem> getDefaultShopItems() {
		return readFile(".\\default-items.csv");
	}
	
}
