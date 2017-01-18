package com.biit.koobepopserver.gui;

import org.springframework.beans.factory.annotation.Autowired;

import com.biit.koobepopserver.gui.common.localization.LocalizationManager;
import com.biit.koobepopserver.gui.webpages.KoobepopLogin;
import com.biit.vaadin.BiitNavigator;
import com.biit.vaadin.localization.ILanguageCode;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

@PreserveOnRefresh
@Push
@Theme("biitTheme")
@Widgetset("com.biit.koobepopserver.gui.AppWidgetSet")
@SpringUI()
public class KoobepopUi extends UI {
	private static final long serialVersionUID = -4146374452119792920L;
	
	private BiitNavigator navigator;
	@Autowired
	private SpringViewProvider viewProvider;
	@Autowired
	private LocalizationManager localization;

	@Override
	protected void init(VaadinRequest request) {
		getPage().setTitle("");
		defineNavigation();
		setErrorHandler(new DefaultErrorHandler());
		navigator.setState(KoobepopLogin.NAME);
	}

	/**
	 * Initialize Spring injected navigator with the views of application.
	 */
	private void defineNavigation() {
		navigator = new BiitNavigator(this, this);
		navigator.addProvider(viewProvider);
		setChangeViewEvents();
	}

	/**
	 * Set change view events to store information of current view
	 */
	private void setChangeViewEvents() {
		navigator.addViewChangeListener(new ViewChangeListener() {
			private static final long serialVersionUID = -668206181478591694L;

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
//				if (event.getViewName().isEmpty()) {
//					// If an access to empty url is done go to main page or
//					// login screen
//					if (UserSessionHandler.getCurrent().getUser() == null) {
//						navigator.navigateTo(UsmoLogin.NAME);
//					} else {
//						UserSessionHandler.navigateToUserMainView();
//					}
//					return false;
//				}
				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {
//				if (UserSessionHandler.getCurrent().getUser() != null) {
//					UsmoLogger.info(this.getClass().getName(), "User '" + UserSessionHandler.getCurrent().getUser().getEmailAddress()
//							+ "' has changed view to '" + event.getNewView() + "'.");
//				}
			}
		});
	}
	public String translate(ILanguageCode languageCode, String... args) {
		return localization.translate(languageCode, args);
	}
}
