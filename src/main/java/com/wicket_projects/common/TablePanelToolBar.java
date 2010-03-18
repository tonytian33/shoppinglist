package com.wicket_projects.common;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;

public abstract class TablePanelToolBar extends Panel {

	public TablePanelToolBar(String id) {
		super(id);

		add(new AjaxFallbackLink("deleteItemLink") {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				doDeleteSelected(target);
			}
		});
		
		add(new AjaxFallbackLink("clearLink") {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				doClearList(target);
			}
		});

		add(new AjaxFallbackLink("emailLink") {
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				// TODO Auto-generated method stub
			}
		});
	}

	public abstract void doClearList(AjaxRequestTarget target);
	public abstract void doDeleteSelected(AjaxRequestTarget target);
}
