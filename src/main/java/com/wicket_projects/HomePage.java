package com.wicket_projects;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.wicket_projects.common.AddItemPanel;
import com.wicket_projects.common.ShopItemTablePanel;
import com.wicket_projects.shoppinglist.model.ShopItem;
import com.wicket_projects.shoppinglist.model.ShoppingListModel;
import com.wicket_projects.shoppinglist.util.ItemListManager;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	private ShoppingListModel shoppingListModel;
	
	private List<ShopItem> defaultShopItems;
	
	private List<String> promptList; 

	// TODO Add any page properties or variables here

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {
    	
    	shoppingListModel = new ShoppingListModel();
    	ItemListManager.readList(shoppingListModel);
    	initPromptList();

        // Add the simplest type of label
        add(new Label("message", "If you see this message wicket is properly configured and running"));
        add(new ShopItemTablePanel("tablePanel", new Model<ShoppingListModel>(shoppingListModel)));
        add(new AddItemPanel("addItemPanel",new Model<ShopItem>(new ShopItem()),promptList ){

			@Override
			public void onAdd() {
				// add item to the list 
				ShopItem shopItemToAdd = new ShopItem((ShopItem)getDefaultModelObject());
				shoppingListModel.addShopItem(shopItemToAdd);
				new ItemListManager().writeList(shoppingListModel.getShopItems());
				((ShopItem)getDefaultModelObject()).setName("");
				((ShopItem)getDefaultModelObject()).setQty(1);
			}
        	
        });
    }

	private void initPromptList() {
		promptList = new ArrayList<String>();
		for (ShopItem item : getDefaultShopItems()){
			promptList.add(item.getName());
		}
	}

	public void setDefaultShopItems(List<ShopItem> defaultShopItems) {
		this.defaultShopItems = defaultShopItems;
	}

	public List<ShopItem> getDefaultShopItems() {
		if (defaultShopItems == null){
			List list = ItemListManager.getDefaultShopItems();
			setDefaultShopItems(list!=null?list:new ArrayList<ShopItem>());
		}
		
		return defaultShopItems;
	}

	public void setPromptList(List<String> promptList) {
		this.promptList = promptList;
	}

	public List<String> getPromptList() {
		return promptList;
	}
}
