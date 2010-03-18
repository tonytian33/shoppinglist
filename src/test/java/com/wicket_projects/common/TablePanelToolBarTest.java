package com.wicket_projects.common;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.TestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.wicket_projects.HomePage;

public class TablePanelToolBarTest {

	private WicketTester tester; 
	
	@Before
	public void setUp() throws Exception {
		tester = new WicketTester();
		tester.startPanel(new TestPanelSource() {
			
			public Panel getTestPanel(String panelId) {
				return new TablePanelToolBar(panelId){

					@Override
					public void doClearList(AjaxRequestTarget target) {
						// do nothing
					}

					@Override
					public void doDeleteSelected(AjaxRequestTarget target) {
						// do nothing
					}
					
				};
			}
		});
	}
	
	@Test
	public void testBasticRender() {
		tester.assertComponent("panel:deleteItemLink", AjaxFallbackLink.class);
		tester.assertComponent("panel:clearLink", AjaxFallbackLink.class);
		tester.assertComponent("panel:emailLink", AjaxFallbackLink.class);
	}

}
