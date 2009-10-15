package com.wicket_projects.shoppinglist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListModel implements Serializable {
	
	private String owner; 
	
	private List<ShopItem> shopItems;
	
	public ShoppingListModel(){
		this.owner = "";
		this.shopItems = new ArrayList<ShopItem>();
	}
	
	public ShoppingListModel(String owner, List<ShopItem> shopItems){
		this.owner = owner;
		this.shopItems = shopItems;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwner() {
		return owner;
	}
	public void setShopItems(List<ShopItem> shopItems) {
		this.shopItems = shopItems;
	}
	public List<ShopItem> getShopItems() {
		return shopItems;
	}
	
	public void addShopItem(ShopItem itemToAdd){
		shopItems.add(itemToAdd);
	}
	
	public void removeShopItem(ShopItem itemToRemove){
		shopItems.remove(itemToRemove);
	}
	
	public void clearList(){
		shopItems.clear();
	}
}
