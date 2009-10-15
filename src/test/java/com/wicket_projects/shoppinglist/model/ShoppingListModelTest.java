package com.wicket_projects.shoppinglist.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ShoppingListModelTest {
	
	private ShoppingListModel shoppingListModel;
	

	@Before
	public void setUp() throws Exception {
		shoppingListModel = new ShoppingListModel(); 
	}
	
	@Test 
	public void testSetOwner(){

		shoppingListModel.addShopItem(new ShopItem());
		shoppingListModel.setOwner("Tony");
		assertEquals("expect owner is set to Tony", "Tony", shoppingListModel.getOwner());
	}
	
	@Test
	public void testAddShopItem(){
		shoppingListModel =  new ShoppingListModel(); 
		shoppingListModel.addShopItem(new ShopItem());
		assertFalse("expect shoppingListModel is not empty", shoppingListModel.getShopItems().isEmpty());
	}
	
	@Test
	public void testRemoveShopItem(){
		shoppingListModel =  new ShoppingListModel(); 
		ShopItem item = new ShopItem();
		shoppingListModel.addShopItem(item);
		shoppingListModel.removeShopItem(item);
		assertTrue("expect shoppingListModel is empty", shoppingListModel.getShopItems().isEmpty());
	}
	
	@Test
	public void testClearShopItem(){
		shoppingListModel =  new ShoppingListModel(); 
		ShopItem item = new ShopItem();
		shoppingListModel.addShopItem(item);
		shoppingListModel.clearList();
		assertTrue("expect shoppingListModel is empty", shoppingListModel.getShopItems().isEmpty());
	}
	
}
