package com.biit.koobepopserver.gui.language;

import com.biit.vaadin.localization.ILanguageCode;
import com.vaadin.ui.UI;

public class LanguageCode implements ILanguageCode{
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
		if(( UI.getCurrent()) != null){
			//return  UI.getCurrent().translate(this, args);
		}
		return "";
	}

}
