package com.wicket_projects.common;

import junit.framework.Assert;

import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.wicket_projects.shoppinglist.model.ShopItem;

public class EditItemPanelTest {

	private WicketTester tester;
	private ShopItem shopItem;

	@Before
	public void setUp() throws Exception {
		tester = new WicketTester();
		shopItem = new ShopItem();
		tester.startPanel(new TestPanelSource(){
			
			public Panel getTestPanel(String panelId) {
				return new EditItemPanel(panelId, new Model(shopItem)){

					@Override
					public void onCancel() {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSave() {
						// TODO Auto-generated method stub
						
					}
					
				};
			}
			
		});
	}
	
	@Test 
	public void testBasicRender(){
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
}
