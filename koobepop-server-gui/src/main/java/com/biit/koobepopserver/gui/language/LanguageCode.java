package com.biit.koobepopserver.gui.language;

import com.biit.koobepopserver.gui.KoobepopUi;
import com.biit.vaadin.localization.ILanguageCode;
import com.vaadin.ui.UI;

public enum LanguageCode implements ILanguageCode{
	
	
	//LOGIN VIEW
	LOGIN_CAPTION_SIGN_IN("login.caption.signin"),
	LOGIN_CAPTION_EMAIL("login.caption.email"),
	LOGIN_CAPTION_PASSWORD("login.caption.password"),
	LOGIN_ERROR_EMAIL("login.error.email"),
	LOGIN_ERROR_PASSWORD("login.error.password"),
	LOGIN_SCREEN_SYSTEM_NAME("login.screen.system.name"),;
	
	private String value;
	
	LanguageCode(String value){
		this.value = value;
	}

	@Override
	public String getCode() {
		return value;
	}

	@Override
	public String translation(String ... args) {
		if(((KoobepopUi) UI.getCurrent()) != null){
			return  ((KoobepopUi)UI.getCurrent()).translate(this, args);
		}
		return "";
	}

}
