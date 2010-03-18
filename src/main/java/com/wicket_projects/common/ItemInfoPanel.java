package com.wicket_projects.common;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.string.Strings;

import com.wicket_projects.shoppinglist.model.ShopItem;
import com.wicket_projects.shoppinglist.model.ShoppingListModel;
import com.wicket_projects.shoppinglist.util.ItemListManager;

public abstract class ItemInfoPanel extends Panel {
	
	private ShopItem shopItem;
	
	public ItemInfoPanel(String id, final IModel<ShopItem> model) {
		super(id, model);
		this.shopItem = model.getObject();
		add(new Label("itemName", new PropertyModel(model.getObject(), "name")));
		add(new Label("qty", new PropertyModel(model.getObject(), "qty")));
		final AjaxCheckBox selectItem = new AjaxCheckBox("selected", new PropertyModel(model.getObject(), "checked")) {
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
			}
		};
		
		selectItem.add(new AjaxEventBehavior("onclick") {
			
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				model.getObject().setChecked(!model.getObject().getChecked());	
			}
		});
		add(selectItem);
		
		add(new operationMenu("operationMenu", model) {
			
			@Override
			public void onEditItem(AjaxRequestTarget target) {
				editItem(shopItem, target);
			}
			
			@Override
			public void onDeleteItem(AjaxRequestTarget target) {
				// call hook method
				deleteItemFromList(shopItem, target);
			}
		});
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return !shopItem.isEditMode();
	}
	
	public abstract void deleteItemFromList(ShopItem itemToDelete,AjaxRequestTarget target);
	public abstract void editItem(ShopItem itemToEdit, AjaxRequestTarget target);

}
