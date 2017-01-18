package com.biit.koobepopserver.gui.converters;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class DateTimestampConverter implements Converter<Date, Timestamp> {
	private static final long serialVersionUID = 1653214479297462370L;

	@Override
	public Timestamp convertToModel(Date value, Class<? extends Timestamp> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value == null) {
			return null;
		}
		return new Timestamp(value.getTime());
	}

	@Override
	public Date convertToPresentation(Timestamp value, Class<? extends Date> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value == null) {
			return null;
		}
		return new Date(value.getTime());
	}

	@Override
	public Class<Timestamp> getModelType() {
		return Timestamp.class;
	}

	@Override
	public Class<Date> getPresentationType() {
		return Date.class;
	}

}
