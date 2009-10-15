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
	
	public ShopItem(){
		this.name = ""; 
		this.qty = 1; 
		this.checked = false; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		this.timeAdded = sdf.format(Calendar.getInstance().getTime());; 
	}
	
	public ShopItem(String name, int qty, boolean checek, String timeAdded){
		this.name = name; 
		this.qty = qty; 
		this.checked = checked; 
		this.timeAdded = timeAdded; 
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
	public boolean isChecked() {
		return checked;
	}
	public void setTimeAdded(String timeAdded) {
		this.timeAdded = timeAdded;
	}
	public String getTimeAdded() {
		return timeAdded;
	} 
}
