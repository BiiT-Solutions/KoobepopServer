package com.biit.koobepopserver.gui.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.biit.koobepopserver.core.security.ISecurityService;
import com.biit.koobepopserver.gui.language.LanguageCode;
import com.biit.vaadin.webpages.login.ILoginPresenter;
import com.biit.vaadin.webpages.login.LoginPresenter;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

@UIScope
@SpringComponent
public class KoobepopLoginPresenter extends LoginPresenter implements ILoginPresenter {

	@Autowired
	private ISecurityService koobepopSecurityService;

	@Override
	public String getLoginCaption() {
		return  LanguageCode.LOGIN_CAPTION_SIGN_IN.translation();
	}

	@Override
	public String getEmailCaption() {
		return LanguageCode.LOGIN_CAPTION_EMAIL.translation();
	}

	@Override
	public String getPasswordCaption() {
		return LanguageCode.LOGIN_CAPTION_PASSWORD.translation();
	}

	@Override
	public String getUsernameRequiredErrorCaption() {
		return LanguageCode.LOGIN_ERROR_EMAIL.translation();
	}

	@Override
	public String getPasswordRequiredErrorCaption() {
		return LanguageCode.LOGIN_ERROR_PASSWORD.translation();
	}

	@Override
	public String getNameVersion() {
		return LanguageCode.LOGIN_SCREEN_SYSTEM_NAME.translation(getCastedView().getVersion());
	}

	@Override
	public void checkUserAndPassword(String username, String password) {
		if (username == null || username.isEmpty()) {
			getCastedView().getUsernameField().setRequiredError(getUsernameRequiredErrorCaption());
		}
		if (password == null || password.isEmpty()) {
			getCastedView().getPasswordField().setRequiredError(getPasswordRequiredErrorCaption());
		}

		// try {
		// koobepopSecurityService.login(username, password);
		// UserSessionHandler.navigateToUserMainView();
		// } catch (InvalidCredentialsException e) {
		// getCastedView().setPasswordError(LanguageCode.LOGIN_ERROR_USER.translation(username));
		// UsmoMessageManager.showError(LanguageCode.ERROR_BADUSERPSWD,
		// LanguageCode.ERROR_TRYAGAIN);
		// } catch (UserManagementException | AuthenticationRequired e) {
		// UsmoLogger.errorMessage(this.getClass().getName(), e);
		// UsmoMessageManager.showError(LanguageCode.ERROR_USER_SERVICE,
		// LanguageCode.ERROR_CONTACT);
		// } catch (InsufficientPermissions e) {
		// UsmoMessageManager.showError(LanguageCode.ERROR_INSUFFICIENT_PERMISSIONS,
		// LanguageCode.ERROR_CONTACT);
		// }
	}

	@Override
	public void init() {
		// Do nothing
	}

}
