package com.wicket_projects.common;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import com.wicket_projects.shoppinglist.model.ShopItem;

public abstract class operationMenu extends Panel{

	public operationMenu(String id, IModel shopItem) {
		super(id,shopItem);
		add(new AjaxFallbackLink("deleteItem"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				onDeleteItem(target);
			}
			
		});
		
		add(new AjaxFallbackLink("editItem"){
			
			@Override
			public void onClick(AjaxRequestTarget target) {
				onEditItem(target);
			}
		});
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return !((ShopItem)this.getDefaultModelObject()).isEditMode();
	}

	public abstract void onDeleteItem(AjaxRequestTarget target);
	public abstract void onEditItem(AjaxRequestTarget target);
}
