package com.biit.koobepopserver.gui;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import com.biit.koobepopserver.logger.KoobepopLogger;
import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.server.SpringVaadinServlet;

public class KoobepopServlet extends SpringVaadinServlet implements SessionDestroyListener, SessionInitListener {
	private static final long serialVersionUID = -5198329062506096945L;

	@Override
	protected void servletInitialized() throws ServletException {
		super.servletInitialized();
		KoobepopLogger.info(KoobepopServlet.class.getName(), "Servlet initialization");
		this.getService().addSessionInitListener(this);
		this.getService().addSessionDestroyListener(this);
	}

	@WebListener
	public static class MyContextLoaderListener extends ContextLoaderListener {
	}

	@Configuration
	@EnableVaadin
	public static class MyConfiguration {
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		KoobepopLogger.info(KoobepopServlet.class.getName(),
				"Vaadin Session initialized '" + event.getSession().getSession().getId() + "'");
		event.getSession().setConverterFactory(new BiitConverterFactory());
	}

	@Override
	public void sessionDestroy(SessionDestroyEvent event) {
		KoobepopLogger.info(KoobepopServlet.class.getName(), "Vaadin Session destroyed");
		CredentialsRegistry.removeSession(event.getSession());
	}

}
