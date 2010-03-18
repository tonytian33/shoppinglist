package com.wicket_projects.shoppinglist.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.wicket_projects.shoppinglist.model.ShoppingListModel;

public class ItemListManagerTest {

	@Before
	public void setUp() throws Exception {
	}  

	@Test
	public void testReadList() {
		ShoppingListModel shoppingListModel = new ShoppingListModel();
		ItemListManager.readList(shoppingListModel);
		assertFalse("expect list is not empty", shoppingListModel.getShopItems().isEmpty());
	}
}
