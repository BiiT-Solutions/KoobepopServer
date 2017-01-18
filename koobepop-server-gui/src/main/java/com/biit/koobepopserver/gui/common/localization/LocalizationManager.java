package com.biit.koobepopserver.gui.common.localization;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.biit.koobepopserver.logger.KoobepopLogger;
import com.biit.vaadin.localization.ILanguageCode;
import com.vaadin.server.Page;

@Scope("singleton")
@Component()
public class LocalizationManager {

	@Autowired
	private transient ApplicationContext applicationContext;

	private static Locale getLocale() {
		// if (UserSessionHandler.getCurrent().getUser() != null) {
		// return
		// StringUtils.parseLocaleString(UserSessionHandler.getCurrent().getUser().getLanguageId());
		// } else {
		if (Page.getCurrent() != null) {
			return Page.getCurrent().getWebBrowser().getLocale();
		} else {
			return null;
		}
		// }
	}

	public String translate(ILanguageCode code, String... args) {
		if (code == null) {
			return null;
		}
		return translate(code.getCode(), args);
	}

	public String translateCode(String code, String... args) {
		if (code == null) {
			return null;
		}
		return translate(code, args);
	}

	private String translationException(String code, String... args) {
		return applicationContext.getMessage(code, args, getLocale());
	}

	private String translate(String code, String... args) {
		try {
			return translationException(code, args);
		} catch (RuntimeException e) {
			KoobepopLogger.errorMessage(LocalizationManager.class.getName(), e);
			return "No translation.";
		}
	}
}
