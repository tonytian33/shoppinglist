package com.wicket_projects.shoppinglist.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.infomata.data.CSVFormat;
import com.infomata.data.DataFile;
import com.infomata.data.DataRow;
import com.wicket_projects.shoppinglist.model.ShopItem;
import com.wicket_projects.shoppinglist.model.ShoppingListModel;


public class ItemListManager {
	
	//constructor
	public ItemListManager(){
	}
	
	//read list from file 
	public static boolean readList(ShoppingListModel shoppingListModel){
		boolean rtv = true; 
		
		DataFile read =
			  DataFile.createReader("8859_1");

			read.setDataFormat(new CSVFormat());
			// no column header
			read.containsHeader(false);
			try {
				  read.open(new File("MyList.csv"));

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
				    shoppingListModel.addShopItem(newItem);
				  }
				  
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
	
					setToDefaultList(shoppingListModel);
					rtv = false;
				}

				finally {
				  read.close();
				}
		
		return rtv; 
	}
	
	//save list to the file
//	public boolean writeList(){
//		boolean rtv = true;
//		
//		DataFile write =
//			  DataFile.createWriter("8859_2", false);
//
//		write.setDataFormat(new CSVFormat());
//		// no column header
//		write.containsHeader(true);
//		
//		DataRow newRow = null;
//		
//		try{
//			write.open(new File("MyList.csv")); 
//			
//			for(Iterator iter=shoppingListModel.iterator(); iter.hasNext();){
//				ShopItem tempItem = (ShopItem)iter.next();
//				newRow = write.next();
//				newRow.add(tempItem.getName());
//				newRow.add(tempItem.getQty());
//				newRow = null;
//			}
//			
//			if (newRow != null){
//				write.next(); //write the last row 
//			}
//			
//		}catch (Exception e) {
//			this.strErrMsg = e.getMessage();
//			rtv = false;
//			this.strErrMsg = "Can not write list from the harddrive.";
//		}finally{
//			write.close();
//		}
//		
//		return rtv; 
//	}
//	
//	//get error message
//	public String getErrMsg(){
//		
//		return strErrMsg;
//	}
	
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

}
