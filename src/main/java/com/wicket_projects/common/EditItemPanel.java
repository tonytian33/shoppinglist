package com.wicket_projects.common;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.MinimumValidator;
import org.apache.wicket.validation.validator.NumberValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.apache.wicket.validation.validator.NumberValidator.RangeValidator;

import com.wicket_projects.shoppinglist.model.ShopItem;

public abstract class EditItemPanel extends Panel{

	public EditItemPanel(String id, IModel<ShopItem> shopItemModel) {
		
		super(id, shopItemModel);
		Form form = new Form("form");
		add(form);
		
		form.add( new FeedbackPanel("editFeedBack"));
		
		TextField txtName = new TextField("nameField", new PropertyModel(shopItemModel.getObject(),"name"));
		txtName.add(StringValidator.lengthBetween(1, 100));
		txtName.setRequired(true);
		form.add(txtName);
		
		TextField txtQty = new TextField("qtyField", new PropertyModel(shopItemModel.getObject(),"qty"));
		txtQty.add(new RangeValidator(1,99));
		txtQty.setRequired(true);
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
		return ((ShopItem)getDefaultModelObject()).isEditMode();
	}
	
	public abstract void onSave();
	public abstract void onCancel();
	
}
