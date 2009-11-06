package com.wicket_projects.common;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wicket_projects.shoppinglist.model.ShopItem;
import com.wicket_projects.shoppinglist.model.ShoppingListModel;

public class ShopItemTablePanel extends Panel{
	
	private ShoppingListModel shoppingList; 

	public ShopItemTablePanel(String id, final Model<ShoppingListModel> shoppingList) {
		super(id, shoppingList);
		setOutputMarkupId(true);
		this.shoppingList = (ShoppingListModel)shoppingList.getObject();
		
		add(new TablePanelToolBar("topToolBar"));
		
		ListView itemList = new ListView("itemList",shoppingList.getObject().getShopItems()){

			@Override
			protected void populateItem(ListItem item) {
				ItemInfoPanel itemInfoPanel = new ItemInfoPanel("itemInfo", item.getModel()){

					@Override
					public void deleteItemFromList(ShopItem itemToDelete,AjaxRequestTarget target) {
						// delete item 
						if(shoppingList.getObject().getShopItems().contains(itemToDelete)){
							shoppingList.getObject().getShopItems().remove(itemToDelete);
							// repaint the ShopItemTablePanel
							target.addComponent(this.getParent().getParent().getParent());
						}
					}

					@Override
					public void editItem(ShopItem itemToEdit,
							AjaxRequestTarget target) {
						// TODO Auto-generated method stub
						itemToEdit.setEditMode(true);
						target.addComponent(this.getParent().getParent().getParent());
						
					}
					
				};
				
				EditItemPanel editItemPanel = new EditItemPanel("editItemPanel",item.getModel()) {
					
					@Override
					public void onSave() {
						// TODO Auto-generated method stub
						((ShopItem)getDefaultModelObject()).setEditMode(false);
					}
					
					@Override
					public void onCancel() {
						// TODO Auto-generated method stub
						((ShopItem)getDefaultModelObject()).setEditMode(false);
						
					}
				};
				item.add(itemInfoPanel);
				item.add(editItemPanel);
			}
			
		};
		add(itemList);
	}

}
