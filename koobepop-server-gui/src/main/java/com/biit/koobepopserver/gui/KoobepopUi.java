package com.biit.koobepopserver.gui;

import com.biit.koobepopserver.gui.webpages.KoobepopLogin;
import com.biit.vaadin.BiitNavigator;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;


@PreserveOnRefresh
@Push
//@Theme("koobepopTheme")
@Widgetset("com.biit.koobepopserver.gui.AppWidgetSet")
@SpringUI()
public class KoobepopUi extends UI{
	private static final long serialVersionUID = -4146374452119792920L;
	private BiitNavigator navigator;
	
	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("");
		defineNavigation();
		navigator.setState(KoobepopLogin.NAME);
	}
	/**
	 * Initialize Spring injected navigator with the views of application.
	 */
	private void defineNavigation() {
		navigator = new BiitNavigator(this, this);
		}

}
