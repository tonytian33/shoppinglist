package com.wicket_projects.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.resolver.AutoComponentResolver;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.string.Strings;
import org.apache.wicket.validation.validator.MaximumValidator;
import org.apache.wicket.validation.validator.MinimumValidator;

import com.wicket_projects.HomePage;
import com.wicket_projects.shoppinglist.model.ShopItem;

public abstract class AddItemPanel extends Panel{
	
	private static final int NO_PROMPT = 100;
	private List<String> promptItemNames ; 
	
	public AddItemPanel(String id, IModel<ShopItem> shopItemModel) {
		this(id, shopItemModel, new ArrayList<String>());
	}
	
	public AddItemPanel(String id, IModel<ShopItem> shopItemModel, final List<String> prompt){
		super(id, shopItemModel);

		this.promptItemNames = prompt;

		Form form = new Form("form");
		add(form);
		TextField txtName = new TextField("nameField", new PropertyModel(shopItemModel.getObject(),"name"));
		txtName.setRequired(true);
		
//		form.add(txtName);
		
		AutoCompleteTextField txtAutoName = new AutoCompleteTextField<String>("autoNameField",new PropertyModel(shopItemModel.getObject(),"name")) {

			@Override
			protected Iterator<String> getChoices(String input) {
				List<String> choices = new ArrayList<String>(NO_PROMPT);

				if (Strings.isEmpty(input))
                {
                    List<String> emptyList = Collections.emptyList();
                    return emptyList.iterator();
                }
				
				for (String itemName : promptItemNames){
					if (itemName.toUpperCase().startsWith(input.toUpperCase())){
						choices.add(itemName);
					}
					
					if (choices.size()==NO_PROMPT){
						break;
					}
				}
				return choices.iterator();
			}
		};
		txtAutoName.setRequired(true);
		form.add(txtAutoName);
		
		TextField txtQty = new TextField("qtyField", new PropertyModel(shopItemModel.getObject(),"qty"));
		txtQty.setRequired(true);
		txtQty.add(new  MaximumValidator<Integer>(99));
		txtQty.add(new MinimumValidator<Integer>(1));
		form.add(txtQty);
		SubmitLink addLink = new SubmitLink("addLink"){
			@Override
			public void onSubmit() {
				super.onSubmit();
				onAdd();
			}
		};
		form.add(addLink);
	}
	
	
	public abstract void onAdd();
}
