package com.wicket_projects.common;



import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.sun.java.swing.plaf.nimbus.FormattedTextFieldPainter;
import com.wicket_projects.shoppinglist.model.ShopItem;

public class AddItemPanelTest {

	private WicketTester tester;
	private ShopItem shopItem;

	@Before
	public void setUp() throws Exception {
		tester = new WicketTester();
		shopItem = new ShopItem();
		tester.startPanel(new TestPanelSource(){
			
			public Panel getTestPanel(String panelId) {
				return new AddItemPanel(panelId, new Model(shopItem)){

					@Override
					public void onAdd() {
						// do nothing
					}
				};
			}
		});
	}
	
	@Test 
	public void testBasicRender(){
		tester.assertComponent("panel:form:autoNameField", TextField.class);
		tester.assertComponent("panel:form:qtyField", TextField.class);
		tester.assertComponent("panel:form:addLink", SubmitLink.class);
	}
	
	@Test 
	public void testNameFieldRequired(){
		FormTester formTester = tester.newFormTester("panel:form");
		formTester.setValue("autoNameField", "");
		formTester.setValue("qtyField", "1");
		formTester.submit();
		tester.assertErrorMessages(new String[] {"Field 'autoNameField' is required."});
	}

	@Test 
	public void testQtyFieldRequired(){
		FormTester formTester = tester.newFormTester("panel:form");
		formTester.setValue("autoNameField", "item1");
		formTester.setValue("qtyField", "");
		formTester.submit();
		tester.assertErrorMessages(new String[] {"Field 'qtyField' is required."});
	}
	
	@Test 
	public void testQtyFieldBelowMinimum(){
		FormTester formTester = tester.newFormTester("panel:form");
		formTester.setValue("autoNameField", "item1");
		formTester.setValue("qtyField", "0");
		formTester.submit();
		tester.assertErrorMessages(new String[] {"'0' is smaller than the minimum of 1."});
	}
	
	@Test 
	public void testQtyFieldAboveMaximum(){
		FormTester formTester = tester.newFormTester("panel:form");
		formTester.setValue("autoNameField", "item1");
		formTester.setValue("qtyField", "100");
		formTester.submit();
		tester.assertErrorMessages(new String[] {"'100' is larger than the maximum of 99."});
	}
}
