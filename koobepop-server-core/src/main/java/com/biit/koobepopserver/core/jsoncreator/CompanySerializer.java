package com.biit.koobepopserver.core.jsoncreator;

import java.lang.reflect.Type;

import com.biit.koobepopserver.persistence.entity.Company;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CompanySerializer  implements JsonSerializer<Company>{

	@Override
	public JsonElement serialize(Company src, Type typeOfSrc, JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();

		jsonObject.add("name", context.serialize(src.getName()));
		jsonObject.add("country", context.serialize(src.getCountry()));
		jsonObject.add("contacts", context.serialize(src.getContacts()));		
		return jsonObject;
	} 
}
