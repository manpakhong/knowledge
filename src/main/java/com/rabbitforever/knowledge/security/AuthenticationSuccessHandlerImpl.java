package com.rabbitforever.knowledge.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.rabbitforever.knowledge.models.eos.AppUser;
import com.rabbitforever.knowledge.models.eos.AppUserPrincipal;
import com.rabbitforever.knowledge.services.UserService;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private WebApplicationContext context;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
			throws IOException, ServletException {
		Authentication authentication = userService.getCurrentAuthentication();
		Object principal = authentication.getPrincipal();
		AppUserPrincipal appUserPrincipal = (AppUserPrincipal) principal;
		AppUser appUser = appUserPrincipal.getAppUser();
		appUser.setLastLogin(new Date());
		userService.saveUser(appUser);
	}
}