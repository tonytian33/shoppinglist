package com.wicket_projects.common;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;

public class TablePanelToolBar extends Panel {

	public TablePanelToolBar(String id) {
		super(id);
		add(new AjaxFallbackLink("addItemLink") {

			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
			}
		});

		add(new AjaxFallbackLink("deleteItemLink") {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
			}
		});
		
		add(new AjaxFallbackLink("clearLink") {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
			}
		});

		add(new AjaxFallbackLink("emailLink") {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
			}
		});
	}

}
