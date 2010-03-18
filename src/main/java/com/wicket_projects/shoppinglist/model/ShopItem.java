package com.wicket_projects.shoppinglist.model;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ShopItem implements Serializable{
	
	private String name; 
	
	private int qty; 
	
	private boolean checked; 
	
	private String timeAdded;
	
	private boolean editMode = false;
	
	public ShopItem(){
		this.name = ""; 
		this.qty = 1; 
		this.checked = false; 
		this.timeAdded = currentTimeString();
	}
	
	public ShopItem(String name, int qty, boolean check, String timeAdded){
		this.name = name; 
		this.qty = qty; 
		this.checked = check; 
		this.timeAdded = timeAdded; 
	}
	
	public ShopItem(ShopItem shopItemToCopy) {
		this.name = shopItemToCopy.getName();
		this.qty = shopItemToCopy.getQty();
		this.checked = false; 
		this.timeAdded = shopItemToCopy.getTimeAdded();
	}

	public ShopItem(String name, int qty, boolean check) {
		this.name = name; 
		this.qty = qty; 
		this.checked = check; 
		this.timeAdded = currentTimeString();
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getQty() {
		return qty;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean getChecked(){
		return checked;
	}
	
	public boolean isChecked() {
		return checked;
	}
	public void setTimeAdded(String timeAdded) {
		this.timeAdded = timeAdded;
	}
	public String getTimeAdded() {
		return timeAdded;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public boolean isEditMode() {
		return editMode;
	} 
	
	private String currentTimeString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		return sdf.format(Calendar.getInstance().getTime());
	}
}
