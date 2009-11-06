package com.wicket_projects.common;


import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.wicket_projects.shoppinglist.model.ShopItem;

public class operationMenuTest {

	private WicketTester tester;
	private boolean editExcuted; 
	private boolean deleteExcuted; 
	private IModel<ShopItem> shopItem;

	@Before
	public void setUp() throws Exception {
		editExcuted = false ;  
		deleteExcuted = false ; 
		shopItem = new Model(new ShopItem());
		tester = new WicketTester();
		tester.startPanel(new TestPanelSource() {
			public Panel getTestPanel(String panelId) {
				return new operationMenu(panelId, shopItem) {
					
					
					@Override
					public void onDeleteItem(AjaxRequestTarget target) {
						deleteExcuted = true ; 
						
					}

					@Override
					public void onEditItem(AjaxRequestTarget target) {
						editExcuted = true; 
						
					}
				};
			}
		});
	}
	
	@Test 
	public void testBasicRender(){
		tester.assertComponent("panel:deleteItem", AjaxFallbackLink.class);
		tester.assertComponent("panel:editItem", AjaxFallbackLink.class);
	}
	
	@Test
	public void testDeleteItemEventHandler(){
		tester.executeAjaxEvent("panel:deleteItem", "onclick");
		assertTrue(deleteExcuted);
	}
	
	@Test
	public void testEditItemEventHandler(){
		tester.executeAjaxEvent("panel:editItem", "onclick");
		assertTrue(editExcuted);
	}
}
