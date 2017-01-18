package com.biit.koobepopserver.gui.webpages;

import org.springframework.beans.factory.annotation.Autowired;

import com.biit.vaadin.mvp.MVPVaadinView;
import com.biit.vaadin.webpages.login.ILoginPresenter;
import com.biit.vaadin.webpages.login.ILoginView;
import com.biit.vaadin.webpages.login.LoginView;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringView(name = KoobepopLogin.NAME)
public class KoobepopLogin extends MVPVaadinView<ILoginView, ILoginPresenter>{
	private static final long serialVersionUID = 8674023750326851914L;

	public static final String NAME = "login";

	@Autowired
	private ILoginPresenter koobepopLoginPresenter;

	@Override
	protected ILoginPresenter instanciatePresenter() {
		return koobepopLoginPresenter;
	}

	@Override
	protected ILoginView instanciateView() {
		return new LoginView();
	}
	
}
