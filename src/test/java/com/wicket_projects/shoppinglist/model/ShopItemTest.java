package com.wicket_projects.shoppinglist.model;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShopItemTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testBasicShopItem(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		String timeAdded = sdf.format(Calendar.getInstance().getTime());
		ShopItem shopItem = new ShopItem("milk",2,false,timeAdded);
		
		Assert.assertEquals("milk", shopItem.getName());
		Assert.assertEquals(2, shopItem.getQty());
		Assert.assertFalse("expect not checked",shopItem.isChecked());
		Assert.assertEquals(timeAdded, shopItem.getTimeAdded());
		
		shopItem.setChecked(true);
		Assert.assertTrue(shopItem.isChecked());
	}

}
