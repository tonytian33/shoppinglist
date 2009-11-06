package com.wicket_projects.common;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import com.wicket_projects.shoppinglist.model.ShopItem;

public abstract class EditItemPanel extends Panel{

	public EditItemPanel(String id, IModel<ShopItem> shopItemModel) {
		
		super(id, shopItemModel);
		Form form = new Form("form");
		add(form);
		TextField txtName = new TextField("nameField", new PropertyModel(shopItemModel.getObject(),"name"));
		form.add(txtName);
		TextField txtQty = new TextField("qtyField", new PropertyModel(shopItemModel.getObject(),"qty"));
		form.add(txtQty);
		SubmitLink saveLink = new SubmitLink("saveLink"){
			@Override
			public void onSubmit() {
				super.onSubmit();
				onSave();
			}
		};
		form.add(saveLink);
		
		SubmitLink cancelLink = new SubmitLink("cancelLink"){
			@Override
			public void onSubmit() {
				super.onSubmit();
				onCancel();
			}
		};
		form.add(cancelLink);
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return ((ShopItem)getDefaultModelObject()).isEditMode();
	}
	
	public abstract void onSave();
	public abstract void onCancel();
	
}
