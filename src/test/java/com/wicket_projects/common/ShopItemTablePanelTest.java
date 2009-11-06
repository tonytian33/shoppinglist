package com.wicket_projects.common;



import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.wicket_projects.HomePage;
import com.wicket_projects.shoppinglist.model.ShoppingListModel;

public class ShopItemTablePanelTest {
	
	private WicketTester tester;
	
	@Before
	public void setUp() throws Exception {
		tester = new WicketTester();
		tester.startPanel(new TestPanelSource() {
			
			public Panel getTestPanel(String panelId) {
				// TODO Auto-generated method stub
				return new ShopItemTablePanel(panelId, new Model(new ShoppingListModel()));
			}
		});
	}
	
	@Test
	public void testBasicRender() {
		tester.assertComponent("panel", Panel.class);
		tester.assertComponent("panel:topToolBar", Panel.class);
	}

}
