package com.wicket_projects.common;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wicket_projects.HomePage;
import com.wicket_projects.shoppinglist.model.ShopItem;
import com.wicket_projects.shoppinglist.model.ShoppingListModel;

public class ShopItemTablePanelTest {
	
	private static WicketTester tester;
	private ShoppingListModel shoppingList = new ShoppingListModel();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tester = new WicketTester();
	}
	
	@Before
	public void setUp() throws Exception {
		shoppingList.clearList();
		shoppingList.addShopItem(new ShopItem("item 1",2,false));
		shoppingList.addShopItem(new ShopItem("item 2",1,false));
		shoppingList.addShopItem(new ShopItem("item 3",3,false));
	}
	
	@Test
	public void testBasicRender() {
		tester.startPanel(new TestPanelSource() {
			public Panel getTestPanel(String panelId) {
				return new ShopItemTablePanel(panelId, new Model(shoppingList));
			}
		});
		
		tester.assertComponent("panel", Panel.class);
		tester.assertComponent("panel:topToolBar", Panel.class);
		tester.assertComponent("panel:itemList", ListView.class);
	}
	
	@Test 
	public void testClearLink() {
		tester.startPanel(new TestPanelSource() {
			public Panel getTestPanel(String panelId) {
				return new ShopItemTablePanel(panelId, new Model(shoppingList));
			}
		});
		
		assertFalse(shoppingList.getShopItems().isEmpty());
		tester.clickLink("panel:topToolBar:clearLink", true);
		assertTrue(shoppingList.getShopItems().isEmpty());
	}
	
	@Test
	public void testDeleteSelected() {
		shoppingList.getShopItems().get(1).setChecked(true);
		
		tester.startPanel(new TestPanelSource() {
			public Panel getTestPanel(String panelId) {
				return new ShopItemTablePanel(panelId, new Model(shoppingList));
			}
		});
		
		assertEquals(3,shoppingList.getShopItems().size());
		tester.clickLink("panel:topToolBar:deleteItemLink", true);
		assertEquals("expect only 2 items left.",2,shoppingList.getShopItems().size());
	}
	
	@Test
	public void testOperationMenuDelete() {
		tester.startPanel(new TestPanelSource() {
			public Panel getTestPanel(String panelId) {
				return new ShopItemTablePanel(panelId, new Model(shoppingList));
			}
		});
		
		assertEquals(3,shoppingList.getShopItems().size());
		tester.clickLink("panel:itemList:1:itemInfo:operationMenu:deleteItem", true);
		assertEquals("expect only 2 items left.",2,shoppingList.getShopItems().size());
	}
	
	@Test 
	public void testEditLink() {
		tester.startPanel(new TestPanelSource() {
			public Panel getTestPanel(String panelId) {
				return new ShopItemTablePanel(panelId, new Model(shoppingList));
			}
		});
		
		tester.assertVisible("panel:itemList:1:itemInfo:operationMenu:editItem");
		tester.assertInvisible("panel:itemList:1:editItemPanel");
		
		tester.clickLink("panel:itemList:1:itemInfo:operationMenu:editItem", true);
		
		tester.assertInvisible("panel:itemList:1:itemInfo:operationMenu:editItem");
		tester.assertVisible("panel:itemList:1:editItemPanel");
	}

}
