package com.wicket_projects.common;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.wicket_projects.shoppinglist.model.ShopItem;

public class ItemInfoPanelTest {
	
	private WicketTester tester;
	
	@Before
	public void setUp() throws Exception {
		tester = new WicketTester();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH-mm-ss");
		String timeAdded = sdf.format(Calendar.getInstance().getTime());
		final ShopItem shopItem = new ShopItem("milk",2,false,timeAdded);
		final Model model = new Model(shopItem);
		tester.startPanel(new TestPanelSource() {
			public Panel getTestPanel(String panelId) {
				return new ItemInfoPanel(panelId,model){

					@Override
					public void deleteItemFromList(ShopItem itemToDelete,
							AjaxRequestTarget target) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void editItem(ShopItem itemToEdit,
							AjaxRequestTarget target) {
						// TODO Auto-generated method stub
						
					}
					
				};
			}
		});
	}
	
	@Test 
	public void testBasicRender(){
		tester.assertComponent("panel:itemName", Label.class);
		tester.assertLabel("panel:itemName", "milk");
		tester.assertComponent("panel:qty", Label.class);
		tester.assertLabel("panel:qty", "2");
		tester.assertComponent("panel:selected", AjaxCheckBox.class);
	}
	
	@Test 
	public void testCheckBoxUpdateModel(){
		tester.assertModelValue("panel:selected", new Boolean(false));
		tester.executeAjaxEvent("panel:selected", "onclick" );
		tester.assertModelValue("panel:selected", new Boolean(true));
	}
	
}
