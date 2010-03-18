package com.wicket_projects.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wicket_projects.shoppinglist.model.ShopItem;
import com.wicket_projects.shoppinglist.model.ShoppingListModel;
import com.wicket_projects.shoppinglist.util.ItemListManager;

public class ShopItemTablePanel extends Panel{
	
	private ShoppingListModel shoppingList; 

	public ShopItemTablePanel(String id, final Model<ShoppingListModel> shoppingList) {
		super(id, shoppingList);
		setOutputMarkupId(true);
		this.shoppingList = (ShoppingListModel)shoppingList.getObject();
		
		add(new TablePanelToolBar("topToolBar"){

			@Override
			public void doClearList(AjaxRequestTarget target) {
				((ShoppingListModel)shoppingList.getObject()).getShopItems().clear();
				target.addComponent(this.getParent());
			}

			@Override
			public void doDeleteSelected(AjaxRequestTarget target) {
				List<ShopItem> shopItems = ((ShoppingListModel)shoppingList.getObject()).getShopItems();
				Iterator<ShopItem> it = shopItems.iterator();
				List<ShopItem> itemsToDelete = new ArrayList<ShopItem>();
				while(it.hasNext()){
					ShopItem shopItem = it.next();
					if (shopItem.isChecked())
					{
						itemsToDelete.add(shopItem);
					}
				}
				shopItems.removeAll(itemsToDelete);
				new ItemListManager().writeList(shopItems);
				target.addComponent(this.getParent());
			}
			
		});
		
		ListView itemList = new ListView("itemList",shoppingList.getObject().getShopItems()){

			@Override
			protected void populateItem(ListItem item) {
				ItemInfoPanel itemInfoPanel = new ItemInfoPanel("itemInfo", item.getModel()){

					@Override
					public void deleteItemFromList(ShopItem itemToDelete,AjaxRequestTarget target) {
						// delete item 
						if(shoppingList.getObject().getShopItems().contains(itemToDelete)){
							shoppingList.getObject().getShopItems().remove(itemToDelete);
							// save list
							new ItemListManager().writeList(shoppingList.getObject().getShopItems());
							// repaint the ShopItemTablePanel
							target.addComponent(this.getParent().getParent().getParent());
						}
					}

					@Override
					public void editItem(ShopItem itemToEdit,
							AjaxRequestTarget target) {
						itemToEdit.setEditMode(true);
						target.addComponent(this.getParent().getParent().getParent());
						
					}
					
				};
				
				EditItemPanel editItemPanel = new EditItemPanel("editItemPanel",item.getModel()) {
					
					@Override
					public void onSave() {
						new ItemListManager().writeList(((ShoppingListModel)shoppingList.getObject()).getShopItems());
						((ShopItem)getDefaultModelObject()).setEditMode(false);
					}
					
					@Override
					public void onCancel() {
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
