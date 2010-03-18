package com.wicket_projects.common;

import java.util.ArrayList;

import junit.framework.Assert;

import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wicket_projects.shoppinglist.model.ShopItem;

public class EditItemPanelTest {

	private WicketTester tester;
	private ShopItem shopItem;
	
	@Before
	public void setUp() throws Exception {
		shopItem = new ShopItem("item 1",2,false);
		shopItem.setEditMode(true);
		tester = new WicketTester();
		
		TestPanelSource testPanelSource = new TestPanelSource(){
			public Panel getTestPanel(String panelId) {
				return new EditItemPanel(panelId, new Model(shopItem)){
					@Override
					public void onCancel() {
						// do nothing
					}
					
					@Override
					public void onSave() {
						// do nothing
					}
				};
			}
		};
		tester.startPanel(testPanelSource);
	}
	
	@Test 
	public void testBasicRender(){
		tester.assertVisible("panel:form:nameField");
		tester.assertComponent("panel:form:nameField",TextField.class);
		tester.assertComponent("panel:form:qtyField",TextField.class);
		tester.assertComponent("panel:form:saveLink",SubmitLink.class);
		tester.assertComponent("panel:form:cancelLink",SubmitLink.class);
		
	}
	
	@Test
	public void testUpdateModel(){
		FormTester formTester = tester.newFormTester("panel:form", false);
		formTester.setValue("nameField", "bread");
		formTester.setValue("qtyField", "3");
		formTester.submit();
		
		Assert.assertEquals("bread",shopItem.getName() );
		Assert.assertEquals(3,shopItem.getQty() );
	}
	
	@Test
	public void testNameFieldValidation(){
		FormTester formTester = tester.newFormTester("panel:form", false);
		formTester.setValue("nameField", "");
		formTester.setValue("qtyField", "3");
		formTester.submit();
		
		String[] errArray = {"Field 'nameField' is required."};
		tester.assertErrorMessages(errArray);
	}
	
	@Test
	public void testQtyFieldRequired(){
		FormTester formTester = tester.newFormTester("panel:form", false);
		formTester.setValue("nameField", "Item 1");
		formTester.setValue("qtyField", "");
		formTester.submit();
		
		String[] errArray = {"Field 'qtyField' is required."};
		tester.assertErrorMessages(errArray);
	}
	
	@Test
	public void testQtyFieldLowerBound(){
		FormTester formTester = tester.newFormTester("panel:form", false);
		formTester.setValue("nameField", "Item 1");
		formTester.setValue("qtyField", "0");
		formTester.submit();
		String[] errArray = {"0 is not between 1 and 99."};
		tester.assertErrorMessages(errArray);
		
	}
	
	@Test
	public void testQtyFieldUpperBound(){
		FormTester formTester = tester.newFormTester("panel:form", false);
		formTester.setValue("nameField", "Item 1");
		formTester.setValue("qtyField", "100");
		formTester.submit();
		String[] errArray = {"100 is not between 1 and 99."};
		tester.assertErrorMessages(errArray);
	}
	
	@Test
	public void testQtyFieldValid(){
		FormTester formTester = tester.newFormTester("panel:form", false);
		formTester.setValue("nameField", "Item 1");
		formTester.setValue("qtyField", "5");
		formTester.submit();
		tester.assertNoErrorMessage();
	}
}
