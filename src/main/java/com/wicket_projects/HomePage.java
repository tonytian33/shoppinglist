package com.wicket_projects;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import com.wicket_projects.shoppinglist.model.ShoppingListModel;
import com.wicket_projects.shoppinglist.util.ItemListManager;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	private ShoppingListModel shoppingListModel;

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

        // Add the simplest type of label
        add(new Label("message", "If you see this message wicket is properly configured and running"));

        // TODO Add your page's components here
    }
}
