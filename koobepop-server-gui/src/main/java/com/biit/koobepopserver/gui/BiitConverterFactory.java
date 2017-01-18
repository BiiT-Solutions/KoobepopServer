package com.biit.koobepopserver.gui;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.biit.koobepopserver.gui.converters.DateTimestampConverter;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.DefaultConverterFactory;

/**
 * Biit converter factory must be registered when the VaadinSession is
 * initializated. This class extends the default converter factory and
 * implements conversors that are not implemented default by vaadin and can't be
 * registered in any other way. Important note, all the conversors registered
 * must be thread-safe.
 * 
 * @author Jorge Izquierdo
 *
 */
public class BiitConverterFactory extends DefaultConverterFactory {
	private static final long serialVersionUID = 3091363909426053833L;
	private final Map<Class<?>, Map<Class<?>, Converter<?, ?>>> registry;

	public BiitConverterFactory() {
		super();
		registry = new HashMap<>();

		addConverter(Date.class, Timestamp.class, new DateTimestampConverter());
	}

	public <PRESENTATION, MODEL> void addConverter(Class<PRESENTATION> presentationType, Class<MODEL> modelType,
			Converter<PRESENTATION, MODEL> converter) {
		Map<Class<?>, Converter<?, ?>> convertersByModel = registry.get(presentationType);
		if (convertersByModel == null) {
			convertersByModel = new HashMap<Class<?>, Converter<?, ?>>();
			registry.put(presentationType, convertersByModel);
		}
		convertersByModel.put(modelType, converter);
	}

	@Override
	protected <PRESENTATION, MODEL> Converter<PRESENTATION, MODEL> findConverter(Class<PRESENTATION> presentationType,
			Class<MODEL> modelType) {
		Map<Class<?>, Converter<?, ?>> convertersByModel = registry.get(presentationType);
		if (convertersByModel != null) {
			@SuppressWarnings("unchecked")
			Converter<PRESENTATION, MODEL> converter = (Converter<PRESENTATION, MODEL>) convertersByModel
					.get(modelType);
			if (converter != null) {
				return converter;
			}
		}

		// Let default factory handle the rest
		return super.findConverter(presentationType, modelType);
	}

}
